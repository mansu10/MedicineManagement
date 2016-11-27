package com.medicine.cl;

import java.util.ArrayList;
import java.util.List;

import com.medicine.dao.LocatorDAO;
import com.medicine.entities.Depot;
import com.medicine.entities.Grid;
import com.medicine.entities.Layer;
import com.medicine.entities.Reservoir;
import com.medicine.entities.Shelf;
import com.medicine.util.MedicineRuntimeException;

public class LocatorManager extends DAOManager{

	private LocatorDAO locatorDAO = new LocatorDAO();
	
	public List<Depot> findAllLocators(){
		List<Depot> depots = new ArrayList<Depot>();
		List<Reservoir> reservoirs = new ArrayList<Reservoir>();
		List<Shelf> shelfs = new ArrayList<Shelf>();
		List<Layer> layers = new ArrayList<Layer>();
		List<Grid> grids = new ArrayList<Grid>();
		try {
			depots = locatorDAO.findAllDepots();
			for (Depot depot : depots) {
				reservoirs = locatorDAO.findAllReservoirs(depot);
				for (Reservoir reservoir : reservoirs) {
					shelfs = locatorDAO.findAllShelfs(reservoir.getReservoirNumber(), depot.getDepotNumber());
					for (Shelf shelf : shelfs) {
						layers = locatorDAO.findAllLayers(shelf.getShelfNumber(), reservoir.getReservoirNumber(), depot.getDepotNumber());
						for (Layer layer : layers) {
							grids = locatorDAO.findAllGrids(layer.getLayerNumber(), shelf.getShelfNumber(), reservoir.getReservoirNumber(), depot.getDepotNumber());
							layer.setGrids(grids);
						}
						shelf.setLayers(layers);
					}
					reservoir.setShelfs(shelfs);
				}
				depot.setReservoirs(reservoirs);
			}
		}catch (Exception e) {
			throw new MedicineRuntimeException(e.getMessage());
		}
		return depots;
	}
	
}
