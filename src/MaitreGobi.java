/**
 * Représente un Maître du gobi, un type d'étudiant dont les caractéristiques
 * initiales sont augmentées (+10 en constitution, +2 pour le reste).
 * 
 * @author Tanguy HARDION
 * @version 1.0
 */
public class MaitreGobi extends Etudiant {

	/**
	 * Constructeur de la classe MaitreGobi.
	 * <p>
	 * Initialise les crédits de ce Maître du gobi à 30 et ses caractéristiques à
	 * leurs valeurs augmentées.
	 */
	public MaitreGobi() {
		super(2, 2, 2, 10, 2);
	}

	/**
	 * Autre constructeur de la classe MaitreGobi.
	 * <p>
	 * Initialise les caractéristiques de ce Maître du gobi avec les valeurs passées
	 * en paramètres, augmentées de 10 pour la constitution et de 2 pour le reste.
	 * 
	 * @param dexterite    la dexterité à attribuer
	 * @param force        la force à attribuer
	 * @param resistance   la résistance à attribuer
	 * @param constitution la constitution à attribuer
	 * @param initiative   l'initiative à attribuer
	 */
	public MaitreGobi(int dexterite, int force, int resistance, int constitution, int initiative) {
		super(dexterite + 2, force + 2, resistance + 2, constitution + 10, initiative + 2);
	}

}