<%@ tag language="java" pageEncoding="ISO-8859-1"%>

<div class="row pt-1 pb-1">
<div class="row ps-5" id="cur_buySellToggleDiv">
	<div class="btn-group" role="group" aria-label="Basic radio toggle button group">
	  <input type="radio" class="btn-check cur_btnradioBuySell" name="cur_btnradioBuySell" id="cur_btnradioBuy" autocomplete="off" value="BUY">
	  <label class="btn btn-outline-success cursor-pointer" for="cur_btnradioBuy">BUY</label>
	
	  <input type="radio" class="btn-check cur_btnradioBuySell" name="cur_btnradioBuySell" id="cur_btnradioShortSell" autocomplete="off" value="SELL">
	  <label class="btn btn-outline-danger cursor-pointer" for="cur_btnradioShortSell">SELL</label>
	</div>
</div>
</div>

<div class="row pt-1 theme1 pb-1 content-grp1" id="cur_entrysection">

<div class="col-md-2">
    <label for="cur_entryPrice" class="form-label">Entry Price</label>
     <div class="input-group">
    <input type="text" class="form-control only-num" id="cur_entryPrice">
    </div>
  </div>
  <div class="col-md-2">
    <label for="cur_stopLossPrice" class="form-label">Stop Loss</label>
     <div class="input-group">
    <input type="text" class="form-control only-num" id="cur_stopLossPrice">
    </div>
  </div>
  
  <div class="col-md-2">
    <label for="cur_targetPrice" class="form-label">Target Price</label>
    <div class="input-group">
    <input type="text" class="form-control only-num" id="cur_targetPrice">
    </div>
  </div>
  
  <div class="col-md-2">
    <label for="cur_numberOfLots" class="form-label">Nos. Lots</label>
    <div class="input-group">
    <input type="text" class="form-control only-num" id="cur_numberOfLots">
    </div>
  </div>
  
  <div class="col-md-2">
    <label for="cur_totalAmountForAllLots" class="form-label">Total Amount</label>
    <div class="input-group">
    <input type="text" class="form-control only-num" id="cur_totalAmountForAllLots" disabled>
    </div>
  </div>
  
  <div class="col-md-2">
    <label for="cur_marginAmountForAllLots" class="form-label">Margin Amt.</label>
    <div class="input-group">
    <input type="text" class="form-control only-num" id="cur_marginAmountForAllLots">
    </div>
  </div>

	
</div>
<div class="row">
	<div class="text-end">
	 <small id="marginHelp" class="form-text text-danger">
	  	Take margin amount from broker as per number of lots
	 </small>
	 </div>
</div>
<div class="row pt-1 pb-1">
 <div class="col-md-6 ps-0">
 		<button type="button" class="btn theme1" id="cur_TradeCalBtn">Calculate Trade</button>
 </div>
 <div class="col-md-3">
    <div class="input-group">
    <span class="input-group-text">R:R</span>
     <input type="text" class="form-control" id="cur_riskReward" disabled>
    </div>
  </div>
 <div class="col-md-3">
    <div class="input-group">
    <span class="input-group-text"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cart-fill" viewBox="0 0 16 16">  <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/></svg></span>
     <input type="text" class="form-control" id="cur_quantity" disabled>
    </div>
  </div>
 
</div>