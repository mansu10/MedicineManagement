package com.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.medicine.entities.Shelf;
import com.medicine.util.MedicineRuntimeException;

public class ShelfDAO {

	private Connection getConn() throws SQLException{
		return DBHelper.getConn();
	}
	
	public int addShelf(Shelf shelf) {
		int count = 0;
		PreparedStatement stmt = null;
		String sql = "insert into tbl_shelf(shelf_name,shelf_specification,shelf_layer,cargo_area_code,depot_code) "
				+ "values (?,?,?,?,?)";
		try {
			stmt = this.getConn().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, shelf.getShelfName());
			stmt.setInt(2, shelf.getShelfSpecification());
			stmt.setInt(3, shelf.getShelfLayer());
			stmt.setString(4, shelf.getCargoAreaCode());
			stmt.setInt(5, shelf.getDepotCode());
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
	
	public List<Shelf> queryAllShelfs(int depotCode){
		List<Shelf> shelfs = new ArrayList<Shelf>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select shelf_code,shelf_specification,shelf_layer,cargo_area_code,depot_code "
				+ "from tbl_shelf "
				+ "where depot_code = ?";
		try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setInt(1, depotCode);
			rs = stmt.executeQuery();
			while(rs.next()){
				Shelf shelf = new Shelf();
				shelf.setCargoAreaCode(rs.getString("cargo_area_code"));
				shelf.setShelfCode(rs.getInt("shelf_code"));
				shelf.setShelfSpecification(rs.getInt("shelf_specification"));
				shelf.setShelfLayer(rs.getInt("shelf_layer"));
				shelf.setDepotCode(rs.getInt("depot_code"));
				shelfs.add(shelf);
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
		return shelfs;
	}
	
	public int updateShelf(Shelf shelf) {
		int count = 0;
		PreparedStatement stmt = null;
		String sql = "update tbl_shelf set cargo_area_code=? "
				+ "where shelf_code = ?";
		try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setString(1, shelf.getCargoAreaCode());
			stmt.setInt(2, shelf.getShelfCode());
			count=stmt.executeUpdate();
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
	
	public List<Shelf> queryAllShelfsByAreaCode(String cargoAreaCode){
		List<Shelf> shelfs = new ArrayList<Shelf>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select shelf_code,shelf_specification,shelf_layer "
				+ "from tbl_shelf "
				+ "where cargo_area_code =?";
		try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setString(1, cargoAreaCode);
			rs = stmt.executeQuery();
			while(rs.next()){
				Shelf shelf = new Shelf();
				shelf.setShelfCode(rs.getInt("shelf_code"));
				shelf.setShelfSpecification(rs.getInt("shelf_specification"));
				shelf.setShelfLayer(rs.getInt("shelf_layer"));
				shelfs.add(shelf);
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
		return shelfs;
	}
}
