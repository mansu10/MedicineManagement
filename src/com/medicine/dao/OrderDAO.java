package com.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.medicine.entities.Order;
import com.medicine.util.MedicineRuntimeException;

public class OrderDAO {

	public List<Order> findAll(String orderCode) {
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Order> orders = new ArrayList<Order>();

		String sql = "select id,order_code,customer_code,product_ids,order_type,order_time,delivery_time,order_status,receiver,receipt_address,tel,receipt_type,"
				+ "invoice_message,ship_method,pay_method,level,ship_time from tbl_order where order_code = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, orderCode);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setOrderCode(rs.getString("order_code"));
				order.setCustomerCode(rs.getString("customer_code"));
				order.setProductIds(rs.getString("product_ids"));
				order.setOrderType(rs.getString("order_type"));
				order.setOrderTime(rs.getTimestamp("order_time"));
				order.setDeliveryTime(rs.getTimestamp("delivery_time"));
				order.setOrderStatus(rs.getInt("order_status"));
				order.setReceiver(rs.getString("receiver"));
				order.setReceiptAddress(rs.getString("receipt_address"));
				order.setTel(rs.getString("tel"));
				order.setReceiptType(rs.getInt("receipt_type"));
				order.setInvoiceMessage(rs.getString("invoice_message"));
				order.setShipMethod(rs.getInt("ship_method"));
				order.setPayMethod(rs.getInt("pay_method"));
				order.setShipTime(rs.getTimestamp("ship_time"));
				order.setLevel(rs.getInt("level"));

				orders.add(order);
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
		return orders;
	}

	public int addOrder(Order order) {
		int count = 0;
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;

		String sql = "insert into tbl_order (order_code,customer_code,order_type,"
				+ "order_time,delivery_time,order_status,receiver,receipt_address,tel,receipt_type,"
				+ "invoice_message,ship_method,pay_method,level,customer_name,memo)"
				+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, order.getOrderCode());
			stmt.setString(2, order.getCustomerCode());
//			stmt.setString(3, order.getProductIds());
			stmt.setString(3, order.getOrderType());
			stmt.setObject(4, new Timestamp(order.getOrderTime().getTime()));
			stmt.setObject(5, new Timestamp(order.getDeliveryTime().getTime()));
			stmt.setInt(6, order.getOrderStatus());
			stmt.setString(7, order.getReceiver());
			stmt.setString(8, order.getReceiptAddress());
			stmt.setString(9, order.getTel());
			stmt.setInt(10, order.getReceiptType());
			stmt.setString(11, order.getInvoiceMessage());
			stmt.setInt(12, order.getShipMethod());
			stmt.setInt(13, order.getPayMethod());
			stmt.setInt(14, order.getLevel());
			stmt.setString(15, order.getCustomerName());
			stmt.setString(16, order.getMemo());
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

	// public int deleteOrderById(long id){
	// int count = 0;
	// sql = "delete from tbl_order where id = " + id;
	// db = new DBHelper(sql);
	// try {
	// stmt.setLong(1, id);
	// count = stmt.executeUpdate();
	// db.close();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// rsurn count;
	// }
	//
	// public int updateOrderById(Order order){
	// int count = 0;
	// sql = "update tbl_order set order_code =
	// ?,customer_code=?,product_id=?,order_type=?,"
	// +
	// "order_time=?,delivery_time=?,order_status=?,receives=?,receipt_address=?,"
	// +
	// "tel=?,receipt_type=?,invoice_message=?,ship_method=?,pay_method=?,ship_time=?,level=?"
	// + "where id = ?";
	// db = new DBHelper(sql);
	// try {
	// stmt.setString(1, order.getOrderCode());
	// stmt.setString(2, order.getCustomerCode());
	// stmt.setInt(3, order.getProductId());
	// stmt.setString(4, order.getOrderType());
	// stmt.setDate(5, new Date(order.getOrderTime().getTime()));
	// stmt.setDate(6, new Date(order.getDeliveryTime().getTime()));
	// stmt.setInt(7, order.getOrderStatus());
	// stmt.setString(8, order.getReceiver());
	// stmt.setString(9, order.getReceiptAddress());
	// stmt.setString(10, order.getTel());
	// stmt.setInt(11, order.getReceiptType());
	// stmt.setString(12, order.getInvoiceMessage());
	// stmt.setInt(13, order.getShipMethod());
	// stmt.setInt(14, order.getPayMethod());
	// stmt.setDate(15, new Date(order.getShipTime().getTime()));
	// stmt.setInt(16, order.getLevel());
	// stmt.setLong(17, order.getId());
	//
	// count = stmt.executeUpdate();
	// db.close();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// rsurn count;
	// }

}
