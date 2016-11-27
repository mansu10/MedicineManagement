package com.medicine.cl;

import java.util.List;

import com.medicine.dao.ProductDAO;
import com.medicine.dao.ReceiptAcceptanceRecordDAO;
import com.medicine.dao.ReceiptItemDAO;
import com.medicine.dao.StockDao;
import com.medicine.entities.Product;
import com.medicine.entities.ReceiptAcceptanceRecord;
import com.medicine.entities.ReceiptItem;
import com.medicine.entities.Stock;
import com.medicine.util.MedicineRuntimeException;

public class ReceiptAcceptanceRecordManager extends DAOManager {

	private ReceiptAcceptanceRecordDAO receiptAcceptanceRecordDAO = new ReceiptAcceptanceRecordDAO();
	private ReceiptItemDAO receiptItemDAO = new ReceiptItemDAO();
	private ProductDAO productDAO = new ProductDAO();
	private StockDao stockDao = new StockDao();
	
	public int addReceiptAcceptanceRecord(ReceiptAcceptanceRecord record) {
		int count = 0;
		try {
			this.beginTransaction();
			count = receiptAcceptanceRecordDAO.addReceiptAcceptanceRecord(record);
			if (record.getItems() != null) {
				for (ReceiptItem item : record.getItems()) {
					item.setReceiptCode(record.getReceiptCode());
					receiptItemDAO.addReceiptItem(item);
					count ++;
				}
			}
			this.commitTransaction();
		} catch (Throwable t) {
			this.rollbackTransaction();
			throw new MedicineRuntimeException(t.getMessage());
		}
		return count;
	}
	
	public ReceiptAcceptanceRecord queryRecordAndItemByCode(String receiptCode) {
		ReceiptAcceptanceRecord record = null;
		record = receiptAcceptanceRecordDAO.queryRecordByCode(receiptCode);
		List<ReceiptItem> items = receiptItemDAO.queryRecordItemByCode(receiptCode);
		for (ReceiptItem receiptItem : items) {
			Product product = productDAO.findProductByCode(receiptItem.getProductCode());
			receiptItem.setProduct(product);
			Stock stock = stockDao.findStockByProdcutCode(receiptItem.getProductCode());
			receiptItem.setStock(stock);
		}
		if (record != null) {
			record.setItems(items);
		}
		return record;
	}
	
	public List<ReceiptAcceptanceRecord> queryAllRecords(String receiptTimeStartStr, String receiptTimeEndStr) {
		List<ReceiptAcceptanceRecord> records = null;
		records = receiptAcceptanceRecordDAO.queryAllReceiptAcceptanceRecordsByReceiptTime(receiptTimeStartStr, receiptTimeEndStr);
		return records;
	}
}
