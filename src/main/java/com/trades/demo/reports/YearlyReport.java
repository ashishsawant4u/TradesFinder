package com.trades.demo.reports;

import java.util.Date;

public class YearlyReport 
{
	private Date year;
	
	private double investment;
	
	private double profitAndLoss;
	
	private double percentageGain;
	
	private int tradesCount;

	public Date getYear() {
		return year;
	}

	public void setYear(Date year) {
		this.year = year;
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
		return "YearlyReport [year=" + year + ", investment=" + investment + ", profitAndLoss=" + profitAndLoss
				+ ", percentageGain=" + percentageGain + ", tradesCount=" + tradesCount + "]";
	}
}
