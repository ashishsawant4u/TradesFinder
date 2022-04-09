<%@ tag language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>


<div class="card bg-light" id="openInterestAnalysisComponent">
<div class="card-body row g-3">

<div class="input-group border-bottom p-1">
    <!-- <span class="input-group-text steps-input">13</span> -->
    <h5 class="card-title ms-2 pt-2">OI Analysis</h5>
</div>


<div class="col-md-3 ">
	<label for="callStrikePrice" class="form-label">Call Strike Price</label>
	<input type="text" class="form-control" id="callStrikePrice">
</div>

<div class="col-md-3 ">
	<label for="callPriceDirection" class="form-label">Call Price</label>
	<div class="input-group">
      <select class="form-select" aria-label="Select Trading Style" id="callPriceDirection">
	  <option selected>Select Call Price</option>
	  <option value="Call Price UP">Call Price UP</option>
	  <option value="Call Price DOWN">Call Price DOWN</option>
	  </select>
    </div>
</div>

<div class="col-md-3 ">
	<label for="callOI" class="form-label">Call OI</label>
	<div class="input-group">
      <select class="form-select" aria-label="Select Trading Style" id="callOI">
	  <option selected>Select Call OI</option>
	  <option value="Call OI UP">Call OI UP</option>
	  <option value="Call OI DOWN">Call OI DOWN</option>
	  </select>
    </div>
</div>

<div class="col-md-3 ">
	<label for="callTmjTmg" class="form-label">Call TMJ/TMG</label>
	<div class="input-group">
      <select class="form-select" aria-label="Select Trading Style" id="callTmjTmg">
	  <option selected>Select Call TMJ/TMG</option>
	  <option value="Call TMJ">Call TMJ</option>
	  <option value="Call TMG">Call TMG</option>
	  </select>
    </div>
    <small id="" class="form-text text-muted">
	  	As per OI Data  
	</small>
</div>

<div class="col-md-12 d-flex justify-content-center fw-bold text-primary">
	<span id="callBuildUpCovering"></span>
</div>


<div class="col-md-3 ">
	<label for="putStrikePrice" class="form-label">Put Strike Price</label>
	<input type="text" class="form-control" id="putStrikePrice">
</div>

<div class="col-md-3 ">
	<label for="putPriceDirection" class="form-label">Put Price</label>
	<div class="input-group">
      <select class="form-select" aria-label="Select Trading Style" id="putPriceDirection">
	  <option selected>Select Put Price</option>
	  <option value="Put Price UP">Put Price UP</option>
	  <option value="Put Price DOWN">Put Price DOWN</option>
	  </select>
    </div>
</div>

<div class="col-md-3 ">
	<label for="putOI" class="form-label">Put OI</label>
	<div class="input-group">
      <select class="form-select" aria-label="Select Trading Style" id="putOI">
	  <option selected>Select Put OI</option>
	  <option value="Put OI UP">Put OI UP</option>
	  <option value="Put OI DOWN">Put OI DOWN</option>
	  </select>
    </div>
</div>

<div class="col-md-3 ">
	<label for="putTmjTmg" class="form-label">Put TMJ/TMG</label>
	<div class="input-group">
      <select class="form-select" aria-label="Select Trading Style" id="putTmjTmg">
	  <option selected>Select Put TMJ/TMG</option>
	  <option value="Put TMJ">Put TMJ</option>
	  <option value="Put TMG">Put TMG</option>
	  </select>
    </div>
    <small id="" class="form-text text-muted">
	  	As per OI Data  
	</small>
</div>


<div class="col-md-12 d-flex justify-content-center fw-bold text-primary">
	<span id="putBuildUpCovering"></span>
</div>

<div class="col-md-6">
  	<label for="volatilitySelect" class="form-label">Volatility</label>
	<div class="input-group">
      <select class="form-select" aria-label="Select Volatility" id="volatilitySelect">
	  <option selected>Select Volatility</option>
	  <option value="High Volatility">High Volatility</option>
	  <option value="Low Volatility">Low Volatility</option>
	  </select>
    </div>
    <small id="" class="form-text text-muted">
    	BUY - When Volatility is LOW option premiums are CHEAPER <br>
	  	SELL - When volatility is HIGH option premiums are EXPENSIVE  
	</small>
</div>

<div class="col-md-6 d-flex justify-content-center fw-bold text-primary h4 mt-5">
	<span id="volaitilityDecision"></span>
</div>

</div>
</div>
