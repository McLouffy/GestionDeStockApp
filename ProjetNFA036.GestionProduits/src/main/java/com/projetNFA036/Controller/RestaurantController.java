package com.projetNFA036.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.projetNFA036.Entities.Restaurants;
import com.projetNFA036.repository.RestaurantRepository;

@Controller
public class RestaurantController {

	@Autowired
	RestaurantRepository rp;

	@GetMapping("/createRestaurant")
	public String vueCreateRestaurant(ModelMap modelMap) {

		

		return "Restaurant/createRestaurant";

	}
	


	@RequestMapping("/saveRestaurant")
	public String saveCategorie(@ModelAttribute Restaurants restaurant, ModelMap model) {

		rp.save(restaurant);

		return "Restaurant/createRestaurant";
	}

}
