package com.medicine.entities;

public class Organization {

	private long id;
	private String organizationName;
	private String organizationType;
	private String organizationAddress;
	private String organizationCoordinate;
	private int organizationPeopleNumber;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getOrganizationType() {
		return organizationType;
	}
	public void setOrganizationType(String organizationType) {
		this.organizationType = organizationType;
	}
	public String getOrganizationAddress() {
		return organizationAddress;
	}
	public void setOrganizationAddress(String organizationAddress) {
		this.organizationAddress = organizationAddress;
	}
	public String getOrganizationCoordinate() {
		return organizationCoordinate;
	}
	public void setOrganizationCoordinate(String organizationCoordinate) {
		this.organizationCoordinate = organizationCoordinate;
	}
	public int getOrganizationPeopleNumber() {
		return organizationPeopleNumber;
	}
	public void setOrganizationPeopleNumber(int organizationPeopleNumber) {
		this.organizationPeopleNumber = organizationPeopleNumber;
	}
	
	
}
