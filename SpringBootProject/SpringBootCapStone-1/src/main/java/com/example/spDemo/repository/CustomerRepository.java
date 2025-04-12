package com.example.spDemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.spDemo.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer>{
	
	//Customer getCustomerByCustomer_city(String cust_city);
	List<Customer> findCustomerByCustomerCity(String cusCity);
	List<Customer> findCustomerByCustomerName(String cusName);
    //Customer findCustomerByAmount(int amount);
}
