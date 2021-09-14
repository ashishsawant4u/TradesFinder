package com.trades.demo.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trades.demo.models.CandleModel;
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
		cal.add(Calendar.YEAR, -10); 
		Date fromDate = cal.getTime();
		
		List<CandleModel> candles = DataHandler.getSymbolDailyEODCandles(symbol, fromDate, new Date());
		
		Calendar cal2 = Calendar.getInstance();  
		cal2.setTime(fromDate);
		cal2.add(Calendar.YEAR, 6); 
		
		candles = candles.stream().filter(c->c.getMarketDateTime().after(cal2.getTime())).collect(Collectors.toList());
		
		model.addAttribute("candlesWithSMA", candles);
		
		return "candleBasicsPage";
	}
}
