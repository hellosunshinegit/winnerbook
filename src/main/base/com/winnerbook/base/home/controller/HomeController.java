package com.winnerbook.base.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping(value="/index.html")
	public String homePage(Model model){
		return "redirect:/user/login.html";
	}
}
