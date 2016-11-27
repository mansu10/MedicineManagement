package com.medicine.entities;

import java.io.Serializable;

public class InvoiceCargo implements Serializable{

	/**
	 * @author tdw
	 * @time: 2016年11月3日下午10:37:54
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String invoiceCode;
	private String boxNumber;
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
	public String getBoxNumber() {
		return boxNumber;
	}
	public void setBoxNumber(String boxNumber) {
		this.boxNumber = boxNumber;
	}
	
}
