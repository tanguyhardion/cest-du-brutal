package fr.utt.lo02.cdb.view;

import fr.utt.lo02.cdb.model.*;
import fr.utt.lo02.cdb.controller.*;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * @author Tanguy HARDION
 */
public class Combat extends JPanel {
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JTabbedPane zonesPane;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public Combat(MainWindow mainWindow, Joueur joueur1, Joueur joueur2) {
        initComponents();
        new CombatController(this, mainWindow, joueur1, joueur2);
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
                    .addComponent(zonesPane, GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
                    .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(zonesPane, GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                    .addContainerGap())
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    public JTabbedPane getZonesPane() {
        return this.zonesPane;
    }

}
