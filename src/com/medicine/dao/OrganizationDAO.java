package com.medicine.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.medicine.entities.Organization;

public class OrganizationDAO {
	static DBHelper db = null; 
	static ResultSet ret = null;
	List<Organization> organizations  = null;
	Organization organization = null;
	String sql = null;
	
	public List<Organization> findAll(){
		sql = "select id,organization_name,organization_type,organization_people_numble,organization_address,organization_coordinate from tbl_organization";
		db = new DBHelper(sql);
		organizations = new ArrayList<Organization>();
		try {  
            ret = db.pst.executeQuery(); 
            while (ret.next()) {  
            	organization= new Organization();
            	organization.setId(ret.getInt("id"));
            	organization.setOrganizationName(ret.getString("organization_name"));
            	organization.setOrganizationType(ret.getString("organization_type"));
            	organization.setOrganizationPeopleNumber(ret.getInt("organization_people_numble"));
            	organization.setOrganizationAddress(ret.getString("organization_address"));
            	organization.setOrganizationCoordinate(ret.getString("organization_coordinate"));
            	organizations.add(organization);
            }
            ret.close();  
            db.close(); 
        } catch (SQLException e) {  
            e.printStackTrace();  
        } 
		return organizations;
	}
}
