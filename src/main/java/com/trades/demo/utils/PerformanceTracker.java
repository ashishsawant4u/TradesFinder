package com.trades.demo.utils;

import java.util.List;

import org.apache.commons.math3.util.Precision;

import com.trades.demo.forms.CurrencyTradeDetails;
import com.trades.demo.models.PerformanceStats;

public class PerformanceTracker 
{
	
	
	public static PerformanceStats getPerformanceStats(List<CurrencyTradeDetails> trades)
	{
		PerformanceStats stats = new PerformanceStats();
		
		long winningTrades  = trades.stream().filter(t-> t.getTradeState().equals("Target Exit") || t.getTradeState().equals("Target Early Exit")).count();
		
		long loosingTrades  = trades.stream().filter(t->t.getTradeState().equals("SL Exit") || t.getTradeState().equals("Breakeven Exit")).count();
		
		long numberOfTrades = winningTrades + loosingTrades;
		
		double hitRatio = (winningTrades * 100) / numberOfTrades;
		
		double totalPnL = trades.stream().filter(t->t.getTradeState().equals("Target Exit") || t.getTradeState().equals("Target Early Exit") || t.getTradeState().equals("SL Exit"))
									.map(t->t.getCalculations().getActualPnL())
									.mapToDouble(Double::doubleValue)
									.sum();
		double sumOfAllWinningRewardRatio = trades.stream().filter(t->t.getTradeState().equals("Target Exit") || t.getTradeState().equals("Target Early Exit"))
										.map(t->t.getCalculations().getActualRewardRatio())
										.mapToDouble(Double::doubleValue)
										.sum();	
		double avgWinningRewardRatio = sumOfAllWinningRewardRatio / winningTrades;
		
		double sumOfRiskRewardRatio = sumOfAllWinningRewardRatio - (double)loosingTrades;
		
		double expectancyOfSystem = sumOfRiskRewardRatio / numberOfTrades;
		
		double capitalGain = (totalPnL * 100) / new TradeCapitalPlan().getCapital();
		
		stats.setWinningTradesCount((int)winningTrades);
		stats.setLoosingTradesCount((int)loosingTrades);
		stats.setNumberOfTrades((int)numberOfTrades);
		stats.setHitRatio(Precision.round(hitRatio,2));
		stats.setTotalPnL(totalPnL);
		stats.setAvgRewardRation(Precision.round(avgWinningRewardRatio,2));
		stats.setExpectancy(Precision.round(expectancyOfSystem,2));
		stats.setCapitalGain(Precision.round(capitalGain,2));
		return stats;
	}
}