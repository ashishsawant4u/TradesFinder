<%@ tag language="java" pageEncoding="ISO-8859-1"%>

<form class="row g-3">

<div class="col-md-6 bg-primary bg-gradient p-2">
    <div class="input-group">
    <label for="stockName" class="input-group-text">Stock / Scrip</label>
    <input type="text" class="form-control" id="stockName">
  	</div>
 </div>
 <div class="col-md-6">
    <label for="studyDate" class="form-label">Date</label>
    <div class="input-group">
    <span class="input-group-text steps-input"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-calendar2-week-fill" viewBox="0 0 16 16">  <path d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5zm9.954 3H2.545c-.3 0-.545.224-.545.5v1c0 .276.244.5.545.5h10.91c.3 0 .545-.224.545-.5v-1c0-.276-.244-.5-.546-.5zM8.5 7a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1zM3 10.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5zm3.5-.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1z"/></svg></span>
    <input type="text" class="form-control" id="studyDate">
  	</div>
</div>

<div class="col-md-6">
	<label for="tide" class="form-label">Tide</label>
	<div class="input-group">
    <span class="input-group-text steps-input">1</span>
	<input type="text" class="form-control" id="tideText" >
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
	  <input type="radio" class="btn-check btnradioBuySell" name="btnradioBuySell" id="btnradioBuy" autocomplete="off" checked>
	  <label class="btn btn-outline-success cursor-pointer" for="btnradioBuy">BUY</label>
	
	  <input type="radio" class="btn-check btnradioBuySell" name="btnradioBuySell" id="btnradioShortSell" autocomplete="off">
	  <label class="btn btn-outline-danger cursor-pointer" for="btnradioShortSell">SHORT SELL</label>
	</div>
</div>
<div class="col-md-6">
</div>


<div class="col-md-6">
	<label for="bullishCandlestickPattern" class="form-label">Bullish Candlestick Pattern</label>
	<div class="input-group">
    <span class="input-group-text steps-input">4</span>
	<select class="form-select" aria-label="Candlestick Pattern" id="bullishCandlestickPattern">
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
	<label for="bearishCandlestickPattern" class="form-label">Bearish Candlestick Pattern</label>
	<div class="input-group">
    <span class="input-group-text steps-input">5</span>
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
    <span class="input-group-text steps-input">6</span>
    <input type="text" class="form-control" id="volumeText">
    </div>
</div>

<div class="col-md-6">
	<label for="emaState" class="form-label">Moving Average EMA</label>
	<div class="input-group">
    <span class="input-group-text steps-input">7</span>
    <select class="form-select" aria-label="Select EMA State" id="emaState">
	  <option selected>Select EMA State</option>
	  <option value="PCO">Positive Crossover State</option>
	  <option value="NCO">Negative Crossover State</option>
	  <option value="Golden Crossover PCO">Golden Crossover PCO</option>
	  <option value="Golden Crossover NCO">Golden Crossover NCO</option>
	  <option value="Not Applicable">Not Applicable</option>
	  </select>
    </div>
    <small id="emaHelp" class="form-text text-muted">
	  	Ignore if Morning Start/Evening Star <br>
	  	Ignore if Double Top/Double Bottom
	  </small>
</div>


<div class="col-md-6">
	<label for="bullishChartPatern" class="form-label">Bullish Chart Pattern</label>
	<div class="input-group">
    <span class="input-group-text steps-input">8</span>
     <select class="form-select" aria-label="Select Chart Pattern" id="bullishChartPatern">
	  <option selected>Select Chart Pattern</option>
	  <option value="Inverted Head And Shoulder">Inverted Head And Shoulder</option>
	  <option value="Double Bottom">Double Bottom</option>
	  <option value="Cup And Handle">Cup And Handle</option>
	  <option value="Breakout Of Flag">Breakout Of Flag</option>
	  <option value="Fake Breakdown">Fake Breakdown</option>
	  </select>
    </div>
</div>

<div class="col-md-6">
	<label for="bearishChartPatern" class="form-label">Bearish Chart Pattern</label>
	<div class="input-group">
    <span class="input-group-text steps-input">9</span>
      <select class="form-select" aria-label="Select Chart Pattern">
	  <option selected>Select Chart Pattern</option>
	  <option value="Head And Shoulder">Head And Shoulder</option>
	  <option value="Double Top">Double Top</option>
	  <option value="Rounding Top">Rounding Top</option>
	  <option value="Breakdown Of Flag">Breakdown Of Flag</option>
	  <option value="Fake Breakout">Fake Breakout</option>
	  </select>
    </div>
</div>


<div class="col-md-6">
	<label for="fibRetracement" class="form-label">Fib Retracement</label>
	<div class="input-group">
    <span class="input-group-text steps-input">10</span>
	<input type="text" class="form-control" id="fibRetracement" >
	</div>
	 <small id="fibHelp" class="form-text text-muted">
	  	Only for continuation pattern
	  </small>
</div>
<div class="col-md-6">
	<label for="divergence" class="form-label">Divergence (Osc.)</label>
	<div class="input-group">
    <span class="input-group-text steps-input">11</span>
	<input type="text" class="form-control" id="divergence">
	</div>
	 <small id="divergenceHelp" class="form-text text-muted">
	  	Specially for top and bottom fishing
	  </small>
</div>

</form>