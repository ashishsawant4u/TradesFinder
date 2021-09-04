package com.trades.demo.controller;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trades.demo.utils.DataHandler;

@Controller
@RequestMapping("/candlebasics")
public class CandleBasicsController 
{
	Logger logger = LoggerFactory.getLogger(LandingPageController.class);
	
	@RequestMapping("/data/{symbol}")
	public String getLandingPage(Model model,@PathVariable("symbol")String symbol)
	{
		Calendar cal = Calendar.getInstance();  
		cal.setTime(new Date());
		cal.add(Calendar.YEAR, -3); 
		Date fromDate = cal.getTime();
		
		model.addAttribute("candlesWithSMA", DataHandler.getSymbolDailyEODCandles(symbol, fromDate, new Date()));
		
		return "candleBasicsPage";
	}
}
