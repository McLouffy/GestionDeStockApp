package com.projetNFA036.service;



import org.springframework.data.domain.Page;

import com.projetNFA036.Entities.Categorie;

public interface CategorieService {

	//List<Categorie> getAllCategories();
	// List<Categorie> getAllProdParCategorie();

	//Categorie saveCategorie(Categorie c);

	//Categorie saveCatgorieId(Categorie id);

	//Categorie updateCategorie(Categorie c);

	Page<Categorie> getAllCategorieParPage(int page, int size);

}
