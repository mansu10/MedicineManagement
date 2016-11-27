package com.medicine.cl;

import java.util.ArrayList;
import java.util.List;

import com.medicine.dao.DemandAgencyDAO;
import com.medicine.entities.DemandAgency;
import com.medicine.util.MedicineRuntimeException;

public class DemandAgencyManager extends DAOManager {

	private DemandAgencyDAO demandAgencyDAO = new DemandAgencyDAO();
	
	public List<DemandAgency> findAllDemandAgencys(String demandAgencyName, String demandAgencyType) {
		List<DemandAgency> demandAgencies = null;
		demandAgencies = demandAgencyDAO.findAllDemandAgencys(demandAgencyName, demandAgencyType);
		return demandAgencies;
	}
	
	public int addDemandAgency(DemandAgency demandAgency) {
		int count = 0;
		try {
			this.beginTransaction();
			count = demandAgencyDAO.addDemandAgency(demandAgency);
			this.commitTransaction();
		} catch (Exception e) {
			this.rollbackTransaction();
			throw new MedicineRuntimeException(e.getMessage());
		}
		return count;
	}
	
	public int updateDemandAgency(DemandAgency demandAgency) {
		int count = 0;
		try {
			this.beginTransaction();
			count = demandAgencyDAO.updateDemandAgency(demandAgency);
			this.commitTransaction();
		} catch (Exception e) {
			this.rollbackTransaction();
			throw new MedicineRuntimeException(e.getMessage());
		}
		return count;
	}
	
	public int deleteDemandAgency(int[] ids) {
		int count = 0;
		try {
			this.beginTransaction();
			for (int id : ids) {
				demandAgencyDAO.deleteDemandAgency(id);
				count++;
			}
			this.commitTransaction();
		} catch (Exception e) {
			this.rollbackTransaction();
			throw new MedicineRuntimeException(e.getMessage());
		}
		return count;
	}
	
	public DemandAgency findDemandAgencyByCode(String demandAgencyCode){
		DemandAgency demandAgency = new DemandAgency();
		demandAgency = demandAgencyDAO.findDemandAgencyByCode(demandAgencyCode);
		return demandAgency;
	}
	
	public List<String> findAllDemandAgencyCodes() {
		List<String> demandAgencyCodes = new ArrayList<String>();
		demandAgencyCodes  = demandAgencyDAO.findAllDemandAgencyCodes();
		return demandAgencyCodes;
	}
}
