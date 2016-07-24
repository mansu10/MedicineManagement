package com.medicine.entities;

import java.util.Date;

public class Stock {

	private long id;
	private int productCode;
	private int stockNumber;
	private Date inStockTime;
	private Date outStockTime;
	private int banchNumber;
	private String storageLocation;
	private String storageCode;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getProductCode() {
		return productCode;
	}
	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}
	public int getStockNumber() {
		return stockNumber;
	}
	public void setStockNumber(int stockNumber) {
		this.stockNumber = stockNumber;
	}
	public Date getInStockTime() {
		return inStockTime;
	}
	public void setInStockTime(Date inStockTime) {
		this.inStockTime = inStockTime;
	}
	public Date getOutStockTime() {
		return outStockTime;
	}
	public void setOutStockTime(Date outStockTime) {
		this.outStockTime = outStockTime;
	}
	public int getBanchNumber() {
		return banchNumber;
	}
	public void setBanchNumber(int banchNumber) {
		this.banchNumber = banchNumber;
	}
	public String getStorageLocation() {
		return storageLocation;
	}
	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}
	public String getStorageCode() {
		return storageCode;
	}
	public void setStorageCode(String storageCode) {
		this.storageCode = storageCode;
	}
	
	
}
