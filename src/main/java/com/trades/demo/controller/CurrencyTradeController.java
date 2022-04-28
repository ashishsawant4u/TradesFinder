package com.trades.demo.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.util.Precision;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.trades.demo.forms.CurrencyCalculatorForm;
import com.trades.demo.forms.CurrencyTradeDetails;
import com.trades.demo.models.CurrencyPairModel;
import com.trades.demo.models.PerformanceStats;
import com.trades.demo.utils.CurrencyPairMaster;
import com.trades.demo.utils.PerformanceTracker;
import com.trades.demo.utils.TradeCapitalPlan;
import com.trades.demo.utils.URLConstants;

@Controller
@RequestMapping("/currency")
public class CurrencyTradeController 
{
	Logger logger = LoggerFactory.getLogger(CurrencyTradeController.class);

	@RequestMapping("/calculator")
	public String currencyCalculatorLandingPage(Model model)
	{
		model.addAttribute("capitalData", new TradeCapitalPlan());
		
		
		return "currencyCalculatorPage";
	}
	
	@RequestMapping("/capitalrisk/{capital}/{risk}")
	@ResponseBody
	public TradeCapitalPlan calculateCapitalRisk(@PathVariable("capital") String capital,@PathVariable("risk")String risk)
	{
		TradeCapitalPlan capitalPlan = new TradeCapitalPlan();
		capitalPlan.setCapital(Double.parseDouble(capital));
		capitalPlan.setRiskPercentage(Double.parseDouble(risk));
		return capitalPlan;
	}
	
	@RequestMapping("/currencyPairInfo/{pairname}")
	@ResponseBody
	public CurrencyPairModel currencyPairInfo(@PathVariable("pairname") String pairname)
	{
		return CurrencyPairMaster.getCurrencyPairInfo(pairname);
	}
	
	@RequestMapping(value="/calculateCurrencyTrade",method = RequestMethod.POST)
	@ResponseBody
	public CurrencyCalculatorForm calculateCurrencyTrade(@RequestBody CurrencyCalculatorForm form)
	{
		logger.info(form.getInstrument());
		
		if(form.getTradeDecision().equalsIgnoreCase("buy"))
		{
			currencyLongCalculation(form);
		}
		else
		{
			currencyShortCalculation(form);
		}
		
		return form;
	}
	
	@RequestMapping(value = "/saveTrade" , method = RequestMethod.POST)
	public @ResponseBody String saveTrade(@RequestBody CurrencyTradeDetails currencyTradeDetails)
	{
		logger.info("currencyTradeDetails "+currencyTradeDetails);
		
		currencyTradeDetailsHandler(currencyTradeDetails);
		
		return "OK";
	}
	
	@RequestMapping("/trades")
	public String getTrades(Model model)
	{
		return "currencyTradesPage";
	}
	
	@RequestMapping("/performanceStats")
	@ResponseBody
	public PerformanceStats getPerformanceStats() throws Exception
	{
		return PerformanceTracker.getPerformanceStats(readCurrencyTradesCSV());
	}
	
	
	@RequestMapping("/getTradeSummary")
	@ResponseBody
	public Map<String, List<CurrencyTradeDetails>> getTradeSummary()
	{
		try 
		{
			List<CurrencyTradeDetails> listOfTrades = readCurrencyTradesCSV();
			
			Collections.reverse(listOfTrades);
		    
		    //model.addAttribute("listOfTrades", listOfTrades.stream().filter(t->!t.getTradeState().equals("Ignore")).collect(Collectors.toList()));
			
		    Map<String, List<CurrencyTradeDetails>> data = new HashMap<String, List<CurrencyTradeDetails>>();
		    
		    data.put("data", listOfTrades);
		    
		    return data;
		    //return listOfTrades.stream().filter(t->!t.getTradeState().equals("Ignore")).collect(Collectors.toList());
		} 
		catch (Exception e) 
		{
			 e.printStackTrace();
			 return null;
		}
		
	}

