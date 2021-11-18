package com.trades.demo.reports;

public class StrategyInfo 
{	
	public String id;
	
	public String name;
	
	public String description;
	
	public String riskRewardRation;
	
	public TradeSummaryReport summaryReport;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRiskRewardRation() {
		return riskRewardRation;
	}

	public void setRiskRewardRation(String riskRewardRation) {
		this.riskRewardRation = riskRewardRation;
	}

	public TradeSummaryReport getSummaryReport() {
		return summaryReport;
	}

	public void setSummaryReport(TradeSummaryReport summaryReport) {
		this.summaryReport = summaryReport;
	}

	@Override
	public String toString() {
		return "StrategyInfo [id=" + id + ", name=" + name + ", description=" + description + ", riskRewardRation="
				+ riskRewardRation + ", summaryReport=" + summaryReport + "]";
	}
}	
