package fr.utt.lo02.cdb.view;

import fr.utt.lo02.cdb.controller.*;
import fr.utt.lo02.cdb.model.*;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Panel d'une zone d'influence à contrôler.
 *
 * @author Tanguy HARDION
 * @author Tristan JAUSSAUD
 */
public class ZonePanel extends JPanel implements Observer {

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel infoLabel;
    private JLabel creditsLabel;
    private JLabel joueurLabel;
    private JComboBox joueursComboBox;
    private JLabel reservistesLabel;
    private JComboBox reservistesComboBox;
    private JButton addReservisteButton;
    private JLabel redeployerLabel;
    private JComboBox troupesComboBox;
    private JButton redeployerButton;
    private JLabel surZoneLabel;
    private JComboBox strategiesComboBox;
    private JLabel strategieLabel;
    private JScrollPane troupesScrollPane;
    private JList surZoneList;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    /**
     * Zone d'influence correspondant à ce panel.
     */
    private Zone zone;

    public ZonePanel(Zone zone, Joueur joueur1, Joueur joueur2) {
        this.zone = zone;

        initComponents();

        joueur1.deleteObservers();
        joueur2.deleteObservers();
        joueur1.addObserver(this);
        joueur2.addObserver(this);
        zone.deleteObservers();
        zone.addObserver(this);

        new ZonePanelController(this, zone, joueur1, joueur2);
    }

    /**
     * Met à jour l'affichage de la fenêtre en fonction des changements effectués par la zone de ce panel.
     *
     * @param o   l'objet observable
     * @param arg un argument passé à la méthode notifyObservers()
     */
    @Override
    public void update(Observable o, Object arg) {
        // Si c'est une zone qui a changé
        if (o instanceof Zone) {
            Zone zone = (Zone) o;
            // Si arg est une zone, la zone de ce panel est contrôlée
            if (arg instanceof Zone) {
                // On vérifie que la zone de ce panel est bien contrôlée par un joueur
                if (((Zone) arg).estControlee()) {
                    // Si oui, on désactive ce panel
                    this.disablePanel();
                    // On met les informations de la zone contrôlée
                    this.infoLabel.setFont(creditsLabel.getFont().deriveFont(Font.BOLD));
                    this.infoLabel.setText("Contrôlée par le " + zone.getControleur() + " ");
                    this.creditsLabel.setText("");
                    // Et on affiche un dialog
                    SystemDialog.showDialog("La zone " + zone.getNomZone().getNom() + " est maintenant contrôlée par " +
                            "le " + zone.getControleur() + " !", SystemDialog.Type.INFO);
                }
            } else {
                // Sinon, la zone n'est pas encore contrôlée, alors on met à jour les crédits
                this.creditsLabel.setText(String.valueOf(zone.getCredits() + " "));
            }
            // Dans tous les cas, on met à jour les troupes sur la zone
            this.surZoneList.setListData(zone.getTroupes((Joueur) this.joueursComboBox.getSelectedItem()).toArray());
        }
        // Finalement, on update le joueur sélectionné
        this.updatePanel();
    }

    /**
     * Met à jour ce panel en rafraîchissant ses différents composants en fonction du joueur sélectionné.
     */
    public void updatePanel() {
        // On récupère le joueur sélectionné
        Joueur joueur = (Joueur) this.joueursComboBox.getSelectedItem();

        // On met à jour ses réservistes
        this.reservistesComboBox.removeAllItems();
        for (Etudiant reserviste : joueur.getReservistes()) {
            this.reservistesComboBox.addItem(reserviste);
        }

        // Ses troupes à redéployer
        this.troupesComboBox.removeAllItems();
        List<Zone> zonesRedeploiement = new ArrayList<>(joueur.getZonesControlees());
        // Zones contrôlées par le joueur qui ont au moins 2 combattants
        zonesRedeploiement.removeIf(z -> z.getTroupes(joueur).size() < 2);
        // Mise à jour des troupes prêtes au redéploiement
        for (Zone zoneC : zonesRedeploiement) {
            for (Etudiant etudiant : zoneC.getTroupes(joueur)) {
                this.troupesComboBox.addItem(etudiant);
            }
        }
        // Stratégie du combattant sélectionné
        Etudiant etudiant = (Etudiant) this.getTroupesComboBox().getSelectedItem();
        if (etudiant != null) {
            this.strategiesComboBox.setSelectedItem(etudiant.getStrategie());
        }

        // Et ses troupes sur la zone
        this.surZoneList.setListData(this.zone.getTroupes(joueur).toArray());
    }

