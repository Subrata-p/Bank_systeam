package com.example.spDemo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spDemo.exception.AccountDoesNotExist;
import com.example.spDemo.exception.AmountInsufficientException;
import com.example.spDemo.exception.CustomerDoesNotExist;
//import com.example.spDemo.exception.CustomerDoesNotExist1;
import com.example.spDemo.exception.ResourceNotFound;
import com.example.spDemo.model.Account;
import com.example.spDemo.model.Customer;
import com.example.spDemo.repository.AccountRepository;
import com.example.spDemo.repository.CustomerRepository;

@Service
public class AccountCustomerService {
	
	int c1,c2;
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	public Customer insertCustomer(Customer customer) {
		List<Account> al=customer.getAcc();
		List<Account> nal=new ArrayList<Account>();
		for(int i=0;i<al.size();i++) {
			//System.out.println(al.get(i));
			//al.get(i).setCustomer(customer);
			nal.add(al.get(i));
		}
		customer.setAcc(nal);
		return customerRepository.save(customer);
	}
	public Account insertAccount(Account account) {
		//account.setCustomer();
		return accountRepository.save(account);
	}
	public Customer getCustomerId(int id) throws ResourceNotFound {
		
		Optional<Customer> c1=customerRepository.findById(id);
		if(c1.isPresent()) {
			return customerRepository.findById(id).get();
		}
		else {
			throw new ResourceNotFound("Customer Data is not in database");
		}
		//System.out.println(c11);
		
	}
	public Account getAccount(int id) throws ResourceNotFound {
		Optional<Account> a1=accountRepository.findById(id);
		if(a1.isPresent()) {
			return accountRepository.findById(id).get();
		}
		else {
			throw new ResourceNotFound("Account Data is not in database");
		}
	}
	public List<Customer> getAllCustomer(){
		return customerRepository.findAll();
	}
	public String deleteCustomer(int id) throws ResourceNotFound{
		Optional<Customer> c1=customerRepository.findById(id);
		if(c1.isPresent()) {
			customerRepository.deleteById(id);
			return "Customer Data Deleted";
		}
		else {
			throw new ResourceNotFound("Customer is Not Found");
		}
}
		
    public void updateCustomer(Customer customer,int cid) {
    	List<Customer> cl=customerRepository.findAll();
    	for(int i=0;i<cl.size();i++) {
    		if(cl.get(i).getCustomerId()==cid) {
    			cl.get(i).setCustomerName(customer.getCustomerName());
    			cl.get(i).setCustomerCity(customer.getCustomerCity());
    			customerRepository.save(cl.get(i));
    		}
    	}
    	//return customerRepository.save(customer);
    }
    public void updateAccountDetail(Account acc,int accno) {
    	List<Account> al=accountRepository.findAll();
    	for(int i=0;i<al.size();i++) {
    		if(al.get(i).getAccountId()==accno) {
    			al.get(i).setAccountType(acc.getAccountType());
    			al.get(i).setAmount(acc.getAmount());
    			accountRepository.save(al.get(i));
    		}
    	}
    }
    public List<Customer> getCustomerByName(String name) {
    	return customerRepository.findCustomerByCustomerName(name);
    }
    public List<Customer> getCustomerByCity(String city){
    	return customerRepository.findCustomerByCustomerCity(city);
    }
    public List<Account> getAllAccountDetails(){
    	return accountRepository.findAll();
    }
    public String deleteAllAccount() throws AccountDoesNotExist {
    	List<Account> acc=accountRepository.findAll();
    	if(acc.size()!=0) {
    		accountRepository.deleteAll();
    		return "deleted";
    	}
    	else {
    		throw new AccountDoesNotExist("No One Account is available");
    	}
    }
   /* public Customer updateCustomerById(int id) {
    	return customerRepository.saveOrU
    }*/
    public String deleteAllCustomer() throws CustomerDoesNotExist {
    	List<Customer> cus=customerRepository.findAll();
    	if(cus.size()!=0) {
    		customerRepository.deleteAll();
    		return "deleted";
    	}
    	else {
    		throw new CustomerDoesNotExist("No One Customer is availble");
    	}
    	
    }
    public List<Account> getAccountByAmount(int amnt) {
    	List<Account> al=accountRepository.findAll();
    	List<Account> al1=new ArrayList<Account>();
    	for(int i=0;i<al.size();i++) {
    	     if(al.get(i).getAmount()==amnt) {
    	    	 al1.add(al.get(i));
    	     }
    	}
    	return al1;
    }
    public List<Account> getAccountByStype(String st) {
    	/*List<Account> al=accountRepository.findAll();
    	List<Account> al1=new ArrayList<Account>();
    	for(int i=0;i<al.size();i++) {
    		if(al.get(i).getAccountType().equals(st)) {
    			al1.add(al.get(i));
    		}
    	}
    	System.out.println(al1);*/
    	return accountRepository.findAccountByAccountType(st);
    }
    public void amountTransfer(int fid,int tid,int amount) throws AmountInsufficientException {
    	Account a1=accountRepository.findById(fid).get();
    	Account a2=accountRepository.findById(tid).get();
    	int fa=a1.getAmount();
    	int ta=a2.getAmount();
    	System.out.println(fa);
    	System.out.println(ta);
    	if(fa>amount) {
    		 c1=fa-amount;
    		 System.out.println(c1);
    		 c2=ta+amount;
    		 System.out.println(c2);
    	}
    	else {
    		throw new AmountInsufficientException("Amount is Insufficient");
    	}
    	a1.setAmount(c1);
    	a2.setAmount(c2);
    	accountRepository.save(a1);
    	accountRepository.save(a2);
		//return "Transfered";
    	
    }
    public int getAccountBalance(int accNum) throws ResourceNotFound {
    	Optional<Account> a1=accountRepository.findById(accNum);
    	if(a1.isPresent()) {
    		int st=a1.get().getAmount();
    		return st;
    	}
    	else {
    		throw new ResourceNotFound("Account is not found");
    	}
    	
    }
    public String deleteAcc(int id) throws ResourceNotFound {
    	Optional<Account> ac1=accountRepository.findById(id);
    	if(ac1.isPresent()) {
    		accountRepository.deleteById(id);
        	return "Account Data deleted";
    	}
    	else {
    		throw new ResourceNotFound("Account is not found");
    	}
    	
    }
    public String getCustAcc(int acc) {
    	Optional<Account> acc1=accountRepository.findById(acc);
    	return acc1.get().getCustomer().getCustomerName();
    }
}
