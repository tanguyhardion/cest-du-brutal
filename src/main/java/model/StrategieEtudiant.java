package model;

/**
 * Interface StrategieEtudiant, représentant une stratégie qui peut être
 * offensive, défensive ou aléatoire.
 */
public interface StrategieEtudiant {

    /**
     * Permet à un étudiant d'agir en fonction de sa stratégie.
     *
     * @param origine         l'étudiant qui agit
     * @param cibleEquipeUne  l'étudiant de l'équipe une ayant le moins de crédits
     * @param cibleEquipeDeux l'étudiant de l'équipe deux ayant le moins de crédits
     */
    public void agir(Etudiant origine, Etudiant cibleEquipeUne, Etudiant cibleEquipeDeux);

}