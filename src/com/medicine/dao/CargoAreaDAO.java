package com.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.medicine.entities.CargoArea;
import com.medicine.util.MedicineRuntimeException;

public class CargoAreaDAO {

	private Connection getConn() throws SQLException{
		return DBHelper.getConn();
	}
	
	public List<CargoArea> queryAllCargoAreasByDepotCode(int depotCode){
		List<CargoArea> areas = new ArrayList<CargoArea>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select c.id,c.cargo_area_code,sum(s.shelf_layer) sum,count(s.shelf_code) count,c.depot_code,"
				+ "c.cargo_area_name,c.cargo_area_desc "
				+ "from tbl_shelf s,tbl_cargo_area c "
				+ "where s.cargo_area_code=c.cargo_area_code "
				+ "group by c.cargo_area_code "
				+ "having depot_code = ?";
		try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setInt(1, depotCode);
			rs = stmt.executeQuery();
			while(rs.next()){
				CargoArea area = new CargoArea();
				area.setId(rs.getInt("id"));
				area.setDepotCode(rs.getInt("depot_code"));
				area.setCargoAreaCode(rs.getString("cargo_area_code"));
				area.setCargoAreaName(rs.getString("cargo_area_name"));
				area.setCargoAreaDesc(rs.getString("cargo_area_desc"));
				area.setShelfCount(rs.getInt("count"));
				area.setLocatorCount(rs.getInt("sum"));
				areas.add(area);
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
		return areas;
	}
	
	public int addCargoArea(CargoArea cargoArea){
		int count = 0;
		PreparedStatement stmt = null;
		String sql = "insert into tbl_cargo_area (depot_code,cargo_area_code,cargo_area_name,cargo_area_desc) "
				+ "values (?,?,?,?)";
		try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setInt(1, cargoArea.getDepotCode());
			stmt.setString(2, cargoArea.getCargoAreaCode());
			stmt.setString(3, cargoArea.getCargoAreaName());
			stmt.setString(4, cargoArea.getCargoAreaDesc());
			stmt.executeUpdate();
			count ++;
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
	
	public List<String> queryCargoAreaCodesByDepotCode(int depotCode){
		List<String> codes = new ArrayList<String>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select cargo_area_code "
				+ "from tbl_cargo_area "
				+ "where depot_code = ?";
		try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setInt(1, depotCode);
			rs = stmt.executeQuery();
			while(rs.next()){
				codes.add(rs.getString("cargo_area_code"));
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
		return codes;
	}
	
	public int updateCargoArea(CargoArea cargoArea){
		int count = 0;
		PreparedStatement stmt = null;
		String sql = "update tbl_cargo_area set cargo_area_name=? "
				+ "where depot_code=? and cargo_area_code=?";
		try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setString(1, cargoArea.getCargoAreaName());
			stmt.setInt(2, cargoArea.getDepotCode());
			stmt.setString(3, cargoArea.getCargoAreaCode());
			stmt.executeUpdate();
			count ++;
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
	public CargoArea queryCargoAreaByDepotCodeAndCargoAreaCode(int depotCode, String cargoAreaCode){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		CargoArea cargoArea = null;
		String sql = "select cargo_area_code,depot_code,cargo_area_name,cargo_area_desc "
				+ "from tbl_cargo_area "
				+ "where depot_code = ? and cargo_area_code = ? ";
		try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setInt(1, depotCode);
			stmt.setString(2, cargoAreaCode);
			rs = stmt.executeQuery();
			while(rs.next()){
				cargoArea = new CargoArea();
				cargoArea.setDepotCode(rs.getInt("depot_code"));
				cargoArea.setCargoAreaCode(rs.getString("cargo_area_code"));
				cargoArea.setCargoAreaName(rs.getString("cargo_area_name"));
				cargoArea.setCargoAreaDesc(rs.getString("cargo_area_desc"));
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
		return cargoArea;
	}
}
