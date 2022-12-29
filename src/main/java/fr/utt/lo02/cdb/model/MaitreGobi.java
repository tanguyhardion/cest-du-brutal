package fr.utt.lo02.cdb.model;

/**
 * Représente un Maître du gobi, un type d'étudiant dont les caractéristiques
 * initiales sont augmentées (+10 en constitution, +2 pour le reste).
 *
 * @author Tanguy HARDION
 * @version 1.0
 */
public class MaitreGobi extends Etudiant {

    /**
     * Constructeur de la classe MaitreGobi.
     * <p>
     * Initialise les crédits de ce Maître du gobi à 30, son équipe, son ID
     * et ses caractéristiques à leurs valeurs augmentées.
     *
     * @param equipe l'équipe à laquelle appartient ce Maître du gobi
     * @param id     l'id de l'étudiant
     */
    public MaitreGobi(Equipe equipe, int id) {
        super(2, 2, 2, 10, 2, equipe, id);
    }

}