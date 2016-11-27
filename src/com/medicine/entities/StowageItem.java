package com.medicine.entities;

import java.io.Serializable;
import java.util.Date;

public class StowageItem implements Serializable{

	/**
	 * @author tdw
	 * @time: 2016年11月3日下午10:44:31
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String stowageCode;
	private String invoiceCode;
	private Date invoiceTime;
	private Date signedTime;
	private String signedRecord;
	private String sequence;
	
	private String receiptAddress;
	
	public String getReceiptAddress() {
		return receiptAddress;
	}
	public void setReceiptAddress(String receiptAddress) {
		this.receiptAddress = receiptAddress;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStowageCode() {
		return stowageCode;
	}
	public void setStowageCode(String stowageCode) {
		this.stowageCode = stowageCode;
	}
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	public Date getInvoiceTime() {
		return invoiceTime;
	}
	public void setInvoiceTime(Date invoiceTime) {
		this.invoiceTime = invoiceTime;
	}
	public Date getSignedTime() {
		return signedTime;
	}
	public void setSignedTime(Date signedTime) {
		this.signedTime = signedTime;
	}
	public String getSignedRecord() {
		return signedRecord;
	}
	public void setSignedRecord(String signedRecord) {
		this.signedRecord = signedRecord;
	}
}
