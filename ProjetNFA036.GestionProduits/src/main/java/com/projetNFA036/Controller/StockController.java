package com.projetNFA036.Controller;

import java.security.Principal;
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
import com.projetNFA036.Entities.Historique;
import com.projetNFA036.Entities.Produits;
import com.projetNFA036.Entities.Stock;
import com.projetNFA036.repository.HistoriqueRepository;
import com.projetNFA036.repository.ProduitRepository;
import com.projetNFA036.repository.StockRepository;
import com.projetNFA036.service.HistoriqueService;
import com.projetNFA036.service.StockService;

@Controller
public class StockController {

	@Autowired
	StockRepository stockRepo;

	@Autowired
	StockService stockServ;

	@Autowired
	ProduitRepository prodRepo;

	@Autowired
	HistoriqueRepository histRepo;

	@Autowired
	HistoriqueService histServ;

	@GetMapping("/showCreateStock")
	public String vueCreateProd(ModelMap modelMap) {

		modelMap.addAttribute("listeProd", prodRepo.findAll());
		modelMap.addAttribute("stock", new Stock());
		return "Stock/createStock";

	}

	// ModelAttribute : récupération d'un objet de type Produit dans le formulaire
	@PostMapping("/saveProduitStock")
	public String saveProduit(ModelMap model, @ModelAttribute("stock") @Valid Stock stock, BindingResult bindingResult,
			Principal principal) {

		if (bindingResult.hasErrors()) {
			List<Produits> listeProd = prodRepo.findAll();
			model.addAttribute("listeProd", listeProd);
			return "Stock/createStock";
		}

		// Gestion de la date d'ouverture+expiration
		stock = stockServ.ajoutDates(stock);

		// Gestion temps restant
		stock.getTempsRestant();

		stock.setUserCreation(principal.getName());
		stockRepo.save(stock);

		// rajout dans historique
		histServ.ajouterStockHistorique(stock);

		model.addAttribute("stock", stock);
		return "redirect:showCreateStock";
	}

	@GetMapping("/listerProduitsStock")
	public String findPage(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size, Model model) {
		Page<Stock> stock = stockServ.getAllStockParPage(page, size);
		model.addAttribute("stock", stock);
		model.addAttribute("currentPage", page);
		model.addAttribute("pages", new int[stock.getTotalPages()]);
		model.addAttribute("totalItem", stock.getTotalElements());

		return "Stock/listerStock";

	}

	@GetMapping("/modifierProduitStock")
	public String modifierProduitStock(@RequestParam("id") Integer idS,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size, ModelMap modelMap) {

		Stock pStock = stockRepo.getById(idS);
		List<Produits> listProduits = prodRepo.findAll();

		modelMap.addAttribute("listeProd", listProduits);
		modelMap.addAttribute("stock", pStock);
		modelMap.addAttribute("historique", new Historique());

		return "Stock/modifierStock";

	}

	@PostMapping("/updateProduitStock")
	public String updateProduit(Stock stock, Historique histo,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size, Model model, Principal principal) {

		histServ.modifierStockHistorique(stock);
		stock.setUserCreation(principal.getName());
		stockRepo.save(stock);

		Page<Stock> stockL = stockServ.getAllStockParPage(page, size);
		model.addAttribute("stock", stockL);
		model.addAttribute("historique", histo);
		model.addAttribute("currentPage", page);
		model.addAttribute("pages", new int[stockL.getTotalPages()]);
		model.addAttribute("totalItem", stockL.getTotalElements());

		return "Stock/listerStock";

	}

}
