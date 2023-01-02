package fr.utt.lo02.cdb.model;

import fr.utt.lo02.cdb.model.enums.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;
import java.util.Random;

/**
 * Représente un joueur du jeu, possédant des troupes.
 *
 * @author Tristan JAUSSAUD
 * @author Tanguy HARDION
 */
public class Joueur extends Observable {

    private int points;
    private Equipe equipe;
    private Filiere filiere;
    private List<Etudiant> troupes;
    private List<Etudiant> reservistes;
    private List<Zone> zonesControlees;

    /**
     * Constructeur de la classe Joueur.
     * <p>
     * Initialise les différents attributs de ce joueur.
     *
     * @param equipe l'équipe du joueur
     */
    public Joueur(Equipe equipe) {
        this.equipe = equipe;
        this.points = 400;
        this.troupes = new ArrayList<>();
        this.reservistes = new ArrayList<>();
        this.zonesControlees = new ArrayList<>();
    }

    /**
     * Initialise les troupes de ce joueur en créeant et en lui attribuant le nombre souhaité de chaque type
     * d'étudiant.
     *
     * @param etudiants      le nombre d'étudiants à créer
     * @param etudiantsElite le nombre d'étudiants d'élite à créer
     * @param maitresGobi    le nombre de Maîtres du gobi à créer
     * @param equipe         l'équipe à laquelle appartiennent les troupes créées
     */
    public void initialiserTroupes(int etudiants, int etudiantsElite, int maitresGobi, Equipe equipe) {
        for (int i = 1; i <= etudiants + etudiantsElite + maitresGobi; i++) {
            if (i <= etudiants) {
                this.addEtudiant(new Etudiant(equipe, i));
            } else if (i <= etudiants + etudiantsElite) {
                this.addEtudiant(new EtudiantElite(equipe, i));
            } else {
                this.addEtudiant(new MaitreGobi(equipe, i));
            }
        }
    }

    /**
     * Répartit aléatoirement les combattants restants du joueur sur les zones du jeu.
     */
    public void repartirTroupesAleatoirement() {
        List<Zone> zones = Partie.getInstance().getZones();
        // Tant qu'il reste des combattants à déployer
        while (this.getTroupes().size() > 0) {
            final Random r = new Random();
            for (Zone zone : zones) {
                // Si le joueur n'a plus de combattants à déployer, on sort du for
                if (this.getTroupes().isEmpty()) {
                    break;
                }
                // Sinon, on choisit un combattant au hasard parmi ceux restants
                int index = r.nextInt(this.troupes.size());
                // On récupère le combattant
                Etudiant etudiant = this.getTroupes().get(index);
                // On ajoute le combattant choisi à la zone en cours
                zone.addCombattant(etudiant);
                // On enlève le combattant des troupes du joueur
                this.removeEtudiant(etudiant);
            }
        }
    }

    /**
     * Paramètre aléatoirement les troupes de ce joueur, en leur attribuant une valeur aléatoire
     * pour chacune de leur caractéristiques.
     * <p>
     * Cette méthode choisit aussi 5 réservistes aléatoirement parmi les étudiants.
     *
     * @param strategies liste dans laquelle on va choisir une stratégie aléatoirement
     */
    public void parametrerTroupesAleatoirement(List<StrategieEtudiant> strategies) {
        final Random random = new Random();

        // Pour toutes les troupes de ce joueur
        for (Etudiant etudiant : this.getTroupes()) {
            // On choisit une stratégie aléatoirement (0, 1 ou 2)
            etudiant.setStrategie(strategies.get(random.nextInt(3)));

            // Pour chaque caractéristique, on attribue une valeur aléatoire entre 0 et 8.
            // De cette manière, en moyenne, la somme des valeurs sera égale à 400.
            // Sachant qu'on choisit un nombre aléatoire entre 0 et 8 : valeurMoyenne = 4
            // donc 20 combattants * 5 caractéristiques * valeurMoyenne = 400 points
            etudiant.setDexterite(random.nextInt(9));
            etudiant.setForce(random.nextInt(9));
            etudiant.setResistance(random.nextInt(9));
            etudiant.setConstitution(random.nextInt(9));
            etudiant.setInitiative(random.nextInt(9));
        }

        // Pour les réservistes restant à choisir, on les choisit aléatoirement
        int reservistes = this.getTroupes().size() / 4;
        for (int i = 0; i < reservistes; i++) {
            Etudiant etudiant = this.troupes.get(random.nextInt(this.troupes.size()));
            etudiant.setReserviste(true);
            this.addReserviste(etudiant);
            this.removeEtudiant(etudiant);
        }

        // On met les points de ce joueur à 0
        this.setPoints(0);

        // On notifie les observateurs que les troupes ont été paramétrées
        this.setChanged();
        this.notifyObservers(new Etudiant(null, 0));
    }

