package fr.utt.lo02.cdb.controller;

import fr.utt.lo02.cdb.model.Filiere;
import fr.utt.lo02.cdb.model.Joueur;
import fr.utt.lo02.cdb.view.Accueil;
import fr.utt.lo02.cdb.view.Configuration;
import fr.utt.lo02.cdb.view.MainWindow;
import fr.utt.lo02.cdb.view.SystemDialog;

import java.util.EnumSet;
import java.util.Set;

/**
 * Contrôle les actions de l'accueil.
 *
 * @author Tanguy HARDION
 */
public class AccueilController {

    private Accueil accueil;

    public AccueilController(Accueil accueil, MainWindow mainWindow, Joueur joueur1, Joueur joueur2) {
        this.accueil = accueil;

        Set<Filiere> filieres = EnumSet.allOf(Filiere.class);

        for (Filiere filiere : filieres) {
            this.accueil.getFilieres1ComboBox().addItem(filiere);
            this.accueil.getFilieres2ComboBox().addItem(filiere);
        }

        this.accueil.getFilieres1ComboBox().addActionListener(e -> {
            Filiere f = (Filiere) this.accueil.getFilieres1ComboBox().getSelectedItem();
            joueur1.setFiliere(f);
        });

        this.accueil.getFilieres2ComboBox().addActionListener(e -> {
            Filiere f = (Filiere) this.accueil.getFilieres2ComboBox().getSelectedItem();
            joueur2.setFiliere(f);
        });

        this.accueil.getFilieres1ComboBox().setSelectedIndex(0);
        this.accueil.getFilieres2ComboBox().setSelectedIndex(1);

        this.accueil.getSuivantButton().addActionListener(e -> {
            if (this.isReady()) {
                Configuration configuration = new Configuration(mainWindow, joueur1, joueur2);
                mainWindow.switchPanel(configuration);
            } else {
                new SystemDialog("Les joueurs ne peuvent pas avoir la même filière !", SystemDialog.Type.ERROR);
            }
        });
    }

    public boolean isReady() {
        return this.accueil.getFilieres1ComboBox().getSelectedIndex() != this.accueil.getFilieres2ComboBox().getSelectedIndex();
    }

}
