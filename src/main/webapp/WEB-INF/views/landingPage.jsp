<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>        
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<tags:scripts/>
</head>
<body>

<tags:navbar/>

<div class="mb-2 mt-2">
<table class="table table-bordered table-hover" id="dailyEODWithMATable">
	<thead style="position: sticky;top: 0" class="table-dark">
	    <tr>
	      <th scope="col">Symbol</th>
	      <th scope="col">MarketDate</th>
	      <th scope="col">Open</th>
	      <th scope="col">High</th>
	      <th scope="col">Low</th>
	      <th scope="col">Close</th>
	      <th scope="col">SMA</th>
	      <th scope="col">EMA</th>
	    </tr>
  </thead>
  <tbody class="searchable">
  		<c:forEach var="candle" items="${candlesWithSMA}">
  				<tr>
  					<td>${candle.symbol}</td>
					<td><fmt:formatDate value="${candle.marketDateTime}" pattern="dd-MMM-yyyy"/></td>
					<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${candle.open}" /></td>
					<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${candle.high}" /></td>
					<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${candle.low}" /></td>
					<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${candle.close}" /></td>
					<td>
					<c:forEach var="entry" items="${candle.sma}">
							${entry.key} : 
							<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${entry.value}" />
							<br>
					</c:forEach>
					</td>
					<td>
					<c:forEach var="entry" items="${candle.ema}">
							${entry.key} : 
							<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${entry.value}" />
							<br>
					</c:forEach>
					</td>
  				</tr>
  		</c:forEach>
  </tbody>
</table>  
</div>

</body>
</html>