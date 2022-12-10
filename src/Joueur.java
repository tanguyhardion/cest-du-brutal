import java.util.Map;
import java.util.List;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Représente un joueur du jeu, possédant des troupes.
 * 
 * @author Tristan JAUSSAUD
 * @author Tanguy HARDION
 */
public class Joueur {

	private int points;
	private String nom;
	private Equipe equipe;
	private Map<Integer, Etudiant> troupes;
	private Map<Integer, Etudiant> reservistes;
	private ArrayList<Zone> zoneControlees;
	private static Scanner scanner;

	/**
	 * Constructeur de la classe Joueur.
	 * <p>
	 * Initialise les différents attributs de ce joueur.
	 * 
	 * @param nom le nom du joueur
	 */
	public Joueur(String nom, Equipe equipe) {
		this.nom = nom;
		this.equipe = equipe;
		this.points = 400;
		this.troupes = new Hashtable<Integer, Etudiant>();
		this.reservistes = new Hashtable<Integer, Etudiant>();
		this.zoneControlees = new ArrayList<Zone>();
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
	 * @param equipe         l'équipe à laquelle appartiennent les troupes créées
	 */
	public void initialiserTroupes(int etudiants, int etudiantsElite, int maitresGobi, Equipe equipe) {
		for (int i = 1; i <= etudiants + etudiantsElite + maitresGobi; i++) {
			if (i <= etudiants) {
				this.addEtudiant(i, new Etudiant(equipe));
			} else if (i <= etudiants + etudiantsElite) {
				this.addEtudiant(i, new EtudiantElite(equipe));
			} else {
				this.addEtudiant(i, new MaitreGobi(equipe));
			}
		}
	}

	/**
	 * Permet à ce joueur de paramétrer ses troupes, en leur distrubuant ses points
	 * et en leur attribuant une stratégie.
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
					int dexterite = Integer.parseInt(scanner.next());
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
					int force = Integer.parseInt(scanner.next());
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
					int resistance = Integer.parseInt(scanner.next());
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
					int constitution = Integer.parseInt(scanner.next());
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
					int initiative = Integer.parseInt(scanner.next());
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

		System.out.println("\n" + Couleurs.JAUNE + this.getNom() + ", choisissez vos réservistes.\n" + Couleurs.RESET);
		System.out.println("Pour afficher vos troupes, entrez " + Couleurs.BLEU + "t" + Couleurs.RESET + ".");
		System.out.println("Pour afficher vos réservistes, entrez " + Couleurs.BLEU + "r" + Couleurs.RESET + ".");
		System.out.println("Pour choisir un réserviste, entrez son numéro.");

		// Tant que le joueur n'a pas choisi 5 réservistes
		while (n <= 5) {
			try {
				String s = scanner.next().toLowerCase();

				if (s.equals("t")) {
					// Affichage des troupes
					this.afficherTroupes();
				} else if (s.equals("r")) {
					// Affichage des réservistes
					this.afficherReservistes();
				} else if (Integer.parseInt(s) > 0 && Integer.parseInt(s) <= nombreCombattants) {
					// Si la saisie est incorrecte, une exception sera levée à la ligne précédente
					int key = Integer.parseInt(s);
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

	/**
	 * Permet à ce joueur de répartir ses troupes sur les différentes zones du jeu.
	 * <p>
	 * Au moins un combattant doit être déployé sur chaque zone.
	 */
	public void repartirTroupes(List<Zone> zones) {
		// On sauvegarde le nombre initial de combattants du joueur
		int combattants = this.getTroupes().size() + this.getReservistes().size();
		// Et le nombre de zones, que l'on décrémentera
		int zonesRestantes = zones.size();

		System.out.println();
		System.out.println(Couleurs.JAUNE + this.getNom() + ", répartissez vos troupes.\n" + Couleurs.RESET);
		System.out.println("Pour afficher vos troupes, entrez " + Couleurs.BLEU + "t" + Couleurs.RESET + ".");
		System.out.println("Pour choisir un combattant, entrez son numéro.");
		System.out.println("Vous devez déployer au moins 1 combattant par zone.");
		System.out.println("Pour passer à la zone suivante, entrez " + Couleurs.BLEU + "suivant"
				+ Couleurs.RESET + ".");

		for (Zone zone : zones) {
			System.out.println("\n" + Couleurs.BLEU + zone.getNom() + " :" + Couleurs.RESET);

			// Tant qu'il reste des combattants à déployer et qu'il reste assez de
			// combattants au joueur pour en déployer au moins un sur chaque zone
			while (this.getTroupes().size() != 0 && this.getTroupes().size() >= zonesRestantes) {
				try {
					String s = scanner.next().toLowerCase();

					// Si le joueur entre "t", on affiche ses troupes
					if (s.equals("t")) {
						this.afficherTroupes();
					} else if (s.equals("suivant")) {
						// Si le joueur entre "suivant" mais qu'il n'a déployé aucun combattant
						if (zone.getTroupesEquipe1().isEmpty() && this.equipe == Equipe.UNE
								|| zone.getTroupesEquipe2().isEmpty() && this.equipe == Equipe.DEUX) {
							System.out.println(Couleurs.ROUGE + "Vous devez déployer au moins 1 combattant par zone."
									+ Couleurs.RESET);
						} else {
							// Si le joueur a déployé au moins un combattant, on passe à la zone suivante
							zonesRestantes--;
							break;
						}
					} else if (Integer.parseInt(s) <= combattants && Integer.parseInt(s) > 0) {
						int key = Integer.parseInt(s);
						Etudiant etudiant = this.getTroupes().get(key);
						// On ajoute le combattant choisi à la zone en cours
						zone.addCombattant(etudiant);
						// On enlève le combattant des troupes du joueur
						this.removeEtudiant(key);
						System.out.println(Couleurs.VERT + "Combattant ajouté." + Couleurs.RESET);
					} else {
						System.out.println(Couleurs.ROUGE + "Veuillez entrer un nombre entier entre 1 et "
								+ combattants + "." + Couleurs.RESET);
					}
				} catch (NumberFormatException e) {
					System.err.println(Couleurs.ROUGE + "Veuillez entrer un nombre entier valide." + Couleurs.RESET);
				} catch (IllegalArgumentException e) {
					System.err.println(Couleurs.ROUGE + "Ce combattant a déjà été déployé." + Couleurs.RESET);
				}
			}

			zonesRestantes--;
		}
	}

	/**
	 * Permet à ce joueur d'affecter ses réservistes sur les zones non contrôlées.
	 * 
	 * @param zonesAffectables la liste des zones non contrôlées
	 */
	public void affecterReservistes(List<Zone> zonesAffectables) {
		// On sauvegarde le nombre initial de réservsites du joueur
		int reservistes = this.getReservistes().size();

		// Si le joueur n'a aucun réserviste à affecter
		if (reservistes == 0) {
			System.out.println(Couleurs.ROUGE + "Vous n'avez aucun réserviste à affecter." + Couleurs.RESET);
			return;
		}

		System.out.println();
		System.out.println(Couleurs.JAUNE + this.getNom() + ", vous pouvez maintenant affecter vos réservistes."
				+ Couleurs.RESET);
		System.out.println();
		System.out.println("Pour afficher vos réservistes, entrez " + Couleurs.BLEU + "r" + Couleurs.RESET + ".");
		System.out.println("Pour choisir un réserviste, entrez son numéro.");
		System.out.println("Pour passer à la zone suivante, entrez " + Couleurs.BLEU + "suivant" + Couleurs.RESET
				+ ".");

		for (Zone zone : zonesAffectables) {
			System.out.println("\n" + Couleurs.BLEU + zone.getNom() + " :" + Couleurs.RESET);

			// Tant qu'il reste des réservistes à affecter
			while (!this.getReservistes().isEmpty()) {
				try {
					String s = scanner.next().toLowerCase();

					// Si le joueur entre "r", on affiche ses réservistes
					if (s.equals("r")) {
						this.afficherReservistes();
					} else if (s.equals("suivant")) {
						// Si le joueur entre "suivant", on passe à la zone suivante
						break;
					} else if (Integer.parseInt(s) <= reservistes && Integer.parseInt(s) > 0) {
						int key = Integer.parseInt(s);
						Etudiant etudiant = this.getReservistes().get(key);
						// On ajoute le réserviste choisi à la zone en cours
						zone.addCombattant(etudiant);
						// On enlève le réserviste des réservistes du joueur
						this.removeReserviste(key);
						System.out.println(Couleurs.VERT + "Réserviste ajouté." + Couleurs.RESET);
					} else {
						System.out.println(Couleurs.ROUGE + "Veuillez entrer un nombre entier entre 1 et "
								+ reservistes + "." + Couleurs.RESET);
					}
				} catch (NumberFormatException e) {
					System.err.println(Couleurs.ROUGE + "Veuillez entrer un nombre entier valide."
							+ Couleurs.RESET);
				} catch (IllegalArgumentException e) {
					System.err.println(Couleurs.ROUGE + "Ce réserviste a déjà été déployé." + Couleurs.RESET);
				}
			}

			// Si le joueur n'a plus de réservistes à affecter, on sort de la boucle
			if (this.getReservistes().isEmpty()) {
				System.out.println(
						"\n" + Couleurs.ROUGE + "Vous n'avez plus de réservistes à affecter." + Couleurs.RESET);
				break;
			}
		}
	}

	public void redeployerTroupes() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Affiche les troupes de ce joueur dans la console.
	 */
	public void afficherTroupes() {
		System.out.println("\nVos troupes :");
		this.troupes.forEach((key, etudiant) -> System.out.println(
				Couleurs.BLEU + "Combattant " + key + Couleurs.RESET + " " + etudiant.toString()));
	}

	/**
	 * Affiche les réservistes de ce joueur dans la console.
	 */
	public void afficherReservistes() {
		if (!reservistes.isEmpty()) {
			System.out.println("\nVos réservistes :");
			this.reservistes.forEach((key, reserviste) -> System.out.println(
					Couleurs.JAUNE + "Réserviste " + key + Couleurs.RESET + " " + reserviste.toString()));
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

	/**
	 * Ferme le scanner de la classe Joueur.
	 */
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
	public List<Zone> getZoneControlees() {
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