package com.medicine.entities;

import java.util.List;

public class Reservoir {

	private String reservoirNumber;
	
	private List<Shelf> shelfs;

	private int reservoirCount;
	
	public int getReservoirCount() {
		return reservoirCount;
	}

	public void setReservoirCount(int reservoirCount) {
		this.reservoirCount = reservoirCount;
	}

	public String getReservoirNumber() {
		return reservoirNumber;
	}

	public void setReservoirNumber(String reservoirNumber) {
		this.reservoirNumber = reservoirNumber;
	}

	public List<Shelf> getShelfs() {
		return shelfs;
	}

	public void setShelfs(List<Shelf> shelfs) {
		this.shelfs = shelfs;
	}
	
}
