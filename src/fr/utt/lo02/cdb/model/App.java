package fr.utt.lo02.cdb.model;

/**
 * Classe d'entrée de l'application.
 */
public class App {

    /**
     * Méthode d'entrée, qui commence la partie.
     * 
     * @param args les arguments de la ligne de commande
     */
    public static void main(String[] args) {
        Partie.getInstance().commencer();
    }

}
