package com.example.spDemo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Customer {
	
	@Id
	private int customerId;
	private String customerName;
	private String customerCity;
	@OneToMany(mappedBy="customer",cascade=CascadeType.ALL)
	@JsonManagedReference
	private List<Account> acc;
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Customer(int customerId, String customerName, String customerCity) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerCity = customerCity;
	}

	public Customer(int customerId, String customerName, String customerCity, List<Account> acc) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerCity = customerCity;
		this.acc = acc;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerCity() {
		return customerCity;
	}
	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}
	public List<Account> getAcc() {
		return acc;
	}
	public void setAcc(List<Account> acc) {
		this.acc = acc;
	}
	
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerCity="
				+ customerCity + ", acc=" + acc + "]";
	}
	

}
