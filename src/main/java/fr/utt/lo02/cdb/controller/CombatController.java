package fr.utt.lo02.cdb.controller;

import fr.utt.lo02.cdb.model.*;
import fr.utt.lo02.cdb.model.enums.NomZone;
import fr.utt.lo02.cdb.view.MainWindow;
import fr.utt.lo02.cdb.view.Combat;
import fr.utt.lo02.cdb.view.ZonePanel;

/**
 * Contrôle les actions du combat.
 *
 * @author Tanguy HARDION
 */
public class CombatController {

    private Combat combat;
    private Joueur joueur1;
    private Joueur joueur2;

    public CombatController(Combat combat, MainWindow mainWindow, Joueur joueur1, Joueur joueur2) {
        this.combat = combat;
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;

        for (int i = 0; i < NomZone.values().length; i++) {
            this.combat.getZonesPane().addTab(NomZone.values()[i].getNom(), new ZonePanel(Partie.getInstance().getZones().get(i)));
        }
    }

}
