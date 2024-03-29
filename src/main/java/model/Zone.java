package model;

import model.enums.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * Représente une zone d'influence sur laquelle des combattants seront déployés.
 *
 * @author Tanguy HARDION
 * @author Tristan JAUSSAUD
 * @version 2.1
 */
public class Zone extends Observable implements Runnable {

    /**
     * Barrière cyclique statique pour synchroniser le début des combats.
     */
    private static final CyclicBarrier barrier = new CyclicBarrier(NomZone.values().length);

    /**
     * CountDownLatch statique pour notifier la partie qu'une zone a fini son combat.
     */
    private static CountDownLatch partieLatch = new CountDownLatch(1);

    /**
     * CountDownLatch statique pour que la partie notifie les zones que la trêve est finie.
     */
    private static CountDownLatch zoneLatch = new CountDownLatch(1);

    /**
     * Détermine si la trêve est en cours. Volatile pour s'assurer que les threads voient la bonne valeur.
     */
    private static volatile boolean treveDeclaree;

    /**
     * Le nom de la zone.
     */
    private final NomZone nomZone;

    /**
     * Le joueur qui contrôle la zone.
     */
    private Joueur controleur;

    /**
     * Détermine si la zone est contrôlée par un joueur.
     */
    private boolean controlee;

    /**
     * La liste des troupes de l'équipe 1.
     */
    private List<Etudiant> troupesEquipe1;

    /**
     * La liste des troupes de l'équipe 2.
     */
    private List<Etudiant> troupesEquipe2;

    /**
     * Constructeur de la classe Zone.
     * <p>
     * Initialise les attributs de cette zone, notamment son nom avec la valeur passée en paramètre.
     *
     * @param nom le nom de la zone, de type NomZone
     */
    public Zone(NomZone nom) {
        this.nomZone = nom;
        this.troupesEquipe1 = new ArrayList<Etudiant>();
        this.troupesEquipe2 = new ArrayList<Etudiant>();
    }

    /**
     * @return le latch qui notifie la Partie
     */
    public static CountDownLatch getPartieLatch() {
        return partieLatch;
    }

    /**
     * Réinitialise le latch qui notifie la Partie.
     */
    public static void resetPartieLatch() {
        partieLatch = new CountDownLatch(1);
    }

    /**
     * @return le latch qui notifie les zones
     */
    public static CountDownLatch getZoneLatch() {
        return zoneLatch;
    }

    /**
     * Réinitialise le latch qui notifie les zones.
     */
    public static void resetZoneLatch() {
        zoneLatch = new CountDownLatch(1);
    }

    /**
     * Termine la trêve et notifie les threads en attente.
     */
    public static void finirTreve() {
        // On marque la trêve comme terminée
        treveDeclaree = false;
        // On notifie les zones que la trêve est terminée
        zoneLatch.countDown();
        // On réinitialise le latch qui notifie les zones
        resetZoneLatch();
    }

    /**
     * Attend que toutes les zones soient prêtes, puis lance le combat.
     */
    @Override
    public void run() {
        try {
            barrier.await();
            this.lancerCombat();
        } catch (InterruptedException | BrokenBarrierException ignored) {
        }
    }

