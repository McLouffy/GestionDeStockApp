package com.projetNFA036.service;

import java.util.List;

import org.springframework.data.domain.Page;
import com.projetNFA036.Entities.Produits;

public interface ProduitsService {

	Produits saveProduit(Produits p);

	Produits updateProduit(Produits p);

	void deleteProduit(Produits p);

	void deleteProduitById(Integer id);

	Produits getProduit(Integer id);

	List<Produits> getAllProduits();
	
	Page<Produits> getAllProduitsParPage(int page, int size);

	Page<Produits> findPage(int pageNumber, int taille);
	
	Page<Produits> getProduitPage(int page, int size);

	
	
	

	
	
	
	

}
