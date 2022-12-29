package fr.utt.lo02.cdb.controller;

import fr.utt.lo02.cdb.model.*;
import fr.utt.lo02.cdb.model.enums.Filiere;
import fr.utt.lo02.cdb.model.themes.*;
import fr.utt.lo02.cdb.view.*;

import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.MaterialTheme;

import javax.swing.*;
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

        this.accueil.getThemeComboBox().addItem(new Light());
        this.accueil.getThemeComboBox().addItem(new JMars());
        this.accueil.getThemeComboBox().addItem(new Oceanic());
        this.accueil.getThemeComboBox().addItem(new StackOverflow());

        this.accueil.getThemeComboBox().addActionListener(e -> {
            MaterialLookAndFeel.changeTheme((MaterialTheme) this.accueil.getThemeComboBox().getSelectedItem());
            SwingUtilities.updateComponentTreeUI(mainWindow);
        });

        // Sélection du thème par défaut (JMars)
        this.accueil.getThemeComboBox().setSelectedIndex(1);

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
                mainWindow.switchPanel(new Configuration(mainWindow, joueur1, joueur2));
            } else {
                SystemDialog.showDialog("Les joueurs ne peuvent pas avoir la même filière !", SystemDialog.Type.ERROR);
            }
        });
    }

    public boolean isReady() {
        return this.accueil.getFilieres1ComboBox().getSelectedIndex() != this.accueil.getFilieres2ComboBox().getSelectedIndex();
    }

}
