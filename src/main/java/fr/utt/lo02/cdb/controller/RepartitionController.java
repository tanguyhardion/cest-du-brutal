package fr.utt.lo02.cdb.controller;

import fr.utt.lo02.cdb.model.Etudiant;
import fr.utt.lo02.cdb.model.Joueur;
import fr.utt.lo02.cdb.model.Partie;
import fr.utt.lo02.cdb.model.Zone;
import fr.utt.lo02.cdb.view.MainWindow;
import fr.utt.lo02.cdb.view.Repartition;

import java.util.List;

public class RepartitionController {

    private Repartition repartition;
    private Joueur joueur1;
    private Joueur joueur2;

    public RepartitionController(Repartition repartition, MainWindow mainWindow, Joueur joueur1, Joueur joueur2) {
        this.repartition = repartition;
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;

        this.repartition.getJoueursComboBox().addItem(joueur1);
        this.repartition.getJoueursComboBox().addItem(joueur2);

        List<Zone> zones = Partie.getInstance().getZones();
        for (Zone zone : zones) {
            this.repartition.getZonesComboBox().addItem(zone);
        }

        this.repartition.getZonesComboBox().addActionListener(e -> {
            Joueur joueur = (Joueur) this.repartition.getJoueursComboBox().getSelectedItem();
            this.repartition.getTroupesComboBox().removeAllItems();
            for (Etudiant etudiant : joueur.getTroupes()) {
                this.repartition.getTroupesComboBox().addItem(etudiant);
            }
        });

        this.repartition.getZonesComboBox().setSelectedIndex(0);

        this.repartition.getAddButton().addActionListener(e -> {
            Joueur joueur = (Joueur) this.repartition.getJoueursComboBox().getSelectedItem();
            Zone zone = (Zone) this.repartition.getZonesComboBox().getSelectedItem();
            Etudiant etudiant = (Etudiant) this.repartition.getTroupesComboBox().getSelectedItem();
            zone.addCombattant(etudiant);
            joueur.removeEtudiant(etudiant.getId());
        });
    }

}
