/*
 * Created by JFormDesigner on Sun Dec 25 15:01:35 CET 2022
 */

package fr.utt.lo02.cdb.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * @author Tanguy HARDION
 */
public class MainWindow extends JFrame {

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public MainWindow() {
        super("C'est du brutal !");
        initComponents();
        // Termine le programme lorsqu'on ferme la fenêtre
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off

        //======== this ========
        var contentPane = getContentPane();

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGap(0, 698, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGap(0, 469, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    public void switchPanel(JPanel panel) {
        this.setContentPane(panel);
        this.revalidate();
        this.repaint();
    }

}
