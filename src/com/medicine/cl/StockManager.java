package com.medicine.cl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.medicine.dao.ProductDAO;
import com.medicine.dao.StockDao;
import com.medicine.entities.Product;
import com.medicine.entities.Stock;
import com.medicine.util.MedicineRuntimeException;

public class StockManager extends DAOManager {

	private StockDao stockDao = new StockDao();
	private ProductDAO productDAO = new ProductDAO();

	public Stock findStockByProdcutCode(int productCode) {
		Stock stock = null;
		stock = stockDao.findStockByProdcutCode(productCode);
		return stock;
	}

	public List<Stock> findAllStocks(String productCodeOrName) {
		List<Stock> stocks = null;
		stocks = stockDao.findAllStocks();
		for (Stock stock : stocks) {
			Product product = new Product();
			product = productDAO.findProductByCode(stock.getProductCode());
			if (product != null) {
				stock.setProduct(product);
				stock.setStockValue(stock.getStockQuantity() * product.getPrice());
				stock.setStockLevel((float)stock.getStockQuantity() /product.getMaxStock());
			}
		}
		if (StringUtils.isNotEmpty(productCodeOrName)) {
			List<Stock> result = new ArrayList<Stock>();
			for (Stock stock : stocks) {
				if (productCodeOrName.equals(String.valueOf(stock.getProductCode()))
						|| productCodeOrName.equals(stock.getProduct().getOrdinaryName())) {
					result.add(stock);
				}
			}
			return result;
		}
		return stocks;
	}

	public int deleteStockById(int id) {
		int count = 0;
		try {
			this.beginTransaction();
			count = stockDao.deleteStockById(id);
			this.commitTransaction();
		} catch (Throwable t) {
			this.rollbackTransaction();
			throw new MedicineRuntimeException(t.getMessage());
		}
		return count;
	}

	public int updateStock(Stock stock) {
		int count = 0;
		try {
			this.beginTransaction();
			count = stockDao.updateStock(stock);
			this.commitTransaction();
		} catch (Throwable t) {
			this.rollbackTransaction();
			throw new MedicineRuntimeException(t.getMessage());
		}
		return count;
	}
	
	public int addStock(List<Stock> stocks){
		int count = 0;
		try {
			this.beginTransaction();
			for (Stock stock : stocks) {
				stockDao.addStock(stock);
				count++;
			}
			this.commitTransaction();
		} catch (Throwable t) {
			this.rollbackTransaction();
			throw new MedicineRuntimeException(t.getMessage());
		}
		return count;
	}
}
