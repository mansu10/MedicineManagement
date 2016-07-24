package com.medicine.entities;

public enum ShipMethod {
	SpecialTrain(0,"专门"),
	Carpool(1,"自取");
	
	private int value;
	private String text;
	
	private ShipMethod(int value,String text){
		this.value = value;
		this.text=text;
	}
	public int getValue(){
		return value;
	}
	public String toString(){
		return text;
	}
	public static ShipMethod valueOf(int value){
		for(ShipMethod logStatus:ShipMethod.values()){
			if(value==logStatus.value)
				return logStatus;
		}
		return null;
	}
	
	public static ShipMethod textOf(String text){
		for(ShipMethod logStatus:ShipMethod.values()){
			if(text.equals(logStatus.text))
				return logStatus;
		}
		return null;
	}
	
}
