package com.medicine.util;

public class MedicineRuntimeException extends  RuntimeException{

	private static final long serialVersionUID = 1L;

	public MedicineRuntimeException() {
		super();
	}

	public MedicineRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public MedicineRuntimeException(String message) {
		super(message);
	}

	public MedicineRuntimeException(Throwable cause) {
		super(cause);
	}
}
