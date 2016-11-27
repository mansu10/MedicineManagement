package com.medicine.cl;

import java.util.ArrayList;
import java.util.List;

import com.medicine.dao.DemandAgencyDAO;
import com.medicine.dao.OrderDAO;
import com.medicine.dao.OrderItemDAO;
import com.medicine.dao.ProductDAO;
import com.medicine.dao.StockDao;
import com.medicine.entities.DemandAgency;
import com.medicine.entities.Order;
import com.medicine.entities.OrderItem;
import com.medicine.entities.OrderMark;
import com.medicine.entities.Product;
import com.medicine.entities.Stock;
import com.medicine.util.MedicineRuntimeException;

public class OrderManager extends DAOManager {

	private OrderDAO orderDAO = new OrderDAO();
	private OrderItemDAO orderItemDAO = new OrderItemDAO();
	private StockDao stockDao = new StockDao();
	private ProductDAO productDAO = new ProductDAO();
	private DemandAgencyDAO demandAgencyDAO = new DemandAgencyDAO();

	/**
	 * 新增订单
	 * 
	 * @author: Tdw
	 * @time: 2016年7月29日下午10:17:29
	 */
	public int addOrder(Order order, List<OrderItem> items) {
		int count = 0;
		try {
			this.beginTransaction();
			orderDAO.addOrder(order);
			for (OrderItem orderItem : items) {
				orderItem.setOrderCode(order.getOrderCode());
				count = orderItemDAO.addOrderItem(orderItem);
				count++;
			}
			count++;
			this.commitTransaction();
		} catch (Throwable t) {
			this.rollbackTransaction();
			throw new MedicineRuntimeException(t.getMessage());
		}
		return count;
	}

	public int updateOrder(Order order, List<OrderItem> items,String itemIds) {
		int count = 0;
		try {
			this.beginTransaction();
			String[] ids = null;
			ids = itemIds.split(",");
			for (int i = 0; i < ids.length; i++) {
				int id = Integer.parseInt(ids[i]);
				orderItemDAO.updateItemMarkById(id, OrderMark.OldOrder.getValue());
				count++;
			}
			orderDAO.updateOrderMarkById(order.getOldOrderId(), OrderMark.OldOrder.getValue());
			orderDAO.addOrder(order);
			for (OrderItem orderItem : items) {
				count = orderItemDAO.addOrderItem(orderItem);
				count++;
			}
			count++;
			this.commitTransaction();
		} catch (Throwable t) {
			this.rollbackTransaction();
			throw new MedicineRuntimeException(t.getMessage());
		}
		return count;
	}

	public List<Order> findOrderByDeliveryTime(String deliveryTimeStr) {
		List<Order> orders = null;
		orders = orderDAO.findOrderByDeliveryTime(deliveryTimeStr);
		return orders;
	}

	public List<Order> findOrderByDto(String orderTimeStart, String orderTimeEnd, String orderStatus,
			String orderProgress) {
		List<Order> orders = null;
		orders = orderDAO.findOrderByDto(orderTimeStart, orderTimeEnd, orderStatus, orderProgress);
		return orders;
	}

	public List<Order> findAllOrders() {
		List<Order> orders = null;
		orders = orderDAO.findAllOrders();
		return orders;
	}
	
	public Order findOrderWithCheckByOrderCode(String orderCode) {
		Order order = findOrderByOrderCode(orderCode);
		List<OrderItem> orderItems = findOrderItemByOrderCode(orderCode);
		if (order != null) {
			order.setOrderItems(orderItems);
			DemandAgency demandAgency = demandAgencyDAO.findDemandAgencyByCode(order.getCustomerCode());
			order.setDemandAgency(demandAgency);
		}
		return order;
	}

	public Order findOrderByOrderCode(String orderCode) {
		Order order = null;
		order = orderDAO.findOrderByOrderCode(orderCode);
		return order;
	}

	public List<OrderItem> findOrderItemByOrderCode(String orderCode) {
		List<OrderItem> items = null;
		items = orderItemDAO.findOrderItemByOrderCode(orderCode);
		if (items != null) {
			for (OrderItem orderItem : items) {
				Stock stock = stockDao.findStockByProdcutCode(orderItem.getProductCode());
				Product product = productDAO.findProductByCode(orderItem.getProductCode());
				if (stock != null) {
					orderItem.setStockNumber(stock.getCaseQuantity());
				}
				if (product != null) {
					orderItem.setOrdinaryName(product.getOrdinaryName());
					orderItem.setUnit(product.getUnit());
					orderItem.setSpecifications(product.getSpecifications());
					orderItem.setPrice(product.getPrice());
					orderItem.setVolume(product.getVolume());
					orderItem.setWeight(product.getWeight());
				}
			}
		}
		return items;
	}
	
	public List<Order> queryAllPassOrders() {
		List<Order> orders = null;
		orders = orderDAO.queryAllPassOrders();
		for (Order order : orders) {
			float totalVolume = 0;
			float totalWeight = 0;
			List<OrderItem> items = orderItemDAO.findOrderItemByOrderCode(order.getOrderCode());
			for (OrderItem orderItem : items) {
				Product product = productDAO.findProductByCode(orderItem.getProductCode());
				totalVolume += orderItem.getProductNumber() * product.getVolume();
				totalWeight += orderItem.getProductNumber() * product.getWeight();
			}
			order.setVolume(totalVolume);
			order.setWeight(totalWeight);
		}
		return orders;
	}
	
	public List<Order> findOrders(String orderCode, String demandAgencyName, String orderTimeStart, String orderTimeEnd){
		List<Order> orders = new ArrayList<Order>();
		orders = orderDAO.findOrders(orderCode, demandAgencyName, orderTimeStart, orderTimeEnd);
		for (Order order : orders) {
			DemandAgency demandAgency = demandAgencyDAO.findDemandAgencyByCode(order.getCustomerCode());
			if (demandAgency != null) {
				order.setDemandAgencyName(demandAgency.getDemandAgencyName());
			}
			int count = orderItemDAO.countFindOrderItemsByCode(order.getOrderCode());
			order.setOrderNumber(count);
		}
		return orders;
	}
	
}
