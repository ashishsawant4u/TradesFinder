package com.trades.demo.reports;

import java.util.Date;

public class MonthlyReport 
{
	private Date month;
	
	private double investment;
	
	private double profitAndLoss;
	
	private double percentageGain;
	
	private int tradesCount;

	public Date getMonth() {
		return month;
	}

	public void setMonth(Date month) {
		this.month = month;
	}

	public double getInvestment() {
		return investment;
	}

	public void setInvestment(double investment) {
		this.investment = investment;
	}

	public double getProfitAndLoss() {
		return profitAndLoss;
	}

	public void setProfitAndLoss(double profitAndLoss) {
		this.profitAndLoss = profitAndLoss;
	}

	public double getPercentageGain() {
		return percentageGain;
	}

	public void setPercentageGain(double percentageGain) {
		this.percentageGain = percentageGain;
	}

	public int getTradesCount() {
		return tradesCount;
	}

	public void setTradesCount(int tradesCount) {
		this.tradesCount = tradesCount;
	}

	@Override
	public String toString() {
		return "MonthlyReport [month=" + month + ", investment=" + investment + ", profitAndLoss=" + profitAndLoss
				+ ", percentageGain=" + percentageGain + ", tradesCount=" + tradesCount + "]";
	}
}
