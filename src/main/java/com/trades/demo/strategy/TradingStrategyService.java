package com.trades.demo.strategy;

import java.util.Date;
import java.util.List;

import com.trades.demo.models.CandleModel;

public interface TradingStrategyService 
{
	public List<CandleModel> strategy1(Date fromDate, Date tillDate);
	
	public List<CandleModel> strategy2(Date fromDate, Date tillDate);
}
