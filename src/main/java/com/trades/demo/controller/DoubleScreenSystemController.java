package com.trades.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/doublescreen")
public class DoubleScreenSystemController 
{
	@RequestMapping("/plan")
	public String getLandingPage(Model model)
	{
		return "doubleScreenSystemPage";
	}
}