    /**
     * Lance le combat dans cette zone dans un thread séparé.
     *
     * @throws InterruptedException si le thread est interrompu
     */
    private synchronized void lancerCombat() throws InterruptedException {
        while (!this.getTroupesEquipe1().isEmpty() && !this.getTroupesEquipe2().isEmpty()) {
            for (Etudiant etudiant : this.getTroupesParInitiative()) {
                if (!this.getTroupesEquipe1().isEmpty() && !this.getTroupesEquipe2().isEmpty() && !treveDeclaree) {
                    // On fait agir l'étudiant (attaquer ou soigner)
                    etudiant.agir(this.getLowestCredits(Equipe.UNE), this.getLowestCredits(Equipe.DEUX));
                    // On enlève les combattants éliminés
                    this.clearCombattantsElimines();
                    // On vérifie que chaque équipe a au moins une stratégie offensive ou aléatoire
                    this.verifierStrategies();
                } else {
                    break;
                }
            }
            // On attend un peu, au cas où un thread aurait déclaré la trêve
            Thread.sleep(new Random().nextLong(10, 50));
            if (treveDeclaree) {
                // Si la trêve est déclarée, on notifie les observateurs
                this.setChanged();
                this.notifyObservers();
                // Et on attend la fin de la trêve avant de reprendre
                zoneLatch.await();
            }
        }
        // On sort du while, donc la zone est forcément contrôlée par un joueur
        // On déclare la trêve et on note la zone comme contrôlée
        treveDeclaree = true;
        this.controlee = true;
        // On récupère les joueurs
        Joueur joueur1 = Partie.getInstance().getJoueur1();
        Joueur joueur2 = Partie.getInstance().getJoueur2();
        if (this.getTroupesEquipe2().isEmpty()) {
            // Le joueur 1 contrôle la zone
            joueur1.addZoneControlee(this);
            this.controleur = joueur1;
        } else if (this.getTroupesEquipe1().isEmpty()) {
            // Le joueur 2 contrôle la zone
            joueur2.addZoneControlee(this);
            this.controleur = joueur2;
        }
        // On notifie la vue qu'un thread est terminé
        this.setChanged();
        this.notifyObservers(this);
    }

    /**
     * Ajoute un étudiant d'un joueur aux combattants du joueur correspondant sur cette zone.
     *
     * @param etudiant l'étudiant à ajouter
     */
    public void addCombattant(Etudiant etudiant) {
        if (etudiant == null) {
            throw new IllegalArgumentException("Étudiant incorrect.");
        }
        if (etudiant.getEquipe() == Equipe.UNE) {
            this.troupesEquipe1.add(etudiant);
        } else if (etudiant.getEquipe() == Equipe.DEUX) {
            this.troupesEquipe2.add(etudiant);
        }
    }

    /**
     * Retire un étudiant d'un joueur des combattants du joueur correspondant sur cette zone.
     *
     * @param etudiant l'étudiant à retirer
     */
    public void removeCombattant(Etudiant etudiant) {
        if (etudiant == null) {
            throw new IllegalArgumentException("Joueur ou étudiant incorrect.");
        }
        if (etudiant.getEquipe() == Equipe.UNE) {
            this.troupesEquipe1.remove(etudiant);
        } else if (etudiant.getEquipe() == Equipe.DEUX) {
            this.troupesEquipe2.remove(etudiant);
        }
    }

    /**
     * Retourne la liste de tous les étudiants présents sur cette zone triés par leur initiative, de la plus grande à la
     * plus petite.
     *
     * @return la liste des étudiants triés par initiative
     */
    private List<Etudiant> getTroupesParInitiative() {
        List<Etudiant> troupes = new ArrayList<>();
        troupes.addAll(this.troupesEquipe1);
        troupes.addAll(this.troupesEquipe2);
        troupes.sort(Comparator.comparingInt(Etudiant::getInitiative).reversed());
        return troupes;
    }

    /**
     * Retourne l'étudiant de l'équipe correspondante ayant le moins de crédits sur cette zone.
     *
     * @param equipe l'équipe dont on veut l'étudiant ayant le moins de crédits
     * @return l'étudiant ayant le moins de crédits
     */
    private Etudiant getLowestCredits(Equipe equipe) {
        List<Etudiant> troupes = new ArrayList<>();
        switch (equipe) {
            case UNE:
                troupes = new ArrayList<>(this.troupesEquipe1);
                break;
            case DEUX:
                troupes = new ArrayList<>(this.troupesEquipe2);
                break;
            default:
                throw new IllegalArgumentException("Équipe incorrecte.");
        }
        troupes.sort(Comparator.comparingInt(Etudiant::getCreditsTotal));
        return troupes.get(0);
    }

    /**
     * Retourne le total des crédits des étudiants du Joueur 1 présents sur cette zone.
     *
     * @return le total des crédits des étudiants du Joueur 1
     */
    public int getCreditsEquipeUne() {
        int credits = 0;
        for (Etudiant e : this.troupesEquipe1) {
            credits += e.getCreditsTotal();
        }
        return credits;
    }

    /**
     * Retourne le total des crédits des étudiants du Joueur 2 présents sur cette zone.
     *
     * @return le total des crédits des étudiants du Joueur 2
     */
    public int getCreditsEquipeDeux() {
        int credits = 0;
        for (Etudiant e : this.troupesEquipe2) {
            credits += e.getCreditsTotal();
        }
        return credits;
    }

