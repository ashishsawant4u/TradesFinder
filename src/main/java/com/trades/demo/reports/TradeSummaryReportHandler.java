package com.trades.demo.reports;

import java.text.ParseException;
import java.util.List;

import com.trades.demo.common.TradeStatus;
import com.trades.demo.models.CandleModel;

public class TradeSummaryReportHandler 
{
	public static TradeSummaryReport summaryReport(List<CandleModel> candleWithTrade) throws ParseException
	{
		TradeSummaryReport report = new TradeSummaryReport();
		report.setTradesCount(candleWithTrade.size());
		report.setTargetExistCount((int)candleWithTrade.stream().filter(c->c.getTradeEntry().getTradeStatus().toString().equals(TradeStatus.TARGET_EXIT.toString())).count());
		report.setStopLossCount((int)candleWithTrade.stream().filter(c->c.getTradeEntry().getTradeStatus().toString().equals(TradeStatus.STOP_LOSS.toString())).count());
		report.setOpenTradesCount((int)candleWithTrade.stream().filter(c->c.getTradeEntry().getTradeStatus().toString().equals(TradeStatus.OPEN.toString())).count());
		report.setProfitableTrades(report.targetExistCount - report.stopLossCount);
		report.setProfitAndLossAmount(candleWithTrade.stream().mapToDouble(c->c.getTradeEntry().getProfitAndLossAmount()).sum());
		return report;
	}
	
}
