package com.medicine.cl;

import java.util.List;

import com.medicine.dao.LocatorDAO;
import com.medicine.dao.ShelfDAO;
import com.medicine.entities.Locator;
import com.medicine.entities.Shelf;
import com.medicine.util.MedicineRuntimeException;

public class ShelfManger extends DAOManager {

	private ShelfDAO shelfDao = new ShelfDAO();
	private LocatorDAO locatorDAO = new LocatorDAO();
	
	public int addShelf(List<Shelf> shelfs){
		int count = 0;
		try {
			this.beginTransaction();
			for (Shelf shelf : shelfs) {
				for (int i = 0; i < shelf.getShelfCount(); i++) {
					int shelfCode = shelfDao.addShelf(shelf);
					if (shelfCode != 0) {
						Locator locator = new Locator();
						locator.setShelfNumber(String.valueOf(shelfCode));
						for (int j = 1; j <= shelf.getShelfLayer(); j++) {
							locator.setLayerNumber((j));
							locator.setShelfNumber(String.valueOf(shelfCode));
							locator.setLocatorCode(String.valueOf(shelfCode)+String.valueOf(j));
							locatorDAO.addLocator(locator);
							count++;
						}
					}
				}
			}
			this.commitTransaction();
		} catch (Throwable t) {
			this.rollbackTransaction();
			throw new MedicineRuntimeException(t.getMessage());
		}
		return count;
	}
	
	public List<Shelf> queryAllShelfs(int depotCode){
		List<Shelf> shelfs = null;
		shelfs = shelfDao.queryAllShelfs(depotCode);
		return shelfs;
	}
	
	public int updateShelf(List<Shelf> shelfs){
		int count = 0;
		try {
			this.beginTransaction();
			for (Shelf shelf : shelfs) {
				shelfDao.updateShelf(shelf);
				count++;
			}
			this.commitTransaction();
		} catch (Throwable t) {
			this.rollbackTransaction();
			throw new MedicineRuntimeException(t.getMessage());
		}
		return count;
	}
}
