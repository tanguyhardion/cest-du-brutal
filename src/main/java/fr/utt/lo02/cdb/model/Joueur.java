package fr.utt.lo02.cdb.model;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.EnumSet;
import java.util.Scanner;
import java.util.Observable;
import java.util.Observer;

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
     * Demande au joueur la filière à laquelle il appartient.
     *
     * @param filiereInterdite la filière déjà prise par l'autre joueur
     */
    public void demanderFiliere(Filiere filiereInterdite) {
        String nomJoueur = this.getEquipe() == Equipe.UNE ? "Joueur 1" : "Joueur 2";
        Set<Filiere> filieres = EnumSet.allOf(Filiere.class);
        System.out.println(nomJoueur + ", à quelle filière appartenez vous ? " + filieres.toString());

        // Tant que le joueur n'a pas défini sa filière.
        while (this.filiere == null) {
            try {
                System.out.print("Filière : ");
                Filiere filiere = Filiere.valueOf(scanner.next().toUpperCase());
                if (filiere == filiereInterdite) {
                    System.out.println("Cette filière n'est pas disponible.");
                } else {
                    this.filiere = filiere;
                    System.out.println(nomJoueur + ", vous appartenez à la filière " + this.filiere + "\n");
                    break;
                }
            } catch (IllegalArgumentException e) {
                System.err.println("Veuillez entrer une filière valide.");
            }
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
     * Permet à ce joueur de paramétrer ses troupes, en leur distrubuant ses points
     * et en leur attribuant une stratégie.
     */
    public void parametrerTroupes() {
        System.out.println();
        System.out.println("Joueur " + this.getFiliere() + ", vous avez " + this.points
                + " points à attribuer aux différentes compétences de vos troupes :");

        // Compteur pour les combattants
        int n = 1;

        // Pour tous les étudiants du joueur
        for (Etudiant etudiant : this.getTroupes()) {

            // Affichage : "Étudiant n°..."
            System.out.print("\nCombattant " + n++);

            // Affichage du type de l'étudiant, s'il en a un
            if (etudiant instanceof MaitreGobi) {
                System.out.println(" (Maître du gobi) :");
            } else if (etudiant instanceof EtudiantElite) {
                System.out.println(" (étudiant d'élite) :");
            } else {
                System.out.println(" :");
            }

            // Tant que l'utilisateur n'a pas défini la dexterité de l'étudiant
            while (this.points > 0) {
                try {
                    // S'il reste des points à attribuer
                    System.out.print("Dextérité : ");
                    int dexterite = Integer.parseInt(scanner.next());
                    // Si l'utilisateur a entré un nombre supérieur à son nombre de points restants
                    if (dexterite > this.points) {
                        System.out.println("Vous n'avez pas assez de points. Points restants : "
                                + this.points);
                    } else {
                        etudiant.setDexterite(dexterite);
                        this.points -= dexterite;
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Veuillez entrer un nombre entier.");
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
            }

            // Tant que l'utilisateur n'a pas défini la force de l'étudiant
            while (this.points > 0) {
                try {
                    System.out.print("Force : ");
                    int force = Integer.parseInt(scanner.next());
                    if (force > this.points) {
                        System.out.println("Vous n'avez pas assez de points. Points restants : "
                                + this.points);
                    } else {
                        etudiant.setForce(force);
                        this.points -= force;
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Veuillez entrer un nombre entier.");
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
            }

            // Tant que l'utilisateur n'a pas défini la résistance de l'étudiant
            while (this.points > 0) {
                try {
                    System.out.print("Résistance : ");
                    int resistance = Integer.parseInt(scanner.next());
                    if (resistance > this.points) {
                        System.out.println("Vous n'avez pas assez de points. Points restants : "
                                + this.points);
                    } else {
                        etudiant.setResistance(resistance);
                        this.points -= resistance;
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Veuillez entrer un nombre entier.");
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
            }

            // Tant que l'utilisateur n'a pas défini la constitution de l'étudiant
            while (this.points > 0) {
                try {
                    System.out.print("Constitution : ");
                    int constitution = Integer.parseInt(scanner.next());
                    if (constitution > this.points) {
                        System.out.println("Vous n'avez pas assez de points. Points restants : "
                                + this.points);
                    } else {
                        etudiant.setConstitution(constitution);
                        this.points -= constitution;
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Veuillez entrer un nombre entier.");
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
            }

            // Tant que l'utilisateur n'a pas défini l'initiative de l'étudiant
            while (this.points > 0) {
                try {
                    System.out.print("Initiative : ");
                    int initiative = Integer.parseInt(scanner.next());
                    if (initiative > this.points) {
                        System.out.println("Vous n'avez pas assez de points. Points restants : "
                                + this.points);
                    } else {
                        etudiant.setInitiative(initiative);
                        this.points -= initiative;
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Veuillez entrer un nombre entier.");
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
            }

            // Tant que l'utilisateur n'a pas défini la stratégie de l'étudiant
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

            // Affichage du nombre de points restants
            if (this.points == 1) {
                System.out.println("\nIl vous reste " + this.points + " point à attribuer.");
            } else if (this.points > 0) {
                System.out.println("\nIl vous reste " + this.points + " points à attribuer.");
            } else {
                System.out.println("\n" + "Vous n'avez plus de points.");
            }
        }
    }

    /**
     * Permet à ce joueur de choisir ses réservistes, des étudiants qui ne
     * pourront seulement être déployés à partir de la première trêve.
     */
    public void choisirReservistes() {
        // Compteur pour les réservistes
        int n = 1;

        System.out.println("\nJoueur " + this.getFiliere()
                + ", choisissez vos réservistes (les troupes qui n'iront pas tout de suite sur les zones de combat).\n");
        System.out.println("Pour afficher vos troupes, entrez t" + ".");
        System.out.println("Pour afficher vos réservistes, entrez r" + ".");
        System.out.println("Pour choisir un réserviste, entrez son numéro.");

        // Tant que le joueur n'a pas choisi 5 réservistes
        while (n <= 5) {
            try {
                String s = scanner.next().toLowerCase();

                if (Integer.parseInt(s) > 0) {
                    // Si la saisie est incorrecte, une exception sera levée à la ligne précédente
                    int id = Integer.parseInt(s);
                    // On récupère l'étudiant dont l'id est égal à la saisie
                    Etudiant etudiant = this.getEtudiant(id);
                    // On l'ajoute aux réservistes
                    this.addReserviste(etudiant);
                    // La ligne précédente n'a pas levé d'exception, on continue
                    n++;
                    // On enlève l'étudiant des troupes
                    this.removeEtudiant(id);
                    System.out.println("Réserviste ajouté.");
                } else {
                    System.out.println("Combattant incorrect.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Veuillez entrer un nombre entier valide.");
            } catch (NullPointerException e) {
                System.err.println("Ce combattant est déjà un réserviste.");
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Permet à ce joueur de répartir ses troupes sur les différentes zones du jeu.
     * <p>
     * Au moins un combattant doit être déployé sur chaque zone.
     *
     * @param zones La liste des zones du jeu
     */
    public void repartirTroupes(List<Zone> zones) {
        // Le nombre de zones, que l'on décrémentera
        int zonesRestantes = zones.size();

        System.out.println();
        System.out.println("Joueur " + this.getFiliere() + ", répartissez vos troupes.\n");
        System.out.println("Pour choisir un combattant, entrez son numéro.");
        System.out.println("Vous devez déployer au moins 1 combattant par zone.");
        System.out.println("Pour passer à la zone suivante, entrez s.");
        System.out.println("Vous pouvez à tout moment entrer aleatoire pour répartir vos troupes aléatoirement.");

        for (Zone zone : zones) {
            System.out.println("\n" + zone.getNom() + " :");

            // Tant qu'il reste des combattants à déployer et qu'il reste assez de
            // combattants au joueur pour en déployer au moins un sur chaque zone
            while (this.getTroupes().size() != 0 && this.getTroupes().size() >= zonesRestantes) {
                try {
                    String s = scanner.next().toLowerCase();

                    if (s.equals("s")) {
                        // Si le joueur entre "s" mais qu'il n'a déployé aucun combattant
                        if (zone.getTroupesEquipe1().isEmpty() && this.equipe == Equipe.UNE
                                || zone.getTroupesEquipe2().isEmpty() && this.equipe == Equipe.DEUX) {
                            System.out.println("Vous devez déployer au moins 1 combattant par zone."
                                   );
                        } else {
                            // Si le joueur a déployé au moins un combattant, on passe à la zone suivante
                            zonesRestantes--;
                            break;
                        }
                    } else if (s.equals("aleatoire")) {
                        this.repartirTroupesRestantes(zones);
                        return;
                    } else if (Integer.parseInt(s) > 0) {
                        int key = Integer.parseInt(s);
                        Etudiant etudiant = this.getTroupes().get(key);
                        // On ajoute le combattant choisi à la zone en cours
                        zone.addCombattant(etudiant);
                        // On enlève le combattant des troupes du joueur
                        this.removeEtudiant(key);
                        System.out.println("Combattant ajouté.");
                    } else {
                        System.out.println("Combattant invalide.");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Veuillez entrer un nombre entier valide.");
                } catch (IllegalArgumentException e) {
                    System.err.println("Ce combattant a déjà été déployé.");
                }
            }

            zonesRestantes--;
        }

        // S'il reste des combattants à déployer, on les répartit aléatoirement
        if (this.getTroupes().size() > 0) {
            this.repartirTroupesRestantes(zones);
        }
    }

    /**
     * Répartit aléatoirement les combattants restants du joueur
     * sur les zones du jeu.
     *
     * @param zones la liste des zones du jeu
     */
    private void repartirTroupesRestantes(List<Zone> zones) {
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
                this.removeEtudiant(index);
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
                    System.err.println("Veuillez entrer un nombre entier valide."
                           );
                } catch (IllegalArgumentException e) {
                    System.err.println("Ce réserviste a déjà été déployé.");
                }
            }

            // Si le joueur n'a plus de réservistes à affecter, on sort de la boucle
            if (this.getReservistes().isEmpty()) {
                System.out.println("\n" + "Vous n'avez plus de réservistes à affecter."
                       );
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
     * Supprime un étudiant de la liste des troupes de ce joueur.
     *
     * @param id la clé de l'étudiant à supprimer
     */
    public void removeEtudiant(int id) {
        this.troupes.removeIf(etudiant -> etudiant.getId() == id);
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