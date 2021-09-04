package com.trades.demo.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trades.demo.common.TradeConstants;
import com.trades.demo.indicators.ExponentialMovingAverage;
import com.trades.demo.indicators.SimpleMovingAverage;
import com.trades.demo.models.CandleModel;

public class DataHandler 
{
	static Logger logger = LoggerFactory.getLogger(DataHandler.class);
	
	public static String DAILY_EOD_SYMBOL_CSV_LOCATION = "C:\\Users\\ashis\\Downloads\\archive\\Datasets\\SCRIP\\data-symbol\\";
	public static String COMMA_DELIMITER = ",";

	
	public static List<CandleModel> getSymbolDailyEODCandles(String instrument,Date from,Date till)
	{
		 File[] files = new File(DAILY_EOD_SYMBOL_CSV_LOCATION).listFiles();
		  
		  for (File file : files)
		  { 
			  if (file.isFile()) 
			  {
				  String symbol = file.getName().replace(".csv", "");
				
				  boolean process = (null==instrument || instrument.equalsIgnoreCase("default") || symbol.equalsIgnoreCase(instrument)) ? true  : false;
				  
				  if(process)
				  {
					  List<CandleModel> candleList = readSymbolDailyEODCandles(symbol);
					  
					  List<CandleModel> candleListWithDateRange = candleList.stream().filter(c->!c.getMarketDateTime().before(from) &&  !c.getMarketDateTime().after(till)).collect(Collectors.toList());
				  
					  List<CandleModel> candleWithSMA = AverageIndicator.simpleMovingAverage(candleListWithDateRange);
					  
					  List<CandleModel> candleWithEMA = AverageIndicator.exponentialMovingAverage(candleWithSMA);
					  
					  TradeConstants.ALL_CANDLES = candleWithEMA;
					  
					  candleWithEMA.forEach(c-> c.setCandlePattern(CandlestickPattern.findCandlePattern(c)));
					  
					  return candleWithEMA;
				  }
			  }
		  }
		  
		  return null;
	}
	
	
	
	public static List<CandleModel> readSymbolDailyEODCandles(String  symbol)
	{
		List<CandleModel> candleList = new ArrayList<CandleModel>();
		
		
		int count = 0;
		try 
		{
			
			BufferedReader br = new BufferedReader(new FileReader(DAILY_EOD_SYMBOL_CSV_LOCATION+symbol+".csv"));
			    String line;
			    while ((line = br.readLine()) != null) {
			    	
			    	if(count != 0)
			    	{
			    		 	String[] lineData = line.split(COMMA_DELIMITER);
			    		 	
			    		 	Date marketDate = new SimpleDateFormat("dd-MMM-yyyy").parse(lineData[5].replace("\"", ""));  
				            
			    		 	CandleModel candle = new CandleModel();
			            	candle.setSymbol(symbol);
			            	candle.setMarketDateTime(marketDate);
			            	candle.setOpen(Double.parseDouble(lineData[1].replace("\"", "")));
			            	candle.setHigh(Double.parseDouble(lineData[2].replace("\"", "")));
			            	candle.setLow(Double.parseDouble(lineData[3].replace("\"", "")));
			            	candle.setClose(Double.parseDouble(lineData[4].replace("\"", "")));
			            	
			            	candleList.add(candle);	
			    	}
			       
			    	count++;
			        
			    }
			
			
		} 
		catch (Exception e) {
			logger.info("Exception while reading CSV "+e);
		}
		
		Collections.sort(candleList, (c1, c2) -> c1.getMarketDateTime().compareTo(c2.getMarketDateTime()));
		
		return candleList;
	}
	
	public static List<CandleModel> getPreviousCandles(CandleModel currentCandle,List<CandleModel> allCandles,int period)
	{
		List<CandleModel> filterdList = allCandles.stream().filter(c->!c.getMarketDateTime().after(currentCandle.getMarketDateTime())).collect(Collectors.toList());
	
		if(filterdList.size() < period)
		{
			return filterdList;
		}
		
		return filterdList.subList(filterdList.size() - period , filterdList.size()-1);
	}
	
	public static List<String> getAllNSESymbols()
	{
		File[] files = new File(DAILY_EOD_SYMBOL_CSV_LOCATION).listFiles();
		  
		return Arrays.asList(files).stream().filter(f->f.isFile()).map(f -> f.getName().replace(".csv", "")).collect(Collectors.toList());
	}
	
}
