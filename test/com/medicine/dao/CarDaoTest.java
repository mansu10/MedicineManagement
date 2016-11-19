package com.daug.dao;

import java.util.List;

import org.junit.Test;

import com.medicine.dao.CarDAO;
import com.medicine.entities.Car;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class CarDaoTest {

	CarDAO cDao = null;
	
	@Test
	public void test() {
		cDao = new CarDAO();
		
		List<Car> cars = cDao.findAll();
		JSONArray jsonArray = new JSONArray();
        for (Car car : cars) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", car.getId());
			jsonObject.put("carCode", car.getCarCode());
			jsonObject.put("carName", car.getCarName());
			jsonObject.put("carUnit", car.getCarUnit());
			jsonObject.put("carDriver", car.getCarDriver());
			jsonObject.put("carVolume", car.getCarVolume());
			jsonObject.put("maxWeight", car.getMaxWeight());
			jsonArray.add(jsonObject);
		}	
        System.out.println(jsonArray.toString());
		
	}

}
