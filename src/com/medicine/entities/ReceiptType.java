package com.medicine.entities;

public enum ReceiptType {

	Distribution(0,"配送"),
	OwnExtraction(1,"自取");
	
	private int value;
	private String text;
	
	private ReceiptType(int value,String text){
		this.value = value;
		this.text=text;
	}
	public int getValue(){
		return value;
	}
	public String toString(){
		return text;
	}
	public static ReceiptType valueOf(int value){
		for(ReceiptType logStatus:ReceiptType.values()){
			if(value==logStatus.value)
				return logStatus;
		}
		return null;
	}
	
	public static ReceiptType textOf(String text){
		for(ReceiptType logStatus:ReceiptType.values()){
			if(text.equals(logStatus.text))
				return logStatus;
		}
		return null;
	}
}
