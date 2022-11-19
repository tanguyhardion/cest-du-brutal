import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * Représente un joueur du jeu, possédant des troupes.
 * 
 * @author Tristan JAUSSAUD
 * @author Tanguy HARDION
 */
public class Joueur {

	private String nom;
	private int points;
	private Map<Integer, Etudiant> troupes;
	private Map<Integer, Etudiant> reservistes;
	private List<Zone> zoneControlees = new ArrayList<Zone>();

	/**
	 * Constructeur de la classe Joueur.
	 * <p>
	 * Initialise les différents attributs de ce joueur.
	 * 
	 * @param nom
	 */
	public Joueur(String nom) {
		this.nom = nom;
		this.points = 400;
		this.troupes = new HashMap<Integer, Etudiant>();
		this.reservistes = new HashMap<Integer, Etudiant>();
		this.zoneControlees = new ArrayList<Zone>();
	}

	/**
	 * Initialise les troupes de ce joueur en créeant et en lui attribuant le nombre
	 * souhaité de chaque type d'étudiant.
	 * 
	 * @param etudiants      le nombre d'étudiants à créer
	 * @param etudiantsElite le nombre d'étudiants d'élite à créer
	 * @param maitresGobi    le nombre de Maîtres du gobi à créer
	 */
	public void initialiserTroupes(int etudiants, int etudiantsElite, int maitresGobi) {
		for (int i = 0; i < etudiants + etudiantsElite + maitresGobi; i++) {
			if (i < etudiants) {
				this.addEtudiant(i, new Etudiant());
			} else if (i < etudiants + etudiantsElite) {
				this.addEtudiant(i, new EtudiantElite());
			} else {
				this.addEtudiant(i, new MaitreGobi());
			}
		}
	}

	public void parametrerTroupes() {
		throw new UnsupportedOperationException();
	}

	public void choisirReservistes() {
		throw new UnsupportedOperationException();
	}

	public void repartirTroupes() {
		throw new UnsupportedOperationException();
	}

	public void affecterReservistes() {
		throw new UnsupportedOperationException();
	}

	public void redeployerTroupes() {
		throw new UnsupportedOperationException();
	}

	public void afficherTroupes(Map<Integer, Etudiant> troupes) {
		throw new UnsupportedOperationException();
	}

	public void afficherReservistes(Map<Integer, Etudiant> reservistes) {
		throw new UnsupportedOperationException();
	}

	public String getNom() {
		return this.nom;
	}

	public Map<Integer, Etudiant> getTroupes() {
		return this.troupes;
	}

	public void addEtudiant(int key, Etudiant etudiant) {
		this.troupes.put(key, etudiant);
	}

	public void removeEtudiant(int key) {
		this.troupes.remove(key);
	}

	public Map<Integer, Etudiant> getReservistes() {
		return this.reservistes;
	}

	public void addReserviste(int key, Etudiant etudiant) {
		this.reservistes.put(key, etudiant);
	}

	public void removeReserviste(int key) {
		this.reservistes.remove(key);
	}

	public List<Zone> getZoneControlees() {
		return this.zoneControlees;
	}

	public void addZoneControlee(Zone zone) {
		this.zoneControlees.add(zone);
	}

}