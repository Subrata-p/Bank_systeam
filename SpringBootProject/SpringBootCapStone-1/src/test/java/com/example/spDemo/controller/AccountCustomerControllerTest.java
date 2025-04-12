package com.example.spDemo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.spDemo.exception.AccountDoesNotExist;
import com.example.spDemo.exception.ResourceNotFound;
import com.example.spDemo.model.Account;
import com.example.spDemo.model.Customer;
import com.example.spDemo.service.AccountCustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AccountCustomerController.class)
class AccountCustomerControllerTest {
	@Autowired
	private MockMvc webMvc;
	
	@MockBean
	private AccountCustomerService acccusService;

	@Test
	void testFun1() throws Exception {
		Customer c1=new Customer(101,"Narendra","Delhi");
		Account a1=new Account(200,"Saving",2000);
		Account a2=new Account(201,"Current",3000);
		List<Account> al=new ArrayList<Account>();
		al.add(a1);
		al.add(a2);
		c1.setAcc(al);
		String conToJson=this.ConvertToJson(c1);
		MvcResult mvcRes=webMvc.perform(MockMvcRequestBuilders.post("/addCustomer").contentType(MediaType.APPLICATION_JSON_VALUE).content(conToJson)).andReturn();
		MockHttpServletResponse response=mvcRes.getResponse();
		assertEquals(201,response.getStatus());
	}
	@Test
	void testaddAcc() throws Exception {
		Account a1=new Account(200,"Saving",2000);
		Customer c1=new Customer(101,"Narendra","Delhi");
		a1.setCustomer(c1);
		String conToJson=this.ConvertToJson(a1);
		MvcResult mvcRes=webMvc.perform(MockMvcRequestBuilders.post("/addAccount").contentType(MediaType.APPLICATION_JSON_VALUE).content(conToJson)).andReturn();
		MockHttpServletResponse response=mvcRes.getResponse();
		assertEquals(201,response.getStatus());
	}
	@Test
	void testFun3() throws Exception {
		//fail("Not yet implemented");
		List<Customer> cl=new ArrayList<Customer>();
		Customer c1=new Customer(101,"Narendra","Delhi");
		Account a1=new Account(200,"Saving",2000);
		Account a2=new Account(201,"Current",3000);
		List<Account> al=new ArrayList<Account>();
		al.add(a1);
		al.add(a2);
		c1.setAcc(al);
		cl.add(c1);
		when(acccusService.getAllCustomer()).thenReturn(cl);
		MvcResult mvcResult=webMvc.perform(MockMvcRequestBuilders.get("/getAllCustomer")).andReturn();
		MockHttpServletResponse response=mvcResult.getResponse();
		assertEquals(200,response.getStatus());
	}
	@Test
	void testFun2() throws Exception {
		List<Customer> cl=new ArrayList<Customer>();
		Customer c1=new Customer(101,"Narendra","Delhi");
		Account a1=new Account(200,"Saving",2000);
		Account a2=new Account(201,"Current",3000);
		List<Account> al=new ArrayList<Account>();
		al.add(a1);
		al.add(a2);
		c1.setAcc(al);
		//cl.add(c1);
		String conToJson=this.ConvertToJson(c1);
		//System.out.println(conToJson);
		when(acccusService.getCustomerId(101)).thenReturn(c1);
		RequestBuilder request=MockMvcRequestBuilders.get("/getCustomer/{id}",101).accept(MediaType.APPLICATION_JSON_VALUE);
		MvcResult mvcResult=webMvc.perform(request).andExpect(status().isOk()).andExpect(content().json("{customerId:101,customerName:Narendra,customerCity:Delhi,acc:[{accountId:200,accountType:Saving,amount:2000},{accountId:201,accountType:Current,amount:3000}]}")).andReturn();
		
	}
	@Test
	void testgetAccDet() throws Exception {
		Account a1=new Account(200,"Saving",3000);
		when(acccusService.getAccount(200)).thenReturn(a1);
		RequestBuilder request=MockMvcRequestBuilders.get("/getAccountDet/{accNum}",200).accept(MediaType.APPLICATION_JSON_VALUE);
		MvcResult mvcResult=webMvc.perform(request).andExpect(status().isOk()).andExpect(content().json("{accountId:200,accountType:Saving,amount:3000}")).andReturn();
	}
	@Test
	void testFun4() throws Exception{
		
		Customer c1=new Customer(101,"Narendra","Delhi");
		Account a1=new Account(200,"Saving",3000);
		List<Account> al=new ArrayList<Account>();
		al.add(a1);
		c1.setAcc(al);
		when(acccusService.deleteCustomer(any(Integer.class))).thenReturn("Customer deleted");
		RequestBuilder request=MockMvcRequestBuilders.delete("/deleteCustomer/{id}",101);
		MvcResult mvcRes=webMvc.perform(request).andReturn();
		MockHttpServletResponse response=mvcRes.getResponse();
		assertEquals(200,response.getStatus());
		
		
	}
	@Test
	void testdeleteAllAcc() throws Exception {
		Account a1=new Account(200,"Saving",2000);
		Customer c1=new Customer(101,"Narendra","Delhi");
		a1.setCustomer(c1);
		when(acccusService.deleteAllAccount()).thenReturn("deleted");
		RequestBuilder request=MockMvcRequestBuilders.delete("/deleteAllAccount");
		MvcResult mvcRes=webMvc.perform(request).andReturn();
		MockHttpServletResponse response=mvcRes.getResponse();
		assertEquals(200,response.getStatus());
	}
	@Test
	void testdeleteAllCus() throws Exception {
		Account a1=new Account(200,"Saving",2000);
		Customer c1=new Customer(101,"Narendra","Delhi");
		List<Account> al=new ArrayList<Account>();
		al.add(a1);
		c1.setAcc(al);
		when(acccusService.deleteAllCustomer()).thenReturn("deleted");
		RequestBuilder request=MockMvcRequestBuilders.delete("/deleteAllCustomer");
		MvcResult mvcRes=webMvc.perform(request).andReturn();
		MockHttpServletResponse response=mvcRes.getResponse();
		assertEquals(200,response.getStatus());
	}
	@Test
	void testdeleteAcc() throws Exception {
		Account a1=new Account(200,"Saving",2000);
		Customer c1=new Customer(101,"Narendra","Delhi");
		a1.setCustomer(c1);
		when(acccusService.deleteAcc(200)).thenReturn("deleted");
		RequestBuilder request=MockMvcRequestBuilders.delete("/deleteAccount/{id}",200);
		MvcResult mvcRes=webMvc.perform(request).andExpect(status().isOk()).andReturn();
		MockHttpServletResponse response=mvcRes.getResponse();
		assertEquals(200,response.getStatus());
	}
    @Test
    void testgetBalance() throws Exception {
    	Account a1=new Account(200,"Saving",2000);
		Customer c1=new Customer(101,"Narendra","Delhi");
		a1.setCustomer(c1);
		when(acccusService.getAccountBalance(a1.getAccountId())).thenReturn(a1.getAmount());
		RequestBuilder request=MockMvcRequestBuilders.get("/getBalance/{accNum}",200);
		MvcResult mvcRes=webMvc.perform(request).andReturn();
		MockHttpServletResponse response=mvcRes.getResponse();
		assertEquals(200,response.getStatus());
    }
	private String ConvertToJson(Object obj) throws JsonProcessingException {
		ObjectMapper mapper=new ObjectMapper();
		return mapper.writeValueAsString(obj);
	}

}
