package com.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.medicine.entities.Organization;
import com.medicine.util.MedicineRuntimeException;

public class OrganizationDAO {

	public List<Organization> findAll() {
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Organization> organizations = new ArrayList<Organization>();
		String sql = "select id,organization_name,organization_type,organization_people_numble,organization_address,organization_coordinate from tbl_organization";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Organization organization = new Organization();
				organization.setId(rs.getInt("id"));
				organization.setOrganizationName(rs.getString("organization_name"));
				organization.setOrganizationType(rs.getString("organization_type"));
				organization.setOrganizationPeopleNumber(rs.getInt("organization_people_numble"));
				organization.setOrganizationAddress(rs.getString("organization_address"));
				organization.setOrganizationCoordinate(rs.getString("organization_coordinate"));
				organizations.add(organization);
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
		return organizations;
	}
}
