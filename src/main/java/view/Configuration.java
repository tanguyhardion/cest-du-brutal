package view;

import controller.*;
import model.*;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle;
import javax.swing.SpinnerNumberModel;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

/**
 * Panel de configuration des étudiants de chaque joueur.
 *
 * @author Tanguy HARDION
 */
public class Configuration extends JPanel implements Observer {

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JComboBox combattantsComboBox;
    private JComboBox joueurComboBox;
    private JLabel joueurLabel;
    private JLabel troupesLabel;
    private JLabel dexteriteLabel;
    private JSpinner dexteriteSpinner;
    private JLabel forceLabel;
    private JSpinner forceSpinner;
    private JLabel resistanceLabel;
    private JSpinner resistanceSpinner;
    private JLabel constitutionLabel;
    private JSpinner constitutionSpinner;
    private JLabel initiativeLabel;
    private JSpinner initiativeSpinner;
    private JLabel strategieLabel;
    private JComboBox strategiesComboBox;
    private JLabel reservisteLabel;
    private JToggleButton reservisteToggle;
    private JButton suivantButton;
    private JLabel titreLabel;
    private JButton aleatoireButton;
    private JLabel pointsRestantsLabel;
    private JLabel pointsLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    /**
     * Constructeur de la classe Configuration. Initialise les composants et le contrôleur.
     * Ajoute également à chaque joueur cette instance en tant qu'observateur.
     *
     * @param mainWindow la fenêtre principale
     * @param joueur1    le premier joueur
     * @param joueur2    le deuxième joueur
     */
    public Configuration(MainWindow mainWindow, Joueur joueur1, Joueur joueur2) {
        initComponents();
        new ConfigurationController(this, mainWindow, joueur1, joueur2);
        joueur1.addObserver(this);
        joueur2.addObserver(this);
    }

    /**
     * Met à jour l'affichage de la fenêtre en fonction des changements effectués par les joueurs.
     *
     * @param o   l'objet observable
     * @param arg un argument passé à la méthode notifyObservers()
     */
    @Override
    public void update(Observable o, Object arg) {
        Joueur joueur = (Joueur) o;

        // Update combattantsComboBox avec la nouvelle valeur
        if (arg instanceof Etudiant) {
            this.combattantsComboBox.removeAllItems();
            for (Etudiant etudiant : joueur.getTroupes()) {
                this.combattantsComboBox.addItem(etudiant);
            }
            for (Etudiant reserviste : joueur.getReservistes()) {
                this.combattantsComboBox.addItem(reserviste);
            }
        }

        // Update pointsLabel avec la nouvelle valeur
        if (arg instanceof Integer) {
            this.pointsLabel.setText(String.valueOf(arg) + " ");
        }
    }

    /**
     * Initialise les composants de la fenêtre.
     * <p>
     * Méthode générée par JFormDesigner. Ne pas modifier.
     */
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        combattantsComboBox = new JComboBox();
        joueurComboBox = new JComboBox();
        joueurLabel = new JLabel();
        troupesLabel = new JLabel();
        dexteriteLabel = new JLabel();
        dexteriteSpinner = new JSpinner();
        forceLabel = new JLabel();
        forceSpinner = new JSpinner();
        resistanceLabel = new JLabel();
        resistanceSpinner = new JSpinner();
        constitutionLabel = new JLabel();
        constitutionSpinner = new JSpinner();
        initiativeLabel = new JLabel();
        initiativeSpinner = new JSpinner();
        strategieLabel = new JLabel();
        strategiesComboBox = new JComboBox();
        reservisteLabel = new JLabel();
        reservisteToggle = new JToggleButton();
        suivantButton = new JButton();
        titreLabel = new JLabel();
        aleatoireButton = new JButton();
        pointsRestantsLabel = new JLabel();
        pointsLabel = new JLabel();

        //======== this ========

        //---- joueurLabel ----
        joueurLabel.setText("Joueur :");

        //---- troupesLabel ----
        troupesLabel.setText("Vos troupes :");

        //---- dexteriteLabel ----
        dexteriteLabel.setText("Dext\u00e9rit\u00e9 : ");

        //---- dexteriteSpinner ----
        dexteriteSpinner.setModel(new SpinnerNumberModel(0, 0, 10, 1));

        //---- forceLabel ----
        forceLabel.setText("Force : ");

        //---- forceSpinner ----
        forceSpinner.setModel(new SpinnerNumberModel(0, 0, 10, 1));

        //---- resistanceLabel ----
        resistanceLabel.setText("R\u00e9sistance : ");

        //---- resistanceSpinner ----
        resistanceSpinner.setModel(new SpinnerNumberModel(0, 0, 10, 1));

        //---- constitutionLabel ----
        constitutionLabel.setText("Constitution : ");

        //---- constitutionSpinner ----
        constitutionSpinner.setModel(new SpinnerNumberModel(0, 0, 30, 1));

        //---- initiativeLabel ----
        initiativeLabel.setText("Initiative : ");

        //---- initiativeSpinner ----
        initiativeSpinner.setModel(new SpinnerNumberModel(0, 0, 10, 1));

        //---- strategieLabel ----
        strategieLabel.setText("Strat\u00e9gie :");

        //---- reservisteLabel ----
        reservisteLabel.setText("R\u00e9serviste ");

        //---- suivantButton ----
        suivantButton.setText("SUIVANT");
        suivantButton.setFocusPainted(false);
        suivantButton.setBorderPainted(false);

