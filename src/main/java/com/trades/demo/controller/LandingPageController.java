package com.trades.demo.controller;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trades.demo.utils.DataHandler;

@Controller
@RequestMapping("/landing")
public class LandingPageController 
{

	Logger logger = LoggerFactory.getLogger(LandingPageController.class);
	
	@RequestMapping("/home")
	public String getLandingPage(Model model)
	{
		Calendar cal = Calendar.getInstance();  
		cal.setTime(new Date());
		cal.add(Calendar.YEAR, -3); 
		Date fromDate = cal.getTime();
		
		model.addAttribute("candlesWithSMA", DataHandler.getSymbolDailyEODCandles("ACC", fromDate, new Date()));
		
		return "landingPage";
	}
	
}
