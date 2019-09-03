package com.ing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ing.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String>{
	Customer findByCustomerIdAndPassword(String customerId, String password);
}
