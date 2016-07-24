package com.medicine.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.medicine.dao.CustomerDAO;
import com.medicine.entities.Customer;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private CustomerDAO customerDAO = new CustomerDAO();
	
    public CustomerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");  
        PrintWriter out = response.getWriter();  
        response.setContentType("text/html;charset=UTF-8");  
        JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		try {
			List<Customer> customers = customerDAO.findAll();
			for (Customer customer : customers) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", customer.getId());
				jsonObject.put("customerCode", customer.getCustomerCode());
				jsonObject.put("customerName", customer.getCustomerName());
				jsonObject.put("customerAddress", customer.getCustomerAddress());
				jsonObject.put("customerCoordinate", customer.getCustomerCoordinate());
				jsonObject.put("customerNumber", customer.getCustomerNumber());
				jsonObject.put("customerType", customer.getCustomerType());
				jsonArray.add(jsonObject);
				json.put("code", 0);
				json.put("order", jsonArray.toString());
			}
		} catch (Exception e) {
			String error = "findCustomer Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}	
        out.write(json.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
