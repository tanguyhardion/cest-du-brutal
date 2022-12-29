package fr.utt.lo02.cdb.view;

import java.awt.*;
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
    private JLabel creditsSurZoneLabel;
    private JLabel creditsLabel;
    private JLabel joueurLabel;
    private JComboBox joueursComboBox;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    private Zone zone;

    public ZonePanel(Zone zone) {
        initComponents();
        this.zone = zone;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        creditsSurZoneLabel = new JLabel();
        creditsLabel = new JLabel();
        joueurLabel = new JLabel();
        joueursComboBox = new JComboBox();

        //======== this ========

        //---- creditsSurZoneLabel ----
        creditsSurZoneLabel.setText("Cr\u00e9dits sur cette zone : ");

        //---- creditsLabel ----
        creditsLabel.setFont(creditsLabel.getFont().deriveFont(creditsLabel.getFont().getStyle() | Font.BOLD));

        //---- joueurLabel ----
        joueurLabel.setText("Joueur :");

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(joueurLabel)
                    .addGap(12, 12, 12)
                    .addComponent(joueursComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(408, 408, 408)
                    .addComponent(creditsSurZoneLabel)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(creditsLabel)
                    .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(13, 13, 13)
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addGap(13, 13, 13)
                            .addComponent(joueurLabel))
                        .addComponent(joueursComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(creditsLabel)
                                .addComponent(creditsSurZoneLabel))))
                    .addGap(396, 396, 396))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    public Zone getZone() {
        return this.zone;
    }
}
