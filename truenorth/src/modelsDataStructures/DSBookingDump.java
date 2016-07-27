package modelsDataStructures;

public class DSBookingDump {

	private int fpYear, fpMonth, fpWeek;
	private double bookingNet, baseList;
	private String atAttach, accountName, customerName, erpDealID, salesOrderNumberDetail, fpQuarter,
	partner, partnerName, tbm, region, salesLevel6, scms, subSCMS, tmsLevel1SalesAllocated, techName,
	techCode, technologyGroup, partnerTierCode, shipToCity, tmsBookingsQuantity, beName, subBEName, 
	tamMatch, agMatch, agParMatch, agMatch2, arch1, arch2, productID, mappedID5, mappedName5, mappedType5, 
	vsTeamNode, billToSiteCity, vertical, fy15TAM, iotPortfolio, gtmu, partnerCertification, partnerType, 
	productFamily, bookingAdjustment, mappedSalesLevel6, mappedSubSCMS, mappedRegion, mappedGTMu, mappedID4, 
	mappedName4, mappedType4, mappedID3, mappedName3, mappedType3, mappedID2, mappedName2, mappedType2, 
	mappedID1, mappedName1, mappedType1, mappedID0, mappedName0, mappedType0, uniqueCity, uniqueState, 
	uniqueCountry, prodSer;
	
	public DSBookingDump(int fpYear, int fpMonth, int fpWeek, double bookingNet, double baseList,
			String atAttach, String accountName, String customerName, String erpDealID, String salesOrderNumberDetail, String fpQuarter,
			String partner, String partnerName, String tbm, String region, String salesLevel6, String scms, String subSCMS, 
			String tmsLevel1SalesAllocated, String techName, String techCode, String technologyGroup, String partnerTierCode, String shipToCity, 
			String tmsBookingsQuantity, String beName, String subBEName, String tamMatch, String agMatch, String agParMatch, String agMatch2, 
			String arch1, String arch2, String productID, String mappedID5, String mappedName5, String mappedType5, String vsTeamNode, 
			String billToSiteCity, String vertical, String fy15TAM, String iotPortfolio, String gtmu, String partnerCertification, 
			String partnerType, String productFamily, String bookingAdjustment, String mappedSalesLevel6, String mappedSubSCMS, String mappedRegion, 
			String mappedGTMu, String mappedID4, String mappedName4, String mappedType4, String mappedID3, String mappedName3, String mappedType3, 
			String mappedID2, String mappedName2, String mappedType2, String mappedID1, String mappedName1, String mappedType1, 
			String mappedID0, String mappedName0, String mappedType0, String uniqueCity, String uniqueState, String uniqueCountry, String prodSer) {
		setFPYear(fpYear); setFPMonth(fpMonth); setFPWeek(fpWeek); setBookingNet(bookingNet); setBaseList(baseList); setATAttach(atAttach); 
		setAccountName(accountName); setCustomerName(customerName); setERPDealID(erpDealID); setSalesOrderNumberDetail(salesOrderNumberDetail); 
		setFPQuarter(fpQuarter); setPartner(partner); setPartnerName(partnerName); setSalesAgent(tbm); setRegion(region);
		setSL6(salesLevel6); setSCMS(scms); setSubSCMS(subSCMS); setTMS1(tmsLevel1SalesAllocated); setTechName(techName); setTechCode(techCode); 
		setTechnologyGroup(technologyGroup); setPartnerTierCode(partnerTierCode); setShipToCity(shipToCity); setBookingsQuantity(tmsBookingsQuantity); 
		setBEName(beName); setSubBEName(subBEName); setTAMMatch(tamMatch); setAGMatch(agMatch); setAGPartnerMatch(agParMatch); setAGMatch2(agMatch2);
		setArch1(arch1); setArch2(arch2); setProductID(productID); setMappedID5(mappedID5); setMappedName5(mappedName5); setMappedType5(mappedType5); 
		setVSTeamNode(vsTeamNode); setBillToSiteCity(billToSiteCity); setVertical(vertical); setFY15TAM(fy15TAM); setIOTPortfolio(iotPortfolio); 
		setGTMu(gtmu); setPartnerCertification(partnerCertification); setPartnerType(partnerType); setProductFamily(productFamily);
		setBookingAdjustment(bookingAdjustment); setMappedSL6(mappedSalesLevel6); setMappedSubSCMS(mappedSubSCMS); setMappedRegion(mappedRegion); 
		setMappedGTMu(mappedGTMu); setMappedID4(mappedID4); setMappedName4(mappedName4); setMappedType4(mappedType4); setMappedID3(mappedID3); 
		setMappedName3(mappedName3); setMappedType3(mappedType3); setMappedID2(mappedID2); setMappedName2(mappedName2); setMappedType2(mappedType2);
		setMappedID1(mappedID1); setMappedName1(mappedName1); setMappedType1(mappedType1); setMappedID0(mappedID0); setMappedName0(mappedName0); 
		setMappedType0(mappedType0); setUniqueCity(uniqueCity); setUniqueState(uniqueState); setUniqueCountry(uniqueCountry); setProductService(prodSer);
	}
	
