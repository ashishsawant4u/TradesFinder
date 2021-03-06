package com.trades.demo.controller;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import com.trades.demo.models.CandleModel;
import com.trades.demo.reports.MonthlyReportHandler;
import com.trades.demo.reports.OrderTracker;
import com.trades.demo.reports.StrategyInfo;
import com.trades.demo.reports.SymbolwiseReport;
import com.trades.demo.reports.TradeSummaryReportHandler;
import com.trades.demo.reports.YearlyReportHandler;

public class ReportsController 
{
	Logger logger = LoggerFactory.getLogger(ReportsController.class);
	
	public void generateReports(Model model,List<CandleModel> candlesWithTrade, StrategyInfo strategyInfo) throws Exception
	{
		logger.info("Preparing Reports.....");
		
		strategyInfo.setSummaryReport(TradeSummaryReportHandler.summaryReport(candlesWithTrade));
		model.addAttribute("strategySummaryReport", strategyInfo);
		Collections.sort(candlesWithTrade, (c1, c2) -> c1.getMarketDateTime().compareTo(c2.getMarketDateTime()));
		Collections.reverse(candlesWithTrade);
		
		model.addAttribute("trades", candlesWithTrade);
		model.addAttribute("symbolwiserTradeReport", SymbolwiseReport.symbolwiserTradeReport(candlesWithTrade));
		model.addAttribute("monthlyReport", MonthlyReportHandler.monthlyReport(candlesWithTrade));
		model.addAttribute("yearlyReport", YearlyReportHandler.yearlyReport(candlesWithTrade));
		model.addAttribute("orderTracker", OrderTracker.orderTracker());
		
		logger.info("Reports Done.....");
		
	}
}
