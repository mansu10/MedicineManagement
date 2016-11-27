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

import com.medicine.cl.OperatorManager;
import com.medicine.entities.Operator;
import com.medicine.util.JSONUtil;

import net.sf.json.JSONObject;

@WebServlet("/OperatorServlet")
public class OperatorServlet extends HttpServlet {

	/**
	 * @author tdw
	 * @time: 2016年11月16日下午11:39:23
	 */
	private static final long serialVersionUID = 1L;
	private OperatorManager operatorManager = new OperatorManager();

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
			if (StringUtils.equals(method, "findOperatorByPosition")) {
				findOperatorByPosition(request, response);
			}

		} catch (Exception e) {
			request.setAttribute("exception", e.getMessage());
		}
	}

	private void findOperatorByPosition(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		String position = request.getParameter("position");
		try {
			List<Operator> operators = operatorManager.findOperatorByPosition(position);
			String str = JSONUtil.serializeObject(operators);
			json.put("code", 0);
			json.put("operators", str);
		} catch (Exception e) {
			String error = "findOperatorByPosition Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());

	}

}
