package com.trades.demo.reports;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.trades.demo.common.TradeStatus;
import com.trades.demo.models.CandleModel;

public class SymbolwiseReport 
{
	public static List<SymbolTradeResults> symbolwiserTradeReport(List<CandleModel> candleWithTrade)
	{
		Set<String> symbolsWithTrade = candleWithTrade.stream().map(c->c.getSymbol()).collect(Collectors.toSet());
		
		List<SymbolTradeResults> tradeReults = new ArrayList<SymbolTradeResults>();
		
		for(String symbol : symbolsWithTrade)
		{
			SymbolTradeResults trade = new SymbolTradeResults();
			trade.setSymbol(symbol);
			
			List<CandleModel> symbolTrades =  candleWithTrade.stream().filter(c->c.getSymbol().equals(symbol)).collect(Collectors.toList());
			
			trade.setTradesCount(symbolTrades.size());
			trade.setTargetExistCount((int)symbolTrades.stream().filter(c->c.getTradeEntry().getTradeStatus().toString().equals(TradeStatus.TARGET_EXIT.toString())).count());
			trade.setStopLossCount((int)symbolTrades.stream().filter(c->c.getTradeEntry().getTradeStatus().toString().equals(TradeStatus.STOP_LOSS.toString())).count());
			trade.setOpenTradesCount((int)symbolTrades.stream().filter(c->c.getTradeEntry().getTradeStatus().toString().equals(TradeStatus.OPEN.toString())).count());
			trade.setProfitableTrades(trade.targetExistCount - trade.stopLossCount);
			
			List<Integer> tradeDurationList = symbolTrades.stream().filter(c->c.getTradeEntry().exit).map((c->c.getTradeEntry().tradeDuration)).collect(Collectors.toList());
			
			DecimalFormat df=new DecimalFormat("#.##");
			
			//trade.setAvgTradeDuration((int) Math.round(Double.parseDouble(df.format(tradeDurationList.stream().mapToInt(val -> val).average().getAsDouble()))));
			
			tradeReults.add(trade);
		}
		
		List<SymbolTradeResults>  sortedList = tradeReults.stream()
		        .sorted(Comparator.comparingInt(SymbolTradeResults::getProfitableTrades).reversed())
		        .collect(Collectors.toList());
		
		return sortedList;
		
	}
}
