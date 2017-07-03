package com.spfood.uias.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value="main")
public class MainController {
	
	
	@RequestMapping(value = "/page")
	public String showPage() {
		return "main";
	}

}
