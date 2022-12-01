import java.util.Random;

public class StrategieAleatoire implements StrategieEtudiant {

	@Override
	public void agir(Etudiant origine, Etudiant cibleEquipeUne, Etudiant cibleEquipeDeux) {
		if (new Random().nextBoolean()) {
			StrategieOffensive offensive = new StrategieOffensive();
			offensive.attaquer(origine, origine.getEquipe() == Equipe.UNE ? cibleEquipeDeux : cibleEquipeUne);
		} else {
			StrategieDefensive defensive = new StrategieDefensive();
			defensive.soigner(origine, origine.getEquipe() == Equipe.UNE ? cibleEquipeUne : cibleEquipeDeux);
		}
	}

}