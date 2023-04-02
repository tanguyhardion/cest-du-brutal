package model.enums;

/**
 * Énumération des noms des différentes zones.
 * <p>
 * Chaque nom de zone correspond à une seule zone du jeu.
 *
 * @author Tanguy HARDION
 * @version 2.0
 */
public enum NomZone {
    /**
     * Le BDE.
     */
    BDE("BDE"),

    /**
     * La bibliothèque.
     */
    BIBLIOTHEQUE("Bibliothèque"),

    /**
     * La halle sportive.
     */
    HALLESPORTIVE("Halle Sportive"),

    /**
     * Les halles industrielles.
     */
    HALLESINDUSTRIELLES("Halles Industrielles"),

    /**
     * Le quartier administratif.
     */
    QUARTIERADMINISTRATIF("Quartier Administratif");

    private String nom;

    /**
     * Constructeur de l'énumération.
     *
     * @param nom le nom de la zone
     */
    NomZone(String nom) {
        this.nom = nom;
    }

    /**
     * @return le nom de la zone
     */
    public String getNom() {
        return this.nom;
    }
}
