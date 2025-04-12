package com.example.spDemo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.spDemo.repository.*;
import com.example.spDemo.exception.ResourceNotFound;
import com.example.spDemo.model.*;

//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class AccountCustomerServiceTest {
    @InjectMocks
    AccountCustomerService acService;
    
    @Mock
    CustomerRepository customerRepository;
    
    @Mock
    AccountRepository accountRepository;
    
	@Test
	public void getAllCustomerTest() {
		//fail("Not yet implemented");
		
		List<Customer> cl=new ArrayList<Customer>();
		List<Account>  al=new ArrayList<Account>();
		List<Account>  al1=new ArrayList<Account>();
		Customer c1=new Customer(100,"Narendra Modi","Delhi");
		Account a1=new Account(200,"Saving",6000);
		Account a2=new Account(201,"Current",8000);
		al.add(a1);
		al.add(a2);
		c1.setAcc(al);
		//al.clear();
		Customer c2=new Customer(101,"Amit Shah","Ahmedabad");
		Account a3=new Account(300,"Saving",9000);
		Account a4=new Account(301,"Current",3000);
		al1.add(a3);
		al1.add(a4);
		c2.setAcc(al1);
		cl.add(c1);
		cl.add(c2);
		when(customerRepository.findAll()).thenReturn(cl);
		List<Customer> cl1=acService.getAllCustomer();
		assertEquals(2,cl1.size());
		
	}
	
	@Test
	public void getCustomerIdTest() throws ResourceNotFound {
		Optional<Customer> c1=Optional.of(new Customer(102,"rakesh","delhi"));
		Account a1=new Account(200,"Saving",20000);
		Account a2=new Account(201,"Current",232000);
		List<Account> al=new ArrayList<Account>();
		al.add(a1);
		al.add(a2);
		c1.get().setAcc(al);
		//customerRepository.save(c1);
		when(customerRepository.findById(c1.get().getCustomerId())).thenReturn(c1);
		Customer c2=acService.getCustomerId(102);
		//System.out.println(c2);
		assertEquals(102,c2.getCustomerId());
	}
	@Test
	public void getAccountTest() throws ResourceNotFound {
		Optional<Account> a1=Optional.of(new Account(200,"Saving",3000));
		when(accountRepository.findById(a1.get().getAccountId())).thenReturn(a1);
		Account a2=acService.getAccount(200);
		assertEquals(3000,a2.getAmount());
	}
	@Test
	public void insertCustomerTest() {
		Customer c1=new Customer(101,"Kamlesh","Jaipur");
		Account a1=new Account(200,"Saving",20000);
		Account a2=new Account(201,"Current",232000);
		List<Account> al=new ArrayList<Account>();
		al.add(a1);
		al.add(a2);
		c1.setAcc(al);
		customerRepository.save(c1);
		verify(customerRepository,times(1)).save(c1);
	}
	@Test
	public void deleteCustomerTest() {
		Customer c1=new Customer(101,"Kamlesh","Jaipur");
		Account a1=new Account(200,"Saving",20000);
		Account a2=new Account(201,"Current",232000);
		List<Account> al=new ArrayList<Account>();
		al.add(a1);
		al.add(a2);
		c1.setAcc(al);
		customerRepository.deleteById(101);
		customerRepository.deleteById(101);
		verify(customerRepository,times(2)).deleteById(101);
	}
	@Test
	public void getAllAccountDetailsTest() {
		List<Account> al=new ArrayList<Account>();
		Account a1=new Account(200,"Saving",20000);
		Account a2=new Account(201,"Current",232000);
		al.add(a1);
		al.add(a2);
		when(accountRepository.findAll()).thenReturn(al);
		List<Account> al1=acService.getAllAccountDetails();
		assertEquals(2,al.size());
	}
	
	@Test
	public void getAccountByStypeTest() {
		List<Account> al=new ArrayList<Account>();
		Account a1=new Account(200,"Saving",20000);
		Account a2=new Account(201,"Current",232000);
		al.add(a1);
		al.add(a2);
		List<Account> al1=new ArrayList<Account>();
		al1.add(a1);
		when(accountRepository.findAccountByAccountType("Saving")).thenReturn(al1);
		List<Account> adl=acService.getAccountByStype("Saving");
		assertEquals(1,adl.size());
	}
	@Test
	public void insertAccountTest() {
		//List<Account> al=new ArrayList<>();
		Account a1=new Account(200,"Saving",20000);
		Account a2=new Account(201,"Current",30000);
		Customer c1=new Customer(101,"Narendra","Delhi");
		Customer c2=new Customer(102,"Amit","Delhi");
		a1.setCustomer(c1);
		a2.setCustomer(c2);
		//al.add(a1);
		//al.add(a2);
		accountRepository.save(a1);
		accountRepository.save(a2);
		accountRepository.save(a2);
		//accountRepository.saveAll(al);
		//accountRepository.saveAll(al);
		verify(accountRepository,times(2)).save(a2);
	}
	@Test
	public void deleteAccountTest() {
		Account a1=new Account(200,"Saving",20000);
		Customer c1=new Customer(101,"Narendra","Delhi");
		a1.setCustomer(c1);
		accountRepository.deleteById(200);
		verify(accountRepository,times(1)).deleteById(200);
	}
	@Test
	public void getAccountBalanceTest() throws ResourceNotFound {
		Optional<Account> a1=Optional.of(new Account(200,"Saving",3000));
		when(accountRepository.findById(a1.get().getAccountId())).thenReturn(a1);
		Account a2=acService.getAccount(200);
		int bal=acService.getAccountBalance(a2.getAccountId());
		assertEquals(3000,bal);
	}
    @Test
    public void getCustAccTest() throws ResourceNotFound {
    	Optional<Account> a1=Optional.of(new Account(200,"Saving",3000));
    	Optional<Customer> c1=Optional.of(new Customer(101,"Narendra","Delhi"));
    	a1.get().setCustomer(c1.get());
    	when(accountRepository.findById(a1.get().getAccountId())).thenReturn(a1);
    	Account a2=acService.getAccount(200);
    	String name=acService.getCustAcc(a2.getAccountId());
    	assertEquals("Narendra",name);
    	
    }
}
