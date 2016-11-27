package com.medicine.cl;

import java.util.List;

import com.medicine.dao.OperatorDAO;
import com.medicine.entities.Operator;

public class OperatorManager extends DAOManager {

	private OperatorDAO operatorDAO = new OperatorDAO();
	
	public List<Operator> findOperatorByPosition(String position) {
		List<Operator> operators = null;
		operators = operatorDAO.findOperatorByPosition(position);
		return operators;
	}
}
