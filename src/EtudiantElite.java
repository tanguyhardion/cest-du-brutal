/**
 * Représente un étudiant d'élite, un type d'étudiant dont les caractéristiques
 * initiales sont augmentées (+5 en constitution, +1 pour le reste).
 * 
 * @author Tanguy HARDION
 * @version 1.0
 */
public class EtudiantElite extends Etudiant {

	/**
	 * Constructeur de la classe EtudiantElite.
	 * <p>
	 * Initialise les crédits de cet étudiant d'élite à 30 et ses caractéristiques à
	 * leurs valeurs augmentées.
	 */
	public EtudiantElite() {
		super(1, 1, 1, 5, 1);
	}

	/**
	 * Autre constructeur de la classe EtudiantElite.
	 * <p>
	 * Initialise les caractéristiques de cet étudiant d'élite avec les valeurs
	 * passées en paramètres, augmentées de 5 pour la constitution et de 1 pour le
	 * reste.
	 * 
	 * @param dexterite    la dexterité à attribuer
	 * @param force        la force à attribuer
	 * @param resistance   la résistance à attribuer
	 * @param constitution la constitution à attribuer
	 * @param initiative   l'initiative à attribuer
	 */
	public EtudiantElite(int dexterite, int force, int resistance, int constitution, int initiative) {
		super(dexterite + 1, force + 1, resistance + 1, constitution + 5, initiative + 1);
	}

}