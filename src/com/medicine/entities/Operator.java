package com.medicine.entities;

import java.io.Serializable;
import java.util.Date;

public class Operator implements Serializable{

	/**
	 * @author tdw
	 * @time: 2016年11月14日下午10:39:38
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String operatorCode;
	private String operatorName;
	private String operatorSex;
	private String position;
	private String specialty;
	private String tel;
	private String agencyCode;
	private Date birth;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getOperatorSex() {
		return operatorSex;
	}
	public void setOperatorSex(String operatorSex) {
		this.operatorSex = operatorSex;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAgencyCode() {
		return agencyCode;
	}
	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
}
