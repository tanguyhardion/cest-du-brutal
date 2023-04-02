package view;

import controller.*;
import model.*;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle;
import java.awt.Component;
import java.util.Observable;
import java.util.Observer;

/**
 * Panel comportant 5 onglets.
 * Chaque onglet est un ZonePanel, qui contient une zone de combat.
 *
 * @author Tristan JAUSSAUD
 */
public class Combat extends JPanel implements Observer {

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    /**
     * Onglets de la fenêtre, chacun comportant un ZonePanel.
     */
    private JTabbedPane zonesPane;
    /**
     * Bouton permettant de relancer le combat, en déclarant la fin de la trêve.
     */
    private JButton relancerCombatButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    /**
     * Constructeur de la classe Combat. Initialise les composants et le contrôleur. Ajoute également à chaque joueur
     * cette instance en tant qu'observateur.
     *
     * @param joueur1 le premier joueur
     * @param joueur2 le deuxième joueur
     */
    public Combat(Joueur joueur1, Joueur joueur2) {
        initComponents();
        new CombatController(this, joueur1, joueur2);
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
        // Si o est un joueur
        if (o instanceof Joueur) {
            Joueur joueur = (Joueur) o;
            // Si le joueur contrôle trois zones
            if (joueur.getZonesControlees().size() >= 3) {
                // Alors le joueur a gagné
                SystemDialog.showDialog("Le " + joueur.toString() + " a gagné !", SystemDialog.Type.INFO);
                // Désactivation du bouton de relance
                this.relancerCombatButton.setEnabled(false);
                // Désactivation des composants de tous les onglets
                for (int i = 0; i < this.zonesPane.getTabCount(); i++) {
                    Component component = this.zonesPane.getComponentAt(i);
                    if (component instanceof ZonePanel) {
                        ((ZonePanel) component).disablePanel();
                    }
                }
            } else {
                // Sinon, on réactive le bouton de relance
                this.enableRelancerCombatButton();
            }

        }
    }

    /**
     * Active ou réactive le bouton de relance du combat, après deux secondes.
     */
    private void enableRelancerCombatButton() {
        // On attend un peu avant de réactiver pour que les zones se mettent à jour
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {
        }
        // Réactivation du bouton de relance
        this.relancerCombatButton.setEnabled(true);
    }

    /**
     * Initialise les composants de la fenêtre.
     * <p>
     * Méthode générée par JFormDesigner. Ne pas modifier.
     */
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        zonesPane = new JTabbedPane();
        relancerCombatButton = new JButton();

        //======== this ========

        //---- relancerCombatButton ----
        relancerCombatButton.setText("RELANCER LE COMBAT");

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addComponent(zonesPane, GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(537, Short.MAX_VALUE)
                                .addComponent(relancerCombatButton)
                                .addGap(46, 46, 46))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(zonesPane, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                                .addComponent(relancerCombatButton, GroupLayout.PREFERRED_SIZE, 40,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    /**
     * @return le JTabbedPane comportant les ZonePanels
     */
    public JTabbedPane getZonesPane() {
        return this.zonesPane;
    }

    /**
     * @return le bouton de relance du combat
     */
    public JButton getRelancerCombatButton() {
        return this.relancerCombatButton;
    }

}
