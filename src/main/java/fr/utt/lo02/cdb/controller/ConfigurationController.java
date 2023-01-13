package fr.utt.lo02.cdb.controller;

import fr.utt.lo02.cdb.model.*;
import fr.utt.lo02.cdb.view.*;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Contrôle les actions de la configuration des troupes.
 *
 * @author Tanguy HARDION
 */
public class ConfigurationController {

    /**
     * La vue de la configuration.
     */
    private final Configuration configuration;

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
     * La valeur précédente du spinner de dextérité.
     */
    private int previousDexteriteSpinnerValue;

    /**
     * La valeur précédente du spinner de force.
     */
    private int previousForceSpinnerValue;

    /**
     * La valeur précédente du spinner de resistance.
     */
    private int previousResistanceSpinnerValue;

    /**
     * La valeur précédente du spinner de constitution.
     */
    private int previousConstitutionSpinnerValue;

    /**
     * La valeur précédente du spinner d'initiative.
     */
    private int previousInitiativeSpinnerValue;

    /**
     * Détermine si le changement qui a été effectué est
     * un changement de l'utilisateur ou du programme.
     */
    private boolean codeChange;

    /**
     * La liste des stratégies.
     */
    private List<StrategieEtudiant> strategies;

    /**
     * Contrôleur de la configuration des troupes.
     *
     * @param configuration la vue de la configuration
     * @param mainWindow    la fenêtre principale
     * @param joueur1       le premier joueur
     * @param joueur2       le deuxième joueur
     */
    public ConfigurationController(Configuration configuration, MainWindow mainWindow, Joueur joueur1, Joueur joueur2) {
        this.configuration = configuration;
        this.mainWindow = mainWindow;
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;

        this.configuration.getJoueurComboBox().addActionListener(this::joueurChanged);

        // Ajout des joueurs
        this.configuration.getJoueurComboBox().addItem(joueur1);
        this.configuration.getJoueurComboBox().addItem(joueur2);

        // Ajout des stratégies
        List<StrategieEtudiant> strategies = new ArrayList<>();
        this.strategies = strategies;
        strategies.add(new StrategieAleatoire());
        strategies.add(new StrategieDefensive());
        strategies.add(new StrategieOffensive());
        for (StrategieEtudiant strat : strategies) {
            this.configuration.getStrategiesComboBox().addItem(strat);
        }

        // Désactivation des champs de texte des spinners
        ((JSpinner.DefaultEditor) this.configuration.getDexteriteSpinner().getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) this.configuration.getForceSpinner().getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) this.configuration.getResistanceSpinner().getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) this.configuration.getConstitutionSpinner().getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) this.configuration.getInitiativeSpinner().getEditor()).getTextField().setEditable(false);

        this.configuration.getTroupesComboBox().addActionListener(this::combattantChanged);

        this.configuration.getDexteriteSpinner().addChangeListener(this::dexteriteSpinnerChanged);

        this.configuration.getForceSpinner().addChangeListener(this::forceSpinnerChanged);

        this.configuration.getResistanceSpinner().addChangeListener(this::resistanceSpinnerChanged);

        this.configuration.getConstitutionSpinner().addChangeListener(this::constitutionSpinnerChanged);

        this.configuration.getInitiativeSpinner().addChangeListener(this::initiativeSpinnerChanged);

        this.configuration.getStrategiesComboBox().addActionListener(this::strategieChanged);

        this.configuration.getReservisteToggle().addActionListener(this::reservisteToggled);

        this.configuration.getAleatoireButton().addActionListener(this::aleatoireButtonClicked);

        this.configuration.getSuivantButton().addActionListener(this::suivantButtonClicked);
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

    /**
     * Détermine si le changement de la valeur d'un spinner est positif.
     *
     * @param e             l'événement
     * @param previousValue la valeur précédente du spinner souhaité
     * @return true si le changement est positif, false sinon
     */
    private boolean changementPositif(ChangeEvent e, int previousValue) {
        JSpinner spinner = (JSpinner) e.getSource();
        return (int) spinner.getValue() > previousValue;
    }

    /**
     * Détermine si les joueurs sont prêts en regardant
     * s'ils ont bien choisi 5 réservistes chacun.
     *
     * @return true si les joueurs sont prêts, false sinon
     */
    private boolean isReady() {
        return this.joueur1.getReservistes().size() == 5 && this.joueur2.getReservistes().size() == 5;
    }

    /**
     * Gère l'évènement de changement de joueur.
     *
     * @param e l'évènement
     */
    private void joueurChanged(ActionEvent e) {
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
        this.configuration.getPointsLabel().setText(joueur.getPoints() + " ");
    }

    /**
     * Gère l'évènement de changement de combattant.
     *
     * @param e l'évènement
     */
    private void combattantChanged(ActionEvent e) {
        Etudiant etudiant = (Etudiant) this.configuration.getTroupesComboBox().getSelectedItem();
        if (etudiant != null) {
            // On marque le changement suivant est effectué par le programme
            codeChange = true;

            this.configuration.getDexteriteSpinner().setValue(etudiant.getDexterite());
            this.configuration.getForceSpinner().setValue(etudiant.getForce());
            this.configuration.getResistanceSpinner().setValue(etudiant.getResistance());
            this.configuration.getConstitutionSpinner().setValue(etudiant.getConstitution());
            this.configuration.getInitiativeSpinner().setValue(etudiant.getInitiative());
            this.configuration.getStrategiesComboBox().setSelectedIndex(etudiant.getStrategie() != null ?
                    strategies.indexOf(etudiant.getStrategie()) : 0);
            this.configuration.getReservisteToggle().setSelected(etudiant.isReserviste());

            // On définit le minimum des spinners en fonction du type de l'étudiant
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

            // Mémoire des précédentes des spinners
            this.previousDexteriteSpinnerValue = etudiant.getDexterite();
            this.previousForceSpinnerValue = etudiant.getForce();
            this.previousResistanceSpinnerValue = etudiant.getResistance();
            this.previousConstitutionSpinnerValue = etudiant.getConstitution();
            this.previousInitiativeSpinnerValue = etudiant.getInitiative();
        }
    }

    /**
     * Gère l'évènement de changement de valeur du spinner de dextérité.
     *
     * @param e l'évènement
     */
    private void dexteriteSpinnerChanged(ChangeEvent e) {
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
    }

    /**
     * Gère l'évènement de changement de valeur du spinner de force.
     *
     * @param e l'évènement
     */
    private void forceSpinnerChanged(ChangeEvent e) {
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
    }

    /**
     * Gère l'évènement de changement de valeur du spinner de résistance.
     *
     * @param e l'évènement
     */
    private void resistanceSpinnerChanged(ChangeEvent e) {
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
    }

    /**
     * Gère l'évènement de changement de valeur du spinner de constitution.
     *
     * @param e l'évènement
     */
    private void constitutionSpinnerChanged(ChangeEvent e) {
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
    }

    /**
     * Gère l'évènement de changement de valeur du spinner d'initiative.
     *
     * @param e l'évènement
     */
    private void initiativeSpinnerChanged(ChangeEvent e) {
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
    }

    /**
     * Gère l'évènement de changement de stratégie.
     *
     * @param e l'évènement
     */
    private void strategieChanged(ActionEvent e) {
        if (!codeChange) {
            Etudiant etudiant = (Etudiant) this.configuration.getTroupesComboBox().getSelectedItem();
            etudiant.setStrategie((StrategieEtudiant) this.configuration.getStrategiesComboBox().getSelectedItem());
        }
    }

    /**
     * Gère l'évènement de basculement du bouton pour être réserviste.
     *
     * @param e l'évènement
     */
    private void reservisteToggled(ActionEvent e) {
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
    }

    /**
     * Gère l'évènement de clic sur le bouton pour paramétrer les troupes aléatoirement.
     *
     * @param e l'évènement
     */
    private void aleatoireButtonClicked(ActionEvent e) {
        Joueur joueur = (Joueur) this.configuration.getJoueurComboBox().getSelectedItem();
        joueur.parametrerTroupesAleatoirement(strategies);
    }

    /**
     * Gère l'évènement de clic sur le bouton pour passer à l'étape suivante.
     *
     * @param e l'évènement
     */
    private void suivantButtonClicked(ActionEvent e) {
        if (this.isReady()) {
            mainWindow.switchPanel(new Repartition(mainWindow, joueur1, joueur2));
        } else {
            SystemDialog.showDialog("Un joueur n'a pas choisi 5 réservistes !", SystemDialog.Type.ERROR);
        }
    }

}