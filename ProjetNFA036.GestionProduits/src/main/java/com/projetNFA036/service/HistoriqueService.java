package com.projetNFA036.service;

import org.springframework.data.domain.Page;

import com.projetNFA036.Entities.Historique;
import com.projetNFA036.Entities.Stock;

public interface HistoriqueService {

	Historique saveHistorique(Stock s);

	Page<Historique> getAllHistoriqueParPage(int page, int size);

	Historique ajouterStockHistorique(Stock s);

	Historique modifierStockHistorique(Stock s);



}
