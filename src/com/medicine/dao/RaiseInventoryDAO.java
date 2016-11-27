package com.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.medicine.entities.RaiseInventory;
import com.medicine.util.MedicineRuntimeException;

public class RaiseInventoryDAO extends DBHelper{
	
	public int addRaiseInventory(RaiseInventory inventory) {
		int count = 0;
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;

		String sql = "insert into tbl_raise_inventory (product_code,order_amount,memo)"
				+ "values (?,?,?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, inventory.getProductCode());
			stmt.setInt(2, inventory.getOrderAmount());
			stmt.setString(3, inventory.getMemo());
			count = stmt.executeUpdate();
		} catch (SQLException ex) {
			throw new MedicineRuntimeException(ex.getMessage());
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException ex) {
				}
			}
		}
		return count;
	}
}
