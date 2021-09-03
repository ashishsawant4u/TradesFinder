package com.trades.demo.utils;

import java.util.function.Predicate;

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

    /**
     * Candle pattern where NONE of the shadows is bigger than the body
     */
    public static final Predicate<CandleModel> NO_SHADOW_LARGER_THAN_BODY = ANY_SHADOW_LARGER_THAN_BODY.negate();
	
    
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
    
    public static String findCandlePattern(CandleModel currentCandle)
	{
		
		return "NA";
		
	}
	
}
