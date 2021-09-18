package com.trades.demo.utils;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.function.Predicate;

import com.trades.demo.common.CandlePatterns;
import com.trades.demo.common.TradeConstants;
import com.trades.demo.models.CandleModel;



public final class CandlestickPattern 
{
	
	public static String REAL_BODY_BULLISH_CANDLE = "REAL_BODY_BULLISH_CANDLE";

	public static String REAL_BODY_BEARISH_CANDLE = "REAL_BODY_BEARISH_CANDLE";
	
	public static String SPINNING_TOP_BULLISH_CANDLE = "SPINNING_TOP_BULLISH_CANDLE";
	
	public static String SPINNING_TOP_BEARISH_CANDLE = "SPINNING_TOP_BEARISH_CANDLE";
	
	public static String DOJI_CANDLE = "DOJI_CANDLE";
	
	public static String BULLISH_LONG_LOWER_SHADOW_CANDLE = "BULLISH_LONG_LOWER_SHADOW_CANDLE";
	
	public static String BEARISH_LONG_UPPER_SHADOW_CANDLE = "BEARISH_LONG_UPPER_SHADOW_CANDLE";
	
	/**
     * GREEN Candle Pattern
     */
    public static final Predicate<CandleModel> GREEN = (ohlc) -> ohlc.open < ohlc.close;

    /**
     * RED Candles Pattern
     */
    public static final Predicate<CandleModel> RED = (ohlc) -> ohlc.open > ohlc.close;

    /**
     * Candle pattern that has upper shadow
     */
    public static final Predicate<CandleModel> UPPER_SHADOW = (ohlc) -> ohlc.high > Math.max(ohlc.open, ohlc.close);

    /**
     * Candle pattern with lower shade
     */
    public static final Predicate<CandleModel> LOWER_SHADOW = (ohlc) -> ohlc.low < Math.min(ohlc.open, ohlc.close);

    /**
     * Candle pattern that has upper and lower shadow
     */
    public static final Predicate<CandleModel> ALL_SHADOW = UPPER_SHADOW.and(LOWER_SHADOW);

    /**
     * Candle pattern that has SOME upper and lower shade
     */
    public static final Predicate<CandleModel> ANY_SHADOW = UPPER_SHADOW.or(LOWER_SHADOW);

    /**
     * Candle pattern with NO shadow
     */
    public static final Predicate<CandleModel> NO_SHADOW = ANY_SHADOW.negate();

    /**
     * Candle pattern where the upper shadow is longer than the body
     */
    public static final Predicate<CandleModel> UPPER_SHADOW_LARGER_THAN_BODY = UPPER_SHADOW.and((ohlc) -> {
        return (ohlc.high - Math.max(ohlc.open, ohlc.close)) > body(ohlc);
    });

    /**
     * Candle pattern where the lower shadow is longer than the body
     */
    public static final Predicate<CandleModel> LOWER_SHADOW_LARGER_THAN_BODY = LOWER_SHADOW.and((ohlc) -> {
        return (Math.min(ohlc.open, ohlc.close) - ohlc.low) > body(ohlc);
    });

    /**
     * Candle pattern where ALL shadows are bigger than the body
     */
    public static final Predicate<CandleModel> ALL_SHADOW_LARGER_THAN_BODY
            = UPPER_SHADOW_LARGER_THAN_BODY.and(LOWER_SHADOW_LARGER_THAN_BODY);

    /**
     * Candle pattern where ANY shadows are bigger than the body
     */
    public static final Predicate<CandleModel> ANY_SHADOW_LARGER_THAN_BODY
            = UPPER_SHADOW_LARGER_THAN_BODY.or(LOWER_SHADOW_LARGER_THAN_BODY);

    
    public static final Predicate<CandleModel> NO_SHADOW_LARGER_THAN_HALF_OF_BODY = ALL_SHADOW.and((ohlc) -> {
        return (ohlc.high - Math.max(ohlc.open, ohlc.close)) <= (body(ohlc)/2)
        		&& (Math.min(ohlc.open, ohlc.close) - ohlc.low) <= (body(ohlc)/2);
    });
    
