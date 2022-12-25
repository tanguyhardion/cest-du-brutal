package fr.utt.lo02.cdb.controller;

import fr.utt.lo02.cdb.model.*;
import fr.utt.lo02.cdb.view.Accueil;

import java.util.Set;
import java.util.EnumSet;

/**
 * Contrôle les actions de l'accueil.
 *
 * @author Tanguy HARDION
 */
public class AccueilController {

    private Accueil accueil;

    public AccueilController(Accueil accueil, Joueur joueur1, Joueur joueur2) {
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
    }

    public boolean isReady() {
        return this.accueil.getFilieres1ComboBox().getSelectedIndex() != this.accueil.getFilieres2ComboBox().getSelectedIndex();
    }

}
