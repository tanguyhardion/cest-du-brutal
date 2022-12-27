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

        this.configuration.getJoueurComboBox().addItem(joueur1);
        this.configuration.getJoueurComboBox().addItem(joueur2);

        this.configuration.getStrategieComboBox().addItem(new StrategieAleatoire());
        this.configuration.getStrategieComboBox().addItem(new StrategieDefensive());
        this.configuration.getStrategieComboBox().addItem(new StrategieOffensive());

        this.configuration.getJoueurComboBox().addActionListener(e -> {
            Joueur j = (Joueur) this.configuration.getJoueurComboBox().getSelectedItem();
            this.configuration.getTroupesComboBox().removeAllItems();
            for (Etudiant etudiant : j.getTroupes()) {
                this.configuration.getTroupesComboBox().addItem(etudiant);
            }
        });

        this.configuration.getTroupesComboBox().addActionListener(e -> {
            Etudiant etudiant = (Etudiant) this.configuration.getTroupesComboBox().getSelectedItem();
            this.configuration.getDexteriteSpinner().setValue(etudiant.getDexterite());
            this.configuration.getForceSpinner().setValue(etudiant.getForce());
            this.configuration.getResistanceSpinner().setValue(etudiant.getResistance());
            this.configuration.getConstitutionSpinner().setValue(etudiant.getConstitution());
            this.configuration.getInitiativeSpinner().setValue(etudiant.getInitiative());
            this.configuration.getStrategieComboBox().setSelectedItem(etudiant.getStrategie() != null ? etudiant.getStrategie() : new StrategieAleatoire());
            this.configuration.getReservisteToggle().setSelected(etudiant.isReserviste());
        });

        this.configuration.getDexteriteSpinner().addChangeListener(e -> {
            Etudiant etudiant = (Etudiant) this.configuration.getTroupesComboBox().getSelectedItem();
            etudiant.setDexterite((int) this.configuration.getDexteriteSpinner().getValue());
        });

        this.configuration.getForceSpinner().addChangeListener(e -> {
            Etudiant etudiant = (Etudiant) this.configuration.getTroupesComboBox().getSelectedItem();
            etudiant.setForce((int) this.configuration.getForceSpinner().getValue());
        });

        this.configuration.getResistanceSpinner().addChangeListener(e -> {
            Etudiant etudiant = (Etudiant) this.configuration.getTroupesComboBox().getSelectedItem();
            etudiant.setResistance((int) this.configuration.getResistanceSpinner().getValue());
        });

        this.configuration.getConstitutionSpinner().addChangeListener(e -> {
            Etudiant etudiant = (Etudiant) this.configuration.getTroupesComboBox().getSelectedItem();
            etudiant.setConstitution((int) this.configuration.getConstitutionSpinner().getValue());
        });

        this.configuration.getInitiativeSpinner().addChangeListener(e -> {
            Etudiant etudiant = (Etudiant) this.configuration.getTroupesComboBox().getSelectedItem();
            etudiant.setInitiative((int) this.configuration.getInitiativeSpinner().getValue());
        });

        this.configuration.getStrategieComboBox().addActionListener(e -> {
            Etudiant etudiant = (Etudiant) this.configuration.getTroupesComboBox().getSelectedItem();
            etudiant.setStrategie((StrategieEtudiant) this.configuration.getStrategieComboBox().getSelectedItem());
        });

        this.configuration.getReservisteToggle().addActionListener(e -> {
            Etudiant etudiant = (Etudiant) this.configuration.getTroupesComboBox().getSelectedItem();
            if (this.configuration.getReservisteToggle().isSelected()) {
                Joueur joueur = (Joueur) this.configuration.getJoueurComboBox().getSelectedItem();
                joueur.getReservistes().add(etudiant);
                joueur.getTroupes().remove(etudiant);
            } else {
                Joueur joueur = (Joueur) this.configuration.getJoueurComboBox().getSelectedItem();
                joueur.getTroupes().add(etudiant);
                joueur.getReservistes().remove(etudiant);
            }
        });
    }

}