package com.projetNFA036.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projetNFA036.Entities.Historique;
import com.projetNFA036.repository.HistoriqueRepository;
import com.projetNFA036.service.HistoriqueService;

@Controller
public class HistoriqueController {

	@Autowired
	HistoriqueRepository histRepo;

	@Autowired
	HistoriqueService histService;

	@GetMapping("/listerHistorique")
	public String listeHistorique(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size, ModelMap modelMap) {

		// List<Historique> hist = histRepo.findAll();
		// modelMap.addAttribute("historique", hist);
		Page<Historique> historique = histService.getAllHistoriqueParPage(page, size);
		modelMap.addAttribute("historique", historique);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("pages", new int[historique.getTotalPages()]);
		modelMap.addAttribute("totalItem", historique.getTotalElements());
		return "Historique/listerHistorique";

	}

}
