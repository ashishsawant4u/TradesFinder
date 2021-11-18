package com.trades.demo.bhavcopy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.opencsv.CSVWriter;
import com.trades.demo.models.CandleModel;
import com.trades.demo.utils.DataHandler;
import com.trades.demo.utils.IndicesConstants;
import com.trades.demo.utils.StockIndicesHelper;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

@Component("yahooFinanaceService")
public class YahooFinanaceServiceimpl implements YahooFinanaceService 
{
	Logger logger = LoggerFactory.getLogger(YahooFinanaceServiceimpl.class);
	
	public static String YAHOO_SYMBOL_DIR_LOC = "C:\\Users\\ashis\\Pictures\\nse_bhavcopy\\yahoo-symbols\\";
	
	@Override
	public String getHistoricalEodDataForNSE() 
	{	
		purgeEODDataDirectories();
		
		Calendar fromDate = Calendar.getInstance();  
		fromDate.setTime(new Date());
		fromDate.add(Calendar.YEAR, -30); 
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		
		List<String> allNSESymbols = StockIndicesHelper.getSymbolsList(IndicesConstants.ALL_NSE_EQ_SYMBOLS);
		
		//List<String> allNSESymbols = Arrays.asList("ACC");
		
		for(String symbol : allNSESymbols)
		{
			symbol = symbol.concat(".NS");
			
			try 
			{
				
				Stock stock = YahooFinance.get(symbol,fromDate,Interval.DAILY);
				
				if(null!=stock)
				{
					List<HistoricalQuote> historyCandles = stock.getHistory();
					
					if(CollectionUtils.isNotEmpty(historyCandles))
					{
						List<String[]> data = new ArrayList<String[]>();
						
						for(HistoricalQuote candle : historyCandles)
						{
							BigDecimal open = candle.getOpen();
							BigDecimal high = candle.getHigh();
							BigDecimal low = candle.getLow();
							BigDecimal close = candle.getClose();
							String marketDate = null;
							if(null!=candle.getDate())
							{
								marketDate =  dateFormat.format(candle.getDate().getTime());
							}
							
							long volume = 0L;
							if(null!=candle.getVolume())
							{
								volume = candle.getVolume();
							}
							
							if(null!=open && null!=high && null!=low && null!=close && null!=marketDate) 
							{
								 data.add(new String[] { symbol.replace(".NS", ""), open.toString(), high.toString(), low.toString(), close.toString(), marketDate, String.valueOf(volume) });
							}
						}
						createSymbolCSV(symbol.replace(".NS", ""), data);
						logger.info(symbol.replace(".NS", "")+"     ....done");
					}
				}
				else
				{
					logger.info(symbol.replace(".NS", "")+"     ....not found");
				}
				
			} 
			catch (Exception e) 
			{
				logger.info(e.getMessage());
			}
		}
		
		
		return "Data Fetched Succesfully.";
	}
	
	public static void createSymbolCSV(String symbol,List<String[]> data)
	{
		String filePath = YAHOO_SYMBOL_DIR_LOC+symbol+".csv";
	  
	    File file = new File(filePath);
	  
	    try {
	        
	        FileWriter outputfile = new FileWriter(file);
	  
	      
	        CSVWriter writer = new CSVWriter(outputfile);
	        writer.writeAll(data);
	        writer.close();
	    }
	    catch (IOException e) 
	    {
	        e.printStackTrace();
	    }
	}
	
	private void purgeEODDataDirectories() 
	{
		Arrays.stream(new File(YAHOO_SYMBOL_DIR_LOC).listFiles()).forEach(File::delete);
	}

	@Override
	public String updateHistoricalEodDataForNSE() 
	{
		List<CandleModel> accCandles = DataHandler.readSymbolDailyEODCandles("ACC");
		
		Calendar fromDate = Calendar.getInstance();  
		fromDate.setTime(accCandles.get(accCandles.size()-1).getMarketDateTime());
		fromDate.add(Calendar.DATE, 1);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		
		List<String> allNSESymbols = StockIndicesHelper.getSymbolsList(IndicesConstants.ALL_NSE_EQ_SYMBOLS);
		
		for(String symbol : allNSESymbols)
		{
			symbol = symbol.concat(".NS");
			
			try 
			{
				
				Stock stock = YahooFinance.get(symbol,fromDate,Interval.DAILY);
				
				if(null!=stock)
				{
					List<HistoricalQuote> historyCandles = stock.getHistory();
					
					if(CollectionUtils.isNotEmpty(historyCandles))
					{
						List<String[]> data = new ArrayList<String[]>();
						
						for(HistoricalQuote candle : historyCandles)
						{
							BigDecimal open = candle.getOpen();
							BigDecimal high = candle.getHigh();
							BigDecimal low = candle.getLow();
							BigDecimal close = candle.getClose();
							String marketDate = null;
							if(null!=candle.getDate())
							{
								marketDate =  dateFormat.format(candle.getDate().getTime());
							}
							
							long volume = 0L;
							if(null!=candle.getVolume())
							{
								volume = candle.getVolume();
							}
							
							if(null!=open && null!=high && null!=low && null!=close && null!=marketDate) 
							{
								 data.add(new String[] { symbol.replace(".NS", ""), open.toString(), high.toString(), low.toString(), close.toString(), marketDate, String.valueOf(volume) });
							}
						}
						updateSymbolCSV(symbol.replace(".NS", ""), data);
						logger.info(symbol.replace(".NS", "")+"     ....done");
					}
				}
				else
				{
					logger.info(symbol.replace(".NS", "")+"     ....not found");
				}
				
			} 
			catch (Exception e) 
			{
				logger.info(e.getMessage());
			}
		}
		
		return "Data Updated Succesfully.";
	}
	
	public static void updateSymbolCSV(String symbol,List<String[]> data)
	{
		String filePath = YAHOO_SYMBOL_DIR_LOC+symbol+".csv";
	  
	    File file = new File(filePath);
	  
	    try {
	        
	        FileWriter outputfile = new FileWriter(file,true);
	  
	        CSVWriter writer = new CSVWriter(outputfile);
	  
	        data.forEach(line ->{
	        	writer.writeNext(line);
	        });
	        writer.close();
	    }
	    catch (IOException e) 
	    {
	        e.printStackTrace();
	    }
	}

}
