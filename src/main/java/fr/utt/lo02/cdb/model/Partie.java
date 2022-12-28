package fr.utt.lo02.cdb.model;

import fr.utt.lo02.cdb.view.Accueil;
import fr.utt.lo02.cdb.view.MainWindow;
import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.MaterialOceanicTheme;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        // Initialisation des troupes de chaque joueur
        // (15 étudiants, 4 étudiants d'élite, 1 Maître du gobi)
        joueur1.initialiserTroupes(15, 4, 1, Equipe.UNE);
        joueur2.initialiserTroupes(15, 4, 1, Equipe.DEUX);

        // Lancement de l'interface graphique
        EventQueue.invokeLater(() -> {
            // Définition du thème Material Design
            try {
                UIManager.setLookAndFeel(new MaterialLookAndFeel(new MaterialOceanicTheme()));
            } catch (UnsupportedLookAndFeelException e) {
                throw new RuntimeException(e);
            }

            // Création de la fenêtre principale
            MainWindow mainWindow = new MainWindow();
            // Création de la page d'accueil
            Accueil accueil = new Accueil(joueur1, joueur2, mainWindow);
            // Ajout de la page d'accueil à la fenêtre principale
            mainWindow.switchPanel(accueil);
            // Affichage de la fenêtre principale
            mainWindow.setVisible(true);
        });
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

    /**
     * @return la zone BDE
     */
    public Zone getBDE() {
        return this.zones.stream().filter(zone -> zone.getNom() == NomZone.BDE).findFirst().orElse(null);
    }

    /**
     * @return la zone Bibliotheque
     */
    public Zone getBibliotheque() {
        return this.zones.stream().filter(zone -> zone.getNom() == NomZone.BIBLIOTHEQUE).findFirst().orElse(null);
    }

    /**
     * @return la zone Halles Industrielles
     */
    public Zone getHallesIndustrielles() {
        return this.zones.stream().filter(zone -> zone.getNom() == NomZone.HALLESINDUSTRIELLES).findFirst().orElse(null);
    }

    /**
     * @return la zone Halle Sportive
     */
    public Zone getHalleSportive() {
        return this.zones.stream().filter(zone -> zone.getNom() == NomZone.HALLESPORTIVE).findFirst().orElse(null);
    }

    /**
     * @return la zone Quartier Administratif
     */
    public Zone getQuartierAdministratif() {
        return this.zones.stream().filter(zone -> zone.getNom() == NomZone.QUARTIERADMINISTRATIF).findFirst().orElse(null);
    }
}