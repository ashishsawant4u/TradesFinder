<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>   


<table class="table table-bordered table-hover" id="symbolwisetraderesultTable">
	<thead style="position: sticky;top: 0" class="table-dark">
	    <tr>
	      <th scope="col" >Symbol</th>
	      <th scope="col">Trades Found</th>
	      <th scope="col">Target Exit Count</th>
	      <th scope="col">Stop Loss Count</th>
	      <th scope="col">Open Trades</th>
	      <th scope="col">Profitable Trades Count</th>
	      <th scope="col">Avg. Duration</th>
	    </tr>
  </thead>
  <tbody class="searchable">
	<c:forEach var="candle" items="${symbolwiserTradeReport}">
		<tr>
			<td class="table-primary">${candle.symbol}</td>
			<td>${candle.tradesCount}</td>
			<td>${candle.targetExistCount}</td>
			<td>${candle.stopLossCount}</td>
			<td>${candle.openTradesCount}</td>
			<c:if test="${candle.profitableTrades lt 0 or candle.profitableTrades eq 0}">
				<td class="bg-danger bg-gradient text-white">${candle.profitableTrades}</td>
			</c:if>
			<c:if test="${candle.profitableTrades gt 0}">
				<td>${candle.profitableTrades}</td>
			</c:if>
			<td>${candle.avgTradeDuration}</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
