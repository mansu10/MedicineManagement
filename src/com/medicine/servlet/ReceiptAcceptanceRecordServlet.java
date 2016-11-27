package com.medicine.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.medicine.cl.ReceiptAcceptanceRecordManager;
import com.medicine.entities.ReceiptAcceptanceRecord;
import com.medicine.entities.ReceiptAcceptanceType;
import com.medicine.entities.ReceiptItem;
import com.medicine.util.JSONUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/ReceiptAcceptanceRecordServlet")
public class ReceiptAcceptanceRecordServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ReceiptAcceptanceRecordManager receiptAcceptanceRecordManager = new ReceiptAcceptanceRecordManager();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddhhmmss");

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String method = request.getParameter("method");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		try {
			if (StringUtils.equals(method, "addReceiptAcceptanceRecord")) {
				addReceiptAcceptanceRecord(request, response);
			} else if (StringUtils.equals(method, "queryAllReceiptAcceptanceRecords")) {
				queryAllReceiptAcceptanceRecords(request, response);
			} else if (StringUtils.equals(method, "queryRecordAndItemByCode")) {
				queryRecordAndItemByCode(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("exception", e.getMessage());
		}
	}

	private void queryRecordAndItemByCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		PrintWriter out = response.getWriter();
		ReceiptAcceptanceRecord record = null;
		String receiptCode = request.getParameter("receiptCode");
		try {
			record = receiptAcceptanceRecordManager.queryRecordAndItemByCode(receiptCode);
			String recordAndItem = JSONUtil.serializeObject(record);
			json.put("code", 0);
			json.put("receiptAcceptanceRecord", recordAndItem);
		} catch (Exception e) {
			String error = "queryRecordAndItemByCode Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());

	}

	private void queryAllReceiptAcceptanceRecords(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		PrintWriter out = response.getWriter();

		List<ReceiptAcceptanceRecord> records = new ArrayList<ReceiptAcceptanceRecord>();
		try {
			String receiptTimeStartStr = request.getParameter("receiptTimeStartStr");
			String receiptTimeEndStr = request.getParameter("receiptTimeEndStr");
			records = receiptAcceptanceRecordManager.queryAllRecords(receiptTimeStartStr, receiptTimeEndStr);
			for (ReceiptAcceptanceRecord record : records) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", record.getId());
				jsonObject.put("receiptCode", record.getReceiptCode());
				jsonObject.put("receiptTime", sdf.format(record.getReceiptTime()));
				jsonObject.put("goodsDescription", record.getGoodsDescription());
				if (record.getReceiptType() == 0 || record.getReceiptType() == 1 || record.getReceiptType() == 2 || record.getReceiptType() == 3) {
					jsonObject.put("receiptType", ReceiptAcceptanceType.valueOf(record.getReceiptType()).toString());
				}
				jsonObject.put("supplierName", record.getSupplierName());
				jsonObject.put("deliveryName", record.getDeliveryName());
				jsonArray.add(jsonObject);
			}
			json.put("code", 0);
			json.put("receiptAcceptanceRecords", jsonArray.toString());
		} catch (Exception e) {
			String error = "queryAllReceiptAcceptanceRecords Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());

	}

	@SuppressWarnings("unused")
	private void addReceiptAcceptanceRecord(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		PrintWriter out = response.getWriter();
		ReceiptAcceptanceRecord receiptAcceptanceRecord = new ReceiptAcceptanceRecord();
		try {
			String receiptAcceptanceRecordStr = request.getParameter("receiptAcceptanceRecord");
			jsonObject = JSONObject.fromObject(receiptAcceptanceRecordStr);
			receiptAcceptanceRecord = (ReceiptAcceptanceRecord) JSONObject.toBean(jsonObject,
					ReceiptAcceptanceRecord.class);
			String receiptItems = request.getParameter("receiptItems");
			if (StringUtils.isEmpty(receiptItems)) {
				jsonObject.put("code", -1);
				jsonObject.put("error", "明细不能为空！");
				return;
			}
			// 将jsonArray转换成对象
			jsonArray = JSONArray.fromObject(receiptItems);
			List<ReceiptItem> items = new ArrayList<ReceiptItem>();
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject json = jsonArray.getJSONObject(i);
				items.add((ReceiptItem) JSONObject.toBean(json, ReceiptItem.class));
			}
			receiptAcceptanceRecord.setReceiptCode(sdf2.format(new Date()));
			receiptAcceptanceRecord.setItems(items);
			// receiptAcceptanceRecord.setReceiptTime(sdf.parse(receiptTimeStr));
			// receiptAcceptanceRecord.setReceiptDepot(receiptDepot);
			// receiptAcceptanceRecord.setReceiver(receiver);
			// receiptAcceptanceRecord.setGoodsDescription(goodsDescription);
			// receiptAcceptanceRecord.setSupplierName(supplierName);
			// receiptAcceptanceRecord.setSupplierContacts(supplierContacts);
			// receiptAcceptanceRecord.setSupplierTel(supplierTel);
			// receiptAcceptanceRecord.setDeliveryName(deliveryName);
			// receiptAcceptanceRecord.setDeliveryContactes(deliveryContactes);
			// receiptAcceptanceRecord.setDeliveryTel(deliveryTel);

			if (receiptAcceptanceRecord != null) {
				int count = receiptAcceptanceRecordManager.addReceiptAcceptanceRecord(receiptAcceptanceRecord);
				if (count != 0) {
					jsonObject.put("code", 0);
				}
			} else {
				jsonObject.put("code", -1);
				jsonObject.put("error", "对象处理错误！");
			}
		} catch (Exception e) {
			String error = "addReceiptAcceptanceRecord Fail error:" + e.getMessage();
			jsonObject.put("code", -1);
			jsonObject.put("error", error);
		}
		out.write(jsonObject.toString());
	}

}
