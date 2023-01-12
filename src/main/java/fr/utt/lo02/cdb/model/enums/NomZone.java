package fr.utt.lo02.cdb.model.enums;

/**
 * Énumération des noms des différentes zones.
 * <p>
 * Chaque nom de zone correspond à une seule zone du jeu.
 *
 * @author Tanguy HARDION
 * @version 2.0
 */
public enum NomZone {
    BDE("BDE"),
    BIBLIOTHEQUE("Bibliothèque"),
    HALLESPORTIVE("Halle Sportive"),
    HALLESINDUSTRIELLES("Halles Industrielles"),
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
