package com.trades.demo.controller;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.trades.demo.forms.DoubleScreenTradeForm;

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
		String filePath = "C:\\Users\\ashis\\Documents\\DSS-TradeLogs.csv";
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
			   			d.minRR,d.maxRR,d.tradeState};
	    
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
			String filePath = "C:\\Users\\ashis\\Documents\\DSS-TradeLogs.csv";
			
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
		    	
		    	listOfTrades.add(trade);
		    }
		    
		    model.addAttribute("listOfTrades", listOfTrades);
			
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
			File inputFile = new File("C:\\Users\\ashis\\Documents\\DSS-TradeLogs.csv");

			// Read existing file 
			CSVReader reader = new CSVReader(new FileReader(inputFile));
			List<String[]> csvBody = reader.readAll();
			// get CSV row column  and replace with by using row and column
			csvBody.get(Integer.parseInt(doubleScreenTradeForm.uid))[33] = doubleScreenTradeForm.tradeState;
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
}