    private void trierTroupes() {
        this.troupes.sort(Comparator.comparingInt(Etudiant::getId));
    }

    private void trierReservistes() {
        this.reservistes.sort(Comparator.comparingInt(Etudiant::getId));
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
        this.setChanged();
        this.notifyObservers(this.points);
    }

    public void incrementerPoints() {
        this.points++;
        this.setChanged();
        this.notifyObservers(this.points);
    }

    public void decrementerPoints() {
        this.points--;
        this.setChanged();
        this.notifyObservers(this.points);
    }

    /**
     * @return l'équipe de ce joueur
     */
    public Equipe getEquipe() {
        return this.equipe;
    }

    /**
     * @return la filière de ce joueur
     */
    public Filiere getFiliere() {
        return this.filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    /**
     * @return les troupes de ce joueur
     */
    public List<Etudiant> getTroupes() {
        return this.troupes;
    }

    public Etudiant getEtudiant(int id) {
        return this.troupes.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    /**
     * Ajoute un étudiant à la liste des troupes de ce joueur et notifie les observateurs.
     * <p>
     * Cette méthode trie également les troupes par ID.
     *
     * @param etudiant l'étudiant à ajouter
     */
    public void addEtudiant(Etudiant etudiant) {
        this.troupes.add(etudiant);
        this.trierTroupes();
        this.setChanged();
        this.notifyObservers(etudiant);
    }

    /**
     * Supprime un étudiant de la liste des troupes de ce joueur et notifie les observateurs.
     * <p>
     * Cette méthode trie également les troupes par ID.
     *
     * @param etudiant l'étudiant à supprimer
     */
    public void removeEtudiant(Etudiant etudiant) {
        this.troupes.remove(etudiant);
        this.trierTroupes();
        this.setChanged();
        this.notifyObservers(etudiant);
    }

    /**
     * @return les réservistes de ce joueur
     */
    public List<Etudiant> getReservistes() {
        return this.reservistes;
    }

    /**
     * Ajoute un étudiant à la liste des réservistes de ce joueur et les trie par ID.
     *
     * @param etudiant l'étudiant à ajouter
     */
    public void addReserviste(Etudiant etudiant) {
        this.reservistes.add(etudiant);
        this.trierReservistes();
    }

    /**
     * Supprime un étudiant de la liste des réservistes de ce joueur et notifie les observateurs.
     * <p>
     * Cette méthode trie également les réservistes par ID.
     *
     * @param reserviste le réserviste à retirer
     */
    public void removeReserviste(Etudiant reserviste) {
        this.reservistes.remove(reserviste);
        this.trierReservistes();
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * @return les zones contrôlées par ce joueur
     */
    public List<Zone> getZonesControlees() {
        return this.zonesControlees;
    }

    /**
     * Ajoute une zone à la liste des zones contrôlées par ce joueur
     * et notifie les observateurs.
     *
     * @param zone la zone à ajouter
     */
    public void addZoneControlee(Zone zone) {
        this.zonesControlees.add(zone);
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public String toString() {
        return "Joueur " + this.filiere;
    }

}