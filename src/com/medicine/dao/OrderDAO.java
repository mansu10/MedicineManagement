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
	
	public List<Order> findAllOrders() {
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Order> orders = new ArrayList<Order>();

		String sql = "select id,order_code,customer_code,item_ids,order_type,order_time,delivery_time,order_status,receiver,receipt_address,tel,receipt_type,"
				+ "invoice_message,ship_method,pay_method,level,ship_time,memo,customer_name,order_progress,old_order_id,order_mark "
				+ "from tbl_order";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setOrderCode(rs.getString("order_code"));
				order.setCustomerCode(rs.getString("customer_code"));
				order.setItemIds(rs.getString("item_ids"));
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
				order.setMemo(rs.getString("memo"));
				order.setCustomerName(rs.getString("customer_name"));
				order.setOrderProgress(rs.getInt("order_progress"));
				order.setOldOrderId(rs.getInt("old_order_id"));
				order.setOrderMark(rs.getInt("order_mark"));
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

	public List<Order> findOrderByDeliveryTime(String deliveryTimeStr) {
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Order> orders = new ArrayList<Order>();
		String sql = "select id,order_code,customer_code,product_ids,order_type,order_time,delivery_time,order_status,"
				+ "receiver,receipt_address,tel,receipt_type,invoice_message,ship_method,pay_method,level,ship_time "
				+ "from tbl_order "
				+ "where delivery_time >= ? and delivery_time <= ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
			stmt.setTimestamp(2, Timestamp.valueOf(deliveryTimeStr));
			rs = stmt.executeQuery();
			while (rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setOrderCode(rs.getString("order_code"));
				order.setCustomerCode(rs.getString("customer_code"));
				order.setItemIds(rs.getString("item_ids"));
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
	public List<Order> findOrderByStowageCode(String stowageCode) {
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Order> orders = new ArrayList<Order>();
		String sql = "select * from tbl_order "
				+ "where order_code = "
				+ "(select order_code "
				+ "from tbl_invoice "
				+ "where invoice_code "
				+ "in (select invoice_code "
				+ "from tbl_stowage_item "
				+ "where stowage_code = ?))";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, stowageCode);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setOrderCode(rs.getString("order_code"));
				order.setOrderType(rs.getString("order_type"));
				order.setDeliveryTime(rs.getTimestamp("delivery_time"));
				order.setReceiver(rs.getString("receiver"));
				order.setReceiptAddress(rs.getString("receipt_address"));
				order.setTel(rs.getString("tel"));
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
				+ "invoice_message,ship_method,pay_method,level,customer_name,memo,order_progress,item_ids,old_order_id,out_processing)"
				+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, order.getOrderCode());
			stmt.setString(2, order.getCustomerCode());
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
			stmt.setInt(17, 0);
			stmt.setString(18, order.getItemIds());
			stmt.setInt(19, order.getOldOrderId());
			stmt.setInt(20, order.getOutProcessing());
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

	public List<Order> findOrderByDto(String orderTimeStart, String orderTimeEnd, String orderStatus, String orderProgress) {
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Order> orders = new ArrayList<Order>();
//		Timestamp startOrderTime =null;
//		Timestamp endOrderTime =null;
//		try {
//			startOrderTime = new Timestamp(sdf.parse(orderTimeStart).getTime());
//			endOrderTime = new Timestamp(sdf.parse(orderTimeEnd).getTime());
//		} catch (ParseException e) {
//			throw new MedicineRuntimeException(e);
//		}
		String sql = "select id,order_code,customer_code,product_ids,order_type,order_time,delivery_time,order_status,"
				+ "receiver,receipt_address,tel,receipt_type,invoice_message,ship_method,pay_method,level,ship_time "
				+ "from tbl_order "
				+ "where 1=1 ";
		
		if (orderTimeStart != null && !"".equals(orderTimeStart.trim())) {
			sql += "and order_time >= ? ";
		}
		if (orderTimeEnd != null && !"".equals(orderTimeEnd.trim())) {
			sql += "and order_time <= ? ";
		}
		if (orderStatus != null && !"".equals(orderStatus.trim())) {
			sql += "and order_status = ? ";
		}
		if (orderProgress != null && !"".equals(orderProgress.trim())) {
			sql += "and order_progress = ? ";
		}
		try {
			stmt = conn.prepareStatement(sql);
			int length = 1;
			if (orderTimeStart != null && !"".equals(orderTimeStart.trim())) {
				stmt.setTimestamp(length, Timestamp.valueOf(orderTimeStart));
				length++;
			}
			if (orderTimeEnd != null && !"".equals(orderTimeEnd.trim())) {
				stmt.setTimestamp(length, Timestamp.valueOf(orderTimeEnd));
				length++;
			}
			if (orderStatus != null && !"".equals(orderStatus.trim())) {
				stmt.setInt(length, Integer.parseInt(orderStatus));
				length++;
			}
			if (orderProgress != null && !"".equals(orderProgress.trim())) {
				stmt.setInt(length, Integer.parseInt(orderProgress));
			}
			rs = stmt.executeQuery();
			while (rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setOrderCode(rs.getString("order_code"));
				order.setCustomerCode(rs.getString("customer_code"));
				order.setItemIds(rs.getString("item_ids"));
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

	public Order findOrderByOrderCode(String orderCode){
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Order order = new Order();
		
		String sql = "select id,order_code,customer_code,item_ids,order_type,order_time,delivery_time,order_status,"
				+ "receiver,receipt_address,tel,receipt_type,invoice_message,ship_method,pay_method,level,ship_time "
				+ "from tbl_order "
				+ "where order_code = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, orderCode);
			rs = stmt.executeQuery();
			while (rs.next()) {
				order.setId(rs.getInt("id"));
				order.setOrderCode(rs.getString("order_code"));
				order.setCustomerCode(rs.getString("customer_code"));
				order.setItemIds(rs.getString("item_ids"));
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
		return order;
	}

	public int updateOrderMarkById(int id,int orderMark){
		int count = 0;
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;

		String sql = "update tbl_order "
				+ "set order_mark = ? "
				+ "where id = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, orderMark);
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
	
	public List<Order> queryAllPassOrders(){
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Order> orders = new ArrayList<Order>();

		String sql = "select id,order_code,customer_code,receipt_address "
				+ "from tbl_order "
				+ "where order_status = 2";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setOrderCode(rs.getString("order_code"));
				order.setCustomerCode(rs.getString("customer_code"));
				order.setReceiptAddress(rs.getString("receipt_address"));
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

	public List<Order> findOrders(String orderCode, String demandAgencyName, String orderTimeStart, String orderTimeEnd){
		List<Order> orders = new ArrayList<Order>();
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select id,order_code,customer_code,order_time,delivery_time,level "
				+ "from tbl_order "
				+ "where 1=1 ";
		
		if (orderTimeStart != null && !"".equals(orderTimeStart.trim())) {
			sql += "and order_time >= ? ";
		}
		if (orderTimeEnd != null && !"".equals(orderTimeEnd.trim())) {
			sql += "and order_time <= ? ";
		}
		if (demandAgencyName != null && !"".equals(demandAgencyName.trim())) {
			sql += "and customer_code = (select demand_agency_code from tbl_demand_agency where demand_agency_name = ?) ";
		}
		if (orderCode != null && !"".equals(orderCode.trim())) {
			sql += "and order_code = ? ";
		}
		try {
			stmt = conn.prepareStatement(sql);
			int length = 1;
			if (orderTimeStart != null && !"".equals(orderTimeStart.trim())) {
				stmt.setTimestamp(length, Timestamp.valueOf(orderTimeStart));
				length++;
			}
			if (orderTimeEnd != null && !"".equals(orderTimeEnd.trim())) {
				stmt.setTimestamp(length, Timestamp.valueOf(orderTimeEnd));
				length++;
			}
			if (demandAgencyName != null && !"".equals(demandAgencyName.trim())) {
				stmt.setString(length, demandAgencyName);
				length++;
			}
			if (orderCode != null && !"".equals(orderCode.trim())) {
				stmt.setString(length, orderCode);
			}
			rs = stmt.executeQuery();
			while (rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setOrderCode(rs.getString("order_code"));
				order.setCustomerCode(rs.getString("customer_code"));
				order.setOrderTime(rs.getTimestamp("order_time"));
				order.setDeliveryTime(rs.getTimestamp("delivery_time"));
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
}
