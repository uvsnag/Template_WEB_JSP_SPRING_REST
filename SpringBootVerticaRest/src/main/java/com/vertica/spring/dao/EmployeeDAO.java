package com.vertica.spring.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vertica.spring.mapper.EmployeeMapper;
import com.vertica.spring.model.Employee;


 
@Repository
@Transactional
public class EmployeeDAO extends JdbcDaoSupport {
 
    @Autowired
    public EmployeeDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
    
	public Integer countById(Integer id) {
		String sql = "select count(*) as count from public.test_employee where index =?";
		System.out.println(sql);
		System.out.println("id:"+id);
		Object[] params = new Object[] { id };
		Integer rowCount = this.getJdbcTemplate().queryForObject(sql, Integer.class,  params );
		if (rowCount == null) {
			return 0;
		}
		return rowCount;
	}
 
	public List<Employee> findById(Integer id) {
		String sql = "select * from public.test_employee where index =?";
		System.out.println(sql);
		System.out.println("id:"+id);
		Object[] params = new Object[] { id };
		EmployeeMapper mapper = new EmployeeMapper();

		List<Employee> listEmployee = this.getJdbcTemplate().query(sql, mapper, params);
		return listEmployee;
	}
	
	public List<Employee> findAll() {
		
		String sql = "select * from public.test_employee order by index";
		System.out.println(sql);
		Object[] params = new Object[] { };
		EmployeeMapper mapper = new EmployeeMapper();

		List<Employee> listEmployee = this.getJdbcTemplate().query(sql, mapper, params);
		
		return listEmployee;
	}

	
	public int insert(Employee employee) {
			String sql = "insert into test_employee(index, name, date_of_birth, address, id_dep) values(?,?,?,?,?)";
			System.out.println(sql);
			Object[] params = new Object[] { employee.getIndex(), employee.getName(), employee.getDateOfBirth(),
					employee.getAddress(), employee.getIdDep() };
			return this.getJdbcTemplate().update(sql, params);
	}
	
	public int update(Employee employee) {

		String sql = "update test_employee set name=?, date_of_birth=?, address=?, id_dep=? where index=?";
		System.out.println(sql);
		Object[] params = new Object[] {  employee.getName(), employee.getDateOfBirth(),
				employee.getAddress(), employee.getIdDep(), employee.getIndex()};
		return this.getJdbcTemplate().update(sql, params);
		
	}

	public int delete(Integer id) {
		String sql = "delete from test_employee where index = ?";
		System.out.println(sql);
		Object[] params = new Object[] { id };
		return this.getJdbcTemplate().update(sql, params);
	}

}