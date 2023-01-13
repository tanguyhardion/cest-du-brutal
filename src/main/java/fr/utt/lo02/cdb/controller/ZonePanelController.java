package fr.utt.lo02.cdb.controller;

import fr.utt.lo02.cdb.model.*;
import fr.utt.lo02.cdb.view.*;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Contrôle les actions du combat sur la zone du panel correspondant.
 */
public class ZonePanelController {

    /**
     * La vue du panel de la zone.
     */
    private final ZonePanel zonePanel;

    /**
     * La zone du panel.
     */
    private final Zone zone;

    /**
     * Les stratégies de combat.
     */
    private List<StrategieEtudiant> strategies;

    /**
     * Contrôleur du panel de la zone.
     *
     * @param zonePanel la vue du panel de la zone
     * @param zone      la zone du panel
     * @param joueur1   le premier joueur
     * @param joueur2   le deuxième joueur
     */
    public ZonePanelController(ZonePanel zonePanel, Zone zone, Joueur joueur1, Joueur joueur2) {
        this.zonePanel = zonePanel;
        this.zone = zone;

        zonePanel.getJoueursComboBox().addActionListener(this::joueurChanged);
        zonePanel.getTroupesComboBox().addActionListener(this::combattantChanged);
        zonePanel.getStrategiesComboBox().addActionListener(this::strategieChanged);
        zonePanel.getAddReservisteButton().addActionListener(this::reservisteAdded);
        zonePanel.getRedeployerButton().addActionListener(this::combattantRedeploye);

        zonePanel.getJoueursComboBox().addItem(joueur1);
        zonePanel.getJoueursComboBox().addItem(joueur2);

        zonePanel.getCreditsLabel().setText(String.valueOf(zone.getCredits()));

        // Ajout des stratégies
        this.strategies = new ArrayList<>();
        this.strategies.add(new StrategieAleatoire());
        this.strategies.add(new StrategieDefensive());
        this.strategies.add(new StrategieOffensive());
        for (StrategieEtudiant strat : strategies) {
            zonePanel.getStrategiesComboBox().addItem(strat);
        }
    }

    /**
     * Gère l'événement de changement de joueur.
     *
     * @param e l'événement
     */
    private void joueurChanged(ActionEvent e) {
        this.zonePanel.updatePanel();
    }

    /**
     * Gère l'événement de changement de combattant.
     *
     * @param e l'événement
     */
    private void combattantChanged(ActionEvent e) {
        Etudiant etudiant = (Etudiant) this.zonePanel.getTroupesComboBox().getSelectedItem();
        if (etudiant != null) {
            for (StrategieEtudiant strategie : this.strategies) {
                if (strategie.toString().equals(etudiant.getStrategie().toString())) {
                    this.zonePanel.getStrategiesComboBox().setSelectedItem(strategie);
                }
            }
        } else {
            this.zonePanel.getStrategiesComboBox().setSelectedItem(null);
        }
    }

    /**
     * Gère l'événement de changement de stratégie d'un combattant.
     *
     * @param e l'événement
     */
    private void strategieChanged(ActionEvent e) {
        Etudiant etudiant = (Etudiant) this.zonePanel.getTroupesComboBox().getSelectedItem();
        if (etudiant != null) {
            StrategieEtudiant strategie = (StrategieEtudiant) this.zonePanel.getStrategiesComboBox().getSelectedItem();
            etudiant.setStrategie(strategie);
        }
    }

    /**
     * Gère l'événement d'ajout d'un réserviste.
     *
     * @param e l'événement
     */
    private void reservisteAdded(ActionEvent e) {
        Joueur joueur = (Joueur) this.zonePanel.getJoueursComboBox().getSelectedItem();
        Etudiant reserviste = (Etudiant) this.zonePanel.getReservistesComboBox().getSelectedItem();
        reserviste.setReserviste(false);
        joueur.removeReserviste(reserviste);
        this.zone.addCombattant(reserviste);
        this.zonePanel.updatePanel();
    }

    /**
     * Gère l'événement de redéploiement d'un combattant.
     *
     * @param e l'événement
     */
    private void combattantRedeploye(ActionEvent e) {
        Etudiant etudiant = (Etudiant) this.zonePanel.getTroupesComboBox().getSelectedItem();
        etudiant.getZone().removeCombattant(etudiant);
        this.zone.addCombattant(etudiant);
        this.zonePanel.updatePanel();
    }

}
