package fr.utt.lo02.cdb.controller;

import fr.utt.lo02.cdb.model.*;
import fr.utt.lo02.cdb.view.*;

public class ConfigController {

    private Configuration configuration;
    private Joueur joueur;

    public ConfigController(Configuration configuration, Joueur joueur) {
        this.configuration = configuration;
        this.joueur = joueur;
    }

    public void loadTroupes() {
        configuration.getTroupesComboBox().removeAllItems();
        for (Etudiant e : joueur.getTroupes()) {
            configuration.getTroupesComboBox().addItem(e);
        }
    }
}