package fr.utt.lo02.cdb.model;

import fr.utt.lo02.cdb.model.enums.Equipe;

/**
 * Représente un étudiant d'élite, un type d'étudiant dont les caractéristiques
 * initiales sont augmentées (+5 en constitution, +1 pour le reste).
 *
 * @author Tanguy HARDION
 * @version 1.2
 */
public class EtudiantElite extends Etudiant {

    /**
     * Constructeur de la classe EtudiantElite.
     * <p>
     * Initialise les crédits de cet étudiant d'élite à 30, son équipe, son ID
     * et ses caractéristiques à leurs valeurs augmentées.
     *
     * @param equipe l'équipe à laquelle appartient cet étudiant d'élite
     * @param id     l'id de l'étudiant
     */
    public EtudiantElite(Equipe equipe, int id) {
        super(1, 1, 1, 5, 1, equipe, id);
    }

}