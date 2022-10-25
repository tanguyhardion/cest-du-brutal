import java.util.Map;
import java.util.ArrayList;

public class Joueur {
	private String nom;
	private Map<Integer, Etudiant> troupes;
	private Map<Integer, Etudiant> reservistes;
	public ArrayList<Zone> zoneControlees = new ArrayList<Zone>();

	public Joueur(String nom) {
		throw new UnsupportedOperationException();
	}

	public String getNom() {
		throw new UnsupportedOperationException();
	}

	public Map<Integer, Etudiant> getTroupes() {
		throw new UnsupportedOperationException();
	}

	public void addEtudiant(int key, Etudiant etudiant) {
		throw new UnsupportedOperationException();
	}

	public void removeEtudiant(int key) {
		throw new UnsupportedOperationException();
	}

	public Map<Integer, Etudiant> getReservistes() {
		throw new UnsupportedOperationException();
	}

	public void addReserviste(int key, Etudiant etudiant) {
		throw new UnsupportedOperationException();
	}

	public void removeReserviste(int key) {
		throw new UnsupportedOperationException();
	}

	public ArrayList<Zone> getZoneControlees() {
		throw new UnsupportedOperationException();
	}

	public void ajouterZoneControlee(Zone zone) {
		throw new UnsupportedOperationException();
	}

	public void enleverZoneControlee(Zone zone) {
		throw new UnsupportedOperationException();
	}
}