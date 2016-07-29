package com.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.medicine.dao.DBHelper;
import com.medicine.entities.Stock;
import com.medicine.util.MedicineRuntimeException;

public class StockDao {

	public List<Stock> findAll() {
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Stock> stocks = new ArrayList<Stock>();
		String sql = "select id,product_code,stock_number,in_stock_time,out_stock_time,batch_number,storage_location,storage_code from tbl_stock";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Stock stock = new Stock();
				stock.setId(rs.getInt("id"));
				stock.setProductCode(rs.getInt("product_code"));
				stock.setStockNumber(rs.getInt("stock_number"));
				stock.setInStockTime(rs.getTimestamp("in_stock_time"));
				stock.setOutStockTime(rs.getDate("out_stock_time"));
				stock.setBanchNumber(rs.getInt("batch_number"));
				stock.setStorageLocation(rs.getString("storage_location"));
				stock.setStorageCode(rs.getString("storage_code"));
				stocks.add(stock);
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
		return stocks;
	}
}
