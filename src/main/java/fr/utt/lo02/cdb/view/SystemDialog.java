package fr.utt.lo02.cdb.view;

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

    private SystemDialog(String message, Type type, boolean disposable) {
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

        // Si la fenêtre peut être fermée
        if (disposable) {
            // Création d'un bouton pour fermer la fenêtre
            JButton okButton = new JButton("OK");
            okButton.addActionListener(e -> dispose());
            okButton.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
            okButton.setFocusPainted(false);
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            // Ajout du bouton au dialog
            this.add(okButton, BorderLayout.SOUTH);
        } else {
            this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        }

        // Paramétrage de la fenêtre (position, affichage)
        this.pack();
        this.setModal(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void showDialog(String message, Type type) {
        new SystemDialog(message, type, true);
    }

    public static void showDialog(String message, Type type, boolean disposable) {
        new SystemDialog(message, type, disposable);
    }

    public enum Type {
        ERROR("Erreur", "/images/error.png"),
        INFO("Information", "/images/info.png");

        private final String titre;
        private final String iconePath;

        Type(String title, String iconePath) {
            this.titre = title;
            this.iconePath = iconePath;
        }

        private String getTitre() {
            return this.titre;
        }

        private String getIconePath() {
            return this.iconePath;
        }
    }

}
