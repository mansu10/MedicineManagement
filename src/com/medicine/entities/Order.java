package com.medicine.entities;

import java.util.Date;

public class Order {

	private long id;
	private String orderCode;
	private String customerCode;
	private int productId;
	private String orderType;
	private Date orderTime;
	private Date deliveryTime;
	private int orderStatus;
	private String receiver;
	private String receiptAddress;
	private String tel;
	private int receiptType;
	private String invoiceMessage;
	private int shipMethod;
	private int payMethod;
	private Date shipTime;
	private int level;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public Date getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getReceiptAddress() {
		return receiptAddress;
	}
	public void setReceiptAddress(String receiptAddress) {
		this.receiptAddress = receiptAddress;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getReceiptType() {
		return receiptType;
	}
	public void setReceiptType(int receiptType) {
		this.receiptType = receiptType;
	}
	public String getInvoiceMessage() {
		return invoiceMessage;
	}
	public void setInvoiceMessage(String invoiceMessage) {
		this.invoiceMessage = invoiceMessage;
	}
	public int getShipMethod() {
		return shipMethod;
	}
	public void setShipMethod(int shipMethod) {
		this.shipMethod = shipMethod;
	}
	public int getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(int payMethod) {
		this.payMethod = payMethod;
	}
	public Date getShipTime() {
		return shipTime;
	}
	public void setShipTime(Date shipTime) {
		this.shipTime = shipTime;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	
}
