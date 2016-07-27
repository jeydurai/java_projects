package modelsDataStructures;

public class DSDumpFromFinance {

	private float id;
	private String bookingsAdjustmentsCode, bookingsAdjustmentsDescription, bookingsAdjustmentsType, atAttach,
	businessUnit, customerName, erpDealID, salesOrderNumberDetail, fiscalPeriodID, fiscalQuarterID,
	fiscalWeekID, partnerName, productFamily, productID, tbm, salesLevel2, salesLevel3, salesLevel4, 
	salesLevel5, salesLevel6, scms, subSCMS, tmsLevel1SalesAllocated, tmsLevel2SalesAllocated, 
	tmsLevel3SalesAllocated, tmsLevel4SalesAllocated, technologyGroup, partnerTierCode, partnerCertification, 
	partnerType, billToSiteCity, shipToCity, cbnFlag; 
	private double bookingNet, baseList; 
	private String bookingsQuantity, beName, subBEName;
	private double bookingsStandardCost; 
	private String prodSer;

	public DSDumpFromFinance(float id, String bookingsAdjustmentsCode, String bookingsAdjustmentsDescription, String bookingsAdjustmentsType, String atAttach,
			String businessUnit, String customerName, String erpDealID, String salesOrderNumberDetail, String fiscalPeriodID, String fiscalQuarterID,
			String fiscalWeekID, String partnerName, String productFamily, String productID, String tbm, String salesLevel2, String salesLevel3, String salesLevel4, 
			String salesLevel5, String salesLevel6, String scms, String subSCMS, String tmsLevel1SalesAllocated, String tmsLevel2SalesAllocated, 
			String tmsLevel3SalesAllocated, String tmsLevel4SalesAllocated, String technologyGroup, String partnerTierCode, String partnerCertification, 
			String partnerType, String billToSiteCity, String shipToCity, String cbnFlag, String bookingsQuantity, String beName, String subBEName, String prodSer, 
			double bookingNet, double baseList, double bookingsStandardCost) {
		setID(id);
		setBookingsAdjustmentsCode(bookingsAdjustmentsCode);
		setBookingsAdjustmentsDescription(bookingsAdjustmentsDescription);
		setBookingsAdjustmentsType(bookingsAdjustmentsType);
		setATAttach(atAttach);
		setBusinessUnit(businessUnit);
		setCustomerName(customerName);
		setERPDealID(erpDealID);
		setSalesOrderNumberDetail(salesOrderNumberDetail);
		setFiscalPeriodID(fiscalPeriodID);
		setFiscalQuarterID(fiscalQuarterID);
		setFiscalWeekID(fiscalWeekID);
		setPartnerName(partnerName);
		setProductFamily(productFamily);
		setProductID(productID);
		setSalesAgent(tbm);
		setSalesLevel2(salesLevel2);
		setSalesLevel3(salesLevel3);
		setSalesLevel4(salesLevel4);
		setSalesLevel5(salesLevel5);
		setSalesLevel6(salesLevel6);
		setSCMS(scms);
		setSubSCMS(subSCMS);
		setTMSLevel1SalesAllocated(tmsLevel1SalesAllocated);
		setTMSLevel2SalesAllocated(tmsLevel2SalesAllocated);
		setTMSLevel3SalesAllocated(tmsLevel3SalesAllocated);
		setTMSLevel4SalesAllocated(tmsLevel4SalesAllocated);
		setTechnologyGroup(technologyGroup);
		setPartnerTierCode(partnerTierCode);
		setPartnerCertification(partnerCertification);
		setPartnerType(partnerType);
		setBillToSiteCity(billToSiteCity);
		setShipToCity(shipToCity);
		setCBNFlag(cbnFlag);
		setBookingsQuantity(bookingsQuantity);
		setBEName(beName);
		setSubBEName(subBEName);
		setProdSer(prodSer);
		setBookingNet(bookingNet);
		setBaseList(baseList);
		setBookingsStandardCost(bookingsStandardCost);
	}
	
