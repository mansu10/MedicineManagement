package com.medicine.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.medicine.cl.UserManager;
import com.medicine.entities.Role;
import com.medicine.entities.User;

import net.sf.json.JSONObject;

//@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -2518970278560579535L;

	private UserManager umgr = new UserManager();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
//		response.setHeader("Access-Control-Allow-Methods","POST");
//		response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");
		JSONObject json = new JSONObject();
		PrintWriter out = response.getWriter();
		try {
			String usercode = request.getParameter("name");
			String password = request.getParameter("password");
			boolean flag = false;
			User user = umgr.findUserByCode(usercode);
			if (user != null) {
				flag = (user.getPassword().equals(password) ? true : false);
				json.put("code", 0);
				json.put("flag", flag);
				if (flag) {
					json.put("userCode", user.getCode());
					json.put("userName", user.getName());
					json.put("role", Role.valueOf(user.getRoleId()).toString());
				}
			}else {
				json.put("code", 0);
				json.put("flag", flag);
			}
		} catch (Exception e) {
			String error = "数据库连接错误 error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.println(json);
	}
}
