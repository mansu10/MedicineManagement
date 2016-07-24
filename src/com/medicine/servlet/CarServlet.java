package com.medicine.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.medicine.dao.CarDAO;
import com.medicine.entities.Car;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/CarServlet")
public class CarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	CarDAO cDao = new CarDAO();
	
    public CarServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");  
        PrintWriter out = response.getWriter();  
        response.setContentType("text/html;charset=UTF-8");  
        List<Car> cars = cDao.findAll();
        //将List转换成json字符串
        JSONArray jsonArray = new JSONArray();
        for (Car car : cars) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", car.getId());
			jsonObject.put("carCode", car.getCarCode());
			jsonObject.put("carName", car.getCarName());
			jsonObject.put("carUnit", car.getCarUnit());
			jsonObject.put("carDriver", car.getCarDriver());
			jsonObject.put("carVolume", car.getCarVolume());
			jsonObject.put("maxWeight", car.getMaxWeight());
			jsonArray.add(jsonObject);
			
		}
        out.write(jsonArray.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
