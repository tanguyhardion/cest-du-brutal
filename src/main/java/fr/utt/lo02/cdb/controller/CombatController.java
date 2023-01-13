package fr.utt.lo02.cdb.controller;

import fr.utt.lo02.cdb.model.*;
import fr.utt.lo02.cdb.model.enums.*;
import fr.utt.lo02.cdb.view.*;

import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Contrôle les actions du combat.
 *
 * @author Tristan JAUSSAUD
 */
public class CombatController {

    /**
     * La vue du combat.
     */
    private final Combat combat;

    /**
     * Contrôleur du combat.
     *
     * @param combat la vue du combat
     * @param joueur1 le premier joueur
     * @param joueur2 le deuxième joueur
     */
    public CombatController(Combat combat, Joueur joueur1, Joueur joueur2) {
        this.combat = combat;

        List<Zone> zones = Partie.getInstance().getZones();

        // Ajout des zones au combat
        for (int i = 0; i < NomZone.values().length; i++) {
            this.combat.getZonesPane().addTab(NomZone.values()[i].getNom(),
                    new ZonePanel(zones.get(i), joueur1, joueur2));
        }

        this.combat.getZonesPane().addChangeListener(this::zoneChanged);

        this.combat.getRelancerCombatButton().addActionListener(this::combatRelance);

        // Lancement du premier combat
        // On créé un pool de threads pour les zones
        ExecutorService executor = Executors.newFixedThreadPool(zones.size());
        // On lance le combat sur chaque zone
        for (Zone zone : zones) {
            executor.execute(zone);
        }

        // On affiche un message de début de combat dans un autre thread, pour ne pas bloquer l'interface
        new Thread(() -> SystemDialog.showDialog("Les combats font rage !", SystemDialog.Type.INFO)).start();
    }

    /**
     * Gère l'évènement de changement de panel de zone.
     *
     * @param e l'évènement
     */
    private void zoneChanged(ChangeEvent e) {
        ZonePanel zonePanel = (ZonePanel) this.combat.getZonesPane().getSelectedComponent();
        zonePanel.updatePanel();
    }

    /**
     * Gère l'événement de clic sur le bouton de relance du combat.
     *
     * @param e l'événement
     */
    private void combatRelance(ActionEvent e) {
        // Fin de la trêve
        Zone.finirTreve();
        // Désactivation du bouton
        this.combat.getRelancerCombatButton().setEnabled(false);
    }

}