    /**
     * Candle pattern where NONE of the shadows is bigger than the body
     */
    public static final Predicate<CandleModel> NO_SHADOW_LARGER_THAN_BODY = ANY_SHADOW_LARGER_THAN_BODY.negate();
    
    public static final Predicate<CandleModel> isLongGreenCandle = (candle) -> {
        
        	double currentCandleBody = body(candle);
        	
        	List<CandleModel> previous15candles = DataHandler.getPreviousCandles(candle, TradeConstants.ALL_CANDLES, 15);
        	
        	double previous15candlesBodyAvg = previous15candles.stream().mapToDouble((c-> Math.abs(c.getOpen()-c.getClose()))).average().getAsDouble();
        	
        	return currentCandleBody > (3*previous15candlesBodyAvg);
    };
    
    
    public static final Predicate<CandleModel> LONG_GREEN_CANDLE = (candle) -> {
    	
        return GREEN
                .and(ALL_SHADOW)
                .and(NO_SHADOW_LARGER_THAN_BODY)
                .and(isLongGreenCandle)
                .test(candle);
    };
    
    
    /**
     * A Doji Candle has the open exactly equal to or nearly equal to the close. 
     * The following formula defines this as the body being less than or equal to 5% of the candle.
     * 20 * ABS(O - C) <= H - L
     * https://help.tc2000.com/m/69445/l/798550-doji-candle
     */
    public static final Predicate<CandleModel> DOJI = (candle) -> {
        return (20 * Math.abs(candle.open - candle.close)) < (candle.high - candle.low);
    };
    
    
    /**
     * (Body) > (average body of the last X days) *0.5
     * https://www.mql5.com/en/articles/101
     */
    public static final Predicate<CandleModel> SHORT_CANDLE = (candle) -> {
    	
    	double currentCandleBody = body(candle);
    	
    	List<CandleModel> previous15candles = DataHandler.getPreviousCandles(candle, TradeConstants.ALL_CANDLES, 15);
    	
    	double previous15candlesBodyAvg = previous15candles.stream().mapToDouble((c-> Math.abs(c.getOpen()-c.getClose()))).average().getAsDouble();
    	
    	return currentCandleBody < (0.5*previous15candlesBodyAvg);
    };
    
    public static final Predicate<CandleModel> LONG_CANDLE = (candle) -> {
    	
    	double currentCandleBody = body(candle);
    	
    	List<CandleModel> previous15candles = DataHandler.getPreviousCandles(candle, TradeConstants.ALL_CANDLES, 15);
    	
    	if(previous15candles.size()<15)
    	{
    		return false;
    	}
    	else
    	{
    		double previous15candlesBodyAvg = previous15candles.stream().mapToDouble((c-> Math.abs(c.getOpen()-c.getClose()))).average().getAsDouble();
        	return currentCandleBody > (1.3*previous15candlesBodyAvg);
    		//double maxCandleHeightInPrev15Candles = previous15candles.stream().mapToDouble(c->CandlestickPattern.size(c)).max().getAsDouble();
    		//return CandlestickPattern.size(candle) > maxCandleHeightInPrev15Candles;
    	}
    	
    };
    
    /**
     * 
     * https://www.mql5.com/en/articles/101
     */
    public static final Predicate<CandleModel> SPINNING_TOP = (candle) -> {
        return SHORT_CANDLE
        		.and(ALL_SHADOW_LARGER_THAN_BODY)
        		.test(candle);
    };
    
