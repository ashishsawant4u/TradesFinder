package com.trades.demo.common;

import java.util.List;

import com.trades.demo.models.CandleModel;

public class TradeConstants 
{
	public static int LONG_GREEN_CANDLE_PERIOD = 15;
	
	public static List<CandleModel> ALL_CANDLES; 
	
	public static boolean ENABLE_QTY_PLANNER = false;
	
	public static String ORDER_TYPE_BUY = "BUY";
	
	public static String ORDER_TYPE_SELL = "SELL";
	
	public static double STRATEGY_RISK_RATIO = 1;
	
	public static double STRATEGY_REWARD_RATIO = 2.0;
}
