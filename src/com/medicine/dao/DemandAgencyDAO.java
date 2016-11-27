package com.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.medicine.entities.DemandAgency;
import com.medicine.util.MedicineRuntimeException;

public class DemandAgencyDAO {

	private Connection getConn() throws SQLException{
		return DBHelper.getConn();
	}
	
	public List<DemandAgency> findAllDemandAgencys(String demandAgencyName, String demandAgencyType) {
		List<DemandAgency> demandAgencies = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "select id,demand_agency_code,demand_agency_name,demand_agency_type,demand_agency_level,demand_agency_address1,"
				+ "demand_agency_address2,demand_agency_coordinate,demand_agency_number,demand_agency_protect,contacts,tel "
				+ "from tbl_demand_agency "
				+ "where 1=1 ";
		if (demandAgencyName != null && !"".equals(demandAgencyName)) {
			sql += " and demand_agency_name = ?";
		}
		if (demandAgencyType != null && !"".equals(demandAgencyType)) {
			sql += " and demand_agency_type = ?";
		}
		try {
			stmt = this.getConn().prepareStatement(sql);
			int length = 1;
			if (demandAgencyName != null && !"".equals(demandAgencyName)) {
				stmt.setString(length, demandAgencyName);
				length++;
			}
			if (demandAgencyType != null && !"".equals(demandAgencyType)) {
				stmt.setString(length, demandAgencyType);
				length++;
			}
			rs = stmt.executeQuery();
			while (rs.next()) {
				DemandAgency demandAgency = new DemandAgency();
				demandAgency.setId(rs.getInt("id"));
				demandAgency.setDemandAgencyCode(rs.getString("demand_agency_code"));
				demandAgency.setDemandAgencyName(rs.getString("demand_agency_name"));
				demandAgency.setDemandAgencyType(rs.getString("demand_agency_type"));
				demandAgency.setDemandAgencyAddress1(rs.getString("demand_agency_address1"));
				demandAgency.setDemandAgencyNumber(rs.getInt("demand_agency_number"));
				demandAgency.setDemandAgencyAddress2(rs.getString("demand_agency_address2"));
				demandAgency.setDemandAgencyProtect(rs.getString("demand_agency_protect"));
				demandAgency.setDemandAgencyLevel(rs.getString("demand_agency_level"));
				demandAgency.setDemandAgencyCoordinate(rs.getString("demand_agency_coordinate"));
				demandAgency.setContacts(rs.getString("contacts"));
				demandAgency.setTel(rs.getString("tel"));
				demandAgencies.add(demandAgency);
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
		return demandAgencies;
	}
	
	public int addDemandAgency(DemandAgency demandAgency) {
		int count = 0;
		PreparedStatement stmt = null;
		String sql = "insert into tbl_demand_agency (demand_agency_code,demand_agency_name,demand_agency_type,demand_agency_level,demand_agency_address1,"
				+ "demand_agency_address2,demand_agency_coordinate,demand_agency_number,demand_agency_protect,contacts,tel) "
				+ "values (?,?,?,?,?,?,?,?,?,?,?)";
		try {
			stmt = this.getConn().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, demandAgency.getDemandAgencyCode());
			stmt.setString(2, demandAgency.getDemandAgencyName());
			stmt.setString(3, demandAgency.getDemandAgencyType());
			stmt.setString(4, demandAgency.getDemandAgencyLevel());
			stmt.setString(5, demandAgency.getDemandAgencyAddress1());
			stmt.setString(6, demandAgency.getDemandAgencyAddress2());
			stmt.setString(7, demandAgency.getDemandAgencyCoordinate());
			stmt.setInt(8, demandAgency.getDemandAgencyNumber());
			stmt.setString(9, demandAgency.getDemandAgencyProtect());
			stmt.setString(10, demandAgency.getContacts());
			stmt.setString(11, demandAgency.getTel());
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
	
	public int deleteDemandAgency(int id) {
		int count = 0;
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		
		String sql = "delete from tbl_demand_agency where id = ?";
		try {
			stmt = conn.prepareStatement(sql);
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
	
	public int updateDemandAgency(DemandAgency demandAgency) {
		int count = 0;
		 PreparedStatement stmt = null;
		 String sql = "update tbl_demand_agency set demand_agency_code=?,demand_agency_name=?,demand_agency_type=?,demand_agency_level=?,"
		 		+ "demand_agency_address1=?,demand_agency_address2=?,demand_agency_coordinate=?,demand_agency_number=?,demand_agency_protect=?,"
		 		+ "contacts=?,tel =? "
		 		+ "where id = ?";
		 try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setString(1, demandAgency.getDemandAgencyCode());
			stmt.setString(2, demandAgency.getDemandAgencyName());
			stmt.setString(3, demandAgency.getDemandAgencyType());
			stmt.setString(4, demandAgency.getDemandAgencyLevel());
			stmt.setString(5, demandAgency.getDemandAgencyAddress1());
			stmt.setString(6, demandAgency.getDemandAgencyAddress2());
			stmt.setString(7, demandAgency.getDemandAgencyCoordinate());
			stmt.setInt(8, demandAgency.getDemandAgencyNumber());
			stmt.setString(9, demandAgency.getDemandAgencyProtect());
			stmt.setString(10, demandAgency.getContacts());
			stmt.setString(11, demandAgency.getTel());
			stmt.setInt(12, demandAgency.getId());
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
	
	public DemandAgency findDemandAgencyByCode(String demandAgencyCode){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DemandAgency demandAgency = new DemandAgency();
		String sql = "select id,demand_agency_code,demand_agency_name,demand_agency_type,demand_agency_level,demand_agency_address1,"
				+ "demand_agency_address2,demand_agency_coordinate,demand_agency_number,demand_agency_protect,contacts,tel,province,city,district,zip_code "
				+ "from tbl_demand_agency "
				+ "where demand_agency_code = ?";
		
		try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setString(1, demandAgencyCode);
			rs = stmt.executeQuery();
			if (rs.next()) {
				demandAgency.setId(rs.getInt("id"));
				demandAgency.setDemandAgencyCode(rs.getString("demand_agency_code"));
				demandAgency.setDemandAgencyName(rs.getString("demand_agency_name"));
				demandAgency.setDemandAgencyType(rs.getString("demand_agency_type"));
				demandAgency.setDemandAgencyAddress1(rs.getString("demand_agency_address1"));
				demandAgency.setDemandAgencyNumber(rs.getInt("demand_agency_number"));
				demandAgency.setDemandAgencyAddress2(rs.getString("demand_agency_address2"));
				demandAgency.setDemandAgencyProtect(rs.getString("demand_agency_protect"));
				demandAgency.setDemandAgencyLevel(rs.getString("demand_agency_level"));
				demandAgency.setDemandAgencyCoordinate(rs.getString("demand_agency_coordinate"));
				demandAgency.setContacts(rs.getString("contacts"));
				demandAgency.setTel(rs.getString("tel"));
				demandAgency.setProvince(rs.getString("province"));
				demandAgency.setCity(rs.getString("city"));
				demandAgency.setDistrict(rs.getString("district"));
				demandAgency.setZipCode(rs.getInt("zip_code"));
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
		return demandAgency;
	}
	
	public List<String> findAllDemandAgencyCodes() {
		List<String> demandAgencyCodes = new ArrayList<String>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select demand_agency_code from tbl_demand_agency ";
		
		try {
			stmt = this.getConn().prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				demandAgencyCodes.add(rs.getString("demand_agency_code"));
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
		return demandAgencyCodes;
	}
}
