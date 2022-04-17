<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<div class="row">
	<table class="table table-sm table-borderless w-100" id="cur_TradeAnalysisTable">
		<thead style="position: sticky;top: 0" class="theme1 nm-th">
		    <tr>
		      <th scope="col">Story and View Building</th>
		      <th scope="col">Trade Bias</th>
		      <th scope="col">Opportunity</th>
		      <th scope="col">WHAT-IF</th>
		    </tr>
	  	</thead>
	</table> 
</div>
<div class="row">
	<table class="table table-sm table-bordered table-hover w-100" id="cur_TradeEntryTable">
		<thead style="position: sticky;top: 0" class="theme1 nm-th">
		    <tr>
		      <th scope="col">Entry Price</th>
		      <th scope="col">Stop Loss</th>
		      <th scope="col">Target</th>
		      <th scope="col">Nos. Lots</th>
		      <th scope="col">Margin Paid</th>
		      <th scope="col">Reward Ratio</th>
		    </tr>
	  	</thead>
	</table> 
</div>


<div class="row">
	<div class="w-50"> 
		<tags:currencyTradeDetailCalculations/>
	</div>
	<div class="w-50 bg-light"> 
		<div class="row">
			<div class="col-md-4">
				<label for="cur_Confirmation" class="form-label">Confirmation</label>
				<div class="input-group">
				<textarea class="form-control" rows="2" id="cur_Confirmation"></textarea>
				</div>
			</div>
			<div class="col-md-4">
				<label for="cur_Learnings" class="form-label">Learnings</label>
				<div class="input-group">
				<textarea class="form-control" rows="2" id="cur_Learnings"></textarea>
				</div>
			</div>
			<div class="col-md-4">
				<label for="cur_comment" class="form-label">Comment</label>
				<div class="input-group">
				<textarea class="form-control" rows="2" id="cur_comment"></textarea>
				</div>
			</div>
		</div>
		<div class="row p-1">
				<div class="col-md-6">
				<label for="cur_tradeState" class="form-label">Trade State</label>
				<div class="input-group">
			    <select class="form-select" aria-label="Select Trade State" id="cur_tradeState">
				  <option selected>Select Trade State</option>
				  <option value="Watchful">Watchful</option>
				  <option value="Go for it">Go for it</option>
				  <option value="Ignore">Ignore</option>
				  <option value="In Progress">In Progress</option>
				  <option value="Completed">Completed</option>
				  <option value="Practice">Practice</option>
				  <option value="Target Exit">Target Exit</option>
		 		  <option value="SL Exit">SL Exit</option>
				  </select>
			    </div>
			    </div>
			    <div class="col-md-6">
			    	<label for="cur_tradeDuration" class="form-label">Trade Duration</label>
			    	<input type="text" class="form-control" id="cur_tradeDuration"/>
			    </div>
		</div>
	
	</div>
</div>