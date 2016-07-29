package com.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.medicine.entities.Customer;
import com.medicine.util.MedicineRuntimeException;

public class CustomerDAO {

	public List<Customer> findAll(){
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Customer> customers = new ArrayList<Customer>();
		String sql = "select id,customer_code,customer_name,customer_type,customer_number,customer_coordinate,customer_address from tbl_customer";
		try {  
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
            while (rs.next()) {  
            	Customer customer = new Customer();
            	customer.setId(rs.getInt("id"));
            	customer.setCustomerCode(rs.getString("customer_code"));
            	customer.setCustomerName(rs.getString("customer_name"));
            	customer.setCustomerType(rs.getString("customer_type"));
            	customer.setCustomerNumber(rs.getInt("customer_number"));
            	customer.setCustomerCoordinate(rs.getString("customer_coordinate"));
            	customer.setCustomerAddress(rs.getString("customer_address"));
                customers.add(customer);
            }
        }catch (SQLException ex) {
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
		return customers;
	}
}
