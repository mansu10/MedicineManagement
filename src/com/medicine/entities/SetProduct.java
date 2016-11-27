package com.medicine.entities;

import java.io.Serializable;

public class SetProduct implements Serializable{

	/**
	 * @author tdw
	 * @time: 2016年10月20日下午8:29:25
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String setProductCode;
	private String subProductCode;
	private int subProductNumber;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSetProductCode() {
		return setProductCode;
	}
	public void setSetProductCode(String setProductCode) {
		this.setProductCode = setProductCode;
	}
	public String getSubProductCode() {
		return subProductCode;
	}
	public void setSubProductCode(String subProductCode) {
		this.subProductCode = subProductCode;
	}
	public int getSubProductNumber() {
		return subProductNumber;
	}
	public void setSubProductNumber(int subProductNumber) {
		this.subProductNumber = subProductNumber;
	}
	
}
