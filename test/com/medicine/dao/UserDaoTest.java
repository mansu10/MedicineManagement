package com.daug.dao;

import java.util.List;

import org.junit.Test;

import com.medicine.dao.UserDAO;
import com.medicine.entities.Role;
import com.medicine.entities.User;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UserDaoTest {

	
	UserDAO userDAO = new UserDAO();
	
//	public static void main(String[] args) {
//		String jsonStr = userDAO.findAll();
//		//String jsonStr = userDAO.findUserByCode("admin");
//		System.out.println(jsonStr);
//	}
	
	@Test
	public void testFindAll() {
		JSONObject json = new JSONObject();
		
		List<User> users = userDAO.findAll();
		JSONArray jsonArray = new JSONArray();
        for (User user : users) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", user.getId());
			jsonObject.put("userCode", user.getCode());
			jsonObject.put("userName", user.getName());
			jsonObject.put("userPassword", user.getPassword());
			jsonObject.put("role", Role.valueOf(user.getRoleId()).toString());
			jsonArray.add(jsonObject);
		}	
        if (jsonArray.toString() != null) {
        	json.put("code", 0);
        	json.put("user", jsonArray.toString());
		}else {
			json.put("code", -1);
		}
		System.out.println(json);
	}
}
