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

import com.medicine.cl.CarManager;
import com.medicine.entities.Car;
import com.medicine.entities.CarStatus;
import com.medicine.util.JSONUtil;
import com.medicine.util.StringUtil;

import net.sf.json.JSONObject;

@WebServlet("/CarServlet")
public class CarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CarManager carManager = new CarManager();

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
			if (StringUtils.equals(method, "queryAllCars")) {
				queryAllCars(request, response);
			} else if (StringUtils.equals(method, "addCar")) {
				addCar(request, response);
			} else if (StringUtils.equals(method, "updateCar")) {
				updateCar(request, response);
			} else if (StringUtils.equals(method, "deleteCar")) {
				deleteCar(request, response);
			} else if (StringUtils.equals(method, "addOrdersToCar")) {
				addOrdersToCar(request, response);
			} else if (StringUtils.equals(method, "queryAllCarsWithStatus")) {
				queryAllCarsWithStatus(request, response);
			} else if (StringUtils.equals(method, "queryAllCarsWithCode")) {
				queryAllCarsWithCode(request, response);
			} 
			else if (StringUtils.equals(method, "findStowageCodesByCarCode")) {
				findStowageCodesByCarCode(request, response);
			}
			else if (StringUtils.equals(method, "updateCarStatusByCode")) {
				updateCarStatusByCode(request, response);
			}
			else if (StringUtils.equals(method, "updateInstructionByCode")) {
				updateInstructionByCode(request, response);
			}

		} catch (Exception e) {
			request.setAttribute("exception", e.getMessage());
		}
	}

	private void findStowageCodesByCarCode(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		String carCode = request.getParameter("carCode");
		String carStatus = request.getParameter("carStatus");
		try {
			List<String> stowageCode = carManager.findStowageCodesByCarCode(carStatus,carCode);
			String str = JSONUtil.serializeObject(stowageCode);
			json.put("code", 0);
			json.put("stowageCode", str);
		} catch (Exception e) {
			String error = "findStowageCodesByCarCode Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}

	private void addOrdersToCar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		PrintWriter out = response.getWriter();
		try {
			String idStr = request.getParameter("id");
			String carOrders = request.getParameter("carOrders");
			if (idStr != null) {
				carManager.addOrdersToCar(carOrders, Integer.parseInt(idStr));
				json.put("code", 0);
			} else {
				json.put("code", -1);
				json.put("error", "id不能为空！");
			}
		} catch (Exception e) {
			String error = "addOrderToCar Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}

	private void deleteCar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		PrintWriter out = response.getWriter();
		try {
			String idStr = request.getParameter("ids");
			int[] ids = StringUtil.StringToInt(StringUtil.convertStrToArray(idStr));
			if (ids != null && ids.length > 0) {
				int count = carManager.deleteCarById(ids);
				if (count != 0) {
					json.put("code", 0);
				}

			} else {
				json.put("code", -1);
				json.put("error", "id不能为空！");
			}
		} catch (Exception e) {
			String error = "deleteCar Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}

	private void updateCar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		PrintWriter out = response.getWriter();
		try {
			Car car = null;
			JSONObject jsonObject = null;
			String carStr = request.getParameter("car");
			jsonObject = JSONObject.fromObject(carStr);
			car = (Car) JSONObject.toBean(jsonObject, Car.class);
			if (car != null) {
				int count = carManager.updateCar(car);
				if (count != 0) {
					json.put("code", 0);
				}
			} else {
				json.put("code", -1);
				json.put("error", "对象处理错误！");
			}
		} catch (Exception e) {
			String error = "updateCar Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}

	private void addCar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		PrintWriter out = response.getWriter();
		try {
			Car car = null;
			JSONObject jsonObject = null;
			String carStr = request.getParameter("car");
			jsonObject = JSONObject.fromObject(carStr);
			car = (Car) JSONObject.toBean(jsonObject, Car.class);
			if (car != null) {
				int count = carManager.addCar(car);
				if (count != 0) {
					json.put("code", 0);
					json.put("id", count);
				}
			} else {
				json.put("code", -1);
				json.put("error", "对象处理错误！");
			}
		} catch (Exception e) {
			String error = "addCar Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());

	}

	private void updateCarStatusByCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		PrintWriter out = response.getWriter();
		try {
			String carStatus = request.getParameter("carStatus");
			String carCode = request.getParameter("carCode");
			int count = carManager.updateCarStatusByCode(carStatus, carCode);
			if (count != 0) {
				json.put("code", 0);
				json.put("id", count);
			} else {
				json.put("code", -1);
				json.put("error", "对象处理错误！");
			}
		} catch (Exception e) {
			String error = "updateCarStatusByCode Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());

	}
	private void updateInstructionByCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		PrintWriter out = response.getWriter();
		try {
			String instruction = request.getParameter("instruction");
			String carCode = request.getParameter("carCode");
			int count = carManager.updateInstructionByCode(instruction, carCode);
			if (count != 0) {
				json.put("code", 0);
				json.put("id", count);
			} else {
				json.put("code", -1);
				json.put("error", "对象处理错误！");
			}
		} catch (Exception e) {
			String error = "updateInstructionByCode Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
		
	}

	private void queryAllCars(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		String numberOrType = request.getParameter("numberOrCode");
		String agencyCode = request.getParameter("agencyCode");
		try {
			List<Car> cars = carManager.queryAllCars(numberOrType, agencyCode);
			String str = JSONUtil.serializeObject(cars);
			json.put("code", 0);
			json.put("cars", str);
		} catch (Exception e) {
			String error = "findAllCars Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}

	private void queryAllCarsWithCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		String agencyCode = request.getParameter("agencyCode");
		try {
			List<CarStatus> carStatus = carManager.queryAllCarStatus(agencyCode);
			String str = JSONUtil.serializeObject(carStatus);
			json.put("code", 0);
			json.put("carStatus", str);
		} catch (Exception e) {
			String error = "queryAllCarsWithCode Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}

	private void queryAllCarsWithStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		try {
			List<Car> cars = carManager.queryAllCars();
			String str = JSONUtil.serializeObject(cars);
			json.put("code", 0);
			json.put("cars", str);
		} catch (Exception e) {
			String error = "queryAllCarsWithStatus Fail error:" + e.getMessage();
			json.put("code", -1);
			json.put("error", error);
		}
		out.write(json.toString());
	}

}
