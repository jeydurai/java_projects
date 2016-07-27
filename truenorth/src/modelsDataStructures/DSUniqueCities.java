package modelsDataStructures;

public class DSUniqueCities {

	private String salesAgent, billToSiteCity, shipToCity, uniqueCity, uniqueState, uniqueCountry;
	
	public DSUniqueCities(String salesAgent, String billToSiteCity, String shipToCity, String uniqueCity, 
			String uniqueState, String uniqueCountry) {
	
		this.salesAgent = salesAgent; this.billToSiteCity = billToSiteCity; this.shipToCity = shipToCity;
		this.uniqueCity = uniqueCity; this.uniqueState = uniqueState; this.uniqueCountry = uniqueCountry;
	}
	
	public String getSalesAgent() {return this.salesAgent; }
	public String getBillToSiteCity() {return this.billToSiteCity; }
	public String getShipToCity() {return this.shipToCity; }
	public String getUniqueCity() {return this.uniqueCity; }
	public String getUniqueState() {return this.uniqueState; }
	public String getUniqueCountry() {return this.uniqueCountry; }
}
