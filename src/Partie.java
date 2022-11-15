import java.util.Map;
import java.util.Scanner;

public class Partie {
	private static Partie instance;
	private Joueur joueur1;
	private Joueur joueur2;
	private int points;
	private Scanner scanner;

	private Partie() {
		this.scanner = new Scanner(System.in);
	}

	public static synchronized Partie getInstance() {
		if (instance == null) {
			instance = new Partie();
		}
		return instance;
	}

	public void commencer() {
		throw new UnsupportedOperationException();
	}

	private void initialiserTroupes(int etudiants, int etudiantsElite, int maitresGobi) {
		throw new UnsupportedOperationException();
	}

	private void parametrerTroupes(Joueur joueur) {
		throw new UnsupportedOperationException();
	}

	private void choisirReservistes(Joueur joueur) {
		throw new UnsupportedOperationException();
	}

	private void repartirTroupes(Joueur joueur) {
		throw new UnsupportedOperationException();
	}

	private void affecterReservistes(Joueur joueur) {
		throw new UnsupportedOperationException();
	}

	private void redeployerTroupes(Joueur joueur) {
		throw new UnsupportedOperationException();
	}

	private void afficherTroupes(Map<Integer, Etudiant> troupes) {
		throw new UnsupportedOperationException();
	}

	private void afficherReservistes(Map<Integer, Etudiant> reservistes) {
		throw new UnsupportedOperationException();
	}
}