<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ attribute name="trade" required="true" type="com.trades.demo.forms.DoubleScreenTradeForm" %>

<table class="table table-sm table-bordered" id="tradeStudyTable">
	<thead style="position: sticky;top: 0" class="${trade.dssDecision eq 'BUY' ? 'bullish nm-th' : 'bearish nm-th'}">
	    <tr>
	      <th scope="col">Trade ID</th>
	      <th scope="col">Tide</th>
	      <th scope="col">Wave</th>
	      <th scope="col">Candle Pattern</th>
	      <th scope="col">Volume</th>
	      <th scope="col">EMA</th>
	      <th scope="col">Chart Pattern</th>
	      <th scope="col">Fib. Retracement</th>
	      <th scope="col">Divergence</th>
	      <th scope="col">PAPA Trade Setup</th>
	    </tr>
  </thead>
  <tbody class="searchable">	
  		<tr>
  			<td>${trade.uid}</td>
  			<td>${trade.tide}</td>
  			<td>${trade.wave}</td>
  			<td>${trade.candleStickpattern}</td>
  			<td>${trade.volume}</td>
  			<td>${trade.ema}</td>
  			<td>${trade.chartpattern}</td>
  			<td>${trade.fibRetracement}</td>
  			<td>${trade.divergence}</td>
  			<td>${trade.papaTradeSetup}</td>
  		</tr>
   </tbody>
</table>

<table class="table table-sm table-bordered" id="tradeEntryTable">
	<thead style="position: sticky;top: 0" class="${trade.dssDecision eq 'BUY' ? 'bullish nm-th' : 'bearish nm-th'}">
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
			<td class="border ${trade.dssDecision eq 'BUY' ? 'bullish-text' : 'bearish-text'}">${trade.dssDecision}</td>
			<td class="border">${trade.entryPrice}</td>
			<td class="border">${trade.stopLossPrice}</td>
			<td class="border">${trade.minTargetPrice}</td>
			<td class="border">${trade.maxTargetPrice}</td>
			<td class="border">${trade.quantity}</td>
			<td class="border">${trade.tradeInvestment}</td>
			<td class="border">${trade.riskPerUnit}</td>
			<td class="border">${trade.totalRisk}</td>
			<td class="border" id="initialSelectionState_${trade.uid}">${trade.tradeState}</td>
  		</tr>
   </tbody>
</table>


<table class="table table-sm table-bordered" id="tradeMoneyTable">
	<thead style="position: sticky;top: 0" class="${trade.dssDecision eq 'BUY' ? 'bullish nm-th' : 'bearish nm-th'}">
	    <tr>
	      <th scope="col">Close Price</th>
	      <c:if test="${trade.dssDecision eq 'BUY'}">	
	      <th scope="col">Immediate Support for stop loss</th>
	      <th scope="col">Major Resistance for target</th>
	      </c:if>
	      <c:if test="${trade.dssDecision eq 'SELL'}">
	      <th scope="col">Immediate Resistance for stop loss</th>
	      <th scope="col">Major Support for target</th>
	      </c:if>
	      <th scope="col">Min Profit Potential</th>
	      <th scope="col">Max Profit Potential</th>
	      <th scope="col">Min. Reward</th>
	      <th scope="col">Max. Reward</th>
	      <th scope="col">Min. Risk Reward Ratio</th>
	      <th scope="col">Max. Risk Reward Ratio</th>
	      <th scope="col">Min. ROI</th>
	      <th scope="col">Max. ROI</th>
	    </tr>
  </thead>
  <tbody class="searchable">	
  		<tr>
  			<td>${trade.closePrice}</td>
  		    <c:if test="${trade.dssDecision eq 'BUY'}">	
  			<td>${trade.immediateSupportForStopLoss}</td>
  			<td>${trade.majorResistanceForTarget}</td>
  			</c:if>
  			<c:if test="${trade.dssDecision eq 'SELL'}"> 
  			<td>${trade.immediateResistanceForStopLoss}</td>
  			<td>${trade.majorSupportForTarget}</td>
  			</c:if>
  			<td>${trade.minProfitPotential}</td>
  			<td>${trade.maxProfitPotential}</td>
  			<td>${trade.minReward}</td>
  			<td>${trade.maxReward}</td>
  			<td>${trade.minRR}</td>
  			<td>${trade.maxRR}</td>
  			<td>${trade.minROI}%</td>
  			<td>${trade.maxROI}%</td>
  		</tr>
   </tbody>
</table>

<div class="col-md-3 float-start">
<label for="tradeSetup${trade.uid}" class="form-label fw-bold">Trade Setup</label>
<div class="input-group">
	<span> <b>${trade.tradingStyle}</b> <br> 
			<%-- ${fn:replace(trade.tradeSetupDetails, '|', '<br>')} --%>
			<c:forTokens items="${trade.tradeSetupDetails}" delims="|" var="setupChecks">
			   <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-all" viewBox="0 0 16 16">
				  <path d="M8.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L2.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093L8.95 4.992a.252.252 0 0 1 .02-.022zm-.92 5.14.92.92a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 1 0-1.091-1.028L9.477 9.417l-.485-.486-.943 1.179z"/>
			   </svg>
			   <c:out value="${setupChecks}"/> </br>
			</c:forTokens>
	</span>
</div>
</div>

<div class="col-md-3 float-start">
<label for="futureOptionsOIDetails${trade.uid}" class="form-label fw-bold">OI Analysis</label>
<div class="input-group">
	<span>
			<%-- ${fn:replace(trade.tradeSetupDetails, '|', '<br>')} --%>
			<c:forTokens items="${trade.futureOptionsOIDetails}" delims="|" var="checklist">
			   <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-all" viewBox="0 0 16 16">
				  <path d="M8.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L2.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093L8.95 4.992a.252.252 0 0 1 .02-.022zm-.92 5.14.92.92a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 1 0-1.091-1.028L9.477 9.417l-.485-.486-.943 1.179z"/>
			   </svg>
			   <c:out value="${checklist}"/> </br>
			</c:forTokens>
	</span>
</div>
</div>


<div class="col-md-3 float-start">
<label for="whatIFAnalysis${trade.uid}" class="form-label fw-bold">What IF Analysis</label>
<div class="input-group"><span>${trade.whatIfAnalysis}</span></div>
</div>

<div class="col-md-3 float-start">
<label for="tradeComment_${trade.uid}" class="form-label fw-bold">Comment</label>
<div class="input-group"><textarea id="tradeComment_${trade.uid}" class="form-control" rows="5">${trade.tradeComment}</textarea></div>
</div>

<div class="col-md-12 float-end">
	<div class="col-md-3 float-end">
		<label for="changeTradeState${trade.uid}" class="form-label fw-bold">Change Trade State</label>
		<div class="input-group">
		   <select class="form-select" aria-label="Select Trade State" id="changeTradeState_${trade.uid}">
		  <option selected>Select Trade State</option>
		  <option value="Watchful">Watchful</option>
		  <option value="Go for it">Go for it</option>
		  <option value="Ignore">Ignore</option>
		  <option value="In Progress">In Progress</option>
		  <option value="Completed">Completed</option>
		   <option value="Target Exit">Target Exit</option>
		    <option value="SL Exit">SL Exit</option>
		  </select>
		</div>
	</div>
</div>