package com.trades.demo.controller;

import java.text.DecimalFormat;

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

import com.trades.demo.forms.CurrencyCalculatorForm;
import com.trades.demo.models.CurrencyPairModel;
import com.trades.demo.utils.CurrencyPairMaster;
import com.trades.demo.utils.TradeCapitalPlan;

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
		
		form.setNumberOfLotsCanBeTraded(Precision.round( Math.abs(form.getRiskPerTrade() / form.getMaxLossPerLot()) , 0));
		
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
		
		form.setNumberOfLotsCanBeTraded(Precision.round( Math.abs(form.getRiskPerTrade() / form.getMaxLossPerLot()) , 0));
	}
}
