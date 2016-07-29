package com.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.medicine.entities.Product;
import com.medicine.util.MedicineRuntimeException;

public class ProductDAO {
	
	public List<Product> findAll(){
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Product> products = new ArrayList<Product>();
		String sql = "select id,product_code,ordinary_name,product_name,specifications,unit,min_price,"
				+ "min_number,min_volume,min_weight,price,volume,weight,first_level,"
				+ "second_level,manufactor,approval_number,validity,unit_dose,average_dose,"
				+ "average_number,max_number "
				+ "from tbl_product";
		try {  
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
            while (rs.next()) {  
				Product product = new Product();
            	product.setId(rs.getInt("id"));
            	product.setProductCode(rs.getInt("product_code"));
            	product.setOrdinaryName(rs.getString("ordinary_name"));
            	product.setProductName(rs.getString("product_name"));
            	product.setSpecifications(rs.getString("specifications"));
            	product.setUnit(rs.getString("unit"));
            	product.setMinPrice(rs.getFloat("min_price"));
            	product.setMinNumber(rs.getInt("min_number"));
            	product.setMinVolume(rs.getFloat("min_volume"));
            	product.setMinWeight(rs.getInt("min_weight"));
            	product.setPrice(rs.getFloat("price"));
            	product.setVolume(rs.getFloat("volume"));
            	product.setWeight(rs.getFloat("weight"));
            	product.setFirstLevel(rs.getString("first_level"));
            	product.setSecondLevel(rs.getString("second_level"));
            	product.setManufactor(rs.getString("manufactor"));
            	product.setApprovalNumber(rs.getString("approval_number"));
            	product.setValidity(rs.getInt("validity"));
            	product.setUnitDose(rs.getFloat("unit_dose"));
            	product.setAverageDose(rs.getFloat("average_dose"));
            	product.setAverageNumber(rs.getInt("average_number"));
            	product.setMaxNumber(rs.getInt("max_number"));
            	
                products.add(product);
            }
        } catch (SQLException ex) {
			throw new MedicineRuntimeException(ex);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException ex) {
				}
			}
		}
		return products;
	}
}
