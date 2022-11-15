import java.util.Map;
import java.util.ArrayList;

public class Joueur {
	private String nom;
	private Map<Integer, Etudiant> troupes;
	private Map<Integer, Etudiant> reservistes;
	public ArrayList<Zone> zoneControlees = new ArrayList<Zone>();

	public Joueur(String nom) {
		this.nom = nom;
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

	public ArrayList<Zone> getZoneControlees() {
		return this.zoneControlees;
	}

	public void ajouterZoneControlee(Zone zone) {
		throw new UnsupportedOperationException();
	}

	public void enleverZoneControlee(Zone zone) {
		throw new UnsupportedOperationException();
	}
}