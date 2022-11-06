public class MaitreGobi extends Etudiant {

	public MaitreGobi() {
		super(2, 2, 2, 10, 2);
	}

	public MaitreGobi(int dexterite, int force, int resistance, int constitution, int initiative) {
		super(dexterite + 2, force + 2, resistance + 2, constitution + 10, initiative + 2);
	}

}