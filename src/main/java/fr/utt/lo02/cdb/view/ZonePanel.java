package fr.utt.lo02.cdb.view;


import javax.swing.*;
import fr.utt.lo02.cdb.controller.*;
import fr.utt.lo02.cdb.model.*;

import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import java.awt.Font;
import mdlaf.shadows.*;

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
    private JLabel reservistesLabel;
    private JComboBox reservistesComboBox;
    private JButton addReservisteButton;
    private JLabel redeployerLabel;
    private JComboBox troupesComboBox;
    private JButton redeployerButton;
    private JButton relancerCombatButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public ZonePanel(Zone zone, Joueur joueur1, Joueur joueur2) {
        initComponents();

        new ZonePanelController(this, zone, joueur1, joueur2);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        creditsSurZoneLabel = new JLabel();
        creditsLabel = new JLabel();
        joueurLabel = new JLabel();
        joueursComboBox = new JComboBox();
        reservistesLabel = new JLabel();
        reservistesComboBox = new JComboBox();
        addReservisteButton = new JButton();
        redeployerLabel = new JLabel();
        troupesComboBox = new JComboBox();
        redeployerButton = new JButton();
        relancerCombatButton = new JButton();

        //======== this ========

        //---- creditsSurZoneLabel ----
        creditsSurZoneLabel.setText("Cr\u00e9dits sur cette zone : ");

        //---- creditsLabel ----
        creditsLabel.setFont(creditsLabel.getFont().deriveFont(creditsLabel.getFont().getStyle() | Font.BOLD));

        //---- joueurLabel ----
        joueurLabel.setText("Joueur :");

        //---- reservistesLabel ----
        reservistesLabel.setText("Vos r\u00e9servistes :");

        //---- addReservisteButton ----
        addReservisteButton.setText("AJOUTER");
        addReservisteButton.setBorder(new RoundedCornerBorder());
        addReservisteButton.setFocusPainted(false);
        addReservisteButton.setBorderPainted(false);

        //---- redeployerLabel ----
        redeployerLabel.setText("Vos troupes \u00e0 red\u00e9ployer :");

        //---- redeployerButton ----
        redeployerButton.setText("RED\u00c9PLOYER");

        //---- relancerCombatButton ----
        relancerCombatButton.setText("RELANCER LE COMBAT");

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(100, 100, 100)
                    .addGroup(layout.createParallelGroup()
                        .addComponent(reservistesComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(reservistesLabel)
                        .addComponent(addReservisteButton))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 282, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup()
                        .addComponent(troupesComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(redeployerButton)
                        .addComponent(redeployerLabel))
                    .addGap(100, 100, 100))
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(joueurLabel)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(joueursComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 444, Short.MAX_VALUE)
                            .addComponent(creditsSurZoneLabel))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(0, 540, Short.MAX_VALUE)
                            .addComponent(relancerCombatButton)))
                    .addGap(0, 0, 0)
                    .addComponent(creditsLabel)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(13, 13, 13)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(joueurLabel)
                        .addComponent(joueursComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(creditsSurZoneLabel)
                        .addComponent(creditsLabel))
                    .addGap(118, 118, 118)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(reservistesLabel)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(reservistesComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(addReservisteButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(redeployerLabel)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(troupesComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(redeployerButton)))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                    .addComponent(relancerCombatButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    public JLabel getCreditsLabel() {
        return this.creditsLabel;
    }

    public JComboBox getJoueursComboBox() {
        return this.joueursComboBox;
    }

    public JComboBox getReservistesComboBox() {
        return this.reservistesComboBox;
    }

    public JComboBox getTroupesComboBox() {
        return this.troupesComboBox;
    }

    public JButton getAddReservisteButton() {
        return this.addReservisteButton;
    }

    public JButton getRedeployerButton() {
        return this.redeployerButton;
    }
}
