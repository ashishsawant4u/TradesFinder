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
	
//	static float BALANCE_AMOUNT = 50000.00F;
	
//	static float MAX_AFFORDABLE_LOSS_AMOUNT = 50000.00F;
	
//	static int NUMBER_OF_TRADES_CAN_GO_WRONG = 20;

//	static float MAX_RISK_PER_TRADE = MAX_AFFORDABLE_LOSS_AMOUNT / NUMBER_OF_TRADES_CAN_GO_WRONG;
	
	public static float MAX_TRADE_AMOUNT_PER_MONTH = 30001.00F;
	
	static float MAX_AMOUNT_PER_TRADE = 5000.00F;
	
	static float MAX_RISK_PER_TRADE = 5000.00F;
	
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
