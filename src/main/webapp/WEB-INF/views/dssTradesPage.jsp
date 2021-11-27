<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>



<div class="container-fluid">

<nav class="navbar navbar-dark bg-primary mb-3">
  <div class="container-fluid">
    <a class="navbar-brand" href="/tradesfinder/doublescreen/plan">Double Screen Trading System</a>
  </div>
</nav>


<table class="table table-hover border" id="dssTradeSummaryTable">
	<thead style="position: sticky;top: 0" class="table-dark">
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
  		<c:forEach var="trade" items="${listOfTrades}">
  			<tr class="cursor-pointer" id="tradeSummaryRow_${trade.uid}" data-bs-toggle="modal" data-bs-target="#dssTradeDetailModal${trade.uid}">
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
  				</td>
  			</tr>
  		</c:forEach>
  </tbody>
</table>




<c:forEach var="trade" items="${listOfTrades}">
<div class="modal" tabindex="-1" id="dssTradeDetailModal${trade.uid}">
  <div class="modal-dialog modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title fw-bold">${trade.stock}</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
      	    <tags:dssTradeDetails trade="${trade}"/>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="dssTradeDetailModalSaveBtn_${trade.uid}" data-uid="${trade.uid}">Save changes</button>
      </div>
    </div>
  </div>
</div>
</c:forEach>

</div>

<tags:scripts/>
<tags:javascriptVariables/>