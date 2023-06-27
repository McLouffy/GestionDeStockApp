package com.projetNFA036.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.projetNFA036.Entities.Historique;
import com.projetNFA036.Entities.Stock;
import com.projetNFA036.repository.HistoriqueRepository;

@Service
public class HistoriqueServiceImpl implements HistoriqueService {

	@Autowired
	HistoriqueRepository histRepo;

	@Override
	public Historique saveHistorique(Stock s) {

		return histRepo.save(s);
	}
	
	@Override
	public Historique ajouterStockHistorique(Stock s) {

		Historique h = new Historique();
		h.setStock(s);
		h.setAction("Creation");
		LocalDate controle = LocalDate.now();
		h.setDateControle(controle);
		//histRepo.save(h);
		
		return histRepo.save(h);
	}
	
	@Override
	public Historique modifierStockHistorique(Stock s) {

		Historique h = new Historique();
		h.setStock(s);
		h.setAction("Modifié");
		LocalDate controle = LocalDate.now();
		h.setDateControle(controle);
		//histRepo.save(h);
		
		return histRepo.save(h);
	}

	@Override
	public Page<Historique> getAllHistoriqueParPage(int page, int size) {
		return histRepo.findAll(PageRequest.of(page, size));
	}

}
