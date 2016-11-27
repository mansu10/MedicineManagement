package com.medicine.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.medicine.cl.ProductManager;
import com.medicine.entities.Product;
import com.medicine.entities.SetProduct;
import com.medicine.util.JSONUtil;
import com.medicine.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductManager productManager = new ProductManager();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
			} else if (StringUtils.equals(method, "findProductByCodeOrName")) {
				findProductByCodeOrName(request, response);
			}
			else if (StringUtils.equals(method, "addProduct")) {
				addProduct(request, response);
			}
			else if (StringUtils.equals(method, "updateProduct")) {
				updateProduct(request, response);
			}
			else if (StringUtils.equals(method, "deleteProduct")) {
				deleteProduct(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("exception", e.getMessage());
		}

	}

	private void findProductByCodeOrName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		try {
			String productCodeOrName = request.getParameter("productCodeOrName");
			Product product = productManager.findProductByCodeOrName(productCodeOrName);
			if (product != null) {
				JSONObject jsonObject = JSONObject.fromObject(product);
				json.put("code", 0);
				json.put("products", jsonObject);
			} else {
				json.put("code", -1);
				json.put("error", "没找到商品");
			}
		} catch (Exception e) {
			String error = "findProductByCodeOrName Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}

	private void queryProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		try {
			String productName = request.getParameter("productName");
			String herbsType = request.getParameter("herbsType");
			List<Product> products = productManager.findAllProducts(productName,herbsType);
			String str = JSONUtil.serializeObject(products);
			json.put("code", 0);
			json.put("products", str);
		} catch (Exception e) {
			String error = "queryProduct Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}
	
	private void addProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		Product product = null;
		try {
			JSONObject jsonObject = null;
			String productStr = request.getParameter("product");
			jsonObject = JSONObject.fromObject(productStr);
			product = (Product) JSONObject.toBean(jsonObject, Product.class);
			String setProductsStr = request.getParameter("setProducts");
			jsonArray = JSONArray.fromObject(setProductsStr);
			List<SetProduct> setProducts = new ArrayList<SetProduct>();
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject json2 = jsonArray.getJSONObject(i);
				setProducts.add((SetProduct) JSONObject.toBean(json2, SetProduct.class));
			}
			if (product != null) {
				product.setSetProducts(setProducts);
				int count = productManager.addProduct(product);
				if (count != 0) {
					json.put("code", 0);
					json.put("id", count);
				}
			} else {
				json.put("code", -1);
				json.put("error", "对象处理错误！");
			}
		} catch (Exception e) {
			String error = "addProduct Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}
	
	private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		Product product = null;
		try {
			JSONObject jsonObject = null;
			String productStr = request.getParameter("product");
			jsonObject = JSONObject.fromObject(productStr);
			product = (Product) JSONObject.toBean(jsonObject, Product.class);
			product = (Product) JSONObject.toBean(jsonObject, Product.class);
			String setProductsStr = request.getParameter("setProducts");
			jsonArray = JSONArray.fromObject(setProductsStr);
			List<SetProduct> setProducts = new ArrayList<SetProduct>();
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject json2 = jsonArray.getJSONObject(i);
				setProducts.add((SetProduct) JSONObject.toBean(json2, SetProduct.class));
			}
			if (product != null) {
				product.setSetProducts(setProducts);
				int count = productManager.updateProduct(product);
				if (count != 0) {
					json.put("code", 0);
					json.put("id", count);
				}
			} else {
				json.put("code", -1);
				json.put("error", "对象处理错误！");
			}
		} catch (Exception e) {
			String error = "addProduct Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}
	
	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		try {
			String idStr = request.getParameter("ids");
			int[] ids = StringUtil.StringToInt(StringUtil.convertStrToArray(idStr));
			if (ids != null && ids.length > 0) {
				int count = productManager.deleteProduct(ids);
				if (count != 0) {
					json.put("code", 0);
				}
			} else {
				json.put("code", -1);
				json.put("error", "对象处理错误！");
			}
		} catch (Exception e) {
			String error = "deleteProduct Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}
}
