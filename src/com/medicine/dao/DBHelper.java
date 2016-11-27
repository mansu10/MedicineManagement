package com.medicine.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.medicine.util.MedicineRuntimeException;

public class DBHelper {

//	public static final String url = "jdbc:mysql://127.0.0.1/school?characterEncoding=utf-8";
//	public static final String name = "com.mysql.jdbc.Driver";  
//    public static final String user = "root";  
//    public static final String password = "123"; 
	public static final String url = "jdbc:mysql://121.42.182.179:3306/med_db?characterEncoding=utf-8&autoReconnect=true&amp;autoReconnectForPools=true";
	public static final String name = "com.mysql.jdbc.Driver";  
    public static final String user = "db_med";  
    public static final String password = "123456";
    
//    public Connection conn = null;  
//    public PreparedStatement pst = null;  
//  
//    public DBHelper(String sql) {  
//        try {  
//            Class.forName(name);//指定连接类型  
//            conn = DriverManager.getConnection(url, user, password);//获取连接  
//            pst = conn.prepareStatement(sql);//准备执行语句  
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        }  
//    }  
//  
//    public void close() {  
//        try {  
//            this.conn.close();  
//            this.pst.close();  
//        } catch (SQLException e) {  
//            e.printStackTrace();  
//        }  
//    }  

	private static ThreadLocal<Connection> threadLocal=new ThreadLocal<Connection>();

	public static synchronized Connection getConn() {
		return getMysqlConn();	
	}

	private static Connection getMysqlConn() {
		try {
			Connection conn = threadLocal.get();
			if (conn == null || conn.isClosed()) {
				Class.forName(name);
	            conn = DriverManager.getConnection(url, user, password);
				conn.setAutoCommit(false);
				threadLocal.set(conn);
			}
			return conn;
		} catch (ClassNotFoundException cnfex) {
			throw new MedicineRuntimeException(cnfex);
		} catch (SQLException sqlex) {
			throw new MedicineRuntimeException(sqlex);
		}
	}
}
