package com.medicine.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.medicine.dao.DBHelper;
import com.medicine.entities.Stock;

public class StockDao {

	static DBHelper db = null; 
	static ResultSet ret = null;
	List<Stock> stocks  = null;
	Stock stock = null;
	String sql = null;
	
	public List<Stock> findAll(){
		sql = "select id,product_code,stock_number,in_stock_time,out_stock_time,batch_number,storage_location,storage_code from tbl_stock";
		db = new DBHelper(sql);
		stocks = new ArrayList<Stock>();
		try {  
            ret = db.pst.executeQuery(); 
            while (ret.next()) {  
            	stock= new Stock();
            	stock.setId(ret.getInt("id"));
            	stock.setProductCode(ret.getInt("product_code"));
            	stock.setStockNumber(ret.getInt("stock_number"));
            	stock.setInStockTime(ret.getTimestamp("in_stock_time"));
            	stock.setOutStockTime(ret.getDate("out_stock_time"));
            	stock.setBanchNumber(ret.getInt("batch_number"));
            	stock.setStorageLocation(ret.getString("storage_location"));
            	stock.setStorageCode(ret.getString("storage_code"));
                stocks.add(stock);
            }
            ret.close();  
            db.close(); 
        } catch (SQLException e) {  
            e.printStackTrace();  
        } 
		return stocks;
	}
}
