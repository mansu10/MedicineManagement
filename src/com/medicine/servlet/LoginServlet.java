package com.medicine.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.medicine.dao.UserDAO;
import com.medicine.entities.User;

import net.sf.json.JSONObject;

//@WebServlet(name = "LoginServlet", urlPatterns = { "/LoginServlet" })
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2518970278560579535L;

	UserDAO udao = new UserDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		String usercode = request.getParameter("name");
		String password = request.getParameter("password");
		boolean flag = false;
		User user = udao.findUserByCode(usercode);
		if (user != null) {
			flag = (user.getPassword().equals(password) ? true : false);
		}
		jsonObject.put("flag", flag);
		out.println(jsonObject);
		// request.setCharacterEncoding("UTF-8");
		// response.setCharacterEncoding("UTF-8");
		// PrintWriter out = response.getWriter();
		// response.setContentType("text/html;charset=UTF-8");
		// String name = request.getParameter("name");
		// out.println("Hello " + name);
		// out.println("This is the output from doPost method!");
		// String usercode = req.getParameter("user_code");
		// String password = req.getParameter("user_password");
		// boolean flag = false;
		// User user = udao.findUserByCode(usercode);
		// if (user != null) {
		// flag = (user.getPassword().equals(password)?true:false);
		// }
		// resp.setContentType("text/javascript"); //声明类型防止乱码
		// resp.getWriter().print(flag); //响应JSON
	}

}
