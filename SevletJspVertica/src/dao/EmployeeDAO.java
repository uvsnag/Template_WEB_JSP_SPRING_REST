package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Employee;
import connection.ConnectionFactory;

public class EmployeeDAO {
	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;

	public EmployeeDAO() {

	}

	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}

	public int countById(Integer id, List<String> queries ) {
		int rowCount = 0;
		try {
			String queryString = "select count(*) as count from test_employee where index =?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, id);
			resultSet = ptmt.executeQuery();
			queries.add("<div class= 'sql'>"+queryString +"</div>"+ "index: " + id);
			
			while (resultSet.next()) {
				 rowCount = Integer.parseInt(resultSet.getString("count"));
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
		return rowCount;
	}
	public List<Employee> findById(Integer id, List<String> queries ) {
		List<Employee> listEmployee = new ArrayList<Employee>();
		Employee emp = null;
		try {

			String queryString = "select * from test_employee where index =?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, id);
			resultSet = ptmt.executeQuery();
			queries.add("<div class= 'sql'>"+queryString +"</div>"+ "index: " + id);
			while (resultSet.next()) {
				emp = new Employee(resultSet.getInt("index"), resultSet.getString("name"),
						resultSet.getString("date_of_birth"), resultSet.getString("address"),
						resultSet.getString("id_dep"));
				listEmployee.add(emp);
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

	public List<Employee> findAll( List<String> queries) {
		List<Employee> listEmployee = new ArrayList<Employee>();
		try {
			String queryString = "select * from test_employee order by index";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			resultSet = ptmt.executeQuery();
			queries.add("<div class= 'sql'>"+queryString +"</div>");
			while (resultSet.next()) {
				Employee emp = new Employee(resultSet.getInt("index"), resultSet.getString("name"),
						resultSet.getString("date_of_birth"), resultSet.getString("address"),
						resultSet.getString("id_dep"));
				listEmployee.add(emp);
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

	public void insert(Employee employee, List<String> queries) {
		try {
			String queryString = "insert into test_employee(index, name, date_of_birth, address, id_dep) values(?,?,?,?,?)";

			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, employee.getIndex());
			ptmt.setString(2, employee.getName());
			ptmt.setString(3, employee.getDateOfBirth());
			ptmt.setString(4, employee.getAddress());
			ptmt.setString(5, employee.getIdDep());
			ptmt.executeUpdate();
			queries.add("<div class= 'sql'>"+queryString +"</div>"
					+ "index: " + employee.getIndex()
					+ "<br/>name: " + employee.getName()
					+ "<br/>date_of_birth: " + employee.getDateOfBirth()
					+ "<br/>address: " + employee.getAddress()
					+ "<br/>id_dep: " + employee.getIdDep()
					);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
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

	}

	public void update(Employee employee, List<String> queries) {

		try {
			String queryString = "update test_employee set name=?, date_of_birth=?, address=?, id_dep=? where index=?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, employee.getName());
			ptmt.setString(2, employee.getDateOfBirth());
			ptmt.setString(3, employee.getAddress());
			ptmt.setString(4, employee.getIdDep());
			ptmt.setInt(5, employee.getIndex());
			ptmt.executeUpdate();
			queries.add("<div class= 'sql'>"+queryString +"</div>"
					+ "<br/>index: " + employee.getIndex()
					+ "<br/>name: " + employee.getName()
					+ "<br/>date_of_birth: " + employee.getDateOfBirth()
					+ "<br/>address: " + employee.getAddress()
					+ "<br/>id_dep: " + employee.getIdDep()
					);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			}

			catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void delete(Integer id, List<String> queries) {

		try {
			String queryString = "delete from test_employee where index = ?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, id);
			ptmt.executeUpdate();
			queries.add("<div class= 'sql'>"+queryString +"</div>" + "index: " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
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

	}
}