    /**
     * Désactive tous les composants de ce panel.
     */
    public void disablePanel() {
        for (Component component : this.getComponents()) {
            if (component == this.infoLabel) continue;
            component.setEnabled(false);
        }
    }

    /**
     * Initialise les composants de la fenêtre.
     * <p>
     * Méthode générée par JFormDesigner. Ne pas modifier.
     */
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        infoLabel = new JLabel();
        creditsLabel = new JLabel();
        joueurLabel = new JLabel();
        joueursComboBox = new JComboBox();
        reservistesLabel = new JLabel();
        reservistesComboBox = new JComboBox();
        addReservisteButton = new JButton();
        redeployerLabel = new JLabel();
        troupesComboBox = new JComboBox();
        redeployerButton = new JButton();
        surZoneLabel = new JLabel();
        strategiesComboBox = new JComboBox();
        strategieLabel = new JLabel();
        troupesScrollPane = new JScrollPane();
        surZoneList = new JList();

        //======== this ========

        //---- infoLabel ----
        infoLabel.setText("Cr\u00e9dits sur cette zone :  ");

        //---- creditsLabel ----
        creditsLabel.setFont(creditsLabel.getFont().deriveFont(creditsLabel.getFont().getStyle() | Font.BOLD));

        //---- joueurLabel ----
        joueurLabel.setText("Joueur :");

        //---- reservistesLabel ----
        reservistesLabel.setText("Vos r\u00e9servistes : ");

        //---- addReservisteButton ----
        addReservisteButton.setText("AJOUTER");
        addReservisteButton.setFocusPainted(false);
        addReservisteButton.setBorderPainted(false);

        //---- redeployerLabel ----
        redeployerLabel.setText("Vos troupes \u00e0 red\u00e9ployer : ");

        //---- redeployerButton ----
        redeployerButton.setText("RED\u00c9PLOYER");

        //---- surZoneLabel ----
        surZoneLabel.setText("Sur cette zone : ");

        //---- strategieLabel ----
        strategieLabel.setText("Strat\u00e9gie : ");

        //======== troupesScrollPane ========
        {
            troupesScrollPane.setViewportView(surZoneList);
        }

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(31, 31, 31)
                            .addGroup(layout.createParallelGroup()
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup()
                                        .addComponent(troupesComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(redeployerLabel)
                                        .addComponent(redeployerButton))
                                    .addGap(58, 58, 58)
                                    .addGroup(layout.createParallelGroup()
                                        .addComponent(strategieLabel)
                                        .addComponent(strategiesComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup()
                                        .addComponent(reservistesLabel)
                                        .addComponent(reservistesComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(addReservisteButton))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 193, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup()
                                        .addComponent(troupesScrollPane, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(surZoneLabel))
                                    .addGap(140, 140, 140))))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(joueurLabel)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(joueursComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 393, Short.MAX_VALUE)
                            .addComponent(infoLabel)
                            .addGap(0, 0, 0)
                            .addComponent(creditsLabel)
                            .addGap(64, 64, 64))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(13, 13, 13)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(joueurLabel)
                        .addComponent(joueursComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(infoLabel)
                        .addComponent(creditsLabel))
                    .addGap(27, 27, 27)
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(reservistesLabel)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(reservistesComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(addReservisteButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(surZoneLabel)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(troupesScrollPane, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
                    .addGap(17, 17, 17)
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(redeployerLabel)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(troupesComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(strategieLabel)
                            .addGap(6, 6, 6)
                            .addComponent(strategiesComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(redeployerButton)
                    .addContainerGap())
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    /**
     * @return le label des crédits de cette zone
     */
    public JLabel getCreditsLabel() {
        return this.creditsLabel;
    }

    /**
     * @return la ComboBox des joueurs
     */
    public JComboBox getJoueursComboBox() {
        return this.joueursComboBox;
    }

    /**
     * @return la ComboBox des réservistes
     */
    public JComboBox getReservistesComboBox() {
        return this.reservistesComboBox;
    }

    /**
     * @return la ComboBox des troupes
     */
    public JComboBox getTroupesComboBox() {
        return this.troupesComboBox;
    }

    /**
     * @return le bouton d'ajout de réservistes
     */
    public JButton getAddReservisteButton() {
        return this.addReservisteButton;
    }

    /**
     * @return le bouton de redéploiement de troupes
     */
    public JButton getRedeployerButton() {
        return this.redeployerButton;
    }

    /**
     * @return la ComboBox des stratégies
     */
    public JComboBox getStrategiesComboBox() {
        return this.strategiesComboBox;
    }

}
