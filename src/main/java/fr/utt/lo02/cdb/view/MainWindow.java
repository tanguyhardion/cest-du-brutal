package fr.utt.lo02.cdb.view;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import java.io.InputStream;

/**
 * @author Tristan JAUSSAUD
 */
public class MainWindow extends JFrame {

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public MainWindow() {
        // Titre
        super("C'est du brutal !");
        // Icône
        try (InputStream is = getClass().getResourceAsStream("/images/logo.png")) {
            this.setIconImage(ImageIO.read(is));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off

        //======== this ========
        var contentPane = getContentPane();

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGap(0, 758, Short.MAX_VALUE)
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
