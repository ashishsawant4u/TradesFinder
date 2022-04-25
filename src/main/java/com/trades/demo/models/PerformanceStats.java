package com.trades.demo.models;

public class PerformanceStats 
{
	private int winningTradesCount;
	
	private int loosingTradesCount;
	
	private int numberOfTrades;
	
	private double hitRatio;

	public int getWinningTradesCount() {
		return winningTradesCount;
	}

	public void setWinningTradesCount(int winningTradesCount) {
		this.winningTradesCount = winningTradesCount;
	}

	public int getLoosingTradesCount() {
		return loosingTradesCount;
	}

	public void setLoosingTradesCount(int loosingTradesCount) {
		this.loosingTradesCount = loosingTradesCount;
	}

	public int getNumberOfTrades() {
		return numberOfTrades;
	}

	public void setNumberOfTrades(int numberOfTrades) {
		this.numberOfTrades = numberOfTrades;
	}

	public double getHitRatio() {
		return hitRatio;
	}

	public void setHitRatio(double hitRatio) {
		this.hitRatio = hitRatio;
	}
	
}
