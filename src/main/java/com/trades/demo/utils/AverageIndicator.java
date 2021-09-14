package com.trades.demo.utils;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.trades.demo.indicators.AverageTrueRange;
import com.trades.demo.indicators.ChandelierExit;
import com.trades.demo.indicators.ExponentialMovingAverage;
import com.trades.demo.indicators.SimpleMovingAverage;
import com.trades.demo.models.CandleModel;

public class AverageIndicator 
{
	public static List<Integer> SMA_PERIODS = Arrays.asList(50,75,100);
	
	public static List<Integer> EMA_PERIODS = Arrays.asList(20,50);

	public static List<Integer> ATR_PERIODS = Arrays.asList(22);
	
	public static List<CandleModel> simpleMovingAverage(List<CandleModel> candles)
	{	
		  List<CandleModel> candleWithSMA = candles;
		  
		  for(Integer sma : SMA_PERIODS)
		  {
			  candleWithSMA = new SimpleMovingAverage(sma).prepareSMA(candleWithSMA);
		  }
		  
		  candleWithSMA.forEach(candle ->{
			  
			  Map sortedMap = candle.getSma().entrySet().stream()
					    .sorted(Map.Entry.comparingByKey()) 			
					    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
					    (oldValue, newValue) -> oldValue, LinkedHashMap::new));
			  
			  candle.setSma(sortedMap);
			  
		  });
		  
		  return candleWithSMA;
	}
	
	
	public static List<CandleModel> exponentialMovingAverage(List<CandleModel> candles)
	{	  
		  List<CandleModel> candleWithEMA = candles;
		  
		  for(Integer ema : EMA_PERIODS)
		  {
			  candleWithEMA = new ExponentialMovingAverage(ema).calculateEmaValues(candleWithEMA);
		  }
		  
		  candleWithEMA.forEach(candle ->{
			  
			  Map sortedMap = candle.getEma().entrySet().stream()
					    .sorted(Map.Entry.comparingByKey()) 			
					    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
					    (oldValue, newValue) -> oldValue, LinkedHashMap::new));
			  
			  candle.setEma(sortedMap);
			  
		  });
		  
		  return candleWithEMA;
	}
	
	
	public static List<CandleModel> averageTrueRange(List<CandleModel> candles)
	{	  
		  List<CandleModel> candleWithAtr = candles;
		  
		  for(Integer atr : ATR_PERIODS)
		  {
			  candleWithAtr = new AverageTrueRange(atr).prepareATRUsingEMA(candleWithAtr);
		  }
		  
		  candleWithAtr.forEach(candle ->{
			  
			  Map sortedMap = candle.getAtr().entrySet().stream()
					    .sorted(Map.Entry.comparingByKey()) 			
					    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
					    (oldValue, newValue) -> oldValue, LinkedHashMap::new));
			  
			  candle.setAtr(sortedMap);
			  
		  });
		  
		  return candleWithAtr;
	}
	
	public static List<CandleModel> chandelierExitForBuy(List<CandleModel> candles)
	{	  
		  List<CandleModel> candleWithchandelierExitForBuy = candles;
		  
		  for(Integer atr : ATR_PERIODS)
		  {
			  candleWithchandelierExitForBuy = new ChandelierExit(atr).chandelierExitForBuy(candles);
		  }
		  
		  candleWithchandelierExitForBuy.forEach(candle ->{
			  
			  Map sortedMap = candle.getChandelierExitBuy().entrySet().stream()
					    .sorted(Map.Entry.comparingByKey()) 			
					    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
					    (oldValue, newValue) -> oldValue, LinkedHashMap::new));
			  
			  candle.setChandelierExitBuy(sortedMap);
			  
		  });
		  
		  return candleWithchandelierExitForBuy;
	}
}
