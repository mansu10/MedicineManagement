package com.medicine.entities;

import java.io.Serializable;

public class SupplyAgencyJob implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String supplyAgencyCode;
	private int roleCode;
	private String roleName;
	private String roleGroup;
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
	public int getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(int roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleGroup() {
		return roleGroup;
	}
	public void setRoleGroup(String roleGroup) {
		this.roleGroup = roleGroup;
	}
	
}
