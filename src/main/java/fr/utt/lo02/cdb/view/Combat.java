package fr.utt.lo02.cdb.view;

import fr.utt.lo02.cdb.controller.CombatController;
import fr.utt.lo02.cdb.model.Joueur;

import javax.swing.*;

/**
 * @author Tanguy HARDION
 */
public class Combat extends JPanel {
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JTabbedPane zonesPane;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    private Joueur joueur1;
    private Joueur joueur2;

    public Combat(MainWindow mainWindow, Joueur joueur1, Joueur joueur2) {
        initComponents();
        new CombatController(this, mainWindow, joueur1, joueur2);
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        zonesPane = new JTabbedPane();

        //======== this ========

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(zonesPane)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(zonesPane)
                                .addContainerGap())
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    public Joueur getJoueur1() {
        return joueur1;
    }

    public Joueur getJoueur2() {
        return joueur2;
    }

    public JTabbedPane getZonesPane() {
        return this.zonesPane;
    }

}
