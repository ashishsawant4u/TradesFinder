package com.trades.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/trades-finder")
public class LandingPageController {

	@RequestMapping("/home")
	public String getLandingPage()
	{
		return "landingPage";
	}
	
}
