package com.medicine.entities;

import java.io.Serializable;
import java.util.Date;

public class GuaranteeRelationShip implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private Date createTime;
	private String demandAgencyCode;
	private String supplyAgencyCode;
	private String transportRoute;
	
	private String demandAgencyName;
	private String demandAgencyAddress1;
	private String supplyAgencyName;
	private String supplyAgencyAddress1;
	
	private DemandAgency demandAgency;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getDemandAgencyCode() {
		return demandAgencyCode;
	}
	public void setDemandAgencyCode(String demandAgencyCode) {
		this.demandAgencyCode = demandAgencyCode;
	}
	public String getSupplyAgencyCode() {
		return supplyAgencyCode;
	}
	public void setSupplyAgencyCode(String supplyAgencyCode) {
		this.supplyAgencyCode = supplyAgencyCode;
	}
	public String getDemandAgencyName() {
		return demandAgencyName;
	}
	public void setDemandAgencyName(String demandAgencyName) {
		this.demandAgencyName = demandAgencyName;
	}
	public String getDemandAgencyAddress1() {
		return demandAgencyAddress1;
	}
	public void setDemandAgencyAddress1(String demandAgencyAddress1) {
		this.demandAgencyAddress1 = demandAgencyAddress1;
	}
	public String getSupplyAgencyName() {
		return supplyAgencyName;
	}
	public void setSupplyAgencyName(String supplyAgencyName) {
		this.supplyAgencyName = supplyAgencyName;
	}
	public String getSupplyAgencyAddress1() {
		return supplyAgencyAddress1;
	}
	public void setSupplyAgencyAddress1(String supplyAgencyAddress1) {
		this.supplyAgencyAddress1 = supplyAgencyAddress1;
	}
	public String getTransportRoute() {
		return transportRoute;
	}
	public void setTransportRoute(String transportRoute) {
		this.transportRoute = transportRoute;
	}
	public DemandAgency getDemandAgency() {
		return demandAgency;
	}
	public void setDemandAgency(DemandAgency demandAgency) {
		this.demandAgency = demandAgency;
	}
	
}
