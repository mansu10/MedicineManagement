package com.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.medicine.entities.Depot;
import com.medicine.util.MedicineRuntimeException;

public class DepotDAO {

	private Connection getConn() throws SQLException {
		return DBHelper.getConn();
	}
	
	public List<Depot> findAllDepots() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Depot> depots = new ArrayList<Depot>();
		String sql = "select id,depot_code,depot_name,depot_type,depot_address "
				+ "from tbl_depot";
		try {
			stmt = this.getConn().prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Depot depot = new Depot();
				depot.setId(rs.getInt("id"));
				depot.setDepotCode(rs.getInt("depot_code"));
				depot.setDepotName(rs.getString("depot_name"));
				depot.setDepotAddress(rs.getString("depot_address"));
				depot.setDepotType(rs.getString("depot_type"));
				
				depots.add(depot);
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
		
		return depots;
	} 
	
	public Depot findDepotByCode(int depotCode){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Depot depot = new Depot();
		String sql = "select id,depot_code,depot_name,depot_type,depot_address,principal,depot_length,depot_width,depot_heigth,depot_volume "
				+ "from tbl_depot "
				+ "where depot_code = ?";
		
		try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setInt(1, depotCode);
			rs = stmt.executeQuery();
			if (rs.next()) {
				depot.setId(rs.getInt("id"));
				depot.setDepotCode(rs.getInt("depot_code"));
				depot.setDepotName(rs.getString("depot_name"));
				depot.setDepotAddress(rs.getString("depot_address"));
				depot.setDepotType(rs.getString("depot_type"));
				depot.setPrincipal(rs.getString("principal"));
				depot.setDepotLength(rs.getInt("depot_length"));
				depot.setDepotWidth(rs.getInt("depot_width"));
				depot.setDepotHeigth(rs.getInt("depot_heigth"));
				depot.setDepotVolume(rs.getInt("depot_volume"));
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
		return depot;
	}
	
	public int addDepot(Depot depot){
		int count = 0;
		PreparedStatement stmt = null;
		String sql = "insert into tbl_depot (depot_code,depot_name,depot_type,depot_address,principal,depot_length,depot_width,depot_heigth,depot_volume) "
				+ "values (?,?,?,?,?,?,?,?,?)";
		try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setInt(1, depot.getDepotCode());
			stmt.setString(2, depot.getDepotName());
			stmt.setString(3, depot.getDepotType());
			stmt.setString(4, depot.getDepotAddress());
			stmt.setString(5, depot.getPrincipal());
			stmt.setInt(6, depot.getDepotLength());
			stmt.setInt(7, depot.getDepotWidth());
			stmt.setInt(8, depot.getDepotHeigth());
			stmt.setInt(9, depot.getDepotVolume());
			count = stmt.executeUpdate();
		} catch (SQLException ex) {
			throw new MedicineRuntimeException(ex.getMessage());
		}finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException ex) {
				}
			}
		}
		
		return count;
	}
	
	public int updateDepot(Depot depot){
		int count = 0;
		PreparedStatement stmt = null;
		String sql = "update tbl_depot set "
				+ "depot_name=?,depot_type=?,principal=?,depot_length=?,depot_width=?,depot_heigth=?,depot_volume=? "
				+ "where depot_code=?";
		try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setString(1, depot.getDepotName());
			stmt.setString(2, depot.getDepotType());
			stmt.setString(3, depot.getPrincipal());
			stmt.setInt(4, depot.getDepotLength());
			stmt.setInt(5, depot.getDepotWidth());
			stmt.setInt(6, depot.getDepotHeigth());
			stmt.setInt(7, depot.getDepotVolume());
			stmt.setInt(8, depot.getDepotCode());
			count = stmt.executeUpdate();
		} catch (SQLException ex) {
			throw new MedicineRuntimeException(ex.getMessage());
		}finally {
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
