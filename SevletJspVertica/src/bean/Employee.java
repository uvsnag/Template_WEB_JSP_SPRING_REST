package bean;

import java.io.Serializable;
import java.util.Objects;

public class Employee  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Integer index;
	String name;
	String dateOfBirth;
	String address;
	String idDep;
	
	public Employee() {

	}

	public Employee(Integer index, String name, String dateOfBirth, String address, String idDep) {
		super();
		this.index = index;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.idDep = idDep;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getIdDep() {
		return idDep;
	}

	public void setIdDep(String idDep) {
		this.idDep = idDep;
	}

	public boolean isNotNull() {
		return Objects.nonNull(index) || Objects.nonNull(name);
	}
	
}
