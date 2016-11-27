package com.medicine.entities;

import java.io.Serializable;

public class PickListItem implements Serializable{

	/**
	 * @author tdw
	 * @time: 2016年10月25日下午10:00:12
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String pickListCode;
	private String productCode;
	private int pickListNumber;
	private String pickListRecord;
	private String checkRecord;
	private Product product;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPickListCode() {
		return pickListCode;
	}
	public void setPickListCode(String pickListCode) {
		this.pickListCode = pickListCode;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public int getPickListNumber() {
		return pickListNumber;
	}
	public void setPickListNumber(int pickListNumber) {
		this.pickListNumber = pickListNumber;
	}
	public String getPickListRecord() {
		return pickListRecord;
	}
	public void setPickListRecord(String pickListRecord) {
		this.pickListRecord = pickListRecord;
	}
	public String getCheckRecord() {
		return checkRecord;
	}
	public void setCheckRecord(String checkRecord) {
		this.checkRecord = checkRecord;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
}
