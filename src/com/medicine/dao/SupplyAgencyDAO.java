package com.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.medicine.entities.SupplyAgency;
import com.medicine.util.MedicineRuntimeException;

public class SupplyAgencyDAO {

	private Connection getConn() throws SQLException{
		return DBHelper.getConn();
	}
	
	public List<SupplyAgency> findAllSupplyAgencys(String supplyAgencyName, String supplyAgencyType) {
		List<SupplyAgency> supplyAgencies = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "select id,supply_agency_code,supply_agency_name,supply_agency_type,supply_agency_address1,supply_agency_number,supply_agency_address2,"
				+ "supply_agency_coordinate,supply_agency_level "
				+ "from tbl_supply_agency "
				+ "where 1=1 ";
		if (supplyAgencyName != null && !"".equals(supplyAgencyName)) {
			sql += " and supply_agency_name = ?";
		}
		if (supplyAgencyType != null && !"".equals(supplyAgencyType)) {
			sql += " and supply_agency_type = ?";
		}
		try {
			stmt = this.getConn().prepareStatement(sql);
			int length = 1;
			if (supplyAgencyName != null && !"".equals(supplyAgencyName)) {
				stmt.setString(length, supplyAgencyName);
				length++;
			}
			if (supplyAgencyType != null && !"".equals(supplyAgencyType)) {
				stmt.setString(length, supplyAgencyType);
				length++;
			}
			rs = stmt.executeQuery();
			while (rs.next()) {
				SupplyAgency supplyAgency = new SupplyAgency();
				supplyAgency.setId(rs.getInt("id"));
				supplyAgency.setSupplyAgencyCode(rs.getString("supply_agency_code"));
				supplyAgency.setSupplyAgencyName(rs.getString("supply_agency_name"));
				supplyAgency.setSupplyAgencyType(rs.getString("supply_agency_type"));
				supplyAgency.setSupplyAgencyAddress1(rs.getString("supply_agency_address1"));
				supplyAgency.setSupplyAgencyNumber(rs.getInt("supply_agency_number"));
				supplyAgency.setSupplyAgencyLevel(rs.getString("supply_agency_level"));
				supplyAgency.setSupplyAgencyAddress2(rs.getString("supply_agency_address2"));
				supplyAgency.setSupplyAgencyCoordinate(rs.getString("supply_agency_coordinate"));
				supplyAgencies.add(supplyAgency);
			}
		}catch (SQLException ex) {
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
		return supplyAgencies;
	}
	
	public int addSupplyAgency(SupplyAgency supplyAgency) {
		int count = 0;
		PreparedStatement stmt = null;
		String sql = "insert into tbl_supply_agency (supply_agency_code,supply_agency_name,supply_agency_type,supply_agency_level,supply_agency_address1,"
				+ "supply_agency_address2,supply_agency_coordinate,supply_agency_number) "
				+ "values (?,?,?,?,?,?,?,?)";
		try {
			stmt = this.getConn().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, supplyAgency.getSupplyAgencyCode());
			stmt.setString(2, supplyAgency.getSupplyAgencyName());
			stmt.setString(3, supplyAgency.getSupplyAgencyType());
			stmt.setString(4, supplyAgency.getSupplyAgencyLevel());
			stmt.setString(5, supplyAgency.getSupplyAgencyAddress1());
			stmt.setString(6, supplyAgency.getSupplyAgencyAddress2());
			stmt.setString(7, supplyAgency.getSupplyAgencyCoordinate());
			stmt.setInt(8, supplyAgency.getSupplyAgencyNumber());
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
	
	public int deleteSupplyAgency(int id) {
		int count = 0;
		PreparedStatement stmt = null;
		String sql = "delete from tbl_supply_agency where id = ?";
		try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setLong(1, id);
			count = stmt.executeUpdate();
		} catch(SQLException ex){
			throw new MedicineRuntimeException(ex);
		}finally{
			if(stmt!=null){
				try{stmt.close();}catch(SQLException ex){}
			}
		}
		return count;
	}
	
	public int updateSupplyAgency(SupplyAgency SupplyAgency) {
		int count = 0;
		 PreparedStatement stmt = null;
		 String sql = "update tbl_supply_agency set supply_agency_code=?,supply_agency_name=?,supply_agency_type=?,supply_agency_level=?,"
		 		+ "supply_agency_address1=?,supply_agency_address2=?,supply_agency_coordinate=?,supply_agency_number=? "
		 		+ "where id = ?";
		 try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setString(1, SupplyAgency.getSupplyAgencyCode());
			stmt.setString(2, SupplyAgency.getSupplyAgencyName());
			stmt.setString(3, SupplyAgency.getSupplyAgencyType());
			stmt.setString(4, SupplyAgency.getSupplyAgencyLevel());
			stmt.setString(5, SupplyAgency.getSupplyAgencyAddress1());
			stmt.setString(6, SupplyAgency.getSupplyAgencyAddress2());
			stmt.setString(7, SupplyAgency.getSupplyAgencyCoordinate());
			stmt.setInt(8, SupplyAgency.getSupplyAgencyNumber());
			stmt.setInt(9, SupplyAgency.getId());
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
	
	public SupplyAgency findSupplyAgencyByCode(String supplyAgencyCode){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		SupplyAgency supplyAgency = new SupplyAgency();
		String sql = "select id,supply_agency_code,supply_agency_name,supply_agency_type,supply_agency_level,supply_agency_address1,"
				+ "supply_agency_address2,supply_agency_coordinate,supply_agency_number "
				+ "from tbl_supply_agency "
				+ "where supply_agency_code = ?";
		
		try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setString(1, supplyAgencyCode);
			rs = stmt.executeQuery();
			if (rs.next()) {
				supplyAgency.setId(rs.getInt("id"));
				supplyAgency.setSupplyAgencyCode(rs.getString("supply_agency_code"));
				supplyAgency.setSupplyAgencyName(rs.getString("supply_agency_name"));
				supplyAgency.setSupplyAgencyType(rs.getString("supply_agency_type"));
				supplyAgency.setSupplyAgencyAddress1(rs.getString("supply_agency_address1"));
				supplyAgency.setSupplyAgencyNumber(rs.getInt("supply_agency_number"));
				supplyAgency.setSupplyAgencyAddress2(rs.getString("supply_agency_address2"));
				supplyAgency.setSupplyAgencyLevel(rs.getString("supply_agency_level"));
				supplyAgency.setSupplyAgencyCoordinate(rs.getString("supply_agency_coordinate"));
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
		return supplyAgency;
	}
	
	public List<String> findAllSupplyAgencyCodes() {
		List<String> supplyAgencyCodes = new ArrayList<String>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select supply_agency_code from tbl_supply_agency ";
		
		try {
			stmt = this.getConn().prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				supplyAgencyCodes.add(rs.getString("supply_agency_code"));
			}
		} catch (SQLException ex) {
			throw new MedicineRuntimeException(ex.getMessage());
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
			}
			if (stmt !=null) {
				try {
					stmt.close();
				} catch (SQLException ex) {
				}
			}
		}
		return supplyAgencyCodes;
	}
}
