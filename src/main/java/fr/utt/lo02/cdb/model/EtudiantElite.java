package fr.utt.lo02.cdb.model;

/**
 * Représente un étudiant d'élite, un type d'étudiant dont les caractéristiques
 * initiales sont augmentées (+5 en constitution, +1 pour le reste).
 *
 * @author Tanguy HARDION
 * @version 1.0
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

    @Override
    public void setDexterite(int dexterite) {
        super.setDexterite(dexterite + 1);
    }

    @Override
    public void setForce(int force) {
        super.setForce(force + 1);
    }

    @Override
    public void setResistance(int resistance) {
        super.setResistance(resistance + 1);
    }

    @Override
    public void setConstitution(int constitution) {
        super.setConstitution(constitution + 5);
    }

    @Override
    public void setInitiative(int initiative) {
        super.setInitiative(initiative + 1);
    }

}