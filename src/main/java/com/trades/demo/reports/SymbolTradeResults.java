package com.trades.demo.reports;

public class SymbolTradeResults 
{
	public String symbol;
	
	public int tradesCount;
	
	public int targetExistCount;
	
	public int stopLossCount;
	
	public int openTradesCount;
	
	public int profitableTrades;
	
	public int avgTradeDuration;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

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

	public int getProfitableTrades() {
		return profitableTrades;
	}

	public void setProfitableTrades(int profitableTrades) {
		this.profitableTrades = profitableTrades;
	}

	public int getAvgTradeDuration() {
		return avgTradeDuration;
	}

	public void setAvgTradeDuration(int avgTradeDuration) {
		this.avgTradeDuration = avgTradeDuration;
	}

	public int getOpenTradesCount() {
		return openTradesCount;
	}

	public void setOpenTradesCount(int openTradesCount) {
		this.openTradesCount = openTradesCount;
	}

	@Override
	public String toString() {
		return "SymbolTradeResults [symbol=" + symbol + ", tradesCount=" + tradesCount + ", targetExistCount="
				+ targetExistCount + ", stopLossCount=" + stopLossCount + ", openTradesCount=" + openTradesCount
				+ ", profitableTrades=" + profitableTrades + ", avgTradeDuration=" + avgTradeDuration + "]";
	}
}
