package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.EmployeeMulti;
import connection.ConnectionFactory;

public class EmployeeMultiQueryDAO {
	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;

	public EmployeeMultiQueryDAO() {

	}

	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}

	public List<EmployeeMulti> findByDeptIdWJoinTable(Integer id, List<String> queries) {
		List<EmployeeMulti> listEmployee = new ArrayList<EmployeeMulti>();
		try {
			String queryString = "select e.name as empName, e.date_of_birth, e.address, d.name as depName, d.describe  "
					+ "from public.test_employee e "
					+ "inner join store.test_department d "
					+ "on e.id_dep=d.index "
					+ "where d.index = ?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, id);
			resultSet = ptmt.executeQuery();
			queries.add("<div class= 'sql'>"+queryString +"</div>"+ "index: " + id);
			while (resultSet.next()) {
				EmployeeMulti employeeMulti = new EmployeeMulti(resultSet.getString("empName"), resultSet.getString("date_of_birth"),
						resultSet.getString("address"), resultSet.getString("depName"),
						resultSet.getString("describe"));
				listEmployee.add(employeeMulti);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return listEmployee;
	}
	public List<EmployeeMulti> findByDeptIdWFlatten(Integer id, List<String> queries) {
		List<EmployeeMulti> listEmployee = new ArrayList<EmployeeMulti>();
		try {
			String queryString = "select"
					+ "	name,"
					+ "	date_of_birth,"
					+ "	address,"
					+ "	depname,"
					+ "	describe"
					+ " from"
					+ "	public.test_employee_flatten"
					+ " where"
					+ "	id_dep = ?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, id);
			resultSet = ptmt.executeQuery();
			queries.add("<div class= 'sql'>"+queryString +"</div>"+ "index: " + id);
			while (resultSet.next()) {
				EmployeeMulti employeeMulti = new EmployeeMulti(resultSet.getString("name"), resultSet.getString("date_of_birth"),
						resultSet.getString("address"), resultSet.getString("depname"),
						resultSet.getString("describe"));
				listEmployee.add(employeeMulti);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return listEmployee;
	}
	public List<EmployeeMulti> findByDeptIdWProjection(Integer id, List<String> queries) {
		List<EmployeeMulti> listEmployee = new ArrayList<EmployeeMulti>();
		try {
			String queryString = "select "
					+ "	name, "
					+ "	date_of_birth, "
					+ "	address, "
					+ "	depname, "
					+ "	describe "
					+ "from "
					+ "	public.employee_department_projection "
					+ "where "
					+ "	id_dep = ?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, id);
			resultSet = ptmt.executeQuery();
			queries.add("<div class= 'sql'>"+queryString +"</div>"+ "index: " + id);
			while (resultSet.next()) {
				EmployeeMulti employeeMulti = new EmployeeMulti(resultSet.getString("name"), resultSet.getString("date_of_birth"),
						resultSet.getString("address"), resultSet.getString("depname"),
						resultSet.getString("describe"));
				listEmployee.add(employeeMulti);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return listEmployee;
	}
}
