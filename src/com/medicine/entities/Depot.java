package com.medicine.entities;

import java.io.Serializable;
import java.util.List;

public class Depot implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int depotCode;
	private String depotName;
	private String depotType;
	private String depotAddress;
	private String principal;
	private int depotLength;
	private int depotWidth;
	private int depotHeigth;
	private int depotVolume;
	
	private List<CargoArea> cargoAreas;
	
	private int depotNumber;
	
	private List<Reservoir> reservoirs;
	private int depotCount;
	
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

	public String getDepotName() {
		return depotName;
	}

	public void setDepotName(String depotName) {
		this.depotName = depotName;
	}

	public String getDepotAddress() {
		return depotAddress;
	}

	public void setDepotAddress(String depotAddress) {
		this.depotAddress = depotAddress;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public int getDepotLength() {
		return depotLength;
	}

	public void setDepotLength(int depotLength) {
		this.depotLength = depotLength;
	}

	public int getDepotWidth() {
		return depotWidth;
	}

	public void setDepotWidth(int depotWidth) {
		this.depotWidth = depotWidth;
	}

	public int getDepotHeigth() {
		return depotHeigth;
	}

	public void setDepotHeigth(int depotHeigth) {
		this.depotHeigth = depotHeigth;
	}

	public int getDepotVolume() {
		return depotVolume;
	}

	public void setDepotVolume(int depotVolume) {
		this.depotVolume = depotVolume;
	}

	public String getDepotType() {
		return depotType;
	}

	public void setDepotType(String depotType) {
		this.depotType = depotType;
	}

	public List<CargoArea> getCargoAreas() {
		return cargoAreas;
	}

	public void setCargoAreas(List<CargoArea> cargoAreas) {
		this.cargoAreas = cargoAreas;
	}

	public int getDepotCount() {
		return depotCount;
	}

	public void setDepotCount(int depotCount) {
		this.depotCount = depotCount;
	}

	public int getDepotNumber() {
		return depotNumber;
	}

	public void setDepotNumber(int depotNumber) {
		this.depotNumber = depotNumber;
	}

	public List<Reservoir> getReservoirs() {
		return reservoirs;
	}

	public void setReservoirs(List<Reservoir> reservoirs) {
		this.reservoirs = reservoirs;
	}

	@Override
	public String toString() {
		return "Depot [id=" + id + ", depotCode=" + depotCode + ", depotName=" + depotName + ", depotType=" + depotType
				+ ", depotAddress=" + depotAddress + ", pricipal=" + principal + ", depotLength=" + depotLength
				+ ", depotWidth=" + depotWidth + ", depotHeigth=" + depotHeigth + ", depotVolume=" + depotVolume
				+ ", cargoAreas=" + cargoAreas + ", depotNumber=" + depotNumber + ", reservoirs=" + reservoirs
				+ ", depotCount=" + depotCount + "]";
	}

}
