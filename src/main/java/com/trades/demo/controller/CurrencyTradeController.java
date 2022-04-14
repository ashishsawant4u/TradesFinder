package com.trades.demo.controller;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.math3.util.Precision;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.trades.demo.forms.CurrencyCalculatorForm;
import com.trades.demo.forms.CurrencyTradeDetails;
import com.trades.demo.forms.DoubleScreenTradeForm;
import com.trades.demo.models.CurrencyPairModel;
import com.trades.demo.utils.CurrencyPairMaster;
import com.trades.demo.utils.TradeCapitalPlan;
import com.trades.demo.utils.URLConstants;

@Controller
@RequestMapping("/currency")
public class CurrencyTradeController 
{
	Logger logger = LoggerFactory.getLogger(CurrencyTradeController.class);

	@RequestMapping("/calculator")
	public String currencyCalculatorLandingPage(Model model)
	{
		model.addAttribute("capitalData", new TradeCapitalPlan());
		
		
		return "currencyCalculatorPage";
	}
	
	@RequestMapping("/capitalrisk/{capital}/{risk}")
	@ResponseBody
	public TradeCapitalPlan calculateCapitalRisk(@PathVariable("capital") String capital,@PathVariable("risk")String risk)
	{
		TradeCapitalPlan capitalPlan = new TradeCapitalPlan();
		capitalPlan.setCapital(Double.parseDouble(capital));
		capitalPlan.setRiskPercentage(Double.parseDouble(risk));
		return capitalPlan;
	}
	
	@RequestMapping("/currencyPairInfo/{pairname}")
	@ResponseBody
	public CurrencyPairModel currencyPairInfo(@PathVariable("pairname") String pairname)
	{
		return CurrencyPairMaster.getCurrencyPairInfo(pairname);
	}
	
	@RequestMapping(value="/calculateCurrencyTrade",method = RequestMethod.POST)
	@ResponseBody
	public CurrencyCalculatorForm calculateCurrencyTrade(@RequestBody CurrencyCalculatorForm form)
	{
		logger.info(form.getInstrument());
		
		if(form.getTradeDecision().equalsIgnoreCase("buy"))
		{
			currencyLongCalculation(form);
		}
		else
		{
			currencyShortCalculation(form);
		}
		
		return form;
	}
	
	@RequestMapping(value = "/saveTrade" , method = RequestMethod.POST)
	public @ResponseBody String saveTrade(@RequestBody CurrencyTradeDetails currencyTradeDetails)
	{
		logger.info("currencyTradeDetails "+currencyTradeDetails);
		
		currencyTradeDetailsHandler(currencyTradeDetails);
		
		return "OK";
	}
	
