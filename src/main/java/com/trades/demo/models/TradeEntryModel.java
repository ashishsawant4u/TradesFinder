package com.trades.demo.models;

import java.util.Date;

import com.trades.demo.common.TradeStatus;

public class TradeEntryModel 
{
	public boolean entry;
	
	public boolean exit;
	
	public double buyPrice;
	
	public double stopLossPrice;
	
	public double targetPrice;

	public double lossPerUnit;
	
	public int quantity;
	
	public int tradeDuration;
	
	public Date tradeExitDate;
	
	public double investment;
	
	public TradeStatus tradeStatus;
	
	public double profitAndLossAmount;

	public boolean isEntry() {
		return entry;
	}

	public void setEntry(boolean entry) {
		this.entry = entry;
	}

	public boolean isExit() {
		return exit;
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public double getStopLossPrice() {
		return stopLossPrice;
	}

	public void setStopLossPrice(double stopLossPrice) {
		this.stopLossPrice = stopLossPrice;
	}

	public double getTargetPrice() {
		return targetPrice;
	}

	public void setTargetPrice(double targetPrice) {
		this.targetPrice = targetPrice;
	}

	public double getLossPerUnit() {
		return lossPerUnit;
	}

	public void setLossPerUnit(double lossPerUnit) {
		this.lossPerUnit = lossPerUnit;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTradeDuration() {
		return tradeDuration;
	}

	public void setTradeDuration(int tradeDuration) {
		this.tradeDuration = tradeDuration;
	}

	public Date getTradeExitDate() {
		return tradeExitDate;
	}

	public void setTradeExitDate(Date tradeExitDate) {
		this.tradeExitDate = tradeExitDate;
	}

	public double getInvestment() {
		return investment;
	}

	public void setInvestment(double investment) {
		this.investment = investment;
	}

	public TradeStatus getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(TradeStatus tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public double getProfitAndLossAmount() {
		return profitAndLossAmount;
	}

	public void setProfitAndLossAmount(double profitAndLossAmount) {
		this.profitAndLossAmount = profitAndLossAmount;
	}

	@Override
	public String toString() {
		return "TradeEntryModel [entry=" + entry + ", exit=" + exit + ", buyPrice=" + buyPrice + ", stopLossPrice="
				+ stopLossPrice + ", targetPrice=" + targetPrice + ", lossPerUnit=" + lossPerUnit + ", quantity="
				+ quantity + ", tradeDuration=" + tradeDuration + ", tradeExitDate=" + tradeExitDate + ", investment="
				+ investment + ", tradeStatus=" + tradeStatus + ", profitAndLossAmount=" + profitAndLossAmount + "]";
	}

	
}