	public void setFPYear(int num) { fpYear = num; }
	public void setFPMonth(int num) { fpMonth = num; }
	public void setFPWeek(int num) { fpWeek = num; }
	public void setBookingNet(double num) { bookingNet = num; }
	public void setBaseList(double num) { baseList = num; }
	public void setATAttach(String str) { atAttach = str; }
	public void setAccountName(String str) { accountName = str; }
	public void setCustomerName(String str) { customerName = str; }
	public void setERPDealID(String str) { erpDealID = str; }
	public void setSalesOrderNumberDetail(String str) { salesOrderNumberDetail = str; }
	public void setFPQuarter(String str) { fpQuarter = str; }
	public void setPartner(String str) { partner = str; }
	public void setPartnerName(String str) { partnerName = str; }
	public void setSalesAgent(String str) { tbm = str; }
	public void setRegion(String str) { region = str; }
	public void setSL6(String str) { salesLevel6 = str; }
	public void setSCMS(String str) { scms = str; }
	public void setSubSCMS(String str) { subSCMS = str; }
	public void setTMS1(String str) { tmsLevel1SalesAllocated = str; }
	public void setTechName(String str) { techName = str; }
	public void setTechCode(String str) { techCode = str; }
	public void setTechnologyGroup(String str) { technologyGroup = str; }
	public void setPartnerTierCode(String str) { partnerTierCode = str; }
	public void setShipToCity(String str) { shipToCity = str; }
	public void setBookingsQuantity(String str) { tmsBookingsQuantity = str; }
	public void setBEName(String str) { beName = str; }
	public void setSubBEName(String str) { subBEName = str; }
	public void setTAMMatch(String str) { tamMatch = str; }
	public void setAGMatch(String str) { agMatch = str; }
	public void setAGPartnerMatch(String str) { agParMatch = str; }
	public void setAGMatch2(String str) { agMatch2 = str; }
	public void setArch1(String str) { arch1 = str; }
	public void setArch2(String str) { arch2 = str; }
	public void setProductID(String str) { productID = str; }
	public void setMappedID5(String str) { mappedID5 = str; }
	public void setMappedName5(String str) { mappedName5 = str; }
	public void setMappedType5(String str) { mappedType5 = str; }
	public void setVSTeamNode(String str) { vsTeamNode = str; }
	public void setBillToSiteCity(String str) { billToSiteCity = str; }
	public void setVertical(String str) { vertical = str; }
	public void setFY15TAM(String str) { fy15TAM = str; }
	public void setIOTPortfolio(String str) { iotPortfolio = str; }
	public void setGTMu(String str) { gtmu = str; }
	public void setPartnerCertification(String str) { partnerCertification = str; }
	public void setPartnerType(String str) { partnerType = str; }
	public void setProductFamily(String str) { productFamily = str; }
	public void setBookingAdjustment(String str) { bookingAdjustment = str; }
	public void setMappedSL6(String str) { mappedSalesLevel6 = str; }
	public void setMappedSubSCMS(String str) { mappedSubSCMS = str; }
	public void setMappedRegion(String str) { mappedRegion = str; }
	public void setMappedGTMu(String str) { mappedGTMu = str; }
	public void setMappedID4(String str) { mappedID4 = str; }
	public void setMappedName4(String str) { mappedName4 = str; }
	public void setMappedType4(String str) { mappedType4 = str; }
	public void setMappedID3(String str) { mappedID3 = str; }
	public void setMappedName3(String str) { mappedName3 = str; }
	public void setMappedType3(String str) { mappedType3 = str; }
	public void setMappedID2(String str) { mappedID2 = str; }
	public void setMappedName2(String str) { mappedName2 = str; }
	public void setMappedType2(String str) { mappedType2 = str; }
	public void setMappedID1(String str) { mappedID1 = str; }
	public void setMappedName1(String str) { mappedName1 = str; }
	public void setMappedType1(String str) { mappedType1 = str; }
	public void setMappedID0(String str) { mappedID0 = str; }
	public void setMappedName0(String str) { mappedName0 = str; }
	public void setMappedType0(String str) { mappedType0 = str; }
	public void setUniqueCity(String str) { uniqueCity = str; }
	public void setUniqueState(String str) { uniqueState = str; }
	public void setUniqueCountry(String str) { uniqueCountry = str; }
	public void setProductService(String str) { prodSer = str; }

	
	public int getFPYear() { return fpYear; }
	public int getFPMonth() { return fpMonth; }
	public int getFPWeek() { return fpWeek; }
	public double getBookingNet() { return bookingNet; }
	public double getBaseList() { return baseList; }
	public String getATAttach() { return atAttach; }
	public String getAccountName() { return accountName; }
	public String getCustomerName() { return customerName; }
	public String getERPDealID() { return erpDealID; }
	public String getSalesOrderNumberDetail() { return salesOrderNumberDetail; }
	public String getFPQuarter() { return fpQuarter; }
	public String getPartner() { return partner; }
	public String getPartnerName() { return partnerName; }
	public String getSalesAgent() { return tbm; }
	public String getRegion() { return region; }
	public String getSL6() { return salesLevel6; }
	public String getSCMS() { return scms; }
	public String getSubSCMS() { return subSCMS; }
	public String getTMS1() { return tmsLevel1SalesAllocated; }
	public String getTechName() { return techName; }
	public String getTechCode() { return techCode; }
	public String getTechnologyGroup() { return technologyGroup; }
	public String getPartnerTierCode() { return partnerTierCode; }
	public String getShipToCity() { return shipToCity; }
	public String getBookingsQuantity() { return tmsBookingsQuantity; }
	public String getBEName() { return beName; }
	public String getSubBEName() { return subBEName; }
	public String getTAMMatch() { return tamMatch; }
	public String getAGMatch() { return agMatch; }
	public String getAGPartnerMatch() { return agParMatch; }
	public String getAGMatch2() { return agMatch2; }
	public String getArch1() { return arch1; }
	public String getArch2() { return arch2; }
	public String getProductID() { return productID; }
	public String getMappedID5() { return mappedID5; }
	public String getMappedName5() { return mappedName5; }
	public String getMappedType5() { return mappedType5; }
	public String getVSTeamNode() { return vsTeamNode; }
	public String getBillToSiteCity() { return billToSiteCity; }
	public String getFY15TAM() { return fy15TAM; }
	public String getIOTPortfolio() { return iotPortfolio; }
	public String getGTMu() { return gtmu; }
	public String getPartnerCertification() { return partnerCertification; }
	public String getPartnerType() { return partnerType; }
	public String getProductFamily() { return productFamily; }
	public String getBookingAdjustment() { return bookingAdjustment; }
	public String getMappedSL6() { return mappedSalesLevel6; }
	public String getMappedSubSCMS() { return mappedSubSCMS; }
	public String getMappedRegion() { return mappedRegion; }
	public String getMappedGTMu() { return mappedGTMu; }
	public String getMappedID4() { return mappedID4; }
	public String getMappedName4() { return mappedName4; }
	public String getMappedType4() { return mappedType4; }
	public String getMappedID3() { return mappedID3; }
	public String getMappedName3() { return mappedName3; }
	public String getMappedType3() { return mappedType3; }
	public String getMappedID2() { return mappedID2; }
	public String getMappedName2() { return mappedName2; }
	public String getMappedType2() { return mappedType2; }
	public String getMappedID1() { return mappedID1; }
	public String getMappedName1() { return mappedName1; }
	public String getMappedType1() { return mappedType1; }
	public String getMappedID0() { return mappedID0; }
	public String getMappedName0() { return mappedName0; }
	public String getMappedType0() { return mappedType0; }
	public String getUniqueCity() { return uniqueCity; }
	public String getUniqueState() { return uniqueState; }
	public String getUniqueCountry() { return uniqueCountry; }
	public String getProductService() { return prodSer; }

}
