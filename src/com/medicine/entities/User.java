package com.medicine.entities;

public class User {

	private long id;
	private String code;
	private String name;
	private String password;
	private long roleId;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	
	/**
	 * 判断密码是否符合要求
	 * @author: Tdw
	 * @time: 2016年7月17日下午8:10:11
	 */
	public static boolean isMeetComplexity(String password){
		if (password.length() < 8){
			return false;
		}
		if(!(password.matches(".*?[^a-zA-Z\\d]+.*?")
				&& password.matches(".*?[a-z]+.*?")  
			    && password.matches(".*?[A-Z]+.*?")  
			    && password.matches(".*?[\\d]+.*?"))) {
			return false;
		}
		return true;
	}
}
