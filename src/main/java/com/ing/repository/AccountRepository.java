package com.ing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ing.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String>{

	public Account findBycustomerId(String customerId);
	
}
