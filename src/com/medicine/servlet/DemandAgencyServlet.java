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

import com.medicine.cl.DemandAgencyManager;
import com.medicine.entities.DemandAgency;
import com.medicine.util.JSONUtil;
import com.medicine.util.StringUtil;

import net.sf.json.JSONObject;

@WebServlet("/DemandAgencyServlet")
public class DemandAgencyServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
			if (StringUtils.equals(method, "addDemandAgency")) {
				addDemandAgency(request, response);
			}
			if (StringUtils.equals(method, "findAllDemandAgencys")) {
				findAllDemandAgencys(request,response);
			}
			if (StringUtils.equals(method, "updateDemandAgency")) {
				updateDemandAgency(request, response);
			}
			if (StringUtils.equals(method, "deleteDemandAgency")) {
				deleteDemandAgency(request, response);
			}
			if (StringUtils.equals(method, "findDemandAgencyByCode")) {
				findDemandAgencyByCode(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("exception", e.getMessage());
		}
	}

	private void findAllDemandAgencys(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		PrintWriter out = response.getWriter();
		try {
			String demandAgencyName = request.getParameter("demandAgencyName");
			String demandAgencyType = request.getParameter("demandAgencyType");
			List<DemandAgency> demandAgencies = demandAgencyManager.findAllDemandAgencys(demandAgencyName, demandAgencyType);
			String str = JSONUtil.serializeObject(demandAgencies);
			json.put("code", 0);
			json.put("demandAgencies", str);
		} catch (Exception e) {
			String error = "findAllDemandAgencys Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}

	private void addDemandAgency(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		DemandAgency demandAgency = null;
		try {
			JSONObject jsonObject = null;
			String demandAgencyStr = request.getParameter("demandAgency");
			jsonObject = JSONObject.fromObject(demandAgencyStr);
			demandAgency = (DemandAgency) JSONObject.toBean(jsonObject, DemandAgency.class);
			if (demandAgency != null) {
				int count = demandAgencyManager.addDemandAgency(demandAgency);
				if (count != 0) {
					json.put("code", 0);
					json.put("id", count);
				}
			} else {
				json.put("code", -1);
				json.put("error", "对象处理错误！");
			}
		} catch (Exception e) {
			String error = "addDemandAgency Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}
	
	private void updateDemandAgency(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		DemandAgency demandAgency = null;
		try {
			JSONObject jsonObject = null;
			String demandAgencyStr = request.getParameter("demandAgency");
			jsonObject = JSONObject.fromObject(demandAgencyStr);
			demandAgency = (DemandAgency) JSONObject.toBean(jsonObject, DemandAgency.class);
			if (demandAgency != null) {
				int count = demandAgencyManager.updateDemandAgency(demandAgency);
				if (count != 0) {
					json.put("code", 0);
				}
			} else {
				json.put("code", -1);
				json.put("error", "对象处理错误！");
			}
		} catch (Exception e) {
			String error = "updateDemandAgency Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}
	
	private void deleteDemandAgency(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		try {
			String idStr = request.getParameter("ids");
			int[] ids = StringUtil.StringToInt(StringUtil.convertStrToArray(idStr));
			if (ids != null && ids.length > 0) {
				int count = demandAgencyManager.deleteDemandAgency(ids);
				if (count != 0) {
					json.put("code", 0);
				}
			} else {
				json.put("code", -1);
				json.put("error", "对象处理错误！");
			}
		} catch (Exception e) {
			String error = "deleteDemandAgency Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}
	   
	private void findDemandAgencyByCode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		JSONObject jsonObject = new JSONObject();
		try {
			String demandAgencyCode = req.getParameter("demandAgencyCode");
			if (demandAgencyCode != null) {
				DemandAgency demandAgency = demandAgencyManager.findDemandAgencyByCode(demandAgencyCode);
				String json = JSONUtil.serializeObject(demandAgency);
				jsonObject.put("code", 0);
				jsonObject.put("demandAgency", json);
			}else {
				jsonObject.put("code", -1);
				jsonObject.put("error", "需求机构编码不能为空！");
			}
		} catch (Exception e) {
			jsonObject.put("code", -1);
			jsonObject.put("error", e.getMessage());
		}
		out.write(jsonObject.toString());
	}
}
