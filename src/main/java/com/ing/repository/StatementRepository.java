package com.ing.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ing.entity.Transaction;
@Repository
public interface StatementRepository  extends JpaRepository<Transaction,Integer>{
@Query("select c from Transaction c where c.accountNumber=:accountNumber order by c.transactionDate desc")
	List<Transaction> findByAccountNumber(String accountNumber,Pageable pageable);

}
