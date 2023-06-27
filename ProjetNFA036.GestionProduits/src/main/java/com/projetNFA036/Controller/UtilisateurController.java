package com.projetNFA036.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projetNFA036.Entities.Roles;

import com.projetNFA036.Entities.User;
import com.projetNFA036.repository.RoleRepository;
import com.projetNFA036.repository.UserRepository;
import com.projetNFA036.service.UserService;

@Controller
public class UtilisateurController {
	@Autowired
	UserRepository userR;

	@Autowired
	UserService userS;
	@Autowired
	RoleRepository roleR;
	@Autowired
	PasswordEncoder passwordEncoder;

	@PostMapping("/showCreateUser")
	public String showCreate() {
		return "Utilisateur/createUser";
	}

	@GetMapping("/showCreateUser")
	public String vueCreateUser(ModelMap modelMap) {

		// List <Role> roles = roleR.findAll();
		modelMap.addAttribute("listeRoles", roleR.findAll());
		modelMap.addAttribute("user", new User());

		return "Utilisateur/createUser";

	}

	@PostMapping("/saveUser")
	public String saveUser(ModelMap model, @ModelAttribute("user") @Valid User user, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			List<Roles> role = roleR.findAll();
			model.addAttribute("listeRoles", role);
			return "Utilisateur/createUser";

		} else {
			// Categorie cat = catRepo.getById(produit.getCategorie().getIdCategorie());
			// produit.setCategorie(cat);

			try {
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				user.setEnabled(true);
				userR.save(user);
			} catch (DataIntegrityViolationException e) {
				return "Utilisateur/accessDenied";
			}

			model.addAttribute("user", userR.findAll());
			return "redirect:showCreateUser";

		}
	}

	@GetMapping("/listerUser")
	public String listerUser(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size, ModelMap modelMap) {

		Page<User> user = userS.getAllUserParPage(page, size);
		modelMap.addAttribute("user", user);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("pages", new int[user.getTotalPages()]);
		modelMap.addAttribute("totalItem", user.getTotalElements());
		// List<User> user = userR.findAll();

		// modelMap.addAttribute("user", user);

		return "Utilisateur/listerUser";
	}

	@GetMapping("/supprimerUser")
	public String supprimerUser(@RequestParam("id") Long id, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size, ModelMap modelMap) {

		userR.deleteById(id);

		Page<User> user = userS.getAllUserParPage(page, size);
		modelMap.addAttribute("user", user);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("pages", new int[user.getTotalPages()]);
		modelMap.addAttribute("totalItem", user.getTotalElements());
		return "Utilisateur/listerUser";
	}

	@GetMapping("/modifierUser")
	public String modifierUser(@RequestParam("id") Long id, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size, ModelMap modelMap) {

		User idUser = userR.getById(id);
		List<Roles> list = roleR.findAll();

		modelMap.addAttribute("listeRoles", list);
		modelMap.addAttribute("user", idUser);
		return "Utilisateur/modifierUser";

	}

	@PostMapping("/updateUser")
	public String updateProduit(User user, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size, ModelMap modelMap) {
		// Categorie cat = catRepo.getById(list);
		// produit.setCategorie(cat);

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setEnabled(true);
		userR.save(user);
		Page<User> userP = userS.getAllUserParPage(page, size);
		modelMap.addAttribute("user", userP);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("pages", new int[userP.getTotalPages()]);
		modelMap.addAttribute("totalItem", userP.getTotalElements());

		return "Utilisateur/listerUser";

	}

}
