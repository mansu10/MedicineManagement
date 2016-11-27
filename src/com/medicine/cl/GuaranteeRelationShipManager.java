package com.medicine.cl;

import java.util.List;

import com.medicine.dao.DemandAgencyDAO;
import com.medicine.dao.GuaranteeRelationShipDAO;
import com.medicine.entities.DemandAgency;
import com.medicine.entities.GuaranteeRelationShip;
import com.medicine.util.MedicineRuntimeException;

public class GuaranteeRelationShipManager extends DAOManager {

	private GuaranteeRelationShipDAO guaranteeRelationShipDAO = new GuaranteeRelationShipDAO();
	private DemandAgencyDAO demandAgencyDAO = new DemandAgencyDAO();

	public List<GuaranteeRelationShip> findAllGuaranteeRelationShips(String supplyAgencyCode, String demandAgencyCode) {
		List<GuaranteeRelationShip> guaranteeRelationShips = null;
		guaranteeRelationShips = guaranteeRelationShipDAO.findAllGuaranteeRelationShips(demandAgencyCode,
				supplyAgencyCode);
		return guaranteeRelationShips;
	}

	public List<GuaranteeRelationShip> findAllGuaranteeRelationShipsBySupplyAgencyCode(String supplyAgencyCode) {
		List<GuaranteeRelationShip> guaranteeRelationShips = null;
		guaranteeRelationShips = guaranteeRelationShipDAO.findAllGuaranteeRelationShips(null, supplyAgencyCode);
		for (GuaranteeRelationShip guaranteeRelationShip : guaranteeRelationShips) {
			DemandAgency demandAgency = demandAgencyDAO
					.findDemandAgencyByCode(guaranteeRelationShip.getDemandAgencyCode());
			guaranteeRelationShip.setDemandAgency(demandAgency);
		}
		return guaranteeRelationShips;
	}

	public int addGuaranteeRelationShip(GuaranteeRelationShip guaranteeRelationShip) {
		int count = 0;
		try {
			this.beginTransaction();
			count = guaranteeRelationShipDAO.addGuaranteeRelationShip(guaranteeRelationShip);
			this.commitTransaction();
		} catch (Exception e) {
			this.rollbackTransaction();
			throw new MedicineRuntimeException(e.getMessage());
		}
		return count;
	}

	public int updateGuaranteeRelationShip(GuaranteeRelationShip guaranteeRelationShip) {
		int count = 0;
		try {
			this.beginTransaction();
			count = guaranteeRelationShipDAO.updateGuaranteeRelationShip(guaranteeRelationShip.getTransportRoute(),
					guaranteeRelationShip.getDemandAgencyCode(), guaranteeRelationShip.getSupplyAgencyCode());
			this.commitTransaction();
		} catch (Exception e) {
			this.rollbackTransaction();
			throw new MedicineRuntimeException(e.getMessage());
		}
		return count;
	}

	public int deleteGuaranteeRelationShip(int[] ids) {
		int count = 0;
		try {
			this.beginTransaction();
			for (int id : ids) {
				guaranteeRelationShipDAO.deleteGuaranteeRelationShip(id);
				count++;
			}
			this.commitTransaction();
		} catch (Exception e) {
			this.rollbackTransaction();
			throw new MedicineRuntimeException(e.getMessage());
		}
		return count;
	}

}
