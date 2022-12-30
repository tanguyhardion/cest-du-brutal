package fr.utt.lo02.cdb.controller;

import fr.utt.lo02.cdb.view.*;

public class ZonePanelController {

    public ZonePanelController(ZonePanel zonePanel, Combat combat) {
        zonePanel.getJoueursComboBox().addItem(combat.getJoueur1());
        zonePanel.getJoueursComboBox().addItem(combat.getJoueur2());

    }

}
