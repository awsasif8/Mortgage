package com.ing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ing.entity.Mortagage;

@Repository
public interface MortagageRepository extends JpaRepository<Mortagage, String> {
	public Mortagage findBycustomerId(String customerId);
}