    /**
     * A Marubozu Candle is a Long Candle which is all body, having no shadows / wicks.
     * H - L = ABS(O - C)
     */
    public static final Predicate<CandleModel> MARUBOZU = (candle) -> {
        
    	 return (candle.high - candle.low) == body(candle);
    };
    
    
    /**
     * The body is located in the upper part of the daily range and the lower shadow is much longer than the body. 
     * It is also necessary to consider the length of the upper shadow, if there is any. 
     * The ratio between the body and the lower shadow is defined as the ratio of the body length to the length of the lower shadow:
	 * (lower shadow)>(body)*2 and  (upper shadow)< (body)*0.1
     * 
     */
    public static final Predicate<CandleModel> HAMMER = (candle) -> {
        
    	 if(lowerShadow(candle) > (body(candle)*2) && upperShadow(candle) < (body(candle)*0.1)) 
    	 {
    		 return true;
    	 }
    	
    	 return false;
   };
	
   /**
    * "Shooting Star" and "Inverted Hammer" are similar to the "Hammer", but with the opposite condition:
	* (lower shadow)<(body)*0.1 and (upper shadow)> (body)*2
	* https://www.mql5.com/en/articles/101
    */
   public static final Predicate<CandleModel> SHOOTING_STAR = (candle) -> {
       
	   if(lowerShadow(candle) < (body(candle)*0.1) && upperShadow(candle) > (body(candle)*2))
	   {
	  		 return true;
	   }
	  	
	  	 return false;
   };
    
    /**
     * Get the candle size
     * <p>
     * Candle size is the difference between the highest and lowest price of this candle.
     *
     * @param ohlc
     * @return
     */
    public static double size(final CandleModel ohlc) {
        return ohlc.high - ohlc.low;
    }

    /**
     * Gets the size of the candle's body
     *
     * @param ohlc
     * @return
     */
    public static double body(final CandleModel ohlc) {
        return Math.abs(ohlc.open - ohlc.close);
    }
    
    public static double upperShadow(final CandleModel ohlc) {
        return ohlc.high - Math.max(ohlc.open, ohlc.close);
    }
    
    public static double lowerShadow(final CandleModel ohlc) {
        return Math.min(ohlc.open, ohlc.close) - ohlc.low;
    }
    
    public static double candleCenter(final CandleModel ohlc) {
        
    	if(CandlestickPattern.GREEN.test(ohlc))
    	{
    		return ohlc.open + (body(ohlc)/2);
    	}
    	else
    	{
    		return ohlc.close + (body(ohlc)/2);
    	}
    }

    
    public static String findCandlePattern(CandleModel currentCandle)
	{
    	if(HAMMER.test(currentCandle))
		{
			return CandlePatterns.HAMMER_CANDLE.toString();
		}
		
		if(SHOOTING_STAR.test(currentCandle))
		{
			return CandlePatterns.SHOOTING_STAR_CANDLE.toString();
		}
		
		if(DOJI.test(currentCandle))
		{
			return CandlePatterns.DOJI_CANDLE.toString();
		}
		
		if(SPINNING_TOP.test(currentCandle))
		{
			return CandlePatterns.SPINNING_TOP_CANDLE.toString();
		}
		
		if(MARUBOZU.test(currentCandle))
		{
			return CandlePatterns.MARUBOZU_CANDLE.toString();
		}
		
		if(LONG_GREEN_CANDLE.test(currentCandle))
		{
			return CandlePatterns.LONG_GREEN_CANDLE.toString();
		}
		
		if(SHORT_CANDLE.test(currentCandle))
		{
			return CandlePatterns.SHORT_CANLDE.toString();
		}
		
		if(LONG_CANDLE.test(currentCandle))
		{
			return CandlePatterns.LONG_CANDLE.toString();
		}
		
		if(GREEN.test(currentCandle))
		{
			return CandlePatterns.GREEN_CANDLE.toString();
		}
		
		if(RED.test(currentCandle))
		{
			return CandlePatterns.RED_CANDLE.toString();
		}
    	
		return "NA";
		
	}
	
}
