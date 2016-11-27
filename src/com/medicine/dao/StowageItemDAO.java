package com.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.medicine.cl.DAOManager;
import com.medicine.entities.StowageItem;
import com.medicine.util.MedicineRuntimeException;

public class StowageItemDAO extends DAOManager {

	private Connection getConn() throws SQLException{
		return DBHelper.getConn();
	}
	
	public int addStowageItem(StowageItem stowageItem) {
		int count = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "insert into tbl_stowage_item (stowage_code,invoice_code,invoice_time,signed_time,signed_record) "
				+ "values (?,?,?,?,?)";
		try {
			stmt = this.getConn().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, stowageItem.getStowageCode());
			stmt.setString(2, stowageItem.getInvoiceCode());
			stmt.setObject(3, stowageItem.getInvoiceTime());
			stmt.setObject(4, stowageItem.getSignedTime());
			stmt.setString(5, stowageItem.getSignedRecord());
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException ex) {
			throw new MedicineRuntimeException(ex.getMessage());
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
	
	public int countStowageItemByCode(String stowageCode) {
		int count = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select count(distinct invoice_code) count from tbl_stowage_item "
				+ "where stowage_code = ?";
		try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setString(1, stowageCode);
			rs = stmt.executeQuery();
			while(rs.next()) {
				count = rs.getInt("count");
			}
		} catch (SQLException ex) {
			throw new MedicineRuntimeException(ex.getMessage());
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
	
	public List<StowageItem> findStowageItemsByCode(String stowageCode) {
		List<StowageItem> stowageItems = new ArrayList<StowageItem>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select  invoice_code,invoice_time,signed_time from tbl_stowage_item "
				+ "where stowage_code = ? order by sequence";
		try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setString(1, stowageCode);
			rs = stmt.executeQuery();
			while(rs.next()) {
				StowageItem stowageItem = new StowageItem();
				stowageItem.setInvoiceCode(rs.getString("invoice_code"));
				stowageItem.setInvoiceTime(rs.getTimestamp("invoice_time"));
				stowageItem.setSignedTime(rs.getTimestamp("signed_time"));
				stowageItems.add(stowageItem);
			}
		} catch (SQLException ex) {
			throw new MedicineRuntimeException(ex.getMessage());
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
		return stowageItems;
	}
	
	public int updateStowageItem(StowageItem stowageItem) {
		int count = 0;
		PreparedStatement stmt = null;
		String sql = "update tbl_stowage_item set invoice_time=?,signed_time=? where stowage_code = ?";
		try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setObject(1, stowageItem.getInvoiceTime());
			stmt.setObject(2, stowageItem.getSignedTime());
			stmt.setString(3, stowageItem.getStowageCode());
			stmt.executeUpdate();
			count++;
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
