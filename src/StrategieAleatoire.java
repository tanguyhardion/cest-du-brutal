import java.util.Random;

public class StrategieAleatoire implements StrategieEtudiant {

	@Override
	public void agir(Etudiant origine, Etudiant cibleEquipeUne, Etudiant cibleEquipeDeux) {
		if (new Random().nextBoolean()) {
			new StrategieOffensive().agir(origine, cibleEquipeUne, cibleEquipeDeux);
		} else {
			new StrategieDefensive().agir(origine, cibleEquipeUne, cibleEquipeDeux);
		}
	}

}