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

@Controller
@RequestMapping("/bhavcopy")
public class BhavCopyController 
{
	Logger logger = LoggerFactory.getLogger(BhavCopyController.class);
	
	@Resource(name = "nseBhavCopyService")
	NSEBhavCopyService nseBhavCopyService;
	
	@RequestMapping("/nse-refresh")
	@ResponseBody
	public String getNSEDailyEodBhavCopy(Model model,@PathVariable("symbol")String symbol)
	{
		Calendar cal = Calendar.getInstance();  
		cal.setTime(new Date());
		cal.add(Calendar.YEAR, -10); 
		Date fromDate = cal.getTime();
		
		return nseBhavCopyService.getNSEDailyEodBhavCopy(fromDate, new Date());
	}
	
	@RequestMapping("/nse-update")
	@ResponseBody
	public String updateNSEDailyEodBhavCopy(Model model,@PathVariable("symbol")String symbol)
	{	
		return nseBhavCopyService.updateNSEDailyEodBhavCopy();
	}
}
