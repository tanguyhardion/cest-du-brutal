package fr.utt.lo02.cdb.model;

import fr.utt.lo02.cdb.model.enums.Equipe;

import java.util.Random;

/**
 * Represente l'action à réaliser d'un étudiant dont la stratégie est défensive.
 * 
 * @author Tanguy HARDION
 * @version 1.0
 */
public class StrategieDefensive implements StrategieEtudiant {

	/**
	 * Soigne un autre étudiant (en lui ajoutant des crédits) en fonction de la
	 * dexterité du soignant et de la constitution du soigné.
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

	@Override
	public void agir(Etudiant origine, Etudiant cibleEquipeUne, Etudiant cibleEquipeDeux) {
		this.soigner(origine, origine.getEquipe() == Equipe.UNE ? cibleEquipeUne : cibleEquipeDeux);
	}

	@Override
	public String toString() {
		return "Défensive";
	}

}