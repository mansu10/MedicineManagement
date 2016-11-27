package com.medicine.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 商品基础表
 * @author tdw
 * @time: 2016年10月15日下午5:34:50
 */
public class Product implements Serializable{

	/**
	 * @author tdw
	 * @time: 2016年10月15日下午5:43:17
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int productCode;
	private String ordinaryName;
	private String productName;
	private String specifications;
	private String unit;
	private float minPrice;
	private float minVolume;
	private int minNumber;
	private int minWeight;
	private float price;
	private float volume;
	private float weight;
	private String firstLevel;
	private String secondLevel;
	private String manufactor;
	private String approvalNumber;
	private int validity;
	private String unitDose;
	private float averageDose;
	private int averageNumber;
	private int maxNumber;
	private Date createTime;
	private String model;
	private int minStock;
	private int maxStock;
	private String supplier;
	private String herbsType;
	private String controlCode;
	private String unityProductCode;
	private String pinyinCode;
	private String thirdLevel;
	private String managementClassification;
	private String storageCoditions;
	private int storageMin;
	private int storageMax;
	private String standard1;
	private String standard2;
	private String standard3;
	private String standard4;
	private String standard5;
	private String specificationSets;
	private String caseType;
	private int caseNumber;
	private int singleBoxVolume;
	private int setVolume;
	private float setPrice;
	private float setWeight;
	private String formulations;
	private String manufactorCode;
	private String minUnit;
	private float minConversionRatio;
	private String material;
	private String minMaterial;
	private String maxUnit;
	private float maxConversionRatio;
	private int maxLength;
	private int maxWidth;
	private int maxHeigth;
	private String locatorCode;
	private List<SetProduct> setProducts;
	private String receiptDepot;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductCode() {
		return productCode;
	}

	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}

	public String getOrdinaryName() {
		return ordinaryName;
	}

	public void setOrdinaryName(String ordinaryName) {
		this.ordinaryName = ordinaryName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public float getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(float minPrice) {
		this.minPrice = minPrice;
	}

	public float getMinVolume() {
		return minVolume;
	}

	public void setMinVolume(float minVolume) {
		this.minVolume = minVolume;
	}

	public int getMinNumber() {
		return minNumber;
	}

	public void setMinNumber(int minNumber) {
		this.minNumber = minNumber;
	}

	public int getMinWeight() {
		return minWeight;
	}

	public void setMinWeight(int minWeight) {
		this.minWeight = minWeight;
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

	public String getFirstLevel() {
		return firstLevel;
	}

	public void setFirstLevel(String firstLevel) {
		this.firstLevel = firstLevel;
	}

	public String getSecondLevel() {
		return secondLevel;
	}

	public void setSecondLevel(String secondLevel) {
		this.secondLevel = secondLevel;
	}

	public String getManufactor() {
		return manufactor;
	}

	public void setManufactor(String manufactor) {
		this.manufactor = manufactor;
	}

	public String getApprovalNumber() {
		return approvalNumber;
	}

	public void setApprovalNumber(String approvalNumber) {
		this.approvalNumber = approvalNumber;
	}

	public int getValidity() {
		return validity;
	}

	public void setValidity(int validity) {
		this.validity = validity;
	}

	public String getUnitDose() {
		return unitDose;
	}

	public void setUnitDose(String unitDose) {
		this.unitDose = unitDose;
	}

	public float getAverageDose() {
		return averageDose;
	}

	public void setAverageDose(float averageDose) {
		this.averageDose = averageDose;
	}

	public int getAverageNumber() {
		return averageNumber;
	}

	public void setAverageNumber(int averageNumber) {
		this.averageNumber = averageNumber;
	}

	public int getMaxNumber() {
		return maxNumber;
	}

	public void setMaxNumber(int maxNumber) {
		this.maxNumber = maxNumber;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getMinStock() {
		return minStock;
	}

	public void setMinStock(int minStock) {
		this.minStock = minStock;
	}

	public int getMaxStock() {
		return maxStock;
	}

	public void setMaxStock(int maxStock) {
		this.maxStock = maxStock;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getHerbsType() {
		return herbsType;
	}

	public void setHerbsType(String herbsType) {
		this.herbsType = herbsType;
	}

	public String getControlCode() {
		return controlCode;
	}

	public void setControlCode(String controlCode) {
		this.controlCode = controlCode;
	}

	public String getUnityProductCode() {
		return unityProductCode;
	}

	public void setUnityProductCode(String unityProductCode) {
		this.unityProductCode = unityProductCode;
	}

	public String getPinyinCode() {
		return pinyinCode;
	}

	public void setPinyinCode(String pinyinCode) {
		this.pinyinCode = pinyinCode;
	}

	public String getThirdLevel() {
		return thirdLevel;
	}

	public void setThirdLevel(String thirdLevel) {
		this.thirdLevel = thirdLevel;
	}

	public String getManagementClassification() {
		return managementClassification;
	}

	public void setManagementClassification(String managementClassification) {
		this.managementClassification = managementClassification;
	}

	public String getStorageCoditions() {
		return storageCoditions;
	}

	public void setStorageCoditions(String storageCoditions) {
		this.storageCoditions = storageCoditions;
	}

	public int getStorageMin() {
		return storageMin;
	}

	public void setStorageMin(int storageMin) {
		this.storageMin = storageMin;
	}

	public int getStorageMax() {
		return storageMax;
	}

	public void setStorageMax(int storageMax) {
		this.storageMax = storageMax;
	}

	public String getStandard1() {
		return standard1;
	}

	public void setStandard1(String standard1) {
		this.standard1 = standard1;
	}

	public String getStandard2() {
		return standard2;
	}

	public void setStandard2(String standard2) {
		this.standard2 = standard2;
	}

	public String getStandard3() {
		return standard3;
	}

	public void setStandard3(String standard3) {
		this.standard3 = standard3;
	}

	public String getStandard4() {
		return standard4;
	}

	public void setStandard4(String standard4) {
		this.standard4 = standard4;
	}

	public String getStandard5() {
		return standard5;
	}

	public void setStandard5(String standard5) {
		this.standard5 = standard5;
	}

	public String getSpecificationSets() {
		return specificationSets;
	}

	public void setSpecificationSets(String specificationSets) {
		this.specificationSets = specificationSets;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public int getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(int caseNumber) {
		this.caseNumber = caseNumber;
	}

	public int getSingleBoxVolume() {
		return singleBoxVolume;
	}

	public void setSingleBoxVolume(int singleBoxVolume) {
		this.singleBoxVolume = singleBoxVolume;
	}

	public int getSetVolume() {
		return setVolume;
	}

	public void setSetVolume(int setVolume) {
		this.setVolume = setVolume;
	}

	public float getSetPrice() {
		return setPrice;
	}

	public void setSetPrice(float setPrice) {
		this.setPrice = setPrice;
	}

	public float getSetWeight() {
		return setWeight;
	}

	public void setSetWeight(float setWeight) {
		this.setWeight = setWeight;
	}

	public String getFormulations() {
		return formulations;
	}

	public void setFormulations(String formulations) {
		this.formulations = formulations;
	}

	public String getManufactorCode() {
		return manufactorCode;
	}

	public void setManufactorCode(String manufactorCode) {
		this.manufactorCode = manufactorCode;
	}

	public String getMinUnit() {
		return minUnit;
	}

	public void setMinUnit(String minUnit) {
		this.minUnit = minUnit;
	}

	public float getMinConversionRatio() {
		return minConversionRatio;
	}

	public void setMinConversionRatio(float minConversionRatio) {
		this.minConversionRatio = minConversionRatio;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getMinMaterial() {
		return minMaterial;
	}

	public void setMinMaterial(String minMaterial) {
		this.minMaterial = minMaterial;
	}

	public String getMaxUnit() {
		return maxUnit;
	}

	public void setMaxUnit(String maxUnit) {
		this.maxUnit = maxUnit;
	}

	public float getMaxConversionRatio() {
		return maxConversionRatio;
	}

	public void setMaxConversionRatio(float maxConversionRatio) {
		this.maxConversionRatio = maxConversionRatio;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public int getMaxWidth() {
		return maxWidth;
	}

	public void setMaxWidth(int maxWidth) {
		this.maxWidth = maxWidth;
	}

	public int getMaxHeigth() {
		return maxHeigth;
	}

	public void setMaxHeigth(int maxHeigth) {
		this.maxHeigth = maxHeigth;
	}

	public String getLocatorCode() {
		return locatorCode;
	}

	public void setLocatorCode(String locatorCode) {
		this.locatorCode = locatorCode;
	}

	public List<SetProduct> getSetProducts() {
		return setProducts;
	}

	public void setSetProducts(List<SetProduct> setProducts) {
		this.setProducts = setProducts;
	}

	public String getReceiptDepot() {
		return receiptDepot;
	}

	public void setReceiptDepot(String receiptDepot) {
		this.receiptDepot = receiptDepot;
	}

}
