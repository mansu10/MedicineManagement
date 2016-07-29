package com.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.medicine.entities.OrderItem;
import com.medicine.util.MedicineRuntimeException;

public class OrderItemDAO {

	public int addOrderItem(OrderItem orderItem){
		int count = 0;
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;

		String sql = "insert into tbl_order_item (order_id,product_id,product_number,total)"
				+ " values (?,?,?,?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, orderItem.getOrderId());
			stmt.setInt(2, orderItem.getProductId());
			stmt.setInt(3, orderItem.getProductNumber());
			stmt.setFloat(4, orderItem.getTotal());
			count = stmt.executeUpdate();
		} catch (SQLException ex) {
			throw new MedicineRuntimeException(ex);
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
