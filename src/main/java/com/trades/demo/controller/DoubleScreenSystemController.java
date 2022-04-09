package com.trades.demo.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.trades.demo.forms.DoubleScreenTradeForm;
import com.trades.demo.utils.URLConstants;

@Controller
@RequestMapping("/doublescreen")
public class DoubleScreenSystemController 
{
	Logger logger = LoggerFactory.getLogger(DoubleScreenSystemController.class);
	
	
	@RequestMapping("/plan")
	public String getLandingPage(Model model)
	{
		return "doubleScreenSystemPage";
	}
	
	@RequestMapping(value = "/tradelog" , method = RequestMethod.POST)
	public @ResponseBody String dssTradeLog(@RequestBody DoubleScreenTradeForm doubleScreenTradeForm)
	{
		logger.info("doubleScreenTradeForm "+doubleScreenTradeForm);
		
		dssTradeLogHandler(doubleScreenTradeForm);
		
		return "OK";
	}
	
	public static void dssTradeLogHandler(DoubleScreenTradeForm d)
	{
		String filePath = URLConstants.DSS_TRADE_LOG_CSV_FILE;
		try 
		{
		    
			FileReader filereader = new FileReader(filePath);
			
		    CSVReader csvReader = new CSVReaderBuilder(filereader)
	                .withSkipLines(1)
	                .build();
		    List<String[]> allData = csvReader.readAll();
		    
		    int rowCount = allData.size();
		    
		    allData.removeAll(allData);
	  
		    String data [] = {String.valueOf(rowCount+1),d.stock,d.date,d.tide,d.wave,d.dssDecision,d.candleStickpattern,d.volume,d.ema,d.chartpattern,d.fibRetracement,d.divergence,
			   			d.immediateSupportForStopLoss,d.majorResistanceForTarget,d.immediateResistanceForStopLoss,d.majorSupportForTarget,
			   			d.closePrice,d.entryPrice,d.stopLossPrice,d.minTargetPrice,d.maxTargetPrice,d.quantity,d.tradeInvestment,d.minReward,d.maxReward,
			   			d.riskPerUnit,d.totalRisk,d.minProfitPotential,d.maxProfitPotential,d.minROI,d.maxROI,
			   			d.minRR,d.maxRR,d.tradeState,d.tradeComment,d.tradeSetupDetails,d.tradingStyle,d.papaTradeSetup,d.futureOptionsOIDetails,d.whatIfAnalysis};
	    
	   		File file = new File(filePath);
	        
	        FileWriter outputfile = new FileWriter(file,true);
	  
	        CSVWriter writer = new CSVWriter(outputfile);
			
	        writer.writeNext(data);
			 
	        writer.close();
	    }
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    }
	}
	
	@RequestMapping("/trades")
	public String getTrades(Model model)
	{
		try 
		{
			String filePath = URLConstants.DSS_TRADE_LOG_CSV_FILE;
			
			FileReader filereader = new FileReader(filePath);
			
		    CSVReader csvReader = new CSVReaderBuilder(filereader)
	                .withSkipLines(1)
	                .build();
		    List<String[]> allData = csvReader.readAll();
		    
		    List<DoubleScreenTradeForm> listOfTrades = new ArrayList<DoubleScreenTradeForm>();
		    
		    for(String [] row :allData)
		    {
		    	DoubleScreenTradeForm trade = new DoubleScreenTradeForm();
		    	trade.setUid(row[0]);
		    	trade.setStock(row[1]);
		    	trade.setDate(row[2]);
		    	trade.setTide(row[3]);
		    	trade.setWave(row[4]);
		    	trade.setDssDecision(row[5]);
		    	trade.setCandleStickpattern(row[6]);
		    	trade.setVolume(row[7]);
		    	trade.setEma(row[8]);
		    	trade.setChartpattern(row[9]);
		    	trade.setFibRetracement(row[10]);
		    	trade.setDivergence(row[11]);
		    	trade.setImmediateSupportForStopLoss(row[12]);
		    	trade.setMajorResistanceForTarget(row[13]);
		    	trade.setImmediateResistanceForStopLoss(row[14]);
		    	trade.setMajorSupportForTarget(row[15]);
		    	trade.setClosePrice(row[16]);
		    	trade.setEntryPrice(row[17]);
		    	trade.setStopLossPrice(row[18]);
		    	trade.setMinTargetPrice(row[19]);
		    	trade.setMaxTargetPrice(row[20]);
		    	trade.setQuantity(row[21]);
		    	trade.setTradeInvestment(row[22]);
		    	trade.setMinReward(row[23]);
		    	trade.setMaxReward(row[24]);
		    	trade.setRiskPerUnit(row[25]);
		    	trade.setTotalRisk(row[26]);
		    	trade.setMinProfitPotential(row[27]);
		    	trade.setMaxProfitPotential(row[28]);
		    	trade.setMinROI(row[29]);
		    	trade.setMaxROI(row[30]);
		    	trade.setMinRR(row[31]);
		    	trade.setMaxRR(row[32]);
		    	trade.setTradeState(row[33]);
		    	trade.setTradeComment(""!=row[34] ? row[34] : "NA");
		    	trade.setTradeSetupDetails(row[35]);
		    	trade.setTradingStyle(row[36]);
		    	trade.setPapaTradeSetup(row[37]);
		    	trade.setFutureOptionsOIDetails(row[38]);
		    	trade.setWhatIfAnalysis(row[39]);
		    	listOfTrades.add(trade);
		    }
		    
		    model.addAttribute("listOfTrades", listOfTrades.stream().filter(t->!t.getTradeState().equals("Ignore")).collect(Collectors.toList()));
			
		} catch (Exception e) 
		{
			 e.printStackTrace();
		}
		
		return "dssTradesPage";
	}
	
	@RequestMapping(value = "/updateTradeState" , method = RequestMethod.POST)
	public @ResponseBody String updateTradeState(@RequestBody DoubleScreenTradeForm doubleScreenTradeForm)
	{
		logger.info("doubleScreenTradeForm "+doubleScreenTradeForm);
		
		try
		{
			File inputFile = new File(URLConstants.DSS_TRADE_LOG_CSV_FILE);

			// Read existing file 
			CSVReader reader = new CSVReader(new FileReader(inputFile));
			List<String[]> csvBody = reader.readAll();
			// get CSV row column  and replace with by using row and column
			csvBody.get(Integer.parseInt(doubleScreenTradeForm.uid))[33] = doubleScreenTradeForm.tradeState;
			csvBody.get(Integer.parseInt(doubleScreenTradeForm.uid))[34] = doubleScreenTradeForm.tradeComment;
			reader.close();

			// Write to CSV file which is open
			CSVWriter writer = new CSVWriter(new FileWriter(inputFile));
			writer.writeAll(csvBody);
			writer.flush();
			writer.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return "OK";
	}
	

	@RequestMapping(value = "/papaGuide")
	public @ResponseBody Map<String,String> papaGuide(@RequestParam(name="candleStickpattern",required = false)String candleStickpattern,
			@RequestParam(name="chartpattern",required = false)String chartpattern)
	{
		logger.info("papaGuide candleStickpattern "+candleStickpattern);
		logger.info("papaGuide chartpattern "+chartpattern);
		
		 Map<String,String> papaMap = new HashMap<String, String>();
		
		Map<String, String> studyMap = new HashMap();
		studyMap.put("Bullish Green Candle", "Bullish Green Candle");
		studyMap.put("Bullish Piercing", "Bullish Piercing,Hammer");
		studyMap.put("Bullish Engulf", "Bullish Engulf,Hammer");
		studyMap.put("Morning Star", "Morning Star,Hammer");
		studyMap.put("Hammer", "Hammer");
		studyMap.put("Bearish Red Candle", "Bearish Red Candle");
		studyMap.put("Bearish Piercing", "Bearish Piercing,Inverted Hammer");
		studyMap.put("Bearish Engulf", "Bearish Engulf,Inverted Hammer");
		studyMap.put("Inverted Hammer", "Inverted Hammer");
		studyMap.put("Evening Star", "Evening Star,Inverted Hammer");
		studyMap.put("Cup And Handle", "Cup And Handle");
		studyMap.put("Breakout Of Flag", "Flag,Genuine Breakout,Fake Breakout,Shakeout");
		studyMap.put("Fake Breakdown", "Fake Breakdown,Genuine Breakdown,Shakeout");
		studyMap.put("Breakdown Of Flag", "Flag,Genuine Breakdown,Fake Breakdown,Shakeout");
		studyMap.put("Fake Breakout", "Fake Breakout,Genuine Breakout,Shakeout");
		studyMap.put("Dark Cloud Cover", "Dark Cloud Cover");
		studyMap.put("Tweezers", "Tweezers");
		studyMap.put("High Wave", "High Wave");
		studyMap.put("Doji", "Doji");
		
		try
		{
			String concepts = studyMap.get(candleStickpattern);
			concepts.concat(",").concat(studyMap.get(chartpattern));
			
			List<String> conceptList = new ArrayList<String>(Arrays.asList(concepts.split(" , ")));
			
			String filePath = URLConstants.PAPA_GUIDE_CSV_FILE;
			
			BufferedReader br = new BufferedReader(new FileReader(filePath));  
			String line = "";  
			while ((line = br.readLine()) != null)   //returns a Boolean value  
			{
				String[] row = line.split(",");  
				if(conceptList.contains(row[0]))
		    	{
		    		papaMap.put(row[0], row[1]);
		    	}
			}  
			
		}
		catch (Exception e) 
		{
			 e.printStackTrace();
		}
		
		return papaMap;
	}
}
