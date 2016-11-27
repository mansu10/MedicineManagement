package com.medicine.entities;

public enum OutProcessing {
	Had(0, "已有先发货"), Gather(1, "备齐再发货");

	private int value;
	private String text;

	private OutProcessing(int value, String text) {
		this.value = value;
		this.text = text;
	}

	public int getValue() {
		return value;
	}

	public String toString() {
		return text;
	}

	public static OutProcessing valueOf(int value) {
		for (OutProcessing  processing: OutProcessing.values()) {
			if (value == processing.value)
				return processing;
		}
		return null;
	}

	public static OutProcessing textOf(String text) {
		for (OutProcessing processing : OutProcessing.values()) {
			if (text.equals(processing.text))
				return processing;
		}
		return null;
	}
}
