<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>   

<form class="row g-3">
  <div class="col-md-4 theme2 p-1">
    <label for="capitalAmount" class="form-label">Capital</label>
    <div class="input-group">
    <span class="input-group-text"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-piggy-bank-fill" viewBox="0 0 16 16">  <path fill-rule="evenodd" d="M7.964 1.527c-2.977 0-5.571 1.704-6.32 4.125h-.55A1 1 0 0 0 .11 6.824l.254 1.46a1.5 1.5 0 0 0 1.478 1.243h.263c.3.513.688.978 1.145 1.382l-.729 2.477a.5.5 0 0 0 .48.641h2a.5.5 0 0 0 .471-.332l.482-1.351c.635.173 1.31.267 2.011.267.707 0 1.388-.095 2.028-.272l.543 1.372a.5.5 0 0 0 .465.316h2a.5.5 0 0 0 .478-.645l-.761-2.506C13.81 9.895 14.5 8.559 14.5 7.069c0-.145-.007-.29-.02-.431.261-.11.508-.266.705-.444.315.306.815.306.815-.417 0 .223-.5.223-.461-.026a.95.95 0 0 0 .09-.255.7.7 0 0 0-.202-.645.58.58 0 0 0-.707-.098.735.735 0 0 0-.375.562c-.024.243.082.48.32.654a2.112 2.112 0 0 1-.259.153c-.534-2.664-3.284-4.595-6.442-4.595zm7.173 3.876a.565.565 0 0 1-.098.21.704.704 0 0 1-.044-.025c-.146-.09-.157-.175-.152-.223a.236.236 0 0 1 .117-.173c.049-.027.08-.021.113.012a.202.202 0 0 1 .064.199zm-8.999-.65A6.613 6.613 0 0 1 7.964 4.5c.666 0 1.303.097 1.893.273a.5.5 0 1 0 .286-.958A7.601 7.601 0 0 0 7.964 3.5c-.734 0-1.441.103-2.102.292a.5.5 0 1 0 .276.962zM5 6.25a.75.75 0 1 1-1.5 0 .75.75 0 0 1 1.5 0z"/></svg></span>
    <input type="text" class="form-control" id="capitalAmount">
    </div>
  </div>
  <div class="col-md-4 theme2 p-1">
    <label for="percentageRiskPerTrade" class="form-label">Percentage Risk Per Trade</label>
    <div class="input-group">
        <span class="input-group-text"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-exclamation-circle-fill" viewBox="0 0 16 16"><path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM8 4a.905.905 0 0 0-.9.995l.35 3.507a.552.552 0 0 0 1.1 0l.35-3.507A.905.905 0 0 0 8 4zm.002 6a1 1 0 1 0 0 2 1 1 0 0 0 0-2z"/></svg></span>
        <input type="text" class="form-control" id="percentageRiskPerTrade">
	    <span class="input-group-text"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-percent" viewBox="0 0 16 16">  <path d="M13.442 2.558a.625.625 0 0 1 0 .884l-10 10a.625.625 0 1 1-.884-.884l10-10a.625.625 0 0 1 .884 0zM4.5 6a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm0 1a2.5 2.5 0 1 0 0-5 2.5 2.5 0 0 0 0 5zm7 6a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm0 1a2.5 2.5 0 1 0 0-5 2.5 2.5 0 0 0 0 5z"/></svg></span>
    </div>
  </div>
  <div class="col-md-4 theme2 p-1">
    <label for="maxRiskPerTrade" class="form-label">Max Risk Per Trade</label>
  	<div class="input-group">
  	<span class="input-group-text">&#8377;</span>
    <input type="text" class="form-control" id="maxRiskPerTrade" disabled>
    </div>
  </div>
  
  <div class="col-md-6 bulls-sections">
    <label for="immediateSupportForStopLoss" class="form-label">Immediate Support for stop loss</label>
    <div class="input-group">
  	<span class="input-group-text steps-input">10</span>
    <input type="text" class="form-control" id="immediateSupportForStopLoss">
    </div>
     <small id="bslHelp" class="form-text text-muted">
	  	Bulls- use it for stop loss <br>
	  	It can be weapon candle low <br>
	  	or below the osc. pco candle <br>
	  	or the median of most significant candle ie one with heavy volume
	 </small>
  </div>
  <div class="col-md-6 bulls-sections">
    <label for="majorResistanceForTarget" class="form-label">Major Resistance for target</label>
    <div class="input-group">
  	<span class="input-group-text steps-input">11</span>
    <input type="text" class="form-control" id="majorResistanceForTarget">
    </div>
    <small id="bTarHelp" class="form-text text-muted">
	  	Bulls- check for exit target <br>
	  	Use descending resistance trendline or MAs <br>
	  	or prev. tops or resistances
	 </small>
  </div>
  
  
  <div class="col-md-6 bears-sections">
    <label for="immediateResistanceForStopLoss" class="form-label">Immediate Resistance for stop loss</label>
    <div class="input-group">
  	<span class="input-group-text steps-input">10</span>
    <input type="text" class="form-control" id="immediateResistanceForStopLoss">
  	</div>
  	<small id="berSlHelp" class="form-text text-muted">
	  	Bears- use it for stop loss <br>
	  	It can be top of the weapon candle <br>
	  	or above the osc. nco candle <br>
	  	or the median of most significant candle ie one with heavy volume
	 </small>
  </div>
  <div class="col-md-6 bears-sections">
    <label for="majorSupportForTarget" class="form-label">Major Support for target</label>
    <div class="input-group">
  	<span class="input-group-text steps-input">11</span>
    <input type="text" class="form-control" id="majorSupportForTarget">
  	</div>
  	<small id="berTarHelp" class="form-text text-muted">
	  	Bears- check for exit target
	  	Use ascending uptrend trendline or MAs <br>
	  	prv. bottoms or support
	 </small>
  </div>
  
  <div class="col-md-4">
    <label for="closePrice" class="form-label">Close Price</label>
     <div class="input-group">
    <span class="input-group-text steps-input">12</span>
    <input type="text" class="form-control" id="closePrice">
    </div>
  </div>
  <div class="col-md-4">
    <label for="entryPrice" class="form-label">Entry Price</label>
     <div class="input-group">
    <span class="input-group-text steps-input">13</span>
    <input type="text" class="form-control" id="entryPrice">
    </div>
  </div>
  <div class="col-md-4">
    <label for="stopLossPrice" class="form-label">Stop Loss</label>
     <div class="input-group">
    <span class="input-group-text steps-input">14</span>
    <input type="text" class="form-control" id="stopLossPrice">
    </div>
  </div>
  
  <div class="col-md-4">
    <label for="minTargetPrice" class="form-label">Min. Target Price</label>
    <div class="input-group">
    <span class="input-group-text steps-input">15</span>
    <input type="text" class="form-control" id="minTargetPrice">
    </div>
  </div>
  <div class="col-md-4">
    <label for="maxTargetPrice" class="form-label">Max. Target Price</label>
    <div class="input-group">
    <span class="input-group-text steps-input">16</span>
    <input type="text" class="form-control" id="maxTargetPrice">
 	</div>
  </div>
  <div class="col-md-4 pt-4 ps-5 align-middle">
  	 <button type="button" class="btn btn-primary btn-lg" id="dssTradeCalBtn">Calculate Trade</button>
  </div>
  
  <div class="col-md-12">
	  <tags:alerts/>
  </div>
  
  <div class="col-md-3 theme2 p-1">
  	<label for="quantity" class="form-label">Max Quantity</label>
    <div class="input-group">
    <span class="input-group-text"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cart-fill" viewBox="0 0 16 16">  <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/></svg></span>
     <input type="text" class="form-control" id="quantity" disabled>
    </div>
  </div>
  <div class="col-md-3 theme2 p-1">
  	<label for="tradeInvestment" class="form-label">Trade Investment</label>
    <div class="input-group">
	<span class="input-group-text">&#8377;</span>
     <input type="text" class="form-control" id="tradeInvestment" disabled>
    </div>
  </div>
  
  
  <div class="col-md-3 theme2 p-1 riskRewardRatioDiv">
    <label for="capitalAmount" class="form-label text-white">Risk Reward Ratio</label>
    <div class="input-group">
	<label for="minRR" class="input-group-text">Min</label>
    <input type="text" class="form-control" id="minRR" disabled>
    </div>
     <small id="minRewHelp" class="text-white">
	  	Should be atleast 3 or above to enter trade
	 </small>
  </div>
  <div class="col-md-3 theme2 p-1 riskRewardRatioDiv">
    <label for="capitalAmount" class="form-label text-white">Risk Reward Ratio</label>
    <div class="input-group">
	<label for="maxRR" class="input-group-text">Max</label>
    <input type="text" class="form-control" id="maxRR" disabled>
    </div>
  </div>
  
  <!-- <div class="col-md-4 p-1">
    <label for="tradeDecision" class="form-label">Trade Decision</label>
	<h2><span class="d-none badge rounded-pill bg-warning text-dark d-flex justify-content-center mt-4" id="tradeDecisionGood"> 
	Go for it !!!</span></h2>
	
	<h2><span class="d-none badge rounded-pill badge bg-danger text-white d-flex justify-content-center mt-4" id="tradeDecisionBad">
	Improve your R:R</span></h2>
	
  </div> -->
  

  <div class="col-md-3 theme2 p-1">
    <label for="minReward" class="form-label">Min. Reward</label>
    <div class="input-group">
    <span class="input-group-text">&#8377;</span>
    <input type="text" class="form-control" id="minReward" disabled>
    </div>
  </div>
  <div class="col-md-3 theme2 p-1">
    <label for="maxReward" class="form-label">Max. Reward</label>
    <div class="input-group">
    <span class="input-group-text">&#8377;</span>
    <input type="text" class="form-control" id="maxReward" disabled>
    </div>
  </div>
  
  <div class="col-md-3 theme2 p-1">
    <label for="riskPerUnit" class="form-label">Risk per unit</label>
    <div class="input-group">
    <span class="input-group-text">&#8377;</span>
    <input type="text" class="form-control" id="riskPerUnit" disabled>
    </div>
  </div>
  <div class="col-md-3 theme2 p-1">
    <label for="totalRisk" class="form-label">Total Risk</label>
    <div class="input-group">
    <span class="input-group-text">&#8377;</span>
    <input type="text" class="form-control" id="totalRisk" disabled>
    </div>
  </div>
  
   <div class="col-md-3 theme2 p-1">
    <label for="minProfitPotential" class="form-label">Min. Profit Potential</label>
    <div class="input-group">
    <span class="input-group-text">&#8377;</span>
    <input type="text" class="form-control" id="minProfitPotential" disabled>
    </div>
  </div>
  <div class="col-md-3 theme2 p-1">
    <label for="maxProfitPotential" class="form-label">Max. Profit Potential</label>
    <div class="input-group">
    <span class="input-group-text">&#8377;</span>
    <input type="text" class="form-control" id="maxProfitPotential" disabled>
    </div>
  </div>
  
  <div class="col-md-3 theme2 p-1">
    <label for="minROI" class="form-label">Min. ROI</label>
    <div class="input-group">
    <input type="text" class="form-control" id="minROI" disabled>
    <span class="input-group-text"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-percent" viewBox="0 0 16 16">  <path d="M13.442 2.558a.625.625 0 0 1 0 .884l-10 10a.625.625 0 1 1-.884-.884l10-10a.625.625 0 0 1 .884 0zM4.5 6a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm0 1a2.5 2.5 0 1 0 0-5 2.5 2.5 0 0 0 0 5zm7 6a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm0 1a2.5 2.5 0 1 0 0-5 2.5 2.5 0 0 0 0 5z"/></svg></span>
  	</div>
  </div>
  <div class="col-md-3 theme2 p-1">
   <label for="maxROI" class="form-label">Max. ROI</label>
    <div class="input-group">
    <input type="text" class="form-control" id="maxROI" disabled>
    <span class="input-group-text"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-percent" viewBox="0 0 16 16">  <path d="M13.442 2.558a.625.625 0 0 1 0 .884l-10 10a.625.625 0 1 1-.884-.884l10-10a.625.625 0 0 1 .884 0zM4.5 6a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm0 1a2.5 2.5 0 1 0 0-5 2.5 2.5 0 0 0 0 5zm7 6a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm0 1a2.5 2.5 0 1 0 0-5 2.5 2.5 0 0 0 0 5z"/></svg></span>
  	</div>
  </div>
  
  <div class="col-md-6">
	<label for="tradeState" class="form-label">Trade State</label>
	<div class="input-group">
    <span class="input-group-text steps-input">17</span>
    <select class="form-select" aria-label="Select Trade State" id="tradeState">
	  <option selected>Select Trade State</option>
	  <option value="Watchful">Watchful</option>
	  <option value="Go for it">Go for it</option>
	  <option value="Ignore">Ignore</option>
	  <option value="In Progress">In Progress</option>
	  <option value="Completed">Completed</option>
	  </select>
    </div>
	</div>
  <div class="col-md-4 pt-4 ps-1 align-middle dssTradeLogBtn d-none">
  	 <button type="button" class="btn btn-primary btn-lg" id="dssTradeLogBtn">Save Trade</button>
  </div>
  

</form>