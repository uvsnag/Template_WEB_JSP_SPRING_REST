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

import bean.Employee;
import dao.EmployeeDAO;
 
 
@WebServlet()
public class EmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static EmployeeDAO empDAO = new EmployeeDAO();
    
    public EmployeeServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	List<String> queries =new ArrayList<String>();
    	String type = request.getParameter("type-update");
    	
    	if(type != null && "get-update".equals(type)) {
    		Integer id = Integer. parseInt(request.getParameter("index"));
    		Employee emp = empDAO.findById(id, queries).get(0);
    		request.setAttribute("empInfo", emp);
    	}
        List<Employee> list = empDAO.findAll(queries);
        request.setAttribute("empLists", list);
        request.setAttribute("queryGets", queries);
         
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/EmployeeInfo.jsp");
        dispatcher.forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String type = request.getParameter("type-update");
    	List<String> queries =new ArrayList<String>();
    	
    	Employee emp = new Employee();
    	Integer id = Integer. parseInt(request.getParameter("index"));
    	String name = request.getParameter("name");
    	String dateOfBirth = request.getParameter("date_of_birth");
    	String address = request.getParameter("address");
    	String idDep = request.getParameter("idDep");
    	
    	emp.setIndex(id);
    	emp.setName(name);
    	emp.setDateOfBirth(dateOfBirth);
    	emp.setAddress(address);
    	emp.setIdDep(idDep);
		if (id != null) {
			if(type != null && "update".equals(type)) {
				int rowCount =  empDAO.countById(id, queries);
				if(rowCount >0) {
					empDAO.update(emp, queries);
				}else {
					empDAO.insert(emp, queries);
				}
			}else if("delete".equals(type)) {
				empDAO.delete(id, queries);
			}
		}
		 request.setAttribute("queryPosts", queries);
        doGet(request, response);
    }
 
}
