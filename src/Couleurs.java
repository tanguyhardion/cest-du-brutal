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
    public static String RESET = "\033[0m";
    // Code pour effacer la console
    public static String CLEAR = "\033[H\033[2J";

    // Couleurs standards
    public static String NOIR = "\033[0;30m";
    public static String ROUGE = "\033[0;31m";
    public static String VERT = "\033[0;32m";
    public static String JAUNE = "\033[0;33m";
    public static String BLEU = "\033[0;34m";
    public static String VIOLET = "\033[0;35m";
    public static String CYAN = "\033[0;36m";
    public static String BLANC = "\033[0;37m";

    // Couleurs gras
    public static String NOIR_GRAS = "\033[1;30m";
    public static String ROUGE_GRAS = "\033[1;31m";
    public static String VERT_GRAS = "\033[1;32m";
    public static String JAUNE_GRAS = "\033[1;33m";
    public static String BLEU_GRAS = "\033[1;34m";
    public static String VIOLET_GRAS = "\033[1;35m";
    public static String CYAN_GRAS = "\033[1;36m";
    public static String BLANC_GRAS = "\033[1;37m";

}
