package com.medicine.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.medicine.entities.User;


public class UserDAO {

	static DBHelper db = null; 
	static ResultSet ret = null;
	List<User> users  = null;
	User user = null;
	String sql = null;
	
	/**
	 * 查找所有用户
	 * @author: Tdw
	 * @time: 2016年7月17日下午10:03:31
	 */
	public List<User> findAll(){
		sql = "select user_id,user_code,user_name,user_password,role_id from tbl_user";
		db = new DBHelper(sql);
		users = new ArrayList<User>();
		try {  
            ret = db.pst.executeQuery(); 
            while (ret.next()) {  
            	user= new User();
            	user.setId(ret.getInt("user_id"));
            	user.setCode(ret.getString("user_code"));
            	user.setName(ret.getString("user_name"));
            	user.setPassword(ret.getString("user_password"));
            	user.setRoleId(ret.getInt("role_id"));
                users.add(user);
            }
            ret.close();  
            db.close(); 
        } catch (SQLException e) {  
            e.printStackTrace();  
        } 
		//JSONArray array = JSONArray.fromObject(users);
		return users;
	}
	
	/**
	 * 根据userCode查找用户
	 * @author: Tdw
	 * @time: 2016年7月17日下午10:03:48
	 */
	public User findUserByCode(String userCode){
		 sql = "select user_id,user_code,user_name,user_password,role_id "
		 		+ "from tbl_user where user_code = '" + userCode + "'";
		 db = new DBHelper(sql);
		 try {  
	            ret = db.pst.executeQuery();
	            while (ret.next()) {  
	            	user = new User();
	            	user.setId(ret.getInt("user_id"));
	            	user.setCode(ret.getString("user_code"));
	            	user.setName(ret.getString("user_name"));
	            	user.setPassword(ret.getString("user_password"));
	            	user.setRoleId(ret.getInt("role_id"));
	            }
	            ret.close();  
	            db.close();//关闭连接  
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        } 
		 //JSONObject object = JSONObject.fromObject(user);
		 return user;
	}
	
	public int deleteUserById(long id){
		 int count = 0;
		 sql = "delete from tbl_user where user_id = " + id;
		 db = new DBHelper(sql);
		 try {  
			    db.pst.setLong(1, id);
	            count = db.pst.executeUpdate();
	            db.close();
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        } 
		 return count;
	}
	
}
