package com.medicine.cl;

import java.util.ArrayList;
import java.util.List;

import com.medicine.dao.CargoAreaDAO;
import com.medicine.entities.CargoArea;
import com.medicine.util.MedicineRuntimeException;

public class CargoAreaManager extends DAOManager{

	private CargoAreaDAO cargoAreaDAO = new CargoAreaDAO();
	
	public int addCargoArea(List<CargoArea> cargoAreas){
		int count = 0;
		try {
			this.beginTransaction();
			for (CargoArea cargoArea : cargoAreas) {
				cargoAreaDAO.addCargoArea(cargoArea);
				count++;
			}
			this.commitTransaction();
		}catch(Throwable t){
			this.rollbackTransaction();
			throw new MedicineRuntimeException(t.getMessage());
		}
		return count;
	}
	
	public List<String> findCargoAreaCodesByDepotCode(int depotCode){
		List<String> codes = new ArrayList<>();
		codes = cargoAreaDAO.queryCargoAreaCodesByDepotCode(depotCode);
		return codes;
	}
	
	public int updateCargoArea(List<CargoArea> cargoAreas){
		int count = 0;
		try {
			this.beginTransaction();
			for (CargoArea cargoArea : cargoAreas) {
				CargoArea area = cargoAreaDAO.queryCargoAreaByDepotCodeAndCargoAreaCode(cargoArea.getDepotCode(), cargoArea.getCargoAreaCode());
				if (area != null) {
					cargoAreaDAO.updateCargoArea(cargoArea);
				}else {
					cargoAreaDAO.addCargoArea(cargoArea);
				}
				count++;
			}
			this.commitTransaction();
		}catch(Throwable t){
			this.rollbackTransaction();
			throw new MedicineRuntimeException(t.getMessage());
		}
		return count;
	}
}
