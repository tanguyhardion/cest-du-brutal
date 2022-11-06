public class EtudiantElite extends Etudiant {

	public EtudiantElite() {
		super(1, 1, 1, 5, 1);
	}

	public EtudiantElite(int dexterite, int force, int resistance, int constitution, int initiative) {
		super(dexterite + 1, force + 1, resistance + 1, constitution + 5, initiative + 1);
	}

}