package com.medicine.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.medicine.dao.StockDao;
import com.medicine.entities.Stock;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/StockServlet")
public class StockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private StockDao stockDao = new StockDao();
	
	
    public StockServlet() {
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
			List<Stock> stocks = stockDao.findAll();
			for (Stock stock : stocks) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", stock.getId());
				jsonObject.put("productCode", stock.getProductCode());
				jsonObject.put("stockNumber", stock.getStockNumber());
				jsonObject.put("inStockTime", stock.getInStockTime()==null?"":stock.getInStockTime().toString());
				jsonObject.put("outStockTime", stock.getOutStockTime()==null?"":stock.getOutStockTime().toString());
				jsonObject.put("banchNumber", stock.getBanchNumber());
				jsonObject.put("storageCode", stock.getStorageCode());
				jsonObject.put("storageLocation", stock.getStorageLocation());
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
