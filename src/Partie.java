import java.util.Scanner;

public class Partie {
	private static Partie instance;
	private Joueur joueur1;
	private Joueur joueur2;
	private int points;
	private Scanner scanner;

	private Partie() {
		throw new UnsupportedOperationException();
	}

	public static synchronized Partie getInstance() {
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

	private void afficherTroupes(Joueur joueur) {
		throw new UnsupportedOperationException();
	}

	public void afficherReservistes(Joueur joueur) {
		throw new UnsupportedOperationException();
	}

	public void affecterReservistes(Joueur joueur) {
		throw new UnsupportedOperationException();
	}

	public void redeployerCombattants(Joueur joueur) {
		throw new UnsupportedOperationException();
	}
}