	//Set Methods
	//============
	public void setID(float num) { id = num; }
	public void setBookingsAdjustmentsCode(String str) { bookingsAdjustmentsCode = str; }
	public void setBookingsAdjustmentsDescription(String str) { bookingsAdjustmentsDescription = str; }
	public void setBookingsAdjustmentsType(String str) { bookingsAdjustmentsType = str; }
	public void setATAttach(String str) { atAttach = str; }
	public void setBusinessUnit(String str) { businessUnit = str; }
	public void setCustomerName(String str) { customerName = str; }
	public void setERPDealID(String str) { erpDealID = str; }
	public void setSalesOrderNumberDetail(String str) { salesOrderNumberDetail = str; }
	public void setFiscalPeriodID(String str) { fiscalPeriodID = str; }
	public void setFiscalQuarterID(String str) { fiscalQuarterID = str; }
	public void setFiscalWeekID(String str) { fiscalWeekID = str; }
	public void setPartnerName(String str) { partnerName = str; }
	public void setProductFamily(String str) { productFamily = str; }
	public void setProductID(String str) { productID = str; }
	public void setSalesAgent(String str) { tbm = str; }
	public void setSalesLevel2(String str) { salesLevel2 = str; }
	public void setSalesLevel3(String str) { salesLevel3 = str; }
	public void setSalesLevel4(String str) { salesLevel4 = str; }
	public void setSalesLevel5(String str) { salesLevel5 = str; }
	public void setSalesLevel6(String str) { salesLevel6 = str; }
	public void setSCMS(String str) { scms = str; }
	public void setSubSCMS(String str) { subSCMS = str; }
	public void setTMSLevel1SalesAllocated(String str) { tmsLevel1SalesAllocated = str; }
	public void setTMSLevel2SalesAllocated(String str) { tmsLevel2SalesAllocated = str; }
	public void setTMSLevel3SalesAllocated(String str) { tmsLevel3SalesAllocated = str; }
	public void setTMSLevel4SalesAllocated(String str) { tmsLevel4SalesAllocated = str; }
	public void setTechnologyGroup(String str) { technologyGroup = str; }
	public void setPartnerTierCode(String str) { partnerTierCode = str; }
	public void setPartnerCertification(String str) { partnerCertification = str; }
	public void setPartnerType(String str) { partnerType = str; }
	public void setBillToSiteCity(String str) { billToSiteCity = str; }
	public void setShipToCity(String str) { shipToCity = str; }
	public void setCBNFlag(String str) { technologyGroup = str; }
	public void setBookingsQuantity(String str) { bookingsQuantity = str; }
	public void setBEName(String str) { beName = str; }
	public void setSubBEName(String str) { subBEName = str; }
	public void setProdSer(String str) { prodSer = str; }
	public void setBookingNet(double num) { bookingNet = num; }
	public void setBaseList(double num) { baseList = num; }
	public void setBookingsStandardCost(double num) { bookingsStandardCost = num; }

	//Get Methods
	//============
	public float getID() { return id; }
	public String getBookingsAdjustmentsCode() { return bookingsAdjustmentsCode; }
	public String getBookingsAdjustmentsDescription() { return bookingsAdjustmentsDescription; }
	public String getBookingsAdjustmentsType() { return bookingsAdjustmentsType; }
	public String getATAttach() { return atAttach; }
	public String getBusinessUnit() { return businessUnit; }
	public String getCustomerName() { return customerName; }
	public String getERPDealID() { return erpDealID; }
	public String getSalesOrderNumberDetail() { return salesOrderNumberDetail; }
	public String getFiscalPeriodID() { return fiscalPeriodID; }
	public String getFiscalQuarterID() { return fiscalQuarterID; }
	public String getFiscalWeekID() { return fiscalWeekID; }
	public String getPartnerName() { return partnerName; }
	public String getProductFamily() { return productFamily; }
	public String getProductID() { return productID; }
	public String getSalesAgent() { return tbm; }
	public String getSalesLevel2() { return salesLevel2; }
	public String getSalesLevel3() { return salesLevel3; }
	public String getSalesLevel4() { return salesLevel4; }
	public String getSalesLevel5() { return salesLevel5; }
	public String getSalesLevel6() { return salesLevel6; }
	public String getSCMS() { return scms; }
	public String getSubSCMS() { return subSCMS; }
	public String getTMSLevel1SalesAllocated() { return tmsLevel1SalesAllocated; }
	public String getTMSLevel2SalesAllocated() { return tmsLevel2SalesAllocated; }
	public String getTMSLevel3SalesAllocated() { return tmsLevel3SalesAllocated; }
	public String getTMSLevel4SalesAllocated() { return tmsLevel4SalesAllocated; }
	public String getTechnologyGroup() { return technologyGroup; }
	public String getPartnerTierCode() { return partnerTierCode; }
	public String getPartnerCertification() { return partnerCertification; }
	public String getPartnerType() { return partnerType; }
	public String getBillToSiteCity() { return billToSiteCity; }
	public String getShipToCity() { return shipToCity; }
	public String getCBNFlag() { return technologyGroup; }
	public String getBookingsQuantity() { return bookingsQuantity; }
	public String getBEName() { return beName; }
	public String getSubBEName() { return subBEName; }
	public String getProdSer() { return prodSer; }
	public double getBookingNet() { return bookingNet; }
	public double getBaseList() { return baseList; }
	public double getBookingsStandardCost() { return bookingsStandardCost; }

}
