package com.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.medicine.entities.Car;
import com.medicine.util.MedicineRuntimeException;

public class CarDAO {

	public List<Car> findAll() {
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Car> cars = new ArrayList<Car>();

		String sql = "select id,car_code,car_name,car_unit,car_driver,max_weight,car_volume from tbl_car";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Car car = new Car();
				car.setId(rs.getInt("id"));
				car.setCarCode(rs.getString("car_code"));
				car.setCarName(rs.getString("car_name"));
				car.setCarUnit(rs.getString("car_unit"));
				car.setCarDriver(rs.getString("car_driver"));
				car.setCarVolume(rs.getInt("car_volume"));
				car.setMaxWeight(rs.getInt("max_weight"));
				cars.add(car);
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
		return cars;
	}
}
