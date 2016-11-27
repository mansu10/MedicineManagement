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
		for(ReceiptType type:ReceiptType.values()){
			if(value==type.value)
				return type;
		}
		return null;
	}
	
	public static ReceiptType textOf(String text){
		for(ReceiptType type:ReceiptType.values()){
			if(text.equals(type.text))
				return type;
		}
		return null;
	}
}
