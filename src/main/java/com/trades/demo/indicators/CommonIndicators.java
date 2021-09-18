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
	 * Candle low taking support over MA
	 * Candle body is within MA
	 * GREEN & NO_SHADOW_LARGER_THAN_BODY & NOT LONG_CANDLE & NO_SHADOW_LARGER_THAN_HALF_OF_BODY
	 */
	public static boolean hasSupportForLong(CandleModel candle,int supportLevel)
	{
		boolean isCandleLandingCloseToMA  =  (candle.low >= candle.getSma().get(supportLevel)) && (candle.getSma().get(supportLevel)/candle.low)*100 >= 99;
		
		boolean isMAWithingCandleBodyRange = (candle.close > candle.getSma().get(supportLevel) && candle.open < candle.getSma().get(supportLevel));
		
		boolean candleTakingSupportOnMA = (isMAWithingCandleBodyRange || isCandleLandingCloseToMA);
		
		return candleTakingSupportOnMA
				&&  CandlestickPattern.GREEN.test(candle)
				&& CandlestickPattern.NO_SHADOW_LARGER_THAN_BODY.test(candle)
				//&& !CandlestickPattern.LONG_CANDLE.test(candle)
				&& CandlestickPattern.NO_SHADOW_LARGER_THAN_HALF_OF_BODY.test(candle);
	}
	
	/**
	 * Rising MA = MAs are ascending order on chart one after another 
	 * GIVEN Candle Support MA = 50
	 * IF MA-50 > MA-75 && MA-75 > MA-100
	 * THEN MA is considered to be rising/UpTrend
	 * 
	 */
	public static boolean isUptrendByMA(CandleModel supportCandle, List<CandleModel> symbolCandles,String avgType,int average,int period)
	{	
		List<CandleModel> prevCandles = DataHandler.getPreviousCandles(supportCandle, symbolCandles, period);
		
		boolean allCandlesLowAboveMA = true;
		
		//average = 50
		int shorterMARange = average/2;  //shorterMARange = 25
		int higherMARange1 = average + shorterMARange;  //higherMARange1 = 75
		int higherMARange2 = higherMARange1 + shorterMARange; //higherMARange2 = 100
		
		
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
		}
		
		if(avgType.equals("EMA"))
		{
			for(CandleModel candle : prevCandles)
			{
				if(candle.getEma().get(average) < candle.getEma().get(higherMARange1) || candle.getEma().get(higherMARange1) < candle.getEma().get(higherMARange2))
				{
					allCandlesLowAboveMA = false;
					break;
				}
			}
		}
		
		if(allCandlesLowAboveMA)
		{
			logger.info("UPTREND "+supportCandle.symbol+" ON "+supportCandle.marketDateTime);
		}
		
		return allCandlesLowAboveMA;
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
	
	public static boolean isOpenedAbovePrevCandle(CandleModel currentCandle,List<CandleModel> symbolCandles)
	{
		List<CandleModel> prevCandles = DataHandler.getPreviousCandles(currentCandle, symbolCandles, 2);
		
		
		if(CollectionUtils.isEmpty(prevCandles))
		{
			return false;
		}
		else
		{
			CandleModel prevCandle = prevCandles.get(0);
			
			if(currentCandle.open>= CandlestickPattern.candleCenter(prevCandle))
			{
				System.out.println("isOpenedAbovePrevCandle ");
				return true;
			}
			return false;
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
	
	
}
