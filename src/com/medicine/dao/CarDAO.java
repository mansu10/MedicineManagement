package com.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.medicine.entities.Car;
import com.medicine.util.MedicineRuntimeException;
import com.mysql.jdbc.Statement;

public class CarDAO {

	public List<Car> findAllCarsByNumberOrCode(String numberOrCode, String agencyCode) {
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Car> cars = new ArrayList<Car>();

		String sql = "select id,car_code,car_name,car_unit,car_driver,max_weight,car_volume,car_number,car_type,"
				+ "car_length,car_width,car_heigth,memo,agency_code,factory,recharge_mileage,max_speed,average_speed "
				+ "from tbl_car "
				+ "where agency_code = ?";
		
		String a  = " and car_number = ? or car_code = ?";
		try {
			if (StringUtils.isEmpty(numberOrCode)) {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, agencyCode);
			}
			else {
				stmt = conn.prepareStatement(sql+a);
				stmt.setString(1, agencyCode);
				stmt.setString(2, numberOrCode);
				stmt.setString(3, numberOrCode);
			}
			rs = stmt.executeQuery();
			while (rs.next()) {
				Car car = new Car();
				car.setId(rs.getInt("id"));
				car.setCarCode(rs.getString("car_code"));
				car.setCarName(rs.getString("car_name"));
				car.setCarUnit(rs.getString("car_unit"));
				car.setCarDriver(rs.getString("car_driver"));
				car.setCarVolume(rs.getDouble("car_volume"));
				car.setMaxWeight(rs.getDouble("max_weight"));
				car.setCarNumber(rs.getString("car_number"));
				car.setCarType(rs.getString("car_type"));
				car.setCarLength(rs.getInt("car_length"));
				car.setCarWidth(rs.getInt("car_width"));
				car.setCarHeigth(rs.getInt("car_heigth"));
				car.setMemo(rs.getString("memo"));
				car.setAgencyCode(rs.getString("agency_code"));
				car.setFactory(rs.getString("factory"));
				car.setMaxSpeed(rs.getString("max_speed"));
				car.setAverageSpeed(rs.getString("average_speed"));
				car.setRechargeMileage(rs.getString("recharge_mileage"));
				cars.add(car);
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
		return cars;
	}

	public int addCar(Car car){
		int count = 0;
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "insert into tbl_car (car_code,car_name,car_unit,car_driver,max_weight,car_volume,car_number,"
				+ "car_type,car_length,car_width,car_heigth,memo,agency_code,factory,recharge_mileage,max_speed,average_speed) "
				+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, car.getCarCode());
			stmt.setString(2, car.getCarName());
			stmt.setString(3, car.getCarUnit());
			stmt.setString(4, car.getCarDriver());
			stmt.setDouble(5, car.getMaxWeight());
			stmt.setDouble(6, car.getCarVolume());
			stmt.setString(7, car.getCarNumber());
			stmt.setString(8, car.getCarType());
			stmt.setInt(9, car.getCarLength());
			stmt.setInt(10, car.getCarWidth());
			stmt.setInt(11, car.getCarHeigth());
			stmt.setString(12, car.getMemo());
			stmt.setString(13, car.getAgencyCode());
			stmt.setString(14, car.getFactory());
			stmt.setString(15, car.getRechargeMileage());
			stmt.setString(16, car.getMaxSpeed());
			stmt.setString(17, car.getAverageSpeed());
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
					rs .close();
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
	
	public int deleteCarById(int id){
		int count = 0;
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;

		String sql = "delete from tbl_car where id = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			count = stmt.executeUpdate();
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
	
	public int updateCar(Car car){
		int count = 0;
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;

		String sql = "update tbl_car "
				+ "set car_code = ?,car_name=?,car_unit=?,car_driver=?,max_weight=?,car_volume=?,"
				+ "car_number=?,car_type=?,memo=?,car_length=?,car_width=?,car_heigth=?,agency_code=?,factory=?,recharge_mileage=?,max_speed=?,average_speed=? "
				+ "where id = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, car.getCarCode());
			stmt.setString(2, car.getCarName());
			stmt.setString(3, car.getCarUnit());
			stmt.setString(4, car.getCarDriver());
			stmt.setDouble(5, car.getMaxWeight());
			stmt.setDouble(6, car.getCarVolume());
			stmt.setString(7, car.getCarNumber());
			stmt.setString(8, car.getCarType());
			stmt.setString(9, car.getMemo());
			stmt.setInt(10, car.getCarLength());
			stmt.setInt(11, car.getCarWidth());
			stmt.setInt(12, car.getCarHeigth());
			stmt.setString(13, car.getAgencyCode());
			stmt.setString(14, car.getFactory());
			stmt.setString(15, car.getRechargeMileage());
			stmt.setString(16, car.getMaxSpeed());
			stmt.setString(17, car.getAverageSpeed());
			stmt.setInt(18, car.getId());
			count = stmt.executeUpdate();
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
	
	public int addOrdersToCar(String carOrders, int carId){
		int count = 0;
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;

		String sql = "update tbl_car "
				+ "set car_order =  ?"
				+ "where id = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, carOrders);
			stmt.setInt(2, carId);
			count = stmt.executeUpdate();
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
	
	public List<Car> findAllCars() {
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Car> cars = new ArrayList<Car>();
		String sql = "select id,car_code,max_weight,car_volume,car_number,car_type"
				+ " from tbl_car ";
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Car car = new Car();
				car.setId(rs.getInt("id"));
				car.setCarCode(rs.getString("car_code"));
				car.setCarVolume(rs.getDouble("car_volume"));
				car.setMaxWeight(rs.getDouble("max_weight"));
				car.setCarNumber(rs.getString("car_number"));
				car.setCarType(rs.getString("car_type"));
				cars.add(car);
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
		return cars;
	}
	
	public Car findCarByCode(String carCode) {
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Car car = null;
		String sql = "select id,car_code,car_name,car_unit,car_driver,max_weight,car_volume,car_number,car_type,"
				+ "car_length,car_width,car_heigth,memo from tbl_car "
				+ "where car_code = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, carCode);
			rs = stmt.executeQuery();
			while (rs.next()) {
				car = new Car();
				car.setId(rs.getInt("id"));
				car.setCarCode(rs.getString("car_code"));
				car.setCarName(rs.getString("car_name"));
				car.setCarUnit(rs.getString("car_unit"));
				car.setCarDriver(rs.getString("car_driver"));
				car.setCarVolume(rs.getDouble("car_volume"));
				car.setMaxWeight(rs.getDouble("max_weight"));
				car.setCarNumber(rs.getString("car_number"));
				car.setCarType(rs.getString("car_type"));
				car.setCarLength(rs.getInt("car_length"));
				car.setCarWidth(rs.getInt("car_width"));
				car.setCarHeigth(rs.getInt("car_heigth"));
				car.setMemo(rs.getString("memo"));
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
		return car;
	}
	
}
