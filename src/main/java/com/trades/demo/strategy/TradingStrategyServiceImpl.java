package com.trades.demo.strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.trades.demo.common.ShortListedStocks;
import com.trades.demo.common.TradeConstants;
import com.trades.demo.indicators.CommonIndicators;
import com.trades.demo.models.CandleModel;
import com.trades.demo.utils.DataHandler;

@Component("tradingStrategyService")
public class TradingStrategyServiceImpl implements TradingStrategyService 
{

	Logger logger = LoggerFactory.getLogger(TradingStrategyServiceImpl.class);

	/**
	 *  BUY IF
	 *  Candle taking support on SMA50
	 *  RISK:REWARD ratio ==> 1:1.5
	 *  BUY above high
	 *  STOPLOSS = candle low - candle_height
	 * 	TARGET ==> BUY + [ (BUY-STOPLOSS)* 1.5 ]
	 * 
	 */
	@Override
	public List<CandleModel> strategy1(Date fromDate, Date tillDate)
	{
		TradeConstants.ENABLE_QTY_PLANNER = true;
		
		List<CandleModel> candlesWithTrade = new ArrayList<CandleModel>();
		
		List<String> ALL_NSE_SYMBOLS = DataHandler.getAllNSESymbols();
		
		//ALL_NSE_SYMBOLS = Arrays.asList("BRIGADE");
		
		Predicate<String> isShortlisted = (s) -> { return ShortListedStocks.strategy1().contains(s); };
		ALL_NSE_SYMBOLS = ALL_NSE_SYMBOLS.stream().filter(isShortlisted).collect(Collectors.toList());
		
		int suppportMA = 50;
		double rewardRatio = 1.5;
		
		//AverageIndicator.SMA_PERIODS = Arrays.asList(56,84,112,140);
		
		for(String symbol :  ALL_NSE_SYMBOLS)
		{
			List<CandleModel> symbolCandles = DataHandler.getSymbolDailyEODCandles(symbol, fromDate, tillDate);
			
			List<CandleModel> candlesWithSMA50SupportAndGreen = symbolCandles.stream()
																	 .filter(c-> CommonIndicators.hasSupportForLong(c,suppportMA))
																	 .filter(c->CommonIndicators.isUptrendByMA(c, symbolCandles, "SMA", suppportMA, 60))
																	 .collect(Collectors.toList());

			
			Calendar cal = Calendar.getInstance();  
			cal.setTime(fromDate);
			cal.add(Calendar.YEAR, 4); 
			
			candlesWithSMA50SupportAndGreen = candlesWithSMA50SupportAndGreen.stream().filter(c->c.getMarketDateTime().after(cal.getTime())).collect(Collectors.toList());
			
			//candlesWithSMA50SupportAndGreen.stream().forEach(c->TradeEntryHandler.setBuyEntryAsPerRatio(c, 1.0, 2.0));
			//candlesWithSMA50SupportAndGreen.stream().forEach(c->TradeEntryHandler.setBuyEntryAsPerHigherMA(c, 75));
			candlesWithSMA50SupportAndGreen.stream().forEach(c->TradeEntryHandler.setBuyEntryAsPerStopLossatCandleHeight(c,rewardRatio));
			
			List<CandleModel> withingBudgetExecutedTrades = candlesWithSMA50SupportAndGreen.stream().filter(c->null!=c.getTradeEntry()&&c.getTradeEntry().isEntry()).collect(Collectors.toList());
			
			withingBudgetExecutedTrades.forEach(c-> TradeEntryHandler.setBuyExit(c,symbolCandles));
			
			candlesWithTrade.addAll(withingBudgetExecutedTrades);
			
			logger.info(symbol + " ...done");
		}
		
		return candlesWithTrade;
	}

}
