package com.medicine.cl;

import java.util.ArrayList;
import java.util.List;

import com.medicine.dao.SupplyAgencyDAO;
import com.medicine.dao.SupplyAgencyJobDAO;
import com.medicine.entities.SupplyAgency;
import com.medicine.entities.SupplyAgencyJob;
import com.medicine.util.MedicineRuntimeException;

public class SupplyAgencyManager extends DAOManager {

	private SupplyAgencyDAO supplyAgencyDAO = new SupplyAgencyDAO();
	private SupplyAgencyJobDAO supplyAgencyJobDAO = new SupplyAgencyJobDAO();
	
	public List<SupplyAgency> findAllSupplyAgencys(String supplyAgencyName, String supplyAgencyType) {
		List<SupplyAgency> supplyAgencies = null;
		supplyAgencies = supplyAgencyDAO.findAllSupplyAgencys(supplyAgencyName, supplyAgencyType);
		for (SupplyAgency supplyAgency : supplyAgencies) {
			List<SupplyAgencyJob> supplyAgencyJobs = new ArrayList<SupplyAgencyJob>();
			supplyAgencyJobs = supplyAgencyJobDAO.findAllSupplyAgencyJobs(supplyAgency.getSupplyAgencyCode());
			supplyAgency.setSupplyAgencyJobs(supplyAgencyJobs);
		}
		return supplyAgencies;
	}
	
	public int addSupplyAgency(SupplyAgency supplyAgency) {
		int count = 0;
		try {
			this.beginTransaction();
			count = supplyAgencyDAO.addSupplyAgency(supplyAgency);
			for (SupplyAgencyJob job : supplyAgency.getSupplyAgencyJobs()) {
				job.setSupplyAgencyCode(supplyAgency.getSupplyAgencyCode());
				supplyAgencyJobDAO.addSupplyAgencyJob(job);
			}
			this.commitTransaction();
		} catch (Exception e) {
			this.rollbackTransaction();
			throw new MedicineRuntimeException(e.getMessage());
		}
		return count;
	}
	
	public int updateSupplyAgency(SupplyAgency supplyAgency) {
		int count = 0;
		try {
			this.beginTransaction();
			count = supplyAgencyDAO.updateSupplyAgency(supplyAgency);
			supplyAgencyJobDAO.deleteSupplyAgencyJobBySupplyAgencyId(supplyAgency.getId());
			for (SupplyAgencyJob job : supplyAgency.getSupplyAgencyJobs()) {
				job.setSupplyAgencyCode(supplyAgency.getSupplyAgencyCode());
				supplyAgencyJobDAO.addSupplyAgencyJob(job);
			}
			this.commitTransaction();
		} catch (Exception e) {
			this.rollbackTransaction();
			throw new MedicineRuntimeException(e.getMessage());
		}
		return count;
	}
	
	public int deleteSupplyAgency(int[] ids) {
		int count = 0;
		try {
			this.beginTransaction();
			for (int id : ids) {
				supplyAgencyJobDAO.deleteSupplyAgencyJobBySupplyAgencyId(id);
				supplyAgencyDAO.deleteSupplyAgency(id);
				count++;
			}
			this.commitTransaction();
		} catch (Exception e) {
			this.rollbackTransaction();
			throw new MedicineRuntimeException(e.getMessage());
		}
		return count;
	}
	
	public SupplyAgency findSupplyAgencyByCode(String supplyAgencyCode){
		SupplyAgency supplyAgency = null;
		supplyAgency = supplyAgencyDAO.findSupplyAgencyByCode(supplyAgencyCode);
		List<SupplyAgencyJob> supplyAgencyJobs = supplyAgencyJobDAO.findAllSupplyAgencyJobs(supplyAgencyCode);
		if (supplyAgency != null) {
			supplyAgency.setSupplyAgencyJobs(supplyAgencyJobs);
		}
		return supplyAgency;
	}
	
	public List<String> findAllSupplyAgencyCodes() {
		List<String> supplyAgencyCodes = new ArrayList<String>();
		supplyAgencyCodes  = supplyAgencyDAO.findAllSupplyAgencyCodes();
		return supplyAgencyCodes;
	}
}
