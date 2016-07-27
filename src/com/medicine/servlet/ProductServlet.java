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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String method = request.getParameter("method");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");

		try {
			if (StringUtils.equals(method, "queryProduct")) {
				queryProduct(request, response);
			}
			// else if (StringUtils.equals(method, "addOrder")) {
			// addOrder(request, response);
			// }
		} catch (Exception e) {
			request.setAttribute("exception", e.getMessage());
		}

	}

	private void queryProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {

		PrintWriter out = response.getWriter();
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
				json.put("products", jsonArray.toString());
			}
		} catch (Exception e) {
			String error = "findProduct Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}
}
