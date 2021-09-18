package com.trades.demo.strategy;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trades.demo.common.TradeConstants;
import com.trades.demo.models.CandleModel;
import com.trades.demo.models.TradeEntryModel;

public class QuantityPlanner {

	private static final Logger logger = LoggerFactory.getLogger(QuantityPlanner.class);	
	
	public static double CAPITAL_AMOUNT = 50000.00;
	
	public static double CAPITAL_PER_TRADE_RISK_PERCENTAGE = 1.0;
	
	public static double MAX_TRADE_AMOUNT_PER_MONTH =  CAPITAL_AMOUNT;
	
	public static double MAX_AMOUNT_PER_TRADE = 5000.00;
	
	public static double MAX_RISK_PER_TRADE = (MAX_TRADE_AMOUNT_PER_MONTH *  CAPITAL_PER_TRADE_RISK_PERCENTAGE)/100;
	
	public static Map<String,Double> monthlyTradeAmountTrackerMap = new HashMap<String, Double>();
	
	public static int getQuantity(CandleModel entryCandle)
	{
		TradeEntryModel tradeEntry =  entryCandle.getTradeEntry();
		
		int quantity = 1;
		
		try 
		{
			if(TradeConstants.ENABLE_QTY_PLANNER && tradeEntry.getLossPerUnit() > 1)
			{
				quantity = (int) (   Math.round(MAX_RISK_PER_TRADE) / Math.round(tradeEntry.getLossPerUnit())    );
				
				double investmentAmount = tradeEntry.getBuyPrice() * Math.round(quantity);
				
				if(investmentAmount > MAX_AMOUNT_PER_TRADE)
				{
					int tempQty = (int) (MAX_AMOUNT_PER_TRADE / tradeEntry.getBuyPrice());
					quantity = (tempQty<1) ? 0 : tempQty;
					investmentAmount = tradeEntry.getBuyPrice() * Math.round(quantity);
				}
				
				double riskAmount = quantity * tradeEntry.lossPerUnit;
				if(riskAmount >  MAX_RISK_PER_TRADE)
				{
					for(int qty=quantity; qty>0 ; qty--)
					{
						riskAmount = qty * tradeEntry.lossPerUnit;
						
						if(riskAmount <= MAX_RISK_PER_TRADE)
						{
							quantity = qty;
							break;
						}
					}
				}
			}
		} 
		catch (Exception e) 
		{
			logger.info("QuantityPlanner Broke ||||||| "+e.getMessage());
		}
		
		
		//quantity=1;
		
		return quantity;
	} 
	
	public static void trackTradeAmountForMonth(CandleModel trade)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("MMM-yyyy");
		String month = sdf.format(trade.getMarketDateTime());
		
		
		if(!monthlyTradeAmountTrackerMap.containsKey(month))
		{
			monthlyTradeAmountTrackerMap.put(month, trade.getTradeEntry().getInvestment());
		}
		else
		{
			monthlyTradeAmountTrackerMap.put(month, monthlyTradeAmountTrackerMap.get(month) + trade.getTradeEntry().getInvestment());
		}
		
	}
	
	
}
