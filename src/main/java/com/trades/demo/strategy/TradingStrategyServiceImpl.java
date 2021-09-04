package com.trades.demo.strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.trades.demo.indicators.CommonIndicators;
import com.trades.demo.models.CandleModel;
import com.trades.demo.utils.CandlestickPattern;
import com.trades.demo.utils.DataHandler;

@Component("tradingStrategyService")
public class TradingStrategyServiceImpl implements TradingStrategyService 
{

	Logger logger = LoggerFactory.getLogger(TradingStrategyServiceImpl.class);
	
	/**
	 *  BUY IF
	 *  Candle taking support on EMA50
	 *  RISK:REWARD ratio ==> 1:2
	 *  BUY above high
	 *  STOPLOSS below low
	 * 	TARGET ==> BUY + [ (BUY-STOPLOSS)*2 ]
	 * 
	 */
	@Override
	public List<CandleModel> strategy1(Date fromDate, Date tillDate) 
	{
		List<CandleModel> candlesWithTrade = new ArrayList<CandleModel>();
		
		List<String> ALL_NSE_SYMBOLS = DataHandler.getAllNSESymbols();
		
		//ALL_NSE_SYMBOLS = Arrays.asList("ACC","WIPRO");
		
		for(String symbol :  ALL_NSE_SYMBOLS)
		{
			List<CandleModel> symbolCandles = DataHandler.getSymbolDailyEODCandles(symbol, fromDate, tillDate);
			
			List<CandleModel> candlesWithEMA50SupportAndGreen = symbolCandles.stream()
																	 .filter(c-> CommonIndicators.hasSupport(c, c.getEma().get(50)))
																	 .filter(c->CandlestickPattern.GREEN.test(c)).collect(Collectors.toList());
			
			//candlesWithEMA50SupportAndGreen.stream().forEach(c-> TradeEntryHandler.setBuyEntryAsPerRatio(c, 1.0, 2.0));
			
			candlesWithEMA50SupportAndGreen.stream().forEach(c->{
				TradeEntryHandler.setBuyEntryAsPerRecentSupport(c, symbolCandles);
			});
			
			candlesWithEMA50SupportAndGreen.stream().forEach(c-> TradeEntryHandler.setBuyExit(c,symbolCandles));
			
			candlesWithTrade.addAll(candlesWithEMA50SupportAndGreen);
			
			logger.info(symbol + " ...done");
		}
		
		return candlesWithTrade;
	}

}
