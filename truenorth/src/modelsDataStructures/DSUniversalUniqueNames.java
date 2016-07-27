package modelsDataStructures;

public class DSUniversalUniqueNames {

	private String uniqueName, vertical;
	
	public DSUniversalUniqueNames(String uniqueName, String industryVertical) {
		this.uniqueName = uniqueName; this.vertical = industryVertical;
	}
	
	public String getUniqueName() { return this.uniqueName; }
	public String getIndustryVertical() { return this.vertical; }
}
