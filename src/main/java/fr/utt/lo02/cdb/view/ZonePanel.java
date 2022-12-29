package fr.utt.lo02.cdb.view;

import fr.utt.lo02.cdb.model.*;

import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * Panel d'une zone.
 *
 * @author Tanguy HARDION
 */
public class ZonePanel extends JPanel {

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    private Zone zone;

    public ZonePanel(Zone zone) {
        initComponents();
        this.zone = zone;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label2 = new JLabel();

        //======== this ========

        //---- label1 ----
        label1.setText("Cr\u00e9dits sur cette zone :");

        //---- label2 ----
        label2.setText("465");

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addComponent(label1)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(label2)
                    .addContainerGap(571, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(11, 11, 11)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2)
                        .addComponent(label1))
                    .addContainerGap(423, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    public Zone getZone() {
        return this.zone;
    }
}
