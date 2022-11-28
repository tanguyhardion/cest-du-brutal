import java.util.List;
import java.util.ArrayList;

/**
 * Classe principale du jeu, implémentant un Singleton.
 * 
 * @author Tristan JAUSSAUD
 * @author Tanguy HARDION
 * @version 1.0
 */
public class Partie {

	private Joueur joueur1;
	private Joueur joueur2;
	private List<Zone> zones;
	private static Partie instance;

	/**
	 * Constructeur privé de la classe Partie.
	 * <p>
	 * Initialise les joueurs et les zones de la partie.
	 */
	private Partie() {
		this.joueur1 = new Joueur("Joueur 1");
		this.joueur2 = new Joueur("Joueur 2");
		this.zones = new ArrayList<Zone>();

		// on récupère le nom de chaque zone
		NomZone[] zones = NomZone.values();
		// on crée une zone pour chaque nom de zone
		for (NomZone nom : zones) {
			this.zones.add(new Zone(nom));
		}
	}

	/**
	 * Crée l'instance de la Partie, si elle n'existe pas encore, et la renvoie.
	 * 
	 * @return l'instance de la Partie
	 */
	public static synchronized Partie getInstance() {
		if (instance == null) {
			instance = new Partie();
		}
		return instance;
	}

	/**
	 * Commence et gère la partie.
	 */
	public void commencer() {
		// boucle pour les tests
		/* for (int i = 1; i <= 20; i++) {
			this.joueur1.addEtudiant(i, new Etudiant(2, 2, 2, 2, 2));
			this.joueur2.addEtudiant(i, new Etudiant(2, 2, 2, 2, 2));
		} */

		joueur1.initialiserTroupes(15, 4, 1);
		joueur1.parametrerTroupes();
		joueur1.choisirReservistes();
		joueur1.repartirTroupes(this.zones);
		
		joueur2.initialiserTroupes(15, 4, 1);
		joueur2.parametrerTroupes();
		joueur2.choisirReservistes();
		joueur2.repartirTroupes(this.zones);

		Joueur.closeScanner();
	}

	public Joueur getJoueur1() {
		return joueur1;
	}

	public Joueur getJoueur2() {
		return joueur2;
	}
}