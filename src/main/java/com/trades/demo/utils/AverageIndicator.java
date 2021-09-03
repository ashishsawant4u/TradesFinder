package com.trades.demo.utils;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.trades.demo.indicators.ExponentialMovingAverage;
import com.trades.demo.indicators.SimpleMovingAverage;
import com.trades.demo.models.CandleModel;

public class AverageIndicator 
{
	public static List<Integer> SMA_PERIODS = Arrays.asList(44,55,200);
	
	public static List<Integer> EMA_PERIODS = Arrays.asList(20,50);
	
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
}
