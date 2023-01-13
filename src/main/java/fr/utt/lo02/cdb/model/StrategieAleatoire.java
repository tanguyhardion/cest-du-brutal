package fr.utt.lo02.cdb.model;

import java.util.Random;

/**
 * Représente l'action à réaliser d'un étudiant dont la stratégie est aléatoire.
 *
 * @author Tristan JAUSSAUD
 * @version 1.2
 */
public class StrategieAleatoire implements StrategieEtudiant {

    @Override
    public void agir(Etudiant origine, Etudiant cibleEquipeUne, Etudiant cibleEquipeDeux) {
        if (new Random().nextBoolean()) {
            new StrategieOffensive().agir(origine, cibleEquipeUne, cibleEquipeDeux);
        } else {
            new StrategieDefensive().agir(origine, cibleEquipeUne, cibleEquipeDeux);
        }
    }

    /**
     * @return la représentation textuelle de la stratégie aléatoire
     */
    @Override
    public String toString() {
        return "Aléatoire";
    }

}