/**
 * Represente un Maitre du gobi, un type d'etudiant dont les caracteristiques
 * initiales sont augmentées (+10 en constitution, +2 pour le reste).
 * 
 * @author Tanguy HARDION
 * @version 1.0
 */
public class MaitreGobi extends Etudiant {

	/**
	 * Constructeur de la classe MaitreGobi.
	 * <p>
	 * Initialise les credits de ce Maitre du gobi a 30 et ses caractéristiques a
	 * leurs valeurs augmentees.
	 */
	public MaitreGobi() {
		super(2, 2, 2, 10, 2);
	}

	/**
	 * Autre constructeur de la classe MaitreGobi.
	 * <p>
	 * Initialise les caracteristiques de ce Maitre du gobi avec les valeurs passees
	 * en parametres, augmentees de 10 pour la constitution et de 2 pour le reste.
	 * 
	 * @param dexterite    la dexterite a attribuer
	 * @param force        la force a attribuer
	 * @param resistance   la resistance a attribuer
	 * @param constitution la constitution a attribuer
	 * @param initiative   l'initiative a attribuer
	 */
	public MaitreGobi(int dexterite, int force, int resistance, int constitution, int initiative) {
		super(dexterite + 2, force + 2, resistance + 2, constitution + 10, initiative + 2);
	}

}