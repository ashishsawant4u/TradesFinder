package com.trades.demo.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opencsv.CSVWriter;
import com.trades.demo.forms.DoubleScreenTradeForm;

@Controller
@RequestMapping("/doublescreen")
public class DoubleScreenSystemController 
{
	Logger logger = LoggerFactory.getLogger(DoubleScreenSystemController.class);
	
	@RequestMapping("/plan")
	public String getLandingPage(Model model)
	{
		return "doubleScreenSystemPage";
	}
	
	@RequestMapping(value = "/tradelog" , method = RequestMethod.POST)
	public @ResponseBody String dssTradeLog(@RequestBody DoubleScreenTradeForm doubleScreenTradeForm)
	{
		logger.info("doubleScreenTradeForm "+doubleScreenTradeForm);
		
		dssTradeLogHandler(doubleScreenTradeForm);
		
		return "OK";
	}
	
	public static void dssTradeLogHandler(DoubleScreenTradeForm d)
	{
		String filePath = "C:\\Users\\ashis\\Documents\\DSS-TradeLogs.csv";
	  
	    File file = new File(filePath);
	  
	   String data [] = {d.stock,d.date,d.tide,d.wave,d.dssDecision,d.candleStickpattern,d.volume,d.ema,d.chartpattern,d.fibRetracement,d.divergence,
			   			d.immediateSupportForStopLoss,d.majorResistanceForTarget,d.immediateResistanceForStopLoss,d.majorSupportForTarget,
			   			d.closePrice,d.entryPrice,d.closePrice,d.minTargetPrice,d.maxTargetPrice,d.quantity,d.tradeInvestment,d.minReward,d.maxReward,
			   			d.riskPerUnit,d.totalRisk,d.minProfitPotential,d.maxProfitPotential,d.minROI,d.maxROI,
			   			d.minRR,d.maxRR};
	    
	    try {
	        
	        FileWriter outputfile = new FileWriter(file,true);
	  
	        CSVWriter writer = new CSVWriter(outputfile);
			
	        writer.writeNext(data);
			 
	        writer.close();
	    }
	    catch (IOException e) 
	    {
	        e.printStackTrace();
	    }
	}
}
