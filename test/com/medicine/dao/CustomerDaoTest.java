package com.daug.dao;

import java.util.List;

import org.junit.Test;

import com.medicine.dao.CustomerDAO;
import com.medicine.entities.Customer;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class CustomerDaoTest {

	CustomerDAO customerDao = new CustomerDAO();
	
	@Test
	public void testFindAll() {
		JSONObject json = new JSONObject();
		List<Customer> customers = customerDao.findAll();
		JSONArray jsonArray = new JSONArray();
        for (Customer customer : customers) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", customer.getId());
			jsonObject.put("CustomerCode", customer.getCustomerCode());
			jsonObject.put("CustomerName", customer.getCustomerName());
			jsonObject.put("CustomerAddress", customer.getCustomerAddress());
			jsonObject.put("CustomerCoordinate", customer.getCustomerCoordinate());
			jsonObject.put("CustomerNumber", customer.getCustomerNumber());
			jsonObject.put("CustomerType", customer.getCustomerType());
			jsonArray.add(jsonObject);
		}	
        if (jsonArray.toString() != null) {
        	json.put("code", 0);
        	json.put("order", jsonArray.toString());
		}else {
			json.put("code", -1);
		}
		System.out.println(json);
		
	}

}
