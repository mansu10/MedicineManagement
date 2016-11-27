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

import com.medicine.cl.PickListManager;
import com.medicine.entities.PickList;
import com.medicine.entities.PickListItem;
import com.medicine.util.JSONUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/PickListServlet")
public class PickListServlet extends HttpServlet {

	/**
	 * @author tdw
	 * @time: 2016年10月31日上午12:13:25
	 */
	private static final long serialVersionUID = 1L;
	private PickListManager pickListManager = new PickListManager();

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
			if (StringUtils.equals(method, "findPickListsByOrderCode")) {
				findPickListsByOrderCode(request, response);
			}
			else if (StringUtils.equals(method, "findPickListByOrderCode")) {
				findPickListByOrderCode(request, response);
			}
			else if (StringUtils.equals(method, "updatePickListItem")) {
				updatePickListItem(request, response);
			}
			else if (StringUtils.equals(method, "checkOrder")) {
				checkOrder(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("exception", e.getMessage());
		}
	}

	private void findPickListsByOrderCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		PrintWriter out = response.getWriter();
		try {
			String orderCode = request.getParameter("orderCode");
			if (orderCode != null) {
				List<PickList> pickLists = pickListManager.findPickListsByOrderCode(orderCode);
				String str = JSONUtil.serializeObject(pickLists);
				json.put("code", 0);
				json.put("pickLists", str);
			} else {
				json.put("code", -1);
				json.put("error", "orderCode不能为空!");
			}
		} catch (Exception e) {
			String error = "findPickListsByOrderCode Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}

	private void findPickListByOrderCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		PrintWriter out = response.getWriter();
		try {
			String orderCode = request.getParameter("orderCode");
			if (orderCode != null) {
				PickList pickList = pickListManager.findPickListByOrderCode(orderCode);
				String str = JSONUtil.serializeObject(pickList);
				json.put("code", 0);
				json.put("pickLists", str);
			} else {
				json.put("code", -1);
				json.put("error", "orderCode不能为空!");
			}
		} catch (Exception e) {
			String error = "findPickListByOrderCode Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}
	
	private void updatePickListItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		PrintWriter out = response.getWriter();
		try {
			String orderCode = request.getParameter("orderCode");
			String pickListItemsStr = request.getParameter("pickListItems");
			jsonArray = JSONArray.fromObject(pickListItemsStr);
			List<PickListItem> pickListItems = new ArrayList<PickListItem>();
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject json2 = jsonArray.getJSONObject(i);
				pickListItems.add((PickListItem) JSONObject.toBean(json2, PickListItem.class));
			}
			if (orderCode != null && pickListItems != null) {
				int count = pickListManager.updatePickListItem(orderCode, pickListItems);
				json.put("code", 0);
				json.put("count", count);
			} else {
				json.put("code", -1);
				json.put("error", "orderCode与pickListItems不能为空!");
			}
		} catch (Exception e) {
			String error = "updatePickListItem Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}
	
	
	private void checkOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		PrintWriter out = response.getWriter();
		try {
			String orderCode = request.getParameter("orderCode");
			if (orderCode != null) {
				int count = pickListManager.checkOrder(orderCode);
				json.put("code", 0);
				json.put("count", count);
			} else {
				json.put("code", -1);
				json.put("error", "orderCode不能为空!");
			}
		} catch (Exception e) {
			String error = "checkOrder Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}
}
