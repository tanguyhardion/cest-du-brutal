/**
 * Représente un étudiant qui sera deployé sur une zone de combat.
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
	private boolean elimine;
	private Equipe equipe;
	private StrategieEtudiant strategie;

	/**
	 * Constructeur de la classe Etudiant.
	 * <p>
	 * Initialise les crédits de cet étudiant à 30.
	 */
	public Etudiant(Equipe equipe) {
		this.credits = 30;
		this.equipe = equipe;
	}

	/**
	 * Autre constructeur de la classe Etudiant.
	 * <p>
	 * Initialise les caractéristiques de cet étudiant avec les valeurs passées en
	 * paramètres.
	 * 
	 * @param dexterite    la dexterité à attribuer
	 * @param force        la force à attribuer
	 * @param resistance   la résistance à attribuer
	 * @param constitution la constitution à attribuer
	 * @param initiative   l'initiative à attribuer
	 */
	public Etudiant(int dexterite, int force, int resistance, int constitution, int initiative, Equipe equipe) {
		this.credits = 30;
		this.setDexterite(dexterite);
		this.setForce(force);
		this.setResistance(resistance);
		this.setConstitution(constitution);
		this.setInitiative(initiative);
		this.equipe = equipe;
	}

	/**
	 * Fait agir cet étudiant sur l'étudiant passe en parametre.
	 * <p>
	 * Selon la stratégie de cet étudiant, il va attaquer un adversaire ou soigner
	 * un allié.
	 * 
	 * @param cible l'étudiant sur lequel l'action est effectuée
	 */
	public void agir(Etudiant cibleEquipeUne, Etudiant cibleEquipeDeux) {
		this.strategie.agir(this, cibleEquipeUne, cibleEquipeDeux);
	}

	/**
	 * @return les crédits de cet étudiant
	 */
	public int getCredits() {
		return this.credits;
	}

	/**
	 * Retourne les crédits de cet étudiant ajoutés à sa constitution.
	 * 
	 * @return les crédits totaux de cet étudiant
	 */
	public int getCreditsTotal() {
		return this.credits + this.constitution;
	}

	/**
	 * Ajoute des crédits à cet étudiant.
	 * <p>
	 * Un étudiant ne peut gagner des crédits qu'en se faisant soigner par un
	 * étudiant allié.
	 * 
	 * @param credits le nombre de crédits à ajouter
	 */
	public void addCredits(int credits) {
		this.credits += credits;
	}

	/**
	 * Enlève le nombre de crédits specifié à cet étudiant.
	 * <p>
	 * Un étudiant devient eliminé si ses credits ajoutés à sa constitution sont
	 * inférieurs ou égaux à 0.
	 * 
	 * @param credits le nombre de crédits à enlever
	 */
	public void removeCredits(int credits) {
		this.credits -= credits;
		if (this.getCreditsTotal() <= 0) {
			this.elimine = true;
		}
	}

	/**
	 * @return la dexterité de cet étudiant
	 */
	public int getDexterite() {
		return this.dexterite;
	}

	/**
	 * Définit la dextérité de cet étudiant.
	 * 
	 * @param dexterite la dexterité à affecter à cet étudiant
	 * @throws IllegalArgumentException si la dexterite specifiée est inférieure à 0
	 *                                  ou supérieure à 10
	 */
	public void setDexterite(int dexterite) {
		if (dexterite < 0 || dexterite > 10) {
			throw new IllegalArgumentException("La dexterite doit être comprise entre 0 et 10.");
		}
		this.dexterite += dexterite;
	}

	/**
	 * @return la force de cet étudiant
	 */
	public int getForce() {
		return this.force;
	}

	/**
	 * Définit la force de cet étudiant.
	 * 
	 * @param force la force à affecter à cet étudiant
	 * @throws IllegalArgumentException si la force specifiée est inférieure à 0 ou
	 *                                  supérieure à 10
	 */
	public void setForce(int force) {
		if (force < 0 || force > 10) {
			throw new IllegalArgumentException("La force doit être comprise entre 0 et 10.");
		}
		this.force += force;
	}

	/**
	 * @return la résistance de cet étudiant
	 */
	public int getResistance() {
		return this.resistance;
	}

	/**
	 * Définit la résistance de cet étudiant.
	 * 
	 * @param resistance la résistance à affecter à cet étudiant
	 * @throws IllegalArgumentException si la résistance specifiée est inférieure à
	 *                                  0 ou supérieure à 10
	 */
	public void setResistance(int resistance) {
		if (resistance < 0 || resistance > 10) {
			throw new IllegalArgumentException("La resistance doit être comprise entre 0 et 10.");
		}
		this.resistance += resistance;
	}

	/**
	 * @return la constitution de cet étudiant
	 */
	public int getConstitution() {
		return this.constitution;
	}

	/**
	 * Définit la constitution de cet étudiant.
	 * 
	 * @param constitution la constitution à affecter à cet étudiant
	 * @throws IllegalArgumentException si la constitution specifiée est inférieure
	 *                                  à 0 ou supérieure à 30
	 */
	public void setConstitution(int constitution) {
		if (constitution < 0 || constitution > 3000) {
			throw new IllegalArgumentException("La constitution doit être comprise entre 0 et 30.");
		}
		this.constitution += constitution;
	}

	/**
	 * @return l'initiative de cet étudiant
	 */
	public int getInitiative() {
		return this.initiative;
	}

	/**
	 * Définit l'initiative de cet étudiant.
	 * 
	 * @param initiative l'initiative à affecter à cet étudiant
	 * @throws IllegalArgumentException si l'initiative specifiée est inférieure à 0
	 *                                  ou supérieure à 10
	 */
	public void setInitiative(int initiative) {
		if (initiative < 0 || initiative > 10) {
			throw new IllegalArgumentException("L'initiative doit être comprise entre 0 et 10.");
		}
		this.initiative += initiative;
	}

	/**
	 * Indique si cet étudiant est éliminé.
	 * 
	 * @return {@code true} si l'étudiant est éliminé, {@code false} sinon
	 */
	public boolean isElimine() {
		return this.elimine;
	}

	/**
	 * @return l'équipe de cet étudiant
	 */
	public Equipe getEquipe() {
		return this.equipe;
	}

	/**
	 * @return la stratégie de cet étudiant
	 */
	public StrategieEtudiant getStrategie() {
		return this.strategie;
	}

	/**
	 * Définit la stratégie de cet étudiant.
	 * <p>
	 * La stratégie peut être offensive, défensive ou aléatoire.
	 * 
	 * @param strategie la stratégie à affecter à cet étudiant
	 * @throws IllegalArgumentException si la strategie est nulle
	 */
	public void setStrategie(StrategieEtudiant strategie) {
		if (strategie == null) {
			throw new IllegalArgumentException("Strategie non reconnue.");
		}
		this.strategie = strategie;
	}

	/**
	 * Renvoie une représentation textuelle de cet étudiant composée de son type et
	 * de ses caractéristiques.
	 * 
	 * @return une représentation textuelle de cet étudiant
	 */
	public String toString() {
		String type = this instanceof MaitreGobi ? "(Maître du gobi)"
				: this instanceof EtudiantElite ? "(Étudiant d'élite)" : "(Étudiant)";
		String strategie = this.strategie != null ? this.strategie.getClass().getSimpleName().substring(9).toLowerCase()
				: "aucune";

		StringBuffer sb = new StringBuffer();
		sb.append(Couleurs.BLANC).append(type).append(Couleurs.RESET);
		sb.append(" | Dextérité : ").append(this.dexterite);
		sb.append(" | Force : ").append(this.force);
		sb.append(" | Résistance : ").append(this.resistance);
		sb.append(" | Constitution : ").append(this.constitution);
		sb.append(" | Initiative : ").append(this.initiative);
		sb.append(" | Stratégie : ").append(strategie);
		return sb.toString();
	}

}