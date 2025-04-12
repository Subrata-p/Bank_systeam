package com.example.spDemo.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.example.spDemo.model.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CustomerRepositoryTest {
	
	@Autowired
	TestEntityManager entityManger;
	
	@Autowired
	CustomerRepository cusRepo;

	@Test
	void testfindCustomerByCustomerCity() {
		List<Customer> cl=new ArrayList<Customer>();
		List<Account>  al=new ArrayList<Account>();
		//List<Account>  al1=new ArrayList<Account>();
		Customer c1=new Customer(101,"Narendra","Delhi");
		Account a1=new Account(200,"Saving",6000);
		Account a2=new Account(201,"Current",8000);
		al.add(a1);
		al.add(a2);
		c1.setAcc(al);
		
		cl.add(c1);
		//cl.add(c2);
		entityManger.persist(c1);
		List<Customer> ctl=cusRepo.findCustomerByCustomerCity("Delhi");
		assertEquals(1,ctl.size());
		
	}
	
	@Test
	void testfindCustomerByCustomerName() {
		Customer c1=new Customer(101,"Narendra","Delhi");
		List<Account> al=new ArrayList<Account>();
		Account a1=new Account(200,"Saving",6000);
		Account a2=new Account(201,"Current",8000);
		al.add(a1);
		al.add(a2);
		c1.setAcc(al);
		entityManger.persist(c1);
		List<Customer> cttl=cusRepo.findCustomerByCustomerName("Narendra");
		assertEquals(1,cttl.size());
	}

}
