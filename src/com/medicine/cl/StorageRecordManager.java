package com.medicine.cl;

import com.medicine.dao.StorageRecordDAO;
import com.medicine.entities.StorageRecord;
import com.medicine.util.MedicineRuntimeException;

public class StorageRecordManager extends DAOManager {

	private StorageRecordDAO storageRecordDAO = new StorageRecordDAO();
	
	public int addStorageRecord(StorageRecord record) {
		int count = 0;
		try {
			this.beginTransaction();
			count = storageRecordDAO.addStorageRecord(record);
			this.commitTransaction();
		} catch (Throwable t) {
			this.rollbackTransaction();
			throw new MedicineRuntimeException(t.getMessage());
		}
		return count;
	}
	
}
