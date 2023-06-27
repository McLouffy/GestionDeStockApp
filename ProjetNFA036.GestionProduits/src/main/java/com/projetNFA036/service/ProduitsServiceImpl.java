package com.projetNFA036.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projetNFA036.Entities.Produits;
import com.projetNFA036.repository.ProduitRepository;

@Service
public class ProduitsServiceImpl implements ProduitsService {

	@Autowired
	ProduitRepository produitRepository;

	@Override
	public Produits saveProduit(Produits p) {

		return produitRepository.save(p);
	}

	@Override
	public Produits updateProduit(Produits p) {
		// TODO Auto-generated method stub
		return produitRepository.save(p);
	}

	@Override
	public void deleteProduit(Produits p) {
		produitRepository.delete(p);

	}

	@Override
	public void deleteProduitById(Integer id) {
		produitRepository.deleteById(id);

	}

	@Override
	public Produits getProduit(Integer id) {
		// TODO Auto-generated method stub
		return produitRepository.findById(id).get();
	}

	@Override
	public List<Produits> getAllProduits() {
		// TODO Auto-generated method stub
		return produitRepository.findAll();
	}

	public List<Produits> findAll() {
		return produitRepository.findAll();
	}

	@Override
	public Page<Produits> getAllProduitsParPage(int page, int size) {
		return produitRepository.findAll(PageRequest.of(page, size));
	}

	@Override
	public Page<Produits> findPage(int pageNumber, int taille) {
		Pageable pageable = PageRequest.of(pageNumber - 1, taille);
		return produitRepository.findAll(pageable);
	}

	@Override
	public Page<Produits> getProduitPage(int page, int size) {
		

		return produitRepository.findAll(PageRequest.of(page, size));
	}

}
