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

    public SystemDialog(String title, String message, String icon) {
        // Définition du titre du dialog
        this.setTitle(title);
        // Définition de l'icône du dialog
        this.setIconImage(new ImageIcon(icon).getImage());

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
}
