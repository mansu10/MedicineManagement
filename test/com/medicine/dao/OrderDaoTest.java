package com.daug.dao;

import java.util.List;

import org.junit.Test;

import com.medicine.dao.OrderDAO;
import com.medicine.entities.Order;
import com.medicine.entities.OrderStatus;
import com.medicine.entities.ReceiptType;
import com.medicine.entities.ShipMethod;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class OrderDaoTest {

	OrderDAO orderDao = new OrderDAO();
	
	@Test
	public void testFindAll() {
		JSONObject json = new JSONObject();
		String orderCode = "201601010010";
		List<Order> orders = orderDao.findAll(orderCode);
		JSONArray jsonArray = new JSONArray();
        for (Order order : orders) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", order.getId());
			jsonObject.put("OrderCode", order.getOrderCode());
			jsonObject.put("CustomerCode", order.getCustomerCode());
			jsonObject.put("ProductId", order.getProductIds());
			jsonObject.put("OrderType", order.getOrderType());
			jsonObject.put("OrderTime", order.getOrderTime()==null?"":order.getOrderTime().toString());
			jsonObject.put("DeliveryTime", order.getDeliveryTime()==null?"":order.getDeliveryTime().toString());
			jsonObject.put("OrderStatus", OrderStatus.valueOf(order.getOrderStatus()).toString());
			jsonObject.put("Receiver", order.getReceiver());
			jsonObject.put("ReceiptAddress", order.getReceiptAddress());
			jsonObject.put("Tel", order.getTel());
			jsonObject.put("ReceiptType", ReceiptType.valueOf(order.getReceiptType()).toString());
			jsonObject.put("InvoiceMessage", order.getInvoiceMessage());
			jsonObject.put("ShipMethod", ShipMethod.valueOf(order.getShipMethod()).toString());
			jsonObject.put("PayMethod", order.getPayMethod());
			jsonObject.put("ShipTime", order.getShipTime()==null?"":order.getShipTime().toString());
			jsonObject.put("Level", order.getLevel());
			jsonArray.add(jsonObject);
		}	
        if (jsonArray.toString() != null) {
        	json.put("code", 0);
        	json.put("order", jsonArray.toString());
		}else {
			json.put("code", -1);
		}
		System.out.println(json);
	}

}
