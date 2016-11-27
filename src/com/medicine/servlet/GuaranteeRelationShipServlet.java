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
import com.medicine.cl.GuaranteeRelationShipManager;
import com.medicine.cl.SupplyAgencyManager;
import com.medicine.entities.DemandAgency;
import com.medicine.entities.GuaranteeRelationShip;
import com.medicine.entities.SupplyAgency;
import com.medicine.util.JSONUtil;
import com.medicine.util.StringUtil;

import net.sf.json.JSONObject;

@WebServlet("/GuaranteeRelationShipServlet")
public class GuaranteeRelationShipServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GuaranteeRelationShipManager guaranteeRelationShipManager = new GuaranteeRelationShipManager();
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
			if (StringUtils.equals(method, "addGuaranteeRelationShip")) {
				addGuaranteeRelationShip(request, response);
			}
			else if (StringUtils.equals(method, "findAllGuaranteeRelationShips")) {
				findAllGuaranteeRelationShips(request,response);
			}
			else if (StringUtils.equals(method, "findAllGuaranteeRelationShipsBySupplyAgencyCode")) {
				findAllGuaranteeRelationShipsBySupplyAgencyCode(request,response);
			}
			else if (StringUtils.equals(method, "deleteGuaranteeRelationShip")) {
				deleteGuaranteeRelationShip(request, response);
			}
			else if (StringUtils.equals(method, "updateGuaranteeRelationShip")) {
				updateGuaranteeRelationShip(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("exception", e.getMessage());
		}
	}

	private void deleteGuaranteeRelationShip(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		PrintWriter out = response.getWriter();
		try {
			String idStr = request.getParameter("ids");
			int[] ids = StringUtil.StringToInt(StringUtil.convertStrToArray(idStr));
			if (ids != null && ids.length > 0) {
				int count = guaranteeRelationShipManager.deleteGuaranteeRelationShip(ids);
				if (count != 0) {
					json.put("code", 0);
				}
			} else {
				json.put("code", -1);
				json.put("error", "id不能为空！");
			}
		} catch (Exception e) {
			String error = "deleteGuaranteeRelationShip Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}

	private void findAllGuaranteeRelationShips(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		String demandAgencyCode = request.getParameter("demandAgencyCode");
		String supplyAgencyCode = request.getParameter("supplyAgencyCode");
		try {
			List<GuaranteeRelationShip> guaranteeRelationShips = guaranteeRelationShipManager.findAllGuaranteeRelationShips(supplyAgencyCode, demandAgencyCode);
			for (GuaranteeRelationShip guaranteeRelationShip : guaranteeRelationShips) {
				DemandAgency demandAgency = demandAgencyManager.findDemandAgencyByCode(guaranteeRelationShip.getDemandAgencyCode());
				SupplyAgency supplyAgency = supplyAgencyManager.findSupplyAgencyByCode(guaranteeRelationShip.getSupplyAgencyCode());
				guaranteeRelationShip.setDemandAgencyName(demandAgency.getDemandAgencyName());
				guaranteeRelationShip.setDemandAgencyAddress1(demandAgency.getDemandAgencyAddress1());
				guaranteeRelationShip.setSupplyAgencyName(supplyAgency.getSupplyAgencyName());
				guaranteeRelationShip.setSupplyAgencyAddress1(supplyAgency.getSupplyAgencyAddress1());
			}
			String str = JSONUtil.serializeObject(guaranteeRelationShips);
			json.put("code", 0);
			json.put("guaranteeRelationShips", str);
		} catch (Exception e) {
			String error = "findAllGuaranteeRelationShips Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}
	
	private void findAllGuaranteeRelationShipsBySupplyAgencyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		String supplyAgencyCode = request.getParameter("supplyAgencyCode");
		try {
			List<GuaranteeRelationShip> guaranteeRelationShips = guaranteeRelationShipManager.findAllGuaranteeRelationShipsBySupplyAgencyCode(supplyAgencyCode);
			String str = JSONUtil.serializeObject(guaranteeRelationShips);
			json.put("code", 0);
			json.put("guaranteeRelationShips", str);
		} catch (Exception e) {
			String error = "findAllGuaranteeRelationShipsBySupplyAgencyCode Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}

	private void addGuaranteeRelationShip(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		PrintWriter out = response.getWriter();
		try {
			GuaranteeRelationShip guaranteeRelationShip = null;
			JSONObject jsonObject = null;
			String guaranteeRelationShipStr = request.getParameter("guaranteeRelationShip");
			jsonObject = JSONObject.fromObject(guaranteeRelationShipStr);
			guaranteeRelationShip = (GuaranteeRelationShip) JSONObject.toBean(jsonObject, GuaranteeRelationShip.class);
			if (guaranteeRelationShip != null) {
				int count = guaranteeRelationShipManager.addGuaranteeRelationShip(guaranteeRelationShip);
				if (count != 0) {
					json.put("code", 0);
				}
			} else {
				json.put("code", -1);
				json.put("error", "对象处理错误！");
			}
		} catch (Exception e) {
			String error = "addGuaranteeRelationShip Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}
	
	private void updateGuaranteeRelationShip(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		PrintWriter out = response.getWriter();
		try {
			GuaranteeRelationShip guaranteeRelationShip = null;
			JSONObject jsonObject = null;
			String guaranteeRelationShipStr = request.getParameter("guaranteeRelationShip");
			jsonObject = JSONObject.fromObject(guaranteeRelationShipStr);
			guaranteeRelationShip = (GuaranteeRelationShip) JSONObject.toBean(jsonObject, GuaranteeRelationShip.class);
			if (guaranteeRelationShip != null) {
				int count = guaranteeRelationShipManager.updateGuaranteeRelationShip(guaranteeRelationShip);
				if (count != 0) {
					json.put("code", 0);
				}
			} else {
				json.put("code", -1);
				json.put("error", "对象处理错误！");
			}
		} catch (Exception e) {
			String error = "updateGuaranteeRelationShip Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}
}
