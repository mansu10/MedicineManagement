package com.medicine.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.medicine.entities.Car;

public class CarDAO {

	static DBHelper db = null; 
	static ResultSet ret = null;
	List<Car> cars  = null;
	Car car = null;
	String sql = null;
	
	public List<Car> findAll(){
		sql = "select id,car_code,car_name,car_unit,car_driver,max_weight,car_volume from tbl_car";
		db = new DBHelper(sql);
		cars = new ArrayList<Car>();
		try {  
            ret = db.pst.executeQuery(); 
            while (ret.next()) {  
            	car= new Car();
            	car.setId(ret.getInt("id"));
            	car.setCarCode(ret.getString("car_code"));
            	car.setCarName(ret.getString("car_name"));
            	car.setCarUnit(ret.getString("car_unit"));
            	car.setCarDriver(ret.getString("car_driver"));
            	car.setCarVolume(ret.getInt("car_volume"));
            	car.setMaxWeight(ret.getInt("max_weight"));
                cars.add(car);
            }
            ret.close();  
            db.close(); 
        } catch (SQLException e) {  
            e.printStackTrace();  
        } 
		return cars;
	}
}
