import java.util.List;
import java.util.Random;
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
		System.out.println(Couleurs.CLEAR);
		System.out.flush();

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

		try {
			this.gerer();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gère le déroulement de la partie.
	 * 
	 * @throws InterruptedException
	 */
	private void gerer() throws InterruptedException {
		// Nombre de zones à contrôler pour gagner la partie
		int zonesAControler = (int) Math.floor(this.zones.size() / 2) + 1;
		// On créé un pool de threads pour les zones
		ExecutorService executor = Executors.newFixedThreadPool(this.zones.size());

		for (Zone zone : this.zones) {
			executor.execute(zone);
		}

		while (this.joueur1.getZoneControlees().size() < zonesAControler
				&& this.joueur2.getZoneControlees().size() < zonesAControler) {

			Zone.getPartieLatch().await();
			Zone.resetPartieLatch();

			// Zones où on peut affecter des réservistes et redéployer des troupes
			List<Zone> zonesNonControlees = new ArrayList<Zone>(this.zones);
			zonesNonControlees.removeIf(zone -> zone.estControlee());

			// Actions pendant la trêve du joueur 1
			this.joueur1.affecterReservistes(zonesNonControlees, this.zones);
			this.joueur1.redeployerTroupes(zonesNonControlees, this.zones);

			// Actions pendant la trêve du joueur 2
			this.joueur2.affecterReservistes(zonesNonControlees, this.zones);
			this.joueur2.redeployerTroupes(zonesNonControlees, this.zones);

			Zone.finirTreve();
			Zone.getZoneLatch().countDown();
			Zone.resetZoneLatch();
		}

		Zone.getPartieLatch().await();

		System.out.println(
				"Le Joueur " + (this.joueur1.getZoneControlees().size() >= zonesAControler ? this.joueur1.getNom()
						: this.joueur2.getNom()) + " a gagné la partie !");

		executor.shutdown();
		Joueur.closeScanner();
	}

	/**
	 * @return le joueur 1
	 */
	public Joueur getJoueur1() {
		return this.joueur1;
	}

	/**
	 * @return le joueur 2
	 */
	public Joueur getJoueur2() {
		return this.joueur2;
	}
}