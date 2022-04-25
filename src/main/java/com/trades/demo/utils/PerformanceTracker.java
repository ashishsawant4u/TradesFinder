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
		
		long winningTrades  = trades.stream().filter(t->t.getTradeState().equals("Target Exit")).count();
		
		long loosingTrades  = trades.stream().filter(t->t.getTradeState().equals("SL Exit")).count();
		
		long numberOfTrades = winningTrades + loosingTrades;
		
		double hitRatio = (winningTrades * 100) / numberOfTrades;
		
		stats.setWinningTradesCount((int)winningTrades);
		stats.setLoosingTradesCount((int)loosingTrades);
		stats.setNumberOfTrades((int)numberOfTrades);
		stats.setHitRatio(Precision.round(hitRatio,2));
		
		
		return stats;
	}
}
