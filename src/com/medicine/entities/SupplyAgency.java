package com.medicine.entities;

import java.io.Serializable;
import java.util.List;

public class SupplyAgency implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String supplyAgencyCode;
	private String supplyAgencyName;
	private String supplyAgencyLevel;
	private String supplyAgencyAddress1;
	private String supplyAgencyAddress2;
	private String supplyAgencyCoordinate;
	private int supplyAgencyNumber;
	private String supplyAgencyType;
	private List<SupplyAgencyJob> supplyAgencyJobs;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSupplyAgencyCode() {
		return supplyAgencyCode;
	}
	public void setSupplyAgencyCode(String supplyAgencyCode) {
		this.supplyAgencyCode = supplyAgencyCode;
	}
	public String getSupplyAgencyName() {
		return supplyAgencyName;
	}
	public void setSupplyAgencyName(String supplyAgencyName) {
		this.supplyAgencyName = supplyAgencyName;
	}
	public String getSupplyAgencyLevel() {
		return supplyAgencyLevel;
	}
	public void setSupplyAgencyLevel(String supplyAgencyLevel) {
		this.supplyAgencyLevel = supplyAgencyLevel;
	}
	public String getSupplyAgencyAddress1() {
		return supplyAgencyAddress1;
	}
	public void setSupplyAgencyAddress1(String supplyAgencyAddress1) {
		this.supplyAgencyAddress1 = supplyAgencyAddress1;
	}
	public String getSupplyAgencyAddress2() {
		return supplyAgencyAddress2;
	}
	public void setSupplyAgencyAddress2(String supplyAgencyAddress2) {
		this.supplyAgencyAddress2 = supplyAgencyAddress2;
	}
	public String getSupplyAgencyCoordinate() {
		return supplyAgencyCoordinate;
	}
	public void setSupplyAgencyCoordinate(String supplyAgencyCoordinate) {
		this.supplyAgencyCoordinate = supplyAgencyCoordinate;
	}
	public int getSupplyAgencyNumber() {
		return supplyAgencyNumber;
	}
	public void setSupplyAgencyNumber(int supplyAgencyNumber) {
		this.supplyAgencyNumber = supplyAgencyNumber;
	}
	public String getSupplyAgencyType() {
		return supplyAgencyType;
	}
	public void setSupplyAgencyType(String supplyAgencyType) {
		this.supplyAgencyType = supplyAgencyType;
	}
	public List<SupplyAgencyJob> getSupplyAgencyJobs() {
		return supplyAgencyJobs;
	}
	public void setSupplyAgencyJobs(List<SupplyAgencyJob> supplyAgencyJobs) {
		this.supplyAgencyJobs = supplyAgencyJobs;
	}
	
}
