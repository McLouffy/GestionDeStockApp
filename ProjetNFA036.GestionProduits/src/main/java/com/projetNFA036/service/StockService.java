package com.projetNFA036.service;

import org.springframework.data.domain.Page;

import com.projetNFA036.Entities.Stock;

public interface StockService {

	Page<Stock> getAllStockParPage(int page, int size);

	Stock ajoutDates(Stock stock);

	

}
