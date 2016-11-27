package com.medicine.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ReceiptAcceptanceRecord implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	//收货编码
	private String receiptCode;
	//收货时间
	private Date receiptTime;
	//收货仓库
	private String receiptDepot;
	//收货人
	private String receiver;
	//货物描述
	private String goodsDescription;
	//供货方
	private String supplierName;
	//供货联系人
	private String supplierContacts;
	//供货联系电话
	private String supplierTel;
	//送货方
	private String deliveryName;
	//送货联系人
	private String deliveryContactes;
	//送货联系电话
	private String deliveryTel;
	//
	private int appearanceDetermination;
	//
	private int manualDetermination;
	//
	private int qualityDetermination;
	//验收纪录
	private String acceptanceRecord;
	//验收员
	private String acceptor;
	//验收结论
	private String acceptanceResult;
	//收货类型
	private int receiptType;
	//来源
	private String source;
	//备注
	private String memo;
	//收货凭证
	private String receiptCertificate;
	//凭证号
	private String certificateNumber;
	
	private List<ReceiptItem> items;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReceiptCode() {
		return receiptCode;
	}
	public void setReceiptCode(String receiptCode) {
		this.receiptCode = receiptCode;
	}
	public Date getReceiptTime() {
		return receiptTime;
	}
	public void setReceiptTime(Date receiptTime) {
		this.receiptTime = receiptTime;
	}
	public String getReceiptDepot() {
		return receiptDepot;
	}
	public void setReceiptDepot(String receiptDepot) {
		this.receiptDepot = receiptDepot;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getGoodsDescription() {
		return goodsDescription;
	}
	public void setGoodsDescription(String goodsDescription) {
		this.goodsDescription = goodsDescription;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getSupplierContacts() {
		return supplierContacts;
	}
	public void setSupplierContacts(String supplierContacts) {
		this.supplierContacts = supplierContacts;
	}
	public String getSupplierTel() {
		return supplierTel;
	}
	public void setSupplierTel(String supplierTel) {
		this.supplierTel = supplierTel;
	}
	public String getDeliveryName() {
		return deliveryName;
	}
	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}
	public String getDeliveryContactes() {
		return deliveryContactes;
	}
	public void setDeliveryContactes(String deliveryContactes) {
		this.deliveryContactes = deliveryContactes;
	}
	public String getDeliveryTel() {
		return deliveryTel;
	}
	public void setDeliveryTel(String deliveryTel) {
		this.deliveryTel = deliveryTel;
	}
	public int getAppearanceDetermination() {
		return appearanceDetermination;
	}
	public void setAppearanceDetermination(int appearanceDetermination) {
		this.appearanceDetermination = appearanceDetermination;
	}
	public int getManualDetermination() {
		return manualDetermination;
	}
	public void setManualDetermination(int manualDetermination) {
		this.manualDetermination = manualDetermination;
	}
	public int getQualityDetermination() {
		return qualityDetermination;
	}
	public void setQualityDetermination(int qualityDetermination) {
		this.qualityDetermination = qualityDetermination;
	}
	public String getAcceptanceRecord() {
		return acceptanceRecord;
	}
	public void setAcceptanceRecord(String acceptanceRecord) {
		this.acceptanceRecord = acceptanceRecord;
	}
	public String getAcceptor() {
		return acceptor;
	}
	public void setAcceptor(String acceptor) {
		this.acceptor = acceptor;
	}
	public String getAcceptanceResult() {
		return acceptanceResult;
	}
	public void setAcceptanceResult(String acceptanceResult) {
		this.acceptanceResult = acceptanceResult;
	}
	public int getReceiptType() {
		return receiptType;
	}
	public void setReceiptType(int receiptType) {
		this.receiptType = receiptType;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getReceiptCertificate() {
		return receiptCertificate;
	}
	public void setReceiptCertificate(String receiptCertificate) {
		this.receiptCertificate = receiptCertificate;
	}
	public String getCertificateNumber() {
		return certificateNumber;
	}
	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}
	public List<ReceiptItem> getItems() {
		return items;
	}
	public void setItems(List<ReceiptItem> items) {
		this.items = items;
	}
	
}
