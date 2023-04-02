package view;

import controller.*;
import model.*;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

/**
 * Panel de répartition des troupes.
 *
 * @author Tristan JAUSSAUD
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
    private JLabel surZoneLabel;
    private JScrollPane troupesScrollPane;
    private JList surZoneList;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    /**
     * Constructeur de la classe Repartition. Initialise les composants et le contrôleur.
     * Ajoute également à chaque joueur et à chaque zone cette instance en tant qu'observateur.
     *
     * @param mainWindow la fenêtre principale
     * @param joueur1    le premier joueur
     * @param joueur2    le deuxième joueur
     */
    public Repartition(MainWindow mainWindow, Joueur joueur1, Joueur joueur2) {
        initComponents();
        new RepartitionController(this, mainWindow, joueur1, joueur2);

        joueur1.deleteObservers();
        joueur2.deleteObservers();
        joueur1.addObserver(this);
        joueur2.addObserver(this);
        for (Zone zone : Partie.getInstance().getZones()) {
            zone.addObserver(this);
        }
    }


    /**
     * Met à jour l'affichage de la fenêtre en fonction des changements effectués par les joueurs.
     *
     * @param o   l'objet observable
     * @param arg un argument passé à la méthode notifyObservers()
     */
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Joueur) {
            Joueur joueur = (Joueur) o;

            this.troupesComboBox.removeAllItems();
            for (Etudiant etudiant : joueur.getTroupes()) {
                this.troupesComboBox.addItem(etudiant);
            }

            Zone zone = (Zone) this.zonesComboBox.getSelectedItem();
            this.surZoneList.setListData(zone.getTroupes(joueur).toArray());
        } else if (o instanceof Zone) {
            Zone zone = (Zone) o;
            Joueur joueur = (Joueur) this.joueursComboBox.getSelectedItem();

            this.surZoneList.setListData(zone.getTroupes(joueur).toArray());
        }
    }

    /**
     * Initialise les composants de la fenêtre.
     * <p>
     * Méthode générée par JFormDesigner. Ne pas modifier.
     */
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
        surZoneLabel = new JLabel();
        troupesScrollPane = new JScrollPane();
        surZoneList = new JList();

        //======== this ========

        //---- titreLabel ----
        titreLabel.setText("R\u00e9partition des troupes");
        titreLabel.setFont(titreLabel.getFont().deriveFont(titreLabel.getFont().getStyle() | Font.BOLD,
                titreLabel.getFont().getSize() + 12f));

        //---- zoneLabel ----
        zoneLabel.setText("Zone :");

        //---- joueurLabel ----
        joueurLabel.setText("Joueur :");

        //---- troupesLabel ----
        troupesLabel.setText("Vos troupes :");

        //---- addButton ----
        addButton.setText("AJOUTER");
        addButton.setFocusPainted(false);
        addButton.setBorderPainted(false);

        //---- suivantButton ----
        suivantButton.setText("SUIVANT");
        suivantButton.setFocusPainted(false);

        //---- aleatoireButton ----
        aleatoireButton.setText("AL\u00c9ATOIRE");
        aleatoireButton.setFocusPainted(false);

        //---- surZoneLabel ----
        surZoneLabel.setText("Sur cette zone :");

        //======== troupesScrollPane ========
        {
            troupesScrollPane.setViewportView(surZoneList);
        }

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(234, 234, 234)
                                .addComponent(titreLabel)
                                .addContainerGap(260, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(joueurLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(joueursComboBox, GroupLayout.PREFERRED_SIZE,
                                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(aleatoireButton))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup()
                                                        .addComponent(zoneLabel)
                                                        .addComponent(zonesComboBox, GroupLayout.PREFERRED_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(surZoneLabel))
                                                .addGap(168, 168, 168)
                                                .addGroup(layout.createParallelGroup()
                                                        .addComponent(troupesLabel)
                                                        .addComponent(troupesComboBox, GroupLayout.PREFERRED_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(addButton, GroupLayout.PREFERRED_SIZE, 110,
                                                                GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(troupesScrollPane, GroupLayout.PREFERRED_SIZE, 270,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 293,
                                                        Short.MAX_VALUE)
                                                .addComponent(suivantButton, GroupLayout.PREFERRED_SIZE, 150,
                                                        GroupLayout.PREFERRED_SIZE)))
                                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(titreLabel)
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(joueurLabel)
                                        .addComponent(joueursComboBox, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(aleatoireButton))
                                .addGap(76, 76, 76)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(zoneLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(zonesComboBox, GroupLayout.PREFERRED_SIZE,
                                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(troupesLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(troupesComboBox, GroupLayout.PREFERRED_SIZE,
                                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(addButton, GroupLayout.PREFERRED_SIZE, 35,
                                                        GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(33, 33, 33)
                                                .addComponent(surZoneLabel)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(troupesScrollPane, GroupLayout.PREFERRED_SIZE, 100,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addGap(73, 73, 73))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(suivantButton, GroupLayout.PREFERRED_SIZE, 40,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addGap(47, 47, 47))))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    /**
     * @return la ComboBox des joueurs
     */
    public JComboBox getJoueursComboBox() {
        return this.joueursComboBox;
    }

    /**
     * @return la ComboBox des zones
     */
    public JComboBox getZonesComboBox() {
        return this.zonesComboBox;
    }

    /**
     * @return la ComboBox des troupes
     */
    public JComboBox getTroupesComboBox() {
        return this.troupesComboBox;
    }

    /**
     * @return le bouton d'ajout de troupes
     */
    public JButton getAddButton() {
        return this.addButton;
    }

    /**
     * @return la JList des troupes sur la zone
     */
    public JList getSurZoneList() {
        return this.surZoneList;
    }

    /**
     * @return le bouton pour paramétrer les troupes aléatoirement
     */
    public JButton getAleatoireButton() {
        return this.aleatoireButton;
    }

    /**
     * @return le bouton pour passer à l'étape suivante
     */
    public JButton getSuivantButton() {
        return this.suivantButton;
    }

}
