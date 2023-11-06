package main;

import java.util.ArrayList;
import java.util.List;

import dao.CustomerDAO;
import dao.DepositDAO;
import dao.LoanDAO;
import entities.Customer;
import entities.Deposit;
import entities.Loan;

public class Test2 {
	
	
	public Test2() {
		CustomerDAO cDAO = new CustomerDAO();
		DepositDAO dDAO = new DepositDAO();
		LoanDAO lDAO = new LoanDAO();
		
		//Add comments
		Deposit d1 = new Deposit("22/05/2018", 1000.00);
		Deposit d2 = new Deposit("25/09/2019", 1500.00);
	    Deposit d3 = new Deposit("27/08/2020", 3000.00);
	//	Deposit d4 = new Deposit("18/10/2020", 25.50);
		dDAO.persist(d1);
		dDAO.persist(d2);
	    dDAO.persist(d3);
	//    dDAO.persist(d4);
		
		List<Deposit> deposits = new ArrayList<Deposit>();
		deposits.add(d1);
		deposits.add(d2);
	 	deposits.add(d3);
	//	deposits.add(d4);
		//Add Profile
		Loan loan = new Loan("Taking out loan for Business", 100000.00, deposits);
		lDAO.persist(loan);
		
		//Add Subscriber
		Customer customer = new Customer("Niall","88 The Grove", "0856789908", 50000.00, loan );
		cDAO.persist(customer);
		
		//View all subscribers (here I've accessed all objects through the subscriber)
		ArrayList<Customer> customers = (ArrayList<Customer>) cDAO.getAllCustomers();
		for(Customer c : customers) {
			System.out.println("Customers name is "+c.getName());
			System.out.println("Loan description "+ c.getLoan().getDescription() + " and the amount taken out is " + c.getLoan().getLoanAmount());
			//Note I've made an Eagar Fetch on the Comments List in Profile to enable this
			System.out.println("Customers first deposit was on "+c.getLoan().getDeposits().get(0).getDate() + " amount was " +c.getLoan().getDeposits().get(0).getAmount());
			System.out.println("Customers second deposit was on "+c.getLoan().getDeposits().get(1).getDate() + " amount was " +c.getLoan().getDeposits().get(1).getAmount());
		//	System.out.println("Customers third deposit was on "+c.getLoan().getDeposits().get(2).getDate() + " amount was " +c.getLoan().getDeposits().get(2).getAmount());
		}
		
		//Update username using merge
	//	customer.setName("Tom");
	//	cDAO.merge(customer);	
		
		//remove the last deposit
		//dDAO.remove(d3);
		
		//Get subscriber by username, print their password
		//System.out.println(sDAO.getSubscriberByUsername("STEVO").getPassword());
		
	}
	
	public static void main(String[] args) {
		new Test2();
	}

}
