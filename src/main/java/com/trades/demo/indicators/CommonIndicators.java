package com.trades.demo.indicators;

import com.trades.demo.models.CandleModel;
import com.trades.demo.utils.CandlestickPattern;

public class CommonIndicators 
{
	public static boolean hasSupport(CandleModel candle,double supportLevel)
	{
		return !CandlestickPattern.DOJI.test(candle) 
					&& !CandlestickPattern.SPINNING_TOP.test(candle)
					&& (candle.low > supportLevel) 
					&& (((supportLevel/candle.low)*100) > 96);
	}
}
