package com.trades.demo.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trades.demo.models.CandleModel;
import com.trades.demo.reports.OrderTracker;
import com.trades.demo.strategy.QuantityPlanner;
import com.trades.demo.strategy.TradingStrategyService;

@Controller
@RequestMapping("/backtest")
public class BackTestController extends ReportsController
{
	Logger logger = LoggerFactory.getLogger(BackTestController.class);
	
	@Resource(name = "tradingStrategyService")
	TradingStrategyService tradingStrategyService;
	
	@RequestMapping("/strategy1")
	public String strategy1(Model model) throws Exception
	{
		logger.info("backtesting strategy1.....");
		
		model.addAttribute("strategyDesc", "Strategy1 : SMA 50 Support");
		Calendar cal = Calendar.getInstance();  
		cal.setTime(new Date());
		cal.add(Calendar.YEAR, -7); 
		Date fromDate = cal.getTime();
		Date tillDate = new Date();
		
		QuantityPlanner.monthlyTradeAmountTrackerMap.clear();
		OrderTracker.ORDER_LIST.clear();
		
		List<CandleModel> candlesWithTrade = tradingStrategyService.strategy1(fromDate,tillDate);
		
		generateReports(model, candlesWithTrade);
		
		
		logger.info("backtesting strategy1 done.....");
		
		return "backtestPage";
	}
}
