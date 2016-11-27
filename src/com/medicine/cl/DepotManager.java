package com.medicine.cl;

import java.util.List;

import com.medicine.dao.CargoAreaDAO;
import com.medicine.dao.DepotDAO;
import com.medicine.dao.LocatorDAO;
import com.medicine.dao.ShelfDAO;
import com.medicine.entities.CargoArea;
import com.medicine.entities.Depot;
import com.medicine.entities.Locator;
import com.medicine.entities.Shelf;
import com.medicine.util.MedicineRuntimeException;

public class DepotManager extends DAOManager{

	private DepotDAO depotDAO = new DepotDAO();
	private CargoAreaDAO cargoAreaDAO = new CargoAreaDAO();
	private ShelfDAO shelfDAO = new ShelfDAO();
	private LocatorDAO locatorDAO = new LocatorDAO();
	
	public List<Depot> findAllDepots() {
		List<Depot> depots = null;
		depots = depotDAO.findAllDepots();
		return depots;
	}
	
	public int addDepot(Depot depot){
		int count = 0;
		try {
			this.beginTransaction();
			count = depotDAO.addDepot(depot);
			this.commitTransaction();
		}catch(Throwable t){
			this.rollbackTransaction();
			throw new MedicineRuntimeException(t.getMessage());
		}
		return count;
	}
	
	public int updateDepot(Depot depot){
		int count = 0;
		try {
			this.beginTransaction();
			count = depotDAO.updateDepot(depot);
			this.commitTransaction();
		} catch (Throwable t) {
			this.rollbackTransaction();
			throw new MedicineRuntimeException(t.getMessage());
		}
		return count;
	}
	
	public Depot findDepotByCode(int depotCode){
		Depot depot = new Depot();
		depot = depotDAO.findDepotByCode(depotCode);
		if (depot != null) {
			List<CargoArea> cargoAreas = cargoAreaDAO.queryAllCargoAreasByDepotCode(depotCode);
			depot.setCargoAreas(cargoAreas);
			for (CargoArea cargoArea : cargoAreas) {
				List<Shelf> shelfs = shelfDAO.queryAllShelfsByAreaCode(cargoArea.getCargoAreaCode());
				for (Shelf shelf : shelfs) {
					List<Locator> locators = locatorDAO.queryAllLocators(String.valueOf(shelf.getShelfCode()));
					cargoArea.setLocators(locators);
					for (Locator locator : locators) {
						locator.setCargoAreaCode(cargoArea.getCargoAreaCode());
					}
				}
			}
		}
		return depot;
	}
}
