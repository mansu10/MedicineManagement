package com.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.medicine.entities.PickList;
import com.medicine.util.MedicineRuntimeException;

public class PickListDAO {

	private Connection getConn() throws SQLException{
		return DBHelper.getConn();
	}
	
	public int addPickList(PickList pickList) {
		int count = 0;
		PreparedStatement stmt = null;
		String sql = "insert into tbl_pick_list (order_code,pick_list_code,create_time,pick_list_depot) "
				+ "values (?,?,?,?)";
		try {
			stmt = this.getConn().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, pickList.getOrderCode());
			stmt.setString(2, pickList.getPickListCode());
			stmt.setObject(3, new Timestamp(System.currentTimeMillis()));
			stmt.setString(4, pickList.getPickListDepot());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				count = rs.getInt(1);
			}
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
	
	public List<PickList> findPickListByOrderCode(String orderCode) {
		List<PickList> pickLists = new ArrayList<PickList>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from tbl_pick_list where order_code = ?";
		try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setString(1, orderCode);
			rs = stmt.executeQuery();
			while(rs.next()) {
				 PickList pickList = new PickList();
				 pickList.setOrderCode(rs.getString("order_code"));
				 pickList.setPickListCode(rs.getString("pick_list_code"));
				 pickList.setCreateTime(rs.getTimestamp("create_time"));
				 pickList.setShipTime(rs.getTimestamp("ship_time"));
				 pickLists.add(pickList);
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
		return pickLists;
	}
	
	public int updateShipTime(String pickListCode){
		int count = 0;
		PreparedStatement stmt = null;
		String sql = "update tbl_pick_list set ship_time = ? where pick_list_code = ?";
		try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setObject(1, new Date());
			stmt.setString(2, pickListCode);
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
	
	public List<String> findPickListCodesByOrderCode(String orderCode) {
		List<String> pickListCodes = new ArrayList<String>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select pick_list_code from tbl_pick_list where order_code = ?";
		try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setString(1, orderCode);
			rs = stmt.executeQuery();
			while(rs.next()) {
				pickListCodes.add(rs.getString("pick_list_code"));
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
		return pickListCodes;
	}
}
