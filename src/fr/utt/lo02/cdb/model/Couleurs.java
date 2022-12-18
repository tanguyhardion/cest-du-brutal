package fr.utt.lo02.cdb.model;

/**
 * Donne accès aux couleurs en ligne de commandes grâce aux escapes codes ANSI,
 * si le terminal est compatible.
 * <p>
 * Pour utiliser ces couleurs, il suffit d'ajouter la constante correspondante
 * au début de la chaîne de caractères à afficher. Ne pas oublier de
 * réinitialiser les couleurs à la fin de l'affichage.
 * <p>
 * Exemple :
 * <p>
 * {@code System.out.println(Couleurs.ROUGE + "texte" + Couleurs.RESET);}
 * <p>
 * Source : https://stackoverflow.com/a/45444716
 */
public class Couleurs {

    // Code pour réinitialiser les couleurs, à utiliser à la fin de chaque affichage
    public static final String RESET = "\033[0m";
    // Code pour effacer la console
    public static final String CLEAR = "\033[H\033[2J";

    // Couleurs standards
    public static final String ROUGE = "\033[0;31m";
    public static final String VERT = "\033[0;32m";
    public static final String JAUNE = "\033[0;33m";
    public static final String BLEU = "\033[0;34m";
    public static final String VIOLET = "\033[0;35m";
    public static final String CYAN = "\033[0;36m";

    // Couleurs gras
    public static final String ROUGE_GRAS = "\033[1;31m";
    public static final String VERT_GRAS = "\033[1;32m";
    public static final String JAUNE_GRAS = "\033[1;33m";
    public static final String BLEU_GRAS = "\033[1;34m";
    public static final String VIOLET_GRAS = "\033[1;35m";
    public static final String CYAN_GRAS = "\033[1;36m";

}
