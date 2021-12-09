<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>   

<form class="row g-3">

<div class="col-md-6 bg-primary bg-gradient p-2">
    <label for="stockName" class="form-label text-white">Stock / Scrip</label>
    <div class="input-group">
    <input type="text" class="form-control" id="stockName">
  	</div>
 </div>
 <div class="col-md-6">
    <label for="studyDate" class="form-label">Date</label>
    <div class="input-group">
    <span class="input-group-text steps-input"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-calendar2-week-fill" viewBox="0 0 16 16">  <path d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5zm9.954 3H2.545c-.3 0-.545.224-.545.5v1c0 .276.244.5.545.5h10.91c.3 0 .545-.224.545-.5v-1c0-.276-.244-.5-.546-.5zM8.5 7a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1zM3 10.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5zm3.5-.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1z"/></svg></span>
    <input type="text" class="form-control datepicker" id="studyDate">
  	</div>
</div>

<div class="col-md-6">
	<label for="tide" class="form-label">Tide</label>
	<div class="input-group">
    <span class="input-group-text steps-input">1</span>
	<input type="text" class="form-control" id="tideText">
	</div>
	  <small id="tideHelp" class="form-text text-muted">
	  	MACD Uptick or Flat after down (ie Trend is Up) BUY SIGNAL <br>
	  	MACD Downtick or Flat after UP (ie Trend is down) SELL SIGNAL
	  </small>
</div>
<div class="col-md-6">
	<label for="wave" class="form-label">Wave</label>
	<div class="input-group">
    <span class="input-group-text steps-input">2</span>
	<input type="text" class="form-control" id="waveText">
	</div>
	<small id="waveHelp" class="form-text text-muted">
	  	Osc - (Uptick/PCO) Stoc or RSI giving BUY SIGNAL <br>
	  	Osc - (Downtick/NCO) Stoc or RSI giving SELL SIGNAL
	 </small>
</div>

<div class="col-md-6">
	<label for="dssDecision" class="form-label">Double Screen Decision</label>
	<div class="btn-group" role="group" aria-label="Basic radio toggle button group">
	  <input type="radio" class="btn-check btnradioBuySell" name="btnradioBuySell" id="btnradioBuy" autocomplete="off" value="BUY">
	  <label class="btn btn-outline-success cursor-pointer" for="btnradioBuy">BUY</label>
	
	  <input type="radio" class="btn-check btnradioBuySell" name="btnradioBuySell" id="btnradioShortSell" autocomplete="off" value="SELL">
	  <label class="btn btn-outline-danger cursor-pointer" for="btnradioShortSell">SHORT SELL</label>
	</div>
</div>
<div class="col-md-6 d-flex justify-content-center">
<img alt="BULL HAT" src="/tradesfinder/images/bull-hat.png" class="d-none w-50 float-start" id="bullHatImg">
<img alt="BULL HAT" src="/tradesfinder/images/bear-hat.png" class="d-none w-50 float-start" id="bearHatImg">
</div>


<div class="col-md-6 bullishCandlestickPattern">
	<label for="bullishCandlestickPattern" class="form-label">Bullish Candlestick Pattern</label>
	<div class="input-group">
    <span class="input-group-text steps-input">4</span>
	<select class="form-select" aria-label="Candlestick Pattern" id="bullishCandlestickPattern">
	  <option selected>Select Candlestick Pattern</option>
	  <option value="Bullish Green Candle">Bullish Green Candle</option>
	  <option value="Bullish Piercing">Bullish Piercing</option>
	  <option value="Bullish Engulf">Bullish Engulf</option>
	  <option value="Hammer">Hammer (Strong Signal if at Bottom)</option>
	  <option value="Morning Star">Morning Star</option>
	  </select>
  </div>
</div>

<div class="col-md-6 bearishCandlestickPattern">
	<label for="bearishCandlestickPattern" class="form-label">Bearish Candlestick Pattern</label>
	<div class="input-group">
    <span class="input-group-text steps-input">4</span>
	<select class="form-select" aria-label="Candlestick Pattern" id="bearishCandlestickPattern">
	  <option selected>Select Candlestick Pattern</option>
	  <option value="Bearish Green Candle">Bearish Red Candle</option>
	  <option value="Bearish Piercing">Bearish Piercing</option>
	  <option value="Bearish Engulf">Bearish Engulf</option>
	  <option value="Inverted Hammer">Inverted Hammer (Strong Signal if at Top)</option>
	  <option value="Evening Star">Evening Star</option>
  </select>
  </div>
</div>

<div class="col-md-6">
	<label for="volume" class="form-label">Volume on most significant candle</label>
	<div class="input-group">
    <span class="input-group-text steps-input">5</span>
    <input type="text" class="form-control" id="volumeText">
    </div>
</div>

