import java.util.Map;
import java.util.Set;
import java.util.Hashtable;
import java.util.HashSet;
import java.util.Scanner;

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
	private Set<Zone> zoneControlees;
	private static Scanner scanner;

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
		this.troupes = new Hashtable<Integer, Etudiant>();
		this.reservistes = new Hashtable<Integer, Etudiant>();
		this.zoneControlees = new HashSet<Zone>();
		if (scanner == null) {
			scanner = new Scanner(System.in);
		}
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
		for (int i = 1; i <= etudiants + etudiantsElite + maitresGobi; i++) {
			if (i <= etudiants) {
				this.addEtudiant(i, new Etudiant());
			} else if (i <= etudiants + etudiantsElite) {
				this.addEtudiant(i, new EtudiantElite());
			} else {
				this.addEtudiant(i, new MaitreGobi());
			}
		}
	}

	/**
	 * Permet à ce joueur de paramétrer ses troupes, en leur distrubuant ses points
	 * et
	 * en leur attribuant une stratégie.
	 */
	public void parametrerTroupes() {
		System.out.println();
		System.out.println(Couleurs.JAUNE + this.getNom() + ", vous avez " + this.points
				+ " points à attribuer aux différentes compétences de vos troupes :" + Couleurs.RESET);

		// Compteur pour les combattants
		int n = 1;

		// Pour tous les étudiants du joueur
		for (Etudiant etudiant : this.getTroupes().values()) {

			// Affichage : "Étudiant n°..."
			System.out.print(Couleurs.BLEU + "\nCombattant " + n++);

			// Affichage du type de l'étudiant, s'il en a un
			if (etudiant instanceof MaitreGobi) {
				System.out.println(" (Maître du gobi) :" + Couleurs.RESET);
			} else if (etudiant instanceof EtudiantElite) {
				System.out.println(" (étudiant d'élite) :" + Couleurs.RESET);
			} else {
				System.out.println(" :" + Couleurs.RESET);
			}

			// Tant que l'utilisateur n'a pas définit la dexterité de l'étudiant
			while (this.points > 0) {
				try {
					// S'il reste des points à attribuer
					System.out.print("Dextérité : ");
					int dexterite = Integer.valueOf(scanner.next());
					// Si l'utilisateur a entré un nombre supérieur à son nombre de points restants
					if (dexterite > this.points) {
						System.out.println(Couleurs.ROUGE + "Vous n'avez pas assez de points. Points restants : "
								+ this.points + Couleurs.RESET);
					} else {
						etudiant.setDexterite(dexterite);
						this.points -= dexterite;
						break;
					}
				} catch (NumberFormatException e) {
					System.out.println(Couleurs.ROUGE + "Veuillez entrer un nombre entier." + Couleurs.RESET);
				} catch (IllegalArgumentException e) {
					System.err.println(Couleurs.ROUGE + e.getMessage() + Couleurs.RESET);
				}
			}

			// Tant que l'utilisateur n'a pas définit la force de l'étudiant
			while (this.points > 0) {
				try {
					System.out.print("Force : ");
					int force = Integer.valueOf(scanner.next());
					if (force > this.points) {
						System.out.println(Couleurs.ROUGE + "Vous n'avez pas assez de points. Points restants : "
								+ this.points + Couleurs.RESET);
					} else {
						etudiant.setForce(force);
						this.points -= force;
						break;
					}
				} catch (NumberFormatException e) {
					System.out.println(Couleurs.ROUGE + "Veuillez entrer un nombre entier." + Couleurs.RESET);
				} catch (IllegalArgumentException e) {
					System.err.println(Couleurs.ROUGE + e.getMessage() + Couleurs.RESET);
				}
			}

			// Tant que l'utilisateur n'a pas définit la résistance de l'étudiant
			while (this.points > 0) {
				try {
					System.out.print("Résistance : ");
					int resistance = Integer.valueOf(scanner.next());
					if (resistance > this.points) {
						System.out.println(Couleurs.ROUGE + "Vous n'avez pas assez de points. Points restants : "
								+ this.points + Couleurs.RESET);
					} else {
						etudiant.setResistance(resistance);
						this.points -= resistance;
						break;
					}
				} catch (NumberFormatException e) {
					System.out.println(Couleurs.ROUGE + "Veuillez entrer un nombre entier." + Couleurs.RESET);
				} catch (IllegalArgumentException e) {
					System.err.println(Couleurs.ROUGE + e.getMessage() + Couleurs.RESET);
				}
			}

			// Tant que l'utilisateur n'a pas définit la constitution de l'étudiant
			while (this.points > 0) {
				try {
					System.out.print("Constitution : ");
					int constitution = Integer.valueOf(scanner.next());
					if (constitution > this.points) {
						System.out.println(Couleurs.ROUGE + "Vous n'avez pas assez de points. Points restants : "
								+ this.points + Couleurs.RESET);
					} else {
						etudiant.setConstitution(constitution);
						this.points -= constitution;
						break;
					}
				} catch (NumberFormatException e) {
					System.out.println(Couleurs.ROUGE + "Veuillez entrer un nombre entier." + Couleurs.RESET);
				} catch (IllegalArgumentException e) {
					System.err.println(Couleurs.ROUGE + e.getMessage() + Couleurs.RESET);
				}
			}

			// Tant que l'utilisateur n'a pas définit l'initiative de l'étudiant
			while (this.points > 0) {
				try {
					System.out.print("Initiative : ");
					int initiative = Integer.valueOf(scanner.next());
					if (initiative > this.points) {
						System.out.println(Couleurs.ROUGE + "Vous n'avez pas assez de points. Points restants : "
								+ this.points + Couleurs.RESET);
					} else {
						etudiant.setInitiative(initiative);
						this.points -= initiative;
						break;
					}
				} catch (NumberFormatException e) {
					System.out.println(Couleurs.ROUGE + "Veuillez entrer un nombre entier." + Couleurs.RESET);
				} catch (IllegalArgumentException e) {
					System.err.println(Couleurs.ROUGE + e.getMessage() + Couleurs.RESET);
				}
			}

			// Tant que l'utilisateur n'a pas définit la stratégie de l'étudiant
			while (true) {
				try {
					System.out.print("Stratégie (Offensive : o, Défensive : d, Aléatoire : a) : ");
					String s = scanner.next();
					switch (s.toUpperCase()) {
						case "O":
							etudiant.setStrategie(new StrategieOffensive());
							break;
						case "D":
							etudiant.setStrategie(new StrategieDefensive());
							break;
						case "A":
							// etudiant.setStrategieAleatoire(true);
							// StrategieEtudiant strategie = new Random().nextBoolean() ? new
							// StrategieOffensive()
							// : new StrategieDefensive();
							etudiant.setStrategie(new StrategieAleatoire());
							break;
						default:
							etudiant.setStrategie(null);
					}
					break;
				} catch (IllegalArgumentException e) {
					System.err.println(Couleurs.ROUGE + e.getMessage() + Couleurs.RESET);
				}
			}

			// Affichage du nombre de points restants
			if (this.points == 1) {
				System.out.println("\n" + Couleurs.JAUNE + "Il vous reste " + this.points + " point à attribuer."
						+ Couleurs.RESET);
			} else if (this.points > 0) {
				System.out.println("\n" + Couleurs.JAUNE + "Il vous reste " + this.points + " points à attribuer."
						+ Couleurs.RESET);
			} else {
				System.out.println("\n" + Couleurs.ROUGE + "Vous n'avez plus de points." + Couleurs.RESET);
			}
		}
	}

	/**
	 * Permet à ce joueur de choisir ses réservistes, des combattants qui ne
	 * pourront seulement être déployés à partir de la première trêve.
	 */
	public void choisirReservistes() {
		// On sauvegarde le nombre initial de combattants du joueur
		final int nombreCombattants = this.getTroupes().size();
		// Compteur pour les réservistes
		int n = 1;

		System.out.println("\n" + Couleurs.JAUNE + this.getNom() + ", choisissez vos réservistes."
				+ Couleurs.RESET);
		System.out.println("Pour afficher vos troupes, entrez " + Couleurs.BLEU + "t" + Couleurs.RESET + ".");
		System.out.println("Pour afficher vos réservistes, entrez " + Couleurs.BLEU + "r" + Couleurs.RESET + ".");
		System.out.println("Pour choisir un réserviste, entrez son numéro.");

		// Tant que le joueur n'a pas choisi 5 réservistes
		while (n <= 5) {
			try {
				String s = scanner.next();

				if (s.equalsIgnoreCase("t")) {
					// Affichage des troupes
					this.afficherTroupes();
				} else if (s.equalsIgnoreCase("r")) {
					// Affichage des réservistes
					this.afficherReservistes();
				} else if (Integer.valueOf(s) > 0 && Integer.valueOf(s) <= nombreCombattants) {
					// Si la saisie est incorrecte, une exception sera levée à la ligne précédente
					int key = Integer.valueOf(s);
					// On récupère le combattant correspondant à la clé
					Etudiant etudiant = this.getTroupes().get(key);
					// On l'ajoute aux réservistes
					this.addReserviste(n, etudiant);
					// La ligne précédente n'a pas levé d'exception, on continue
					n++;
					// On enlève le combattant des troupes
					this.removeEtudiant(key);
					System.out.println(Couleurs.VERT + "Réserviste ajouté." + Couleurs.RESET);
				} else {
					System.out.println(Couleurs.ROUGE + "Veuillez entrer un nombre entier entre 1 et "
							+ nombreCombattants + "." + Couleurs.RESET);
				}
			} catch (NumberFormatException e) {
				System.err.println(Couleurs.ROUGE + "Veuillez entrer un nombre entier valide." + Couleurs.RESET);
			} catch (NullPointerException e) {
				System.err.println(Couleurs.ROUGE + "Ce combattant est déjà un réserviste." + Couleurs.RESET);
			} catch (IllegalArgumentException e) {
				System.err.println(Couleurs.ROUGE + e.getMessage() + Couleurs.RESET);
			}
		}
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

	public void afficherTroupes() {
		for (Map.Entry<Integer, Etudiant> entry : this.troupes.entrySet()) {
			Etudiant etudiant = entry.getValue();
			System.out.println(
					Couleurs.BLEU + "Combattant " + entry.getKey() + Couleurs.RESET + " " + etudiant.toString());
		}
	}

	public void afficherReservistes() {
		if (!reservistes.isEmpty()) {
			for (Map.Entry<Integer, Etudiant> entry : this.reservistes.entrySet()) {
				Etudiant reserviste = entry.getValue();
				System.out.println(
						Couleurs.JAUNE + "Réserviste " + entry.getKey() + Couleurs.RESET + " " + reserviste.toString());
			}
		} else {
			throw new IllegalArgumentException(Couleurs.ROUGE + "Vous n'avez pas de réservistes." + Couleurs.RESET);
		}
	}

	/**
	 * @return le nom de ce joueur
	 */
	public String getNom() {
		return this.nom;
	}

	public static void closeScanner() {
		scanner.close();
	}

	/**
	 * @return les troupes de ce joueur
	 */
	public Map<Integer, Etudiant> getTroupes() {
		return this.troupes;
	}

	/**
	 * Ajoute un étudiant à la liste des troupes de ce joueur.
	 * 
	 * @param key      la clé de l'étudiant à ajouter
	 * @param etudiant l'étudiant à ajouter
	 */
	public void addEtudiant(int key, Etudiant etudiant) {
		this.troupes.put(key, etudiant);
	}

	/**
	 * Supprime un étudiant de la liste des troupes de ce joueur.
	 * 
	 * @param key la clé de l'étudiant à supprimer
	 */
	public void removeEtudiant(int key) {
		this.troupes.remove(key);
	}

	/**
	 * @return les réservistes de ce joueur
	 */
	public Map<Integer, Etudiant> getReservistes() {
		return this.reservistes;
	}

	/**
	 * Ajoute un étudiant à la liste des réservistes de ce joueur.
	 * 
	 * @param key      la clé de l'étudiant à ajouter
	 * @param etudiant l'étudiant à ajouter
	 */
	public void addReserviste(int key, Etudiant etudiant) {
		this.reservistes.put(key, etudiant);
	}

	/**
	 * Supprime un étudiant de la liste des réservistes de ce joueur.
	 * 
	 * @param key la clé de l'étudiant à supprimer
	 */
	public void removeReserviste(int key) {
		this.reservistes.remove(key);
	}

	/**
	 * @return les zones contrôlées par ce joueur
	 */
	public Set<Zone> getZoneControlees() {
		return this.zoneControlees;
	}

	/**
	 * Ajoute une zone à la liste des zones contrôlées par ce joueur.
	 * 
	 * @param zone la zone à ajouter
	 */
	public void addZoneControlee(Zone zone) {
		this.zoneControlees.add(zone);
	}

}