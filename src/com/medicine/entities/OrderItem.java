package com.medicine.entities;

public class OrderItem {

	private int id;
	// 订单编号
	private String orderCode;
	// 商品编码
	private int productCode;
	// 商品数量
	private int productNumber;
	// 总价
	private float total;
	// 库存数
	private int stockNumber;
	// 旧明细id
	private int oldItemId;
	// 明细标志
	private int itemMark;
	//分配量
	private int allocation;
	// 通用名
	private String ordinaryName;
	// 单位
	private String unit;
	// 规格
	private String specifications;
	// 单价
	private float price;
	//体积
	private float volume;
	//重量
	private float weight;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public int getProductCode() {
		return productCode;
	}

	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}

	public int getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(int productNumber) {
		this.productNumber = productNumber;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public int getStockNumber() {
		return stockNumber;
	}

	public void setStockNumber(int stockNumber) {
		this.stockNumber = stockNumber;
	}

	public int getOldItemId() {
		return oldItemId;
	}

	public void setOldItemId(int oldItemId) {
		this.oldItemId = oldItemId;
	}

	public int getItemMark() {
		return itemMark;
	}

	public void setItemMark(int itemMark) {
		this.itemMark = itemMark;
	}

	public String getOrdinaryName() {
		return ordinaryName;
	}

	public void setOrdinaryName(String ordinaryName) {
		this.ordinaryName = ordinaryName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getVolume() {
		return volume;
	}

	public void setVolume(float volume) {
		this.volume = volume;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public int getAllocation() {
		return allocation;
	}

	public void setAllocation(int allocation) {
		this.allocation = allocation;
	}

}
