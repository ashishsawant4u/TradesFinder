package com.trades.demo.strategy;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.trades.demo.common.TradeStatus;
import com.trades.demo.models.CandleModel;
import com.trades.demo.models.TradeEntryModel;
import com.trades.demo.utils.DataHandler;

public class TradeEntryHandler 
{
	public static void setBuyEntryAsPerRatio(CandleModel candle,double RISK_RATIO,double REWARD_RATIO)
	{
		TradeEntryModel tradeEntry = new TradeEntryModel();
		
		tradeEntry.setEntry(true);
		
		double ENTRY_MARGIN = getEntryMargin(candle);
		
		double buyPrice = candle.high + ENTRY_MARGIN;
		
		double stopLossPrice = candle.low - RISK_RATIO;
		
		double targetPrice = buyPrice + (buyPrice - stopLossPrice) * REWARD_RATIO;
		
		double lossPerUnit = buyPrice - stopLossPrice;

		tradeEntry.setBuyPrice(buyPrice);
		tradeEntry.setStopLossPrice(stopLossPrice);
		tradeEntry.setTargetPrice(targetPrice);
		tradeEntry.setLossPerUnit(lossPerUnit);
		tradeEntry.setTradeStatus(TradeStatus.OPEN);
		candle.setTradeEntry(tradeEntry);
		
		int quantity = QuantityPlanner.getQuantity(candle);
		
		tradeEntry.setQuantity(quantity);

		tradeEntry.setInvestment(tradeEntry.getQuantity()*buyPrice);
		
		candle.setTradeEntry(tradeEntry);
	}
	
	public static void setBuyEntryAsPerRecentSupport(CandleModel candle , List<CandleModel> symbolAllCandles)
	{
		
		List<CandleModel> prev15Candles = DataHandler.getPreviousCandles(candle, symbolAllCandles, 15);
		
		double lowestOfPrev15Candles = prev15Candles.stream().mapToDouble(c->c.getLow()).min().getAsDouble();
		
		TradeEntryModel tradeEntry = new TradeEntryModel();
		
		tradeEntry.setEntry(true);
		
		double ENTRY_MARGIN = getEntryMargin(candle);
		
		double buyPrice = candle.high + ENTRY_MARGIN;
		
		double stopLossPrice = lowestOfPrev15Candles;
		
		double targetPrice = buyPrice + (buyPrice - stopLossPrice) * 2;
		
		double lossPerUnit = buyPrice - stopLossPrice;

		tradeEntry.setBuyPrice(buyPrice);
		tradeEntry.setStopLossPrice(stopLossPrice);
		tradeEntry.setTargetPrice(targetPrice);
		tradeEntry.setLossPerUnit(lossPerUnit);
		tradeEntry.setTradeStatus(TradeStatus.OPEN);
		candle.setTradeEntry(tradeEntry);
		
		int quantity = QuantityPlanner.getQuantity(candle);
		
		tradeEntry.setQuantity(quantity);

		tradeEntry.setInvestment(tradeEntry.getQuantity()*buyPrice);
		
		candle.setTradeEntry(tradeEntry);
	}
	
	public static void setBuyExit(CandleModel entryCandle, List<CandleModel> symbolAllCandles)
	{
		//List<CandleModel>  candlesAfterEntry = DataHandler.getSymbolDailyEODCandles(entryCandle.getSymbol(), entryCandle.getMarketDateTime(), new Date());
		
		List<CandleModel>  candlesAfterEntry =  symbolAllCandles.stream().filter(c->c.getMarketDateTime().after(entryCandle.getMarketDateTime())).collect(Collectors.toList());
		
		TradeEntryModel tradeEntry = entryCandle.getTradeEntry();
		
		for(CandleModel candle : candlesAfterEntry)
		{
			if(candle.low <= tradeEntry.targetPrice && tradeEntry.targetPrice <= candle.high)
			{
				tradeEntry.setExit(true);
				tradeEntry.setTradeStatus(TradeStatus.TARGET_EXIT);
				tradeEntry.setTradeExitDate(candle.getMarketDateTime());
				tradeEntry.setTradeDuration(getNosDaysBetweenDates(entryCandle.getMarketDateTime(), candle.getMarketDateTime()));
				tradeEntry.setProfitAndLossAmount((tradeEntry.targetPrice - tradeEntry.buyPrice) * tradeEntry.quantity);
				break;
			}
			if(candle.low <= tradeEntry.stopLossPrice && tradeEntry.stopLossPrice <= candle.high)
			{
				tradeEntry.setExit(true);
				tradeEntry.setTradeStatus(TradeStatus.STOP_LOSS);
				tradeEntry.setTradeExitDate(candle.getMarketDateTime());
				tradeEntry.setTradeDuration(getNosDaysBetweenDates(entryCandle.getMarketDateTime(), candle.getMarketDateTime()));
				tradeEntry.setProfitAndLossAmount((tradeEntry.stopLossPrice - tradeEntry.buyPrice ) * tradeEntry.quantity );
				break;
			}
			
			entryCandle.setTradeEntry(tradeEntry);
			
		}
	}
	
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
	
}
