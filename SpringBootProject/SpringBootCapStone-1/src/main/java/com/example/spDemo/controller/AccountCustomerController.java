package com.example.spDemo.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spDemo.exception.AccountDoesNotExist;
import com.example.spDemo.exception.AccountNotFoundException;
import com.example.spDemo.exception.AmountInsufficientException;
import com.example.spDemo.exception.CustomerDoesNotExist;
import com.example.spDemo.exception.FromAccountDoesnotExist;
import com.example.spDemo.exception.ProperDataNotAddedException;
//import com.example.spDemo.exception.CustomerNotFoundException;
import com.example.spDemo.exception.ResourceNotFound;
import com.example.spDemo.exception.ToAccountDoesnotExist;
import com.example.spDemo.model.Account;
import com.example.spDemo.model.Customer;
import com.example.spDemo.service.AccountCustomerService;

@RestController
public class AccountCustomerController{
	
	@Autowired
	AccountCustomerService acService;
	
	@PostMapping(value="/addCustomer")
	public ResponseEntity<Object> fun1(@RequestBody Customer customer) throws ProperDataNotAddedException {
		
		if(customer.getCustomerName()==null) {
			throw new ProperDataNotAddedException("Customer Data Not Added");
		}
		 acService.insertCustomer(customer);
		 //logg.warning("You add one Customer");
		 return new ResponseEntity<Object>("Data Inserted Successfully",HttpStatus.CREATED);
	}
	@PostMapping(value="/addAccount")
	public ResponseEntity<Object> addAcc(@RequestBody Account acc1){
		acService.insertAccount(acc1);
		return new ResponseEntity<Object>("Data Inserted Successfully",HttpStatus.CREATED);
	}
	@GetMapping("/getCustomer/{id}")
	public Customer fun2(@PathVariable("id") int id) throws ResourceNotFound {
		Customer c1= acService.getCustomerId(id);
		return c1;
	}
	@GetMapping("/getAccountDet/{accNum}")
	public Account getAccDet(@PathVariable("accNum")int accNum) throws ResourceNotFound {
		return acService.getAccount(accNum);
	}
	@RequestMapping("/getBalance/{accNum}")
	public ResponseEntity<Object> getBalance(@PathVariable("accNum") int accNum) throws  ResourceNotFound{
		/*Account acc1=acService.getAccountBalance(accNum);
		List<Account> accl=acService.getAllAccountDetails();
		List<Integer> ac1=new ArrayList<Integer>();
		for(int i=0;i<accl.size();i++) {
			ac1.add(accl.get(i).getAccountId());
		}
		System.out.println(ac1);
		if(ac1.contains(accNum)==false) {
			throw new AccountDoesNotExist("Account Does not Exist");
		}
		
		int balance=acc1.getAmount();
		return "Balance Of Your Account No: "+accNum+" is "+balance+" Rs.";*/
		int balance=acService.getAccountBalance(accNum);
		return new ResponseEntity<Object>("Balance Of Your Account No: "+accNum+" is "+balance+" Rs.",HttpStatus.OK);
	}
	@GetMapping("/getCustByAcc/{Accno}")
	public String getCustByacc(@PathVariable("Accno") int accno) {
		String str=acService.getCustAcc(accno);
		return "customer name is:"+str;
	}
	@GetMapping("/getAllCustomer")
	public List<Customer> fun3() throws ResourceNotFound{
		List<Customer> cusl=acService.getAllCustomer();
		if(cusl.size()==0) {
			throw new ResourceNotFound("Customer Not Found");
		}
		return cusl;
	}
	@DeleteMapping("/deleteCustomer/{id}")
	public ResponseEntity<Object> fun4(@PathVariable("id") int id) throws ResourceNotFound {
		String s1=acService.deleteCustomer(id);
		return new ResponseEntity<Object>(s1,HttpStatus.OK);
	}
	@DeleteMapping("/deleteAccount/{id}")
	public ResponseEntity<Object> deleteAcc(@PathVariable("id") int accId) throws ResourceNotFound {
		String s1=acService.deleteAcc(accId);
		return new ResponseEntity<Object>(s1,HttpStatus.OK);
	}
	@PutMapping("/updateCustomer/{id}")
	public ResponseEntity<Object> fun5(@RequestBody Customer cus,@PathVariable("id") int id) {
		List<Customer> cl=acService.getAllCustomer();
		List<Integer> cil=new ArrayList<Integer>();
		for(int i=0;i<cl.size();i++) {
			cil.add(cl.get(i).getCustomerId());
		}
		if(cil.contains(id)) {
			acService.updateCustomer(cus,id);
			return new ResponseEntity<Object>("Data Updated",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
	}
	@PutMapping("/updateAccount/{id}")
	public ResponseEntity<Object> updateAcc(@RequestBody Account acc,@PathVariable("id") int id){
		List<Account> al=acService.getAllAccountDetails();
		List<Integer> ail=new ArrayList<Integer>();
		System.out.println(al);
		for(int i=0;i<al.size();i++) {
			ail.add(al.get(i).getAccountId());
		}
		if(ail.contains(id)) {
			acService.updateAccountDetail(acc, id);
			return new ResponseEntity<Object>("Account Details Updated Successfully",HttpStatus.OK);
			
		}
		else {
			System.out.println("dscfzdczdc");
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/getAcc/{amount}")
	public List<Account> getAccByAmount(@PathVariable("amount") int amount) throws AccountNotFoundException {
		List<Account> al=acService.getAccountByAmount(amount);
		if(al.size()==0) {
			throw new AccountNotFoundException("Account who have this amount is not found");
		}
		return al;
	}
	@GetMapping("/Account/{type}")
	public List<Account> getAccountBySavType(@PathVariable("type") String type) throws AccountNotFoundException {
		List<Account> al=acService.getAccountByStype(type);
		if(al.size()==0) {
			throw new AccountNotFoundException("Account who have this account type is not found");
		}
		return al;
	}
	@DeleteMapping("/deleteAllCustomer")
	public ResponseEntity<Object> deleteAllCus() throws CustomerDoesNotExist {
		acService.deleteAllCustomer();
		return new ResponseEntity<Object>("All Customer Details have been deleted",HttpStatus.OK);
	}
	@DeleteMapping("/deleteAllAccount")
	public ResponseEntity<Object> deleteAllAcc() throws AccountDoesNotExist {
		acService.deleteAllAccount();
		return new ResponseEntity<Object>("All Account Details have been deleted",HttpStatus.OK);
	}
	@RequestMapping("transfer/{fid}/{tid}/{amount}")
	public String fun6(@PathVariable int fid,@PathVariable int tid,@PathVariable int amount) throws FromAccountDoesnotExist, ToAccountDoesnotExist, AmountInsufficientException{
		
		String s1="Amount is transfered successfully";
		List<Account> accl=acService.getAllAccountDetails();
		List<Integer> ac1=new ArrayList<Integer>();
		for(int i=0;i<accl.size();i++) {
			ac1.add(accl.get(i).getAccountId());
		}
		System.out.println(ac1);
		if(ac1.contains(fid)==false) {
			throw new FromAccountDoesnotExist("From Account Does not Exist");
		}
		else if(ac1.contains(tid)==false) {
			throw new ToAccountDoesnotExist("To Account Does not Exist");
		}
	    acService.amountTransfer(fid, tid, amount);
		return s1;
	}
	@GetMapping("/getCust/{name}")
	public List<Customer> getCustomername(@PathVariable("name") String nam) throws ResourceNotFound {
		List<Customer> cl=acService.getCustomerByName(nam);
		if(cl.size()==0) {
			throw new ResourceNotFound("Customer whose name is :"+nam+" are Not Found");
		}
		return cl;
	}
	@GetMapping("/getCustom/{city}")
	public List<Customer> getCustomerbycity(@PathVariable("city")String city) throws ResourceNotFound {
		List<Customer> cl=acService.getCustomerByCity(city);
		if(cl.size()==0) {
			throw new ResourceNotFound("Customer whose city is :"+city+" are Not Found");
		}
		return cl;
	}

}
