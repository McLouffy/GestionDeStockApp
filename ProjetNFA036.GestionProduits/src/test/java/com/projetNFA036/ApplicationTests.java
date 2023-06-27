package com.projetNFA036;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.projetNFA036.Entities.Produits;
import com.projetNFA036.repository.ProduitRepository;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private ProduitRepository produitRepository;

	/*
	 * @Test public void testCreateProduit() { Produits prod = new
	 * Produits("Steack Haché", "Viande", 3); produitRepository.save(prod); }
	 */

	
}
