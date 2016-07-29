package com.medicine.cl;

import java.util.List;

import com.medicine.dao.OrderDAO;
import com.medicine.dao.OrderItemDAO;
import com.medicine.entities.Order;
import com.medicine.entities.OrderItem;
import com.medicine.util.MedicineRuntimeException;

public class OrderManager extends DAOManager {

	private OrderDAO orderDAO = new OrderDAO();
	private OrderItemDAO orderItemDAO = new OrderItemDAO();
	
	/**
	 * 新增订单
	 * @author: Tdw
	 * @time: 2016年7月29日下午10:17:29
	 */
	public int addOrder(Order order,List<OrderItem> items){
		int count = 0;
		try {
			this.beginTransaction();
			for (OrderItem orderItem : items) {
				count = orderItemDAO.addOrderItem(orderItem);
				count ++;
			}
			orderDAO.addOrder(order);
			count ++;
			this.commitTransaction();
		}catch(Throwable t){
			this.rollbackTransaction();
			throw new MedicineRuntimeException(t.getMessage());
		}
		return count;
	}
	
}
