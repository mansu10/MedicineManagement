package com.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.medicine.entities.SupplyAgencyJob;
import com.medicine.util.MedicineRuntimeException;

public class SupplyAgencyJobDAO {

	private Connection getConn() throws SQLException{
		return DBHelper.getConn();
	}
	
	public int updateSupplyAgencyJob(SupplyAgencyJob supplyAgencyJob) {
		int count = 0;
		PreparedStatement stmt = null;
		String sql = "iupdate tbl_supply_agency_job set role_code=?,role_name=?,role_group=? "
				+ "where id = ?";
		try {
			stmt = this.getConn().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, supplyAgencyJob.getRoleCode());
			stmt.setString(2, supplyAgencyJob.getRoleName());
			stmt.setString(3, supplyAgencyJob.getRoleGroup());
			stmt.setInt(4, supplyAgencyJob.getId());
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
	
	public int addSupplyAgencyJob(SupplyAgencyJob supplyAgencyJob) {
		int count = 0;
		PreparedStatement stmt = null;
		String sql = "insert into tbl_supply_agency_job (supply_agency_code,role_code,role_name,role_group) "
				+ "values (?,?,?,?)";
		try {
			stmt = this.getConn().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, supplyAgencyJob.getSupplyAgencyCode());
			stmt.setInt(2, supplyAgencyJob.getRoleCode());
			stmt.setString(3, supplyAgencyJob.getRoleName());
			stmt.setString(4, supplyAgencyJob.getRoleGroup());
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
	
	public List<SupplyAgencyJob> findAllSupplyAgencyJobs(String supplyAgencyCode) {
		List<SupplyAgencyJob> supplyAgencyJobs = new ArrayList<SupplyAgencyJob>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "select id,supply_agency_code,role_code,role_name,role_group "
				+ "from tbl_supply_agency_job "
				+ "where supply_agency_code=? ";
		try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setString(1, supplyAgencyCode);
			rs = stmt.executeQuery();
			while (rs.next()) {
				SupplyAgencyJob supplyAgencyJob = new SupplyAgencyJob();
				supplyAgencyJob.setId(rs.getInt("id"));
				supplyAgencyJob.setSupplyAgencyCode(rs.getString("supply_agency_code"));
				supplyAgencyJob.setRoleCode(rs.getInt("role_code"));
				supplyAgencyJob.setRoleName(rs.getString("role_name"));
				supplyAgencyJob.setRoleGroup(rs.getString("role_group"));
				supplyAgencyJobs.add(supplyAgencyJob);
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
		return supplyAgencyJobs;
	}
	
	public int deleteSupplyAgencyJob(int id) {
		int count = 0;
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		String sql = "delete from tbl_supply_agency_job where id = ?";
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
	
	public int deleteSupplyAgencyJobBySupplyAgencyId(int supplyAgencyId) {
		int count = 0;
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		String sql = "delete from tbl_supply_agency_job where supply_agency_code = (select supply_agency_code from tbl_supply_agency where id = ?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, supplyAgencyId);
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
}
