package vertica.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
 
import org.springframework.jdbc.core.RowMapper;

import vertica.model.Employee;
 
public class EmployeeMapper implements RowMapper<Employee> {
 
    public static final String BASE_SQL = //
            "Select e.index, e.name, e.date_of_birth, e.address, e.id_dep "//
                    + " from public.test_employee e ";
 
//    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Integer index = rs.getInt("index");
        String name = rs.getString("name");
        String dateOfBirth = rs.getString("date_of_birth");
        String address = rs.getString("address");
        String idDep = rs.getString("id_dep");
 
        return new Employee(index, name, dateOfBirth, address, idDep);
    }
 
}
