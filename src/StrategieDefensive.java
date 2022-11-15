import java.util.Random;

/**
 * Represente l'action à realiser d'un etudiant dont la strategie est defensive.
 * 
 * @author Tanguy HARDION
 * @version 1.0
 */
public class StrategieDefensive implements StrategieEtudiant {

	/**
	 * Soigne un autre etudiant (en lui ajoutant des credits) en fonction de la
	 * dexterite du soignant et de la constitution du soigne.
	 * 
	 * @param origine l'etudiant qui soigne
	 * @param cible   l'etudiant qui est soigne
	 */
	public void agir(Etudiant origine, Etudiant cible) {
		int x = new Random().nextInt(101);

		if (x <= 20 + 6 * origine.getDexterite()) {
			double y = Math.random() * 0.6;
			double creditsGagnes = y * (10 + cible.getConstitution());
			cible.addCredits(Math.min((int) Math.floor(creditsGagnes), 30 + cible.getConstitution()));
		}
	}

}