package com.projetNFA036.Controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.projetNFA036.Entities.Categorie;
import com.projetNFA036.repository.CategorieRepository;
import com.projetNFA036.service.CategorieService;

@Controller
public class CategorieController {

	@Autowired
	CategorieRepository catRepo;
	@Autowired
	CategorieService categorieService;

	@GetMapping("/showCreateCategorie")
	public String vueCreateCat(ModelMap modelMap) {

		modelMap.addAttribute("categorie", new Categorie());

		return "Categorie/createCategorie";

	}

	@RequestMapping("/saveCategorie")
	public String saveCategorie(@Valid Categorie categorie, BindingResult bindingResult, ModelMap model) {
		if (bindingResult.hasErrors())
			return "Categorie/createCategorie";

		try {
			catRepo.save(categorie);
		} catch (DataIntegrityViolationException e) {
			return "Categorie/accessDeniedCreate";
		}

		return "redirect:/showCreateCategorie";
	}

	@RequestMapping("/listerCategorie")
	public String listerCategorie(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size, Model model) {
		// List<Categorie> cat = categorieService.getAllCategories();
		Page<Categorie> cat = categorieService.getAllCategorieParPage(page, size);
		model.addAttribute("categories", cat);
		model.addAttribute("currentPage", page);
		model.addAttribute("pages", new int[cat.getTotalPages()]);
		model.addAttribute("totalItem", cat.getTotalElements());
		// model.addAttribute("categories", cat);

		return "Categorie/listerCategorie";
	}

	@GetMapping("/supprimerCategorie")
	public String supprimerCategorie(@RequestParam("id") Integer id,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size, Model model) {

		try {
			catRepo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			return "Categorie/accessDeniedCategorie";
		}
		Page<Categorie> cat = categorieService.getAllCategorieParPage(page, size);
		model.addAttribute("categories", cat);
		model.addAttribute("currentPage", page);
		model.addAttribute("pages", new int[cat.getTotalPages()]);
		model.addAttribute("totalItem", cat.getTotalElements());
		return "Categorie/listerCategorie";
	}

	@GetMapping("/modifierCategorie")
	public String modifierCategorie(@RequestParam("id") Integer id,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size, ModelMap modelMap) {
		Categorie c = catRepo.getById(id);

		modelMap.addAttribute("categorie", c);
		return "Categorie/modifierCategorie";

	}

	@PostMapping("/updateCategorie")
	public String updateCategorie(@ModelAttribute Categorie categorie,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size, Model model) {
		
		catRepo.save(categorie);
		Page<Categorie> cat = categorieService.getAllCategorieParPage(page, size);
		model.addAttribute("categories", cat);
		model.addAttribute("currentPage", page);
		model.addAttribute("pages", new int[cat.getTotalPages()]);
		model.addAttribute("totalItem", cat.getTotalElements());

		return "Categorie/listerCategorie";

	}

}