        //---- titreLabel ----
        titreLabel.setText("Param\u00e9trage des troupes");
        titreLabel.setFont(titreLabel.getFont().deriveFont(titreLabel.getFont().getStyle() | Font.BOLD,
                titreLabel.getFont().getSize() + 12f));

        //---- aleatoireButton ----
        aleatoireButton.setText("AL\u00c9ATOIRE");
        aleatoireButton.setFocusPainted(false);

        //---- pointsRestantsLabel ----
        pointsRestantsLabel.setText("Points restants : ");

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup()
                                                        .addComponent(strategiesComboBox, GroupLayout.PREFERRED_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(strategieLabel)
                                                        .addComponent(combattantsComboBox, GroupLayout.PREFERRED_SIZE
                                                                , 250, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup()
                                                                        .addComponent(dexteriteLabel)
                                                                        .addComponent(dexteriteSpinner,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addGap(52, 52, 52)
                                                                .addGroup(layout.createParallelGroup()
                                                                        .addComponent(forceLabel)
                                                                        .addComponent(forceSpinner,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addGap(52, 52, 52)
                                                                .addGroup(layout.createParallelGroup()
                                                                        .addComponent(resistanceLabel)
                                                                        .addComponent(resistanceSpinner,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addGap(52, 52, 52)
                                                                .addGroup(layout.createParallelGroup()
                                                                        .addComponent(constitutionSpinner,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(constitutionLabel))
                                                                .addGap(53, 53, 53)
                                                                .addGroup(layout.createParallelGroup()
                                                                        .addComponent(initiativeSpinner,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(initiativeLabel))))
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(troupesLabel)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 544, Short.MAX_VALUE)
                                                                .addComponent(pointsRestantsLabel)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(pointsLabel))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(joueurLabel)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(joueurComboBox,
                                                                        GroupLayout.PREFERRED_SIZE,
                                                                        GroupLayout.DEFAULT_SIZE,
                                                                        GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(aleatoireButton))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(reservisteLabel)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(reservisteToggle,
                                                                        GroupLayout.PREFERRED_SIZE, 25,
                                                                        GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 466, Short.MAX_VALUE)
                                                                .addComponent(suivantButton,
                                                                        GroupLayout.PREFERRED_SIZE, 150,
                                                                        GroupLayout.PREFERRED_SIZE)))
                                                .addGap(30, 30, 30))))
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(231, Short.MAX_VALUE)
                                .addComponent(titreLabel)
                                .addGap(0, 237, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(titreLabel)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(joueurComboBox, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(joueurLabel)
                                        .addComponent(aleatoireButton))
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(troupesLabel)
                                        .addComponent(pointsRestantsLabel)
                                        .addComponent(pointsLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(combattantsComboBox, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(dexteriteLabel)
                                                        .addComponent(resistanceLabel))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup()
                                                        .addComponent(dexteriteSpinner, GroupLayout.PREFERRED_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(resistanceSpinner, GroupLayout.PREFERRED_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(constitutionLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(constitutionSpinner, GroupLayout.PREFERRED_SIZE,
                                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(forceLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(forceSpinner, GroupLayout.PREFERRED_SIZE,
                                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(initiativeLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(initiativeSpinner, GroupLayout.PREFERRED_SIZE,
                                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(strategieLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(strategiesComboBox, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup()
                                                        .addComponent(reservisteLabel)
                                                        .addComponent(reservisteToggle, GroupLayout.PREFERRED_SIZE,
                                                                20, GroupLayout.PREFERRED_SIZE))
                                                .addContainerGap(67, Short.MAX_VALUE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 40,
                                                        Short.MAX_VALUE)
                                                .addComponent(suivantButton, GroupLayout.PREFERRED_SIZE, 40,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addGap(25, 25, 25))))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    /**
     * @return la ComboBox des joueurs
     */
    public JComboBox<Joueur> getJoueurComboBox() {
        return this.joueurComboBox;
    }

    /**
     * @return la ComboBox des troupes
     */
    public JComboBox<Etudiant> getTroupesComboBox() {
        return this.combattantsComboBox;
    }

    /**
     * @return le JSpinner de la dextérité
     */
    public JSpinner getDexteriteSpinner() {
        return this.dexteriteSpinner;
    }

    /**
     * @return le JSpinner de la force
     */
    public JSpinner getForceSpinner() {
        return this.forceSpinner;
    }

    /**
     * @return le JSpinner de la constitution
     */
    public JSpinner getResistanceSpinner() {
        return this.resistanceSpinner;
    }

    /**
     * @return le JSpinner de la constitution
     */
    public JSpinner getConstitutionSpinner() {
        return this.constitutionSpinner;
    }

    /**
     * @return le JSpinner de l'initiative
     */
    public JSpinner getInitiativeSpinner() {
        return this.initiativeSpinner;
    }

    /**
     * @return la ComboBox des stratégies
     */
    public JComboBox getStrategiesComboBox() {
        return this.strategiesComboBox;
    }

    /**
     * @return le JToggleButton pour être réserviste
     */
    public JToggleButton getReservisteToggle() {
        return this.reservisteToggle;
    }

    /**
     * @return le label des points du joueur
     */
    public JLabel getPointsLabel() {
        return this.pointsLabel;
    }

    /**
     * @return le bouton pour lancer le paramétrage aléatoire
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
