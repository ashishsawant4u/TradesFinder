package com.trades.demo.models;

public class CurrencyPairModel 
{
	private String currenyPairName;
	
	private double pipSize;
	
	private double lotSize;
	
	private double profitAndLossPerLotPerPip;
	
	

	public CurrencyPairModel(String currenyPairName, double pipSize, double lotSize, double profitAndLossPerLotPerPip) 
	{
		
		this.currenyPairName = currenyPairName;
		this.pipSize = pipSize;
		this.lotSize = lotSize;
		this.profitAndLossPerLotPerPip = profitAndLossPerLotPerPip;
	}

	public String getCurrenyPairName() {
		return currenyPairName;
	}

	public void setCurrenyPairName(String currenyPairName) {
		this.currenyPairName = currenyPairName;
	}

	public double getPipSize() {
		return pipSize;
	}

	public void setPipSize(double pipSize) {
		this.pipSize = pipSize;
	}

	public double getLotSize() {
		return lotSize;
	}

	public void setLotSize(double lotSize) {
		this.lotSize = lotSize;
	}

	public double getProfitAndLossPerLotPerPip() {
		return profitAndLossPerLotPerPip;
	}

	public void setProfitAndLossPerLotPerPip(double profitAndLossPerLotPerPip) {
		this.profitAndLossPerLotPerPip = profitAndLossPerLotPerPip;
	}
	
	
}
