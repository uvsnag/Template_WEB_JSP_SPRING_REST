package sevlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.EmployeeMulti;
import dao.EmployeeMultiQueryDAO;
 
 
@WebServlet()
public class EmployeeMultiServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static EmployeeMultiQueryDAO empMultiDAO = new EmployeeMultiQueryDAO();
    
    public EmployeeMultiServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	List<String> queries =new ArrayList<String>();
    	List<EmployeeMulti> list =new ArrayList<EmployeeMulti>();
    	String depId = request.getParameter("deparId");
    	String queryOn = request.getParameter("queryOn");
    	
		if (depId != null && queryOn != null) {
			switch(queryOn) {
			case "table": 
				list = empMultiDAO.findByDeptIdWJoinTable(Integer. parseInt(depId), queries);
				break;
			case "flatten":
				list = empMultiDAO.findByDeptIdWFlatten(Integer. parseInt(depId), queries);
				break;
			case "projection":
				list = empMultiDAO.findByDeptIdWProjection(Integer. parseInt(depId), queries);
				break;
			}
				
    		request.setAttribute("empLists", list);
    		request.setAttribute("queryGets", queries);
    		request.setAttribute("depId", depId);
    		request.setAttribute("queryfrom", queryOn);
    	}
         
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/MultiInfo.jsp");
        dispatcher.forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}
