package com.medicine.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.medicine.dao.ProductDAO;
import com.medicine.entities.Product;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductDAO productDAO = new ProductDAO();
       
    public ProductServlet() {
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
			List<Product> products = productDAO.findAll();
			for (Product product : products) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", product.getId());
				jsonObject.put("productCode", product.getProductCode());
				jsonObject.put("ordinaryName", product.getOrdinaryName());
				jsonObject.put("productName", product.getProductName());
				jsonObject.put("specifications", product.getSpecifications());
				jsonObject.put("unit", product.getUnit());
				jsonObject.put("minPrice", product.getMinPrice());
				jsonObject.put("minNumber", product.getMinNumber());
				jsonObject.put("minVolume", product.getMinVolume());
				jsonObject.put("minWeight", product.getMinWeight());
				jsonObject.put("price", product.getPrice());
				jsonObject.put("volume", product.getVolume());
				jsonObject.put("weight", product.getWeight());
				jsonObject.put("firstLevel", product.getFirstLevel());
				jsonObject.put("secondLevel", product.getSecondLevel());
				jsonObject.put("manufactor", product.getManufactor());
				jsonObject.put("approvalNumber", product.getApprovalNumber());
				jsonObject.put("validity", product.getValidity());
				jsonObject.put("unitDose", product.getUnitDose());
				jsonObject.put("averageDose", product.getAverageDose());
				jsonObject.put("averageNumber", product.getAverageNumber());
				jsonObject.put("maxNumber", product.getMaxNumber());
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
