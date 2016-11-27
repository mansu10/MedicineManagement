package com.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.medicine.entities.CarStatus;
import com.medicine.util.MedicineRuntimeException;

public class CarStatusDAO {

	private Connection getConn() throws SQLException{
		return DBHelper.getConn();
	}
	
	public List<CarStatus> findCarStatusByCode(String carCode) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select id,car_code,car_status,start_time,mission_code,operator,instruction"
				+ " from tbl_car_status "
				+ "where car_code = ? "
				+ "order by start_time desc";
		List<CarStatus> list = new ArrayList<CarStatus>();
		CarStatus carStatus = null;
		try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setString(1, carCode);
			rs = stmt.executeQuery();
			while (rs.next()) {
				carStatus = new CarStatus();
				carStatus.setId(rs.getInt("id"));
				carStatus.setCarCode(rs.getString("car_code"));
				carStatus.setCarStatus(rs.getString("car_status"));
				carStatus.setStartTime(rs.getTimestamp("start_time"));
				carStatus.setMissionCode(rs.getString("mission_code"));
				carStatus.setOperator(rs.getString("operator"));
				carStatus.setInstruction(rs.getString("instruction"));
				list.add(carStatus);
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
		return list;
	}
	public List<CarStatus> findCarStatusByStatusAndCode(String status,String carCode) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select id,car_code,car_status,start_time,mission_code,operator,instruction"
				+ " from tbl_car_status "
				+ "where car_code = ? and car_status =? "
				+ "order by start_time desc";
		List<CarStatus> list = new ArrayList<CarStatus>();
		CarStatus carStatus = null;
		try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setString(1, carCode);
			stmt.setString(2, status);
			rs = stmt.executeQuery();
			while (rs.next()) {
				carStatus = new CarStatus();
				carStatus.setId(rs.getInt("id"));
				carStatus.setCarCode(rs.getString("car_code"));
				carStatus.setCarStatus(rs.getString("car_status"));
				carStatus.setStartTime(rs.getTimestamp("start_time"));
				carStatus.setMissionCode(rs.getString("mission_code"));
				carStatus.setOperator(rs.getString("operator"));
				carStatus.setInstruction(rs.getString("instruction"));
				list.add(carStatus);
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
		return list;
	}
	
	public CarStatus findCarStatusByCode2(String carCode) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select id,car_code,car_status,start_time,mission_code,operator,instruction"
				+ " from tbl_car_status "
				+ "where car_code = ? "
				+ "order by start_time desc "
				+ "limit 1";
		CarStatus carStatus = new CarStatus();
		try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setString(1, carCode);
			rs = stmt.executeQuery();
			while (rs.next()) {
				carStatus.setId(rs.getInt("id"));
				carStatus.setCarCode(rs.getString("car_code"));
				carStatus.setCarStatus(rs.getString("car_status"));
				carStatus.setStartTime(rs.getTimestamp("start_time"));
				carStatus.setMissionCode(rs.getString("mission_code"));
				carStatus.setOperator(rs.getString("operator"));
				carStatus.setInstruction(rs.getString("instruction"));
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
		return carStatus;
	}
	
	public CarStatus findCarStatusByStatus(String status, String carCode, String missionCode){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select id,car_code,car_status,start_time,mission_code,operator,instruction"
				+ " from tbl_car_status "
				+ "where car_status = ? and car_code = ? and mission_code = ? "
				+ "order by start_time desc ";
		CarStatus carStatus =  new CarStatus();
		try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setString(1, status);
			stmt.setString(2, carCode);
			stmt.setString(3, missionCode);
			rs = stmt.executeQuery();
			while (rs.next()) {
				carStatus.setId(rs.getInt("id"));
				carStatus.setCarCode(rs.getString("car_code"));
				carStatus.setCarStatus(rs.getString("car_status"));
				carStatus.setStartTime(rs.getTimestamp("start_time"));
				carStatus.setMissionCode(rs.getString("mission_code"));
				carStatus.setOperator(rs.getString("operator"));
				carStatus.setInstruction(rs.getString("instruction"));
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
		return carStatus;
	}
	
	public int updateCarStatusByCode(String carStatus, String carCode) {
		int count = 0;
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		String sql = "update tbl_car_status set car_status=?,start_time=? where car_code = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, carStatus);
			stmt.setObject(2, new Date());
			stmt.setString(3, carCode);
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
	public int updateInstructionByCode(String instruction, String carCode) {
		int count = 0;
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		String sql = "update tbl_car_status set instruction=? where car_code = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, instruction);
			stmt.setString(2, carCode);
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
	public int updateOperatorByCode(String operator, String stowageCode) {
		int count = 0;
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		String sql = "update tbl_car_status set operator=? where car_code = (select car_code from tbl_stowage where stowage_code = ?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, operator);
			stmt.setString(2, stowageCode);
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
