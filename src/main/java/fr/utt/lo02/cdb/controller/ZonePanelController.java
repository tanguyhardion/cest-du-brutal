package fr.utt.lo02.cdb.controller;

import fr.utt.lo02.cdb.model.*;
import fr.utt.lo02.cdb.view.*;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class ZonePanelController {

    private ZonePanel zonePanel;
    private Zone zone;

    public ZonePanelController(ZonePanel zonePanel, Zone zone, Joueur joueur1, Joueur joueur2) {
        this.zonePanel = zonePanel;
        this.zone = zone;

        this.zonePanel.getJoueursComboBox().addActionListener(this::joueurChanged);
        this.zonePanel.getAddReservisteButton().addActionListener(this::reservisteAdded);
        this.zonePanel.getRedeployerButton().addActionListener(this::combattantRedeploye);

        zonePanel.getJoueursComboBox().addItem(joueur1);
        zonePanel.getJoueursComboBox().addItem(joueur2);

        this.zonePanel.getCreditsLabel().setText(String.valueOf(zone.getCredits()));
    }

    private void joueurChanged(ActionEvent e) {
        Joueur joueur = (Joueur) this.zonePanel.getJoueursComboBox().getSelectedItem();

        this.zonePanel.updateJoueur();
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
