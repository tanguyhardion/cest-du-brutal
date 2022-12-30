package fr.utt.lo02.cdb.controller;

import fr.utt.lo02.cdb.model.*;
import fr.utt.lo02.cdb.view.*;

import java.util.List;

/**
 * Contrôle les actions de la répartition des troupes.
 *
 * @author Tanguy HARDION
 */
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

        this.repartition.getJoueursComboBox().addActionListener(e -> {
            Joueur joueur = (Joueur) this.repartition.getJoueursComboBox().getSelectedItem();
            this.repartition.getTroupesComboBox().removeAllItems();
            for (Etudiant etudiant : joueur.getTroupes()) {
                this.repartition.getTroupesComboBox().addItem(etudiant);
            }
        });

        this.repartition.getAddButton().addActionListener(e -> {
            Joueur joueur = (Joueur) this.repartition.getJoueursComboBox().getSelectedItem();
            if (joueur.getTroupes().size() > 0) {
                Zone zone = (Zone) this.repartition.getZonesComboBox().getSelectedItem();
                if (zones.stream().filter(z -> z.getTroupes(joueur).isEmpty()).count() == joueur.getTroupes().size()
                        && !zone.getTroupes(joueur).isEmpty()) {
                    SystemDialog.showDialog("Vous ne pouvez plus déployer de troupes dans cette zone !",
                            SystemDialog.Type.ERROR);
                } else {
                    Etudiant etudiant = (Etudiant) this.repartition.getTroupesComboBox().getSelectedItem();
                    zone.addCombattant(etudiant);
                    joueur.removeEtudiant(etudiant);
                }
            } else {
                SystemDialog.showDialog("Vous avez déployé toutes vos troupes !", SystemDialog.Type.ERROR);
            }
        });

        this.repartition.getAleatoireButton().addActionListener(e -> {
            Joueur joueur = (Joueur) this.repartition.getJoueursComboBox().getSelectedItem();
            if (!joueur.getTroupes().isEmpty()) {
                joueur.repartirTroupesAleatoirement(Partie.getInstance().getZones());
            } else {
                SystemDialog.showDialog("Vous avez déployé toutes vos troupes !", SystemDialog.Type.ERROR);
            }
        });

        this.repartition.getSuivantButton().addActionListener(e -> {
            if (zones.stream().anyMatch(zone -> zone.getTroupes(this.joueur1).isEmpty()
                    || zone.getTroupes(this.joueur2).isEmpty())) {
                SystemDialog.showDialog("Chaque joueur doit placer au moins un combattant dans chaque zone !",
                        SystemDialog.Type.ERROR);
            } else {
                mainWindow.switchPanel(new Combat(mainWindow, joueur1, joueur2));
            }
        });
    }

}