	public void currencyTradeDetailsHandler(CurrencyTradeDetails d)
	{
		String filePath = URLConstants.CURRRENCY_TRADE_LOG_CSV_FILE;
		
		try 
		{
		    
			FileReader filereader = new FileReader(filePath);
			
		    CSVReader csvReader = new CSVReaderBuilder(filereader)
	                .withSkipLines(1)
	                .build();
		    List<String[]> allData = csvReader.readAll();
		    
		    int rowCount = allData.size();
		    
		    allData.removeAll(allData);
		    
		    CurrencyCalculatorForm calc = d.getCalculations();
	  
		    String data [] = {String.valueOf(rowCount+1),calc.getInstrument(),new Date().toString(),calc.getTradeDecision(),d.getStoryAndViewBuilding(),
		    				 d.getTradeBias(),d.getOpportunity(),d.getWhatIfAnalysis(),d.getConfirmation(),d.isExecuted() ? "Yes" : "NO",
		    			     d.getOutcome(),d.getLearnings(),String.valueOf(calc.getRiskPerTrade()),String.valueOf(calc.getPipSize()),String.valueOf(calc.getLotSize()),
		    			     String.valueOf(calc.getEntry()),String.valueOf(calc.getStopLoss()),String.valueOf(calc.getTarget()),String.valueOf(calc.getNumberOfLots()),
		    			     String.valueOf(calc.getTotalAmountForAllLots()),String.valueOf(calc.getMarginAmountForAllLots()),String.valueOf(calc.getRewardRatio()),
		    			     String.valueOf(calc.getTargetPriceMovement()),String.valueOf(calc.getTargetPipMovenment()),
		    			     String.valueOf(calc.getMaxProfitPerLot()),String.valueOf(calc.getMaxProfitAllLots()),String.valueOf(calc.getPnlPercentageAsPerAllLotsForTarget()),
		    			     String.valueOf(calc.getSlPriceMovement()),String.valueOf(calc.getSlPipMovenment()),
		    			     String.valueOf(calc.getMaxLossPerLot()),String.valueOf(calc.getMaxLossAllLots()),String.valueOf(calc.getPnlPercentageAsPerAllLotsForSL())};
	    
	   		File file = new File(filePath);
	        
	        FileWriter outputfile = new FileWriter(file,true);
	  
	        CSVWriter writer = new CSVWriter(outputfile);
			
	        writer.writeNext(data);
			 
	        writer.close();
	    }
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    }
	}
	
	private void currencyShortCalculation(CurrencyCalculatorForm form) 
	{
		CurrencyPairModel currencyPair = CurrencyPairMaster.getCurrencyPairInfo(form.getInstrument());
	
		
		// #### SELL TARGET CALCULATION
		
		//entry -target
		form.setTargetPriceMovement(Precision.round( form.getEntry() - form.getTarget() , 4));
		//price movement / min movement
		form.setTargetPipMovenment(Precision.round(form.getTargetPriceMovement()/currencyPair.getPipSize() , 4));
		
		//Lot size x number of lots x movement in pips x pip size
		form.setMaxProfitPerLot(Precision.round(currencyPair.getLotSize() * 1 * form.getTargetPipMovenment() * currencyPair.getPipSize() , 2));
		form.setMaxProfitAllLots(Precision.round(currencyPair.getLotSize() * form.getNumberOfLots() * form.getTargetPipMovenment() * currencyPair.getPipSize() , 4));
	
		form.setPnlPercentageAsPerAllLotsForTarget(Precision.round((form.getMaxProfitAllLots() / form.getMarginAmountForAllLots()) * 100 , 2));
		
		// #### SELL SL CALCULATION
		
		//entry-sl
		form.setSlPriceMovement(Precision.round(form.getEntry() - form.getStopLoss() , 4));
		
		//price movement / min movement
		form.setSlPipMovenment(Precision.round(form.getSlPriceMovement() /currencyPair.getPipSize() , 4));
		
		form.setMaxLossPerLot(Precision.round(currencyPair.getLotSize() * 1 * form.getSlPipMovenment() * currencyPair.getPipSize() , 2));
		form.setMaxLossAllLots(Precision.round(currencyPair.getLotSize() * form.getNumberOfLots() * form.getSlPipMovenment() * currencyPair.getPipSize() , 2));
		
		form.setPnlPercentageAsPerAllLotsForSL(Precision.round((form.getMaxLossAllLots() / form.getMarginAmountForAllLots()) * 100 , 2));
		
		form.setNumberOfLotsCanBeTraded(Precision.round( Math.round(Math.abs(form.getRiskPerTrade() / form.getMaxLossPerLot())) , 0));
		
		form.setRewardRatio(Math.round(form.getMaxLossPerLot()/form.getMaxLossPerLot()));
	}

	private void currencyLongCalculation(CurrencyCalculatorForm form) 
	{
		CurrencyPairModel currencyPair = CurrencyPairMaster.getCurrencyPairInfo(form.getInstrument());
		
		// #### BUY TARGET CALCULATION
		
		//target - entry
		form.setTargetPriceMovement(Precision.round( form.getTarget() - form.getEntry() , 4));
		//price movement / min movement
		form.setTargetPipMovenment(Precision.round( form.getTargetPriceMovement()/currencyPair.getPipSize() , 4));
		
		//Lot size x number of lots x movement in pips x pip size
		form.setMaxProfitPerLot(Precision.round( currencyPair.getLotSize() * 1 * form.getTargetPipMovenment() * currencyPair.getPipSize() , 2));
		form.setMaxProfitAllLots(Precision.round( currencyPair.getLotSize() * form.getNumberOfLots() * form.getTargetPipMovenment() * currencyPair.getPipSize() , 2));
		

		form.setPnlPercentageAsPerAllLotsForTarget(Precision.round( (form.getMaxProfitAllLots() / form.getMarginAmountForAllLots()) * 100 , 2));
		
		// #### BUY SL CALCULATION
		
		//sl - entry
		form.setSlPriceMovement(Precision.round( form.getStopLoss() - form.getEntry() , 4));
		
		//price movement / min movement
		form.setSlPipMovenment(Precision.round( form.getSlPriceMovement() /currencyPair.getPipSize() , 4));
		
		form.setMaxLossPerLot(Precision.round( currencyPair.getLotSize() * 1 * form.getSlPipMovenment() * currencyPair.getPipSize() , 2));
		form.setMaxLossAllLots(Precision.round( currencyPair.getLotSize() * form.getNumberOfLots() * form.getSlPipMovenment() * currencyPair.getPipSize() , 2));
		
		form.setPnlPercentageAsPerAllLotsForSL(Precision.round( (form.getMaxLossAllLots() / form.getMarginAmountForAllLots()) * 100 , 2));
		
		form.setNumberOfLotsCanBeTraded(Precision.round( Math.round(Math.abs(form.getRiskPerTrade() / form.getMaxLossPerLot())) , 0));
		
		form.setRewardRatio(Math.round(form.getMaxLossPerLot()/form.getMaxLossPerLot()));
	}
}
