package fr.utt.lo02.cdb.model;

import fr.utt.lo02.cdb.model.enums.*;

/**
 * Représente un étudiant qui sera deployé sur une zone de combat.
 *
 * @author Tanguy HARDION
 * @version 1.0
 */
public class Etudiant {

    private final Equipe equipe;
    private int credits;
    private int dexterite;
    private int force;
    private int resistance;
    private int constitution;
    private int initiative;
    private int id;
    private boolean elimine;
    private boolean reserviste;
    private StrategieEtudiant strategie;
    private Zone zone;

    /**
     * Constructeur de la classe Etudiant.
     * <p>
     * Initialise les crédits de cet étudiant à 30.
     *
     * @param equipe l'équipe à laquelle appartient cet étudiant
     * @param id     l'id de l'étudiant
     */
    public Etudiant(Equipe equipe, int id) {
        this.credits = 30;
        this.equipe = equipe;
        this.id = id;
    }

    /**
     * Autre constructeur de la classe Etudiant.
     * <p>
     * Initialise les caractéristiques de cet étudiant avec les valeurs passées en paramètres.
     *
     * @param dexterite    la dextérité à attribuer
     * @param force        la force à attribuer
     * @param resistance   la résistance à attribuer
     * @param constitution la constitution à attribuer
     * @param initiative   l'initiative à attribuer
     * @param equipe       l'équipe à laquelle appartient cet étudiant
     */
    public Etudiant(int dexterite, int force, int resistance, int constitution, int initiative, Equipe equipe, int id) {
        this.credits = 30;
        this.dexterite = dexterite;
        this.force = force;
        this.resistance = resistance;
        this.constitution = constitution;
        this.initiative = initiative;
        this.equipe = equipe;
        this.id = id;
    }

    /**
     * Fait agir cet étudiant sur l'étudiant passe en paramètre.
     * <p>
     * Selon la stratégie de cet étudiant, il va attaquer un adversaire ou soigner un allié.
     *
     * @param cibleEquipeUne  l'étudiant de l'équipe 1 à attaquer ou soigner
     * @param cibleEquipeDeux l'étudiant de l'équipe 2 à attaquer ou soigner
     */
    public void agir(Etudiant cibleEquipeUne, Etudiant cibleEquipeDeux) {
        this.strategie.agir(this, cibleEquipeUne, cibleEquipeDeux);
    }

    /**
     * @return les crédits de cet étudiant
     */
    public int getCredits() {
        return this.credits;
    }

    /**
     * Retourne les crédits de cet étudiant ajoutés à sa constitution.
     *
     * @return les crédits totaux de cet étudiant
     */
    public int getCreditsTotal() {
        return this.credits + this.constitution;
    }

    /**
     * Ajoute des crédits à cet étudiant.
     * <p>
     * Un étudiant ne peut gagner des crédits qu'en se faisant soigner par un étudiant allié.
     * <p>
     * Ces crédits ne peuvent pas dépasser 100, afin que le combat ne se bloque pas si les étudiants avec une stratégie
     * offensive ne peuvent pas rivaliser contre les étudiants avec une stratégie défensive.
     *
     * @param credits le nombre de crédits à ajouter
     */
    public void addCredits(int credits) {
        this.credits += credits;
        if (this.credits > 100) {
            this.credits = 100;
        }
    }

    /**
     * Enlève le nombre de crédits spécifié à cet étudiant.
     * <p>
     * Un étudiant devient éliminé si ses credits ajoutés à sa constitution sont inférieurs ou égaux à 0.
     *
     * @param credits le nombre de crédits à enlever
     */
    public void removeCredits(int credits) {
        this.credits -= credits;
        if (this.getCreditsTotal() <= 0) {
            this.elimine = true;
        }
    }

    /**
     * @return la dextérité de cet étudiant
     */
    public int getDexterite() {
        return this.dexterite;
    }

    /**
     * Définit la dextérité de cet étudiant.
     *
     * @param dexterite la dextérité à affecter à cet étudiant
     * @throws IllegalArgumentException si la dextérité spécifiée est inférieure à 0 ou supérieure à 10
     */
    public void setDexterite(int dexterite) {
        if (dexterite < 0 || dexterite > 10) {
            throw new IllegalArgumentException("La dextérité doit être comprise entre 0 et 10.");
        }
        this.dexterite = dexterite;
    }

