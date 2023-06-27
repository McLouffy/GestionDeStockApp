package com.projetNFA036.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.projetNFA036.Entities.Categorie;

import com.projetNFA036.repository.CategorieRepository;

@Service
public class CategorieServiceImpl implements CategorieService {

	@Autowired
	CategorieRepository categorieRepository;

	// Méthodes classe Catégorie
	/*@Override
	public List<Categorie> getAllCategories() {
		List<Categorie> cat = categorieRepository.findAll();


		return cat;
	}

	@Override
	public Categorie saveCategorie(Categorie c) {
		// TODO Auto-generated method stub
		return categorieRepository.save(c);
	}

	@Override
	public Categorie saveCatgorieId(Categorie id) {
		return categorieRepository.save(id);
	}

	@Override
	public Categorie updateCategorie(Categorie c) {
		// TODO Auto-generated method stub
		return categorieRepository.save(c);
	}*/

	@Override
	public Page<Categorie> getAllCategorieParPage(int page, int size) {
		
		return categorieRepository.findAll(PageRequest.of(page, size));
	}

}
