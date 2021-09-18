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
	      <th scope="col">Capital Balance</th>
	    </tr>
  </thead>
  <tbody class="searchable">
	<c:forEach var="order" items="${orderTracker}">
		<c:if test="${order.capitalBalance gt 0}">
				<tr>
		</c:if>
		<c:if test="${order.capitalBalance lt 0}">
				<tr class="bg-danger bg-gradient text-white">
		</c:if>
			<td>${order.orderType}</td>
			<td>${order.symbol}</td>
			<td><fmt:formatDate value="${order.marketDateTime}" pattern="dd-MMM-yyyy"/></td>
			<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${order.buyPrice}" /></td>
			<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${order.sellPrice}" /></td>
			<td>${order.quantity}</td>
			<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${order.orderAmount}" /></td>
			<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${order.capitalBalance}" /></td>
		</tr>
	</c:forEach>
	</tbody>
</table>

</div>