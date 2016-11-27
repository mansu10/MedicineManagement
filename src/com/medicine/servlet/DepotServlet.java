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

import com.medicine.cl.DepotManager;
import com.medicine.entities.Depot;
import com.medicine.util.JSONUtil;

import net.sf.json.JSONObject;
@WebServlet("/DepotServlet")
public class DepotServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DepotManager depotManager = new DepotManager();;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getParameter("method");
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json;charset=UTF-8");
		resp.setHeader("Access-Control-Allow-Origin", "*");
		try {
			if (StringUtils.equals(method, "queryAllDepots")) {
				queryAllDepots(req,resp);
			}
			else if (StringUtils.equals(method, "addDepot")) {
				addDepot(req, resp);
			}
			else if (StringUtils.equals(method, "updateDepot")) {
				updateDepot(req, resp);
			}
			else if (StringUtils.equals(method, "findDepotByCode")) {
				findDepotByCode(req,resp);
			}
		} catch (Exception e) {
			req.setAttribute("exception", e.getMessage());
		}
	}

	private void findDepotByCode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		JSONObject jsonObject = new JSONObject();
		try {
			String depotCodeStr = req.getParameter("depotCode");
			if (depotCodeStr != null) {
				Depot depot = depotManager.findDepotByCode(Integer.parseInt(depotCodeStr));
				String json = JSONUtil.serializeObject(depot);
				jsonObject.put("code", 0);
				jsonObject.put("depots", json);
			}else {
				jsonObject.put("code", -1);
				jsonObject.put("error", "仓库编码不能为空！");
			}
		} catch (Exception e) {
			jsonObject.put("code", -1);
			jsonObject.put("error", e.getMessage());
		}
		
		out.write(jsonObject.toString());
	}

	private void queryAllDepots(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		JSONObject jsonObject = new JSONObject();
		try {
			List<Depot> depots = depotManager.findAllDepots();
			String json = JSONUtil.serializeObject(depots);
			jsonObject.put("code", 0);
			jsonObject.put("depots", json);
		} catch (Exception e) {
			jsonObject.put("code", -1);
			jsonObject.put("error", e.getMessage());
		}
		
		out.write(jsonObject.toString());
	}
	
	private void addDepot(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		PrintWriter out = response.getWriter();
		try {
			Depot depot = null;
			JSONObject jsonObject = null;
			String depotStr = request.getParameter("depot");
			jsonObject = JSONObject.fromObject(depotStr);
			depot = (Depot) JSONObject.toBean(jsonObject, Depot.class);
			if (depot != null) {
				int count = depotManager.addDepot(depot);
				if (count != 0) {
					json.put("code", 0);
				}
			} else {
				json.put("code", -1);
				json.put("error", "对象处理错误！");
			}
		} catch (Exception e) {
			String error = "addDepot Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}
	private void updateDepot(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		PrintWriter out = response.getWriter();
		try {
			Depot depot = null;
			JSONObject jsonObject = null;
			String depotStr = request.getParameter("depot");
			jsonObject = JSONObject.fromObject(depotStr);
			depot = (Depot) JSONObject.toBean(jsonObject, Depot.class);
			if (depot != null) {
				int count = depotManager.updateDepot(depot);
				if (count != 0) {
					json.put("code", 0);
				}
			} else {
				json.put("code", -1);
				json.put("error", "对象处理错误！");
			}
		} catch (Exception e) {
			String error = "updateDepot Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}
	
}
