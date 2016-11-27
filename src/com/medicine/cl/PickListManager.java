package com.medicine.cl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.medicine.dao.OrderItemDAO;
import com.medicine.dao.PickListDAO;
import com.medicine.dao.PickListItemDAO;
import com.medicine.dao.ProductDAO;
import com.medicine.dao.ReceiptAcceptanceRecordDAO;
import com.medicine.entities.OrderItem;
import com.medicine.entities.PickList;
import com.medicine.entities.PickListItem;
import com.medicine.entities.Product;
import com.medicine.util.MedicineRuntimeException;

public class PickListManager extends DAOManager {

	private OrderItemDAO orderItemDAO = new OrderItemDAO();
	private ProductDAO productDAO = new ProductDAO();
	private ReceiptAcceptanceRecordDAO receiptAcceptanceRecordDAO = new ReceiptAcceptanceRecordDAO();
	private PickListDAO pickListDAO = new PickListDAO();
	private PickListItemDAO pickListItemDAO = new PickListItemDAO();
	
	public List<PickList> findPickListsByOrderCode(String orderCode) {
		List<PickList> pickLists = new ArrayList<PickList>();
		List<OrderItem> orderItems = orderItemDAO.findOrderItemByOrderCode(orderCode);
		Set<Integer> productCodes = new HashSet<Integer>();
		Set<String> receiptDepots = new HashSet<String>();
		List<Product> products = new ArrayList<>();
		for (OrderItem orderItem : orderItems) {
			productCodes.add(orderItem.getProductCode());
		}
		for (Integer productCode : productCodes) {
			Product product = productDAO.findProductByCode(productCode);
			String receiptDepot = receiptAcceptanceRecordDAO.findRecriptDepotByProductCode(productCode);
			product.setReceiptDepot(receiptDepot);
			products.add(product);
			receiptDepots.add(receiptDepot);
		}
		// int count = receiptDepots.size();
		Iterator<String> iterator = receiptDepots.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			PickList pickList = new PickList();
			pickList.setOrderCode(orderCode);
			pickList.setPickListCode(orderCode + "0" + (i + 1));
			String receiptDepot = iterator.next();
			pickList.setPickListDepot(receiptDepot);
			List<Product> products2 = new ArrayList<Product>();
			for (Product product : products) {
				if (receiptDepot.equals(product.getReceiptDepot())) {
					products2.add(product);
				}
			}
			pickList.setProducts(products2);
			pickLists.add(pickList);
			i++;
		}
		List<PickList> existPickLists = pickListDAO.findPickListByOrderCode(orderCode);
		try {
			this.beginTransaction();
			if (existPickLists == null || existPickLists.isEmpty()) {
				List<PickListItem> items = new ArrayList<PickListItem>();
				for (PickList pickList : pickLists) {
					pickListDAO.addPickList(pickList);
					for (Product product : pickList.getProducts()) {
						PickListItem item = new PickListItem();
						item.setPickListCode(pickList.getPickListCode());
						item.setProductCode(String.valueOf(product.getProductCode()));
						items.add(item);
					}
				}
				for (PickListItem pickListItem : items) {
					pickListItemDAO.addPickListItem(pickListItem);
				}
			}
			this.commitTransaction();
		} catch (Throwable t) {
			this.rollbackTransaction();
			throw new MedicineRuntimeException(t.getMessage());
		}
		return pickLists;
	}
	
	public PickList findPickListByOrderCode(String orderCode) {
		PickList pickList = null;
		List<PickList> existPickLists = pickListDAO.findPickListByOrderCode(orderCode);
		if (!existPickLists.isEmpty()) {
			pickList = existPickLists.get(0);
			List<PickListItem> pickListItems = pickListItemDAO.findProductCodesByOrderCode(orderCode);
//			List<Product> products = new ArrayList<>();
			for (PickListItem pickListItem : pickListItems) {
				Product product = productDAO.findProductByCode(Integer.parseInt(pickListItem.getProductCode()));
				pickListItem.setProduct(product);
			}
			pickList.setPickListItems(pickListItems);
		}
		return pickList;
	}
	
	public int updatePickListItem(String orderCode, List<PickListItem> pickListItems){
		int count = 0;
		for (PickListItem pickListItem : pickListItems) {
			pickListItemDAO.updatePickListItem(orderCode, pickListItem);
			count++;
		}
		return count;
	}
	
	public int checkOrder(String orderCode) {
		int count = 0;
		List<String> pickListCodes = pickListDAO.findPickListCodesByOrderCode(orderCode);
		for (String pickListCode : pickListCodes) {
			pickListDAO.updateShipTime(pickListCode);
			count++;
		}
		return count;
	}
}
