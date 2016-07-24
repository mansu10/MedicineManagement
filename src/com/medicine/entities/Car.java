package com.medicine.entities;

public class Car {

	private long id;
	private String carCode;
	private String carName;
	private String carUnit;
	private String carDriver;
	private double maxWeight;
	private double carVolume;
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	
	
}
