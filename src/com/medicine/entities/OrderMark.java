package com.medicine.entities;

public enum OrderMark {
	NewOrder(0, "新订单"), OldOrder(1, "旧订单");

	private int value;
	private String text;

	private OrderMark(int value, String text) {
		this.value = value;
		this.text = text;
	}

	public int getValue() {
		return value;
	}

	public String toString() {
		return text;
	}

	public static OrderMark valueOf(int value) {
		for (OrderMark  mark: OrderMark.values()) {
			if (value == mark.value)
				return mark;
		}
		return null;
	}

	public static OrderMark textOf(String text) {
		for (OrderMark mark : OrderMark.values()) {
			if (text.equals(mark.text))
				return mark;
		}
		return null;
	}
}
