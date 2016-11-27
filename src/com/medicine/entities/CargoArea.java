package com.medicine.entities;

import java.io.Serializable;
import java.util.List;

public class CargoArea implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int depotCode;
	private String cargoAreaCode;
	private String cargoAreaName;
	private String cargoAreaDesc;
	
	private int shelfCount;
	private int locatorCount;
	private int boxCount;
	private List<Shelf> shelfs;
	private List<Locator> locators;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDepotCode() {
		return depotCode;
	}
	public void setDepotCode(int depotCode) {
		this.depotCode = depotCode;
	}
	public String getCargoAreaCode() {
		return cargoAreaCode;
	}
	public void setCargoAreaCode(String cargoAreaCode) {
		this.cargoAreaCode = cargoAreaCode;
	}
	public String getCargoAreaName() {
		return cargoAreaName;
	}
	public void setCargoAreaName(String cargoAreaName) {
		this.cargoAreaName = cargoAreaName;
	}
	public String getCargoAreaDesc() {
		return cargoAreaDesc;
	}
	public void setCargoAreaDesc(String cargoAreaDesc) {
		this.cargoAreaDesc = cargoAreaDesc;
	}
	public int getShelfCount() {
		return shelfCount;
	}
	public void setShelfCount(int shelfCount) {
		this.shelfCount = shelfCount;
	}
	public int getLocatorCount() {
		return locatorCount;
	}
	public void setLocatorCount(int locatorCount) {
		this.locatorCount = locatorCount;
	}
	public int getBoxCount() {
		return boxCount;
	}
	public void setBoxCount(int boxCount) {
		this.boxCount = boxCount;
	}
	public List<Shelf> getShelfs() {
		return shelfs;
	}
	public void setShelfs(List<Shelf> shelfs) {
		this.shelfs = shelfs;
	}
	public List<Locator> getLocators() {
		return locators;
	}
	public void setLocators(List<Locator> locators) {
		this.locators = locators;
	}
	@Override
	public String toString() {
		return "CargoArea [id=" + id + ", depotCode=" + depotCode + ", cargoAreaCode=" + cargoAreaCode
				+ ", cargoAreaName=" + cargoAreaName + ", cargoAreaDesc=" + cargoAreaDesc + ", shelfCount=" + shelfCount
				+ ", locatorCount=" + locatorCount + ", boxCount=" + boxCount + "]";
	}
	
}
