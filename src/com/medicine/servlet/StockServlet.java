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

import com.medicine.cl.StockManager;
import com.medicine.entities.Stock;
import com.medicine.util.JSONUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/StockServlet")
public class StockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StockManager stockManager = new StockManager();

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
			if (StringUtils.equals(method, "queryAllStocks")) {
				queryAllStocks(request, response);
			} else if (StringUtils.equals(method, "deleteStock")) {
				deleteStockById(request, response);
			} else if (StringUtils.equals(method, "addStock")) {
				addStock(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("exception", e.getMessage());
		}
	}

	private void addStock(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		PrintWriter out = response.getWriter();
		JSONArray jsonArray = new JSONArray();
		try {
			String stockStr = request.getParameter("stocks");
			jsonArray = JSONArray.fromObject(stockStr);
			List<Stock> stocks = new ArrayList<Stock>();
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				stocks.add((Stock) JSONObject.toBean(jsonObject, Stock.class));
			}
			if (stocks != null) {
				int count = stockManager.addStock(stocks);
				if (count != 0) {
					json.put("code", 0);
				} else {
					json.put("code", -1);
					json.put("error", "stocks不能为空！");
				}
			}
		} catch (Exception e) {
			String error = "addStock Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}

	private void deleteStockById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		PrintWriter out = response.getWriter();
		try {
			String idStr = request.getParameter("id");
			if (idStr != null) {
				stockManager.deleteStockById(Integer.parseInt(idStr));
				json.put("code", 0);
			} else {
				json.put("code", -1);
				json.put("error", "id不能为空！");
			}
		} catch (Exception e) {
			String error = "deleteStock Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}

	private void queryAllStocks(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		try {
			String productCodeOrName = request.getParameter("productCodeOrName");
			List<Stock> stocks = stockManager.findAllStocks(productCodeOrName);
			String str = JSONUtil.serializeObject(stocks);
			json.put("code", 0);
			json.put("stocks", str);
		} catch (Exception e) {
			String error = "findAllStocks Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}

}
