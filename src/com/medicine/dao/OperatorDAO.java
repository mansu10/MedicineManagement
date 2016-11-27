package com.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.medicine.entities.Operator;
import com.medicine.util.MedicineRuntimeException;

public class OperatorDAO {
	private Connection getConn() throws SQLException{
		return DBHelper.getConn();
	}
	
	public List<Operator> findOperatorByPosition(String position) {
		List<Operator> operators = new ArrayList<Operator>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from tbl_operator where 1=1 ";
		if (position != null && !"".equals(position.trim())) {
			sql += "and position = ? ";
		}
		try {
			stmt = this.getConn().prepareStatement(sql);
			int length = 1;
			if (position != null && !"".equals(position.trim())) {
				stmt.setString(length, position);
				length++;
			}
			rs = stmt.executeQuery();
			while(rs.next()) {
				Operator operator = new Operator();
				operator.setId(rs.getInt("id"));
				operator.setOperatorCode(rs.getString("operator_code"));
				operator.setOperatorName(rs.getString("operator_name"));
				operator.setOperatorSex(rs.getString("operator_sex"));
				operator.setPosition(rs.getString("position"));
				operator.setSpecialty(rs.getString("specialty"));
				operator.setAgencyCode(rs.getString("agency_code"));
				operator.setTel(rs.getString("tel"));
				operator.setBirth(rs.getTimestamp("birth"));
				operators.add(operator);
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
		return operators;
	}

}
