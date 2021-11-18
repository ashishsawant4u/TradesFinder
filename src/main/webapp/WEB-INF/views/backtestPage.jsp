<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.trades.demo.common.TradeStatus" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>        
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>TradeFinder</title>
<style type="text/css">
.bg01
{
	background-color: #5D6D7E!important;

}
.buy-bg01
{
	background-color: #1f9358!important;
}
.sell-bg01
{
	background-color: #e04d5c!important;
}
</style>
<tags:scripts/>
</head>
<body>
<tags:navbar/>

<div class="container">

${strategyDesc}

<ul class="nav nav-tabs mt-1 mb-2" id="myTab" role="tablist">
   <li class="nav-item" role="presentation">
    <button class="nav-link active" id="strategySummary-tab" data-bs-toggle="tab" data-bs-target="#strategySummary" type="button" role="tab" aria-controls="home" aria-selected="true">Strategy Summary</button>
  </li>
  <li class="nav-item" role="presentation">
    <button class="nav-link" id="symbolTradeSummary-tab" data-bs-toggle="tab" data-bs-target="#symbolTradeSummary" type="button" role="tab" aria-controls="home" aria-selected="true">Symbol Trade Summary</button>
  </li>
  <li class="nav-item" role="presentation">
    <button class="nav-link" id="monthlyReport-tab" data-bs-toggle="tab" data-bs-target="#monthlyReport" type="button" role="tab" aria-controls="profile" aria-selected="false">Monthly Report</button>
  </li>
  <li class="nav-item" role="presentation">
    <button class="nav-link" id="yearlyReport-tab" data-bs-toggle="tab" data-bs-target="#yearlyReport" type="button" role="tab" aria-controls="contact" aria-selected="false">Yearly Report</button>
  </li>
  <li class="nav-item" role="presentation">
    <button class="nav-link" id="tradesList-tab" data-bs-toggle="tab" data-bs-target="#tradesList" type="button" role="tab" aria-controls="contact" aria-selected="false">Trades</button>
  </li>
  <li class="nav-item" role="presentation">
    <button class="nav-link" id="orders-tab" data-bs-toggle="tab" data-bs-target="#orders" type="button" role="tab" aria-controls="contact" aria-selected="false">Orders</button>
  </li>
</ul>
<div class="tab-content" id="myTabContent">
  <div class="tab-pane fade show active" id="strategySummary" role="tabpanel" aria-labelledby="strategySummary-tab"><tags:strategySummaryReport/></div>
  <div class="tab-pane fade" id="symbolTradeSummary" role="tabpanel" aria-labelledby="symbolTradeSummary-tab"><tags:symbolTradeResults/></div>
  <div class="tab-pane fade" id="monthlyReport" role="tabpanel" aria-labelledby="monthlyReport-tab"><tags:monthlyReport/></div>
  <div class="tab-pane fade" id="yearlyReport" role="tabpanel" aria-labelledby="yearlyReport-tab"><tags:yearlyReport/></div>
  <div class="tab-pane fade" id="tradesList" role="tabpanel" aria-labelledby="tradesList-tab"><tags:tradeList/></div>
  <div class="tab-pane fade" id="orders" role="tabpanel" aria-labelledby="orders-tab"><tags:orderTracker/></div>
</div>


</div>



</body>
</html>