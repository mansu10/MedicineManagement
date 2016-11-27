package com.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.medicine.entities.PickListItem;
import com.medicine.util.MedicineRuntimeException;

public class PickListItemDAO {
	private Connection getConn() throws SQLException{
		return DBHelper.getConn();
	}
	
	public int addPickListItem(PickListItem pickListItem) {
		int count = 0;
		PreparedStatement stmt = null;
		String sql = "insert into tbl_pick_list_item (pick_list_code,product_code) "
				+ "values (?,?)";
		try {
			stmt = this.getConn().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, pickListItem.getPickListCode());
			stmt.setString(2, pickListItem.getProductCode());
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

	public List<PickListItem> findProductCodesByOrderCode(String orderCode) {
		List<PickListItem> pickListItems = new ArrayList<PickListItem>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select product_code,pick_list_record,check_record from tbl_pick_list_item where pick_list_code in (select pick_list_code from tbl_pick_list where order_code =  ?)";
		try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setString(1, orderCode);
			rs = stmt.executeQuery();
			while(rs.next()) {
				PickListItem pickListItem = new PickListItem();
				pickListItem.setProductCode(rs.getString("product_code"));
				pickListItem.setPickListRecord(rs.getString("pick_list_record"));
				pickListItem.setCheckRecord(rs.getString("check_record"));
				pickListItems.add(pickListItem);
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
		return pickListItems;
	}
	
	//update tbl_pick_list_item set pick_list_record = '测试' where pick_list_code in (select pick_list_code from tbl_pick_list where order_code = 201601010010) and product_code = '10000001'
	
	public int updatePickListItem(String orderCode, PickListItem pickListItem) {
		int count = 0;
		PreparedStatement stmt = null;
		String sql = "update tbl_pick_list_item set pick_list_record = ?,check_record=? where pick_list_code in (select pick_list_code from tbl_pick_list where order_code = ?) and product_code = ?";
		try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setString(1, pickListItem.getPickListRecord());
			stmt.setString(2, pickListItem.getCheckRecord());
			stmt.setString(3, orderCode);
			stmt.setString(4, pickListItem.getProductCode());
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
