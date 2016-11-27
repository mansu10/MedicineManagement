package com.medicine.entities;

import java.io.Serializable;
import java.util.List;

public class Car implements Serializable{

	/**
	 * @author tdw
	 * @time: 2016年11月5日上午10:35:50
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	//车辆编号
	private String carCode;
	//车辆名称
	private String carName;
	//车辆单位
	private String carUnit;
	//司机
	private String carDriver;
	//最大载重
	private double maxWeight;
	//车厢体积
	private double carVolume;
	//车牌号
	private String carNumber;
	//车型
	private String carType;
	//车辆长度
	private int carLength;
	//车辆宽度
	private int carWidth;
	//车辆高度
	private int carHeigth;
	//状态
	private String agencyCode;
	private String factory;
	private String rechargeMileage;
	private String maxSpeed;
	private String averageSpeed;
	private String carStatusStr;
//	private Date startTime;
//	private Date endTime;
//	private String stowageCode;
//	private String transportRoute;
	private List<CarRecord> carRecords;
	//备注
	private String memo;
	private String carOrder;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCarCode() {
		return carCode;
	}
	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public String getCarUnit() {
		return carUnit;
	}
	public void setCarUnit(String carUnit) {
		this.carUnit = carUnit;
	}
	public String getCarDriver() {
		return carDriver;
	}
	public void setCarDriver(String carDriver) {
		this.carDriver = carDriver;
	}
	public double getMaxWeight() {
		return maxWeight;
	}
	public void setMaxWeight(double maxWeight) {
		this.maxWeight = maxWeight;
	}
	public double getCarVolume() {
		return carVolume;
	}
	public void setCarVolume(double carVolume) {
		this.carVolume = carVolume;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public int getCarLength() {
		return carLength;
	}
	public void setCarLength(int carLength) {
		this.carLength = carLength;
	}
	public int getCarWidth() {
		return carWidth;
	}
	public void setCarWidth(int carWidth) {
		this.carWidth = carWidth;
	}
	public int getCarHeigth() {
		return carHeigth;
	}
	public void setCarHeigth(int carHeigth) {
		this.carHeigth = carHeigth;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getCarOrder() {
		return carOrder;
	}
	public void setCarOrder(String carOrder) {
		this.carOrder = carOrder;
	}
	public String getFactory() {
		return factory;
	}
	public void setFactory(String factory) {
		this.factory = factory;
	}
	public String getRechargeMileage() {
		return rechargeMileage;
	}
	public void setRechargeMileage(String rechargeMileage) {
		this.rechargeMileage = rechargeMileage;
	}
	public String getMaxSpeed() {
		return maxSpeed;
	}
	public void setMaxSpeed(String maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	public String getAverageSpeed() {
		return averageSpeed;
	}
	public void setAverageSpeed(String averageSpeed) {
		this.averageSpeed = averageSpeed;
	}
	public String getAgencyCode() {
		return agencyCode;
	}
	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}
	public String getCarStatusStr() {
		return carStatusStr;
	}
	public void setCarStatusStr(String carStatusStr) {
		this.carStatusStr = carStatusStr;
	}
//	public Date getStartTime() {
//		return startTime;
//	}
//	public void setStartTime(Date startTime) {
//		this.startTime = startTime;
//	}
//	public Date getEndTime() {
//		return endTime;
//	}
//	public void setEndTime(Date endTime) {
//		this.endTime = endTime;
//	}
//	public String getStowageCode() {
//		return stowageCode;
//	}
//	public void setStowageCode(String stowageCode) {
//		this.stowageCode = stowageCode;
//	}
//	public String getTransportRoute() {
//		return transportRoute;
//	}
//	public void setTransportRoute(String transportRoute) {
//		this.transportRoute = transportRoute;
//	}
	public List<CarRecord> getCarRecords() {
		return carRecords;
	}
	public void setCarRecords(List<CarRecord> carRecords) {
		this.carRecords = carRecords;
	}
	
}
