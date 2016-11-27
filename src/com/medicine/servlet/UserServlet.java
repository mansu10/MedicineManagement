package com.medicine.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.medicine.cl.UserManager;
import com.medicine.entities.Role;
import com.medicine.entities.User;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private UserManager userManager = new UserManager();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String method = request.getParameter("method");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");

		try {
			if (StringUtils.equals(method, "queryUser")) {
				queryUser(request, response);
			} else if (StringUtils.equals(method, "addUser")) {
				addUser(request, response);
			} else if (StringUtils.equals(method, "deleteUser")) {
				deleteUser(request, response);
			} else if (StringUtils.equals(method, "resetPassword")) {
				resetPassword(request, response);
			} else if (StringUtils.equals(method, "updateUser")) {
				updateUser(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("exception", e.getMessage());
		}
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		PrintWriter out = response.getWriter();
		try {
			User user = null;
			JSONObject jsonObject = null;
			String userStr = request.getParameter("user");
			jsonObject = JSONObject.fromObject(userStr);
			user = (User) JSONObject.toBean(jsonObject, User.class);
			if (user != null) {
				int count = userManager.updateUser(user);
				if (count != 0) {
					json.put("code", 0);
				}
			} else {
				json.put("code", -1);
				json.put("error", "对象处理错误！");
			}
		} catch (Exception e) {
			String error = "updateUser Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}

	private void resetPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		PrintWriter out = response.getWriter();
		try {
			String idStr = request.getParameter("id");
			if (idStr != null) {
				userManager.resetPassword(Integer.parseInt(idStr));
				json.put("code", 0);
			} else {
				json.put("code", -1);
				json.put("error", "id不能为空！");
			}
		} catch (Exception e) {
			String error = "resetPassword Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		PrintWriter out = response.getWriter();
		try {
			String idStr = request.getParameter("id");
			if (idStr != null) {
				userManager.deleteUserById(Integer.parseInt(idStr));
				json.put("code", 0);
			} else {
				json.put("code", -1);
				json.put("error", "id不能为空！");
			}
		} catch (Exception e) {
			String error = "deleteUser Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}

	private void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		PrintWriter out = response.getWriter();
		try {
			User user = null;
			JSONObject jsonObject = null;
			String userStr = request.getParameter("user");
			String[] dateFormats = new String[] {"yyyy-MM-dd"};  
	        JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateFormats));  
			jsonObject = JSONObject.fromObject(userStr);
			user = (User) JSONObject.toBean(jsonObject, User.class);
			if (user != null) {
				int count = userManager.addUser(user);
				if (count != 0) {
					json.put("code", 0);
					json.put("id", count);
				}
			} else {
				json.put("code", -1);
				json.put("error", "对象处理错误！");
			}
		} catch (Exception e) {
			String error = "addUser Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}

	private void queryUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		PrintWriter out = response.getWriter();
		JSONArray jsonArray = new JSONArray();
		try {
			String userCode = request.getParameter("userCode");
			String userName = request.getParameter("userName");
			String userTypeStr = request.getParameter("userType");
			String className = request.getParameter("className");
			List<User> users = userManager.findAll(userCode,userName,userTypeStr,className);
			for (User user : users) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", user.getId());
				jsonObject.put("userCode", user.getUserCode());
				jsonObject.put("userName", user.getUserName());
				jsonObject.put("userPassword", user.getUserPassword());
				jsonObject.put("userType", Role.valueOf(user.getUserType()).toString());
				jsonObject.put("studentId", user.getStudentId());
				jsonObject.put("className", user.getClassName());
				jsonObject.put("startTime", sdf.format(user.getStartTime()));
				jsonObject.put("endTime", sdf.format(user.getEndTime()));
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