<div class="col-md-6">
	<label for="emaState" class="form-label">Moving Average EMA</label>
	<div class="input-group">
    <span class="input-group-text steps-input">6</span>
    <select class="form-select" aria-label="Select EMA State" id="emaState">
	  <option selected>Select EMA State</option>
	  <option value="PCO">Positive Crossover State</option>
	  <option value="NCO">Negative Crossover State</option>
	  <option value="Golden Crossover PCO">Golden Crossover PCO</option>
	  <option value="Golden Crossover NCO">Golden Crossover NCO</option>
	  <option value="Partial PCO">Partial PCO</option>
	  <option value="Partial NCO">Partial NCO</option>
	  <option value="Not Applicable">Not Applicable</option>
	  </select>
    </div>
    <small id="emaHelp" class="form-text text-muted">
	  	Ignore if Morning Start/Evening Star <br>
	  	Ignore if Double Top/Double Bottom
	  </small>
</div>


<div class="col-md-6 bullishChartPatern">
	<label for="bullishChartPatern" class="form-label">Bullish Chart Pattern</label>
	<div class="input-group">
    <span class="input-group-text steps-input">7</span>
     <select class="form-select" aria-label="Select Chart Pattern" id="bullishChartPatern">
	  <option selected>Select Chart Pattern</option>
	  <option value="Inverted Head And Shoulder">Inverted Head And Shoulder</option>
	  <option value="Double Bottom">Double Bottom</option>
	  <option value="Cup And Handle">Cup And Handle</option>
	  <option value="Breakout Of Flag">Breakout Of Flag</option>
	  <option value="Fake Breakdown">Fake Breakdown</option>
	  <option value="Not Found">Not Found</option>
	  </select>
    </div>
</div>

<div class="col-md-6 bearishChartPatern">
	<label for="bearishChartPatern" class="form-label">Bearish Chart Pattern</label>
	<div class="input-group">
    <span class="input-group-text steps-input">7</span>
      <select class="form-select" aria-label="Select Chart Pattern" id="bearishChartPatern">
	  <option selected>Select Chart Pattern</option>
	  <option value="Head And Shoulder">Head And Shoulder</option>
	  <option value="Double Top">Double Top</option>
	  <option value="Rounding Top">Rounding Top</option>
	  <option value="Breakdown Of Flag">Breakdown Of Flag</option>
	  <option value="Fake Breakout">Fake Breakout</option>
	  <option value="Not Found">Not Found</option>
	  </select>
    </div>
</div>


<div class="col-md-6">
	<label for="fibRetracement" class="form-label">Fib Retracement</label>
	<div class="input-group">
    <span class="input-group-text steps-input">8</span>
	<input type="text" class="form-control" id="fibRetracement" >
	</div>
	 <small id="fibHelp" class="form-text text-muted">
	  	Only for continuation pattern <br>
	  	Upto 50% Healthy
	  </small>
</div>
<div class="col-md-6">
	<label for="divergence" class="form-label">Divergence (Osc.)</label>
	<div class="input-group">
    <span class="input-group-text steps-input">9</span>
	<input type="text" class="form-control" id="divergence">
	</div>
	 <small id="divergenceHelp" class="form-text text-muted">
	  	Specially for top and bottom fishing
	  </small>
</div>

<div class="col-md-6 ">
	<label for="tradingStyle" class="form-label">Trading Style</label>
	<div class="input-group">
    <span class="input-group-text steps-input">10</span>
      <select class="form-select" aria-label="Select Trading Style" id="tradingStyle">
	  <option selected>Select Trading Style</option>
	  <option value="Momentum Trading">Momentum Trading</option>
	  <option value="Swing Trading">Swing Trading</option>
	  </select>
    </div>
</div>


<div class="col-md-6 ">
	<label for="papaTradeSetup" class="form-label">PAPA Trade Setup</label>
	<div class="input-group">
    <span class="input-group-text steps-input">11</span>
      <select class="form-select" aria-label="Select PAPA Trade Setup" id=""papaTradeSetup"">
	  <option selected>Select PAPA Trade Setup</option>
	  <option value="Double Top">Double Top</option>
	  <option value="Double Bottom">Double Bottom</option>
	  <option value="Three White Soldiers">Three White Soldiers</option>
	  <option value="Three Black Crows">Three Black Crows</option>
	  <option value="Bulls Counter Attack">Bulls Counter Attack</option>
	  <option value="Bears Counter Attack">Bears Counter Attack</option>
	  <option value="Sandwich Pattern">Sandwich Pattern</option>
	  <option value="Rounding Bottom">Rounding Bottom</option>
	  <option value="Rounding Top">Rounding Top</option>
	  <option value="Genuine Breakout">Genuine Breakout</option>
	  <option value="Genuine Breakdown">Genuine Breakdown</option>
	  <option value="Fake Breakout">Fake Breakout</option>
	  <option value="Fake Breakdown">Fake Breakdown</option>
	  <option value="Gap Up">Gap Up</option>
	  <option value="Gap Down">Gap Down</option>
	  <option value="Not Found">Not Found</option>
	  </select>
    </div>
</div>


<tags:momentumSetup/>

<tags:swingSetup/>


<div class="col-md-12">
	<label for="tradeComment" class="form-label">Comment</label>
	<div class="input-group">
	<textarea class="form-control" rows="3" id="tradeComment"></textarea>
	</div>
</div>

</form>