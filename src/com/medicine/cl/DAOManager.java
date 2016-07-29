package com.medicine.cl;

import java.sql.SQLException;

import com.medicine.dao.DBHelper;
import com.medicine.util.MedicineRuntimeException;

public abstract class DAOManager {
	protected void beginTransaction(){
		try {
			DBHelper.getConn().setAutoCommit(false);
		} catch (SQLException e) {
			throw new MedicineRuntimeException(e);
		}
	}
	
	protected void commitTransaction(){
		try{
			DBHelper.getConn().commit();
		}catch(SQLException e){
			throw new MedicineRuntimeException(e);
		}
	}
	
	protected void rollbackTransaction(){
		try{
			DBHelper.getConn().rollback();
		}catch(SQLException e){
			throw new MedicineRuntimeException(e);
		}
	}
}
