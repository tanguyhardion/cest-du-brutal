package fr.utt.lo02.cdb.view;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class SystemDialog extends JDialog {

    /**
     * Crée une boîte de dialogue système avec un message.
     */
    public SystemDialog(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

}
