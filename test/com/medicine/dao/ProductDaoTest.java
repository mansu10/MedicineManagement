package com.daug.dao;

import java.util.List;

import org.junit.Test;

import com.medicine.dao.ProductDAO;
import com.medicine.entities.Product;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ProductDaoTest {

	ProductDAO productDao = new ProductDAO();
	
	@Test
	public void testFindAllProduct() {
		JSONObject json = new JSONObject();
		
		List<Product> products = productDao.findAll();
		JSONArray jsonArray = new JSONArray();
        for (Product product : products) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", product.getId());
			jsonObject.put("ProductCode", product.getProductCode());
			jsonObject.put("OrdinaryName", product.getOrdinaryName());
			jsonObject.put("ProductName", product.getProductName());
			jsonObject.put("Specifications", product.getSpecifications());
			jsonObject.put("Unit", product.getUnit());
			jsonObject.put("MinPrice", product.getMinPrice());
			jsonObject.put("MinNumber", product.getMinNumber());
			jsonObject.put("MinVolume", product.getMinVolume());
			jsonObject.put("MinWeight", product.getMinWeight());
			jsonObject.put("Price", product.getPrice());
			jsonObject.put("Volume", product.getVolume());
			jsonObject.put("Weight", product.getWeight());
			jsonObject.put("FirstLevel", product.getFirstLevel());
			jsonObject.put("SecondLevel", product.getSecondLevel());
			jsonObject.put("Manufactor", product.getManufactor());
			jsonObject.put("ApprovalNumber", product.getApprovalNumber());
			jsonObject.put("Validity", product.getValidity());
			jsonObject.put("UnitDose", product.getUnitDose());
			jsonObject.put("AverageDose", product.getAverageDose());
			jsonObject.put("AverageNumber", product.getAverageNumber());
			jsonObject.put("MaxNumber", product.getMaxNumber());
			jsonArray.add(jsonObject);
		}	
        if (jsonArray.toString() != null) {
        	json.put("code", 0);
        	json.put("user", jsonArray.toString());
		}else {
			json.put("code", -1);
		}
		System.out.println(json);
	}

}
