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

import com.medicine.cl.CargoAreaManager;
import com.medicine.entities.CargoArea;
import com.medicine.util.JSONUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/CargoAreaServlet")
public class CargoAreaServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CargoAreaManager cargoAreaManager = new CargoAreaManager();

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
			if (StringUtils.equals(method, "addCargoArea")) {
				addCargoArea(req, resp);
			} else if (StringUtils.equals(method, "findCargoAreaCodesByDepotCode")) {
				findCargoAreaCodesByDepotCode(req, resp);
			} else if (StringUtils.equals(method, "updateCargoArea")) {
				updateCargoArea(req, resp);
			}
		} catch (Exception e) {
			req.setAttribute("exception", e.getMessage());
		}
	}

	private void findCargoAreaCodesByDepotCode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		JSONObject jsonObject = new JSONObject();
		try {
			String depotCodeStr = req.getParameter("depotCode");
			if (depotCodeStr != null) {
				List<String> codes = cargoAreaManager.findCargoAreaCodesByDepotCode(Integer.parseInt(depotCodeStr));
				String json = JSONUtil.serializeObject(codes);
				jsonObject.put("code", 0);
				jsonObject.put("cargoAreaCodes", json);
			} else {
				jsonObject.put("code", -1);
				jsonObject.put("error", "仓库编码不能为空！");
			}
		} catch (Exception e) {
			jsonObject.put("code", -1);
			jsonObject.put("error", e.getMessage());
		}

		out.write(jsonObject.toString());
	}

	private void addCargoArea(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		JSONObject json = new JSONObject();
		PrintWriter out = resp.getWriter();
		JSONArray jsonArray = new JSONArray();
		try {
			String cargoAreaStr = req.getParameter("cargoAreas");
			jsonArray = JSONArray.fromObject(cargoAreaStr);
			List<CargoArea> cargoAreas = new ArrayList<CargoArea>();
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				cargoAreas.add((CargoArea) JSONObject.toBean(jsonObject, CargoArea.class));
			}
			if (cargoAreas != null) {
				int count = cargoAreaManager.addCargoArea(cargoAreas);
				if (count != 0) {
					json.put("code", 0);
					json.put("count", count);
				} else {
					json.put("code", -1);
					json.put("error", "cargoAreas不能为空！");
				}
			}
		} catch (Exception e) {
			String error = "addCargoArea Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}

	private void updateCargoArea(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		JSONObject json = new JSONObject();
		PrintWriter out = resp.getWriter();
		JSONArray jsonArray = new JSONArray();
		try {
			String cargoAreaStr = req.getParameter("cargoAreas");
			jsonArray = JSONArray.fromObject(cargoAreaStr);
			List<CargoArea> cargoAreas = new ArrayList<CargoArea>();
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				cargoAreas.add((CargoArea) JSONObject.toBean(jsonObject, CargoArea.class));
			}
			if (cargoAreas != null) {
				int count = cargoAreaManager.updateCargoArea(cargoAreas);
				if (count != 0) {
					json.put("code", 0);
					json.put("count", count);
				} else {
					json.put("code", -1);
					json.put("error", "cargoAreas不能为空！");
				}
			}
		} catch (Exception e) {
			String error = "updateCargoArea Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}
}
