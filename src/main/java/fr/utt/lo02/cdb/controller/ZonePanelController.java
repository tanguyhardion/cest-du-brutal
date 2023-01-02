package fr.utt.lo02.cdb.controller;

import fr.utt.lo02.cdb.model.*;
import fr.utt.lo02.cdb.view.*;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class ZonePanelController {

    private final ZonePanel zonePanel;
    private final Zone zone;

    public ZonePanelController(ZonePanel zonePanel, Zone zone, Joueur joueur1, Joueur joueur2) {
        this.zonePanel = zonePanel;
        this.zone = zone;

        zonePanel.getJoueursComboBox().addActionListener(this::joueurChanged);
        zonePanel.getTroupesComboBox().addActionListener(this::combattantChanged);
        zonePanel.getAddReservisteButton().addActionListener(this::reservisteAdded);
        zonePanel.getRedeployerButton().addActionListener(this::combattantRedeploye);

        zonePanel.getJoueursComboBox().addItem(joueur1);
        zonePanel.getJoueursComboBox().addItem(joueur2);

        zonePanel.getCreditsLabel().setText(String.valueOf(zone.getCredits()));

        for (StrategieEtudiant strategie : ConfigurationController.getStrategies()) {
            zonePanel.getStrategieComboBox().addItem(strategie);
        }
    }

    private void joueurChanged(ActionEvent e) {
        this.zonePanel.updateJoueur();
    }

    private void combattantChanged(ActionEvent e) {
        Etudiant etudiant = (Etudiant) this.zonePanel.getTroupesComboBox().getSelectedItem();
        if (etudiant != null) {
            this.zonePanel.getStrategieComboBox().setSelectedItem(etudiant.getStrategie());
        } else {
            this.zonePanel.getStrategieComboBox().setSelectedItem(null);
        }
    }

    private void reservisteAdded(ActionEvent e) {
        Joueur joueur = (Joueur) this.zonePanel.getJoueursComboBox().getSelectedItem();
        Etudiant reserviste = (Etudiant) this.zonePanel.getReservistesComboBox().getSelectedItem();
        this.zone.addCombattant(reserviste);
        joueur.removeReserviste(reserviste);
    }

    private void combattantRedeploye(ActionEvent e) {
        Etudiant etudiant = (Etudiant) this.zonePanel.getTroupesComboBox().getSelectedItem();
        this.zone.addCombattant(etudiant);
        etudiant.getZone().removeCombattant(etudiant);
    }

}
