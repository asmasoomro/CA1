package com.example.dit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "customer")

@NamedQueries({
	@NamedQuery(name="Customer.findAll", query="select o from Customer o"), 
	@NamedQuery(name = "Customer.findByUsername", query = "select o from Customer o where o.name=:name")
})

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String address;
	private String phoneNumber;
	private double annualSalary;
	
	@OneToOne
	private Loan loan;
	
public Customer() {
		
	}
	
	public Customer(String name, String address, String phoneNumber, double annualSalary, Loan loan) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.annualSalary = annualSalary;
		this.loan = loan;
	}
	
	
	@XmlElement
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@XmlElement
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElement
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	@XmlElement
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@XmlElement
	public double getAnnualSalary() {
		return annualSalary;
	}
	
	public void setAnnualSalary(double annualSalary) {
		this.annualSalary = annualSalary;
	}
	
	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}
	
}
