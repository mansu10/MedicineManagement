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

import com.medicine.cl.ShelfManger;
import com.medicine.entities.Shelf;
import com.medicine.util.JSONUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@WebServlet("/ShelfServlet")
public class ShelfServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ShelfManger shelfManger = new ShelfManger();
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
			if (StringUtils.equals(method, "addShelf")) {
				addShelf(req,resp);
			}
			else if (StringUtils.equals(method, "queryAllShelfs")) {
				queryAllShelfs(req,resp);
			}
			else if (StringUtils.equals(method, "updateShelf")) {
				updateShelf(req, resp);
			}
		} catch (Exception e) {
			req.setAttribute("exception", e.getMessage());
		}
	}

	private void queryAllShelfs(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		JSONObject jsonObject = new JSONObject();
		try {
			String depotCodeStr = req.getParameter("depotCode");
			if (depotCodeStr != null) {
				List<Shelf> shelfs = shelfManger.queryAllShelfs(Integer.parseInt(depotCodeStr));
				String json = JSONUtil.serializeObject(shelfs);
				jsonObject.put("code", 0);
				jsonObject.put("shelfs", json);
			}else {
				jsonObject.put("code", -1);
				jsonObject.put("shelfs", "仓库编号不能为空！");
			}
		} catch (Exception e) {
			jsonObject.put("code", -1);
			jsonObject.put("error", e.getMessage());
		}
		out.write(jsonObject.toString());
	}

	private void addShelf(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		JSONObject json = new JSONObject();
		PrintWriter out = resp.getWriter();
		JSONArray jsonArray = new JSONArray();
		try {
			String shelfStr = req.getParameter("shelfs");
			jsonArray = JSONArray.fromObject(shelfStr);
			List<Shelf> shelfs = new ArrayList<Shelf>();
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				shelfs.add((Shelf) JSONObject.toBean(jsonObject, Shelf.class));
			}
			if (shelfs != null) {
				int count = shelfManger.addShelf(shelfs);
				if (count != 0) {
					json.put("code", 0);
					json.put("count", count);
				} else {
					json.put("code", -1);
					json.put("error", "shelfs不能为空！");
				}
			}
		} catch (Exception e) {
			String error = "addShelf Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}
	
	private void updateShelf(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		JSONObject json = new JSONObject();
		PrintWriter out = resp.getWriter();
		JSONArray jsonArray = new JSONArray();
		try {
			String shelfStr = req.getParameter("shelfs");
			jsonArray = JSONArray.fromObject(shelfStr);
			List<Shelf> shelfs = new ArrayList<Shelf>();
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				shelfs.add((Shelf) JSONObject.toBean(jsonObject, Shelf.class));
			}
			if (shelfs != null) {
				int count = shelfManger.updateShelf(shelfs);
				if (count != 0) {
					json.put("code", 0);
					json.put("count", count);
				} else {
					json.put("code", -1);
					json.put("error", "shelfs不能为空！");
				}
			}
		} catch (Exception e) {
			String error = "updateShelf Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}
}
