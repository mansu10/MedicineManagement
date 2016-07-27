package com.medicine.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.medicine.entities.Order;

public class OrderDAO {

	static DBHelper db = null; 
	static ResultSet ret = null;
	List<Order> orders  = null;
	Order order = null;
	String sql = null;
	
	public List<Order> findAll(String orderCode){
		sql = "select id,order_code,customer_code,product_id,order_type,order_time,delivery_time,order_status,receiver,receipt_address,tel,receipt_type,"
				+ "invoice_message,ship_method,pay_method,level,ship_time from tbl_order where order_code = " + orderCode;
		db = new DBHelper(sql);
		orders = new ArrayList<Order>();
		try {  
            ret = db.pst.executeQuery(); 
            while (ret.next()) {  
            	order= new Order();
            	order.setId(ret.getInt("id"));
            	order.setOrderCode(ret.getString("order_code"));
            	order.setCustomerCode(ret.getString("customer_code"));
            	order.setProductIds(ret.getString("product_ids"));
            	order.setOrderType(ret.getString("order_type"));
            	order.setOrderTime(ret.getTimestamp("order_time"));
            	order.setDeliveryTime(ret.getTimestamp("delivery_time"));
            	order.setOrderStatus(ret.getInt("order_status"));
            	order.setReceiver(ret.getString("receiver"));
            	order.setReceiptAddress(ret.getString("receipt_address"));
            	order.setTel(ret.getString("tel"));
            	order.setReceiptType(ret.getInt("receipt_type"));
            	order.setInvoiceMessage(ret.getString("invoice_message"));
            	order.setShipMethod(ret.getInt("ship_method"));
            	order.setPayMethod(ret.getInt("pay_method"));
            	order.setShipTime(ret.getTimestamp("ship_time"));
            	order.setLevel(ret.getInt("level"));
            	
                orders.add(order);
            }
            ret.close();  
            db.close(); 
        } catch (SQLException e) {  
            e.printStackTrace();  
        } 
		return orders;
	}
	
	public int addOrder(Order order){
		int count = 0;
		sql = "insert into tbl_order (order_code,customer_code,product_ids,order_type,"
				+ "order_time,delivery_time,order_status,receiver,receipt_address,tel,receipt_type,"
				+ "invoice_message,ship_method,pay_method,level,customer_name,memo)"
				+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		db = new DBHelper(sql);
		try {  
			db.pst.setString(1,order.getOrderCode());
			db.pst.setString(2, order.getCustomerCode());
			db.pst.setString(3, order.getProductIds());
			db.pst.setString(4, order.getOrderType());
			db.pst.setDate(5, new Date(order.getOrderTime().getTime()));
			db.pst.setDate(6, new Date(order.getDeliveryTime().getTime()));
			db.pst.setInt(7, order.getOrderStatus());
			db.pst.setString(8, order.getReceiver());
			db.pst.setString(9, order.getReceiptAddress());
			db.pst.setString(10, order.getTel());
			db.pst.setInt(11, order.getReceiptType());
			db.pst.setString(12, order.getInvoiceMessage());
			db.pst.setInt(13, order.getShipMethod());
			db.pst.setInt(14, order.getPayMethod());
			db.pst.setInt(15, order.getLevel());
			db.pst.setString(16, order.getCustomerName());
			db.pst.setString(17, order.getMemo());
			
			count = db.pst.executeUpdate();
            db.close(); 
        } catch (SQLException e) {  
            e.printStackTrace();  
        } 
		return count;
	}
	
//	public int deleteOrderById(long id){
//		 int count = 0;
//		 sql = "delete from tbl_order where id = " + id;
//		 db = new DBHelper(sql);
//		 try {  
//			    db.pst.setLong(1, id);
//	            count = db.pst.executeUpdate();
//	            db.close();
//	        } catch (SQLException e) {  
//	            e.printStackTrace();  
//	        } 
//		 return count;
//	}
//	
//	public int updateOrderById(Order order){
//		 int count = 0;
//		 sql = "update tbl_order set order_code = ?,customer_code=?,product_id=?,order_type=?,"
//		 		+ "order_time=?,delivery_time=?,order_status=?,receives=?,receipt_address=?,"
//		 		+ "tel=?,receipt_type=?,invoice_message=?,ship_method=?,pay_method=?,ship_time=?,level=?"
//		 		+ "where id = ?";
//		 db = new DBHelper(sql);
//		 try {  
//			    db.pst.setString(1, order.getOrderCode());
//			    db.pst.setString(2, order.getCustomerCode());
//			    db.pst.setInt(3, order.getProductId());
//			    db.pst.setString(4, order.getOrderType());
//			    db.pst.setDate(5, new Date(order.getOrderTime().getTime()));
//			    db.pst.setDate(6, new Date(order.getDeliveryTime().getTime()));
//			    db.pst.setInt(7, order.getOrderStatus());
//			    db.pst.setString(8, order.getReceiver());
//			    db.pst.setString(9, order.getReceiptAddress());
//			    db.pst.setString(10, order.getTel());
//			    db.pst.setInt(11, order.getReceiptType());
//			    db.pst.setString(12, order.getInvoiceMessage());
//			    db.pst.setInt(13, order.getShipMethod());
//			    db.pst.setInt(14, order.getPayMethod());
//			    db.pst.setDate(15, new Date(order.getShipTime().getTime()));
//			    db.pst.setInt(16, order.getLevel());
//			    db.pst.setLong(17, order.getId());
//			    
//	            count = db.pst.executeUpdate();
//	            db.close();
//	        } catch (SQLException e) {  
//	            e.printStackTrace();  
//	        } 
//		 return count;
//	}
	
}
