/**
 * Represente un etudiant qui sera deploye sur une zone de combat.
 * 
 * @author Tanguy HARDION
 * @version 1.0
 */
public class Etudiant {

	private int credits;
	private int dexterite;
	private int force;
	private int resistance;
	private int constitution;
	private int initiative;
	private StrategieEtudiant strategie;
	private boolean elimine;

	/**
	 * Constructeur de la classe Etudiant.
	 * <p>
	 * Initialise les credits de cet etudiant a 30.
	 */
	public Etudiant() {
		this.credits = 30;
	}

	/**
	 * Autre constructeur de la classe Etudiant.
	 * <p>
	 * Initialise les caracteristiques de cet etudiant avec les valeurs passees en
	 * parametres.
	 * <p>
	 * Sachant que les classes filles EtudiantElite et MaitreGobi ont des
	 * caracteristiques initiales augmentees, la valeur de chaque parametre est
	 * ajoute a la valeur de l'attribut correspondant.
	 * 
	 * @param dexterite    la dexterite a ajouter
	 * @param force        la force a ajouter
	 * @param resistance   la resistance a ajouter
	 * @param constitution la constitution a ajouter
	 * @param initiative   l'initiative a ajouter
	 */
	public Etudiant(int dexterite, int force, int resistance, int constitution, int initiative) {
		this.credits = 30;
		this.dexterite += dexterite;
		this.force += force;
		this.resistance += resistance;
		this.constitution += constitution;
		this.initiative += initiative;
	}

	public void agir(Etudiant cible) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @return les credits de cet etudiant
	 */
	public int getCredits() {
		return this.credits;
	}

	/**
	 * Retourne les credits de cet etudiant ajoutes a sa constitution.
	 * 
	 * @return les credits totaux de cet etudiant
	 */
	public int getCreditsTotal() {
		return this.credits + this.constitution;
	}

	/**
	 * Ajoute des credits a cet etudiant.
	 * <p>
	 * Un etudiant ne peut gagner des credits qu'en se faisant soigner par un autre
	 * etudiant.
	 * 
	 * @param credits le nombre de credits a ajouter
	 */
	public void addCredits(int credits) {
		this.credits += credits;
	}

	/**
	 * Enleve le nombre de credits specifie a cet etudiant.
	 * <p>
	 * Un etudiant devient elimine si ses credits ajoutes a sa constitution sont
	 * inferieurs ou egaux a 0.
	 * 
	 * @param credits le nombre de credits a enlever
	 */
	public void removeCredits(int credits) {
		this.credits -= credits;
		if (this.getCreditsTotal() <= 0) {
			this.elimine = true;
		}
	}

	/**
	 * @return la dexterite de cet etudiant
	 */
	public int getDexterite() {
		return this.dexterite;
	}

	/**
	 * Ajoute la dexterite specifiee a la valeur initiale de la dexterite de cet
	 * etudiant.
	 * 
	 * @param dexterite la dexterite a ajouter a cet etudiant
	 * @throws IllegalArgumentException si la dexterite specifiee est inferieure a 0
	 *                                  ou superieure a 10
	 */
	public void addDexterite(int dexterite) {
		if (dexterite < 0 || dexterite > 10) {
			throw new IllegalArgumentException("La dexterite doit être comprise entre 0 et 10.");
		}
		this.dexterite += dexterite;
	}

	/**
	 * @return la force de cet etudiant
	 */
	public int getForce() {
		return this.force;
	}

	/**
	 * Ajoute la force specifiee a la valeur initiale de la force de cet etudiant.
	 * 
	 * @param force la force a ajouter a cet etudiant
	 * @throws IllegalArgumentException si la force specifiee est inferieure a 0 ou
	 *                                  superieure a 10
	 */
	public void addForce(int force) {
		if (force < 0 || force > 10) {
			throw new IllegalArgumentException("La force doit être comprise entre 0 et 10.");
		}
		this.force += force;
	}

