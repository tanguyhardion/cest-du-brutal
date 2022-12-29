package fr.utt.lo02.cdb.view;

import fr.utt.lo02.cdb.model.*;
import fr.utt.lo02.cdb.controller.RepartitionController;

import mdlaf.shadows.RoundedCornerBorder;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * @author Tanguy HARDION
 */
public class Repartition extends JPanel implements Observer {

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel titreLabel;
    private JLabel zoneLabel;
    private JComboBox zonesComboBox;
    private JLabel joueurLabel;
    private JComboBox joueursComboBox;
    private JLabel troupesLabel;
    private JComboBox troupesComboBox;
    private JButton addButton;
    private JButton suivantButton;
    private JButton aleatoireButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public Repartition(MainWindow mainWindow, Joueur joueur1, Joueur joueur2) {
        initComponents();
        new RepartitionController(this, mainWindow, joueur1, joueur2);
        joueur1.deleteObservers();
        joueur2.deleteObservers();
        joueur1.addObserver(this);
        joueur2.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.troupesComboBox.removeAllItems();
        Joueur joueur = (Joueur) o;
        for (Etudiant etudiant : joueur.getTroupes()) {
            this.troupesComboBox.addItem(etudiant);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        titreLabel = new JLabel();
        zoneLabel = new JLabel();
        zonesComboBox = new JComboBox();
        joueurLabel = new JLabel();
        joueursComboBox = new JComboBox();
        troupesLabel = new JLabel();
        troupesComboBox = new JComboBox();
        addButton = new JButton();
        suivantButton = new JButton();
        aleatoireButton = new JButton();

        //======== this ========

        //---- titreLabel ----
        titreLabel.setText("R\u00e9partition des troupes");
        titreLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));

        //---- zoneLabel ----
        zoneLabel.setText("Zone :");

        //---- joueurLabel ----
        joueurLabel.setText("Joueur :");

        //---- troupesLabel ----
        troupesLabel.setText("Vos troupes :");

        //---- addButton ----
        addButton.setText("AJOUTER");
        addButton.setBorder(new RoundedCornerBorder());
        addButton.setFocusPainted(false);
        addButton.setBorderPainted(false);

        //---- suivantButton ----
        suivantButton.setText("SUIVANT");
        suivantButton.setFocusPainted(false);

        //---- aleatoireButton ----
        aleatoireButton.setText("AL\u00c9ATOIRE");
        aleatoireButton.setFocusPainted(false);

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(234, 234, 234)
                    .addComponent(titreLabel)
                    .addContainerGap(234, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(17, 17, 17)
                            .addGroup(layout.createParallelGroup()
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(joueurLabel)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(joueursComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(aleatoireButton))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup()
                                        .addComponent(zoneLabel)
                                        .addComponent(zonesComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGap(168, 168, 168)
                                    .addGroup(layout.createParallelGroup()
                                        .addComponent(troupesLabel)
                                        .addComponent(troupesComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(addButton, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 326, Short.MAX_VALUE))))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap(570, Short.MAX_VALUE)
                            .addComponent(suivantButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
                    .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(titreLabel)
                    .addGap(44, 44, 44)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(joueurLabel)
                                .addComponent(joueursComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(aleatoireButton))
                            .addGap(90, 90, 90)
                            .addComponent(zoneLabel)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(zonesComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(troupesLabel)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(troupesComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addGap(18, 18, 18)
                    .addComponent(addButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                    .addComponent(suivantButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addGap(25, 25, 25))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    public JComboBox getJoueursComboBox() {
        return this.joueursComboBox;
    }

    public JComboBox getZonesComboBox() {
        return this.zonesComboBox;
    }

    public JComboBox getTroupesComboBox() {
        return this.troupesComboBox;
    }

    public JButton getAddButton() {
        return this.addButton;
    }

    public JButton getAleatoireButton() {
        return this.aleatoireButton;
    }

    public JButton getSuivantButton() {
        return this.suivantButton;
    }
}