	private List<CurrencyTradeDetails> readCurrencyTradesCSV() throws FileNotFoundException, IOException, CsvException 
	{
		String filePath = URLConstants.CURRRENCY_TRADE_LOG_CSV_FILE;
		
		FileReader filereader = new FileReader(filePath);
		
		CSVReader csvReader = new CSVReaderBuilder(filereader)
		        .withSkipLines(1)
		        .build();
		List<String[]> allData = csvReader.readAll();
		
		List<CurrencyTradeDetails> listOfTrades = new ArrayList<CurrencyTradeDetails>();
		
		for(String [] row :allData)
		{
			CurrencyTradeDetails trade = new CurrencyTradeDetails();
			trade.setUid(row[0]);
			CurrencyCalculatorForm calc = new CurrencyCalculatorForm();
			calc.setInstrument(row[1]);
			trade.setDate(convDate(row[2]));
			calc.setTradeDecision(row[3]);
			trade.setStoryAndViewBuilding(row[4]);
			trade.setTradeBias(row[5]);
			trade.setOpportunity(row[6]);
			trade.setWhatIfAnalysis(row[7]);
			trade.setConfirmation(row[8]);
			trade.setExecuted(Boolean.parseBoolean(row[9]));
			trade.setOutcome(row[10]);
			trade.setLearnings(row[11]);
			calc.setRiskPerTrade(Double.parseDouble(row[12]));
			calc.setPipSize(Double.parseDouble(row[13]));
			calc.setLotSize(Double.parseDouble(row[14]));
			calc.setEntry(Double.parseDouble(row[15]));
			calc.setStopLoss(Double.parseDouble(row[16]));
			calc.setTarget(Double.parseDouble(row[17]));
			calc.setNumberOfLots(Integer.parseInt(row[18]));
			calc.setTotalAmountForAllLots(Double.parseDouble(row[19]));
			calc.setMarginAmountForAllLots(Double.parseDouble(row[20]));
			calc.setRewardRatio(Double.parseDouble(row[21]));
			calc.setTargetPriceMovement(Double.parseDouble(row[22]));
			calc.setTargetPipMovenment(Double.parseDouble(row[23]));
			calc.setMaxProfitPerLot(Double.parseDouble(row[24]));
			calc.setMaxProfitAllLots(Double.parseDouble(row[25]));
			calc.setPnlPercentageAsPerAllLotsForTarget(Double.parseDouble(row[26]));
			calc.setSlPriceMovement(Double.parseDouble(row[27]));
			calc.setSlPipMovenment(Double.parseDouble(row[28]));
			calc.setMaxLossPerLot(Double.parseDouble(row[29]));
			calc.setMaxLossAllLots(Double.parseDouble(row[30]));
			calc.setPnlPercentageAsPerAllLotsForSL(Double.parseDouble(row[31]));
			trade.setTradeState(row[32]);
			trade.setTradeDuration(StringUtils.isNotBlank(row[33]) ? Integer.parseInt(row[33]) : 0);
			trade.setChartImageUrl(row[34]);
			trade.setComment(row[35]);
			
			calc.setActualRewardRatio(StringUtils.isNotBlank(row[36]) ?   Double.parseDouble(row[36]) : 0);
			calc.setActualPnL(StringUtils.isNotBlank(row[37]) ?   Double.parseDouble(row[37]) : 0);
			trade.setCalculations(calc);		    	
			listOfTrades.add(trade);
		}
		
		
		return listOfTrades.stream().filter(t->!t.getTradeState().equals("Ignore")).collect(Collectors.toList());
	}
	
	@RequestMapping("/getTradeDetails/{tradeid}")
	@ResponseBody
	public CurrencyTradeDetails getTradeDetails(@PathVariable("tradeid") String tradeid) throws Exception
	{
		List<CurrencyTradeDetails> listOfTrades = readCurrencyTradesCSV();
		
	    return listOfTrades.stream().filter(t->t.getUid().equals(tradeid)).findFirst().get();
	}
	
