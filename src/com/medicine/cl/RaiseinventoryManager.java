package com.medicine.cl;

import com.medicine.dao.RaiseInventoryDAO;
import com.medicine.entities.RaiseInventory;
import com.medicine.util.MedicineRuntimeException;

public class RaiseinventoryManager extends DAOManager {

	private RaiseInventoryDAO raiseInventoryDAO = new RaiseInventoryDAO();

	public int addRaiseInventory(RaiseInventory inventory) {
		int count = 0;
		try {
			this.beginTransaction();
			raiseInventoryDAO.addRaiseInventory(inventory);
			count++;
			this.commitTransaction();
		} catch (Throwable t) {
			this.rollbackTransaction();
			throw new MedicineRuntimeException(t.getMessage());
		}
		return count;
	}

}
