/**
 * Représente l'action réalisée par un étudiant dont la stratégie est offensive.
 * 
 * @author Tristan JAUSSAUD
 * @version 1.0
 */
public class StrategieOffensive implements StrategieEtudiant {

	/**
	 * Attaque un autre étudiant en lui enlevant des crédits.
	 * 
	 * @param origine l'etudiant qui attaque
	 * @param cible   l'etudiant qui est attaqué
	 */
	public void agir(Etudiant origine, Etudiant cible) {

		double x = Math.random() * 100;

		if (x <= (40 + 3 * origine.getDexterite())) {
			double y = Math.random();
			double coeffDegat = Math.max(0, Math.min(100, 10 * origine.getForce() - 5 * cible.getResistance()));
			cible.removeCredits((int) Math.floor(y * (1 + coeffDegat) * 10));
		}
	}

}