package com.example.dit.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name = "customer")
@Entity
public class Loan {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String description;
	private double loanAmount;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Deposit> deposits = new ArrayList<Deposit>();
	
    public Loan() {
	
    }
	
	public Loan(String description, double loanAmount, List<Deposit> deposits) {
		this.description = description;
		this.loanAmount = loanAmount;
		this.deposits = deposits;
	}
	
	public Loan(String description, double loanAmount) {
		this.description = description;
		this.loanAmount = loanAmount;
	}

	
	@XmlElement
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	@XmlElement
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	@XmlElement
	public double getLoanAmount() {
		return loanAmount;
	}
	
	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}
	
	
	public void addDeposit(Deposit deposit) {
		deposits.add(deposit);
	}
	
	public List<Deposit> getDespoits() {
		return deposits;
	}



	public void setDeposits(List<Deposit> deposits) {
		this.deposits = deposits;
	}

	
}
