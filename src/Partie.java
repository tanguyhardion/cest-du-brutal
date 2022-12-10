import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

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
		this.joueur1 = new Joueur("Joueur 1", Equipe.UNE);
		this.joueur2 = new Joueur("Joueur 2", Equipe.DEUX);
		this.zones = new ArrayList<Zone>();

		// on récupère tous les noms de zones
		EnumSet<NomZone> zones = EnumSet.allOf(NomZone.class);
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
		Scanner sc = new Scanner(System.in);
		// boucles pour les tests
		final Random random = new Random();
		for (int i = 1; i <= 15; i++) {
			StrategieEtudiant strategie1 = random.nextBoolean() ? new StrategieDefensive()
					: new StrategieOffensive();
			Etudiant etudiant1 = new Etudiant(random.nextInt(10), random.nextInt(10), random.nextInt(10),
					random.nextInt(10), random.nextInt(10), Equipe.UNE);
			etudiant1.setStrategie(strategie1);
			this.joueur1.addEtudiant(i, etudiant1);

			StrategieEtudiant strategie2 = random.nextBoolean() ? new StrategieDefensive()
					: new StrategieOffensive();
			Etudiant etudiant2 = new Etudiant(random.nextInt(10), random.nextInt(10), random.nextInt(10),
					random.nextInt(10), random.nextInt(10), Equipe.DEUX);
			etudiant2.setStrategie(strategie2);
			this.joueur2.addEtudiant(i, etudiant2);
		}
		for (int i = 1; i <= 5; i++) {
			this.joueur1.addReserviste(i, this.joueur1.getTroupes().get(i));
			this.joueur2.addReserviste(i, this.joueur2.getTroupes().get(i));
		}
		int n = 1;
		for (Zone zone : this.zones) {
			for (int i = 0; i < 3; i++) {
				zone.addCombattant(this.joueur1.getTroupes().get(n));
				zone.addCombattant(this.joueur2.getTroupes().get(n++));
			}
		}

		// joueur1.initialiserTroupes(15, 4, 1, Equipe.UNE);
		// joueur1.parametrerTroupes();
		// joueur1.choisirReservistes();
		// joueur1.repartirTroupes(this.zones);

		// joueur2.initialiserTroupes(15, 4, 1, Equipe.DEUX);
		// joueur2.parametrerTroupes();
		// joueur2.choisirReservistes();
		// joueur2.repartirTroupes(this.zones);

		int zonesAControler = (int) Math.floor(this.zones.size() / 2) + 1;
		ExecutorService executor = Executors.newCachedThreadPool();

		for (Zone zone : this.zones) {
			executor.execute(zone);
		}

		while (this.joueur1.getZoneControlees().size() < zonesAControler
				&& this.joueur2.getZoneControlees().size() < zonesAControler) {
			try {
				Zone.getPartieLatch().await();
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
			Zone.resetPartieLatch();

			// Zones sur lesquelles on peut affecter des réservistes
			List<Zone> zonesAffectables = new ArrayList<Zone>(this.zones);
			zonesAffectables.removeAll(this.joueur1.getZoneControlees());
			zonesAffectables.removeAll(this.joueur2.getZoneControlees());

			this.joueur1.affecterReservistes(zonesAffectables);
			this.joueur2.affecterReservistes(zonesAffectables);

			Zone.finirTreve();
			Zone.getZoneLatch().countDown();
			Zone.resetZoneLatch();
		}

		System.out.println(
				"Le Joueur " + (this.joueur1.getZoneControlees().size() >= zonesAControler ? this.joueur1.getNom()
						: this.joueur2.getNom()) + " a gagné la partie !");

		executor.shutdown();
		sc.close();
		Joueur.closeScanner();
	}

	public Joueur getJoueur1() {
		return this.joueur1;
	}

	public Joueur getJoueur2() {
		return this.joueur2;
	}
}