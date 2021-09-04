package com.trades.demo.reports;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.trades.demo.models.CandleModel;

public class YearlyReportHandler 
{
	public static List<YearlyReport> yearlyReport(List<CandleModel> candleWithTrade) throws ParseException
	{
		List<YearlyReport> reportList = new ArrayList<YearlyReport>();
		
		SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
		
		Set<String> yearList = candleWithTrade.stream().map(c -> yearFormat.format(c.getMarketDateTime())).collect(Collectors.toSet());
		
		for(String year :  yearList)
		{
			List<CandleModel> yearCandles = candleWithTrade.stream().filter(c-> yearFormat.format(c.getMarketDateTime()).equals(year)).collect(Collectors.toList());
			
			YearlyReport report = new YearlyReport();
			report.setYear(yearFormat.parse(year));
			report.setInvestment(yearCandles.stream().mapToDouble(c->c.getTradeEntry().getInvestment()).sum());
			report.setProfitAndLoss(yearCandles.stream().mapToDouble(c->c.getTradeEntry().getProfitAndLossAmount()).sum());
			report.setTradesCount(yearCandles.size());
			report.setPercentageGain((report.getProfitAndLoss() / report.getInvestment())*100);
			reportList.add(report);
		}
		
		Collections.sort(reportList, (c1, c2) -> c1.getYear().compareTo(c2.getYear()));
		Collections.reverse(reportList);
		
		return reportList;
	}
}
