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

import com.trades.demo.common.TradeConstants;
import com.trades.demo.models.CandleModel;
import com.trades.demo.reports.OrderTracker;
import com.trades.demo.reports.StrategyInfo;
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
		
		TradeConstants.STRATEGY_RISK_RATIO = 1;
		TradeConstants.STRATEGY_REWARD_RATIO = 1.5;
		
		StrategyInfo strategyInfo = new StrategyInfo();
		strategyInfo.setId("strategy1");
		strategyInfo.setName("SMA 50 Support Long");
		strategyInfo.setDescription("");
		strategyInfo.setRiskRewardRation(TradeConstants.STRATEGY_RISK_RATIO+":"+TradeConstants.STRATEGY_REWARD_RATIO);
		
		List<CandleModel> candlesWithTrade = tradingStrategyService.strategy1(fromDate,tillDate);
		
		generateReports(model, candlesWithTrade,strategyInfo);
		
		logger.info("backtesting strategy1 done.....");
		
		return "backtestPage";
	}
	
	@RequestMapping("/strategy2")
	public String strategy2(Model model) throws Exception
	{
		logger.info("backtesting strategy2.....");
		
		model.addAttribute("strategyDesc", "Strategy2 : SMA 50 Support Exit 4 Days");
		Calendar cal = Calendar.getInstance();  
		cal.setTime(new Date());
		cal.add(Calendar.YEAR, -7); 
		Date fromDate = cal.getTime();
		Date tillDate = new Date();
		
		QuantityPlanner.monthlyTradeAmountTrackerMap.clear();
		OrderTracker.ORDER_LIST.clear();
		
		TradeConstants.STRATEGY_RISK_RATIO = 1;
		TradeConstants.STRATEGY_REWARD_RATIO = 0;
		
		StrategyInfo strategyInfo = new StrategyInfo();
		strategyInfo.setId("strategy2");
		strategyInfo.setName("SMA 50 Support Exit 4 Days");
		strategyInfo.setDescription("");
		strategyInfo.setRiskRewardRation(TradeConstants.STRATEGY_RISK_RATIO+":"+TradeConstants.STRATEGY_REWARD_RATIO);
		
		List<CandleModel> candlesWithTrade = tradingStrategyService.strategy2(fromDate,tillDate);
		
		generateReports(model, candlesWithTrade,strategyInfo);
		
		logger.info("backtesting strategy1 done.....");
		
		return "backtestPage";
	}
}
