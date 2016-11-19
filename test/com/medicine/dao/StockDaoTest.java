package com.daug.dao;

import java.util.List;

import org.junit.Test;

import com.medicine.dao.StockDao;
import com.medicine.entities.Stock;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class StockDaoTest {

	StockDao stockDao = new StockDao();
	
	@Test
	public void test() {
		List<Stock> stocks = stockDao.findAll();
		
		JSONArray jsonArray = new JSONArray();
        for (Stock stock : stocks) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", stock.getId());
			jsonObject.put("StockNumber", stock.getStockNumber());
			jsonObject.put("StorageCode", stock.getStorageCode());
			jsonObject.put("StorageLocation", stock.getStorageLocation());
			jsonObject.put("InStockTime", stock.getInStockTime());
			jsonObject.put("OutStockTime", stock.getOutStockTime());
			jsonObject.put("ProductCode", stock.getProductCode());
			jsonObject.put("BanchNumber", stock.getBanchNumber());
			jsonArray.add(jsonObject);
		}	
        System.out.println(jsonArray.toString());
	}

}
