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

import com.medicine.dao.UserDAO;
import com.medicine.entities.Role;
import com.medicine.entities.User;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDAO userDAO = new UserDAO();

	public UserServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String method = request.getParameter("method");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		try {
			if (StringUtils.equals(method, "queryUser")) {
				queryUser(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("exception", e.getMessage());
		}
	}

	private void queryUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		PrintWriter out = response.getWriter();
		JSONArray jsonArray = new JSONArray();
		try {
			List<User> users = userDAO.findAll();
			for (User user : users) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", user.getId());
				jsonObject.put("userCode", user.getCode());
				jsonObject.put("userName", user.getName());
				jsonObject.put("userPassword", user.getPassword());
				jsonObject.put("role", Role.valueOf(user.getRoleId()).toString());
				jsonArray.add(jsonObject);
				json.put("code", 0);
				json.put("order", jsonArray.toString());
			}
		} catch (Exception e) {
			String error = "findUser Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}

}
