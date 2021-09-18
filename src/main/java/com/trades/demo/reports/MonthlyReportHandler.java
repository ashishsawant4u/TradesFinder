package com.trades.demo.reports;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.trades.demo.common.TradeStatus;
import com.trades.demo.models.CandleModel;

public class MonthlyReportHandler 
{
	public static List<MonthlyReport> monthlyReport(List<CandleModel> candleWithTrade) throws ParseException
	{
		List<MonthlyReport> reportList = new ArrayList<MonthlyReport>();
		
		SimpleDateFormat monthFormat = new SimpleDateFormat("MMM-yyyy");
		
		Set<String> monthsList = candleWithTrade.stream().map(c -> monthFormat.format(c.getMarketDateTime())).collect(Collectors.toSet());
		
		for(String month :  monthsList)
		{
			List<CandleModel> monthCandles = candleWithTrade.stream().filter(c-> monthFormat.format(c.getMarketDateTime()).equals(month)).collect(Collectors.toList());
			
			MonthlyReport report = new MonthlyReport();
			report.setMonth(monthFormat.parse(month));
			report.setInvestment(monthCandles.stream().mapToDouble(c->c.getTradeEntry().getInvestment()).sum());
			report.setProfitAndLoss(monthCandles.stream().mapToDouble(c->c.getTradeEntry().getProfitAndLossAmount()).sum());
			report.setMaxRiskAmount(monthCandles.stream().mapToDouble(c->(c.getTradeEntry().quantity*c.getTradeEntry().lossPerUnit)).sum());
			report.setTradesCount(monthCandles.size());
			report.setOpenTradesCount((int)monthCandles.stream().filter(c->c.getTradeEntry().getTradeStatus().toString().equals(TradeStatus.OPEN.toString())).count());
			report.setPercentageGain((report.getProfitAndLoss() / report.getInvestment())*100);
			reportList.add(report);
		}
		
		Collections.sort(reportList, (c1, c2) -> c1.getMonth().compareTo(c2.getMonth()));
		Collections.reverse(reportList);
		
		return reportList;
	}
}
