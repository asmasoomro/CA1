package com.example.dit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.example.dit.model.Customer;

@Path("/sampleservice")
public class SampleService {
	
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
    @Path("/customers")
    @Produces("application/xml")
    public List<Customer> listCustomers(){
        return new ArrayList<Customer>(customers.values());
    }
	
	@GET
    @Path("/customer/{customername}")
    @Produces("application/xml")
    public Customer getCustomer(@PathParam("name")String name){
		return customers.get(name);		
    }
	
	@POST
	@Path("/createxml")
    @Consumes("application/xml")
    public String addCustomer(Customer customer) {
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
	

}

