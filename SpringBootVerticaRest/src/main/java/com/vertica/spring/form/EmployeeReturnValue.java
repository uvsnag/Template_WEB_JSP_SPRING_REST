package com.vertica.spring.form;

import java.util.List;

import com.vertica.spring.model.Employee;

public class EmployeeReturnValue {
	
	private String errorMessage;
	private Employee employeeInfo;
	private List<Employee>  listEmployeeInfo;
	
	public EmployeeReturnValue() {
		super();
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Employee getEmployeeInfo() {
		return employeeInfo;
	}
	public void setEmployeeInfo(Employee employeeInfo) {
		this.employeeInfo = employeeInfo;
	}
	public List<Employee> getListEmployeeInfo() {
		return listEmployeeInfo;
	}
	public void setListEmployeeInfo(List<Employee> listEmployeeInfo) {
		this.listEmployeeInfo = listEmployeeInfo;
	}
	
}
