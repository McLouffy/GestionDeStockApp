package com.projetNFA036.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projetNFA036.Entities.Categorie;
import com.projetNFA036.Entities.Produits;

import com.projetNFA036.repository.CategorieRepository;
import com.projetNFA036.repository.ProduitRepository;
import com.projetNFA036.service.ProduitsService;

@Controller
public class ProduitController {

	@Autowired
	ProduitRepository prodRepo;
	@Autowired
	ProduitsService prodServ;

	@Autowired
	CategorieRepository catRepo;

	
	@GetMapping("/showCreate")
	public String vueCreateProd(ModelMap modelMap) {

		List<Categorie> list = catRepo.findAll();
		modelMap.addAttribute("listeCat", list);
		modelMap.addAttribute("produit", new Produits());

		return "Produit/createProduit";

	}

	// ModelAttribute : récupération d'un objet de type Produit dans le formulaire
	@PostMapping("/saveProduit")
	public String saveProduit(ModelMap model, @ModelAttribute("produit") @Valid Produits produit,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			List<Categorie> list = catRepo.findAll();
			model.addAttribute("listeCat", list);
			return "Produit/createProduit";

		}
		
		prodRepo.save(produit);
		model.addAttribute("produit", produit);
		return "redirect:showCreate";
	}

	
	@GetMapping("/listerProduits")
	public String findPaginated(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "5") int size, Model model) {

		Page<Produits> produitsList = prodServ.getAllProduitsParPage(page, size);
		model.addAttribute("produits", produitsList);
		model.addAttribute("currentPage", page);
		//model.addAttribute("pages", produitsList.getTotalPages());// totalPages
		model.addAttribute("pages", new int[produitsList.getTotalPages()]);
		model.addAttribute("totalItem", produitsList.getTotalElements());
		return "Produit/listerProduits";
	}
	

	@GetMapping("/supprimerProduit")
	public String supprimerProduit(@RequestParam("id") Integer id, ModelMap modelMap,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		prodRepo.deleteById(id);
		
		Page<Produits> produitsList = prodServ.getAllProduitsParPage(page, size);
		
		
		modelMap.addAttribute("produits", produitsList);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("pages", new int[produitsList.getTotalPages()]);
		modelMap.addAttribute("totalItem", produitsList.getTotalElements());
		return "Produit/listerProduits";
	}

	@GetMapping("/modifierProduit")
	public String modifierProduit(@RequestParam("id") Integer id, ModelMap modelMap, @RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		Produits p = prodRepo.getById(id);
		List<Categorie> list = catRepo.findAll();
		modelMap.addAttribute("listeCat", list);
		modelMap.addAttribute("produit", p);
		return "Produit/modifierProduit";

	}

	@PostMapping("/updateProduit")
	public String updateProduit(Produits produit, ModelMap modelMap, @RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
	

		prodRepo.save(produit);
		Page<Produits> produitsList = prodServ.getAllProduitsParPage(page, size);
		modelMap.addAttribute("produits", produitsList);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("pages", produitsList.getTotalPages());
		modelMap.addAttribute("totalItem", produitsList.getTotalElements());

		return "Produit/listerProduits";

	}

}
