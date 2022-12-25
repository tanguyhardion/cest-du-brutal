package fr.utt.lo02.cdb.controller;


import fr.utt.lo02.cdb.model.*;
import fr.utt.lo02.cdb.view.Configuration;

/**
 * Contrôle les actions de la configuration.
 *
 * @author Tanguy HARDION
 */
public class ConfigController {

    private Configuration configuration;

    public ConfigController(Configuration configuration, Joueur joueur1, Joueur joueur2) {
        this.configuration = configuration;

        this.configuration.getJoueurComboBox().addActionListener(e -> {
            Joueur j = (Joueur) this.configuration.getJoueurComboBox().getSelectedItem();
            this.configuration.getTroupesComboBox().removeAllItems();
            for (Etudiant etudiant : j.getTroupes()) {
                this.configuration.getTroupesComboBox().addItem(etudiant);
            }
        });

        this.configuration.getJoueurComboBox().addItem(joueur1);
        this.configuration.getJoueurComboBox().addItem(joueur2);
    }

}