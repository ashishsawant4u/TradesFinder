package com.trades.demo.indicators;

import java.util.List;
import java.util.function.Predicate;

import com.trades.demo.common.Trends;
import com.trades.demo.models.CandleModel;
import com.trades.demo.utils.DataHandler;

public class TrendFinder 
{
	private final int period;

	// constructor to initialize period
	public TrendFinder(int period)
	{
		this.period = period;
	}
	
	public void setTrendUsingChandelierExitBuy(List<CandleModel> candles)
	{
		candles.forEach(candle ->{
			
			List<CandleModel> prevCandles = DataHandler.getPreviousCandles(candle, candles, period);
			
			Predicate<CandleModel> isLowAboveChandelierExitBuy = (c) ->  c.low > c.getChandelierExitBuy().get(period);
			Predicate<CandleModel> isAboveChandelierExitBuy = (c) ->  c.low > c.getChandelierExitBuy().get(period);
			Predicate<CandleModel> isEqualChandelierExitBuy = (c) ->  c.low == c.getChandelierExitBuy().get(period);
			
			long lowCandlesCount = prevCandles.stream().filter(isLowAboveChandelierExitBuy).count();
			long highCandlesCount = prevCandles.stream().filter(isAboveChandelierExitBuy).count();
			long equalCandlesCount = prevCandles.stream().filter(isEqualChandelierExitBuy).count();

			if(lowCandlesCount > Math.max(highCandlesCount, equalCandlesCount))
			{
				candle.setTrend(Trends.DOWNTREND.toString());
			}
			if(highCandlesCount > Math.max(lowCandlesCount, equalCandlesCount))
			{
				candle.setTrend(Trends.UPTREND.toString());
			}
			if(equalCandlesCount > Math.max(highCandlesCount, lowCandlesCount))
			{
				candle.setTrend(Trends.SIDEWAYS.toString());
			}
			
		});
	}
}
