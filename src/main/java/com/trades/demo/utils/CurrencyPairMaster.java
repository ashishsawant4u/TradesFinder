package com.trades.demo.utils;

import java.util.HashMap;
import java.util.Map;

import com.trades.demo.models.CurrencyPairModel;

public class CurrencyPairMaster 
{
	private static CurrencyPairModel usd_inr = new CurrencyPairModel("USDINR", 0.0025, 1000, 0.0025*1000);
	
	static Map<String, CurrencyPairModel> currencyPairMasterMap = new HashMap<String, CurrencyPairModel>();
	
	public static void prepareCurrencyData()
	{
		currencyPairMasterMap.put("USDINR", usd_inr);
	}
	public static CurrencyPairModel getCurrencyPairInfo(String currencyPair) 
	{
		prepareCurrencyData();
		
		return currencyPairMasterMap.get(currencyPair.toUpperCase());
	}
}
