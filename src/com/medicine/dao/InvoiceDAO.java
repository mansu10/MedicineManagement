package com.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.medicine.entities.Invoice;
import com.medicine.util.MedicineRuntimeException;

public class InvoiceDAO {

	private Connection getConn() throws SQLException{
		return DBHelper.getConn();
	}
	
	public List<Invoice> findAllInvoices() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Invoice> invoices = new ArrayList<Invoice>();
		String sql = "select distinct ti.order_code,ti.id,ti.invoice_code,ti.transport_route,ti.invoice_status,tor.level,tor.delivery_time,tor.receipt_address,tib.box_number,tii.product_code,tii.product_number "
				+ "from tbl_invoice ti "
				+ "left join tbl_order tor on ti.order_code = tor.order_code "
				+ "left join tbl_invoice_box tib on ti.invoice_code = tib.invoice_code "
				+ "left join tbl_invoice_item tii on ti.invoice_code = tii.invoice_code  "
				+ "where ti.invoice_status='待装载'";
		try {
			stmt = this.getConn().prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Invoice invoice = new Invoice();
				invoice.setId(rs.getInt("id"));
				invoice.setInvoiceCode(rs.getString("invoice_code"));
				invoice.setOrderCode(rs.getString("order_code"));
				invoice.setInvoiceStatus(rs.getString("invoice_status"));
				invoice.setTransportRoute(rs.getString("transport_route"));
				invoice.setLevel(rs.getInt("level"));
				invoice.setReceiptAddress(rs.getString("receipt_address"));
				invoice.setDeliveryTime(rs.getTimestamp("delivery_time"));
				invoice.setBoxNumber(rs.getString("box_number"));
				invoice.setProductCode(rs.getString("product_code"));
				invoice.setProductNumber(rs.getInt("product_number"));
				invoices.add(invoice);
			}
		} catch (SQLException ex) {
			throw new MedicineRuntimeException(ex.getMessage());
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
		
		return invoices;
	}
	
	public Invoice findInvoiceByCode(String invoiceCode) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Invoice invoice = new Invoice();
		
		String sql = "select * from tbl_invoice where invoice_code = ?";
		try {
			stmt = this.getConn().prepareStatement(sql);
			stmt.setString(1, invoiceCode);
			rs = stmt.executeQuery();
			while (rs.next()) {
				invoice.setId(rs.getInt("id"));
				invoice.setInvoiceCode(rs.getString("invoice_code"));
				invoice.setOrderCode(rs.getString("order_code"));
				invoice.setInvoiceStatus(rs.getString("invoice_status"));
				invoice.setTransportRoute(rs.getString("transport_route"));
				invoice.setReceiptAddress(rs.getString("receipt_address"));
			}
		} catch (SQLException ex) {
			throw new MedicineRuntimeException(ex.getMessage());
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
		
		return invoice;
	}
}
