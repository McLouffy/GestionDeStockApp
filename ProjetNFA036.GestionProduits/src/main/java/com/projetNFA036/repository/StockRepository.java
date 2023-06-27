package com.projetNFA036.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetNFA036.Entities.Stock;

public interface StockRepository extends JpaRepository<Stock, Integer> {

	List<Stock> findAll();

}
