package com.medicine.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable{

	/**
	 * @author tdw
	 * @time: 2016年10月27日下午8:54:16
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	//订单编号
	private String orderCode;
	//客户编号
	private String customerCode;
	//商品id
	private String itemIds;
	//订单类型
	private String orderType;
	//收单时间
	private Date orderTime;
	//交货时间
	private Date deliveryTime;
	//订单状态
	private int orderStatus;
	//收货人
	private String receiver;
	//收货地址
	private String receiptAddress;
	//电话
	private String tel;
	//收货类型
	private int receiptType;
	//发票信息
	private String invoiceMessage;
	//配送方式
	private int shipMethod;
	//支付方式
	private int payMethod;
	//配送时间
	private Date shipTime;
	//优先级
	private int level;
	//缺货处理
	private int outProcessing;
	//客户名称
	private String customerName;
	//备注
	private String memo;
	//订单进度
	private int orderProgress;
	//旧订单id
	private int oldOrderId;
	//订单标志
	private int orderMark;
	//包装方式
	private int packageMethod;
	//拟交货时间
	private Date intendDeliveryTime;
	//体积
	private float volume;
	//重量
	private float weight;
	
	private String demandAgencyName;
	
	private int orderNumber;
	private DemandAgency demandAgency;
	
	private List<OrderItem> orderItems;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getItemIds() {
		return itemIds;
	}
	public void setItemIds(String itemIds) {
		this.itemIds = itemIds;
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
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public int getOrderProgress() {
		return orderProgress;
	}
	public void setOrderProgress(int orderProgress) {
		this.orderProgress = orderProgress;
	}
	public int getOldOrderId() {
		return oldOrderId;
	}
	public void setOldOrderId(int oldOrderId) {
		this.oldOrderId = oldOrderId;
	}
	public int getOrderMark() {
		return orderMark;
	}
	public void setOrderMark(int orderMark) {
		this.orderMark = orderMark;
	}
	public int getPackageMethod() {
		return packageMethod;
	}
	public void setPackageMethod(int packageMethod) {
		this.packageMethod = packageMethod;
	}
	public Date getIntendDeliveryTime() {
		return intendDeliveryTime;
	}
	public void setIntendDeliveryTime(Date intendDeliveryTime) {
		this.intendDeliveryTime = intendDeliveryTime;
	}
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public float getVolume() {
		return volume;
	}
	public void setVolume(float volume) {
		this.volume = volume;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public int getOutProcessing() {
		return outProcessing;
	}
	public void setOutProcessing(int outProcessing) {
		this.outProcessing = outProcessing;
	}
	public String getDemandAgencyName() {
		return demandAgencyName;
	}
	public void setDemandAgencyName(String demandAgencyName) {
		this.demandAgencyName = demandAgencyName;
	}
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	public DemandAgency getDemandAgency() {
		return demandAgency;
	}
	public void setDemandAgency(DemandAgency demandAgency) {
		this.demandAgency = demandAgency;
	}
	
}
