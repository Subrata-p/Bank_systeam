package com.example.spDemo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import com.example.spDemo.model.Customer;

@Entity
public class Account {
	@Id
	private int accountId;
	private String accountType;
	private int amount;
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="customerId")
	private Customer customer;
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account(int accountId, String accountType, int amount) {
		super();
		this.accountId = accountId;
		this.accountType = accountType;
		this.amount = amount;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	
	

}
