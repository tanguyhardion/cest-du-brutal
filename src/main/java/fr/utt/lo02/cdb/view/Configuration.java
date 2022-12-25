/*
 * Created by JFormDesigner on Sun Dec 25 16:38:15 CET 2022
 */

package fr.utt.lo02.cdb.view;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

import fr.utt.lo02.cdb.controller.ConfigController;
import fr.utt.lo02.cdb.model.*;
import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.JMarsDarkTheme;

/**
 * @author Tanguy HARDION
 */
public class Configuration extends JPanel {

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JComboBox troupesComboBox;
    private JComboBox joueurComboBox;
    private JLabel label1;
    private JLabel label2;
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
        label1 = new JLabel();
        label2 = new JLabel();

        //======== this ========

        //---- joueurComboBox ----
        joueurComboBox.addItemListener(e -> joueurComboBoxItemStateChanged(e));

        //---- label1 ----
        label1.setText("Joueur :");

        //---- label2 ----
        label2.setText("Combattant :");

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addGroup(layout.createParallelGroup()
                        .addComponent(label2)
                        .addComponent(troupesComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(label1)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(joueurComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(532, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(joueurComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label1))
                    .addGap(80, 80, 80)
                    .addComponent(label2)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(troupesComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(286, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    public JComboBox<Etudiant> getTroupesComboBox() {
        return this.troupesComboBox;
    }

    public JComboBox<Joueur> getJoueurComboBox() {
        return joueurComboBox;
    }

}
