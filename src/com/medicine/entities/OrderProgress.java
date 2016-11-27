package com.medicine.entities;

public enum OrderProgress {
	NoProgress(0, "无进程"), Distribution(1, "在配货"), Collection(2, "在集货"), Loading(3, "在装载"), Transport(4, "在运输");

	private int value;
	private String text;

	private OrderProgress(int value, String text) {
		this.value = value;
		this.text = text;
	}

	public int getValue() {
		return value;
	}

	public String toString() {
		return text;
	}

	public static OrderProgress valueOf(int value) {
		for (OrderProgress progress : OrderProgress.values()) {
			if (value == progress.value)
				return progress;
		}
		return null;
	}

	public static OrderProgress textOf(String text) {
		for (OrderProgress progress : OrderProgress.values()) {
			if (text.equals(progress.text))
				return progress;
		}
		return null;
	}
}
