package com.medicine.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.medicine.entities.Product;

public class ProductDAO {
	static DBHelper db = null; 
	static ResultSet ret = null;
	List<Product> products  = null;
	Product product = null;
	String sql = null;
	
	public List<Product> findAll(){
		sql = "select id,product_code,ordinary_name,product_name,specifications,unit,min_price,"
				+ "min_number,min_volume,min_weight,price,volume,weight,first_level,"
				+ "second_level,manufactor,approval_number,validity,unit_dose,average_dose,"
				+ "average_number,max_number "
				+ "from tbl_product";
		db = new DBHelper(sql);
		products = new ArrayList<Product>();
		try {  
            ret = db.pst.executeQuery(); 
            while (ret.next()) {  
            	product = new Product();
            	product.setId(ret.getInt("id"));
            	product.setProductCode(ret.getInt("product_code"));
            	product.setOrdinaryName(ret.getString("ordinary_name"));
            	product.setProductName(ret.getString("product_name"));
            	product.setSpecifications(ret.getString("specifications"));
            	product.setUnit(ret.getString("unit"));
            	product.setMinPrice(ret.getFloat("min_price"));
            	product.setMinNumber(ret.getInt("min_number"));
            	product.setMinVolume(ret.getFloat("min_volume"));
            	product.setMinWeight(ret.getInt("min_weight"));
            	product.setPrice(ret.getFloat("price"));
            	product.setVolume(ret.getFloat("volume"));
            	product.setWeight(ret.getFloat("weight"));
            	product.setFirstLevel(ret.getString("first_level"));
            	product.setSecondLevel(ret.getString("second_level"));
            	product.setManufactor(ret.getString("manufactor"));
            	product.setApprovalNumber(ret.getString("approval_number"));
            	product.setValidity(ret.getInt("validity"));
            	product.setUnitDose(ret.getFloat("unit_dose"));
            	product.setAverageDose(ret.getFloat("average_dose"));
            	product.setAverageNumber(ret.getInt("average_number"));
            	product.setMaxNumber(ret.getInt("max_number"));
            	
                products.add(product);
            }
            ret.close();  
            db.close(); 
        } catch (SQLException e) {  
            e.printStackTrace();  
        } 
		return products;
	}
}
