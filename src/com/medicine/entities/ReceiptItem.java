package com.medicine.entities;

import java.io.Serializable;
import java.util.Date;

public class ReceiptItem implements Serializable{

	/**
	 * @author tdw
	 * @time: 2016年11月19日下午7:50:48
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	//收货编号
	private String receiptCode;
	//商品编码
	private int productCode;
	//订购单价
	private float purchasePrice;
	//零箱标记
	private int boxMark;
	//箱数
	private int boxQuantity;
	//数量
	private int quantity;
	//出厂日期
	private Date factoryTime;
	//生产批号
	private String productionBatch;
	//备注
	private String memo;
	//商品
	private Product product;
	//库存 
	private Stock stock;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getReceiptCode() {
		return receiptCode;
	}

	public void setReceiptCode(String receiptCode) {
		this.receiptCode = receiptCode;
	}

	public int getProductCode() {
		return productCode;
	}

	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}

	public float getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(float purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public int getBoxMark() {
		return boxMark;
	}

	public void setBoxMark(int boxMark) {
		this.boxMark = boxMark;
	}

	public int getBoxQuantity() {
		return boxQuantity;
	}

	public void setBoxQuantity(int boxQuantity) {
		this.boxQuantity = boxQuantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getFactoryTime() {
		return factoryTime;
	}

	public void setFactoryTime(Date factoryTime) {
		this.factoryTime = factoryTime;
	}

	public String getProductionBatch() {
		return productionBatch;
	}

	public void setProductionBatch(String productionBatch) {
		this.productionBatch = productionBatch;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

}
