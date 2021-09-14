package com.trades.demo.indicators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.trades.demo.models.CandleModel;
import com.trades.demo.utils.DataHandler;

public class ChandelierExit 
{
	private final int period;

	// constructor to initialize period
	public ChandelierExit(int period)
	{
		this.period = period;
	}
	
	public List<CandleModel> chandelierExitForBuy(List<CandleModel> candles)
	{
		List<CandleModel> candlesWithchandelierExitBuy = new ArrayList<CandleModel>();
		
		for(CandleModel candle :  candles)
		{
			List<CandleModel> prevCandles = DataHandler.getPreviousCandles(candle, candles, period);
			double high22Day = prevCandles.stream().mapToDouble(c->c.high).max().getAsDouble();
			
			double chandelierExitForBuy = high22Day - (candle.getAtr().get(period) * 3);
			
			if(null!=candle.getChandelierExitBuy())
			{
				candle.getChandelierExitBuy().put(period, chandelierExitForBuy);
			}
			else
			{
				Map<Integer, Double> chandelierExitBuyMap = new HashMap<Integer, Double>();
				chandelierExitBuyMap.put(period, chandelierExitForBuy);
				candle.setChandelierExitBuy(chandelierExitBuyMap);
			}
			
			String trend = "";
			if(candle.low > candle.chandelierExitBuy.get(period))
			{
				trend = "UPTREND";
			}
			else if(candle.low < candle.chandelierExitBuy.get(period))
			{
				trend = "DOWNTREND";
			}
			else
			{
				trend = "SIDEWAYS";
			}
			
			candle.setTrend(trend);
			
			candlesWithchandelierExitBuy.add(candle);
		}
		
		return candlesWithchandelierExitBuy;
	}
	
}
