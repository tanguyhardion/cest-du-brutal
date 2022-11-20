import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Comparator;

/**
 * Représente une zone d'influence sur laquelle des combattants seront déployés.
 * 
 * @author Tanguy HARDION
 * @author Tristan JAUSSAUD
 * @version 1.0
 */
public class Zone {

	private boolean controlee;
	private NomZone nomZone;
	private Map<Integer, Etudiant> troupesJoueur1;
	private Map<Integer, Etudiant> troupesJoueur2;

	/**
	 * Constructeur de la classe Zone.
	 * <p>
	 * Initialise les attributs de cette zone, notamment son nom avec la valeur
	 * passée en paramètre.
	 * 
	 * @param nomZone le nom de la zone, de type NomZone
	 */
	public Zone(NomZone nomZone) {
		this.setNomZone(nomZone);
		this.troupesJoueur1 = new HashMap<Integer, Etudiant>();
		this.troupesJoueur2 = new HashMap<Integer, Etudiant>();
	}

	/**
	 * Ajoute un étudiant du Joueur 1 à cette zone.
	 * 
	 * @param key      le numéro de l'étudiant
	 * @param etudiant l'étudiant à ajouter
	 */
	public void addEtudiantJoueur1(int key, Etudiant etudiant) {
		this.troupesJoueur1.put(key, etudiant);
	}

	/**
	 * Ajoute un étudiant du Joueur 2 à cette zone.
	 * 
	 * @param key      le numéro de l'étudiant
	 * @param etudiant l'étudiant à ajouter
	 */
	public void addEtudiantJoueur2(int key, Etudiant etudiant) {
		this.troupesJoueur2.put(key, etudiant);
	}

	/**
	 * Retourne la liste de tous les étudiants présents sur cette zone triés par
	 * leur initiative, de la plus grande à la plus petite.
	 * 
	 * @return la liste des étudiants triés par initiative
	 */
	public List<Etudiant> getTroupesParInitiative() {
		List<Etudiant> troupes = new ArrayList<Etudiant>();
		troupes.addAll(this.troupesJoueur1.values());
		troupes.addAll(this.troupesJoueur2.values());
		troupes.sort(Comparator.comparingInt(Etudiant::getInitiative).reversed());
		return troupes;
	}

	/**
	 * Retourne la liste des étudiants du Joueur 1 présents sur cette zone triés par
	 * crédits, du plus petit au plus grand.
	 * 
	 * @return la liste des étudiants du Joueur 1 triés par crédits
	 */
	public List<Etudiant> getTroupesJoueur1ParCredits() {
		List<Etudiant> troupes = new ArrayList<Etudiant>(this.troupesJoueur1.values());
		troupes.sort(Comparator.comparingDouble(Etudiant::getCreditsTotal));
		return troupes;
	}

	/**
	 * Retourne la liste des étudiants du Joueur 2 présents sur cette zone triés par
	 * crédits, du plus petit au plus grand.
	 * 
	 * @return la liste des étudiants du Joueur 2 triés par crédits
	 */
	public List<Etudiant> getTroupesJoueur2ParCredits() {
		List<Etudiant> troupes = new ArrayList<Etudiant>(this.troupesJoueur2.values());
		troupes.sort(Comparator.comparingDouble(Etudiant::getCreditsTotal));
		return troupes;
	}

	/**
	 * Retourne le total des crédits des étudiants du Joueur 1 présents sur cette
	 * zone.
	 * 
	 * @return le total des crédits des étudiants du Joueur 1
	 */
	public int getCreditsTroupesJoueur1() {
		int credits = 0;
		for (Etudiant e : this.troupesJoueur1.values()) {
			credits += e.getCreditsTotal();
		}
		return credits;
	}

	/**
	 * Retourne le total des crédits des étudiants du Joueur 2 présents sur cette
	 * zone.
	 * 
	 * @return le total des crédits des étudiants du Joueur 2
	 */
	public int getCreditsTroupesJoueur2() {
		int credits = 0;
		for (Etudiant e : this.troupesJoueur2.values()) {
			credits += e.getCreditsTotal();
		}
		return credits;
	}

	/**
	 * @return {@code true} si cette zone est contrôlée par un des joueurs,
	 *         {@code false} sinon
	 */
	public boolean estControlee() {
		return controlee;
	}

	/**
	 * Définit le nom de la zone
	 * 
	 * @param nomZone
	 */
	public void setNomZone(NomZone nomZone) {
		if (nomZone == null) {
			throw new IllegalArgumentException("Nom de zone incorrect.");
		}
		this.nomZone = nomZone;
	}

	public Map<Integer, Etudiant> getTroupesJoueur1() {
		return troupesJoueur1;
	}

	public Map<Integer, Etudiant> getTroupesJoueur2() {
		return troupesJoueur2;
	}

	@Override
	public String toString() {
		switch (this.nomZone) {
			case BDE:
				return "BDE";
			case BIBLIOTHEQUE:
				return "Bibliothèque";
			case HALLESINDUSTRIELLES:
				return "Halles industrielles";
			case HALLESPORTIVE:
				return "Halle sportive";
			case QUARTIERADMINISTRATIF:
				return "Quartier administratif";
			default:
				return "Zone inconnue";
		}
	}

}