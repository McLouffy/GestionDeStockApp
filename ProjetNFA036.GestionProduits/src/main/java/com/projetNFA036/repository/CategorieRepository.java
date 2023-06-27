package com.projetNFA036.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetNFA036.Entities.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Integer> {
	
	//@Query("select count(p) from Produits p where p.categorie=:categorie")
	//public int nbProdParCat(@Param("categorie") Categorie categorie);

}
