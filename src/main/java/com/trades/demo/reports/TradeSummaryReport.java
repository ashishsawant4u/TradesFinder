package com.trades.demo.reports;

public class TradeSummaryReport 
{
	public int tradesCount;
	
	public int targetExistCount;
	
	public int stopLossCount;
	
	public int openTradesCount;
	
	public int profitableTrades;
	
	public double profitAndLossAmount;
	
	public double winRate;

	public int getTradesCount() {
		return tradesCount;
	}

	public void setTradesCount(int tradesCount) {
		this.tradesCount = tradesCount;
	}

	public int getTargetExistCount() {
		return targetExistCount;
	}

	public void setTargetExistCount(int targetExistCount) {
		this.targetExistCount = targetExistCount;
	}

	public int getStopLossCount() {
		return stopLossCount;
	}

	public void setStopLossCount(int stopLossCount) {
		this.stopLossCount = stopLossCount;
	}

	public int getOpenTradesCount() {
		return openTradesCount;
	}

	public void setOpenTradesCount(int openTradesCount) {
		this.openTradesCount = openTradesCount;
	}

	public int getProfitableTrades() {
		return profitableTrades;
	}

	public void setProfitableTrades(int profitableTrades) {
		this.profitableTrades = profitableTrades;
	}

	public double getProfitAndLossAmount() {
		return profitAndLossAmount;
	}

	public void setProfitAndLossAmount(double profitAndLossAmount) {
		this.profitAndLossAmount = profitAndLossAmount;
	}

	public double getWinRate() {
		return winRate;
	}

	public void setWinRate(double winRate) {
		this.winRate = winRate;
	}

	@Override
	public String toString() {
		return "TradeSummaryReport [tradesCount=" + tradesCount + ", targetExistCount=" + targetExistCount
				+ ", stopLossCount=" + stopLossCount + ", openTradesCount=" + openTradesCount + ", profitableTrades="
				+ profitableTrades + ", profitAndLossAmount=" + profitAndLossAmount + ", winRate=" + winRate + "]";
	}
}
