/**
 * Interface StrategieEtudiant, representant une strategie qui peut etre
 * offensive ou defensive.
 * 
 */
public interface StrategieEtudiant {

	public void agir(Etudiant origine, Etudiant cible);

}