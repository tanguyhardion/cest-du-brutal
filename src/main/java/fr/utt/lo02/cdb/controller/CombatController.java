package fr.utt.lo02.cdb.controller;

import fr.utt.lo02.cdb.model.*;
import fr.utt.lo02.cdb.model.enums.*;
import fr.utt.lo02.cdb.view.*;

import java.awt.event.ActionEvent;

/**
 * Contrôle les actions du combat.
 *
 * @author Tanguy HARDION
 */
public class CombatController {

    private Combat combat;

    public CombatController(Combat combat, MainWindow mainWindow, Joueur joueur1, Joueur joueur2) {
        this.combat = combat;

        for (int i = 0; i < NomZone.values().length; i++) {
            this.combat.getZonesPane().addTab(NomZone.values()[i].getNom(),
                    new ZonePanel(Partie.getInstance().getZones().get(i), joueur1, joueur2));
        }

        this.combat.getRelancerCombatButton().addActionListener(this::combatRelance);
    }

    private void combatRelance(ActionEvent e) {
        Zone.finirTreve();
    }

}
