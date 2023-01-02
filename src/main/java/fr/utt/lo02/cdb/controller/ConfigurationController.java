package fr.utt.lo02.cdb.controller;


import fr.utt.lo02.cdb.model.*;
import fr.utt.lo02.cdb.view.*;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Contrôle les actions de la configuration des troupes.
 *
 * @author Tanguy HARDION
 */
public class ConfigurationController {

    private static List<StrategieEtudiant> strategies;
    private final Configuration configuration;
    private int previousDexteriteSpinnerValue;
    private int previousForceSpinnerValue;
    private int previousResistanceSpinnerValue;
    private int previousConstitutionSpinnerValue;
    private int previousInitiativeSpinnerValue;
    private boolean codeChange;
    private Joueur joueur1;
    private Joueur joueur2;


    public ConfigurationController(Configuration configuration, MainWindow mainWindow, Joueur joueur1, Joueur joueur2) {
        this.configuration = configuration;
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;

        this.configuration.getJoueurComboBox().addActionListener(e -> {
            // Récupération du joueur sélectionné
            Joueur joueur = (Joueur) this.configuration.getJoueurComboBox().getSelectedItem();
            // On enlève toutes les troupes
            this.configuration.getTroupesComboBox().removeAllItems();

            // On ajoute les troupes du joueur sélectionné
            for (Etudiant etudiant : joueur.getTroupes()) {
                this.configuration.getTroupesComboBox().addItem(etudiant);
            }

            // Et ses réservistes
            for (Etudiant reserviste : joueur.getReservistes()) {
                this.configuration.getTroupesComboBox().addItem(reserviste);
            }

            // Nombre de points du joueur sélectionné
            this.configuration.getPointsLabel().setText(String.valueOf(joueur.getPoints() + " "));
        });

        // Ajout des joueurs
        this.configuration.getJoueurComboBox().addItem(joueur1);
        this.configuration.getJoueurComboBox().addItem(joueur2);

        // Ajout des stratégies
        List<StrategieEtudiant> strategies = new ArrayList<>();
        strategies.add(new StrategieAleatoire());
        strategies.add(new StrategieDefensive());
        strategies.add(new StrategieOffensive());
        for (StrategieEtudiant strat : strategies) {
            this.configuration.getStrategieComboBox().addItem(strat);
        }
        this.strategies = strategies;

        this.configuration.getTroupesComboBox().addActionListener(e -> {
            Etudiant etudiant = (Etudiant) this.configuration.getTroupesComboBox().getSelectedItem();
            if (etudiant != null) {
                codeChange = true;

                this.configuration.getDexteriteSpinner().setValue(etudiant.getDexterite());
                this.configuration.getForceSpinner().setValue(etudiant.getForce());
                this.configuration.getResistanceSpinner().setValue(etudiant.getResistance());
                this.configuration.getConstitutionSpinner().setValue(etudiant.getConstitution());
                this.configuration.getInitiativeSpinner().setValue(etudiant.getInitiative());
                this.configuration.getStrategieComboBox().setSelectedIndex(etudiant.getStrategie() != null ?
                        strategies.indexOf(etudiant.getStrategie()) : 0);
                this.configuration.getReservisteToggle().setSelected(etudiant.isReserviste());

                if (etudiant instanceof MaitreGobi) {
                    ((SpinnerNumberModel) this.configuration.getDexteriteSpinner().getModel()).setMinimum(2);
                    ((SpinnerNumberModel) this.configuration.getForceSpinner().getModel()).setMinimum(2);
                    ((SpinnerNumberModel) this.configuration.getResistanceSpinner().getModel()).setMinimum(2);
                    ((SpinnerNumberModel) this.configuration.getConstitutionSpinner().getModel()).setMinimum(10);
                    ((SpinnerNumberModel) this.configuration.getInitiativeSpinner().getModel()).setMinimum(2);
                } else if (etudiant instanceof EtudiantElite) {
                    ((SpinnerNumberModel) this.configuration.getDexteriteSpinner().getModel()).setMinimum(1);
                    ((SpinnerNumberModel) this.configuration.getForceSpinner().getModel()).setMinimum(1);
                    ((SpinnerNumberModel) this.configuration.getResistanceSpinner().getModel()).setMinimum(1);
                    ((SpinnerNumberModel) this.configuration.getConstitutionSpinner().getModel()).setMinimum(5);
                    ((SpinnerNumberModel) this.configuration.getInitiativeSpinner().getModel()).setMinimum(1);
                } else {
                    ((SpinnerNumberModel) this.configuration.getDexteriteSpinner().getModel()).setMinimum(0);
                    ((SpinnerNumberModel) this.configuration.getForceSpinner().getModel()).setMinimum(0);
                    ((SpinnerNumberModel) this.configuration.getResistanceSpinner().getModel()).setMinimum(0);
                    ((SpinnerNumberModel) this.configuration.getConstitutionSpinner().getModel()).setMinimum(0);
                    ((SpinnerNumberModel) this.configuration.getInitiativeSpinner().getModel()).setMinimum(0);
                }

                codeChange = false;

                this.previousDexteriteSpinnerValue = etudiant.getDexterite();
                this.previousForceSpinnerValue = etudiant.getForce();
                this.previousResistanceSpinnerValue = etudiant.getResistance();
                this.previousConstitutionSpinnerValue = etudiant.getConstitution();
                this.previousInitiativeSpinnerValue = etudiant.getInitiative();
            }
        });

        this.configuration.getDexteriteSpinner().addChangeListener(e -> {
            if (!codeChange) {
                Joueur joueur = (Joueur) this.configuration.getJoueurComboBox().getSelectedItem();
                int value = (int) this.configuration.getDexteriteSpinner().getValue();

                if (joueur.getPoints() > 0) {
                    Etudiant etudiant = (Etudiant) this.configuration.getTroupesComboBox().getSelectedItem();
                    etudiant.setDexterite(value);
                } else {
                    this.configuration.getDexteriteSpinner().setValue(this.previousDexteriteSpinnerValue);
                }

                this.updatePoints(e, joueur, this.previousDexteriteSpinnerValue);
                this.previousDexteriteSpinnerValue = value;
            }
        });

        this.configuration.getForceSpinner().addChangeListener(e -> {
            if (!codeChange) {
                Joueur joueur = (Joueur) this.configuration.getJoueurComboBox().getSelectedItem();
                int value = (int) this.configuration.getForceSpinner().getValue();

                if (joueur.getPoints() > 0) {
                    Etudiant etudiant = (Etudiant) this.configuration.getTroupesComboBox().getSelectedItem();
                    etudiant.setForce(value);
                } else {
                    this.configuration.getForceSpinner().setValue(this.previousForceSpinnerValue);
                }

                this.updatePoints(e, joueur, this.previousForceSpinnerValue);
                this.previousForceSpinnerValue = value;
            }
        });

        this.configuration.getResistanceSpinner().addChangeListener(e -> {
            if (!codeChange) {
                Joueur joueur = (Joueur) this.configuration.getJoueurComboBox().getSelectedItem();
                int value = (int) this.configuration.getResistanceSpinner().getValue();

                if (joueur.getPoints() > 0) {
                    Etudiant etudiant = (Etudiant) this.configuration.getTroupesComboBox().getSelectedItem();
                    etudiant.setResistance(value);
                } else {
                    this.configuration.getResistanceSpinner().setValue(this.previousResistanceSpinnerValue);
                }

                this.updatePoints(e, joueur, this.previousResistanceSpinnerValue);
                this.previousResistanceSpinnerValue = value;
            }
        });

        this.configuration.getConstitutionSpinner().addChangeListener(e -> {
            if (!codeChange) {
                Joueur joueur = (Joueur) this.configuration.getJoueurComboBox().getSelectedItem();
                int value = (int) this.configuration.getConstitutionSpinner().getValue();

                if (joueur.getPoints() > 0) {
                    Etudiant etudiant = (Etudiant) this.configuration.getTroupesComboBox().getSelectedItem();
                    etudiant.setConstitution(value);
                } else {
                    this.configuration.getConstitutionSpinner().setValue(this.previousConstitutionSpinnerValue);
                }

                this.updatePoints(e, joueur, this.previousConstitutionSpinnerValue);
                this.previousConstitutionSpinnerValue = value;
            }
        });

        this.configuration.getInitiativeSpinner().addChangeListener(e -> {
            if (!codeChange) {
                Joueur joueur = (Joueur) this.configuration.getJoueurComboBox().getSelectedItem();
                int value = (int) this.configuration.getInitiativeSpinner().getValue();

                if (joueur.getPoints() > 0) {
                    Etudiant etudiant = (Etudiant) this.configuration.getTroupesComboBox().getSelectedItem();
                    etudiant.setInitiative(value);
                } else {
                    this.configuration.getInitiativeSpinner().setValue(this.previousInitiativeSpinnerValue);
                }

                this.updatePoints(e, joueur, this.previousInitiativeSpinnerValue);
                this.previousInitiativeSpinnerValue = value;
            }
        });

        this.configuration.getStrategieComboBox().addActionListener(e -> {
            if (!codeChange) {
                Etudiant etudiant = (Etudiant) this.configuration.getTroupesComboBox().getSelectedItem();
                etudiant.setStrategie((StrategieEtudiant) this.configuration.getStrategieComboBox().getSelectedItem());
            }
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
                    SystemDialog.showDialog("Vous avez déjà 5 réservistes !", SystemDialog.Type.ERROR);
                }
            } else {
                // Retrait du réserviste
                etudiant.setReserviste(false);
                joueur.removeReserviste(etudiant);
                joueur.addEtudiant(etudiant);
                // Sélection de l'étudiant choisi dans la ComboBox
                this.configuration.getTroupesComboBox().setSelectedItem(etudiant);
            }
        });

        this.configuration.getAleatoireButton().addActionListener(e -> {
            Joueur joueur = (Joueur) this.configuration.getJoueurComboBox().getSelectedItem();
            joueur.parametrerTroupesAleatoirement(strategies);
        });

        this.configuration.getSuivantButton().addActionListener(e -> {
            if (this.isReady()) {
                mainWindow.switchPanel(new Repartition(mainWindow, joueur1, joueur2));
            } else {
                SystemDialog.showDialog("Un joueur n'a pas choisi 5 réservistes !", SystemDialog.Type.ERROR);
            }
        });
    }

    private void updatePoints(ChangeEvent e, Joueur joueur, int previousValue) {
        if (this.changementPositif(e, previousValue)) {
            if (joueur.getPoints() > 0) {
                joueur.decrementerPoints();
            } else {
                SystemDialog.showDialog("Vous n'avez plus de points !", SystemDialog.Type.ERROR);
            }
        } else {
            joueur.incrementerPoints();
        }
    }

    private boolean changementPositif(ChangeEvent e, int previousValue) {
        JSpinner spinner = (JSpinner) e.getSource();
        return (int) spinner.getValue() > previousValue;
    }

    private boolean isReady() {
        return this.joueur1.getReservistes().size() == 5 && this.joueur2.getReservistes().size() == 5;
    }

    public static List<StrategieEtudiant> getStrategies() {
        return strategies;
    }

}