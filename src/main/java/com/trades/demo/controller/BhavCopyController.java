package com.trades.demo.controller;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trades.demo.bhavcopy.NSEBhavCopyService;
import com.trades.demo.bhavcopy.YahooFinanaceService;
import com.trades.demo.utils.StockIndicesHelper;

@Controller
@RequestMapping("/bhavcopy")
public class BhavCopyController 
{
	Logger logger = LoggerFactory.getLogger(BhavCopyController.class);
	
	@Resource(name = "nseBhavCopyService")
	NSEBhavCopyService nseBhavCopyService;
	
	@Resource(name = "yahooFinanaceService")
	YahooFinanaceService yahooFinanaceService;
	
	@RequestMapping("/nse-refresh")
	@ResponseBody
	public String getNSEDailyEodBhavCopy(Model model)
	{
		Calendar cal = Calendar.getInstance();  
		cal.setTime(new Date());
		cal.add(Calendar.YEAR, -21); 
		Date fromDate = cal.getTime();
		
		return nseBhavCopyService.getNSEDailyEodBhavCopy(fromDate, new Date());
	}
	
	@RequestMapping("/nse-update")
	@ResponseBody
	public String updateNSEDailyEodBhavCopy(Model model,@PathVariable("symbol")String symbol)
	{	
		return nseBhavCopyService.updateNSEDailyEodBhavCopy();
	}
	
	@RequestMapping("/yahoo-eod")
	@ResponseBody
	public String getNSEDailyEodBhavCopyWithYahoo(Model model)
	{	
		return yahooFinanaceService.getHistoricalEodDataForNSE();
	}
	
	@RequestMapping("/yahoo-eod-update")
	@ResponseBody
	public String updateNSEDailyEodBhavCopyWithYahoo(Model model)
	{	
		return yahooFinanaceService.updateHistoricalEodDataForNSE();
	}
	
	@RequestMapping("/download-indices")
	@ResponseBody
	public String downloadIndices(Model model)
	{	
		StockIndicesHelper.downloadAllIndices();
		
		return "Indices Download Done";
	}
	
}
