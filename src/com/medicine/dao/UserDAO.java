package com.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.medicine.entities.User;
import com.medicine.util.MedicineRuntimeException;

public class UserDAO {

	/**
	 * 查找所有用户
	 * 
	 * @author: Tdw
	 * @time: 2016年7月17日下午10:03:31
	 */
	public List<User> findAll() {
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "select user_id,user_code,user_name,user_password,role_id from tbl_user";
		List<User> result = new ArrayList<User>();
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("user_id"));
				user.setCode(rs.getString("user_code"));
				user.setName(rs.getString("user_name"));
				user.setPassword(rs.getString("user_password"));
				user.setRoleId(rs.getInt("role_id"));
				result.add(user);
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
		return result;
	}

	/**
	 * 根据userCode查找用户
	 * 
	 * @author: Tdw
	 * @time: 2016年7月17日下午10:03:48
	 */
	public User findUserByCode(String userCode) {
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User user = null;
		String sql = "select user_id,user_code,user_name,user_password,role_id "
				+ "from tbl_user where user_code = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userCode);
			rs = stmt.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("user_id"));
				user.setCode(rs.getString("user_code"));
				user.setName(rs.getString("user_name"));
				user.setPassword(rs.getString("user_password"));
				user.setRoleId(rs.getInt("role_id"));
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
		return user;
	}

	public int deleteUserById(long id) {
		int count = 0;
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		
		String sql = "delete from tbl_user where user_id = ?";
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

}
