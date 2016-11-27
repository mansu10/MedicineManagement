package com.medicine.entities;

import java.io.Serializable;

public class DemandAgency implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String demandAgencyCode;
	private String demandAgencyName;
	private String demandAgencyType;
	private String demandAgencyLevel;
	private String demandAgencyAddress1;
	private String demandAgencyAddress2;
	private String demandAgencyCoordinate;
	private int demandAgencyNumber;
	private String demandAgencyProtect;
	private String contacts;
	private String tel;
	private String province;
	private String city;
	private String district;
	private int zipCode;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDemandAgencyCode() {
		return demandAgencyCode;
	}
	public void setDemandAgencyCode(String demandAgencyCode) {
		this.demandAgencyCode = demandAgencyCode;
	}
	public String getDemandAgencyName() {
		return demandAgencyName;
	}
	public void setDemandAgencyName(String demandAgencyName) {
		this.demandAgencyName = demandAgencyName;
	}
	public String getDemandAgencyType() {
		return demandAgencyType;
	}
	public void setDemandAgencyType(String demandAgencyType) {
		this.demandAgencyType = demandAgencyType;
	}
	public String getDemandAgencyLevel() {
		return demandAgencyLevel;
	}
	public void setDemandAgencyLevel(String demandAgencyLevel) {
		this.demandAgencyLevel = demandAgencyLevel;
	}
	public String getDemandAgencyAddress1() {
		return demandAgencyAddress1;
	}
	public void setDemandAgencyAddress1(String demandAgencyAddress1) {
		this.demandAgencyAddress1 = demandAgencyAddress1;
	}
	public String getDemandAgencyAddress2() {
		return demandAgencyAddress2;
	}
	public void setDemandAgencyAddress2(String demandAgencyAddress2) {
		this.demandAgencyAddress2 = demandAgencyAddress2;
	}
	public String getDemandAgencyCoordinate() {
		return demandAgencyCoordinate;
	}
	public void setDemandAgencyCoordinate(String demandAgencyCoordinate) {
		this.demandAgencyCoordinate = demandAgencyCoordinate;
	}
	public int getDemandAgencyNumber() {
		return demandAgencyNumber;
	}
	public void setDemandAgencyNumber(int demandAgencyNumber) {
		this.demandAgencyNumber = demandAgencyNumber;
	}
	public String getDemandAgencyProtect() {
		return demandAgencyProtect;
	}
	public void setDemandAgencyProtect(String demandAgencyProtect) {
		this.demandAgencyProtect = demandAgencyProtect;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public int getZipCode() {
		return zipCode;
	}
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

}
