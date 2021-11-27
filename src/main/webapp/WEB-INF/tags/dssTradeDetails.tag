<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ attribute name="trade" required="true" type="com.trades.demo.forms.DoubleScreenTradeForm" %>

<table class="table table-sm table-bordered" id="tradeEntryTable">
	<thead style="position: sticky;top: 0" class="table-primary">
	    <tr>
	      <th scope="col">Stock</th>
	      <th scope="col">Date</th>
	      <th scope="col">DSS Decision</th>
	      <th scope="col">Entry Price</th>
	      <th scope="col">Stop Loss Price</th>
	      <th scope="col">Target Price</th>
	      <th scope="col">Target 2 Price</th>
	      <th scope="col">Quantity</th>
	      <th scope="col">Investment</th>
	      <th scope="col">Risk per unit</th>
	      <th scope="col">Total Risk</th>
	      <th scope="col">Trade State</th>
	    </tr>
  </thead>
  <tbody class="searchable">	
  		<tr>
  			<td class="border">${trade.stock}</td>
			<td class="border">${trade.date}</td>
			<td class="border">${trade.dssDecision}</td>
			<td class="border">${trade.entryPrice}</td>
			<td class="border">${trade.stopLossPrice}</td>
			<td class="border">${trade.minTargetPrice}</td>
			<td class="border">${trade.maxTargetPrice}</td>
			<td class="border">${trade.quantity}</td>
			<td class="border">${trade.tradeInvestment}</td>
			<td class="border">${trade.riskPerUnit}</td>
			<td class="border">${trade.totalRisk}</td>
			<td class="border">${trade.tradeState}
  		</tr>
   </tbody>
</table>


<table class="table table-sm table-bordered" id="tradeStudyTable">
	<thead style="position: sticky;top: 0" class="table-primary">
	    <tr>
	      <th scope="col">Tide</th>
	      <th scope="col">Wave</th>
	      <th scope="col">Candle Pattern</th>
	      <th scope="col">Volume</th>
	      <th scope="col">EMA</th>
	      <th scope="col">Chart Pattern</th>
	      <th scope="col">Fib. Retracement</th>
	      <th scope="col">Divergence</th>
	    </tr>
  </thead>
  <tbody class="searchable">	
  		<tr>
  			<td>${trade.tide}</td>
  			<td>${trade.wave}</td>
  			<td>${trade.candleStickpattern}</td>
  			<td>${trade.volume}</td>
  			<td>${trade.ema}</td>
  			<td>${trade.chartpattern}</td>
  			<td>${trade.fibRetracement}</td>
  			<td>${trade.divergence}</td>
  		</tr>
   </tbody>
</table>


<table class="table table-sm table-bordered" id="tradeMoneyTable">
	<thead style="position: sticky;top: 0" class="table-primary">
	    <tr>
	      <th scope="col">Immediate Support for stop loss</th>
	      <th scope="col">Major Resistance for target</th>
	      <th scope="col">Immediate Resistance for stop loss</th>
	      <th scope="col">Major Support for target</th>
	      <th scope="col">Min RR</th>
	      <th scope="col">Max RR</th>
	      <th scope="col">Min. Reward</th>
	      <th scope="col">Max. Reward</th>
	      <th scope="col">Min. ROI</th>
	      <th scope="col">Max. ROI</th>
	    </tr>
  </thead>
  <tbody class="searchable">	
  		<tr>
  			<td>${trade.immediateSupportForStopLoss}</td>
  			<td>${trade.majorResistanceForTarget}</td>
  			<td>${trade.immediateResistanceForStopLoss}</td>
  			<td>${trade.majorSupportForTarget}</td>
  			<td>${trade.minRR}</td>
  			<td>${trade.maxRR}</td>
  			<td>${trade.minReward}</td>
  			<td>${trade.maxReward}</td>
  			<td>${trade.minROI}</td>
  			<td>${trade.maxROI}</td>
  		</tr>
   </tbody>
</table>

<div class="col-md-3 float-end">
<label for="changeTradeState${trade.uid}" class="form-label fw-bold">Change Trade State</label>
<div class="input-group">
   <select class="form-select" aria-label="Select Trade State" id="changeTradeState_${trade.uid}">
  <option selected>Select Trade State</option>
  <option value="Watchful">Watchful</option>
  <option value="Go for it">Go for it</option>
  <option value="Ignore">Ignore</option>
  <option value="Executed">Executed</option>
  </select>
</div>
</div>