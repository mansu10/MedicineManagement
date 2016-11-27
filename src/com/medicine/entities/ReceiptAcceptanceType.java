package com.medicine.entities;

public enum ReceiptAcceptanceType {

	Receive(0, "请领收货"), Allotment(1, "配发收货"), Outsourcing(2, "外购收货"), Other(3, "其他");

	private int value;
	private String text;

	private ReceiptAcceptanceType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	public int getValue() {
		return value;
	}

	public String toString() {
		return text;
	}

	public static ReceiptAcceptanceType valueOf(int value) {
		for (ReceiptAcceptanceType type : ReceiptAcceptanceType.values()) {
			if (value == type.value)
				return type;
		}
		return null;
	}

	public static ReceiptAcceptanceType textOf(String text) {
		for (ReceiptAcceptanceType type : ReceiptAcceptanceType.values()) {
			if (text.equals(type.text))
				return type;
		}
		return null;
	}
}
