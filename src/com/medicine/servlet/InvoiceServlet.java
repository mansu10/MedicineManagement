package com.medicine.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.medicine.cl.InvoiceManager;
import com.medicine.entities.Invoice;
import com.medicine.util.JSONUtil;

import net.sf.json.JSONObject;

@WebServlet("/InvoiceServlet")
public class InvoiceServlet extends HttpServlet {

	/**
	 * @author tdw
	 * @time: 2016年11月5日下午4:39:04
	 */
	private static final long serialVersionUID = 1L;

	private InvoiceManager invoiceManager = new InvoiceManager();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getParameter("method");
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json;charset=UTF-8");
		resp.setHeader("Access-Control-Allow-Origin", "*");
		try {
			if (StringUtils.equals(method, "findAllInvoices")) {
				findAllInvoices(req,resp);
			}
		} catch (Exception e) {
			req.setAttribute("exception", e.getMessage());
		}
	}

	private void findAllInvoices(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		JSONObject jsonObject = new JSONObject();
		try {
			List<Invoice> invoices = invoiceManager.findAllInvoices();
			String json = JSONUtil.serializeObject(invoices);
			jsonObject.put("code", 0);
			jsonObject.put("invoices", json);
		} catch (Exception e) {
			jsonObject.put("code", -1);
			jsonObject.put("error", e.getMessage());
		}
		
		out.write(jsonObject.toString());		
	}
}
