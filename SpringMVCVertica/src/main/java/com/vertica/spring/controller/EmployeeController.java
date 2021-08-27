package com.vertica.spring.controller;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vertica.spring.common.ErrorMessage;
import com.vertica.spring.dao.EmployeeDAO;
import com.vertica.spring.form.EmployeeReturnValue;
import com.vertica.spring.form.PostEmpArgument;
import com.vertica.spring.model.Employee;

@CrossOrigin("*") 
@RestController
public class EmployeeController {
 
    @Autowired
    private EmployeeDAO employeeDAO;
 
    @RequestMapping(value = { "/info" },
    		 method = RequestMethod.GET, //
             produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
	public EmployeeReturnValue simpleGet(@RequestParam(required=false, name="typeUpdate") String type,
			@RequestParam(required=false, name="id") String id ) {
		EmployeeReturnValue result = new EmployeeReturnValue();
		
		if (StringUtils.isNumeric(id)&& StringUtils.equals("get-update",type)
				&& employeeDAO.countById(Integer.parseInt(id))>0 ) {
			Employee emp = employeeDAO.findById(Integer.parseInt(id)).get(0);
			result.setEmployeeInfo(emp);
		}
		List<Employee> list = employeeDAO.findAll();
		result.setListEmployeeInfo(list);

		return result;
	}
    
    
    @RequestMapping(value = { "/info" }, method = RequestMethod.POST,
    		   produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
	public EmployeeReturnValue simperPost(@RequestBody PostEmpArgument postEmpArgument) {
		EmployeeReturnValue result = new EmployeeReturnValue();
		String errorMessage = "";

		if (Objects.nonNull(postEmpArgument) && Objects.nonNull(postEmpArgument.getIndex())) {
			int rowCount = employeeDAO.countById(postEmpArgument.getEmployee().getIndex());
			int resultCode = 0;

			switch (postEmpArgument.getTypeUpdate()) {
			case "delete":
				resultCode = employeeDAO.delete(postEmpArgument.getIndex());
				errorMessage = (resultCode == 1) ? ErrorMessage.DELETE_SUCCESS : ErrorMessage.ERROR;
				break;
			case "update":
				//
				if (rowCount > 0) {
					resultCode = employeeDAO.update(postEmpArgument.getEmployee());
					errorMessage = (resultCode == 1) ? ErrorMessage.UPDATE_SUCCESS : ErrorMessage.ERROR;
				} else {
					resultCode = employeeDAO.insert(postEmpArgument.getEmployee());
					errorMessage = (resultCode == 1) ? ErrorMessage.INSERT_SUCCESS : ErrorMessage.ERROR;
				}

				break;
			default:
				break;
			}
		}

		List<Employee> list = employeeDAO.findAll();
		result.setErrorMessage(errorMessage);
		result.setListEmployeeInfo(list);

		return result;
	}

}