package com.medicine.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.medicine.cl.DemandAgencyManager;
import com.medicine.cl.SupplyAgencyManager;
import com.medicine.entities.SupplyAgency;
import com.medicine.entities.SupplyAgencyJob;
import com.medicine.util.JSONUtil;
import com.medicine.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/SupplyAgencyServlet")
public class SupplyAgencyServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SupplyAgencyManager supplyAgencyManager = new SupplyAgencyManager();
	private DemandAgencyManager demandAgencyManager = new DemandAgencyManager();
	
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
			if (StringUtils.equals(method, "addSupplyAgency")) {
				addSupplyAgency(request, response);
			}
			if (StringUtils.equals(method, "findAllSupplyAgencys")) {
				findAllSupplyAgencys(request,response);
			}
			if (StringUtils.equals(method, "updateSupplyAgency")) {
				updateSupplyAgency(request, response);
			}
			if (StringUtils.equals(method, "deleteSupplyAgency")) {
				deleteSupplyAgency(request, response);
			}
			if (StringUtils.equals(method, "findSupplyAgencyByCode")) {
				findSupplyAgencyByCode(request, response);
			}
			if (StringUtils.equals(method, "findAllSupplyAgencyAndDemandAgencyCodes")) {
				findAllSupplyAgencyAndDemandAgencyCodes(request, response);
			}
			
		} catch (Exception e) {
			request.setAttribute("exception", e.getMessage());
		}
	}

	private void findSupplyAgencyByCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		PrintWriter out = response.getWriter();
		try {
			String supplyAgencyCode = request.getParameter("supplyAgencyCode");
			SupplyAgency supplyAgency = supplyAgencyManager.findSupplyAgencyByCode(supplyAgencyCode);
			String supplyAgencyAndJob = JSONUtil.serializeObject(supplyAgency);
			json.put("code", 0);
			json.put("order", supplyAgencyAndJob);
		} catch (Exception e) {
			String error = "findAllSupplyAgencys Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());

	}

	private void findAllSupplyAgencys(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		PrintWriter out = response.getWriter();
		try {
			String supplyAgencyName = request.getParameter("supplyAgencyName");
			String supplyAgencyType = request.getParameter("supplyAgencyType");
			List<SupplyAgency> supplyAgencies = supplyAgencyManager.findAllSupplyAgencys(supplyAgencyName, supplyAgencyType);
			String str = JSONUtil.serializeObject(supplyAgencies);
			json.put("code", 0);
			json.put("supplyAgencies", str);
		} catch (Exception e) {
			String error = "findAllSupplyAgencys Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}

	private void addSupplyAgency(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		SupplyAgency supplyAgency = null;
		try {
			JSONObject jsonObject = null;
			String supplyAgencyStr = request.getParameter("supplyAgency");
			jsonObject = JSONObject.fromObject(supplyAgencyStr);
			supplyAgency = (SupplyAgency) JSONObject.toBean(jsonObject, SupplyAgency.class);
			String supplyAgencyJobsStr = request.getParameter("supplyAgencyJobs");
			jsonArray = JSONArray.fromObject(supplyAgencyJobsStr);
			List<SupplyAgencyJob> supplyAgencyJobs = new ArrayList<SupplyAgencyJob>();
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject json2 = jsonArray.getJSONObject(i);
				supplyAgencyJobs.add((SupplyAgencyJob) JSONObject.toBean(json2, SupplyAgencyJob.class));
			}
			if (supplyAgency != null) {
				supplyAgency.setSupplyAgencyJobs(supplyAgencyJobs);
				int count = supplyAgencyManager.addSupplyAgency(supplyAgency);
				if (count != 0) {
					json.put("code", 0);
					json.put("id", count);
				}
			} else {
				json.put("code", -1);
				json.put("error", "对象处理错误！");
			}
		} catch (Exception e) {
			String error = "addSupplyAgency Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}
	
	private void updateSupplyAgency(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		SupplyAgency supplyAgency = null;
		try {
			JSONObject jsonObject = null;
			String supplyAgencyStr = request.getParameter("supplyAgency");
			jsonObject = JSONObject.fromObject(supplyAgencyStr);
			supplyAgency = (SupplyAgency) JSONObject.toBean(jsonObject, SupplyAgency.class);
			String supplyAgencyJobsStr = request.getParameter("supplyAgencyJobs");
			jsonArray = JSONArray.fromObject(supplyAgencyJobsStr);
			List<SupplyAgencyJob> supplyAgencyJobs = new ArrayList<SupplyAgencyJob>();
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject json2 = jsonArray.getJSONObject(i);
				supplyAgencyJobs.add((SupplyAgencyJob) JSONObject.toBean(json2, SupplyAgencyJob.class));
			}
			if (supplyAgency != null) {
				supplyAgency.setSupplyAgencyJobs(supplyAgencyJobs);
				int count = supplyAgencyManager.updateSupplyAgency(supplyAgency);
				if (count != 0) {
					json.put("code", 0);
				}
			} else {
				json.put("code", -1);
				json.put("error", "对象处理错误！");
			}
		} catch (Exception e) {
			String error = "updateSupplyAgency Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}
	
	private void deleteSupplyAgency(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		try {
			String idStr = request.getParameter("ids");
			int[] ids = StringUtil.StringToInt(StringUtil.convertStrToArray(idStr));
			if (ids != null && ids.length > 0) {
				int count = supplyAgencyManager.deleteSupplyAgency(ids);
				if (count != 0) {
					json.put("code", 0);
				}
			} else {
				json.put("code", -1);
				json.put("error", "对象处理错误！");
			}
		} catch (Exception e) {
			String error = "deleteSupplyAgency Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}
	
	private void findAllSupplyAgencyAndDemandAgencyCodes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		PrintWriter out = response.getWriter();
		try {
			List<String> supplyAgencyCodes = supplyAgencyManager.findAllSupplyAgencyCodes();
			List<String> demandAgencyCodes = demandAgencyManager.findAllDemandAgencyCodes();
			String str = JSONUtil.serializeObject(supplyAgencyCodes);
			String str2 = JSONUtil.serializeObject(demandAgencyCodes);
			json.put("code", 0);
			json.put("supplyAgencyCodes", str);
			json.put("demandAgencyCodes", str2);
		} catch (Exception e) {
			String error = "findAllSupplyAgencyAndDemandAgencyCodes Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}

}
