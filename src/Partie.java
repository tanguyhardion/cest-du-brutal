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
		this.initialiserTroupes(15, 4, 1);
	}

	private void initialiserTroupes(int etudiants, int etudiantsElite, int maitresGobi) {
		for (int i = 0; i < etudiants + etudiantsElite + maitresGobi; i++) {
			if (i < etudiants) {
				this.joueur1.addEtudiant(i, new Etudiant());
				this.joueur2.addEtudiant(i, new Etudiant());
			} else if (i < etudiants + etudiantsElite) {
				this.joueur1.addEtudiant(i, new EtudiantElite());
				this.joueur2.addEtudiant(i, new EtudiantElite());
			} else {
				this.joueur1.addEtudiant(i, new MaitreGobi());
				this.joueur2.addEtudiant(i, new MaitreGobi());
			}
		}
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