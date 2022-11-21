import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
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
	private Scanner scanner;
	private Map<Integer, Etudiant> troupes;
	private Map<Integer, Etudiant> reservistes;
	private List<Zone> zoneControlees = new ArrayList<Zone>();

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
		this.scanner = new Scanner(System.in);
		this.troupes = new HashMap<Integer, Etudiant>();
		this.reservistes = new HashMap<Integer, Etudiant>();
		this.zoneControlees = new ArrayList<Zone>();
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
		for (int i = 0; i < etudiants + etudiantsElite + maitresGobi; i++) {
			if (i < etudiants) {
				this.addEtudiant(i, new Etudiant());
			} else if (i < etudiants + etudiantsElite) {
				this.addEtudiant(i, new EtudiantElite());
			} else {
				this.addEtudiant(i, new MaitreGobi());
			}
		}
	}

	public void parametrerTroupes() {
		System.out.println();
		System.out.println(Couleurs.JAUNE + this.getNom() + ", vous avez " + this.points
				+ " points à attribuer aux différentes compétences de vos troupes :" + Couleurs.RESET + "\n");

		int n = 1;

		// Pour tous les étudiants du joueur
		for (Etudiant etudiant : this.getTroupes().values()) {

			// Affichage du nombre restant de points à attribuer,
			// si le joueur actuel n'est pas le premier
			if (n != 1 && this.points > 0) {
				System.out.println(
						Couleurs.BLEU + "\nIl vous reste " + this.points + " points à attribuer." + Couleurs.RESET);
			}

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
					int dexterite = Integer.valueOf(this.scanner.next());
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
					int force = Integer.valueOf(this.scanner.next());
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
					int resistance = Integer.valueOf(this.scanner.next());
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
					int constitution = Integer.valueOf(this.scanner.next());
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
					int initiative = Integer.valueOf(this.scanner.next());
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

			if (this.points < 0) {
				System.out.println(Couleurs.ROUGE + "Vous n'avez plus de points." + Couleurs.RESET);
			}
		}
	}

	public void choisirReservistes() {
		throw new UnsupportedOperationException();
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

	public void afficherTroupes(Map<Integer, Etudiant> troupes) {
		throw new UnsupportedOperationException();
	}

	public void afficherReservistes(Map<Integer, Etudiant> reservistes) {
		throw new UnsupportedOperationException();
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

	public List<Zone> getZoneControlees() {
		return this.zoneControlees;
	}

	public void addZoneControlee(Zone zone) {
		this.zoneControlees.add(zone);
	}

}