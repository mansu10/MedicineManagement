package com.medicine.entities;

import java.io.Serializable;

public class InvoiceItem implements Serializable{

	/**
	 * @author tdw
	 * @time: 2016年11月3日下午10:39:13
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String invoiceCode;
	private String productCode;
	private int productNumber;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public int getProductNumber() {
		return productNumber;
	}
	public void setProductNumber(int productNumber) {
		this.productNumber = productNumber;
	}
	

}