    /**
     * Retourne le total des crédits de tous les étudiants présents sur cette zone.
     *
     * @return le total des crédits de tous les étudiants
     */
    public int getCredits() {
        return this.getCreditsEquipeUne() + this.getCreditsEquipeDeux();
    }

    /**
     * Enlève les étudiants éliminés sur cette zone de la liste des combattants de chaque équipe.
     */
    private void clearCombattantsElimines() {
        this.troupesEquipe1.removeIf(Etudiant::isElimine);
        this.troupesEquipe2.removeIf(Etudiant::isElimine);
    }

    /**
     * Vérifie qu'il existe assez de combattants qui possèdent une stratégie offensive ou aléatoire sur cette zone, afin
     * que le combat ne soit pas bloqué.
     */
    private void verifierStrategies() {
        List<Etudiant> tempEquipe1 = new ArrayList<>(this.troupesEquipe1);
        List<Etudiant> tempEquipe2 = new ArrayList<>(this.troupesEquipe2);

        // On enlève les étudiants qui ont une stratégie offensive ou aléatoire
        tempEquipe1.removeIf(e -> e.getStrategie() instanceof StrategieDefensive);
        tempEquipe2.removeIf(e -> e.getStrategie() instanceof StrategieDefensive);

        // Si il n'y a plus assez d'étudiants avec une stratégie offensive ou aléatoire
        if (!this.troupesEquipe1.isEmpty() && (tempEquipe1.size() == Math.floor(this.troupesEquipe1.size() - 1)
                || tempEquipe1.size() == this.troupesEquipe1.size())) {
            // On choisit un étudiant au hasard et on lui donne une stratégie offensive
            Random r = new Random();
            Etudiant etudiantRandom = this.troupesEquipe1.get(r.nextInt(this.troupesEquipe1.size()));
            etudiantRandom.setStrategie(new StrategieOffensive());
        }

        // Même principe pour l'équipe 2
        if (!this.troupesEquipe2.isEmpty() && (tempEquipe2.size() == Math.floor(this.troupesEquipe2.size() - 1)
                || tempEquipe2.size() == this.troupesEquipe2.size())) {
            Random r = new Random();
            Etudiant etudiantRandom = this.troupesEquipe2.get(r.nextInt(this.troupesEquipe2.size()));
            etudiantRandom.setStrategie(new StrategieOffensive());
        }
    }

    /**
     * @return {@code true} si cette zone est contrôlée par un des joueurs, {@code false} sinon
     */
    public boolean estControlee() {
        return controlee;
    }

    /**
     * @return le nom de cette zone
     */
    public NomZone getNomZone() {
        return this.nomZone;
    }

    /**
     * @return le joueur qui contrôle cette zone
     */
    public Joueur getControleur() {
        return this.controleur;
    }

    /**
     * @return les troupes du Joueur 1 présentes sur cette zone
     */
    public List<Etudiant> getTroupesEquipe1() {
        return troupesEquipe1;
    }

    /**
     * @return les troupes du Joueur 2 présentes sur cette zone
     */
    public List<Etudiant> getTroupesEquipe2() {
        return troupesEquipe2;
    }

    /**
     * Retourne la liste des étudiants présents sur cette zone pour le joueur passé en paramètre.
     * <p>
     * Cette méthode a pour but de simplifier la récupération des troupes lorsqu'on connaît le joueur dont on veut les
     * troupes.
     *
     * @param joueur le joueur dont on veut les troupes
     * @return la liste des étudiants présents sur cette zone pour le joueur
     */
    public List<Etudiant> getTroupes(Joueur joueur) {
        if (joueur == null) {
            throw new IllegalArgumentException("Joueur incorrect.");
        }
        if (joueur.getEquipe() == Equipe.UNE) {
            return new ArrayList<Etudiant>(this.troupesEquipe1);
        } else if (joueur.getEquipe() == Equipe.DEUX) {
            return new ArrayList<Etudiant>(this.troupesEquipe2);
        }
        return null;
    }

    @Override
    public String toString() {
        return this.nomZone.toString();
    }

}