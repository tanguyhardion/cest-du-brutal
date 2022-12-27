package fr.utt.lo02.cdb.model;

import fr.utt.lo02.cdb.view.Accueil;
import fr.utt.lo02.cdb.view.MainWindow;

import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Hashtable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.MaterialOceanicTheme;

/**
 * Classe principale du jeu, implémentant un Singleton.
 *
 * @author Tristan JAUSSAUD
 * @author Tanguy HARDION
 * @version 1.0
 */
public class Partie {

    private static Partie instance;
    private final Joueur joueur1;
    private final Joueur joueur2;
    private List<Zone> zones;
    private Scanner scanner;

    /**
     * Constructeur privé de la classe Partie.
     * <p>
     * Initialise les joueurs et les zones de la partie.
     */
    private Partie() {
        this.joueur1 = new Joueur(Equipe.UNE);
        this.joueur2 = new Joueur(Equipe.DEUX);
        this.zones = new ArrayList<>();
        this.scanner = new Scanner(System.in);

        // On récupère tous les noms de zones
        Set<NomZone> zones = EnumSet.allOf(NomZone.class);
        // On crée une zone pour chaque nom de zone
        for (NomZone nom : zones) {
            this.zones.add(new Zone(nom));
        }
    }

    /**
     * Crée l'instance de la Partie, si elle n'existe pas encore, et la renvoie.
     *
     * @return l'instance de la Partie
     */
    public static synchronized Partie getInstance() {
        if (instance == null) {
            instance = new Partie();
        }
        return instance;
    }

