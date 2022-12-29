package fr.utt.lo02.cdb.controller;


import fr.utt.lo02.cdb.model.*;
import fr.utt.lo02.cdb.view.*;

/**
 * Contrôle les actions de la configuration des troupes.
 *
 * @author Tanguy HARDION
 */
public class ConfigurationController {

    private Configuration configuration;
    private Joueur joueur1;
    private Joueur joueur2;

    public ConfigurationController(Configuration configuration, MainWindow mainWindow, Joueur joueur1, Joueur joueur2) {
        this.configuration = configuration;
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;

        this.configuration.getJoueurComboBox().addActionListener(e -> {
            Joueur joueur = (Joueur) this.configuration.getJoueurComboBox().getSelectedItem();
            this.configuration.getTroupesComboBox().removeAllItems();
            for (Etudiant etudiant : joueur.getTroupes()) {
                this.configuration.getTroupesComboBox().addItem(etudiant);
            }
            for (Etudiant reserviste : joueur.getReservistes()) {
                this.configuration.getTroupesComboBox().addItem(reserviste);
            }
        });

        // Ajout des stratégies
        this.configuration.getStrategieComboBox().addItem(new StrategieAleatoire());
        this.configuration.getStrategieComboBox().addItem(new StrategieDefensive());
        this.configuration.getStrategieComboBox().addItem(new StrategieOffensive());

        // Ajout des joueurs
        this.configuration.getJoueurComboBox().addItem(joueur1);
        this.configuration.getJoueurComboBox().addItem(joueur2);

        this.configuration.getTroupesComboBox().addActionListener(e -> {
            Etudiant etudiant = (Etudiant) this.configuration.getTroupesComboBox().getSelectedItem();
            if (etudiant != null) {
                this.configuration.getDexteriteSpinner().setValue(etudiant.getDexterite());
                this.configuration.getForceSpinner().setValue(etudiant.getForce());
                this.configuration.getResistanceSpinner().setValue(etudiant.getResistance());
                this.configuration.getConstitutionSpinner().setValue(etudiant.getConstitution());
                this.configuration.getInitiativeSpinner().setValue(etudiant.getInitiative());
                this.configuration.getStrategieComboBox().setSelectedItem(etudiant.getStrategie() != null ? etudiant.getStrategie() : new StrategieAleatoire());
                this.configuration.getReservisteToggle().setSelected(etudiant.isReserviste());
            }
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
            Joueur joueur = (Joueur) this.configuration.getJoueurComboBox().getSelectedItem();
            Etudiant etudiant = (Etudiant) this.configuration.getTroupesComboBox().getSelectedItem();
            if (this.configuration.getReservisteToggle().isSelected()) {
                if (joueur.getReservistes().size() < 5) {
                    // Ajout du réserviste
                    etudiant.setReserviste(true);
                    joueur.addReserviste(etudiant);
                    joueur.removeEtudiant(etudiant);
                    // Sélection du réserviste choisi dans la ComboBox
                    this.configuration.getTroupesComboBox().setSelectedItem(etudiant);
                } else {
                    // 5 réservistes déjà présents
                    this.configuration.getReservisteToggle().setSelected(false);
                    SystemDialog.showDialog("Vous ne pouvez pas avoir plus de 5 réservistes !", SystemDialog.Type.ERROR);
                }
            } else {
                // Retrait du réserviste
                etudiant.setReserviste(false);
                joueur.getTroupes().add(etudiant);
                joueur.getReservistes().remove(etudiant);
            }
        });

        this.configuration.getAleatoireButton().addActionListener(e -> {
            Joueur joueur = (Joueur) this.configuration.getJoueurComboBox().getSelectedItem();
            joueur.parametrerTroupesAleatoirement();
        });

        this.configuration.getSuivantButton().addActionListener(e -> {
            if (this.isReady()) {
                mainWindow.switchPanel(new Repartition(mainWindow, joueur1, joueur2));
            } else {
                SystemDialog.showDialog("Un des joueurs n'a pas choisi ses 5 réservistes !", SystemDialog.Type.ERROR);
            }
        });
    }

    public boolean isReady() {
        return this.joueur1.getReservistes().size() == 5 && this.joueur2.getReservistes().size() == 5;
    }

}