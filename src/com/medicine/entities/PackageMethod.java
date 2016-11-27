package com.medicine.entities;

public enum PackageMethod {
	Carton(0, "纸箱"), PPHollowCrate(1, "PP中空板箱"), StandardMedicalKit(2, "制式医疗箱");

	private int value;
	private String text;

	private PackageMethod(int value, String text) {
		this.value = value;
		this.text = text;
	}

	public int getValue() {
		return value;
	}

	public String toString() {
		return text;
	}

	public static PackageMethod valueOf(int value) {
		for (PackageMethod method : PackageMethod.values()) {
			if (value == method.value)
				return method;
		}
		return null;
	}

	public static PackageMethod textOf(String text) {
		for (PackageMethod method : PackageMethod.values()) {
			if (text.equals(method.text))
				return method;
		}
		return null;
	}
}
