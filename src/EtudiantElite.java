/**
 * Represente un Etudiant d'elite, un type d'etudiant dont les caracteristiques
 * initiales sont augmentées (+5 en constitution, +1 pour le reste).
 * 
 * @author Tanguy HARDION
 * @version 1.0
 */
public class EtudiantElite extends Etudiant {

	/**
	 * Constructeur de la classe EtudiantElite.
	 * <p>
	 * Initialise les credits de cet Etudiant d'elite a 30 et ses caractéristiques a
	 * leurs valeurs augmentees.
	 */
	public EtudiantElite() {
		super(1, 1, 1, 5, 1);
	}

	/**
	 * Autre constructeur de la classe EtudiantElite.
	 * <p>
	 * Initialise les caracteristiques de cet Etudiant d'elite avec les valeurs
	 * passees en parametres, augmentees de 5 pour la constitution et de 1 pour le
	 * reste.
	 * 
	 * @param dexterite    la dexterite a attribuer
	 * @param force        la force a attribuer
	 * @param resistance   la resistance a attribuer
	 * @param constitution la constitution a attribuer
	 * @param initiative   l'initiative a attribuer
	 */
	public EtudiantElite(int dexterite, int force, int resistance, int constitution, int initiative) {
		super(dexterite + 1, force + 1, resistance + 1, constitution + 5, initiative + 1);
	}

}