    /**
     * Commence et gère la partie.
     */
    public void commencer() {
        // On initialise les troupes de chaque joueur
        // (15 étudiants, 4 étudiants d'élite, 1 Maître du gobi)
        joueur1.initialiserTroupes(15, 4, 1, Equipe.UNE);
        joueur2.initialiserTroupes(15, 4, 1, Equipe.DEUX);

        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new MaterialLookAndFeel(new MaterialOceanicTheme()));
            } catch (UnsupportedLookAndFeelException e) {
                throw new RuntimeException(e);
            }

            MainWindow mainWindow = new MainWindow();
            Accueil accueil = new Accueil(joueur1, joueur2, mainWindow);
            mainWindow.switchPanel(accueil);
            mainWindow.setVisible(true);
        });

        // On demande à chaque joueur de choisir sa filière
        joueur1.demanderFiliere(null);
        joueur2.demanderFiliere(joueur1.getFiliere());

        boolean parametrageManuel = false;

        // On vérifie que le choix est "oui" ou "non"
        String choix = this.verifierChoix(this.scanner.next().toLowerCase(), "oui", "non");

        // Si le choix est "oui", on paramètre les troupes aléatoirement
        if (choix.equals("oui")) {
            this.parametrerTroupesAleatoirement();
        } else {
            // Sinon, on se souvient que le paramétrage est manuel
            parametrageManuel = true;
        }

        /* Joueur 1 */
        // Si le paramétrage est manuel
        if (parametrageManuel) {
            this.joueur1.parametrerTroupes();
        }
        // Choix des réservistes
        joueur1.choisirReservistes();
        // Répartition des troupes
        joueur1.repartirTroupes(this.zones);

        /* Joueur 2 */
        // On répète les mêmes étapes
        if (parametrageManuel) {
            this.joueur2.parametrerTroupes();
        }
        joueur2.choisirReservistes();
        joueur2.repartirTroupes(this.zones);

        // Setup effectué, on lance la partie
        try {
            this.gerer();
        } catch (InterruptedException ignored) {
        }
    }

    /**
     * Gère le déroulement de la partie.
     *
     * @throws InterruptedException si une zone est interrompue lors du combat
     */
    private void gerer() throws InterruptedException {
        // Nombre de zones à contrôler pour gagner la partie
        int zonesAControler = (int) Math.floor(this.zones.size() / 2) + 1;
        // On créé un pool de threads pour les zones
        ExecutorService executor = Executors.newFixedThreadPool(this.zones.size());

        // On lance le combat sur chaque zone
        for (Zone zone : this.zones) {
            executor.execute(zone);
        }

        // Tant que personne n'a gagné
        while (this.joueur1.getZonesControlees().size() < zonesAControler
                && this.joueur2.getZonesControlees().size() < zonesAControler) {

            System.out.println("\nLancement des combats...");

            // On attend qu'une zone soit contrôlée
            Zone.getPartieLatch().await();

            // On attend un peu pour que les zones aient le temps de se mettre en pause
            Thread.sleep(10);

            // On vérifie si la partie est terminée
            if (this.joueur1.getZonesControlees().size() >= zonesAControler
                    || this.joueur2.getZonesControlees().size() >= zonesAControler) {
                break;
            }

            // On réinitialise le latch qui notifie la partie
            Zone.resetPartieLatch();

            // Zones où on peut affecter des réservistes et redéployer des troupes
            List<Zone> zonesNonControlees = new ArrayList<>(this.zones);
            zonesNonControlees.removeIf(zone -> zone.estControlee());

            // Actions pendant la trêve du joueur 1
            this.afficherCreditsZones();
            this.joueur1.affecterReservistes(zonesNonControlees, this.zones);
            this.joueur1.redeployerTroupes(zonesNonControlees, this.zones);

            // Actions pendant la trêve du joueur 2
            this.afficherCreditsZones();
            this.joueur2.affecterReservistes(zonesNonControlees, this.zones);
            this.joueur2.redeployerTroupes(zonesNonControlees, this.zones);

            // Fin de la trêve
            Zone.finirTreve();
            // On notifie les zones que la trêve est terminée
            Zone.getZoneLatch().countDown();
            // On réinitialise le latch qui notifie les zones
            Zone.resetZoneLatch();
        }

        // On récupère le gagnant
        Filiere filiereJoueurGagnant = this.joueur1.getZonesControlees().size() >= zonesAControler
                ? this.joueur1.getFiliere()
                : this.joueur2.getFiliere();

        System.out.println("Le Joueur " + filiereJoueurGagnant + " a gagné la partie !");

        executor.shutdownNow();
        this.scanner.close();
    }

    /**
     * Paramètre aléatoirement les troupes de chaque joueur, en leur attribuant une
     * valeur aléatoire pour chacune de leur caractéristiques.
     */
    private void parametrerTroupesAleatoirement() {
        // Hashtable dans laquelle on va choisir une stratégie aléatoirement
        Hashtable<Integer, StrategieEtudiant> strategie = new Hashtable<>();
        strategie.put(0, new StrategieAleatoire());
        strategie.put(1, new StrategieOffensive());
        strategie.put(2, new StrategieDefensive());

        final Random random = new Random();

        // Valeurs aléatoires pour les caractéristiques des troupes du joueur 1
        for (Etudiant etudiant : this.joueur1.getTroupes()) {
            // On choisit une stratégie aléatoirement (0, 1 ou 2)
            etudiant.setStrategie(strategie.get(random.nextInt(3)));

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

        // Même principe pour les troupes du joueur 2
        for (Etudiant etudiant : this.joueur2.getTroupes()) {
            etudiant.setStrategie(strategie.get(random.nextInt(3)));

            etudiant.setDexterite(random.nextInt(9));
            etudiant.setForce(random.nextInt(9));
            etudiant.setResistance(random.nextInt(9));
            etudiant.setConstitution(random.nextInt(9));
            etudiant.setInitiative(random.nextInt(9));
        }
    }

    /**
     * Affiche le nombre de crédits sur chaque zone.
     */
    private void afficherCreditsZones() {
        ArrayList<Zone> zonesNC = new ArrayList<>(this.zones);
        zonesNC.removeIf(zone -> zone.estControlee());

        System.out.println("\nCrédits par zones :   ");

        for (Zone zone : zonesNC) {
            System.out.print(zone.getNom() + " : " + (zone.getCreditsEquipeUne() + zone.getCreditsEquipeDeux()));
            // Si ce n'est pas la dernière zone, on affiche un tiret
            if (zonesNC.indexOf(zone) != zonesNC.size() - 1) {
                System.out.print(" - ");
            }
        }

        System.out.println();
    }

    /**
     * Vérifie le choix de l'utilisateur grâce aux réponses possibles et lui
     * redemande de saisir une réponse tant qu'il n'a pas saisi une réponse valide.
     *
     * @param choix    le choix de l'utilisateur
     * @param reponses les réponses possibles
     * @return le choix de l'utilisateur
     */
    private String verifierChoix(String choix, String... reponses) {
        // On crée un HashSet pour stocker les réponses possibles
        HashSet<String> reponsesPossibles = new HashSet<String>();
        for (String reponse : reponses) {
            reponsesPossibles.add(reponse);
        }

        // Message d'erreur
        String saisieIncorrecte = "Veuillez répondre par " + reponsesPossibles.toString() + ".";

        // Tant que le choix n'est pas "oui" ou "non"
        while (!reponsesPossibles.contains(choix)) {
            System.out.println(saisieIncorrecte);
            choix = this.scanner.next().toLowerCase();
        }

        return choix;
    }

    /**
     * @return le joueur 1
     */
    public Joueur getJoueur1() {
        return this.joueur1;
    }

    /**
     * @return le joueur 2
     */
    public Joueur getJoueur2() {
        return this.joueur2;
    }

    /**
     * @return les zones
     */
    public List<Zone> getZones() {
        return this.zones;
    }
}