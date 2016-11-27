package com.medicine.cl;

import java.util.List;

import com.medicine.dao.UserDAO;
import com.medicine.entities.User;
import com.medicine.util.MedicineRuntimeException;

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
	
	public int addUser(User user){
		int count = 0;
		try {
			this.beginTransaction();
			count = userDAO.addUser(user);
			this.commitTransaction();
		} catch (Exception e) {
			this.rollbackTransaction();
			throw new MedicineRuntimeException(e.getMessage());
		}
		return count;
	}
	
	public List<User> findAll(String userCode, String userName, String userTypeStr, String className) {
		List<User> users = null;
		users = userDAO.findAll(userCode,userName,userTypeStr,className);
		return users;
	}
	
	public int deleteUserById(int id){
		int count = 0;
		try {
			this.beginTransaction();
			count = userDAO.deleteUserById(id);
			this.commitTransaction();
		}catch(Throwable t){
			this.rollbackTransaction();
			throw new MedicineRuntimeException(t.getMessage());
		}
		return count;
	}
	
	public int resetPassword(int id){
		int count = 0;
		try {
			this.beginTransaction();
			count = userDAO.resetPassword(id);
			this.commitTransaction();
		}catch(Throwable t){
			this.rollbackTransaction();
			throw new MedicineRuntimeException(t.getMessage());
		}
		return count;
	}
	
	public int updateUser(User user){
		int count = 0;
		try {
			this.beginTransaction();
			count = userDAO.updateUser(user);
			this.commitTransaction();
		}catch(Throwable t){
			this.rollbackTransaction();
			throw new MedicineRuntimeException(t.getMessage());
		}
		return count;
	}
}
