package com.medicine.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PickList implements Serializable{

	/**
	 * @author tdw
	 * @time: 2016年10月25日下午9:55:53
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String pickListCode;
	private String orderCode;
	private Date createTime;
	private String pickListDepot;
	private String pickListCargoArea;
	private String pickListPerson;
	private Date shipTime;
	private int specifications;
	private int total;
	
	private List<Product> products;
	private List<PickListItem> pickListItems;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPickListCode() {
		return pickListCode;
	}
	public void setPickListCode(String pickListCode) {
		this.pickListCode = pickListCode;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getPickListDepot() {
		return pickListDepot;
	}
	public void setPickListDepot(String pickListDepot) {
		this.pickListDepot = pickListDepot;
	}
	public String getPickListCargoArea() {
		return pickListCargoArea;
	}
	public void setPickListCargoArea(String pickListCargoArea) {
		this.pickListCargoArea = pickListCargoArea;
	}
	public String getPickListPerson() {
		return pickListPerson;
	}
	public void setPickListPerson(String pickListPerson) {
		this.pickListPerson = pickListPerson;
	}
	public Date getShipTime() {
		return shipTime;
	}
	public void setShipTime(Date shipTime) {
		this.shipTime = shipTime;
	}
	public int getSpecifications() {
		return specifications;
	}
	public void setSpecifications(int specifications) {
		this.specifications = specifications;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public List<PickListItem> getPickListItems() {
		return pickListItems;
	}
	public void setPickListItems(List<PickListItem> pickListItems) {
		this.pickListItems = pickListItems;
	}
	
}
