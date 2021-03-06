package com.trades.demo.reports;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.trades.demo.common.TradeConstants;
import com.trades.demo.strategy.QuantityPlanner;

public class OrderTracker 
{
	public static List<Order> ORDER_LIST = new ArrayList<Order>();
	
	public static List<Order> orderTracker()
	{
		Collections.sort(ORDER_LIST, (o1, o2) -> o1.getMarketDateTime().compareTo(o2.getMarketDateTime()));
	
		ORDER_LIST.forEach(order ->{
			
			if(order.getOrderType().equals(TradeConstants.ORDER_TYPE_BUY))
			{
				QuantityPlanner.CAPITAL_AMOUNT = QuantityPlanner.CAPITAL_AMOUNT - order.orderAmount;
				order.setCapitalBalance(QuantityPlanner.CAPITAL_AMOUNT);
				
			}
			
			if(order.getOrderType().equals(TradeConstants.ORDER_TYPE_SELL))
			{
				QuantityPlanner.CAPITAL_AMOUNT = QuantityPlanner.CAPITAL_AMOUNT + order.orderAmount;
				order.setCapitalBalance(QuantityPlanner.CAPITAL_AMOUNT);
			}
			
		});
		
		return ORDER_LIST;
	}
}
