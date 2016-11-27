package com.medicine.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.medicine.cl.CarManager;
import com.medicine.cl.StowageManager;
import com.medicine.entities.Stowage;
import com.medicine.entities.StowageItem;
import com.medicine.util.JSONUtil;
import com.medicine.util.StringUtil;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

@WebServlet("/StowageServlet")
public class StowageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private StowageManager stowageManager = new StowageManager();
	private CarManager carManager = new CarManager();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getParameter("method");
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json;charset=UTF-8");
		resp.setHeader("Access-Control-Allow-Origin", "*");
		try {
			if (StringUtils.equals(method, "addStowage")) {
				addStowage(req, resp);
			} 
			else if (StringUtils.equals(method, "findAllStowages")) {
				findAllStowages(req, resp);
			}
			else if (StringUtils.equals(method, "deleteStowage")) {
				deleteStowage(req, resp);
			} 
			else if (StringUtils.equals(method, "findStowageByCode")) {
				findStowageByCode(req, resp);
			}
			else if (StringUtils.equals(method, "updateStowageItems")) {
				updateStowageItems(req, resp);
			}
			else if (StringUtils.equals(method, "updateStowage")) {
				updateStowage(req, resp);
			}
		} catch (Exception e) {
			req.setAttribute("exception", e.getMessage());
		}
	}

	private void addStowage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		Stowage stowage = null;
		try {
			JSONObject jsonObject = null;
			String stowageStr = request.getParameter("stowage");
			jsonObject = JSONObject.fromObject(stowageStr);
			stowage = (Stowage) JSONObject.toBean(jsonObject, Stowage.class);
			if (stowage != null) {
				int count = stowageManager.addStowage(stowage);
				if (count != 0) {
					json.put("code", 0);
				}
			} else {
				json.put("code", -1);
				json.put("error", "对象处理错误！");
			}
		} catch (Exception e) {
			String error = "addStowage Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}
	private void updateStowage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		Stowage stowage = null;
		try {
			JSONObject jsonObject = null;
			String stowageStr = request.getParameter("stowage");
			String[] dateFormats = new String[] {"yyyy-MM-dd HH:mm:ss"}; 
			JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateFormats));
			jsonObject = JSONObject.fromObject(stowageStr);
			stowage = (Stowage) JSONObject.toBean(jsonObject, Stowage.class);
			if (stowage != null) {
				int count = stowageManager.updateStowage(stowage);
				if (count != 0) {
					json.put("code", 0);
					json.put("count", count);
				}
			} else {
				json.put("code", -1);
				json.put("error", "对象处理错误！");
			}
		} catch (Exception e) {
			String error = "updateStowage Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}

	private void findAllStowages(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		try {
			String stowageCode = request.getParameter("stowageCode");
			String carCode = request.getParameter("carCode");
			List<Stowage> stowages = stowageManager.findAllStowages(stowageCode, carCode);
			String json = JSONUtil.serializeObject(stowages);
			jsonObject.put("code", 0);
			jsonObject.put("stowages", json);
		} catch (Exception e) {
			String error = "findAllStowages Fail error:" + e.getMessage();
			jsonObject.put("code", -1);
			jsonObject.put("error", error);
		}
		out.write(jsonObject.toString());
	}

	private void deleteStowage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		try {
			String idStr = request.getParameter("ids");
			int[] ids = StringUtil.StringToInt(StringUtil.convertStrToArray(idStr));
			if (ids != null && ids.length > 0) {
				int count = stowageManager.deleteStowage(ids);
				if (count != 0) {
					json.put("code", 0);
				}
			} else {
				json.put("code", -1);
				json.put("error", "对象处理错误！");
			}
		} catch (Exception e) {
			String error = "deleteStowage Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}

	private void updateStowageItems(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		try {
			String stowageItemsStr = request.getParameter("stowageItems");
//			String operator = request.getParameter("operator");
			String[] dateFormats = new String[] {"yyyy-MM-dd HH:mm:ss"};  
	        JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateFormats));  
			jsonArray = JSONArray.fromObject(stowageItemsStr);
			List<StowageItem> stowageItems = new ArrayList<StowageItem>();
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject json2 = jsonArray.getJSONObject(i);
				stowageItems.add((StowageItem) JSONObject.toBean(json2, StowageItem.class));
			}
			if (stowageItems != null && stowageItems.size() > 0) {
				int count = stowageManager.updateStowageItems(stowageItems);
//				String stowageCode = stowageItems.get(0).getStowageCode();
//				count = carManager.updateOperatorByCode(operator, stowageCode);
				json.put("code", 0);
				json.put("count", count);
			} else {
				json.put("code", -1);
				json.put("error", "stowageItems不能为空!");
			}
		} catch (Exception e) {
			String error = "updateStowageItems Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}

	private void findStowageByCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		try {
			String stowageCode = request.getParameter("stowageCode");
			Stowage stowage = stowageManager.findStowageByCode(stowageCode);
			String json = JSONUtil.serializeObject(stowage);
			jsonObject.put("code", 0);
			jsonObject.put("stowage", json);
		} catch (Exception e) {
			String error = "findStowageByCode Fail error:" + e.getMessage();
			jsonObject.put("code", -1);
			jsonObject.put("error", error);
		}
		out.write(jsonObject.toString());
	}

}
