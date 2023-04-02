package model;

import model.enums.*;

import java.util.Random;

/**
 * Représente l'action à réaliser d'un étudiant dont la stratégie est défensive.
 *
 * @author Tanguy HARDION
 * @version 1.2
 */
public class StrategieDefensive implements StrategieEtudiant {

    /**
     * Soigne un autre étudiant (en lui ajoutant des crédits) en fonction de la
     * dextérité du soignant et de la constitution du soigné.
     *
     * @param origine l'étudiant qui soigne
     * @param cible   l'étudiant qui est soigné
     */
    public void soigner(Etudiant origine, Etudiant cible) {
        int x = new Random().nextInt(101);

        if (x <= 20 + 6 * origine.getDexterite()) {
            double y = Math.random() * 0.6;
            double creditsGagnes = y * (10 + cible.getConstitution());
            // Gain maximal de 30 + constitution du soigné
            cible.addCredits(Math.min((int) Math.floor(creditsGagnes), 30 + cible.getConstitution()));
        }
    }

    /**
     * Permet à un étudiant d'agir défensivement.
     *
     * @param origine         l'étudiant qui agit
     * @param cibleEquipeUne  l'étudiant de l'équipe une ayant le moins de crédits
     * @param cibleEquipeDeux l'étudiant de l'équipe deux ayant le moins de crédits
     */
    @Override
    public void agir(Etudiant origine, Etudiant cibleEquipeUne, Etudiant cibleEquipeDeux) {
        this.soigner(origine, origine.getEquipe() == Equipe.UNE ? cibleEquipeUne : cibleEquipeDeux);
    }

	/**
	 * @return la représentation textuelle de la stratégie défensive
	 */
    @Override
    public String toString() {
        return "Défensive";
    }

}