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

import com.medicine.cl.RaiseinventoryManager;
import com.medicine.entities.RaiseInventory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/RaiseInventoryServlet")
public class RaiseInventoryServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RaiseinventoryManager raiseinventoryManager = new RaiseinventoryManager();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		try {
			if (StringUtils.equals(method, "addRaiseInventory")) {
				addRaiseInventory(request, response);
			}

		} catch (Exception e) {
			request.setAttribute("exception", e.getMessage());
		}
	}

	private void addRaiseInventory(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		PrintWriter out = response.getWriter();
		try {
			JSONArray jsonArray = new JSONArray();
			String inventorys = request.getParameter("inventorys");
			// 将jsonArray转换成对象
			jsonArray = JSONArray.fromObject(inventorys);
			List<RaiseInventory> inventories = new ArrayList<RaiseInventory>();
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				inventories.add((RaiseInventory) JSONObject.toBean(jsonObject, RaiseInventory.class));
			}
			for (RaiseInventory inventory : inventories) {
				int count = raiseinventoryManager.addRaiseInventory(inventory);
				if (count != 0) {
					json.put("code", 0);
				} else {
					json.put("code", -1);
					json.put("error", "对象处理错误！");
				}
			}
		} catch (Exception e) {
			String error = "addRaiseInventory Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());

	}
}
