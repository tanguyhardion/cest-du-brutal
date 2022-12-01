/**
 * Interface StrategieEtudiant, représentant une stratégie qui peut être
 * offensive ou défensive.
 * 
 */
public interface StrategieEtudiant  {

	public void agir(Etudiant origine, Etudiant cibleEquipeUne, Etudiant cibleEquipeDeux);

}