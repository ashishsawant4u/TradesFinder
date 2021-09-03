package com.trades.demo.indicators;

//Java program to calculate
//Simple Moving Average
import java.util.*;

import com.trades.demo.models.CandleModel;

public class SimpleMovingAverage 
{
	
	// queue used to store list so that we get the average
	private final Queue<Double> Dataset = new LinkedList<Double>();
	private final int period;
	private double sum;

	// constructor to initialize period
	public SimpleMovingAverage(int period)
	{
		this.period = period;
	}

	// function to add new data in the
	// list and update the sum so that
	// we get the new mean
	public void addData(double num)
	{
		sum += num;
		Dataset.add(num);

		// Updating size so that length
		// of data set should be equal
		// to period as a normal mean has
		if (Dataset.size() > period)
		{
			sum -= Dataset.remove();
		}
	}

	// function to calculate mean
	public double getAvg()
	{
		return sum / period;
	}
	
	public List<CandleModel> prepareSMA(List<CandleModel> candles)
	{
		List<CandleModel> candlesWithSMA = new ArrayList<CandleModel>();
		
		SimpleMovingAverage sma = new SimpleMovingAverage(period);
		
		for(CandleModel candle : candles)
		{
			sma.addData(candle.getClose());
			
			if(null!=candle.getSma())
			{
				candle.getSma().put(period, sma.getAvg());
			}
			else
			{
				Map<Integer, Double> smaMap = new HashMap<Integer, Double>();
				smaMap.put(period, sma.getAvg());
				candle.setSma(smaMap);
			}
			
			
			candlesWithSMA.add(candle);
		}
		
		return candlesWithSMA;
	}

}
