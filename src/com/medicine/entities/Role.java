package com.medicine.entities;

public enum Role {

	Admin(0,"管理员"),
	Srudent(1,"学员"),
	Teacher(2,"教员");
	
	private long value;
	private String text;
	
	private Role(long value,String text){
		this.value = value;
		this.text=text;
	}
	public long getValue(){
		return value;
	}
	public String toString(){
		return text;
	}
	public static Role valueOf(long value){
		for(Role role:Role.values()){
			if(value==role.value)
				return role;
		}
		return null;
	}
	
	public static Role textOf(String text){
		for(Role role:Role.values()){
			if(text.equals(role.text))
				return role;
		}
		return null;
	}
}
