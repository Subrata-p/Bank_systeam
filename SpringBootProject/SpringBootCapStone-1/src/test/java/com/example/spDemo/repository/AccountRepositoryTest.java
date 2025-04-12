package com.example.spDemo.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.spDemo.controller.AccountCustomerController;
import com.example.spDemo.model.Account;
import com.example.spDemo.service.AccountCustomerService;

@ExtendWith(SpringExtension.class)
@DataJpaTest
//@SpringBootTest(classes=AccountCustomerController.class)
class AccountRepositoryTest {

	@Autowired
	TestEntityManager entityManager;
	
	@Autowired
	AccountRepository accountRepo;
	@Test
	void testfindAccountByAmount() {
		//fail("Not yet implemented");
		Account a1=new Account(201,"Saving",3000);
		entityManager.persist(a1);
		List<Account> al=accountRepo.findAccountByAmount(3000);
		assertEquals(3000,al.get(0).getAmount());
	}
    @Test
    void testfindAccountByAccountType() {
    	//fail("Not yet implemented");
    			Account a1=new Account(201,"Saving",3000);
    			entityManager.persist(a1);
    			List<Account> al=accountRepo.findAccountByAccountType("Saving");
    			assertEquals("Saving",al.get(0).getAccountType());
    	
    }
}
