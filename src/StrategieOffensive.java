/**
 * Represente l'action realisee par un Etudiant dont la strategie est offensive.
 * 
 * @author Tristan JAUSSAUD
 * @version 1.0
 */
public class StrategieOffensive implements StrategieEtudiant {

	/**
	 * Attaque un autre etudiant en lui enelevant des credits.
	 * 
	 * @param origine l'etudiant qui attaque
	 * @param cible l'etudiant qui est attaque
	 */
	public void agir(Etudiant origine, Etudiant cible) {

		double x = Math.random() * 100;

		if (x <= (40 + 3 * origine.getDexterite())) {
			double y = Math.random();
			double coeffDegat = Math.max(0, Math.min(100, 10 * origine.getForce() - 5 * cible.getResistance()));
			cible.removeCredits(Math.floor(y * (1 + coeffDegat) * 10));
		}

		throw new UnsupportedOperationException();
	}

}