package com.medicine.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.medicine.cl.StorageRecordManager;
import com.medicine.entities.StorageRecord;

import net.sf.json.JSONObject;

@WebServlet("/StorageRecordServlet")
public class StorageRecordServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StorageRecordManager storageRecordManager = new StorageRecordManager();
	private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddhhmmss");
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String method = request.getParameter("method");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		try {
			if (StringUtils.equals(method, "addStorageRecord")) {
				addStorageRecord(request, response);
			} 

		} catch (Exception e) {
			request.setAttribute("exception", e.getMessage());
		}
	}

	private void addStorageRecord(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		PrintWriter out = response.getWriter();
		try {
			StorageRecord storageRecord = null;
			JSONObject jsonObject = null;
			String storageRecordStr = request.getParameter("storageRecord");
			jsonObject = JSONObject.fromObject(storageRecordStr);
			storageRecord = (StorageRecord) JSONObject.toBean(jsonObject, StorageRecord.class);
			if (storageRecord != null) {
				storageRecord.setStorageRecordCode(sdf2.format(new Date()));
				int count = storageRecordManager.addStorageRecord(storageRecord);
				if (count != 0) {
					json.put("code", 0);
				}
			} else {
				json.put("code", -1);
				json.put("error", "对象处理错误！");
			}
		} catch (Exception e) {
			String error = "addStorageRecord Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}
}
