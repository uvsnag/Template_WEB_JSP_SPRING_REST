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
    
	public Integer countById(Integer id, List<String> queries) {
		String sql = "select count(*) as count from test_employee where index =?";
		queries.add("<div class= 'sql'>" + sql + "</div>" + "index: " + id);
		Object[] params = new Object[] { id };

		Integer rowCount = this.getJdbcTemplate().queryForObject(sql, Integer.class,  params );
		if (rowCount == null) {
			return 0;
		}
		return rowCount;
	}
 
	public List<Employee> findById(Integer id, List<String> queries) {
		String sql = "select * from test_employee where index =?";
		Object[] params = new Object[] { id };
		EmployeeMapper mapper = new EmployeeMapper();

		List<Employee> listEmployee = this.getJdbcTemplate().query(sql, mapper, params);
		return listEmployee;
	}
	
	public List<Employee> findAll( List<String> queries) {
		
		String sql = "select * from test_employee order by index";
		Object[] params = new Object[] { };
		EmployeeMapper mapper = new EmployeeMapper();

		List<Employee> listEmployee = this.getJdbcTemplate().query(sql, mapper, params);
		
		return listEmployee;
	}

	
	public void insert(Employee employee, List<String> queries) {
			String sql = "insert into test_employee(index, name, date_of_birth, address, id_dep) values(?,?,?,?,?)";

			Object[] params = new Object[] { employee.getIndex(), employee.getName(), employee.getDateOfBirth(),
					employee.getAddress(), employee.getIdDep() };
			this.getJdbcTemplate().update(sql, params);
	}

}