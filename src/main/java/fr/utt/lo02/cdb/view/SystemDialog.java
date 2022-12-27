package fr.utt.lo02.cdb.view;

import javax.swing.JDialog;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

/**
 * Affiche une fenêtre de dialogue.
 *
 * @author Tanguy HARDION
 */
public class SystemDialog extends JDialog {

    public SystemDialog(String message, Type type) {
        // Définition du titre du dialog
        this.setTitle(type.getTitre());
        // Définition de l'icône du dialog
        this.setIconImage(type.getIcone().getImage());

        // Création d'un panel pour contenir le label du message
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel messageLabel = new JLabel(message);
        messagePanel.add(messageLabel);

        // Création un panel pour contenir le bouton OK
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton okButton = new JButton("OK");
        okButton.setFocusPainted(false);
        okButton.addActionListener(e -> {
            this.setVisible(false);
        });
        buttonPanel.add(okButton);

        // Ajout des panels au dialog
        this.getContentPane().add(messagePanel, BorderLayout.CENTER);
        this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Adaptation de la taille du dialog
        this.pack();

        // Centrage et affichage du dialog
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public enum Type {
        ERROR("Erreur", new ImageIcon("src/main/resources/images/error.png")),
        INFO("Information", new ImageIcon("src/main/resources/images/information.png"));

        private final String titre;
        private final ImageIcon icone;

        Type(String title, ImageIcon icone) {
            this.titre = title;
            this.icone = icone;
        }

        private String getTitre() {
            return this.titre;
        }

        private ImageIcon getIcone() {
            return this.icone;
        }
    }
}
