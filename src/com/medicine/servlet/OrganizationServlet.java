package com.medicine.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.medicine.dao.OrganizationDAO;
import com.medicine.entities.Organization;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/OrganizationServlet")
public class OrganizationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrganizationDAO organizationDAO = new OrganizationDAO();
       
    public OrganizationServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");  
        PrintWriter out = response.getWriter();  
        response.setContentType("text/html;charset=UTF-8");  
        JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		try {
			List<Organization> organizations = organizationDAO.findAll();
			for (Organization organization : organizations) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", organization.getId());
				jsonObject.put("organizationName", organization.getOrganizationName());
				jsonObject.put("organizationType", organization.getOrganizationType());
				jsonObject.put("organizationPeopleNumber", organization.getOrganizationPeopleNumber());
				jsonObject.put("organizationAddress", organization.getOrganizationAddress());
				jsonObject.put("organizationCoordinate", organization.getOrganizationCoordinate());
				jsonArray.add(jsonObject);
				json.put("code", 0);
				json.put("order", jsonArray.toString());
			}
		} catch (Exception e) {
			String error = "findOrganization Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}	
        out.write(json.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
