package com.medicine.cl;

import java.util.ArrayList;
import java.util.List;

import com.medicine.dao.ContainerBoxDAO;
import com.medicine.dao.InvoiceDAO;
import com.medicine.dao.ProductDAO;
import com.medicine.entities.ContainerBox;
import com.medicine.entities.Invoice;
import com.medicine.entities.Product;

public class InvoiceManager extends DAOManager {

	private InvoiceDAO invoiceDAO = new InvoiceDAO();
	private ContainerBoxDAO containerBoxDAO = new ContainerBoxDAO();
	private ProductDAO productDAO = new ProductDAO();
	
	public List<Invoice> findAllInvoices() throws Exception{
		List<Invoice> invoices = new ArrayList<Invoice>();
		invoices = invoiceDAO.findAllInvoices();
		for (Invoice invoice : invoices) {
			ContainerBox containerBox = containerBoxDAO.findBoxByNumber(invoice.getBoxNumber());
			if (containerBox != null) {
				invoice.setVolume(containerBox.getBoxVolume());
			}
			Product product = productDAO.findProductByCodeOrName(invoice.getProductCode());
			if (product != null) {
				invoice.setWeight((invoice.getProductNumber()*product.getMinWeight()));
			}
		}
		return invoices;
	}
}
