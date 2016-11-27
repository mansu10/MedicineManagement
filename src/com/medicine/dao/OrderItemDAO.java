package com.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.medicine.entities.OrderItem;
import com.medicine.util.MedicineRuntimeException;

public class OrderItemDAO {

	public int addOrderItem(OrderItem orderItem){
		int count = 0;
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;

		String sql = "insert into tbl_order_item (order_code,product_code,product_number,total,old_item_id,item_mark,allocation)"
				+ " values (?,?,?,?,?,?,?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, orderItem.getOrderCode());
			stmt.setInt(2, orderItem.getProductCode());
			stmt.setInt(3, orderItem.getProductNumber());
			stmt.setFloat(4, orderItem.getTotal());
			stmt.setInt(5, orderItem.getOldItemId());
			stmt.setInt(6, orderItem.getItemMark());
			stmt.setInt(7, orderItem.getAllocation());
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
	
	public List<OrderItem> findOrderItemByOrderCode(String orderCode){
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<OrderItem> items = new ArrayList<>();
		String sql = "select product_code,id,order_code,product_number,total "
				+ "from tbl_order_item "
				+ "where order_code = ? "
				+ "group by product_code,order_code";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, orderCode);
			rs = stmt.executeQuery();
			while (rs.next()) {
				OrderItem item = new OrderItem();
				item.setId(rs.getInt("id"));
				item.setOrderCode(rs.getString("order_code"));
				item.setProductCode(rs.getInt("product_code"));
				item.setProductNumber(rs.getInt("product_number"));
				item.setTotal(rs.getFloat("total"));
				items.add(item);
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
		return items;
	}
	
	public int countFindOrderItemsByCode(String orderCode) {
		int count = 0;
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select count(id) count "
				+ "from tbl_order_item "
				+ "where order_code = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, orderCode);
			rs = stmt.executeQuery();
			while(rs.next()) {
				count = rs.getInt("count");
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
		return count;
	}
	
	public int updateItemMarkById(int id,int itemMark){
		int count = 0;
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;

		String sql = "update tbl_order_item "
				+ "set item_mark = ? "
				+ "where id = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, itemMark);
			stmt.setInt(2, id);
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
