package com.trades.demo.strategy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.trades.demo.common.TradeConstants;
import com.trades.demo.models.CandleModel;
import com.trades.demo.models.TradeEntryModel;

public class AbstractTradeEntryHandler 
{	
	public static float getEntryMargin(CandleModel  candle)
	{
		float entryMargin = 0.0F;
		
		if(candle.open <=400)
		{
			entryMargin = 0.5F;
		}
		else
		{
			entryMargin = 1.0F;
		}
		
		return entryMargin;
	}
	
	public static int getNosDaysBetweenDates(Date entryDate, Date existDate) 
	{
	    long diff = existDate.getTime() - entryDate.getTime();
	    return (int)TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	public static void validateTradeAgainstMonthlyBudget(CandleModel candle) 
	{	
		TradeEntryModel tradeEntry = candle.getTradeEntry();
		
		SimpleDateFormat sdf = new SimpleDateFormat("MMM-yyyy");
		String month =  sdf.format(candle.getMarketDateTime());
		
		if(!TradeConstants.ENABLE_QTY_PLANNER )
		{
			candle.setTradeEntry(tradeEntry);
			QuantityPlanner.trackTradeAmountForMonth(candle);
		}
		else
		{	
			if(null==QuantityPlanner.monthlyTradeAmountTrackerMap.get(month) 
				|| (QuantityPlanner.monthlyTradeAmountTrackerMap.get(month)+candle.getTradeEntry().getInvestment()) < QuantityPlanner.MAX_TRADE_AMOUNT_PER_MONTH
				   )	
			{
				candle.setTradeEntry(tradeEntry);
				QuantityPlanner.trackTradeAmountForMonth(candle);
			}
			else
			{
				candle.setTradeEntry(null);
			}
			
			if(tradeEntry.getQuantity()==0)
			{
				candle.setTradeEntry(null);
			}
		}
	}
}
