package fr.utt.lo02.cdb.controller;

import fr.utt.lo02.cdb.model.*;
import fr.utt.lo02.cdb.view.*;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class ZonePanelController {

    private final ZonePanel zonePanel;
    private final Zone zone;
    private List<StrategieEtudiant> strategies;

    public ZonePanelController(ZonePanel zonePanel, Zone zone, Joueur joueur1, Joueur joueur2) {
        this.zonePanel = zonePanel;
        this.zone = zone;

        zonePanel.getJoueursComboBox().addActionListener(this::joueurChanged);
        zonePanel.getTroupesComboBox().addActionListener(this::combattantChanged);
        zonePanel.getStrategieComboBox().addActionListener(this::strategieChanged);
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
            zonePanel.getStrategieComboBox().addItem(strat);
        }
    }

    private void joueurChanged(ActionEvent e) {
        this.zonePanel.updateJoueur();
    }

    private void combattantChanged(ActionEvent e) {
        Etudiant etudiant = (Etudiant) this.zonePanel.getTroupesComboBox().getSelectedItem();
        if (etudiant != null) {
            for (StrategieEtudiant strategie : this.strategies) {
                if (strategie.toString().equals(etudiant.getStrategie().toString())) {
                    this.zonePanel.getStrategieComboBox().setSelectedItem(strategie);
                }
            }
        } else {
            this.zonePanel.getStrategieComboBox().setSelectedItem(null);
        }
    }

    private void strategieChanged(ActionEvent e) {
        Etudiant etudiant = (Etudiant) this.zonePanel.getTroupesComboBox().getSelectedItem();
        if (etudiant != null) {
            StrategieEtudiant strategie = (StrategieEtudiant) this.zonePanel.getStrategieComboBox().getSelectedItem();
            etudiant.setStrategie(strategie);
        }
    }

    private void reservisteAdded(ActionEvent e) {
        Joueur joueur = (Joueur) this.zonePanel.getJoueursComboBox().getSelectedItem();
        Etudiant reserviste = (Etudiant) this.zonePanel.getReservistesComboBox().getSelectedItem();
        reserviste.setReserviste(false);
        joueur.removeReserviste(reserviste);
        this.zone.addCombattant(reserviste);
        this.zonePanel.updateJoueur();
    }

    private void combattantRedeploye(ActionEvent e) {
        Etudiant etudiant = (Etudiant) this.zonePanel.getTroupesComboBox().getSelectedItem();
        etudiant.getZone().removeCombattant(etudiant);
        this.zone.addCombattant(etudiant);
        this.zonePanel.updateJoueur();
    }

}
