package com.medicine.entities;

public enum OrderStatus {

	Untreated(0,"未处理"),
	Processed(1,"已处理");
	
	private int value;
	private String text;
	
	private OrderStatus(int value,String text){
		this.value = value;
		this.text=text;
	}
	public int getValue(){
		return value;
	}
	public String toString(){
		return text;
	}
	public static OrderStatus valueOf(int value){
		for(OrderStatus logStatus:OrderStatus.values()){
			if(value==logStatus.value)
				return logStatus;
		}
		return null;
	}
	
	public static OrderStatus textOf(String text){
		for(OrderStatus logStatus:OrderStatus.values()){
			if(text.equals(logStatus.text))
				return logStatus;
		}
		return null;
	}
	
}
