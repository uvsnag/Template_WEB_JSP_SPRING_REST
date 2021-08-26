package vertica.controller;

import java.util.ArrayList;
import java.util.List;

import vertica.dao.EmployeeDAO;
import vertica.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
 
@Controller
public class MainController {
 
    @Autowired
    private EmployeeDAO employeeDAO;
 
    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String simpleGet(Model model, @RequestParam("type-update") String type,
			@RequestParam("index") Integer id) {

		List<String> queries = new ArrayList<String>();
		if (type != null && "get-update".equals(type)) {
			Employee emp = employeeDAO.findById(id, queries).get(0);
			model.addAttribute("empInfo", emp);
		}
		List<Employee> list = employeeDAO.findAll(queries);
		model.addAttribute("empLists", list);
		model.addAttribute("queryGets", queries);

		return "EmployeeInfo";
	}
    @RequestMapping(value = { "/" }, method = RequestMethod.POST)
    public String simperPost(Model model,  @RequestParam("type-update") String type,
			@RequestParam("index") Integer id) {
    	List<String> queries =new ArrayList<String>();
    	if(type != null && "get-update".equals(type)) {
    		Employee emp = employeeDAO.findById(id, queries).get(0);
    		 model.addAttribute("empInfo", emp);
    	}
        List<Employee> list = employeeDAO.findAll(queries);
        model.addAttribute("empLists", list);
        model.addAttribute("queryGets", queries);
         
        //
//        employeeDAO.insertEmployee("HR", "Chicago");
//        employeeDAO.insertEmployee("INV", "Hanoi");
//        List<Employee> list = employeeDAO.listEmployee();
//        model.addAttribute("Employees", list);
        return "EmployeeInfo";
    }
     
}