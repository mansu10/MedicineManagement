package com.daug.dao;

import java.util.List;

import org.junit.Test;

import com.medicine.dao.OrganizationDAO;
import com.medicine.entities.Organization;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class OrganizationDaoTest {

	OrganizationDAO organizationDAO = null;

	@Test
	public void test() {
		organizationDAO = new OrganizationDAO();
		List<Organization> organizations = organizationDAO.findAll();
		
		JSONArray jsonArray = new JSONArray();
        for (Organization organization : organizations) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", organization.getId());
			jsonObject.put("OrganizationName", organization.getOrganizationName());
			jsonObject.put("OrganizationType", organization.getOrganizationType());
			jsonObject.put("OrganizationPeopleNumbe", organization.getOrganizationPeopleNumber());
			jsonObject.put("OrganizationAddress", organization.getOrganizationAddress());
			jsonObject.put("OrganizationCoordinate", organization.getOrganizationCoordinate());
			jsonArray.add(jsonObject);
		}	
        System.out.println(jsonArray.toString());
		
	}

}
