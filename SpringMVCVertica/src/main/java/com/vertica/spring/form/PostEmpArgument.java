package com.vertica.spring.form;

import com.vertica.spring.model.Employee;

public class PostEmpArgument {

	private String typeUpdate;
	private Integer index;
	private Employee employee;
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getTypeUpdate() {
		return typeUpdate;
	}
	public void setTypeUpdate(String typeUpdate) {
		this.typeUpdate = typeUpdate;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public PostEmpArgument() {
		super();
	}
	
}
