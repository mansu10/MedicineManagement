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
import com.medicine.entities.Order;
import com.medicine.entities.OrderItem;
import com.medicine.entities.OrderProgress;
import com.medicine.entities.OrderStatus;
import com.medicine.entities.OutProcessing;
import com.medicine.entities.ReceiptType;
import com.medicine.entities.ShipLevel;
import com.medicine.entities.ShipMethod;
import com.medicine.util.JSONUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private OrderManager orderManager = new OrderManager();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddhhmmss");

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		try {
			if (StringUtils.equals(method, "queryAllOrders")) {
				queryAllOrders(request, response);
			} else if (StringUtils.equals(method, "addOrder")) {
				addOrder(request, response);
			} else if (StringUtils.equals(method, "queryOrderAndItem")) {
				queryOrderAndItem(request, response);
			} else if (StringUtils.equals(method, "updateOrderPass")) {
				updateOrder(request, response);
			} else if (StringUtils.equals(method, "queryAllPassOrders")) {
				queryAllPassOrders(request, response);
			}
			else if (StringUtils.equals(method, "queryAllOrdersWithDemandAgency")) {
				queryAllOrdersWithDemandAgency(request, response);
			}
			else if (StringUtils.equals(method, "findOrderWithCheckByOrderCode")) {
				findOrderWithCheckByOrderCode(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("exception", e.getMessage());
		}
	}
	
	
	private void findOrderWithCheckByOrderCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		PrintWriter out = response.getWriter();
		String orderCode = request.getParameter("orderCode");
		try {
			Order order = orderManager.findOrderWithCheckByOrderCode(orderCode);
			json.put("code", 0);
			json.put("order", order);
		} catch (Exception e) {
			String error = "findOrderWithCheckByOrderCode Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}
	
	private void queryAllOrdersWithDemandAgency(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		PrintWriter out = response.getWriter();
		List<Order> orders = new ArrayList<Order>();
		String orderCode = request.getParameter("orderCode");
		String demandAgencyName = request.getParameter("demandAgencyName");
		String orderTimeStart = request.getParameter("orderTimeStart");
		String orderTimeEnd = request.getParameter("orderTimeEnd");
		try {
			orders = orderManager.findOrders(orderCode, demandAgencyName, orderTimeStart, orderTimeEnd);
			json.put("code", 0);
			json.put("orders", orders);
		} catch (Exception e) {
			String error = "queryAllOrdersWithDemandAgency Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}
	
	private void updateOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		PrintWriter out = response.getWriter();
		try {
			String oldOrderIdStr = request.getParameter("oldOrderId");
			String orderCode = request.getParameter("orderCode");
			String itemIds = request.getParameter("itemIds");
			String orderTimeStr = request.getParameter("orderTime");
			String deliveryTimeStr = request.getParameter("deliveryTime");
			String customerCode = request.getParameter("customerCode");
			String customerName = request.getParameter("customerName");
			String receiptAddress = request.getParameter("receiptAddress");
			String receiver = request.getParameter("receiver");
			String tel = request.getParameter("tel");
			String memo = request.getParameter("memo");
			String orderItems = request.getParameter("orderItems");
			String intendDeliveryTimeStr = request.getParameter("intendDeliveryTime");
			String packageMethodStr = request.getParameter("packageMethod");
//			String shipMethodStr = request.getParameter("shipMethod");
			String outProcessingStr = request.getParameter("outProcessing");
			String orderStatusStr = request.getParameter("orderStatus");
			String levelStr = request.getParameter("level");
			if (checkParameter(orderTimeStr, deliveryTimeStr, intendDeliveryTimeStr, oldOrderIdStr, orderStatusStr,
					packageMethodStr)) {
				Date orderTime = sdf.parse(orderTimeStr);
				Date deliveryTime = sdf.parse(deliveryTimeStr);
				Date intendDeliveryTime = sdf.parse(intendDeliveryTimeStr);
				int oldOrderId = Integer.valueOf(oldOrderIdStr);
				int orderStatus = Integer.valueOf(orderStatusStr);
//				int shipMethod = Integer.valueOf(shipMethodStr);
				int packageMethod = Integer.valueOf(packageMethodStr);
				int level = ShipLevel.textOf(levelStr).getValue();
				int outProcessing = OutProcessing.textOf(outProcessingStr).getValue();
				Order order = new Order();
				order.setOldOrderId(oldOrderId);
				order.setOrderCode(orderCode);
				order.setItemIds(itemIds);
				order.setOrderTime(orderTime);
				order.setDeliveryTime(deliveryTime);
				order.setCustomerCode(customerCode);
				order.setCustomerName(customerName);
				order.setReceiptAddress(receiptAddress);
				order.setTel(tel);
				order.setReceiver(receiver);
				order.setIntendDeliveryTime(intendDeliveryTime);
				order.setMemo(memo);
				order.setOrderStatus(orderStatus);
				order.setOutProcessing(outProcessing);
//				order.setShipMethod(shipMethod);
				order.setPackageMethod(packageMethod);
				order.setLevel(level);
				if (orderItems == null) {
					return;
				}
				// 将jsonArray转换成对象
				jsonArray = JSONArray.fromObject(orderItems);
				List<OrderItem> items = new ArrayList<OrderItem>();
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					items.add((OrderItem) JSONObject.toBean(jsonObject, OrderItem.class));
				}
				int count = orderManager.updateOrder(order, items, itemIds);
				if (count != 0) {
					json.put("code", 0);
				}
			} else {
				json.put("code", 0);
				json.put("error", "审核字段不全！");
			}
		} catch (Exception e) {
			String error = "updateOrderStatus Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}

	private void queryOrderAndItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		PrintWriter out = response.getWriter();
		String orderCode = request.getParameter("orderCode");
		Order order = null;
		List<OrderItem> items = new ArrayList<OrderItem>();
		try {
			items = orderManager.findOrderItemByOrderCode(orderCode);
			order = orderManager.findOrderByOrderCode(orderCode);
			if (items != null) {
				order.setOrderItems(items);
			}
			String orderAndItem = JSONUtil.serializeObject(order);
			json.put("code", 0);
			json.put("order", orderAndItem);
		} catch (Exception e) {
			String error = "queryOrderAndItem Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}

	private void queryAllPassOrders(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		PrintWriter out = response.getWriter();
		List<Order> orders = new ArrayList<Order>();
		try {
			orders = orderManager.queryAllPassOrders();
			json.put("code", 0);
			json.put("orders", orders);
		} catch (Exception e) {
			String error = "queryAllPassOrders Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}

	/**
	 * 订单查询与审核功能
	 * 
	 * @author: Tdw
	 * @time: 2016年7月31日下午8:38:04
	 */
	private void queryAllOrders(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		PrintWriter out = response.getWriter();

		List<Order> orders = new ArrayList<Order>();
		try {
			orders = orderManager.findAllOrders();
			for (Order order : orders) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", order.getId());
				jsonObject.put("orderCode", order.getOrderCode());
				jsonObject.put("customerCode", order.getCustomerCode());
				jsonObject.put("orderType", order.getOrderType());
				jsonObject.put("orderTime", sdf.format(order.getOrderTime()));
				jsonObject.put("deliveryTime", sdf.format(order.getDeliveryTime()));
				jsonObject.put("orderStatus", OrderStatus.valueOf(order.getOrderStatus()).toString());
				jsonObject.put("receiver", order.getReceiver());
				jsonObject.put("receiptAddress", order.getReceiptAddress());
				jsonObject.put("tel", order.getTel());
				jsonObject.put("receiptType", ReceiptType.valueOf(order.getReceiptType()).toString());
				jsonObject.put("invoiceMessage", order.getInvoiceMessage());
				jsonObject.put("shipMethod", ShipMethod.valueOf(order.getShipMethod()).toString());
				jsonObject.put("payMethod", order.getPayMethod());
				jsonObject.put("shipTime", order.getShipTime() == null ? "" : sdf.format(order.getShipTime()));
				jsonObject.put("level", order.getLevel());
				jsonObject.put("memo", order.getMemo());
				jsonObject.put("customerName", order.getCustomerName());
				jsonObject.put("orderProgress", OrderProgress.valueOf(order.getOrderProgress()).toString());
				jsonObject.put("oldOrderId", order.getOldOrderId());
				jsonObject.put("orderMark", order.getOrderMark());
				jsonArray.add(jsonObject);
			}
			json.put("code", 0);
			json.put("order", jsonArray.toString());
		} catch (Exception e) {
			String error = "queryOrderForExamine Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}

	/**
	 * 订单录入功能
	 * 
	 * @author: Tdw
	 * @time: 2016年7月31日下午8:38:29
	 */
	private void addOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		PrintWriter out = response.getWriter();
		Order order = new Order();
		try {
			String customerCode = request.getParameter("customerCode");
			String customerName = request.getParameter("customerName");
			String orderTime = request.getParameter("orderTime");
			String deliveryTime = request.getParameter("deliveryTime");
			String receiptAddress = request.getParameter("receiptAddress");
			String receiver = request.getParameter("receiver");
			String tel = request.getParameter("tel");
			String orderType = request.getParameter("orderType");
			String memo = request.getParameter("memo");
			String orderItems = request.getParameter("orderItems");
			if (StringUtils.isEmpty(orderItems)) {
				json.put("code", -1);
				json.put("error", "明细不能为空！");
				return;
			}
			// 将jsonArray转换成对象
			jsonArray = JSONArray.fromObject(orderItems);
			List<OrderItem> items = new ArrayList<OrderItem>();
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				items.add((OrderItem) JSONObject.toBean(jsonObject, OrderItem.class));
			}
			order.setOrderCode(sdf2.format(new Date()));
			order.setCustomerCode(customerCode);
			order.setCustomerName(customerName);
			order.setOrderTime(sdf.parse(orderTime));
			order.setDeliveryTime(sdf.parse(deliveryTime));
			order.setReceiptAddress(receiptAddress);
			order.setReceiver(receiver);
			order.setTel(tel);
			order.setOrderType(orderType);
			order.setMemo(memo);
			int count = orderManager.addOrder(order, items);
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

	private boolean checkParameter(String... arm) {
		boolean flag = true;
		for (String str : arm) {
			if (StringUtils.isEmpty(str)) {
				flag = false;
			}
			return flag;
		}
		return flag;
	}
}
