package fr.utt.lo02.cdb.view;

import fr.utt.lo02.cdb.controller.ConfigController;
import fr.utt.lo02.cdb.model.Etudiant;
import fr.utt.lo02.cdb.model.Joueur;
import fr.utt.lo02.cdb.model.StrategieEtudiant;

import javax.swing.*;
import java.awt.event.ItemEvent;

/**
 * @author Tanguy HARDION
 */
public class Configuration extends JPanel {

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JComboBox troupesComboBox;
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
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public Configuration(Joueur joueur1, Joueur joueur2) {
        initComponents();
        ConfigController configController = new ConfigController(this, joueur1, joueur2);
    }

    private void joueurComboBoxItemStateChanged(ItemEvent e) {
        // update troupesComboBox with the selected player's troops
        Joueur joueur = (Joueur) joueurComboBox.getSelectedItem();
        troupesComboBox.removeAllItems();
        for (Etudiant etudiant : joueur.getTroupes()) {
            troupesComboBox.addItem(etudiant);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        troupesComboBox = new JComboBox();
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

        //======== this ========

        //---- joueurComboBox ----
        joueurComboBox.addItemListener(e -> joueurComboBoxItemStateChanged(e));

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
        constitutionSpinner.setModel(new SpinnerNumberModel(0, 0, 10, 1));

        //---- initiativeLabel ----
        initiativeLabel.setText("Initiative :");

        //---- initiativeSpinner ----
        initiativeSpinner.setModel(new SpinnerNumberModel(0, 0, 10, 1));

        //---- strategieLabel ----
        strategieLabel.setText("Strat\u00e9gie :");

        //---- reservisteLabel ----
        reservisteLabel.setText("R\u00e9serviste");

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(reservisteLabel)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(reservisteToggle))
                        .addComponent(strategieComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(strategieLabel)
                        .addComponent(combattantLabel)
                        .addComponent(troupesComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(joueurLabel)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(joueurComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup()
                                .addComponent(dexteriteLabel)
                                .addComponent(dexteriteSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(49, 49, 49)
                            .addGroup(layout.createParallelGroup()
                                .addComponent(forceLabel)
                                .addComponent(forceSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(55, 55, 55)
                            .addGroup(layout.createParallelGroup()
                                .addComponent(resistanceLabel)
                                .addComponent(resistanceSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(49, 49, 49)
                            .addGroup(layout.createParallelGroup()
                                .addComponent(constitutionLabel)
                                .addComponent(constitutionSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(58, 58, 58)
                            .addGroup(layout.createParallelGroup()
                                .addComponent(initiativeSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(initiativeLabel))))
                    .addContainerGap(162, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(joueurComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(joueurLabel))
                    .addGap(80, 80, 80)
                    .addComponent(combattantLabel)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(troupesComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(52, 52, 52)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(dexteriteLabel)
                        .addComponent(forceLabel)
                        .addComponent(constitutionLabel)
                        .addComponent(initiativeLabel)
                        .addComponent(resistanceLabel))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup()
                        .addComponent(dexteriteSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(forceSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(constitutionSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(resistanceSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addComponent(initiativeSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(strategieLabel)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(strategieComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(reservisteLabel)
                        .addComponent(reservisteToggle, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(52, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    public JComboBox<Joueur> getJoueurComboBox() {
        return joueurComboBox;
    }

    public JComboBox<Etudiant> getTroupesComboBox() {
        return this.troupesComboBox;
    }

    public JSpinner getDexteriteSpinner() {
        return dexteriteSpinner;
    }

    public JSpinner getForceSpinner() {
        return forceSpinner;
    }

    public JSpinner getResistanceSpinner() {
        return resistanceSpinner;
    }

    public JSpinner getConstitutionSpinner() {
        return constitutionSpinner;
    }

    public JSpinner getInitiativeSpinner() {
        return initiativeSpinner;
    }

    public JComboBox<StrategieEtudiant> getStrategieComboBox() {
        return strategieComboBox;
    }

    public JToggleButton getReservisteToggle() {
        return reservisteToggle;
    }

}
