package com.trades.demo.strategy;

import java.util.List;
import java.util.stream.Collectors;

import com.trades.demo.common.TradeStatus;
import com.trades.demo.models.CandleModel;
import com.trades.demo.models.TradeEntryModel;
import com.trades.demo.utils.CandlestickPattern;
import com.trades.demo.utils.DataHandler;

public class TradeEntryHandler extends AbstractTradeEntryHandler
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
		
		validateTradeAgainstMonthlyBudget(candle);
	}
	
	public static void setBuyEntryAsPerRecentSupport(CandleModel candle , List<CandleModel> symbolAllCandles)
	{
		
		List<CandleModel> prev15Candles = DataHandler.getPreviousCandles(candle, symbolAllCandles, 5);
		
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
	
	public static void setBuyEntryAsPerHigherMA(CandleModel candle,int supportLevel)
	{
		TradeEntryModel tradeEntry = new TradeEntryModel();
		
		tradeEntry.setEntry(true);
		
		double ENTRY_MARGIN = getEntryMargin(candle);
		
		double buyPrice = candle.high + ENTRY_MARGIN;
		
		double stopLossPrice = candle.getSma().get(supportLevel);
		
		double targetPrice = buyPrice + (buyPrice - stopLossPrice) * 1.5;
		
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
		
		validateTradeAgainstMonthlyBudget(candle);
	}
	
	/**
	 *  BUY above high
	 *  STOPLOSS = candle low - candle_height
	 * 	TARGET ==> BUY + [ (BUY-STOPLOSS)* 1.5 ]
	 * 
	 */
	public static void setBuyEntryAsPerStopLossatCandleHeight(CandleModel candle,double rewardRatio)
	{
		TradeEntryModel tradeEntry = new TradeEntryModel();
		
		tradeEntry.setEntry(true);
		
		double ENTRY_MARGIN = getEntryMargin(candle);
		
		double buyPrice = candle.high + ENTRY_MARGIN;
		
		double stopLossPrice = candle.low - CandlestickPattern.size(candle);
		
		double targetPrice = buyPrice + (buyPrice - stopLossPrice) * rewardRatio;
		
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
		
		validateTradeAgainstMonthlyBudget(candle);
	}
	
	public static void setBuyExit(CandleModel entryCandle, List<CandleModel> symbolAllCandles)
	{
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
				entryCandle.setTradeEntry(tradeEntry);
				placeSellOrder(entryCandle);
				break;
			}
			if(candle.low <= tradeEntry.stopLossPrice && tradeEntry.stopLossPrice <= candle.high)
			{
				tradeEntry.setExit(true);
				tradeEntry.setTradeStatus(TradeStatus.STOP_LOSS);
				tradeEntry.setTradeExitDate(candle.getMarketDateTime());
				tradeEntry.setTradeDuration(getNosDaysBetweenDates(entryCandle.getMarketDateTime(), candle.getMarketDateTime()));
				tradeEntry.setProfitAndLossAmount((tradeEntry.stopLossPrice - tradeEntry.buyPrice ) * tradeEntry.quantity );
				entryCandle.setTradeEntry(tradeEntry);
				placeSellOrder(entryCandle);
				break;
			}
		}
	}
	
	public static void setBuyExitAfterNextnDays(CandleModel entryCandle, List<CandleModel> symbolAllCandles,int existDays)
	{
		List<CandleModel>  candlesAfterEntry =  symbolAllCandles.stream().filter(c->c.getMarketDateTime().after(entryCandle.getMarketDateTime())).collect(Collectors.toList());
		
		TradeEntryModel tradeEntry = entryCandle.getTradeEntry();
		
		if(candlesAfterEntry.size() > existDays)
		{
			CandleModel existDayCandle = candlesAfterEntry.get(existDays);
			
			tradeEntry.setExit(true);
			
			TradeStatus tradeStatus = existDayCandle.high > tradeEntry.getBuyPrice() ? TradeStatus.TARGET_EXIT : TradeStatus.STOP_LOSS;
			
			tradeEntry.setTradeStatus(tradeStatus);
			tradeEntry.setTradeExitDate(existDayCandle.getMarketDateTime());
			tradeEntry.setTradeDuration(getNosDaysBetweenDates(entryCandle.getMarketDateTime(), existDayCandle.getMarketDateTime()));
			tradeEntry.setProfitAndLossAmount( (existDayCandle.high - tradeEntry.getBuyPrice())* tradeEntry.quantity );
			entryCandle.setTradeEntry(tradeEntry);
			placeSellOrder(entryCandle);
		}
		
	}
}
