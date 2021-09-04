package com.trades.demo.indicators;

import com.trades.demo.models.CandleModel;

public class CommonIndicators 
{
	public static boolean hasSupport(CandleModel candle,double supportLevel)
	{
		return (candle.low > supportLevel) && ((supportLevel/candle.low)*100 > 90);
	}
}
