package com.example.spDemo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.spDemo.model.*;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer>{
	List<Account> findAccountByAmount(int amount);
    List<Account> findAccountByAccountType(String accType);
    
}
