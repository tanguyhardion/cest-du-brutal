package fr.utt.lo02.cdb.view;

import fr.utt.lo02.cdb.controller.*;
import fr.utt.lo02.cdb.model.*;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

/**
 * Panel d'une zone.
 *
 * @author Tanguy HARDION
 */
public class ZonePanel extends JPanel implements Observer {

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
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public ZonePanel(Zone zone, Joueur joueur1, Joueur joueur2) {
        initComponents();

        joueur1.deleteObservers();
        joueur2.deleteObservers();
        joueur1.addObserver(this);
        joueur2.addObserver(this);
        zone.deleteObservers();
        zone.addObserver(this);

        new ZonePanelController(this, zone, joueur1, joueur2);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Zone) {
            Zone zone = (Zone) o;
            this.creditsLabel.setText(String.valueOf(zone.getCredits()));
        } else if (o instanceof Joueur) {
            Joueur joueur = (Joueur) o;
            for (Etudiant reserviste : joueur.getReservistes()) {
                this.reservistesComboBox.addItem(reserviste);
            }
        }
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
        addReservisteButton.setFocusPainted(false);
        addReservisteButton.setBorderPainted(false);

        //---- redeployerLabel ----
        redeployerLabel.setText("Vos troupes \u00e0 red\u00e9ployer :");

        //---- redeployerButton ----
        redeployerButton.setText("RED\u00c9PLOYER");

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
                    .addComponent(joueurLabel)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(joueursComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 411, Short.MAX_VALUE)
                    .addComponent(creditsSurZoneLabel)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(creditsLabel)
                    .addContainerGap(34, Short.MAX_VALUE))
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
                    .addContainerGap(66, Short.MAX_VALUE))
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
