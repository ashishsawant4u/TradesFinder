package com.trades.demo.indicators;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trades.demo.models.CandleModel;
import com.trades.demo.utils.CandlestickPattern;
import com.trades.demo.utils.DataHandler;

public class CommonIndicators 
{
	static Logger logger = LoggerFactory.getLogger(CommonIndicators.class);
	/**
	 * Support IF
	 * Candle body taking support over MA
	 * Candle close is near to MA
	 * 
	 */
	public static boolean hasSupport(CandleModel candle,int supportLevel)
	{
		//boolean isMAWithingCandleBodyRange = (candle.high > supportLevel && candle.low < supportLevel);
		//boolean isCandleLandingCloseToMA  = (candle.low > supportLevel && ((candle.low - supportLevel) < 3));
		
		boolean isMAWithingCandleBodyRange = (candle.close > candle.getSma().get(supportLevel) && candle.open < candle.getSma().get(supportLevel));
		
		boolean hasSupport = isMAWithingCandleBodyRange 
					&& CandlestickPattern.NO_SHADOW_LARGER_THAN_BODY.test(candle) 
					&& !CandlestickPattern.DOJI.test(candle)
					&& !CandlestickPattern.SPINNING_TOP.test(candle)
					&&  CandlestickPattern.GREEN.test(candle) 
					&& !CandlestickPattern.LONG_CANDLE.test(candle)
					&& CandlestickPattern.ALL_SHADOW.test(candle);
		
		if(hasSupport)
		{
			logger.info("hasSupport "+candle);
		}
		
		return hasSupport;
	}
	
	public static boolean hasSupport(CandleModel candle)
	{
		boolean candleBodyAboveMA50 = Math.min(candle.open, candle.close) > candle.getSma().get(50);
		boolean candleLowWickCuttingMA50 = (Math.min(candle.open, candle.close) > candle.getSma().get(50) && candle.low < candle.getSma().get(50));
		
		if(candleBodyAboveMA50 && candleLowWickCuttingMA50 && CandlestickPattern.HAMMER.test(candle))
		{
			//logger.info(candle.marketDateTime+" O:"+candle.open+" H:"+candle.high+" L:"+candle.low+" C:"+candle.close+" MA50:"+candle.getSma().get(50));
			
			return true;
		}
		
		return false;
	}
	
	public static boolean prevCandlehasSupport(CandleModel candle,List<CandleModel> symbolCandles)
	{
		List<CandleModel> prevCandles = DataHandler.getPreviousCandles(candle, symbolCandles, 2);
		
		
		if(CollectionUtils.isEmpty(prevCandles))
		{
			return false;
		}
		else
		{
			return hasSupport(prevCandles.get(0), 50) && CandlestickPattern.GREEN.test(candle);
		}
	}
	
	
	public static boolean isUptrendByMA2(CandleModel candleModel, List<CandleModel> symbolCandles,String avgType,int average,int period)
	{	
		List<CandleModel> prevCandles = DataHandler.getPreviousCandles(candleModel, symbolCandles, period);
		
		boolean allCandlesLowAboveMA = true;
		
		for(CandleModel candle : prevCandles)
		{
			if(candle.getSma().get(50) < candle.getSma().get(75) || candle.getSma().get(75) < candle.getSma().get(100))
			{
				allCandlesLowAboveMA = false;
				break;
			}
		}
		
		if(allCandlesLowAboveMA)
		{
			System.out.println("UPTREND");
		}
		
		return allCandlesLowAboveMA;
	}
	
	public static boolean isUptrendByMA(CandleModel supportCandle, List<CandleModel> symbolCandles,String avgType,int average,int period)
	{	
		List<CandleModel> prevCandles = DataHandler.getPreviousCandles(supportCandle, symbolCandles, period);
		
		boolean allCandlesLowAboveMA = true;
		
		//average = 50
		int risingMARatio = average/2;  //risingMARatio = 25
		int higherMARange1 = average + risingMARatio;  //higherMARange1 = 75
		int higherMARange2 = higherMARange1 + risingMARatio; //higherMARange2 = 100
		
		
		if(avgType.equals("SMA"))
		{
			for(CandleModel candle : prevCandles)
			{
				if(candle.getSma().get(average) < candle.getSma().get(higherMARange1) || candle.getSma().get(higherMARange1) < candle.getSma().get(higherMARange2))
				{
					allCandlesLowAboveMA = false;
					break;
				}
			}
			
			for(CandleModel candle : prevCandles)
			{
				if(candle.low < candle.getSma().get(average))
				{
					allCandlesLowAboveMA = false;
					break;
				}
			}
		}
		
		if(avgType.equals("EMA"))
		{
			
		}
		
		if(allCandlesLowAboveMA)
		{
			System.out.println("UPTREND");
		}
		
		return allCandlesLowAboveMA;
	}
}
