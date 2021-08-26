package com.vertica.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.vertica.spring.dao.EmployeeDAO;
import com.vertica.spring.model.Employee;

 
@Controller
public class EmployeeController {
 
    @Autowired
    private EmployeeDAO employeeDAO;
 
    @RequestMapping(value = { "/info" }, method = RequestMethod.GET)
	public String simpleGet(Model model
//			, @RequestParam("type-update") String type,
//			@RequestParam("index") Integer id
			) {

		List<String> queries = new ArrayList<String>();
//		if (type != null && "get-update".equals(type)) {
//			Employee emp = employeeDAO.findById(id, queries).get(0);
//			model.addAttribute("empInfo", emp);
//		}
		List<Employee> list = employeeDAO.findAll(queries);
		model.addAttribute("empLists", list);
		model.addAttribute("queryGets", queries);

		return "employeeInfo";
	}
    @RequestMapping(value = { "/info" }, method = RequestMethod.POST)
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
        return "employeeInfo";
    }
     
}