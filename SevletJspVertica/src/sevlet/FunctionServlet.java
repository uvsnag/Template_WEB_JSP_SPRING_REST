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

import dao.SQLAnalyticsDAO;
 
 
@WebServlet()
public class FunctionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static SQLAnalyticsDAO sqlAnalyticsDAO = new SQLAnalyticsDAO();
    
    public FunctionServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		List<String> queries = new ArrayList<String>();
		String input = request.getParameter("input");
		if(input!= null) {
			String result = sqlAnalyticsDAO.callSqLFunction(Integer.parseInt(input), queries);
			request.setAttribute("input", result);
		}
		request.setAttribute("queryGets", queries);

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/Function.jsp");
        dispatcher.forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}
