<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>  


<div class="card">
 <div class="card-header bg01 text-white">
    	${strategySummaryReport.id}
    	<b>${strategySummaryReport.name}</b>
  </div>
  <div class="card-body">
		Total Trades Found : <span class="badge rounded-pill bg-dark">${strategySummaryReport.summaryReport.tradesCount}</span> 
		Target Exit Count : <span class="badge rounded-pill bg-dark">${strategySummaryReport.summaryReport.targetExistCount}</span> 
		Stop Loss Count :  <span class="badge rounded-pill bg-dark">${strategySummaryReport.summaryReport.stopLossCount}</span> 
		Open Trades Count : <span class="badge rounded-pill bg-dark">${strategySummaryReport.summaryReport.openTradesCount}</span>
		Profitable Trades : <span class="badge rounded-pill bg-dark">${strategySummaryReport.summaryReport.profitableTrades}</span> 
		Win Rate : <span class="badge rounded-pill bg-dark">${strategySummaryReport.summaryReport.winRate}%</span> 
		P/L : <span class="badge rounded-pill bg-dark"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${strategySummaryReport.summaryReport.profitAndLossAmount}" /></span>
  </div>
</div>
