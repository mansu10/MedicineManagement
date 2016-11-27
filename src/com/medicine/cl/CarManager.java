package com.medicine.cl;

import java.util.ArrayList;
import java.util.List;

import com.medicine.dao.CarDAO;
import com.medicine.dao.CarStatusDAO;
import com.medicine.dao.StowageDAO;
import com.medicine.dao.StowageItemDAO;
import com.medicine.entities.Car;
import com.medicine.entities.CarRecord;
import com.medicine.entities.CarStatus;
import com.medicine.util.MedicineRuntimeException;

public class CarManager extends DAOManager {
	private static final String CAR_STATUS_FC = "发车中";
	private static final String CAR_STATUS_HSWB = "回收完毕";

	private CarDAO carDAO = new CarDAO();
	private CarStatusDAO carStatusDAO = new CarStatusDAO();
	private StowageDAO stowageDAO = new StowageDAO();
	private StowageItemDAO stowageItemDAO = new StowageItemDAO();

	public List<Car> queryAllCars(String numberOrCode, String agencyCode) {
		List<Car> cars = null;
		try {
			cars = carDAO.findAllCarsByNumberOrCode(numberOrCode, agencyCode);
			for (Car car : cars) {
				List<CarStatus> carStatus = carStatusDAO.findCarStatusByCode(car.getCarCode());
				if (carStatus != null && carStatus.size() > 0) {
					car.setCarStatusStr(carStatus.get(0).getCarStatus());
				}
				List<CarRecord> carRecords = queryCarRecordByCode(car.getCarCode());
				car.setCarRecords(carRecords);
			}
		} catch (Exception e) {
			throw new MedicineRuntimeException(e.getMessage());
		}
		return cars;
	}

	public List<Car> queryAllCars() {
		List<Car> cars = null;
		try {
			cars = carDAO.findAllCars();
			// for (Car car : cars) {
			// List<CarStatus> carStatus =
			// carStatusDAO.findCarStatusByCode(car.getCarCode());
			// if (carStatus != null && carStatus.size() > 0) {
			// car.setCarStatusStr(carStatus.get(0).getCarStatus());
			// }
			// List<CarRecord> carRecords =
			// queryCarRecordByCode(car.getCarCode());
			// car.setCarRecords(carRecords);
			// }
		} catch (Exception e) {
			throw new MedicineRuntimeException(e.getMessage());
		}
		return cars;
	}

	public int addCar(Car car) {
		int count = 0;
		try {
			this.beginTransaction();
			count = carDAO.addCar(car);
			this.commitTransaction();
		} catch (Throwable t) {
			this.rollbackTransaction();
			throw new MedicineRuntimeException(t.getMessage());
		}
		return count;
	}

	public int deleteCarById(int[] ids) {
		int count = 0;
		try {
			this.beginTransaction();
			for (int id : ids) {
				carDAO.deleteCarById(id);
				count++;
			}
			this.commitTransaction();
		} catch (Throwable t) {
			this.rollbackTransaction();
			throw new MedicineRuntimeException(t.getMessage());
		}
		return count;
	}

	public int updateCar(Car car) {
		int count = 0;
		try {
			this.beginTransaction();
			count = carDAO.updateCar(car);
			this.commitTransaction();
		} catch (Throwable t) {
			this.rollbackTransaction();
			throw new MedicineRuntimeException(t.getMessage());
		}
		return count;
	}

	public int addOrdersToCar(String carOrders, int carId) {
		int count = 0;
		try {
			this.beginTransaction();
			count = carDAO.addOrdersToCar(carOrders, carId);
			this.commitTransaction();
		} catch (Throwable t) {
			this.rollbackTransaction();
			throw new MedicineRuntimeException(t.getMessage());
		}
		return count;
	}

	public List<CarRecord> queryCarRecordByCode(String carCode) {
		List<CarRecord> carRecords = new ArrayList<CarRecord>();
		try {
			List<CarStatus> carStatus = carStatusDAO.findCarStatusByCode(carCode);
			List<String> stowageCodes = new ArrayList<String>();
			for (CarStatus carStatus2 : carStatus) {
				stowageCodes.add(carStatus2.getMissionCode());
			}
			for (String stowageCode : stowageCodes) {
				CarRecord carRecord = new CarRecord();
				CarStatus status = carStatusDAO.findCarStatusByStatus(CAR_STATUS_FC, carCode, stowageCode);
				carRecord.setStowageCode(stowageCode);
				carRecord.setStartTime(status.getStartTime());
				status = carStatusDAO.findCarStatusByStatus(CAR_STATUS_HSWB, carCode, stowageCode);
				carRecord.setEndTime(status.getStartTime());
				carRecord.setTransportRoute(stowageDAO.findStowageByCode(stowageCode).getTransportRoute());
				carRecord.setCountInvoiceCode(stowageItemDAO.countStowageItemByCode(stowageCode));
				carRecords.add(carRecord);
			}
		} catch (Exception e) {
			throw new MedicineRuntimeException(e.getMessage());
		}
		return carRecords;
	}

	public List<CarStatus> queryAllCarStatus(String agencyCode) {
		List<CarStatus> list = new ArrayList<CarStatus>();
		try {
			List<Car> cars = carDAO.findAllCarsByNumberOrCode(null, agencyCode);
			for (Car car : cars) {
				CarStatus carStatus = carStatusDAO.findCarStatusByCode2(car.getCarCode());
				list.add(carStatus);
			}
		} catch (Exception e) {
			throw new MedicineRuntimeException(e.getMessage());
		}
		return list;
	}

	public List<String> findStowageCodesByCarCode(String carStatus, String carCode) {
		List<String> stowageCodes = new ArrayList<String>();
		List<CarStatus> carStatuss = carStatusDAO.findCarStatusByStatusAndCode(carStatus, carCode);
		for (CarStatus status : carStatuss) {
			stowageCodes.add(status.getMissionCode());
		}
		return stowageCodes;
	}

	public int updateCarStatusByCode(String carStatus, String carCode) {
		int count = 0;
		try {
			this.beginTransaction();
			carStatusDAO.updateCarStatusByCode(carStatus, carCode);
			count++;
			this.commitTransaction();
		} catch (Exception e) {
			this.rollbackTransaction();
			throw new MedicineRuntimeException(e.getMessage());
		}
		return count;
	}

	public int updateInstructionByCode(String instruction, String carCode) {
		int count = 0;
		try {
			this.beginTransaction();
			carStatusDAO.updateInstructionByCode(instruction, carCode);
			count++;
			this.commitTransaction();
		} catch (Exception e) {
			this.rollbackTransaction();
			throw new MedicineRuntimeException(e.getMessage());
		}
		return count;
	}

	public int updateOperatorByCode(String operator, String stowageCode) {
		int count = 0;
		try {
			this.beginTransaction();
			carStatusDAO.updateOperatorByCode(operator, stowageCode);
			count++;
			this.commitTransaction();
		} catch (Exception e) {
			this.rollbackTransaction();
			throw new MedicineRuntimeException(e.getMessage());
		}
		return count;
	}
}
