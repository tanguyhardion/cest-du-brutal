package fr.utt.lo02.cdb.view;

import fr.utt.lo02.cdb.controller.*;
import fr.utt.lo02.cdb.model.*;
import fr.utt.lo02.cdb.model.enums.*;
import mdlaf.themes.MaterialTheme;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

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
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public Accueil(MainWindow mainWindow, Joueur joueur1, Joueur joueur2) {
        initComponents();
        new AccueilController(this, mainWindow, joueur1, joueur2);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        filieres1ComboBox = new JComboBox();
        filieres2ComboBox = new JComboBox();
        filieres1Label = new JLabel();
        filieres2Label = new JLabel();
        suivantButton = new JButton();
        themeLabel = new JLabel();
        themeComboBox = new JComboBox();

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

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(120, 120, 120)
                    .addGroup(layout.createParallelGroup()
                        .addComponent(filieres1Label)
                        .addComponent(filieres1ComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup()
                        .addComponent(filieres2ComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(filieres2Label))
                    .addGap(179, 179, 179))
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(561, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(themeLabel)
                            .addGap(18, 18, 18)
                            .addComponent(themeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addComponent(suivantButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                    .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(themeLabel)
                        .addComponent(themeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 194, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(filieres1Label)
                        .addComponent(filieres2Label))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup()
                        .addComponent(filieres1ComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(filieres2ComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(115, 115, 115)
                    .addComponent(suivantButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addGap(25, 25, 25))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    public JComboBox getThemeComboBox() {
        return this.themeComboBox;
    }

    public JComboBox<Filiere> getFilieres1ComboBox() {
        return filieres1ComboBox;
    }

    public JComboBox<Filiere> getFilieres2ComboBox() {
        return filieres2ComboBox;
    }

    public JButton getSuivantButton() {
        return suivantButton;
    }
}
