package com.medicine.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.medicine.dao.OrderDAO;
import com.medicine.entities.Order;
import com.medicine.entities.OrderStatus;
import com.medicine.entities.ReceiptType;
import com.medicine.entities.ShipMethod;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private OrderDAO orderDao = new OrderDAO();
       
    public OrderServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String method = request.getParameter("method");
		request.setCharacterEncoding("UTF-8");  
		response.setCharacterEncoding("UTF-8");  
		response.setContentType("text/json;charset=UTF-8");  

		try {
			if (StringUtils.equals(method, "queryOrder")) {
				queryOrder(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("exception", e.getMessage());
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void queryOrder(HttpServletRequest request, HttpServletResponse response) throws IOException{
        JSONObject json = new JSONObject();
        PrintWriter out = response.getWriter(); 
        JSONArray jsonArray = new JSONArray();
        String orderCode = request.getParameter("orderCode"); 
		try {
			List<Order> orders = orderDao.findAll(orderCode);
			for (Order order : orders) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", order.getId());
				jsonObject.put("OrderCode", order.getOrderCode());
				jsonObject.put("CustomerCode", order.getCustomerCode());
				jsonObject.put("ProductId", order.getProductId());
				jsonObject.put("OrderType", order.getOrderType());
				jsonObject.put("OrderTime", order.getOrderTime().toString());
				jsonObject.put("DeliveryTime", order.getDeliveryTime().toString());
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
				json.put("code", 0);
				json.put("order", jsonArray.toString());
			}
		} catch (Exception e) {
			String error = "findOrder Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}	
        out.write(json.toString());
	}
}
