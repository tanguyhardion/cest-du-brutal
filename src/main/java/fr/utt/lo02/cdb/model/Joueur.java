package fr.utt.lo02.cdb.model;

import java.util.*;

/**
 * Représente un joueur du jeu, possédant des troupes.
 *
 * @author Tristan JAUSSAUD
 * @author Tanguy HARDION
 */
public class Joueur extends Observable {

    private static Scanner scanner;
    private int points;
    private Equipe equipe;
    private Filiere filiere;
    private List<Etudiant> troupes;
    private List<Etudiant> reservistes;
    private List<Zone> zonesControlees;

    /**
     * Constructeur de la classe Joueur.
     * <p>
     * Initialise les différents attributs de ce joueur.
     *
     * @param equipe l'équipe du joueur
     */
    public Joueur(Equipe equipe) {
        this.equipe = equipe;
        this.points = 400;
        this.troupes = new ArrayList<>();
        this.reservistes = new ArrayList<>();
        this.zonesControlees = new ArrayList<>();
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
                this.addEtudiant(new Etudiant(equipe, i));
            } else if (i <= etudiants + etudiantsElite) {
                this.addEtudiant(new EtudiantElite(equipe, i));
            } else {
                this.addEtudiant(new MaitreGobi(equipe, i));
            }
        }
    }

    /**
     * Répartit aléatoirement les combattants restants du joueur
     * sur les zones du jeu.
     *
     * @param zones la liste des zones du jeu
     */
    public void repartirTroupesAleatoirement(List<Zone> zones) {
        // Tant qu'il reste des combattants à déployer
        while (this.getTroupes().size() > 0) {
            // On récupère les troupes restantes du joueur
            List<Etudiant> troupes = new ArrayList<>(this.troupes);
            Random r = new Random();
            for (Zone zone : zones) {
                // Si le joueur n'a plus de combattants à déployer, on sort du while
                if (this.getTroupes().isEmpty()) {
                    break;
                }
                // Sinon, on choisit un combattant au hasard parmi ceux restants
                int index = r.nextInt(troupes.size());
                // On récupère le combattant
                Etudiant etudiant = this.getTroupes().get(index);
                // On ajoute le combattant choisi à la zone en cours
                zone.addCombattant(etudiant);
                // On enlève le combattant des troupes du joueur
                this.removeEtudiant(etudiant);
                // On enlève le combattant des troupes restantes
                troupes.remove(index);
            }
        }
    }

    /**
     * Permet à ce joueur d'affecter ses réservistes sur les zones non contrôlées.
     *
     * @param zonesNonControlees la liste des zones non contrôlées
     * @param zones              la liste de toutes les zones
     */
    public void affecterReservistes(List<Zone> zonesNonControlees, List<Zone> zones) {
        // Si le joueur n'a aucun réserviste à affecter
        if (this.reservistes.size() == 0) {
            System.out.println("Joueur " + this.getFiliere()
                    + ", vous n'avez aucun réserviste à affecter.");
            return;
        }

        System.out.println();
        System.out.println("Joueur " + this.getFiliere()
                + ", vous pouvez maintenant affecter vos réservistes.");
        System.out.println();
        System.out.println("Pour choisir un réserviste, entrez son numéro.");
        System.out.println("Pour passer à la zone suivante, entrez s.");

        for (Zone zone : zonesNonControlees) {
            System.out.println("\n" + zone.getNom() + " :");

            // Tant qu'il reste des réservistes à affecter
            while (!this.getReservistes().isEmpty()) {
                try {
                    String s = scanner.next().toLowerCase();

                    if (s.equals("s")) {
                        // Si le joueur entre "s", on passe à la zone suivante
                        break;
                    } else if (Integer.parseInt(s) > 0) {
                        int key = Integer.parseInt(s);
                        Etudiant etudiant = this.getReservistes().get(key);
                        // On ajoute le réserviste choisi à la zone en cours correspondante
                        zones.get(zones.indexOf(zone)).addCombattant(etudiant);
                        // On enlève le réserviste des réservistes du joueur
                        this.removeReserviste(key);
                        System.out.println("Réserviste affecté.");
                    } else {
                        System.out.println("Réserviste invalide.");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Veuillez entrer un nombre entier valide.");
                } catch (IllegalArgumentException e) {
                    System.err.println("Ce réserviste a déjà été déployé.");
                }
            }

            // Si le joueur n'a plus de réservistes à affecter, on sort de la boucle
            if (this.getReservistes().isEmpty()) {
                System.out.println("\n" + "Vous n'avez plus de réservistes à affecter.");
                break;
            }
        }
    }

    /**
     * Permet à ce joueur de redéployer ses troupes valides des zones qu'il contrôle
     * sur les zones qui ne sont pas encore contrôlées.
     * <p>
     * Au moins un combattant doit rester dans chaque zone contrôlée.
     *
     * @param zonesNonControlees la liste des zones non contrôlées
     * @param zones              la liste de toutes les zones
     */
    public void redeployerTroupes(List<Zone> zonesNonControlees, List<Zone> zones) {
        // Zones contrôlées par le joueur qui ont au moins 2 combattants
        List<Zone> zonesControlees = new ArrayList<>(this.zonesControlees);
        zonesControlees.removeIf(zone -> zone.getTroupes(this).size() <= 1);

        // Si le joueur n'a aucune zone contrôlée avec au moins 2 combattants
        if (zonesControlees.isEmpty()) {
            System.out.println("Joueur " + this.getFiliere()
                    + ", vous n'avez aucun combattant à redéployer.");
            return;
        }

        System.out.println();
        System.out.println("Joueur " + this.getFiliere()
                + ", vous pouvez maintenant redéployer vos troupes.");
        System.out.println();
        System.out.println("Pour afficher vos troupes, entrez t" + ".");
        System.out.println("Pour choisir un combattant, entrez son numéro.");
        System.out.println("Pour passer à la zone suivante, entrez s" + ".");

        // Pour toutes les zones contrôlées par le joueur qui ont au moins 2 combattants
        for (Zone zoneC : zonesControlees) {
            // On affiche le nom de la zone en cours contrôlée
            System.out.println("\n" + zoneC.getNom() + " (contrôlée)" + " :");
            // Pour toutes les zones non contrôlées, sur lesquelles le joueur peut
            // redéployer
            for (Zone zoneNC : zonesNonControlees) {
                // Tant que le joueur a des combattants à redéployer
                while (zoneC.getTroupes(this).size() > 1) {
                    // On affiche le nom de la zone en cours non contrôlée
                    System.out.println("\n" + zoneNC.getNom() + " :");

                    try {
                        String s = scanner.next().toLowerCase();
                        if (s.equals("s")) {
                            // Si le joueur entre "s", on passe à la zone suivante
                            break;
                        } else if (Integer.parseInt(s) > 0) {
                            int key = Integer.parseInt(s);
                            Etudiant etudiant = zoneC.getTroupes(this).get(key);
                            // On propose au joueur d'attribuer une nouvelle stratégie au combattant
                            this.attribuerNouvelleStrategie(etudiant);
                            // On ajoute le combattant à la zone non contrôlée correspondante
                            zones.get(zones.indexOf(zoneNC)).addCombattant(etudiant);
                            // On retire le combattant de la zone contrôlée
                            this.zonesControlees.get(this.zonesControlees.indexOf(zoneC)).removeCombattant(key,
                                    etudiant);
                            System.out.println("Combattant redeployé");
                        } else {
                            System.out.println("Combattant invalide.");
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Veuillez entrer un nombre entier valide."
                        );
                    } catch (NullPointerException e) {
                        System.err.println("Combattant invalide.");
                    }
                }
            }
            // Si le joueur n'a plus de combattants à redéployer
            if (zoneC.getTroupes(this).size() <= 1) {
                System.out.println(
                        "\n" + "Vous n'avez plus de combattants à redéployer.");
                // On sort de la boucle for
                break;
            }
        }
    }

    /**
     * Paramètre aléatoirement les troupes de ce joueur, en leur attribuant une
     * valeur aléatoire pour chacune de leur caractéristiques.
     * <p>
     * Cette méthode choisit aussi 5 réservistes aléatoirement parmi les étudiants.
     */
    public void parametrerTroupesAleatoirement() {
        // List dans laquelle on va choisir une stratégie aléatoirement
        List<StrategieEtudiant> strategies = new ArrayList<>();
        strategies.add(new StrategieAleatoire());
        strategies.add(new StrategieOffensive());
        strategies.add(new StrategieDefensive());

        final Random random = new Random();

        // Pour toutes les troupes de ce joueur
        for (Etudiant etudiant : this.getTroupes()) {
            // On choisit une stratégie aléatoirement (0, 1 ou 2)
            etudiant.setStrategie(strategies.get(random.nextInt(3)));

            // Pour chaque caractéristique, on attribue une valeur aléatoire entre 0 et 8.
            // De cette manière, en moyenne, la somme des valeurs sera égale à 400.
            // Sachant qu'on choisit un nombre aléatoire entre 0 et 8 : valeurMoyenne = 4
            // donc 20 combattants * 5 caractéristiques * valeurMoyenne = 400 points
            etudiant.setDexterite(random.nextInt(9));
            etudiant.setForce(random.nextInt(9));
            etudiant.setResistance(random.nextInt(9));
            etudiant.setConstitution(random.nextInt(9));
            etudiant.setInitiative(random.nextInt(9));
        }

        // Pour les réservistes restant à choisir, on les choisit aléatoirement
        int reservistes = this.getTroupes().size() - 15;
        for (int i = 0; i < reservistes; i++) {
            Etudiant etudiant = this.troupes.get(random.nextInt(this.troupes.size()));
            etudiant.setReserviste(true);
            this.addReserviste(etudiant);
            this.removeEtudiant(etudiant);
        }

        // On notifie les observateurs que les troupes ont été paramétrées
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Permet au joueur d'attribuer une nouvelle stratégie à un étudiant
     * lors du redéploiement.
     *
     * @param etudiant l'étudiant à qui on attribue une nouvelle stratégie
     */
    public void attribuerNouvelleStrategie(Etudiant etudiant) {
        System.out.println("L'étudiant en question est : " + etudiant.toString());
        System.out.println("Voulez vous attribuer lui une nouvelle stratégie (oui/non) :");

        while (true) {
            String result = scanner.next().toLowerCase();
            if (result.equals("non")) {
                return;
            } else if (result.equals("oui")) {
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
                        System.err.println(e.getMessage());
                    }
                }
                break;
            } else {
                System.out.println("Saisie non reconnue.");
            }
        }

    }

    public int getPoints() {
        return this.points;
    }

    /**
     * @return l'équipe de ce joueur
     */
    public Equipe getEquipe() {
        return this.equipe;
    }

    /**
     * @return la filière de ce joueur
     */
    public Filiere getFiliere() {
        return this.filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    /**
     * @return les troupes de ce joueur
     */
    public List<Etudiant> getTroupes() {
        return this.troupes;
    }

    public Etudiant getEtudiant(int id) {
        return this.troupes.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    /**
     * Ajoute un étudiant à la liste des troupes de ce joueur.
     *
     * @param etudiant l'étudiant à ajouter
     */
    public void addEtudiant(Etudiant etudiant) {
        this.troupes.add(etudiant);
    }

    /**
     * Supprime un étudiant de la liste des troupes de ce joueur
     * et notifie les observateurs.
     *
     * @param etudiant l'étudiant à supprimer
     */
    public void removeEtudiant(Etudiant etudiant) {
        this.troupes.remove(etudiant);
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * @return les réservistes de ce joueur
     */
    public List<Etudiant> getReservistes() {
        return this.reservistes;
    }

    /**
     * Ajoute un étudiant à la liste des réservistes de ce joueur.
     *
     * @param etudiant l'étudiant à ajouter
     */
    public void addReserviste(Etudiant etudiant) {
        this.reservistes.add(etudiant);
    }

    /**
     * Supprime un étudiant de la liste des réservistes de ce joueur.
     *
     * @param id l'index de l'étudiant à supprimer
     */
    public void removeReserviste(int id) {
        this.reservistes.removeIf(etudiant -> etudiant.getId() == id);
    }

    /**
     * @return les zones contrôlées par ce joueur
     */
    public List<Zone> getZonesControlees() {
        return this.zonesControlees;
    }

    /**
     * Ajoute une zone à la liste des zones contrôlées par ce joueur.
     *
     * @param zone la zone à ajouter
     */
    public void addZoneControlee(Zone zone) {
        this.zonesControlees.add(zone);
    }

    @Override
    public String toString() {
        return "Joueur " + this.filiere;
    }

}