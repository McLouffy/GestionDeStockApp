package com.projetNFA036.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projetNFA036.Entities.Produits;

public interface ProduitRepository extends JpaRepository<Produits, Integer> {

	/*
	 * List<Produits> findByNomProduit(String nom);
	 * 
	 * List<Produits> findByNomProduitContains(String nom);
	 * 
	 * @Query("select p from Produit p where p.nomProduit like %:nom and p.prixProduit > :prix"
	 * ) List<Produits> findByNomPrix(@Param("nom") String nom, @Param("prix")
	 * Double prix);
	 * 
	 * @Query("select p from Produit p where p.categorie = ?1") List<Produits>
	 * findByCategorie(Categorie categorie);
	 * 
	 * List<Produits> findByCategorieIdCat(int id);
	 */

	// @Query("select count(*) from produits")
	// List<Produits> getMaxProduits ();
	
	List <Produits> findAll();
	
}
