package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.AnalyticNumEmployee;
import connection.ConnectionFactory;

public class SQLAnalyticsDAO {
	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;

	public SQLAnalyticsDAO() {

	}

	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}

	public List<AnalyticNumEmployee> analyticContEmployee(List<String> queries) {
		List<AnalyticNumEmployee> listAnalNumEmp = new ArrayList<AnalyticNumEmployee>();
		try {
			String queryString = "select "
					+ "	distinct(d.index), "
					+ "	d.name, "
					+ "	count(e.index)  "
					+ "	over(partition by d.name  "
					+ "	order by d.index rows  "
					+ "	between unbounded preceding and unbounded following) as count "
					+ "from "
					+ "	public.test_employee e "
					+ "right join store.test_department d on "
					+ "	e.id_dep = d.index";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			resultSet = ptmt.executeQuery();
			queries.add("<div class= 'sql'>"+queryString +"</div>");
			while (resultSet.next()) {
				AnalyticNumEmployee analNumEmp = new AnalyticNumEmployee(resultSet.getInt("index"), resultSet.getString("name"),
						resultSet.getInt("count"));
				listAnalNumEmp.add(analNumEmp);
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
		return listAnalNumEmp;
	}
	
	public String callSqLFunction(Integer id, List<String> queries) {
		String result = "";
			try {
				String queryString = " select myzeroifnull(?);";
				connection = getConnection();
				ptmt = connection.prepareStatement(queryString);
				ptmt.setInt(1, id);
				resultSet = ptmt.executeQuery();
				queries.add("<div class= 'sql'>"+queryString +"</div>"+ "input: " + id);
				while (resultSet.next()) {
					result = resultSet.getString("myzeroifnull");
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
			return result;
		}
}
