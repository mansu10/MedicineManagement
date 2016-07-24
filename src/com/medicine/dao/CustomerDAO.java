package com.medicine.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.medicine.entities.Customer;

public class CustomerDAO {

	static DBHelper db = null; 
	static ResultSet ret = null;
	List<Customer> customers  = null;
	Customer customer = null;
	String sql = null;
	
	public List<Customer> findAll(){
		sql = "select id,customer_code,customer_name,customer_type,customer_number,customer_coordinate,customer_address from tbl_customer";
		db = new DBHelper(sql);
		customers = new ArrayList<Customer>();
		try {  
            ret = db.pst.executeQuery(); 
            while (ret.next()) {  
            	customer= new Customer();
            	customer.setId(ret.getInt("id"));
            	customer.setCustomerCode(ret.getString("customer_code"));
            	customer.setCustomerName(ret.getString("customer_name"));
            	customer.setCustomerType(ret.getString("customer_type"));
            	customer.setCustomerNumber(ret.getInt("customer_number"));
            	customer.setCustomerCoordinate(ret.getString("customer_coordinate"));
            	customer.setCustomerAddress(ret.getString("customer_address"));
                customers.add(customer);
            }
            ret.close();  
            db.close(); 
        } catch (SQLException e) {  
            e.printStackTrace();  
        } 
		//JSONArray array = JSONArray.fromObject(users);
		return customers;
	}
}
