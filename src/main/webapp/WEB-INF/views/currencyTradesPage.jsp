<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Currency Trades</title>
<tags:javascriptVariables/>
</head>
<body class="cur_tradespage">
<tags:navigation-currency/>

<div class="container-fluid">



<table class="table table-hover border" id="cur_TradeSummaryTable">
	<thead style="position: sticky;top: 0" class="theme1">
	    <tr>
	      <th scope="col">ID</th>
	      <th scope="col">Instrument</th>
	      <th scope="col">Date</th>
	      <th scope="col">Decision</th>
	      <th scope="col">Entry Price</th>
	      <th scope="col">Stop Loss Price</th>
	      <th scope="col">Target Price</th>
	      <th scope="col">Quantity</th>
	      <th scope="col">Trade State</th>
	    </tr>
  </thead>
</table>  

</div>

<div class="modal fade" id="cur_TradeDetailsModal" tabindex="-1" aria-labelledby="cur_trade_instrument" aria-hidden="true">
  <div class="modal-dialog modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <div class="row w-100">
        	<div class="col-xs-6 col-lg-6">
        			<span>(<span class="" id="cur_trade_id"></span>)</span>
        			<span class="modal-title text-uppercase fw-bold" id="cur_trade_instrument"></span>
       			    <span class="text-muted" id="cur_trade_date"></span>
        	</div>
        	<div class="col-xs-6 col-lg-6">
		       		 <div class="float-end col-md-4">
					    	<span class="modal-title text-uppercase fw-bold float-end fs-5" id="cur_trade_decision"></span>
					  </div>
        	</div>
        </div>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
     	<tags:tradeDetailsModalContent/>
      </div>
      <div class="modal-footer">
      	<div>
      		<a href="#" id="cur_trade_snapshot" target="_blank">Chart Snapshot</a>
      	</div>
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="cur_updateTradeBtn">Update Trade</button>
      </div>
    </div>
  </div>
</div>

<tags:scripts/>
</body>
</html>