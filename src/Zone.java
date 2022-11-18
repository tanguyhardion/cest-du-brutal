import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Zone {

	private boolean controlee;
	private NomZone nomZone;
	private Map<Integer, Etudiant> troupesJoueur1;
	private Map<Integer, Etudiant> troupesJoueur2;

	public Zone(NomZone nomZone) {
		this.nomZone = nomZone;
	}

	public void addCombattantJoueur1(int key, Etudiant etudiant) {
		this.troupesJoueur1.put(key, etudiant);
	}

	public void addCombattantJoueur2(int key, Etudiant etudiant) {
		this.troupesJoueur2.put(key, etudiant);
	}

	public Map<Integer, Etudiant> getTroupesJoueur1() {
		return troupesJoueur1;
	}

	public Map<Integer, Etudiant> getTroupesJoueur2() {
		return troupesJoueur2;
	}

	public List<Etudiant> getTroupesParInitiative() {
		List<Etudiant> troupes = new ArrayList<Etudiant>();
		troupes.addAll(this.troupesJoueur1.values());
		troupes.addAll(this.troupesJoueur2.values());
		troupes.sort(Comparator.comparingInt(Etudiant::getInitiative).reversed());
		return troupes;
	}

	public List<Etudiant> getTroupesJoueur1ParCredits() {
		List<Etudiant> troupes = new ArrayList<Etudiant>(this.troupesJoueur2.values());
		troupes.sort(Comparator.comparingDouble(Etudiant::getCreditsTotal));
		return troupes;
	}

	public List<Etudiant> getTroupesJoueur2ParCredits() {
		List<Etudiant> troupes = new ArrayList<Etudiant>(this.troupesJoueur2.values());
		troupes.sort(Comparator.comparingDouble(Etudiant::getCreditsTotal));
		return troupes;
	}

}