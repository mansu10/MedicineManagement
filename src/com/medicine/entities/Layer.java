package com.medicine.entities;

import java.util.List;

public class Layer {

	private int layerNumber;

	private List<Grid> grids;
	
	public List<Grid> getGrids() {
		return grids;
	}

	public void setGrids(List<Grid> grids) {
		this.grids = grids;
	}

	public int getLayerNumber() {
		return layerNumber;
	}

	public void setLayerNumber(int layerNumber) {
		this.layerNumber = layerNumber;
	}
	
}