	/**
	 * @return la resistance de cet etudiant
	 */
	public int getResistance() {
		return this.resistance;
	}

	/**
	 * Ajoute la resistance specifiee a la valeur initiale de la resistance de cet
	 * etudiant.
	 * 
	 * @param resistance la resistance a ajouter a cet etudiant
	 * @throws IllegalArgumentException si la resistance specifiee est inferieure a
	 *                                  0 ou superieure a 10
	 */
	public void addResistance(int resistance) {
		if (resistance < 0 || resistance > 10) {
			throw new IllegalArgumentException("La resistance doit être comprise entre 0 et 10.");
		}
		this.resistance += resistance;
	}

	/**
	 * @return la constitution de cet etudiant
	 */
	public int getConstitution() {
		return this.constitution;
	}

	/**
	 * Ajoute la constitution specifiee a la valeur initiale de la constitution de
	 * cet etudiant.
	 * 
	 * @param constitution la constitution a ajouter a cet etudiant
	 * @throws IllegalArgumentException si la constitution specifiee est inferieure
	 *                                  a 0 ou superieure a 30
	 */
	public void addConstitution(int constitution) {
		if (constitution < 0 || constitution > 30) {
			throw new IllegalArgumentException("La constitution doit être comprise entre 0 et 30.");
		}
		this.constitution += constitution;
	}

	/**
	 * @return l'initiative de cet etudiant
	 */
	public int getInitiative() {
		return this.initiative;
	}

	/**
	 * Ajoute l'initiative specifiee a la valeur initiale de l'initiative de cet
	 * etudiant.
	 * 
	 * @param initiative l'initiative a ajouter a cet etudiant
	 * @throws IllegalArgumentException si l'initiative specifiee est inferieure a 0
	 *                                  ou superieure a 10
	 */
	public void addInitiative(int initiative) {
		if (initiative < 0 || initiative > 10) {
			throw new IllegalArgumentException("L'initiative doit être comprise entre 0 et 10.");
		}
		this.initiative += initiative;
	}

	/**
	 * @return la strategie de cet etudiant
	 */
	public StrategieEtudiant getStrategie() {
		return this.strategie;
	}

	/**
	 * Definit la strategie de cet etudiant.
	 * <p>
	 * La strategie peut être offensive, defensive ou aleatoire.
	 * 
	 * @param strategie la nouvelle strategie de l'etudiant
	 * @throws IllegalArgumentException si la strategie est nulle
	 */
	public void setStrategie(StrategieEtudiant strategie) {
		if (strategie == null) {
			throw new IllegalArgumentException("Strategie non reconnue.");
		}
		this.strategie = strategie;
	}

	/**
	 * Indique si cet etudiant est elimine.
	 * 
	 * @return {@code true} si l'etudiant est elimine, {@code false} sinon
	 */
	public boolean isElimine() {
		return this.elimine;
	}

	/**
	 * Renvoie une representation textuelle de cet etudiant composee de son type et
	 * de ses caracteristiques.
	 * 
	 * @return une representation textuelle de cet etudiant
	 */
	public String toString() {
		String type = this instanceof MaitreGobi ? "(Maître du gobi)"
				: this instanceof EtudiantElite ? "(Étudiant d'élite)" : "(Étudiant)";
		String strategie = this.strategie != null ? this.strategie.getClass().getSimpleName().substring(9).toLowerCase()
				: "aucune";

		StringBuffer sb = new StringBuffer();
		sb.append(type);
		sb.append(" | Dextérité : ").append(this.dexterite);
		sb.append(" | Force : ").append(this.force);
		sb.append(" | Résistance : ").append(this.resistance);
		sb.append(" | Constitution : ").append(this.constitution);
		sb.append(" | Initiative : ").append(this.initiative);
		sb.append(" | Stratégie : ").append(strategie);
		return sb.toString();
	}

}