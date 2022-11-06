/**
 * Représente un étudiant qui sera déployé sur une zone de combat.
 * 
 * @author Tanguy HARDION
 * @version 1.0
 */
public class Etudiant {

	private double credits;
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
	 * Initialise les crédits de cet étudiant à 30.
	 */
	public Etudiant() {
		this.credits = 30;
	}

	/**
	 * Autre constructeur de la classe Etudiant.
	 * <p>
	 * Initialise les caractéristiques de cet étudiant avec les valeurs passées en
	 * paramètres.
	 * <p>
	 * Sachant que les classes filles EtudiantElite et MaitreGobi ont des
	 * caractéristiques initiales augmentées, la valeur de chaque paramètre est
	 * ajouté à la valeur de l'attribut correspondant.
	 * 
	 * @param dexterite    la dextérité à ajouter.
	 * @param force        la force à ajouter.
	 * @param resistance   la résistance à ajouter.
	 * @param constitution la constitution à ajouter.
	 * @param initiative   l'initiative à ajouter.
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
	 * @return les crédits de cet étudiant.
	 */
	public double getCredits() {
		return this.credits;
	}

	/**
	 * Retourne les crédits de cet étudiant ajoutés à sa constitution.
	 * 
	 * @return les crédits totaux de cet étudiant.
	 */
	public double getCreditsTotal() {
		return this.credits + this.constitution;
	}

	/**
	 * Ajoute des crédits à cet étudiant.
	 * <p>
	 * Un étudiant ne peut gagner des crédits qu'en se faisant soigner par un autre
	 * étudiant.
	 * 
	 * @param credits le nombre de crédits à ajouter.
	 */
	public void addCredits(double credits) {
		this.credits += credits;
	}

	/**
	 * Enlève le nombre de crédits spécifié à cet étudiant.
	 * <p>
	 * Un étudiant devient éliminé si ses crédits ajoutés à sa constitution sont
	 * inférieurs ou égaux à 0.
	 * 
	 * @param credits le nombre de crédits à enlever.
	 */
	public void removeCredits(double credits) {
		this.credits -= credits;
		if (this.getCreditsTotal() <= 0) {
			this.elimine = true;
		}
	}

	/**
	 * @return la dextérité de cet étudiant.
	 */
	public int getDexterite() {
		return this.dexterite;
	}

	/**
	 * Ajoute la dextérité spécifiée à la valeur initiale de la dextérité de cet
	 * étudiant.
	 * 
	 * @param dexterite la dextérité à ajouter à cet étudiant.
	 * @throws IllegalArgumentException si la dextérité spécifiée est inférieure à 0
	 *                                  ou
	 *                                  supérieure à 10.
	 */
	public void addDexterite(int dexterite) {
		if (dexterite < 0 || dexterite > 10) {
			throw new IllegalArgumentException("La dextérité doit être comprise entre 0 et 10.");
		}
		this.dexterite += dexterite;
	}

	/**
	 * @return la force de cet étudiant.
	 */
	public int getForce() {
		return this.force;
	}

	/**
	 * Ajoute la force spécifiée à la valeur initiale de la force de cet étudiant.
	 * 
	 * @param force la force à ajouter à cet étudiant.
	 * @throws IllegalArgumentException si la force spécifiée est inférieure à 0 ou
	 *                                  supérieure à 10.
	 */
	public void addForce(int force) {
		if (force < 0 || force > 10) {
			throw new IllegalArgumentException("La force doit être comprise entre 0 et 10.");
		}
		this.force += force;
	}

	/**
	 * @return la résistance de cet étudiant.
	 */
	public int getResistance() {
		return this.resistance;
	}

	/**
	 * Ajoute la résistance spécifiée à la valeur initiale de la résistance de cet
	 * étudiant.
	 * 
	 * @param resistance la résistance à ajouter à cet étudiant.
	 * @throws IllegalArgumentException si la résistance spécifiée est inférieure à
	 *                                  0 ou supérieure à 10.
	 */
	public void addResistance(int resistance) {
		if (resistance < 0 || resistance > 10) {
			throw new IllegalArgumentException("La résistance doit être comprise entre 0 et 10.");
		}
		this.resistance += resistance;
	}

	/**
	 * @return la constitution de cet étudiant.
	 */
	public int getConstitution() {
		return this.constitution;
	}

	/**
	 * Ajoute la constitution spécifiée à la valeur initiale de la constitution de
	 * cet étudiant.
	 * 
	 * @param constitution la constitution à ajouter à cet étudiant.
	 * @throws IllegalArgumentException si la constitution spécifiée est inférieure
	 *                                  à 0 ou supérieure à 10.
	 */
	public void addConstitution(int constitution) {
		if (constitution < 0 || constitution > 10) {
			throw new IllegalArgumentException("La constitution doit être comprise entre 0 et 10.");
		}
		this.constitution += constitution;
	}

	/**
	 * @return l'initiative de cet étudiant.
	 */
	public int getInitiative() {
		return this.initiative;
	}

	/**
	 * Ajoute l'initiative spécifiée à la valeur initiale de l'initiative de cet
	 * étudiant.
	 * 
	 * @param initiative l'initiative à ajouter à cet étudiant.
	 * @throws IllegalArgumentException si l'initiative spécifiée est inférieure à 0
	 *                                  ou supérieure à 10.
	 */
	public void addInitiative(int initiative) {
		if (initiative < 0 || initiative > 10) {
			throw new IllegalArgumentException("L'initiative doit être comprise entre 0 et 10.");
		}
		this.initiative += initiative;
	}

	/**
	 * @return la stratégie de cet étudiant.
	 */
	public StrategieEtudiant getStrategie() {
		return this.strategie;
	}

	/**
	 * Définit la stratégie de cet étudiant.
	 * <p>
	 * La stratégie peut être offensive, défensive ou aléatoire.
	 * 
	 * @param strategie la nouvelle stratégie de l'étudiant.
	 * @throws IllegalArgumentException si la stratégie est nulle.
	 */
	public void setStrategie(StrategieEtudiant strategie) {
		if (strategie == null) {
			throw new IllegalArgumentException("Stratégie non reconnue.");
		}
		this.strategie = strategie;
	}

	/**
	 * Indique si cet étudiant est éliminé.
	 * 
	 * @return {@code true} si l'étudiant est éliminé, {@code false} sinon.
	 */
	public boolean isElimine() {
		return this.elimine;
	}

	/**
	 * Renvoie une représentation textuelle de cet étudiant composée de son type et
	 * de ses caractéristiques.
	 * 
	 * @return une représentation textuelle de cet étudiant.
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