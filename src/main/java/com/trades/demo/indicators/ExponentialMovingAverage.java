package com.trades.demo.indicators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.trades.demo.models.CandleModel;

public class ExponentialMovingAverage 
{
	private final int period;
	
	// constructor to initialize period
	public ExponentialMovingAverage(int period)
	{
		this.period = period;
	}
	
	public List<CandleModel> calculateEmaValues(List<CandleModel> candleModels)
	{
		
		List<CandleModel> candlesWithEMA = new ArrayList<CandleModel>();

        double[] results = new double[candleModels.size()];
        
        for (int i = 0; i < candleModels.size(); i++) 
        {
        	double ema = calculateEmasHelper(candleModels, i , results);
        	
        	if(null!=candleModels.get(i).getEma())
			{
        		candleModels.get(i).getEma().put(period, ema);
			}
			else
			{
				Map<Integer, Double> emaMap = new HashMap<Integer, Double>();
				emaMap.put(period, ema);
				candleModels.get(i).setEma(emaMap);
			}
        	
        	candlesWithEMA.add(candleModels.get(i));
        }

        
        return candlesWithEMA;
    }

	
	public double calculateEmasHelper(List<CandleModel> candleModels, int i, double[] results)
	{

        if(i == 0)
        {
            results[0] = candleModels.get(i).getClose();
            return results[0];
        }
        else 
        {	
            double close = candleModels.get(i).getClose();
            double factor = ( 2.0 / (period +1) );
            double ema =  close * factor + (1 - factor) * calculateEmasHelper(candleModels, i-1, results) ;
            results[i] = ema;
            return ema;
        }


    }
}
