package com.projetNFA036.Security;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

	@GetMapping("/login")
	public String getLoginForm() {
		return "login";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) throws ServletException {
		request.logout();
		return "redirect:/login";
	}

}