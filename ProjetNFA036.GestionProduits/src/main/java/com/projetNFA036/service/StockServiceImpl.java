package com.projetNFA036.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.projetNFA036.Entities.Stock;

import com.projetNFA036.repository.HistoriqueRepository;
import com.projetNFA036.repository.StockRepository;
import com.projetNFA036.repository.UserRepository;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	StockRepository stockRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	HistoriqueRepository historiqueRepository;

	@Override
	public Page<Stock> getAllStockParPage(int page, int size) {
		return stockRepository.findAll(PageRequest.of(page, size));
	}

	@Override
	public Stock ajoutDates(Stock stock) {

		LocalDate dateEntree = LocalDate.now();
		stock.setDateOuverture(dateEntree);
		stockRepository.save(stock);

		LocalDate dateExpiration = dateEntree.plusDays(stock.getProduit().getDlc());
		stock.setDateExpiration(dateExpiration);
		stockRepository.save(stock);

		return stock;

	}

}
