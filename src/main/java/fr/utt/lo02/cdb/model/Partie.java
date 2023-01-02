package fr.utt.lo02.cdb.model;

import fr.utt.lo02.cdb.model.enums.*;
import fr.utt.lo02.cdb.model.themes.*;
import fr.utt.lo02.cdb.view.*;
import mdlaf.MaterialLookAndFeel;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
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

    /**
     * Constructeur privé de la classe Partie.
     * <p>
     * Initialise les joueurs et les zones de la partie.
     */
    private Partie() {
        this.joueur1 = new Joueur(Equipe.UNE);
        this.joueur2 = new Joueur(Equipe.DEUX);
        this.zones = new ArrayList<>();

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
            // Définition du thème Material
            try {
                UIManager.setLookAndFeel(new MaterialLookAndFeel(new JMars()));
            } catch (UnsupportedLookAndFeelException e) {
                throw new RuntimeException(e);
            }

            // Création de la fenêtre principale
            MainWindow mainWindow = new MainWindow();
            // Création de la page d'accueil
            Accueil accueil = new Accueil(mainWindow, joueur1, joueur2);
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
    public void gerer() throws InterruptedException {
        // Nombre de zones à contrôler pour gagner la partie
        int zonesAControler = (int) Math.floor(this.zones.size() / 2) + 1;
        // On créé un pool de threads pour les zones
        ExecutorService executor = Executors.newFixedThreadPool(this.zones.size());

        // On lance le combat sur chaque zone
        for (Zone zone : this.zones) {
            executor.execute(zone);
        }


        executor.shutdownNow();
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
     * @param nomZone le nom de la zone demandée
     * @return la zone correspondant au nom donné
     */
    public Zone getBibliotheque(NomZone nomZone) {
        return this.zones.stream().filter(zone -> zone.getNom() == nomZone).findFirst().orElse(null);
    }

}