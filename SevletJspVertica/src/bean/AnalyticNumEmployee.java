package bean;

import java.io.Serializable;

public class AnalyticNumEmployee implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Integer index;
	String name;
	Integer numOfEmployee;
	
	
	public AnalyticNumEmployee() {
		super();
	}
	public AnalyticNumEmployee(Integer index, String name, Integer numOfEmployee) {
		super();
		this.index = index;
		this.name = name;
		this.numOfEmployee = numOfEmployee;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNumOfEmployee() {
		return numOfEmployee;
	}
	public void setNumOfEmployee(Integer numOfEmployee) {
		this.numOfEmployee = numOfEmployee;
	}
	

}
