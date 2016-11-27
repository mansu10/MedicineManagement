package com.medicine.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Stowage implements Serializable{

	/**
	 * @author tdw
	 * @time: 2016年11月3日下午10:43:56
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String orderCode;
	private String carCode;
	private Date createTime;
	private String memo;
	private float volumePercent;
	private float weightPercent;
	private String stowageCode;
	private String transportRoute;
	private String operator;
	private Date loadTime;
	private Date departureTime;
	
	private List<StowageItem> stowageItems;
	
	public List<StowageItem> getStowageItems() {
		return stowageItems;
	}
	public void setStowageItems(List<StowageItem> stowageItems) {
		this.stowageItems = stowageItems;
	}
	private int count;
	private String invoiceCode;
	private Car car;
	private Order order;
	
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getLoadTime() {
		return loadTime;
	}
	public void setLoadTime(Date loadTime) {
		this.loadTime = loadTime;
	}
	public Date getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
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
	public String getCarCode() {
		return carCode;
	}
	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public float getVolumePercent() {
		return volumePercent;
	}
	public void setVolumePercent(float volumePercent) {
		this.volumePercent = volumePercent;
	}
	public float getWeightPercent() {
		return weightPercent;
	}
	public void setWeightPercent(float weightPercent) {
		this.weightPercent = weightPercent;
	}
	public String getStowageCode() {
		return stowageCode;
	}
	public void setStowageCode(String stowageCode) {
		this.stowageCode = stowageCode;
	}
	public String getTransportRoute() {
		return transportRoute;
	}
	public void setTransportRoute(String transportRoute) {
		this.transportRoute = transportRoute;
	}
	
}
