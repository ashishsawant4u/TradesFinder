package com.trades.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/landing")
public class LandingPageController 
{

	Logger logger = LoggerFactory.getLogger(LandingPageController.class);
	
	@RequestMapping("/home")
	public String getLandingPage(Model model)
	{
		
		return "landingPage";
	}
	
}
