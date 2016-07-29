package com.medicine.cl;

import com.medicine.dao.UserDAO;
import com.medicine.entities.User;

public class UserManager extends DAOManager {

	private UserDAO userDAO = new UserDAO();
	
	public User findUserByCode(String userCode){
		User user = null;
		if (userCode == null) {
			return null;
		}
		user = userDAO.findUserByCode(userCode);
		return user;
	}
	
}
