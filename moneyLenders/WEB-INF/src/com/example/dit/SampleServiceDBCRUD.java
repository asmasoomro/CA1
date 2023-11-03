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

import com.example.dit.model.Customer;
import com.example.dit.model.Deposit;
import com.example.dit.model.Loan;



@Path("/sampleserviceDBCRUD")
public class SampleServiceDBCRUD {
	
	private static Map<String, Customer> customers = new HashMap<String, Customer>();
	
	static {
		
		Customer customer1 = new Customer();
        customer1.setName("Kamila");
        customer1.setAddress("45 huntstown way");
        customer1.setPhoneNumber("0897865567");
        customer1.setAnnualSalary(30000.30);
        customers.put(customer1.getName(), customer1);
        
        Customer customer2 = new Customer();
        customer2.setName("Tommy");
        customer2.setAddress("28 Allendale Park");
        customer2.setPhoneNumber("0863452234");
        customer2.setAnnualSalary(45250.50);
        customers.put(customer2.getName(), customer2);
        
    }
	
	
private static Map<String, Deposit> deposits = new HashMap<String, Deposit>();
	
	static {
		
		Deposit deposit1 = new Deposit();
        deposit1.setDate("12/09/2022");
        deposit1.setAmount(209);
        deposits.put(deposit1.getDate(), deposit1);
        
        Deposit deposit2 = new Deposit();
        deposit2.setDate("15/02/2020");
        deposit2.setAmount(50.50);
        deposits.put(deposit2.getDate(), deposit2);
        
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
    @Path("/customers")
    @Produces("application/xml")
    public List<Customer> listCustomers(){
        return new ArrayList<Customer>(customers.values());
    }
	
	@GET
    @Path("/customer/{name}")
    @Produces("application/xml")
    public Customer getCustomer(@PathParam("name")String name){
		return customers.get(name);		
    }
	
	@POST
	@Path("/createxml")
    @Consumes("application/xml")
    public String addCustomer(Customer customer){
		
		return "Customer added " +customer.getName();		
    }
	
	@POST
	@Path("/createjson")
    @Consumes("application/json")
    public String addJSONCustomer(Customer customer){
		return "Customer added " +customer.getName();		
    }
	
	@GET
    @Path("/json/customers/")
    @Produces("application/json")
    public List<Customer> listCustomersJSON(){
		return new ArrayList<Customer>(customers.values());
    }

	@GET
    @Path("/json/customer/{name}")
    @Produces("application/json")
    public Customer getCustomerJSON(@PathParam("name")String name){
		return customers.get(name);		
    }
	
	@GET
    @Path("/customersxmlfromdb")
    @Produces("application/xml")
    public List<Customer> getCustomersFromDB(){
        CustomerDAO dao = new CustomerDAO();
        return dao.getAllCustomers();
    }
	
	@GET
    @Path("/customersjsonfromdb")
    @Produces("application/json")
    public List<Customer> getJSONCustomersFromDB(){
        CustomerDAO dao = new CustomerDAO();
        return dao.getAllCustomers();
    }
	
	@GET
    @Path("/jsonDB/customer/{name}")
    @Produces("application/json")
    public Customer getCustomerByNameFromDBJSON(@PathParam("name")String name){
		CustomerDAO dao = new CustomerDAO();
		return dao.getCustomerByName(name);		
    }
	
	@GET
    @Path("/customerfromDBXML/{name}")
    @Produces("application/xml")
    public Customer getCustomerByNameFromDBXML(@PathParam("name")String name){
		CustomerDAO dao = new CustomerDAO();
		return dao.getCustomerByName(name);	
    }
	
	@POST
	@Path("/newCustomer")
    @Consumes("application/json")
    public String addCustomerToDBJSON(Customer customer){
		CustomerDAO dao = new CustomerDAO();
		dao.persist(customer);
		return "Customer added to DB from JSON Param "+customer.getName();	
    }
	
	@PUT
    @Path("/updateCustomer/")
    @Produces("application/json")
    public Customer updateCustomer(Customer customer){
		CustomerDAO dao = new CustomerDAO();
		return dao.merge(customer);	
    }
	
	@DELETE
    @Path("/deleteCustomer/{name}")
    @Produces("text/plain")
    public String deleteCustomer(@PathParam("name")String name){
		CustomerDAO dao = new CustomerDAO();
		Customer cus = dao.getCustomerByName(name);
		dao.removeCustomer(cus);	
		return "Customer "+cus+" deleted";
    }
	
	@POST
	@Path("/newDeposit")
    @Consumes("application/json")
    public String addDepositToDBJSON(Deposit deposit){
		DepositDAO dao = new DepositDAO();
		dao.persist(deposit);
		return "Deposit added to DB from JSON Param "+deposit.getAmount();	
    }
	
	@PUT
    @Path("/updateDeposit/")
    @Produces("application/json")
    public Deposit updateDeposit(Deposit deposit){
		DepositDAO dao = new DepositDAO();
		return dao.merge(deposit);	
    }
	
	@DELETE
    @Path("/deleteDeposit/{id}")
    @Produces("text/plain")
    public String deleteDeposit(@PathParam("id")int id){
		DepositDAO dao = new DepositDAO();
		Deposit dep = dao.getDepositById(id);
		dao.removeDeposit(dep);	
		return "Deposit "+dep+" deleted";
    }
	
	@POST
	@Path("/addLoan")
    @Consumes("application/json")
    public String addLoanToDBJSON(Loan loan){
		LoanDAO dao = new LoanDAO();
		dao.persist(loan);
		return "Loan added to DB from JSON Param " + loan.getDescription() + loan.getLoanAmount();	
    }
	
	@PUT
    @Path("/updateLoan/")
    @Produces("application/json")
    public Loan updateLoan(Loan loan){
		LoanDAO dao = new LoanDAO();
		return dao.merge(loan);	
    }
	
    @DELETE 
    @Path("/deleteLoan/{id}")
    @Produces("text/plain")
    public String deleteLoan(@PathParam("id")int id) {
    	LoanDAO dao = new LoanDAO();
    	Loan lo = dao.getLoanById(id);
    	dao.removeLoan(lo);
    	return "Loan " +lo+ "deleted";
    }
	
	
}

