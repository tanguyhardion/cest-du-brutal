package fr.utt.lo02.cdb.view;

import fr.utt.lo02.cdb.controller.*;
import fr.utt.lo02.cdb.model.*;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle;
import java.util.Observable;
import java.util.Observer;

/**
 * @author Tanguy HARDION
 */
public class Combat extends JPanel implements Observer {

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JTabbedPane zonesPane;
    private JButton relancerCombatButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public Combat(MainWindow mainWindow, Joueur joueur1, Joueur joueur2) {
        initComponents();
        new CombatController(this, mainWindow, joueur1, joueur2);
        joueur1.addObserver(this);
        joueur2.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Joueur) {
            Joueur joueur = (Joueur) o;
            if (joueur.getZonesControlees().size() > 3) {
                SystemDialog.showDialog("le " + joueur.toString() + " a gagné !", SystemDialog.Type.INFO,
                        false);
                this.relancerCombatButton.setEnabled(false);
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        zonesPane = new JTabbedPane();
        relancerCombatButton = new JButton();

        //======== this ========

        //---- relancerCombatButton ----
        relancerCombatButton.setText("RELANCER LE COMBAT");

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addComponent(zonesPane, GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(527, Short.MAX_VALUE)
                                .addComponent(relancerCombatButton)
                                .addGap(46, 46, 46))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(zonesPane, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                                .addComponent(relancerCombatButton, GroupLayout.PREFERRED_SIZE, 40,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    public JTabbedPane getZonesPane() {
        return this.zonesPane;
    }

    public JButton getRelancerCombatButton() {
        return this.relancerCombatButton;
    }

}
