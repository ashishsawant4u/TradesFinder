<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<div class="card d-none bg-light" id="momentumSetupComponent">
<div class="card-body row g-3">

<div class="input-group border-bottom p-1">
    <span class="input-group-text steps-input">12</span>
    <h5 class="card-title ms-2 pt-2">Momentum Setup</h5>
</div>
<div class="col-md-6">
	<div class="form-check" id="noImmediateSupportChekboxDiv">
	  <input class="form-check-input" type="checkbox" value="No Immediate Support" id="noImmediateSupportChekbox">
	  <label class="form-check-label" for="noImmediateSupportChekbox">
	    No Immediate Support
	  </label>
	</div>
	
	<div class="form-check" id="noImmediateResistanceChekboxDiv">
	  <input class="form-check-input" type="checkbox" value="No Immediate Resistance" id="noImmediateResistanceChekbox">
	  <label class="form-check-label" for="noImmediateResistanceChekbox">
	    No Immediate Resistance
	  </label>
	</div>
</div>

<div class="col-md-6">
	<div class="form-check" id="bbChallangedUpsideDiv">
	  <input class="form-check-input" type="checkbox" value="BB Challenge Upside" id="bbChallangedUpside">
	  <label class="form-check-label" for="bbChallangedUpside">
	    BB Challenge Upside
	  </label>
	</div>
	
	<div class="form-check" id="bbChallangedDownsideDiv">
	  <input class="form-check-input" type="checkbox" value="BB Challenge Downside" id="bbChallangedDownside">
	  <label class="form-check-label" for="bbChallangedDownside">
	   BB Challenge Downside
	  </label>
	</div>
</div>


<div class="col-md-4">
	<label for="adxUngli" class="form-label">ADX/Ungli</label>
	<input type="text" class="form-control" id="adxUngli" >
	 <small id="adxUngliHelp" class="form-text text-muted">
	  	Should be above 12-15 and less tha 50-55	  
	 </small>
</div>
<div class="col-md-4">
	<label for="rsiLevelMomentum" class="form-label">RSI</label>
	<input type="text" class="form-control" id="rsiLevelMomentum">
	 <small id="rsiLevelMomentumHelp" class="form-text text-muted">
	  	RSI > 60 (for bulls) and RSI < 40 (for bears)
	  </small>
</div>

<div class="col-md-4">
	<label for="shakoutMomentum" class="form-label">Shakeout</label>
	<input type="text" class="form-control" id="shakoutMomentum">
</div>
</div>
</div>