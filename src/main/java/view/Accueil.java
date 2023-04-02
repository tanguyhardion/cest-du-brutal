package view;

import controller.*;
import model.*;
import model.enums.*;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import java.awt.Font;

/**
 * @author Tanguy HARDION
 */
public class Accueil extends JPanel {

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JComboBox filieres1ComboBox;
    private JComboBox filieres2ComboBox;
    private JLabel filieres1Label;
    private JLabel filieres2Label;
    private JButton suivantButton;
    private JLabel themeLabel;
    private JComboBox themeComboBox;
    private JLabel label1;
    private JLabel label2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    /**
     * Constructeur de la classe Accueil. Initialise les composants et le contrôleur.
     *
     * @param mainWindow la fenêtre principale
     * @param joueur1    le premier joueur
     * @param joueur2    le deuxième joueur
     */
    public Accueil(MainWindow mainWindow, Joueur joueur1, Joueur joueur2) {
        initComponents();
        new AccueilController(this, mainWindow, joueur1, joueur2);
    }

    /**
     * Initialise les composants de la fenêtre.
     * <p>
     * Méthode générée par JFormDesigner. Ne pas modifier.
     */
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        filieres1ComboBox = new JComboBox();
        filieres2ComboBox = new JComboBox();
        filieres1Label = new JLabel();
        filieres2Label = new JLabel();
        suivantButton = new JButton();
        themeLabel = new JLabel();
        themeComboBox = new JComboBox();
        label1 = new JLabel();
        label2 = new JLabel();

        //======== this ========

        //---- filieres1Label ----
        filieres1Label.setText("Fili\u00e8re Joueur 1 :");

        //---- filieres2Label ----
        filieres2Label.setText("Fili\u00e8re Joueur 2 :");

        //---- suivantButton ----
        suivantButton.setText("SUIVANT");
        suivantButton.setFocusPainted(false);

        //---- themeLabel ----
        themeLabel.setText("Th\u00e8me :");

        //---- label1 ----
        label1.setText("C'EST DU BRUTAL ! ");
        label1.setFont(label1.getFont().deriveFont(Font.BOLD | Font.ITALIC, label1.getFont().getSize() + 18f));

        //---- label2 ----
        label2.setText("Pour commencer, choisissez vos fili\u00e8res respectives :   ");
        label2.setFont(label2.getFont().deriveFont(label2.getFont().getStyle() | Font.BOLD));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(571, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(suivantButton, GroupLayout.PREFERRED_SIZE, 150,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(themeLabel)
                                                .addGap(18, 18, 18)
                                                .addComponent(themeComboBox, GroupLayout.PREFERRED_SIZE,
                                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addGap(30, 30, 30))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(150, 150, 150)
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(label2)
                                                .addContainerGap(308, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup()
                                                        .addComponent(filieres1Label)
                                                        .addComponent(filieres1ComboBox, GroupLayout.PREFERRED_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 253,
                                                        Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup()
                                                        .addComponent(filieres2ComboBox, GroupLayout.PREFERRED_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(filieres2Label))
                                                .addGap(159, 159, 159))))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(226, 226, 226)
                                .addComponent(label1)
                                .addContainerGap(257, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(themeLabel)
                                        .addComponent(themeComboBox, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addComponent(label1)
                                .addGap(88, 88, 88)
                                .addComponent(label2)
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(filieres1Label)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(filieres1ComboBox, GroupLayout.PREFERRED_SIZE,
                                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(filieres2Label)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(filieres2ComboBox, GroupLayout.PREFERRED_SIZE,
                                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                                .addComponent(suivantButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    /**
     * @return la ComboBox des thèmes
     */
    public JComboBox getThemeComboBox() {
        return this.themeComboBox;
    }

    /**
     * @return la ComboBox des filières pour le joueur 1
     */
    public JComboBox<Filiere> getFilieres1ComboBox() {
        return filieres1ComboBox;
    }

    /**
     * @return la ComboBox des filières pour le joueur 2
     */
    public JComboBox<Filiere> getFilieres2ComboBox() {
        return filieres2ComboBox;
    }

    /**
     * @return le bouton pour passer à l'étape suivante
     */
    public JButton getSuivantButton() {
        return suivantButton;
    }

}
