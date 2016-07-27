package modelsDataStructures;

public class DSTechGrandMaster {

	private String technology, arch1, arch2;
	
	public DSTechGrandMaster(String tech, String arch1, String arch2) {
		this.technology = tech; this.arch1 = arch1; this.arch2 = arch2;
	}
	
	public String getTechnology() { return this.technology; }
	public String getArchitecture1() { return this.arch1; }
	public String getArchitecture2() { return this.arch2; }
}
