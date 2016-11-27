package com.medicine.entities;

import java.io.Serializable;
import java.util.Date;

public class ContainerBox implements Serializable{

	/**
	 * @author tdw
	 * @time: 2016年11月5日下午4:28:10
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String boxNumber;
	private Date createTime;
	private int boxStatus;
	private String boxName;
	private String locatorCode;
	private float boxVolume;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBoxNumber() {
		return boxNumber;
	}
	public void setBoxNumber(String boxNumber) {
		this.boxNumber = boxNumber;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getBoxStatus() {
		return boxStatus;
	}
	public void setBoxStatus(int boxStatus) {
		this.boxStatus = boxStatus;
	}
	public String getBoxName() {
		return boxName;
	}
	public void setBoxName(String boxName) {
		this.boxName = boxName;
	}
	public String getLocatorCode() {
		return locatorCode;
	}
	public void setLocatorCode(String locatorCode) {
		this.locatorCode = locatorCode;
	}
	public float getBoxVolume() {
		return boxVolume;
	}
	public void setBoxVolume(float boxVolume) {
		this.boxVolume = boxVolume;
	}
	
}
