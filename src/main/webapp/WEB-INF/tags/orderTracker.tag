<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>   
<div class="row">

<table class="table table-bordered table-hover" id="orderTrackerTable">
	<thead style="position: sticky;top: 0" class="table-dark">
	    <tr>
	      <th scope="col">Order Type</th>
	      <th scope="col">Symbol</th>
	      <th scope="col">Date</th>
	      <th scope="col">Buy Price</th>
	      <th scope="col">Sell Price</th>
	      <th scope="col">Quantity</th>
	      <th scope="col">Order Amount</th>
	      <th scope="col">Trade Status</th>
	      <th scope="col">Capital Balance</th>
	    </tr>
  </thead>
  <tbody class="searchable">
	<c:forEach var="order" items="${orderTracker}">
		<tr>
			
			<c:if test="${order.orderType eq 'BUY'}"> <td class="bg-success bg-gradient text-white">${order.orderType}</td></c:if>
			<c:if test="${order.orderType eq 'SELL'}"> <td class="bg-danger bg-gradient text-white">${order.orderType}</td></c:if>
			<td>${order.symbol}</td>
			<td><fmt:formatDate value="${order.marketDateTime}" pattern="dd-MMM-yyyy"/></td>
			<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${order.buyPrice}" /></td>
			<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${order.sellPrice}" /></td>
			<td>${order.quantity}</td>
			<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${order.orderAmount}" /></td>
			<td>${order.exitType}</td>
			<c:if test="${order.capitalBalance gt 0}">
				<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${order.capitalBalance}" /></td>
			</c:if>
			<c:if test="${order.capitalBalance lt 0}">
				<td class="bg-warning bg-gradient"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${order.capitalBalance}" /></td>
			</c:if>
		</tr>
	</c:forEach>
	</tbody>
</table>

</div>