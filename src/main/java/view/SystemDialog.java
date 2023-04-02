package view;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.io.InputStream;

/**
 * Boîte de dialogue système.
 *
 * @author Tanguy HARDION
 */
public class SystemDialog extends JDialog {

    /**
     * Crée et affiche une nouvelle boîte de dialogue système customisée.
     *
     * @param message le message à afficher
     * @param type    le type de boîte de dialogue (erreur, information)
     */
    private SystemDialog(String message, Type type) {
        // Définition du titre du dialog
        this.setTitle(type.getTitre());
        // Définition de l'icône du dialog
        try (InputStream is = this.getClass().getResourceAsStream(type.getIconePath())) {
            this.setIconImage(ImageIO.read(is));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setLayout(new BorderLayout());

        // Création d'un label pour le message
        JLabel label = new JLabel(message);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Ajout du label au dialog
        this.add(label, BorderLayout.CENTER);

        // Création d'un bouton pour fermer la fenêtre
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> dispose());
        okButton.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        okButton.setFocusPainted(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Ajout du bouton au dialog
        this.add(okButton, BorderLayout.SOUTH);

        // Paramétrage de la fenêtre (position, affichage)
        this.pack();
        this.setModal(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Appelle le constructeur de la classe avec le message et le type de boîte de dialogue.
     *
     * @param message le message à afficher
     * @param type    le type de boîte de dialogue (erreur, information)
     */
    public static void showDialog(String message, Type type) {
        new SystemDialog(message, type);
    }

    /**
     * Énumération des types de boîtes de dialogue.
     */
    public enum Type {
        /**
         * Boîte de dialogue d'erreur.
         */
        ERROR("Erreur", "/images/error.png"),

        /**
         * Boîte de dialogue d'information.
         */
        INFO("Information", "/images/info.png");

        /**
         * Le titre de la boîte de dialogue.
         */
        private final String titre;

        /**
         * Le chemin de l'icône de la boîte de dialogue.
         */
        private final String iconePath;

        /**
         * Crée un nouveau type de boîte de dialogue.
         *
         * @param title le titre de la boîte de dialogue
         * @param iconePath le chemin de l'icône de la boîte de dialogue
         */
        Type(String title, String iconePath) {
            this.titre = title;
            this.iconePath = iconePath;
        }

        /**
         * @return le titre de la boîte de dialogue
         */
        private String getTitre() {
            return this.titre;
        }

        /**
         * @return le chemin de l'icône de la boîte de dialogue
         */
        private String getIconePath() {
            return this.iconePath;
        }
    }

}
