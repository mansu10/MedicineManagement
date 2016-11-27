package com.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.medicine.entities.User;
import com.medicine.util.MedicineRuntimeException;

public class UserDAO {
	
	private Connection getConn() throws SQLException{
		return DBHelper.getConn();
	}

	/**
	 * 查找所有用户
	 * 
	 * @author: Tdw
	 * @param className 
	 * @param userTypeStr 
	 * @param userName 
	 * @param userCode 
	 * @time: 2016年7月17日下午10:03:31
	 */
	public List<User> findAll(String userCode, String userName, String userTypeStr, String className) {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "select id,user_code,user_name,user_password,user_type,student_id,class_name,start_time,end_time from tbl_user "
				+ "where 1=1 ";
		if (userCode != null && !"".equals(userCode)) {
			sql += "and user_code = ? ";
		}
		if (userName != null && !"".equals(userName)) {
			sql += "and user_name = ? ";
		}
		if (className != null && !"".equals(className)) {
			sql += "and class_name = ? ";
		}
		if (userTypeStr != null && !"".equals(userTypeStr)) {
			sql += "and user_type = ?";
		}
		List<User> result = new ArrayList<User>();
		try {
			stmt = this.getConn().prepareStatement(sql);
			int length = 1;
			if (userCode != null && !"".equals(userCode)) {
				stmt.setString(length, userCode);
				length++;
			}
			if (userName != null && !"".equals(userName)) {
				stmt.setString(length, userName);
				length++;
			}
			if (className != null && !"".equals(className)) {
				stmt.setString(length, className);
				length++;
			}
			if (userTypeStr != null && !"".equals(userTypeStr)) {
				stmt.setInt(length, Integer.parseInt(userTypeStr));
				length++;
			}
			rs = stmt.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUserCode(rs.getString("user_code"));
				user.setUserName(rs.getString("user_name"));
				user.setUserPassword(rs.getString("user_password"));
				user.setUserType(rs.getInt("user_type"));
				user.setStudentId(rs.getString("student_id"));
				user.setClassName(rs.getString("class_name"));
				user.setStartTime(rs.getTimestamp("start_time"));
				user.setEndTime(rs.getTimestamp("end_time"));
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
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User user = null;
		String sql = "select id,user_code,user_name,user_password,user_type "
				+ "from tbl_user where user_code = ?";
		try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setString(1, userCode);
			rs = stmt.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUserCode(rs.getString("user_code"));
				user.setUserName(rs.getString("user_name"));
				user.setUserPassword(rs.getString("user_password"));
				user.setUserType(rs.getInt("user_type"));
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
		
		String sql = "delete from tbl_user where id = ?";
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

	 public int addUser(User user) {
		 int count = 0;
		 PreparedStatement stmt = null;
		 String sql = "insert into tbl_user (user_code,user_name,user_password,user_type,student_id,class_name,start_time,end_time) "
		 		+ "values (?,?,?,?,?,?,?,?)";
		 try {
			stmt = this.getConn().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, user.getUserCode());
			stmt.setString(2, user.getUserName());
			stmt.setString(3, user.getUserPassword());
			stmt.setInt(4, user.getUserType());
			stmt.setString(5, user.getStudentId());
			stmt.setString(6, user.getClassName());
			stmt.setObject(7, new Timestamp(user.getStartTime().getTime()));
			stmt.setObject(8, new Timestamp(user.getEndTime().getTime()));
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
	 
	 public int resetPassword(int id) {
		 int count = 0;
		 PreparedStatement stmt = null;
		 String sql = "update tbl_user set user_password = '123456' "
		 		+ "where id = ?";
		 try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setInt(1, id);
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
	 
	 public int updateUser(User user) {
		 int count = 0;
		 PreparedStatement stmt = null;
		 String sql = "update tbl_user set user_password=? "
		 		+ "where id = ?";
		 try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setString(1, user.getUserPassword());
			stmt.setInt(2, user.getId());
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
}
