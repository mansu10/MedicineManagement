package com.medicine.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.medicine.cl.OrderManager;
import com.medicine.dao.OrderDAO;
import com.medicine.entities.Order;
import com.medicine.entities.OrderItem;
import com.medicine.entities.OrderStatus;
import com.medicine.entities.ReceiptType;
import com.medicine.entities.ShipMethod;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private OrderManager orderManager = new OrderManager();

	public OrderServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String method = request.getParameter("method");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		try {
			if (StringUtils.equals(method, "queryOrder")) {
				queryOrder(request, response);
			} 
			else if (StringUtils.equals(method, "addOrder")) {
				addOrder(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("exception", e.getMessage());
		}
	}

	private void addOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		PrintWriter out = response.getWriter();
		Order order = new Order();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		try {
			String customerCode = request.getParameter("customerCode");
			String customerName = request.getParameter("customerName");
			String orderTime = request.getParameter("orderTime");
			String deliveryTime = request.getParameter("deliveryTime");
			String receiptAddress = request.getParameter("receiptAddress");
			String receiver = request.getParameter("receiver");
			String tel = request.getParameter("tel");
			String memo = request.getParameter("memo");
			String productIds = request.getParameter("productIds");
			if (productIds == null) {
				return;
			}
			//将jsonArray转换成对象
			jsonArray = JSONArray.fromObject(productIds);
			List<OrderItem> items = new ArrayList<OrderItem>();
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				items.add((OrderItem) JSONObject.toBean(jsonObject, OrderItem.class));
			}
			order.setOrderCode(sdf.format(new Date()));
			order.setCustomerCode(customerCode);
			order.setCustomerName(customerName);
			order.setOrderTime(sdf.parse(orderTime));
			order.setDeliveryTime(sdf.parse(deliveryTime));
			order.setReceiptAddress(receiptAddress);
			order.setReceiver(receiver);
			order.setTel(tel);
			order.setProductIds(productIds);
			order.setMemo(memo);
			int count = orderManager.addOrder(order,items);
			if (count != 0) {
				json.put("code", 0);
			}
		} catch (Exception e) {
			String error = "addOrder Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}

	private void queryOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		JSONObject json = new JSONObject();
//		PrintWriter out = response.getWriter();
//		JSONArray jsonArray = new JSONArray();
//		String orderCode = request.getParameter("orderCode");
//		String deliveryTimeStart = request.getParameter("deliveryTimeStart");
//		String deliveryTimeEnd = request.getParameter("deliveryTimeStartEnd");
//		String shipMethod = request.getParameter("shipMethod");
//		String orderStatus = request.getParameter("orderStatus");
//		try {
//			List<Order> orders = orderManager.findAll(orderCode);
//			for (Order order : orders) {
//				JSONObject jsonObject = new JSONObject();
//				jsonObject.put("id", order.getId());
//				jsonObject.put("OrderCode", order.getOrderCode());
//				jsonObject.put("CustomerCode", order.getCustomerCode());
//				jsonObject.put("ProductId", order.getProductIds());
//				jsonObject.put("OrderType", order.getOrderType());
//				jsonObject.put("OrderTime", order.getOrderTime().toString());
//				jsonObject.put("DeliveryTime", order.getDeliveryTime().toString());
//				jsonObject.put("OrderStatus", OrderStatus.valueOf(order.getOrderStatus()).toString());
//				jsonObject.put("Receiver", order.getReceiver());
//				jsonObject.put("ReceiptAddress", order.getReceiptAddress());
//				jsonObject.put("Tel", order.getTel());
//				jsonObject.put("ReceiptType", ReceiptType.valueOf(order.getReceiptType()).toString());
//				jsonObject.put("InvoiceMessage", order.getInvoiceMessage());
//				jsonObject.put("ShipMethod", ShipMethod.valueOf(order.getShipMethod()).toString());
//				jsonObject.put("PayMethod", order.getPayMethod());
//				jsonObject.put("ShipTime", order.getShipTime() == null ? "" : order.getShipTime().toString());
//				jsonObject.put("Level", order.getLevel());
//				jsonArray.add(jsonObject);
//				json.put("code", 0);
//				json.put("order", jsonArray.toString());
//			}
//		} catch (Exception e) {
//			String error = "findOrder Fail error:" + e.getMessage();
//			json.put("code", -1);
//			json.put("error", error);
//		}
//		out.write(json.toString());
	}
}