	@RequestMapping(value = "/updateTrade" , method = RequestMethod.POST)
	public @ResponseBody String updateTrade(@RequestBody CurrencyTradeDetails currencyTradeDetails)
	{
		logger.info("currencyTradeDetails "+currencyTradeDetails);
		
		try
		{
			File inputFile = new File(URLConstants.CURRRENCY_TRADE_LOG_CSV_FILE);

			// Read existing file 
			CSVReader reader = new CSVReader(new FileReader(inputFile));
			List<String[]> csvBody = reader.readAll();
			// get CSV row column  and replace with by using row and column
			csvBody.get(Integer.parseInt(currencyTradeDetails.getUid()))[8] = currencyTradeDetails.getConfirmation();
			csvBody.get(Integer.parseInt(currencyTradeDetails.getUid()))[11] = currencyTradeDetails.getLearnings();
			csvBody.get(Integer.parseInt(currencyTradeDetails.getUid()))[32] = currencyTradeDetails.getTradeState();
			csvBody.get(Integer.parseInt(currencyTradeDetails.getUid()))[33] = String.valueOf(currencyTradeDetails.getTradeDuration());
			csvBody.get(Integer.parseInt(currencyTradeDetails.getUid()))[35] = currencyTradeDetails.getComment();
			csvBody.get(Integer.parseInt(currencyTradeDetails.getUid()))[36] = String.valueOf(currencyTradeDetails.getCalculations().getActualRewardRatio());
			csvBody.get(Integer.parseInt(currencyTradeDetails.getUid()))[37] = String.valueOf(currencyTradeDetails.getCalculations().getActualPnL());
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
	
	public void currencyTradeDetailsHandler(CurrencyTradeDetails d)
	{
		String filePath = URLConstants.CURRRENCY_TRADE_LOG_CSV_FILE;
		
		try 
		{
		    
			FileReader filereader = new FileReader(filePath);
			
		    CSVReader csvReader = new CSVReaderBuilder(filereader)
	                .withSkipLines(1)
	                .build();
		    List<String[]> allData = csvReader.readAll();
		    
		    int rowCount = allData.size();
		    
		    allData.removeAll(allData);
		    
		    CurrencyCalculatorForm calc = d.getCalculations();
	  
		    String data [] = {String.valueOf(rowCount+1),calc.getInstrument(),new Date().toString(),calc.getTradeDecision(),d.getStoryAndViewBuilding(),
		    				 d.getTradeBias(),d.getOpportunity(),d.getWhatIfAnalysis(),d.getConfirmation(),d.isExecuted() ? "Yes" : "NO",
		    			     d.getOutcome(),d.getLearnings(),String.valueOf(calc.getRiskPerTrade()),String.valueOf(calc.getPipSize()),String.valueOf(calc.getLotSize()),
		    			     String.valueOf(calc.getEntry()),String.valueOf(calc.getStopLoss()),String.valueOf(calc.getTarget()),String.valueOf(calc.getNumberOfLots()),
		    			     String.valueOf(calc.getTotalAmountForAllLots()),String.valueOf(calc.getMarginAmountForAllLots()),String.valueOf(calc.getRewardRatio()),
		    			     String.valueOf(calc.getTargetPriceMovement()),String.valueOf(calc.getTargetPipMovenment()),
		    			     String.valueOf(calc.getMaxProfitPerLot()),String.valueOf(calc.getMaxProfitAllLots()),String.valueOf(calc.getPnlPercentageAsPerAllLotsForTarget()),
		    			     String.valueOf(calc.getSlPriceMovement()),String.valueOf(calc.getSlPipMovenment()),
		    			     String.valueOf(calc.getMaxLossPerLot()),String.valueOf(calc.getMaxLossAllLots()),String.valueOf(calc.getPnlPercentageAsPerAllLotsForSL()),
		    			     d.getTradeState(),String.valueOf(d.getTradeDuration()),d.getChartImageUrl(),
		    			     d.getComment(),"0","0"};
	    
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
	
	private void currencyShortCalculation(CurrencyCalculatorForm form) 
	{
		CurrencyPairModel currencyPair = CurrencyPairMaster.getCurrencyPairInfo(form.getInstrument());
	
		
		// #### SELL TARGET CALCULATION
		
		//entry -target
		form.setTargetPriceMovement(Precision.round( form.getEntry() - form.getTarget() , 4));
		//price movement / min movement
		form.setTargetPipMovenment(Precision.round(form.getTargetPriceMovement()/currencyPair.getPipSize() , 4));
		
		//Lot size x number of lots x movement in pips x pip size
		form.setMaxProfitPerLot(Precision.round(currencyPair.getLotSize() * 1 * form.getTargetPipMovenment() * currencyPair.getPipSize() , 2));
		form.setMaxProfitAllLots(Precision.round(currencyPair.getLotSize() * form.getNumberOfLots() * form.getTargetPipMovenment() * currencyPair.getPipSize() , 4));
	
		form.setPnlPercentageAsPerAllLotsForTarget(Precision.round((form.getMaxProfitAllLots() / form.getMarginAmountForAllLots()) * 100 , 2));
		
		// #### SELL SL CALCULATION
		
		//entry-sl
		form.setSlPriceMovement(Precision.round(form.getEntry() - form.getStopLoss() , 4));
		
		//price movement / min movement
		form.setSlPipMovenment(Precision.round(form.getSlPriceMovement() /currencyPair.getPipSize() , 4));
		
		form.setMaxLossPerLot(Precision.round(currencyPair.getLotSize() * 1 * form.getSlPipMovenment() * currencyPair.getPipSize() , 2));
		form.setMaxLossAllLots(Precision.round(currencyPair.getLotSize() * form.getNumberOfLots() * form.getSlPipMovenment() * currencyPair.getPipSize() , 2));
		
		form.setPnlPercentageAsPerAllLotsForSL(Precision.round((form.getMaxLossAllLots() / form.getMarginAmountForAllLots()) * 100 , 2));
		
		form.setNumberOfLotsCanBeTraded(Precision.round( Math.round(Math.abs(form.getRiskPerTrade() / form.getMaxLossPerLot())) , 0));
		
		form.setRewardRatio(Math.abs(Math.round(form.getMaxProfitPerLot()/form.getMaxLossPerLot())));
	}

	private void currencyLongCalculation(CurrencyCalculatorForm form) 
	{
		CurrencyPairModel currencyPair = CurrencyPairMaster.getCurrencyPairInfo(form.getInstrument());
		
		// #### BUY TARGET CALCULATION
		
		//target - entry
		form.setTargetPriceMovement(Precision.round( form.getTarget() - form.getEntry() , 4));
		//price movement / min movement
		form.setTargetPipMovenment(Precision.round( form.getTargetPriceMovement()/currencyPair.getPipSize() , 4));
		
		//Lot size x number of lots x movement in pips x pip size
		form.setMaxProfitPerLot(Precision.round( currencyPair.getLotSize() * 1 * form.getTargetPipMovenment() * currencyPair.getPipSize() , 2));
		form.setMaxProfitAllLots(Precision.round( currencyPair.getLotSize() * form.getNumberOfLots() * form.getTargetPipMovenment() * currencyPair.getPipSize() , 2));
		

		form.setPnlPercentageAsPerAllLotsForTarget(Precision.round( (form.getMaxProfitAllLots() / form.getMarginAmountForAllLots()) * 100 , 2));
		
		// #### BUY SL CALCULATION
		
		//sl - entry
		form.setSlPriceMovement(Precision.round( form.getStopLoss() - form.getEntry() , 4));
		
		//price movement / min movement
		form.setSlPipMovenment(Precision.round( form.getSlPriceMovement() /currencyPair.getPipSize() , 4));
		
		form.setMaxLossPerLot(Precision.round( currencyPair.getLotSize() * 1 * form.getSlPipMovenment() * currencyPair.getPipSize() , 2));
		form.setMaxLossAllLots(Precision.round( currencyPair.getLotSize() * form.getNumberOfLots() * form.getSlPipMovenment() * currencyPair.getPipSize() , 2));
		
		form.setPnlPercentageAsPerAllLotsForSL(Precision.round( (form.getMaxLossAllLots() / form.getMarginAmountForAllLots()) * 100 , 2));
		
		form.setNumberOfLotsCanBeTraded(Precision.round( Math.round(Math.abs(form.getRiskPerTrade() / form.getMaxLossPerLot())) , 0));
		
		form.setRewardRatio(Math.abs(Math.round(form.getMaxProfitPerLot()/form.getMaxLossPerLot())));
	}
	
	private String convDate(String dateStr) 
	{
		try 
		{
			Date dateConv = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy").parse(dateStr);  

			String convStr = new SimpleDateFormat("E dd-MMM-yyyy HH:mm:ss").format(dateConv);
			
			return convStr;
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
		}
		return dateStr;
	}
}
