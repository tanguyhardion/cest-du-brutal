package fr.utt.lo02.cdb;

import fr.utt.lo02.cdb.model.Partie;

/**
 * Classe d'entrée de l'application.
 */
public class App {

    /**
     * Méthode d'entrée, qui commence la partie en lançant l'interface graphique.
     *
     * @param args les arguments de la ligne de commande
     */
    public static void main(String[] args) {
        Partie.getInstance().commencer();
    }

}
