package com.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.medicine.entities.ReceiptAcceptanceRecord;
import com.medicine.util.MedicineRuntimeException;

public class ReceiptAcceptanceRecordDAO {
	
	public int addReceiptAcceptanceRecord(ReceiptAcceptanceRecord record){
		int count = 0;
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;

		String sql = "insert into tbl_receipt_acceptance_record (receipt_code,receipt_time,receipt_depot,receiver,goods_description,supplier_name,supplier_contacts,"
				+ "supplier_tel,delivery_name,delivery_contacts,delivery_tel,acceptance_record,acceptor,acceptance_result,receipt_type,source,memo,receipt_certificate,certificate_number) "
				+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, record.getReceiptCode());
			stmt.setObject(2, new Timestamp(record.getReceiptTime().getTime()));
			stmt.setString(3, record.getReceiptDepot());
			stmt.setString(4, record.getReceiver());
			stmt.setString(5, record.getGoodsDescription());
			stmt.setString(6, record.getSupplierName());
			stmt.setString(7, record.getSupplierContacts());
			stmt.setString(8, record.getSupplierTel());
			stmt.setString(9, record.getDeliveryName());
			stmt.setString(10, record.getDeliveryContactes());
			stmt.setString(11, record.getDeliveryTel());
			stmt.setString(12, record.getAcceptanceRecord());
			stmt.setString(13, record.getAcceptor());
			stmt.setString(14, record.getAcceptanceResult());
			stmt.setInt(15, record.getReceiptType());
			stmt.setString(16, record.getSource());
			stmt.setString(17, record.getMemo());
			stmt.setString(18, record.getReceiptCertificate());
			stmt.setString(19, record.getCertificateNumber());
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
	
	public List<ReceiptAcceptanceRecord> queryAllReceiptAcceptanceRecordsByReceiptTime(String receiptTimeStartStr, String receiptTimeEndStr){
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ReceiptAcceptanceRecord> records = new ArrayList<ReceiptAcceptanceRecord>();
		String sql = "select id,receipt_time,receipt_code,goods_description,receipt_type,supplier_name,delivery_name "
				+ "from tbl_receipt_acceptance_record "
				+ "where 1=1 ";
		if (receiptTimeStartStr != null && !"".equals(receiptTimeStartStr.trim()) 
				&& receiptTimeEndStr != null && !"".equals(receiptTimeEndStr.trim())) {
			sql += "and receipt_time >= ? and receipt_time <= ?";
		}
		try {
			stmt = conn.prepareStatement(sql);
			if (receiptTimeStartStr != null && !"".equals(receiptTimeStartStr.trim()) 
					&& receiptTimeEndStr != null && !"".equals(receiptTimeEndStr.trim())) {
				stmt.setTimestamp(1, Timestamp.valueOf(receiptTimeStartStr));
				stmt.setTimestamp(2, Timestamp.valueOf(receiptTimeEndStr));
			}
			rs = stmt.executeQuery();
			while (rs.next()) {
				ReceiptAcceptanceRecord record = new ReceiptAcceptanceRecord();
				record.setId(rs.getInt("id"));
				record.setReceiptTime(rs.getTimestamp("receipt_time"));
				record.setReceiptType(rs.getInt("receipt_type"));
				record.setReceiptCode(rs.getString("receipt_code"));
				record.setGoodsDescription(rs.getString("goods_description"));
				record.setSupplierName(rs.getString("supplier_name"));
				record.setDeliveryName(rs.getString("delivery_name"));

				records.add(record);
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
		return records;
	}
	
	public ReceiptAcceptanceRecord queryRecordByCode(String receiptCode) {
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ReceiptAcceptanceRecord record = new ReceiptAcceptanceRecord();
		String sql = "select id,receipt_code,receipt_type,receiver,receipt_depot,receipt_time,goods_description,source,"
				+ "supplier_name,supplier_contacts,supplier_tel,delivery_name,delivery_contacts,delivery_tel,receipt_certificate,certificate_number,memo "
				+ "from tbl_receipt_acceptance_record "
				+ "where receipt_code = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, receiptCode);
			rs = stmt.executeQuery();
			while (rs.next()) {
				record.setId(rs.getInt("id"));
				record.setReceiptTime(rs.getTimestamp("receipt_time"));
				record.setReceiptType(rs.getInt("receipt_type"));
				record.setReceiptCode(rs.getString("receipt_code"));
				record.setGoodsDescription(rs.getString("goods_description"));
				record.setSupplierName(rs.getString("supplier_name"));
				record.setDeliveryName(rs.getString("delivery_name"));
				record.setReceiver(rs.getString("receiver"));
				record.setSource(rs.getString("source"));
				record.setSupplierName(rs.getString("supplier_name"));
				record.setSupplierContacts(rs.getString("supplier_contacts"));
				record.setSupplierTel(rs.getString("supplier_tel"));
				record.setDeliveryName(rs.getString("delivery_name"));
				record.setDeliveryContactes(rs.getString("delivery_contacts"));
				record.setDeliveryTel(rs.getString("delivery_tel"));
				record.setReceiptCertificate(rs.getString("receipt_certificate"));
				record.setCertificateNumber(rs.getString("certificate_number"));
				record.setReceiptDepot(rs.getString("receipt_depot"));
				record.setMemo(rs.getString("memo"));
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
		return record;
	}
	
	public String findRecriptDepotByProductCode(int productCode) {
		String receiptDepot = null;
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select receipt_depot "
				+ "from tbl_receipt_acceptance_record "
				+ "where receipt_code in "
				+ "(select distinct receipt_code "
				+ "from tbl_receipt_item "
				+ "where product_code = ?) "
				+ "group by receipt_code";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, productCode);
			rs = stmt.executeQuery();
			while(rs.next()){
				receiptDepot = rs.getString("receipt_depot");
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
		return receiptDepot;
	}
}
