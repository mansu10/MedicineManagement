package com.medicine.entities;

import java.io.Serializable;
import java.util.List;

public class Shelf implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5886006023998576322L;
	private int shelfCode;
	private String shelfName;
	private int shelfSpecification;
	private int shelfLayer;
	private String cargoAreaCode;
	private String shelfStorageCategory;
	private List<Locator> locators;
	private int depotCode;
	
	private String shelfNumber;
	private int shelfCount;
	private int locatorCount;
	private List<Layer> layers;
	
	public int getShelfCode() {
		return shelfCode;
	}

	public void setShelfCode(int shelfCode) {
		this.shelfCode = shelfCode;
	}

	public String getShelfName() {
		return shelfName;
	}

	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}

	public int getShelfSpecification() {
		return shelfSpecification;
	}

	public void setShelfSpecification(int shelfSpecification) {
		this.shelfSpecification = shelfSpecification;
	}

	public int getShelfLayer() {
		return shelfLayer;
	}

	public void setShelfLayer(int shelfLayer) {
		this.shelfLayer = shelfLayer;
	}

	public String getCargoAreaCode() {
		return cargoAreaCode;
	}

	public void setCargoAreaCode(String cargoAreaCode) {
		this.cargoAreaCode = cargoAreaCode;
	}

	public String getShelfStorageCategory() {
		return shelfStorageCategory;
	}

	public void setShelfStorageCategory(String shelfStorageCategory) {
		this.shelfStorageCategory = shelfStorageCategory;
	}

	public int getShelfCount() {
		return shelfCount;
	}

	public void setShelfCount(int shelfCount) {
		this.shelfCount = shelfCount;
	}

	public String getShelfNumber() {
		return shelfNumber;
	}

	public void setShelfNumber(String shelfNumber) {
		this.shelfNumber = shelfNumber;
	}

	public List<Layer> getLayers() {
		return layers;
	}

	public void setLayers(List<Layer> layers) {
		this.layers = layers;
	}

	public int getLocatorCount() {
		return locatorCount;
	}

	public void setLocatorCount(int locatorCount) {
		this.locatorCount = locatorCount;
	}

	public List<Locator> getLocators() {
		return locators;
	}

	public void setLocators(List<Locator> locators) {
		this.locators = locators;
	}

	public int getDepotCode() {
		return depotCode;
	}

	public void setDepotCode(int depotCode) {
		this.depotCode = depotCode;
	}
	
}
