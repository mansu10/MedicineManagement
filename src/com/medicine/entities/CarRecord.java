package com.medicine.entities;

import java.io.Serializable;
import java.util.Date;

public class CarRecord implements Serializable{

	/**
	 * @author tdw
	 * @time: 2016年11月13日下午8:12:45
	 */
	private static final long serialVersionUID = 763468450826282541L;
	
	private Date startTime;
	private Date endTime;
	private String stowageCode;
	private String transportRoute;
	private int countInvoiceCode;
	private String mileage;
	
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
	public int getCountInvoiceCode() {
		return countInvoiceCode;
	}
	public void setCountInvoiceCode(int countInvoiceCode) {
		this.countInvoiceCode = countInvoiceCode;
	}
	public String getMileage() {
		return mileage;
	}
	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
	
}
