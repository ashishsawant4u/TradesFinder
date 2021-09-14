package com.trades.demo.indicators;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import com.trades.demo.models.CandleModel;


/**
 * 
 * The average true range (ATR) is a simple moving average (SMA) 
 *  or exponential moving average of the true range.
 */
public class AverageTrueRange 
{	
	Map<String, Double> trueRangeMap = new HashMap<String, Double>();
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

	private final int period;

	// constructor to initialize period
	public AverageTrueRange(int period)
	{
		this.period = period;
	}
	
	public Map<String, Double> TrueRange(List<CandleModel> symbolCandles)
	{
		trueRangeMap.clear();
		
		setupTrueRangeForAllCandles(symbolCandles);
		
		return trueRangeMap;
	}

	private void setupTrueRangeForAllCandles(List<CandleModel> symbolCandles) 
	{
		IntStream.range(0, symbolCandles.size()).forEach(index ->{
			
			CandleModel currentCandle = symbolCandles.get(index);
			CandleModel prevCandle = index==0 ? null : symbolCandles.get(index-1);
			
			trueRangeMap.put(dateFormat.format(currentCandle.getMarketDateTime()), getTrueRange(currentCandle, prevCandle));
			
		});
	}

	
	/**
	 * True range is the largest value found by solving the following three equations: 
	 * 1. TR = H − L
	 * 2. TR = H − prev_candle_close
	 * 3. TR = prev_candle_close − L
	 * 
	 *
	 */
	public double getTrueRange(CandleModel currentCandle,CandleModel prevCandle) 
	{
		double value1 = currentCandle.high - currentCandle.low;
		
		if(null!=prevCandle)
		{
			double value2 = Math.abs(currentCandle.high - prevCandle.close);
			//double value3 = Math.abs(currentCandle.low - prevCandle.close);
			double value3 = Math.abs(prevCandle.close - currentCandle.low);
			double result =  Math.max(value1, Math.max(value2, value3));
			return result;
		}
		
		return value1;
		
	}
		
	public List<CandleModel> prepareATRUsingSMA(List<CandleModel> candles)
	{	
		Map<String, Double> trueRangeMap = TrueRange(candles);
		
		List<CandleModel> candlesWithATR = new ArrayList<CandleModel>();
		
		SimpleMovingAverage sma = new SimpleMovingAverage(period);
		
		for(CandleModel candle : candles)
		{
			double trueRangeForCandle = null!= trueRangeMap.get(dateFormat.format(candle.getMarketDateTime())) ? trueRangeMap.get(dateFormat.format(candle.getMarketDateTime())) : 0.0;
			sma.addData(trueRangeForCandle);
			
			if(null!=candle.getAtr())
			{
				candle.getAtr().put(period, sma.getAvg());
			}
			else
			{
				Map<Integer, Double> atrMap = new HashMap<Integer, Double>();
				atrMap.put(period, sma.getAvg());
				candle.setAtr(atrMap);
			}
			
			
			candlesWithATR.add(candle);
		}
		
		return candlesWithATR;
	}

	
	public List<CandleModel> prepareATRUsingEMA(List<CandleModel> candleModels)
	{
		Map<String, Double> trueRangeMap = TrueRange(candleModels);
		
		List<CandleModel> candlesWithATR = new ArrayList<CandleModel>();

        double[] results = new double[candleModels.size()];
        
        for (int i = 0; i < candleModels.size(); i++) 
        {
        	double ema = calculateEmasHelper(candleModels, i , results);
        	
        	if(null!=candleModels.get(i).getAtr())
			{
        		candleModels.get(i).getAtr().put(period, ema);
			}
			else
			{
				Map<Integer, Double> atrMap = new HashMap<Integer, Double>();
				atrMap.put(period, ema);
				candleModels.get(i).setAtr(atrMap);
			}
        	
        	candlesWithATR.add(candleModels.get(i));
        }

        
        return candlesWithATR;
    }

	
	public double calculateEmasHelper(List<CandleModel> candleModels, int i, double[] results)
	{

		double trueRangeForCandle = null!= trueRangeMap.get(dateFormat.format(candleModels.get(i).getMarketDateTime())) ? trueRangeMap.get(dateFormat.format(candleModels.get(i).getMarketDateTime())) : 0.0;

		
        if(i == 0)
        {
            results[0] = trueRangeForCandle;
            return results[0];
        }
        else 
        {	
            double close = trueRangeForCandle;
            double factor = ( 2.0 / (period +1) );
            double ema =  close * factor + (1 - factor) * calculateEmasHelper(candleModels, i-1, results) ;
            results[i] = ema;
            return ema;
        }


    }

}
