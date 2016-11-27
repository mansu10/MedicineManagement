package com.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.medicine.entities.ContainerBox;
import com.medicine.util.MedicineRuntimeException;

public class ContainerBoxDAO {

	private Connection getConn() throws SQLException{
		return DBHelper.getConn();
	}
	
	public ContainerBox findBoxByNumber(String boxNumber) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ContainerBox containerBox = new ContainerBox();
		String sql = "select id,box_number,create_time,box_status,box_name,box_volume,locator_code "
				+ "from tbl_container_box "
				+ "where box_number = ?";
		
		try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setString(1, boxNumber);
			rs = stmt.executeQuery();
			if (rs.next()) {
				containerBox.setId(rs.getInt("id"));
				containerBox.setBoxNumber(rs.getString("box_number"));
				containerBox.setCreateTime(rs.getTimestamp("create_time"));
				containerBox.setBoxStatus(rs.getInt("box_status"));
				containerBox.setBoxName(rs.getString("box_name"));
				containerBox.setBoxVolume(rs.getFloat("box_volume"));
				containerBox.setLocatorCode(rs.getString("locator_code"));
			}
		} catch (SQLException ex) {
			throw new MedicineRuntimeException(ex.getMessage());
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception ex) {
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException ex) {
				}
			}
		}
		return containerBox;
	}
}
