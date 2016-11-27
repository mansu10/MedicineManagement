package com.medicine.entities;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int userType;
	private String userCode;
	private String userName;
	private String userPassword;
	private String studentId;
	private String className;
	private Date startTime;
	private Date endTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * 判断密码是否符合要求
	 * 
	 * @author: Tdw
	 * @time: 2016年7月17日下午8:10:11
	 */
	public static boolean isMeetComplexity(String password) {
		if (password.length() < 8) {
			return false;
		}
		if (!(password.matches(".*?[^a-zA-Z\\d]+.*?") && password.matches(".*?[a-z]+.*?")
				&& password.matches(".*?[A-Z]+.*?") && password.matches(".*?[\\d]+.*?"))) {
			return false;
		}
		return true;
	}

}
