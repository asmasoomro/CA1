package com.example.dit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "customer")
@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String address;
	private String phoneNumber;
	private double annualSalary;
	
	public Customer(String name, String address, String phoneNumber, double annualSalary) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.annualSalary = annualSalary;
	}
	
	public Customer() {
		
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
	
	public void setAnnualSalar(double annualSalary) {
		this.annualSalary = annualSalary;
	}
	
}
