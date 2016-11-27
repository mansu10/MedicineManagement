package com.medicine.entities;

public enum ShelfSpecification {

	small(0,"1.2*0.4*2"),
	middle(1,"1.5*0.5*2"),
	large(2,"2*0.6*2");
	
	private int value;
	private String text;
	
	private ShelfSpecification(int value,String text){
		this.value = value;
		this.text=text;
	}
	public int getValue(){
		return value;
	}
	public String toString(){
		return text;
	}
	public static ShelfSpecification valueOf(int value){
		for(ShelfSpecification specification:ShelfSpecification.values()){
			if(value==specification.value)
				return specification;
		}
		return null;
	}
	
	public static ShelfSpecification textOf(String text){
		for(ShelfSpecification specification:ShelfSpecification.values()){
			if(text.equals(specification.text))
				return specification;
		}
		return null;
	}
}
