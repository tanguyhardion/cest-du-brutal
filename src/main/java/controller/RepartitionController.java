package controller;

import model.*;
import view.*;

import java.awt.event.ActionEvent;
import java.util.List;

/**
 * Contrôle les actions de la répartition des troupes.
 *
 * @author Tristan JAUSSAUD
 */
public class RepartitionController {

    /**
     * La vue de la répartition des troupes.
     */
    private final Repartition repartition;

    /**
     * La fenêtre principale.
     */
    private final MainWindow mainWindow;

    /**
     * Le premier joueur.
     */
    private final Joueur joueur1;

    /**
     * Le deuxième joueur.
     */
    private final Joueur joueur2;

    /**
     * Les zones du jeu.
     */
    private List<Zone> zones;

    /**
     * Contrôleur de la répartition des troupes.
     *
     * @param repartition la vue de la répartition des troupes
     * @param mainWindow la fenêtre principale
     * @param joueur1 le premier joueur
     * @param joueur2 le deuxième joueur
     */
    public RepartitionController(Repartition repartition, MainWindow mainWindow, Joueur joueur1, Joueur joueur2) {
        this.repartition = repartition;
        this.mainWindow = mainWindow;
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;

        this.repartition.getJoueursComboBox().addActionListener(this::joueurChanged);

        this.repartition.getZonesComboBox().addActionListener(this::zoneChanged);

        this.repartition.getJoueursComboBox().addItem(joueur1);
        this.repartition.getJoueursComboBox().addItem(joueur2);

        List<Zone> zones = Partie.getInstance().getZones();
        this.zones = zones;

        for (Zone zone : zones) {
            this.repartition.getZonesComboBox().addItem(zone);
        }

        this.repartition.getZonesComboBox().setSelectedIndex(0);

        this.repartition.getAddButton().addActionListener(this::addButtonClicked);

        this.repartition.getAleatoireButton().addActionListener(this::aleatoireButtonClicked);

        this.repartition.getSuivantButton().addActionListener(this::suivantButtonClicked);
    }

    /**
     * Gère l'événement de changement de joueur.
     *
     * @param e L'événement.
     */
    private void joueurChanged(ActionEvent e) {
        Joueur joueur = (Joueur) this.repartition.getJoueursComboBox().getSelectedItem();

        this.repartition.getTroupesComboBox().removeAllItems();
        for (Etudiant etudiant : joueur.getTroupes()) {
            this.repartition.getTroupesComboBox().addItem(etudiant);
        }

        Zone zone = (Zone) this.repartition.getZonesComboBox().getSelectedItem();
        if (zone != null) {
            this.repartition.getSurZoneList().setListData(zone.getTroupes(joueur).toArray());
        }
    }

    /**
     * Gère l'événement de changement de zone.
     *
     * @param e L'événement.
     */
    private void zoneChanged(ActionEvent e) {
        Joueur joueur = (Joueur) this.repartition.getJoueursComboBox().getSelectedItem();

        this.repartition.getTroupesComboBox().removeAllItems();
        for (Etudiant etudiant : joueur.getTroupes()) {
            this.repartition.getTroupesComboBox().addItem(etudiant);
        }

        Zone zone = (Zone) this.repartition.getZonesComboBox().getSelectedItem();
        if (zone != null) {
            this.repartition.getSurZoneList().setListData(zone.getTroupes(joueur).toArray());
        }
    }

    /**
     * Gère l'événement de clic sur le bouton d'ajout d'étudiant.
     *
     * @param e l'événement.
     */
    private void addButtonClicked(ActionEvent e) {
        Joueur joueur = (Joueur) this.repartition.getJoueursComboBox().getSelectedItem();
        // S'il y a encore des troupes à déployer
        if (joueur.getTroupes().size() > 0) {
            Zone zone = (Zone) this.repartition.getZonesComboBox().getSelectedItem();
            // S'il n'y a plus assez d'étudiants pour avoir au moins un combattant par zone
            if (zones.stream().filter(z -> z.getTroupes(joueur).isEmpty()).count() == joueur.getTroupes().size()
                    && !zone.getTroupes(joueur).isEmpty()) {
                SystemDialog.showDialog("Vous ne pouvez plus déployer de troupes dans cette zone !",
                        SystemDialog.Type.ERROR);
            } else {
                Etudiant etudiant = (Etudiant) this.repartition.getTroupesComboBox().getSelectedItem();
                etudiant.setZone(zone);
                zone.addCombattant(etudiant);
                joueur.removeEtudiant(etudiant);
            }
        } else {
            SystemDialog.showDialog("Vous avez déployé toutes vos troupes !", SystemDialog.Type.ERROR);
        }
    }

    /**
     * Gère l'événement de clic sur le bouton de répartition aléatoire.
     *
     * @param e l'événement.
     */
    private void aleatoireButtonClicked(ActionEvent e) {
        Joueur joueur = (Joueur) this.repartition.getJoueursComboBox().getSelectedItem();
        if (!joueur.getTroupes().isEmpty()) {
            joueur.repartirTroupesAleatoirement();
        } else {
            SystemDialog.showDialog("Vous avez déployé toutes vos troupes !", SystemDialog.Type.ERROR);
        }
    }

    /**
     * Gère l'événement de clic sur le bouton suivant.
     *
     * @param e l'événement
     */
    private void suivantButtonClicked(ActionEvent e) {
        if (zones.stream().anyMatch(zone -> zone.getTroupes(this.joueur1).isEmpty()
                || zone.getTroupes(this.joueur2).isEmpty())) {
            SystemDialog.showDialog("Chaque joueur doit placer au moins un combattant dans chaque zone !",
                    SystemDialog.Type.ERROR);
        } else {
            mainWindow.switchPanel(new Combat(joueur1, joueur2));
        }
    }

}
