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

import com.medicine.cl.LocatorManager;
import com.medicine.entities.Depot;
import com.medicine.util.JSONUtil;

import net.sf.json.JSONObject;

@WebServlet("/LocatorServlet")
public class LocatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LocatorManager locatorManager = new LocatorManager();

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
			if (StringUtils.equals(method, "queryAllLocators")) {
				queryAllLocators(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("exception", e.getMessage());
		}
	}

	private void queryAllLocators(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		try {
			List<Depot> depots = locatorManager.findAllLocators();
			String str = JSONUtil.serializeObject(depots);
			json.put("code", 0);
			json.put("locators", str);
		} catch (Exception e) {
			String error = "queryAllLocators Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}
}
