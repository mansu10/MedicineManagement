package com.medicine.entities;

import java.util.Date;

public class Stock {

	private int id;
	private int productCode;
	private int caseQuantity;
	private Date inStockTime;
	private Date outStockTime;
	private int banchNumber;
	private String locatorName;
	private String locatorCode;
	private String memo;
	private String caseNumber;
	private int status;
	private Date factoryTime;
	private	String receiptCode;
	
	private int stockQuantity;
	private float stockValue;
	private float stockLevel;
	private String stockStatus;
	
	private Product product;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProductCode() {
		return productCode;
	}
	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}
	public int getCaseQuantity() {
		return caseQuantity;
	}
	public void setCaseQuantity(int caseQuantity) {
		this.caseQuantity = caseQuantity;
	}
	public String getLocatorName() {
		return locatorName;
	}
	public void setLocatorName(String locatorName) {
		this.locatorName = locatorName;
	}
	public String getLocatorCode() {
		return locatorCode;
	}
	public void setLocatorCode(String locatorCode) {
		this.locatorCode = locatorCode;
	}
	public int getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	public float getStockValue() {
		return stockValue;
	}
	public void setStockValue(float stockValue) {
		this.stockValue = stockValue;
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
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getCaseNumber() {
		return caseNumber;
	}
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getFactoryTime() {
		return factoryTime;
	}
	public void setFactoryTime(Date factoryTime) {
		this.factoryTime = factoryTime;
	}
	public float getStockLevel() {
		return stockLevel;
	}
	public void setStockLevel(float stockLevel) {
		this.stockLevel = stockLevel;
	}
	public String getStockStatus() {
		return stockStatus;
	}
	public void setStockStatus(String stockStatus) {
		this.stockStatus = stockStatus;
	}
	public String getReceiptCode() {
		return receiptCode;
	}
	public void setReceiptCode(String receiptCode) {
		this.receiptCode = receiptCode;
	}
	
}