    /**
     * @return la force de cet étudiant
     */
    public int getForce() {
        return this.force;
    }

    /**
     * Définit la force de cet étudiant.
     *
     * @param force la force à affecter à cet étudiant
     * @throws IllegalArgumentException si la force spécifiée est inférieure à 0 ou supérieure à 10
     */
    public void setForce(int force) {
        if (force < 0 || force > 10) {
            throw new IllegalArgumentException("La force doit être comprise entre 0 et 10.");
        }
        this.force = force;
    }

    /**
     * @return la résistance de cet étudiant
     */
    public int getResistance() {
        return this.resistance;
    }

    /**
     * Définit la résistance de cet étudiant.
     *
     * @param resistance la résistance à affecter à cet étudiant
     * @throws IllegalArgumentException si la résistance spécifiée est inférieure à 0 ou supérieure à 10
     */
    public void setResistance(int resistance) {
        if (resistance < 0 || resistance > 10) {
            throw new IllegalArgumentException("La résistance doit être comprise entre 0 et 10.");
        }
        this.resistance = resistance;
    }

    /**
     * @return la constitution de cet étudiant
     */
    public int getConstitution() {
        return this.constitution;
    }

    /**
     * Définit la constitution de cet étudiant.
     *
     * @param constitution la constitution à affecter à cet étudiant
     * @throws IllegalArgumentException si la constitution spécifiée est inférieure à 0 ou supérieure à 30
     */
    public void setConstitution(int constitution) {
        if (constitution < 0 || constitution > 30) {
            throw new IllegalArgumentException("La constitution doit être comprise entre 0 et 30.");
        }
        this.constitution = constitution;
    }

    /**
     * @return l'initiative de cet étudiant
     */
    public int getInitiative() {
        return this.initiative;
    }

    /**
     * Définit l'initiative de cet étudiant.
     *
     * @param initiative l'initiative à affecter à cet étudiant
     * @throws IllegalArgumentException si l'initiative spécifiée est inférieure à 0 ou supérieure à 10
     */
    public void setInitiative(int initiative) {
        if (initiative < 0 || initiative > 10) {
            throw new IllegalArgumentException("L'initiative doit être comprise entre 0 et 10.");
        }
        this.initiative = initiative;
    }

    /**
     * @return l'index de cet étudiant
     */
    public int getId() {
        return this.id;
    }

    /**
     * Indique si cet étudiant est éliminé.
     *
     * @return {@code true} si l'étudiant est éliminé, {@code false} sinon
     */
    public boolean isElimine() {
        return this.elimine;
    }

    public boolean isReserviste() {
        return this.reserviste;
    }

    public void setReserviste(boolean reserviste) {
        this.reserviste = reserviste;
    }

    /**
     * @return l'équipe de cet étudiant
     */
    public Equipe getEquipe() {
        return this.equipe;
    }

    /**
     * @return la zone sur laquelle est cet étudiant
     * @throws IllegalStateException si cet étudiant n'est pas sur une zone
     */
    public Zone getZone() {
        if (this.zone == null) {
            throw new IllegalStateException("Cet étudiant n'est pas encore sur une zone.");
        }
        return this.zone;
    }

    /**
     * Définit la zone sur laquelle se trouve cet étudiant.
     */
    public void setZone(Zone zone) {
        this.zone = zone;
    }


    /**
     * @return la stratégie de cet étudiant
     */
    public StrategieEtudiant getStrategie() {
        return this.strategie;
    }

    /**
     * Définit la stratégie de cet étudiant.
     * <p>
     * La stratégie peut être offensive, défensive ou aléatoire.
     *
     * @param strategie la stratégie à affecter à cet étudiant
     * @throws IllegalArgumentException si la stratégie est nulle
     */
    public void setStrategie(StrategieEtudiant strategie) {
        if (strategie == null) {
            throw new IllegalArgumentException("Stratégie non reconnue.");
        }
        this.strategie = strategie;
    }

    /**
     * Renvoie une représentation textuelle de cet étudiant, composée de son type et de ses caractéristiques.
     *
     * @return une représentation textuelle de cet étudiant
     */
    @Override
    public String toString() {
        // Type de l'étudiant
        String type = "(" + this.getClass().getSimpleName() + ")";
        // Réserviste ou non
        String reserviste = this.isReserviste() ? " (R)" : "";

        return "Combattant " + this.id + " " + type + reserviste;
    }

}