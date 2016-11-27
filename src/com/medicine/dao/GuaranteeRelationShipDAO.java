package com.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.medicine.entities.GuaranteeRelationShip;
import com.medicine.util.MedicineRuntimeException;

public class GuaranteeRelationShipDAO {

	private Connection getConn() throws SQLException{
		return DBHelper.getConn();
	}
	
	public List<GuaranteeRelationShip> findAllGuaranteeRelationShips(String demandAgencyCode, String supplyAgencyCode) {
		List<GuaranteeRelationShip> guaranteeRelationShips = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "select id,supply_agency_code,demand_agency_code,create_time,transport_route "
				+ "from tbl_guarantee_relationship "
				+ "where 1=1 ";
		if (demandAgencyCode != null && !"".equals(demandAgencyCode)) {
			sql += " and demand_agency_code = ?";
		}
		if (supplyAgencyCode != null && !"".equals(supplyAgencyCode)) {
			sql += " and supply_agency_code = ?";
		}
		try {
			stmt = this.getConn().prepareStatement(sql);
			int length = 1;
			if (demandAgencyCode != null && !"".equals(demandAgencyCode)) {
				stmt.setString(length, demandAgencyCode);
				length++;
			}
			if (supplyAgencyCode != null && !"".equals(supplyAgencyCode)) {
				stmt.setString(length, supplyAgencyCode);
				length++;
			}
			rs = stmt.executeQuery();
			while (rs.next()) {
				GuaranteeRelationShip guaranteeRelationShip = new GuaranteeRelationShip();
				guaranteeRelationShip.setId(rs.getInt("id"));
				guaranteeRelationShip.setSupplyAgencyCode(rs.getString("supply_agency_code"));
				guaranteeRelationShip.setDemandAgencyCode(rs.getString("demand_agency_code"));
				guaranteeRelationShip.setCreateTime(rs.getDate("create_time"));
				guaranteeRelationShip.setTransportRoute(rs.getString("transport_route"));
				guaranteeRelationShips.add(guaranteeRelationShip);
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
		return guaranteeRelationShips;
	}
	
	public int addGuaranteeRelationShip(GuaranteeRelationShip guaranteeRelationShip) {
		int count = 0;
		PreparedStatement stmt = null;
		ResultSet  rs = null;
		String sql = "insert into tbl_guarantee_relationship (supply_agency_code,demand_agency_code,create_time) "
				+ "values (?,?,?)";
		try {
			stmt = this.getConn().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, guaranteeRelationShip.getSupplyAgencyCode());
			stmt.setString(2, guaranteeRelationShip.getDemandAgencyCode());
			stmt.setObject(3, new Timestamp(System.currentTimeMillis()));
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				count = rs.getInt(1);
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
		return count;
	}
	
	public int deleteGuaranteeRelationShip(int id) {
		int count = 0;
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		String sql = "delete from tbl_guarantee_relationship where id = ?";
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
	public int updateGuaranteeRelationShip(String transportRoute, String demandAgencyCode, String supplyAgencyCode) {
		int count = 0;
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		String sql = "update tbl_guarantee_relationship set transport_route = ? "
				+ "where demand_agency_code = ? and supply_agency_code = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, transportRoute);
			stmt.setString(2, demandAgencyCode);
			stmt.setString(3, supplyAgencyCode);
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
