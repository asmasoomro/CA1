package com.example.dit;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.example.dit.model.Employee;


@Path("/sampleserviceDBCRUD")
public class SampleServiceDBCRUD {
	
	private static Map<String, Employee> employees = new HashMap<String, Employee>();
	
	static {
		
        Employee employee1 = new Employee();
        employee1.setEmployeeId("1");
        employee1.setEmployeeName("Fabrizio");
        employee1.setJob("Software Engineer");
        employees.put(employee1.getEmployeeId(), employee1);
        
        Employee employee2 = new Employee();
        employee2.setEmployeeId("2");
        employee2.setEmployeeName("Justin");
        employee2.setJob("Business Analyst");
        employees.put(employee2.getEmployeeId(), employee2);
        
    }

	@GET
    @Path("/hello")
    @Produces("text/plain")
    public String hello(){
        return "Hello World";    
    }
	
	@GET
    @Path("/helloworld")
    @Produces("text/plain")
    public String helloWorld(){
        return "Hello World New";    
    }
	
	@GET
    @Path("/echo/{message}")
    @Produces("text/plain")
    public String echo(@PathParam("message")String message){
        return message;  
    }
	
	@GET
    @Path("/newEcho/{message}")
    @Produces("text/plain")
    public String newEcho(@PathParam("message")String message){
        return message;  
    }

	
	@GET
    @Path("/employees")
    @Produces("application/xml")
    public List<Employee> listEmployees(){
        return new ArrayList<Employee>(employees.values());
    }
	
	@GET
    @Path("/employee/{employeeid}")
    @Produces("application/xml")
    public Employee getEmployee(@PathParam("employeeid")String employeeId){
		return employees.get(employeeId);		
    }
	
	@POST
	@Path("/createxml")
    @Consumes("application/xml")
    public String addEmployee(Employee employee){
		
		return "Employee added " +employee.getEmployeeName();		
    }
	
	@POST
	@Path("/createjson")
    @Consumes("application/json")
    public String addJSONEmployee(Employee employee){
		return "Employee added " +employee.getEmployeeName();		
    }
	
	@GET
    @Path("/json/employees/")
    @Produces("application/json")
    public List<Employee> listEmployeesJSON(){
		return new ArrayList<Employee>(employees.values());
    }

	@GET
    @Path("/json/employee/{employeeid}")
    @Produces("application/json")
    public Employee getEmployeeJSON(@PathParam("employeeid")String employeeId){
		return employees.get(employeeId);		
    }
	
	@GET
    @Path("/employeesxmlfromdb")
    @Produces("application/xml")
    public List<Employee> getEmployeesFromDB(){
        EmployeeDAO dao = new EmployeeDAO();
        return dao.getAllEmployees();
    }
	
	@GET
    @Path("/employeesjsonfromdb")
    @Produces("application/json")
    public List<Employee> getJSONEmployeesFromDB(){
        EmployeeDAO dao = new EmployeeDAO();
        return dao.getAllEmployees();
    }
	
	@GET
    @Path("/jsonDB/employee/{employeeName}")
    @Produces("application/json")
    public Employee getEmployeeByNameFromDBJSON(@PathParam("employeeName")String employeeName){
		EmployeeDAO dao = new EmployeeDAO();
		return dao.getEmployeeByName(employeeName);		
    }
	
	@GET
    @Path("/employeefromDBXML/{employeeName}")
    @Produces("application/xml")
    public Employee getEmployeeByNameFromDBXML(@PathParam("employeeName")String employeeName){
		EmployeeDAO dao = new EmployeeDAO();
		return dao.getEmployeeByName(employeeName);	
    }
	
	@POST
	@Path("/newEmployee")
    @Consumes("application/json")
    public String addEmployeeToDBJSON(Employee employee){
		EmployeeDAO dao = new EmployeeDAO();
		dao.persist(employee);
		return "Employee added to DB from JSON Param "+employee.getEmployeeName();	
    }
	
	@PUT
    @Path("/updateEmployee/")
    @Produces("application/json")
    public Employee updateEmployee(Employee employee){
		EmployeeDAO dao = new EmployeeDAO();
		return dao.merge(employee);	
    }
	
	@DELETE
    @Path("/deleteEmployee/{employeeName}")
    @Produces("text/plain")
    public String deleteEmployee(@PathParam("employeeName")String employeeName){
		EmployeeDAO dao = new EmployeeDAO();
		Employee emp = dao.getEmployeeByName(employeeName);
		dao.removeEmployee(emp);	
		return "Employee "+emp+" deleted";
    }
	
	
}

