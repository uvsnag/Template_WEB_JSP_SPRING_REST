package bean;

import java.io.Serializable;

public class EmployeeMulti  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int depId;
	String empName;
	String dateOfBirth;
	String address;
	String depName;
	String describe;
	
	
	public EmployeeMulti() {
		super();
	}
	public EmployeeMulti(String empName, String dateOfBirth, String address, String depName,
			String describe) {
		super();
		this.empName = empName;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.depName = depName;
		this.describe = describe;
	}
	
	public EmployeeMulti(int depId, String empName, String dateOfBirth, String address, String depName,
			String describe) {
		super();
		this.depId = depId;
		this.empName = empName;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.depName = depName;
		this.describe = describe;
	}
	public int getDepId() {
		return depId;
	}
	public void setDepId(int depId) {
		this.depId = depId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
	
}
