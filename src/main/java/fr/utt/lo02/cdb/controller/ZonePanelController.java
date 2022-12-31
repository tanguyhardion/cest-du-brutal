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

        this.zonePanel.getReservistesComboBox().removeAllItems();
        // Affichage des réservistes
        for (Etudiant reserviste : joueur.getReservistes()) {
            this.zonePanel.getReservistesComboBox().addItem(reserviste);
        }

        this.zonePanel.getTroupesComboBox().removeAllItems();
        // Zones sur lesquelles il y a des troupes à redéployer
        List<Zone> zonesRedeploiement = new ArrayList<>(joueur.getZonesControlees());
        // Zones contrôlées par le joueur qui ont au moins 2 combattants
        zonesRedeploiement.removeIf(z -> z.getTroupes(joueur).size() < 2);
        // Affichage des troupes prêtes au redéploiement
        for (Zone zoneC : zonesRedeploiement) {
            for (Etudiant etudiant : zoneC.getTroupes(joueur)) {
                this.zonePanel.getTroupesComboBox().addItem(etudiant);
            }
        }
    }

    private void reservisteAdded(ActionEvent e) {
        Joueur joueur = (Joueur) this.zonePanel.getJoueursComboBox().getSelectedItem();
        Etudiant reserviste = (Etudiant) this.zonePanel.getReservistesComboBox().getSelectedItem();
        this.zone.addCombattant(reserviste);
        joueur.removeReserviste(reserviste);
    }

    private void combattantRedeploye(ActionEvent e) {
        Joueur joueur = (Joueur) this.zonePanel.getJoueursComboBox().getSelectedItem();
        Etudiant etudiant = (Etudiant) this.zonePanel.getTroupesComboBox().getSelectedItem();
    }

}
