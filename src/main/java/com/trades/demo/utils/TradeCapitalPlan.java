package com.trades.demo.utils;

public class TradeCapitalPlan 
{
	public double capital = 50000;
	
	public double riskPercentage = 2.0;
	
	public double riskPerTrade = (riskPercentage/100)*capital;

	public double getCapital() {
		return capital;
	}

	public void setCapital(double capital) {
		this.capital = capital;
	}

	public double getRiskPercentage() {
		return riskPercentage;
	}

	public void setRiskPercentage(double riskPercentage) {
		this.riskPercentage = riskPercentage;
	}

	public double getRiskPerTrade() {
		return (riskPercentage/100)*capital;
	}

	public void setRiskPerTrade(double riskPerTrade) {
		this.riskPerTrade = riskPerTrade;
	}
	
	
}
