package com.medicine.entities;

import java.io.Serializable;

public class Locator implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String locatorCode;
	private String locatorName;
	private int gridLength;
	private int gridWidth;
	private int gridHeigth;
	private int gridVolume;
	private int gridWeigth;
	private String shelfNumber;
	private int layerNumber;
	private String locatorDesc;
	private String cargoAreaCode;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLocatorCode() {
		return locatorCode;
	}
	public void setLocatorCode(String locatorCode) {
		this.locatorCode = locatorCode;
	}
	public String getLocatorName() {
		return locatorName;
	}
	public void setLocatorName(String locatorName) {
		this.locatorName = locatorName;
	}
	public int getGridLength() {
		return gridLength;
	}
	public void setGridLength(int gridLength) {
		this.gridLength = gridLength;
	}
	public int getGridWidth() {
		return gridWidth;
	}
	public void setGridWidth(int gridWidth) {
		this.gridWidth = gridWidth;
	}
	public int getGridHeigth() {
		return gridHeigth;
	}
	public void setGridHeigth(int gridHeigth) {
		this.gridHeigth = gridHeigth;
	}
	public int getGridVolume() {
		return gridVolume;
	}
	public void setGridVolume(int gridVolume) {
		this.gridVolume = gridVolume;
	}
	public int getGridWeigth() {
		return gridWeigth;
	}
	public void setGridWeigth(int gridWeigth) {
		this.gridWeigth = gridWeigth;
	}
	public String getShelfNumber() {
		return shelfNumber;
	}
	public void setShelfNumber(String shelfNumber) {
		this.shelfNumber = shelfNumber;
	}
	public int getLayerNumber() {
		return layerNumber;
	}
	public void setLayerNumber(int layerNumber) {
		this.layerNumber = layerNumber;
	}
	public String getLocatorDesc() {
		return locatorDesc;
	}
	public void setLocatorDesc(String locatorDesc) {
		this.locatorDesc = locatorDesc;
	}
	public String getCargoAreaCode() {
		return cargoAreaCode;
	}
	public void setCargoAreaCode(String cargoAreaCode) {
		this.cargoAreaCode = cargoAreaCode;
	}
	
}
