/*
 * Created by JFormDesigner on Sun Dec 25 20:04:09 CET 2022
 */

package fr.utt.lo02.cdb.view;

import fr.utt.lo02.cdb.controller.AccueilController;
import fr.utt.lo02.cdb.model.*;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.JMarsDarkTheme;

/**
 * @author Tanguy HARDION
 */
public class Accueil extends JPanel {

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JComboBox filieres1ComboBox;
    private JComboBox filieres2ComboBox;
    private JLabel filieres1Label;
    private JLabel filieres2Label;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    static {
        try {
            UIManager.setLookAndFeel(new MaterialLookAndFeel(new JMarsDarkTheme()));
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    public Accueil(Joueur joueur1, Joueur joueur2) {
        initComponents();
        AccueilController accueilController = new AccueilController(this, joueur1, joueur2);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        filieres1ComboBox = new JComboBox<Filiere>();
        filieres2ComboBox = new JComboBox<Filiere>();
        filieres1Label = new JLabel();
        filieres2Label = new JLabel();

        //======== this ========

        //---- filieres1Label ----
        filieres1Label.setText("Fili\u00e8re Joueur 1 :");

        //---- filieres2Label ----
        filieres2Label.setText("Fili\u00e8re Joueur 2 :");

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(120, 120, 120)
                    .addGroup(layout.createParallelGroup()
                        .addComponent(filieres1Label)
                        .addComponent(filieres1ComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 262, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup()
                        .addComponent(filieres2Label)
                        .addComponent(filieres2ComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(120, 120, 120))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(271, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(filieres1Label)
                        .addComponent(filieres2Label))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup()
                        .addComponent(filieres1ComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(filieres2ComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(159, 159, 159))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    public JComboBox<Filiere> getFilieres1ComboBox() {
        return filieres1ComboBox;
    }

    public JComboBox<Filiere> getFilieres2ComboBox() {
        return filieres2ComboBox;
    }
}
