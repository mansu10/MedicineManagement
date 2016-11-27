package com.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.medicine.cl.DAOManager;
import com.medicine.entities.Stowage;
import com.medicine.util.MedicineRuntimeException;

public class StowageDAO extends DAOManager {

	private Connection getConn() throws SQLException{
		return DBHelper.getConn();
	}
	
	public int addStowage(Stowage stowage) {
		int count = 0;
		PreparedStatement stmt = null;

		String sql = "insert into tbl_stowage (stowage_code,car_code,create_time,volume_percent,weight_percent,transport_route) "
				+ "values (?,?,?,?,?,?)";
		try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setString(1, stowage.getStowageCode());
			stmt.setString(2, stowage.getCarCode());
			stmt.setObject(3, new Timestamp(System.currentTimeMillis()));
			stmt.setFloat(4, stowage.getVolumePercent());
			stmt.setFloat(5, stowage.getWeightPercent());
			stmt.setString(6, stowage.getTransportRoute());
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
	public int updateStowage(Stowage stowage) {
		int count = 0;
		PreparedStatement stmt = null;
		
		String sql = "update tbl_stowage set operator=?,load_time=?,departure_time=? "
				+ "where stowage_code=?";
		try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setString(1, stowage.getOperator());
			stmt.setObject(2, stowage.getLoadTime());
			stmt.setObject(3, stowage.getDepartureTime());
			stmt.setString(4, stowage.getStowageCode());
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
	
	public List<Stowage> findAllStowages(String stowageCode, String carCode) {
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Stowage> stowages = new ArrayList<Stowage>();
		String sql = "select * from tbl_stowage where 1=1 ";
		
		if (stowageCode != null && !"".equals(stowageCode.trim())) {
			sql += "and stowage_code = ? ";
		}
		if (carCode != null && !"".equals(carCode.trim())) {
			sql += "and car_code = ? ";
		}
		try {
			stmt = conn.prepareStatement(sql);
			int length = 1;
			if (stowageCode != null && !"".equals(stowageCode.trim())) {
				stmt.setString(length, stowageCode);
				length++;
			}
			if (carCode != null && !"".equals(carCode.trim())) {
				stmt.setString(length, carCode);
				length++;
			}
			rs = stmt.executeQuery();
			while(rs.next()) {
				Stowage stowage = new Stowage();
				stowage.setId(rs.getInt("id"));
				stowage.setStowageCode(rs.getString("stowage_code"));
				stowage.setCarCode(rs.getString("car_code"));
				stowage.setTransportRoute(rs.getString("transport_route"));
				stowage.setVolumePercent(rs.getFloat("volume_percent"));
				stowage.setWeightPercent(rs.getFloat("weight_percent"));
				stowages.add(stowage);
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
		return stowages;
	}
	
	public Stowage findStowageByCode(String stowageCode) {
		Stowage stowage = new Stowage();
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from tbl_stowage where 1=1 ";
		if (stowageCode != null && !"".equals(stowageCode.trim())) {
			sql += "and stowage_code = ? ";
		}
		try {
			stmt = conn.prepareStatement(sql);
			int length = 1;
			if (stowageCode != null && !"".equals(stowageCode.trim())) {
				stmt.setString(length, stowageCode);
				length++;
			}
			rs = stmt.executeQuery();
			while(rs.next()) {
				stowage.setId(rs.getInt("id"));
				stowage.setStowageCode(rs.getString("stowage_code"));
				stowage.setCarCode(rs.getString("car_code"));
				stowage.setTransportRoute(rs.getString("transport_route"));
				stowage.setVolumePercent(rs.getFloat("volume_percent"));
				stowage.setWeightPercent(rs.getFloat("weight_percent"));
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
		return stowage;
	}
	
	public int deleteStowageById(int id){
		int count = 0;
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		String sql = "delete from tbl_stowage where id = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
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
