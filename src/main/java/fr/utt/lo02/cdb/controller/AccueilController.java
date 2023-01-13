package fr.utt.lo02.cdb.controller;

import fr.utt.lo02.cdb.model.*;
import fr.utt.lo02.cdb.model.enums.*;
import fr.utt.lo02.cdb.model.themes.*;
import fr.utt.lo02.cdb.view.*;
import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.MaterialTheme;

import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.util.EnumSet;
import java.util.Set;

/**
 * Contrôle les actions de l'accueil.
 *
 * @author Tanguy HARDION
 */
public class AccueilController {

    /**
     * La vue de l'accueil.
     */
    private final Accueil accueil;

    /**
     * La fenêtre principale.
     */
    private final MainWindow mainWindow;

    /**
     * Le premier joueur.
     */
    private final Joueur joueur1;

    /**
     * Le deuxième joueur.
     */
    private final Joueur joueur2;

    /**
     * Contrôleur de l'accueil.
     *
     * @param accueil    la vue de l'accueil
     * @param mainWindow la fenêtre principale
     * @param joueur1    le premier joueur
     * @param joueur2    le deuxième joueur
     */
    public AccueilController(Accueil accueil, MainWindow mainWindow, Joueur joueur1, Joueur joueur2) {
        this.accueil = accueil;
        this.mainWindow = mainWindow;
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;

        this.accueil.getThemeComboBox().addItem(new JMars());
        this.accueil.getThemeComboBox().addItem(new Oceanic());
        this.accueil.getThemeComboBox().addItem(new StackOverflow());
        this.accueil.getThemeComboBox().addItem(new Light());

        this.accueil.getThemeComboBox().addActionListener(this::themeChanged);

        // Sélection du thème par défaut (JMars)
        this.accueil.getThemeComboBox().setSelectedIndex(0);

        Set<Filiere> filieres = EnumSet.allOf(Filiere.class);

        for (Filiere filiere : filieres) {
            this.accueil.getFilieres1ComboBox().addItem(filiere);
            this.accueil.getFilieres2ComboBox().addItem(filiere);
        }

        this.accueil.getFilieres1ComboBox().addActionListener(this::filiere1Changed);

        this.accueil.getFilieres2ComboBox().addActionListener(this::filiere2Changed);

        this.accueil.getFilieres1ComboBox().setSelectedIndex(0);
        this.accueil.getFilieres2ComboBox().setSelectedIndex(1);

        this.accueil.getSuivantButton().addActionListener(this::suivantClicked);
    }

    /**
     * Vérifie si les joueurs sont prêts en évaluant
     * s'ils ont bien une filière différente.
     *
     * @return true si les joueurs sont prêts, false sinon
     */
    public boolean isReady() {
        return this.accueil.getFilieres1ComboBox().getSelectedIndex()
                != this.accueil.getFilieres2ComboBox().getSelectedIndex();
    }

    /**
     * Gère l'événement de changement le thème de l'application.
     *
     * @param e l'événement
     */
    private void themeChanged(ActionEvent e) {
        MaterialLookAndFeel.changeTheme((MaterialTheme) this.accueil.getThemeComboBox().getSelectedItem());
        SwingUtilities.updateComponentTreeUI(this.mainWindow);
    }

    /**
     * Gère l'événement de changement de filière du joueur 1.
     *
     * @param e l'événement
     */
    private void filiere1Changed(ActionEvent e) {
        Filiere f = (Filiere) this.accueil.getFilieres1ComboBox().getSelectedItem();
        joueur1.setFiliere(f);
    }

    /**
     * Gère l'événement de changement de filière du joueur 2.
     *
     * @param e l'événement
     */
    private void filiere2Changed(ActionEvent e) {
        Filiere f = (Filiere) this.accueil.getFilieres2ComboBox().getSelectedItem();
        joueur2.setFiliere(f);
    }

    /**
     * Gère l'événement de clic sur le bouton suivant.
     *
     * @param e l'événement
     */
    private void suivantClicked(ActionEvent e) {
        if (this.isReady()) {
            mainWindow.switchPanel(new Configuration(mainWindow, joueur1, joueur2));
        } else {
            SystemDialog.showDialog("Les joueurs ne peuvent pas avoir la même filière !",
                    SystemDialog.Type.ERROR);
        }
    }

}
