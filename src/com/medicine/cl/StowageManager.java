package com.medicine.cl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.medicine.dao.CarDAO;
import com.medicine.dao.InvoiceDAO;
import com.medicine.dao.OrderDAO;
import com.medicine.dao.StowageDAO;
import com.medicine.dao.StowageItemDAO;
import com.medicine.entities.Car;
import com.medicine.entities.Invoice;
import com.medicine.entities.Order;
import com.medicine.entities.Stowage;
import com.medicine.entities.StowageItem;
import com.medicine.util.MedicineRuntimeException;

public class StowageManager extends DAOManager {

	private StowageDAO stowageDAO = new StowageDAO();
	private StowageItemDAO stowageItemDAO = new StowageItemDAO();
	private CarDAO carDAO = new CarDAO();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
	private OrderDAO orderDAO = new OrderDAO();
	private InvoiceDAO invoiceDAO  = new InvoiceDAO();
	
	public int addStowage(Stowage stowage) {
		int count = 0;
		try {
			this.beginTransaction();
			String stowageCode =  sdf.format(new Date());
			stowage.setStowageCode(stowageCode);
			count = stowageDAO.addStowage(stowage);
			StowageItem stowageItem = new StowageItem();
			stowageItem.setStowageCode(stowageCode);
			stowageItem.setInvoiceCode(stowage.getInvoiceCode());
			stowageItemDAO.addStowageItem(stowageItem);
			this.commitTransaction();
		} catch (Throwable t) {
			this.rollbackTransaction();
			throw new MedicineRuntimeException(t.getMessage());
		}
		return count;
	}
	
	public List<Stowage> findAllStowages(String stowageCode, String carCode) {
		List<Stowage> stowages = new ArrayList<Stowage>();
		stowages = stowageDAO.findAllStowages(stowageCode, carCode);
		for (Stowage stowage : stowages) {
				Car car = carDAO.findCarByCode(stowage.getCarCode());
				int count = stowageItemDAO.countStowageItemByCode(stowage.getStowageCode());
				stowage.setCount(count);
				List<Order> orders = orderDAO.findOrderByStowageCode(stowage.getStowageCode());
				if (orders != null && orders.size() > 0) {
					stowage.setOrder(orders.get(0));
				}
				stowage.setCar(car);
		}
		return stowages;
	}
	
	public int deleteStowage(int[] ids) {
		int count = 0;
		try {
			this.beginTransaction();
			for (int id : ids) {
				stowageDAO.deleteStowageById(id);
				count++;
			}
			this.commitTransaction();
		} catch (Exception e) {
			this.rollbackTransaction();
			throw new MedicineRuntimeException(e.getMessage());
		}
		return count;
	}
	
	public Stowage findStowageByCode(String stowageCode) {
		Stowage stowage = new Stowage();
		stowage = stowageDAO.findStowageByCode(stowageCode);
		List<StowageItem> stowageItems = stowageItemDAO.findStowageItemsByCode(stowageCode);
		for (StowageItem stowageItem : stowageItems) {
			Invoice invoice = invoiceDAO.findInvoiceByCode(stowageItem.getInvoiceCode());
			if (invoice != null) {
				stowageItem.setReceiptAddress(invoice.getReceiptAddress());
			}
		}
		if (stowage != null) {
			stowage.setStowageItems(stowageItems);
		}
		return stowage;
	}
	
	public int updateStowageItems(List<StowageItem> stowageItems) {
		int count = 0;
		try {
			this.beginTransaction();
			for (StowageItem stowageItem : stowageItems) {
				stowageItemDAO.updateStowageItem(stowageItem);
				count++;
			}
			this.commitTransaction();
		} catch (Exception e) {
			this.rollbackTransaction();
			throw new MedicineRuntimeException(e.getMessage());
		}
		return count;
	}
	
	public int updateStowage(Stowage stowage) {
		int count = 0;
		try {
			this.beginTransaction();
			count = stowageDAO.updateStowage(stowage);
			this.commitTransaction();
		} catch (Exception e) {
			this.rollbackTransaction();
			throw new MedicineRuntimeException(e.getMessage());
		}
		return count;
	}
	
	
}
