package com.trades.demo.models;

import java.util.Date;
import java.util.Map;

public class CandleModel 
{	
	public String symbol;
	
	public Date marketDateTime;
	
	public double open;

    public double high;

    public double low;

    public double close;
    
    public double volume;

	public Map<Integer, Double> sma;
    
	public Map<Integer, Double> ema;
	
	public Map<Integer, Double> atr;
	
	public Map<Integer, Double> chandelierExitBuy;
	
	public String candlePattern;

	public String trend;
	
	public TradeEntryModel tradeEntry;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Date getMarketDateTime() {
		return marketDateTime;
	}

	public void setMarketDateTime(Date marketDateTime) {
		this.marketDateTime = marketDateTime;
	}

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public Map<Integer, Double> getSma() {
		return sma;
	}

	public void setSma(Map<Integer, Double> sma) {
		this.sma = sma;
	}

	public Map<Integer, Double> getEma() {
		return ema;
	}

	public void setEma(Map<Integer, Double> ema) {
		this.ema = ema;
	}

	public String getCandlePattern() {
		return candlePattern;
	}

	public void setCandlePattern(String candlePattern) {
		this.candlePattern = candlePattern;
	}

	public TradeEntryModel getTradeEntry() {
		return tradeEntry;
	}

	public void setTradeEntry(TradeEntryModel tradeEntry) {
		this.tradeEntry = tradeEntry;
	}

	public Map<Integer, Double> getAtr() {
		return atr;
	}

	public void setAtr(Map<Integer, Double> atr) {
		this.atr = atr;
	}

	public Map<Integer, Double> getChandelierExitBuy() {
		return chandelierExitBuy;
	}

	public void setChandelierExitBuy(Map<Integer, Double> chandelierExitBuy) {
		this.chandelierExitBuy = chandelierExitBuy;
	}

	public String getTrend() {
		return trend;
	}

	public void setTrend(String trend) {
		this.trend = trend;
	}

	@Override
	public String toString() {
		return "CandleModel [symbol=" + symbol + ", marketDateTime=" + marketDateTime + ", open=" + open + ", high="
				+ high + ", low=" + low + ", close=" + close + ", volume=" + volume + ", sma=" + sma + ", ema=" + ema
				+ ", atr=" + atr + ", chandelierExitBuy=" + chandelierExitBuy + ", candlePattern=" + candlePattern
				+ ", trend=" + trend + ", tradeEntry=" + tradeEntry + "]";
	}
}
