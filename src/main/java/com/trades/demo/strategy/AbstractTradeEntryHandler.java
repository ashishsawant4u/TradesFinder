package com.trades.demo.strategy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.trades.demo.common.TradeConstants;
import com.trades.demo.common.TradeStatus;
import com.trades.demo.models.CandleModel;
import com.trades.demo.models.TradeEntryModel;
import com.trades.demo.reports.Order;
import com.trades.demo.reports.OrderTracker;

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
			
			placeBuyOrder(candle);
		}
		else
		{	
			if(null==QuantityPlanner.monthlyTradeAmountTrackerMap.get(month) 
				|| (QuantityPlanner.monthlyTradeAmountTrackerMap.get(month)+candle.getTradeEntry().getInvestment()) < QuantityPlanner.MAX_TRADE_AMOUNT_PER_MONTH
				   )	
			{
				candle.setTradeEntry(tradeEntry);
				QuantityPlanner.trackTradeAmountForMonth(candle);
				placeBuyOrder(candle);
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

	public static void placeBuyOrder(CandleModel candle) 
	{
		if(candle.getTradeEntry().getQuantity()>0)
		{
			Order order = new Order();
			order.setSymbol(candle.getSymbol());
			order.setOrderType(TradeConstants.ORDER_TYPE_BUY);
			order.setMarketDateTime(candle.getMarketDateTime());
			order.setBuyPrice(candle.getTradeEntry().getBuyPrice());
			order.setQuantity(candle.getTradeEntry().getQuantity());
			order.setOrderAmount(candle.getTradeEntry().getInvestment());
			OrderTracker.ORDER_LIST.add(order);
		}
	}
	
	public static void placeSellOrder(CandleModel candle) 
	{
		if(candle.getTradeEntry().getQuantity()>0)
		{
			Order order = new Order();
			order.setSymbol(candle.getSymbol());
			order.setOrderType(TradeConstants.ORDER_TYPE_SELL);
			order.setMarketDateTime(candle.getTradeEntry().getTradeExitDate());
			order.setSellPrice(candle.getTradeEntry().getTargetPrice());
			order.setQuantity(candle.getTradeEntry().getQuantity());
			if(candle.getTradeEntry().getTradeStatus().equals(TradeStatus.TARGET_EXIT))
			{
				order.setOrderAmount(candle.getTradeEntry().getTargetPrice() * candle.getTradeEntry().getQuantity());
			}
			
			if(candle.getTradeEntry().getTradeStatus().equals(TradeStatus.STOP_LOSS))
			{
				order.setOrderAmount(candle.getTradeEntry().getStopLossPrice() * candle.getTradeEntry().getQuantity());
			}
			
			order.setExitType(candle.getTradeEntry().getTradeStatus().toString());
			
			OrderTracker.ORDER_LIST.add(order);
		}
	}
}
