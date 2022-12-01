import java.util.List;
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
	 * @param origine l'etudiant qui soigne
	 * @param cible   l'etudiant qui est soigne
	 */
	public void soigner(Etudiant origine, Etudiant cible) {
		int x = new Random().nextInt(101);

		if (x <= 20 + 6 * origine.getDexterite()) {
			double y = Math.random() * 0.6;
			double creditsGagnes = y * (10 + cible.getConstitution());
			cible.addCredits(Math.min((int) Math.floor(creditsGagnes), 30 + cible.getConstitution()));
		}
	}

	@Override
	public void agir(Etudiant origine, Etudiant cibleEquipeUne, Etudiant cibleEquipeDeux) {
		if (origine.getEquipe() == Equipe.UNE) {
			this.soigner(origine, cibleEquipeUne);
		} else {
			this.soigner(origine, cibleEquipeDeux);
		}
	}

}