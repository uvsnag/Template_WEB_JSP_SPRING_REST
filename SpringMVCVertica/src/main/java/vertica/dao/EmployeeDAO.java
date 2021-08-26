package vertica.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vertica.mapper.EmployeeMapper;
import vertica.model.Employee;
 
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

		Integer rowCount = this.getJdbcTemplate().queryForObject(sql, params, Integer.class);
		if (rowCount == null) {
			return 0;
		}
		return rowCount;
	}
 
	public List<Employee> findById(Integer id, List<String> queries) {
		String sql = "select * from test_employee where index =?";
		Object[] params = new Object[] { id };
		EmployeeMapper mapper = new EmployeeMapper();

		List<Employee> listEmployee = this.getJdbcTemplate().query(sql, params, mapper);
		return listEmployee;
	}
	
	public List<Employee> findAll( List<String> queries) {
		
		String sql = "select * from test_employee order by index";
		Object[] params = new Object[] { };
		EmployeeMapper mapper = new EmployeeMapper();

		List<Employee> listEmployee = this.getJdbcTemplate().query(sql, params, mapper);
		return listEmployee;
	}

	
	public void insert(Employee employee, List<String> queries) {
			String sql = "insert into test_employee(index, name, date_of_birth, address, id_dep) values(?,?,?,?,?)";

			Object[] params = new Object[] { employee.getIndex(), employee.getName(), employee.getDateOfBirth(),
					employee.getAddress(), employee.getIdDep() };
			this.getJdbcTemplate().update(sql, params);
	}

    ///////////////////////////////////////
//    private int getMaxDeptId() {
//        String sql = "Select max(d.dept_id) from Employee d";
// 
//        Integer value = this.getJdbcTemplate().queryForObject(sql, Integer.class);
//        if (value == null) {
//            return 0;
//        }
//        return value;
//    }
// 
//    public Employee findEmployee(String deptNo) {
//        String sql = EmployeeMapper.BASE_SQL //
//                + " where d.dept_no = ?";
// 
//        Object[] params = new Object[] { deptNo };
//         
//        EmployeeMapper mapper = new EmployeeMapper();
// 
//        Employee dept = this.getJdbcTemplate().queryForObject(sql, params, mapper);
//        return dept;
//    }
// 
//    public List<Employee> listEmployee() {
//        String sql = EmployeeMapper.BASE_SQL;
// 
//        Object[] params = new Object[] {};
//        EmployeeMapper mapper = new EmployeeMapper();
// 
//        List<Employee> list = this.getJdbcTemplate().query(sql, params, mapper);
//        return list;
//    }
 
//    public void insertEmployee(String deptName, String location) {
//        String sql = "Insert into Employee (dept_id,dept_no,dept_name,location) "//
//                + " values (?,?,?,?) ";
//        int deptId = this.getMaxDeptId() + 1;
//        String deptNo = "D" + deptId;
//        Object[] params = new Object[] { deptId, deptNo, deptName, location };
//        this.getJdbcTemplate().update(sql, params);
//    }
 
}