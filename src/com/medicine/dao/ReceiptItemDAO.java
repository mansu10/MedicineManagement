package com.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.medicine.entities.ReceiptAcceptanceRecord;
import com.medicine.entities.ReceiptItem;
import com.medicine.util.MedicineRuntimeException;

public class ReceiptItemDAO {

	public int addReceiptItem(ReceiptItem item){
		int count = 0;
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;

		String sql = "insert into tbl_receipt_item "
				+ "(receipt_code,product_code,purchase_price,box_mark,box_quantity,quantity,factory_time,production_batch,memo) "
				+ "values (?,?,?,?,?,?,?,?,?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, item.getReceiptCode());
			stmt.setInt(2, item.getProductCode());
			stmt.setFloat(3, item.getPurchasePrice());
			stmt.setInt(4, item.getBoxMark());
			stmt.setInt(5, item.getBoxQuantity());
			stmt.setInt(6, item.getQuantity());
			stmt.setObject(7, new Timestamp(item.getFactoryTime().getTime()));
			stmt.setString(8, item.getProductionBatch());
			stmt.setString(9, item.getMemo());
			
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
	
	public List<ReceiptItem> queryRecordItemByCode(String receiptCode) {
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ReceiptItem> items = new ArrayList<ReceiptItem>();
		String sql = "select id,receipt_code,product_code,purchase_price,box_mark,box_quantity,quantity,factory_time,production_batch,memo "
				+ "from tbl_receipt_item "
				+ "where receipt_code = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, receiptCode);
			rs = stmt.executeQuery();
			while (rs.next()) {
				ReceiptItem receiptItem = new ReceiptItem();
				receiptItem.setId(rs.getInt("id"));
				receiptItem.setReceiptCode(rs.getString("receipt_code"));
				receiptItem.setProductCode(rs.getInt("product_code"));
				receiptItem.setPurchasePrice(rs.getFloat("purchase_price"));
				receiptItem.setBoxMark(rs.getInt("box_mark"));
				receiptItem.setBoxQuantity(rs.getInt("box_quantity"));
				receiptItem.setQuantity(rs.getInt("quantity"));
				receiptItem.setFactoryTime(rs.getTimestamp("factory_time"));
				receiptItem.setProductionBatch(rs.getString("production_batch"));
				receiptItem.setMemo(rs.getString("memo"));
				items.add(receiptItem);
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
}
