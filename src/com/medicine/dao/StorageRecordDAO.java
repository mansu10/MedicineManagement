package com.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.medicine.entities.StorageRecord;
import com.medicine.util.MedicineRuntimeException;

public class StorageRecordDAO {

	public int addStorageRecord(StorageRecord record){
		int count = 0;
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;

		String sql = "insert into tbl_storage_record (storage_record_code,storage_record_time,depot_code,storager,morning_record_time,morning_temperature,"
				+ "morning_humidity,morning_measure,afternoon_record_time,afternoon_temperature,afternoon_humidity,afternoon_measure,"
				+ "equipment_situation,conserve_reservoir,conserver,conserve_number,conserve_batch,quality_status,conserve_measure,handle_result,memo) "
				+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, record.getStorageRecordCode());
			stmt.setObject(2, new Timestamp(record.getStorageRecordTime().getTime()));
			stmt.setString(3, record.getDepotCode());
			stmt.setString(4, record.getStorager());
			stmt.setObject(5, new Timestamp(record.getMorningRecordTime().getTime()));
			stmt.setInt(6, record.getMorningTemperature());
			stmt.setInt(7, record.getMorningHumidity());
			stmt.setString(8, record.getMorningMeasure());
			stmt.setObject(9, new Timestamp(record.getAfternoonRecordTime().getTime()));
			stmt.setInt(10, record.getAfternoonTemperature());
			stmt.setInt(11, record.getAfternoonHumidity());
			stmt.setString(12, record.getAfternoonMeasure());
			stmt.setString(13, record.getEquipmentSituation());
			stmt.setString(14, record.getConserveReservoir());
			stmt.setString(15, record.getConserver());
			stmt.setInt(16, record.getConserveNumber());
			stmt.setInt(17, record.getConserveBatch());
			stmt.setString(18, record.getQualityStatus());
			stmt.setString(19, record.getConserveMeasure());
			stmt.setString(20, record.getHandleResult());
			stmt.setString(21, record.getMemo());
			
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
	
}
