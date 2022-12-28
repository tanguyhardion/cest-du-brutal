package fr.utt.lo02.cdb.view;

import fr.utt.lo02.cdb.controller.ConfigurationController;
import fr.utt.lo02.cdb.model.Etudiant;
import fr.utt.lo02.cdb.model.Joueur;
import fr.utt.lo02.cdb.model.StrategieEtudiant;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.Font;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Tanguy HARDION
 */
public class Configuration extends JPanel implements Observer {

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JComboBox combattantsComboBox;
    private JComboBox joueurComboBox;
    private JLabel joueurLabel;
    private JLabel combattantLabel;
    private JLabel dexteriteLabel;
    private JSpinner dexteriteSpinner;
    private JLabel forceLabel;
    private JSpinner forceSpinner;
    private JLabel resistanceLabel;
    private JSpinner resistanceSpinner;
    private JLabel constitutionLabel;
    private JSpinner constitutionSpinner;
    private JLabel initiativeLabel;
    private JSpinner initiativeSpinner;
    private JLabel strategieLabel;
    private JComboBox strategieComboBox;
    private JLabel reservisteLabel;
    private JToggleButton reservisteToggle;
    private JButton suivantButton;
    private JLabel titreLabel;
    private JButton aleatoireButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public Configuration(MainWindow mainWindow, Joueur joueur1, Joueur joueur2) {
        initComponents();
        ConfigurationController configurationController = new ConfigurationController(this, mainWindow, joueur1, joueur2);
        joueur1.addObserver(this);
        joueur2.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.combattantsComboBox.removeAllItems();
        Joueur joueur = (Joueur) o;
        for (Etudiant etudiant : joueur.getTroupes()) {
            this.combattantsComboBox.addItem(etudiant);
        }
        for (Etudiant reserviste : joueur.getReservistes()) {
            this.combattantsComboBox.addItem(reserviste);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        combattantsComboBox = new JComboBox();
        joueurComboBox = new JComboBox();
        joueurLabel = new JLabel();
        combattantLabel = new JLabel();
        dexteriteLabel = new JLabel();
        dexteriteSpinner = new JSpinner();
        forceLabel = new JLabel();
        forceSpinner = new JSpinner();
        resistanceLabel = new JLabel();
        resistanceSpinner = new JSpinner();
        constitutionLabel = new JLabel();
        constitutionSpinner = new JSpinner();
        initiativeLabel = new JLabel();
        initiativeSpinner = new JSpinner();
        strategieLabel = new JLabel();
        strategieComboBox = new JComboBox();
        reservisteLabel = new JLabel();
        reservisteToggle = new JToggleButton();
        suivantButton = new JButton();
        titreLabel = new JLabel();
        aleatoireButton = new JButton();

        //======== this ========

        //---- joueurLabel ----
        joueurLabel.setText("Joueur :");

        //---- combattantLabel ----
        combattantLabel.setText("Combattant :");

        //---- dexteriteLabel ----
        dexteriteLabel.setText("Dext\u00e9rit\u00e9 :");

        //---- dexteriteSpinner ----
        dexteriteSpinner.setModel(new SpinnerNumberModel(0, 0, 10, 1));

        //---- forceLabel ----
        forceLabel.setText("Force :");

        //---- forceSpinner ----
        forceSpinner.setModel(new SpinnerNumberModel(0, 0, 10, 1));

        //---- resistanceLabel ----
        resistanceLabel.setText("R\u00e9sistance :");

        //---- resistanceSpinner ----
        resistanceSpinner.setModel(new SpinnerNumberModel(0, 0, 10, 1));

        //---- constitutionLabel ----
        constitutionLabel.setText("Constitution :");

        //---- constitutionSpinner ----
        constitutionSpinner.setModel(new SpinnerNumberModel(0, 0, 30, 1));

        //---- initiativeLabel ----
        initiativeLabel.setText("Initiative :");

        //---- initiativeSpinner ----
        initiativeSpinner.setModel(new SpinnerNumberModel(0, 0, 10, 1));

        //---- strategieLabel ----
        strategieLabel.setText("Strat\u00e9gie :");

        //---- reservisteLabel ----
        reservisteLabel.setText("R\u00e9serviste");

        //---- suivantButton ----
        suivantButton.setText("SUIVANT");
        suivantButton.setFocusPainted(false);
        suivantButton.setBorderPainted(false);

        //---- titreLabel ----
        titreLabel.setText("Param\u00e9trage des troupes");
        titreLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));

        //---- aleatoireButton ----
        aleatoireButton.setText("AL\u00c9ATOIRE");
        aleatoireButton.setFocusPainted(false);

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup()
                                .addComponent(strategieComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(strategieLabel)
                                .addComponent(combattantLabel)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup()
                                        .addComponent(dexteriteLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dexteriteSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGap(52, 52, 52)
                                    .addGroup(layout.createParallelGroup()
                                        .addComponent(forceLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(forceSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGap(52, 52, 52)
                                    .addGroup(layout.createParallelGroup()
                                        .addComponent(resistanceLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(resistanceSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGap(52, 52, 52)
                                    .addGroup(layout.createParallelGroup()
                                        .addComponent(constitutionSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(constitutionLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                                    .addGap(44, 44, 44)
                                    .addGroup(layout.createParallelGroup()
                                        .addComponent(initiativeSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(initiativeLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)))
                                .addComponent(combattantsComboBox, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(joueurLabel)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(joueurComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 441, Short.MAX_VALUE)
                                    .addComponent(aleatoireButton))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(reservisteLabel, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(reservisteToggle, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 457, Short.MAX_VALUE)
                                    .addComponent(suivantButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
                            .addGap(30, 30, 30))))
                .addGroup(layout.createSequentialGroup()
                    .addGap(224, 224, 224)
                    .addComponent(titreLabel)
                    .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(titreLabel)
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(joueurComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(joueurLabel)
                        .addComponent(aleatoireButton))
                    .addGap(35, 35, 35)
                    .addComponent(combattantLabel)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(combattantsComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(52, 52, 52)
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(dexteriteLabel)
                                .addComponent(initiativeLabel)
                                .addComponent(resistanceLabel))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup()
                                .addComponent(dexteriteSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(resistanceSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(initiativeSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(constitutionLabel)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(constitutionSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(forceLabel)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(forceSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addGap(18, 18, 18)
                    .addComponent(strategieLabel)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(strategieComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup()
                                .addComponent(reservisteToggle, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                .addComponent(reservisteLabel))
                            .addContainerGap(59, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                            .addComponent(suivantButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                            .addGap(25, 25, 25))))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    public JComboBox<Joueur> getJoueurComboBox() {
        return this.joueurComboBox;
    }

    public JComboBox<Etudiant> getTroupesComboBox() {
        return this.combattantsComboBox;
    }

    public JSpinner getDexteriteSpinner() {
        return this.dexteriteSpinner;
    }

    public JSpinner getForceSpinner() {
        return this.forceSpinner;
    }

    public JSpinner getResistanceSpinner() {
        return this.resistanceSpinner;
    }

    public JSpinner getConstitutionSpinner() {
        return this.constitutionSpinner;
    }

    public JSpinner getInitiativeSpinner() {
        return this.initiativeSpinner;
    }

    public JComboBox<StrategieEtudiant> getStrategieComboBox() {
        return this.strategieComboBox;
    }

    public JToggleButton getReservisteToggle() {
        return this.reservisteToggle;
    }

    public JButton getAleatoireButton() {
        return this.aleatoireButton;
    }

    public JButton getSuivantButton() {
        return this.suivantButton;
    }
}
