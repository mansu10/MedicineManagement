package com.medicine.entities;

public enum ShipLevel {
	Normal(0, "正常"), Urgent(1, "急"),ExtraUrgent(2,"特急");

	private int value;
	private String text;

	private ShipLevel(int value, String text) {
		this.value = value;
		this.text = text;
	}

	public int getValue() {
		return value;
	}

	public String toString() {
		return text;
	}

	public static ShipLevel valueOf(int value) {
		for (ShipLevel  level: ShipLevel.values()) {
			if (value == level.value)
				return level;
		}
		return null;
	}

	public static ShipLevel textOf(String text) {
		for (ShipLevel level : ShipLevel.values()) {
			if (text.equals(level.text))
				return level;
		}
		return null;
	}
}
