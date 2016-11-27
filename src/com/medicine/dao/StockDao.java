package com.medicine.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.medicine.entities.Stock;
import com.medicine.util.MedicineRuntimeException;

public class StockDao {

	public List<Stock> findAllStocks() {
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Stock> stocks = new ArrayList<Stock>();
		String sql = "select id,product_code,sum(case_quantity) stock_quantity,in_stock_time,out_stock_time,batch_number,locator_name,locator_code,memo,case_number,"
				+ "factory_time,status "
				+ "from tbl_stock "
				+ "group by product_code";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Stock stock = new Stock();
				stock.setId(rs.getInt("id"));
				stock.setProductCode(rs.getInt("product_code"));
				stock.setInStockTime(rs.getTimestamp("in_stock_time"));
				stock.setOutStockTime(rs.getTimestamp("out_stock_time"));
				stock.setBanchNumber(rs.getInt("batch_number"));
				stock.setLocatorName(rs.getString("locator_name"));
				stock.setLocatorCode(rs.getString("locator_code"));
				stock.setMemo(rs.getString("memo"));
				stock.setCaseNumber(rs.getString("case_number"));
				stock.setFactoryTime(rs.getTimestamp("factory_time"));
				stock.setStatus(rs.getInt("status"));
				stock.setStockQuantity(rs.getInt("stock_quantity"));
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
	
	public Stock findStockByProdcutCode(int productCode){
		Stock stock = new Stock();
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select id,product_code,case_quantity,in_stock_time,out_stock_time,batch_number,locator_name,locator_code,memo,case_number "
				+ "from tbl_stock "
				+ "where product_code = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, productCode);
			rs = stmt.executeQuery();
			while (rs.next()) {
				stock.setId(rs.getInt("id"));
				stock.setProductCode(rs.getInt("product_code"));
				stock.setCaseQuantity(rs.getInt("case_quantity"));
				stock.setInStockTime(rs.getTimestamp("in_stock_time"));
				stock.setOutStockTime(rs.getDate("out_stock_time"));
				stock.setBanchNumber(rs.getInt("batch_number"));
				stock.setLocatorName(rs.getString("locator_name"));
				stock.setLocatorCode(rs.getString("locator_code"));
				stock.setMemo(rs.getString("memo"));
				stock.setCaseNumber(rs.getString("case_number"));
			}
		} catch (SQLException ex) {
			throw new MedicineRuntimeException("查询库存失败！ " + ex.getMessage());
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
		return stock;
	}
	
	public int deleteStockById(int id){
		int count = 0;
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;

		String sql = "delete from tbl_stock where id = ?";
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
	
	public int updateStock(Stock stock){
		int count = 0;
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;

		String sql = "update tbl_stock "
				+ "set product_code = ?,case_quantity=?,out_stock_time=?,batch_number=?,locator_name=?,locator_code=?,memo=?"
				+ "where id = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, stock.getProductCode());
			stmt.setInt(2, stock.getCaseQuantity());
			stmt.setDate(3, new Date(stock.getOutStockTime().getTime()));
			stmt.setInt(4, stock.getBanchNumber());
			stmt.setString(5, stock.getLocatorName());
			stmt.setString(6, stock.getLocatorCode());
			stmt.setString(7, stock.getMemo());
			stmt.setInt(8, stock.getId());
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
	
	public int addStock(Stock stock) {
		int count;
		Connection conn = DBHelper.getConn();
		PreparedStatement stmt = null;

		String sql = "insert into tbl_stock (product_code,case_quantity,in_stock_time,batch_number,locator_name,locator_code,memo,"
				+ "case_number,factory_time,status,receipt_code) "
				+ "values (?,?,?,?,?,?,?,?,?,?,?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, stock.getProductCode());
			stmt.setInt(2, stock.getCaseQuantity());
			stmt.setObject(3, new Timestamp(System.currentTimeMillis()));
			stmt.setInt(4, stock.getBanchNumber());
			stmt.setString(5, stock.getLocatorName());
			stmt.setString(6, stock.getLocatorCode());
			stmt.setString(7, stock.getMemo());
			stmt.setString(8, stock.getCaseNumber());
			stmt.setObject(9, new Timestamp(stock.getFactoryTime().getTime()));
			stmt.setInt(10, stock.getStatus());
			stmt.setString(11, stock.getReceiptCode());
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
