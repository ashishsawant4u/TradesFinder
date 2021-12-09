<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>


<div class="card d-none bg-light" id="swingSetupComponent">
<div class="card-body row g-3">
<div class="input-group border-bottom p-1">
    <span class="input-group-text steps-input">12</span>
    <h5 class="card-title ms-2 pt-2">Swing Setup</h5>
</div>
<div class="col-md-4">
	<div class="form-check" id="bkpChekboxDiv">
	  <input class="form-check-input" type="checkbox" value="Bhagvan ka Prasad" id="bkpChekbox">
	  <label class="form-check-label" for="bkpChekbox">
	    Bhagvan ka Prasad
	  </label>
	</div>
	
	<div class="form-check" id="bktChekboxDiv">
	  <input class="form-check-input" type="checkbox" value="Baapu Ki Tapli" id="bktChekbox">
	  <label class="form-check-label" for="bktChekbox">
	    Baapu Ki Tapli
	  </label>
	</div>
</div>

<div class="col-md-4">
<div class="form-check">
	  <input class="form-check-input" type="checkbox" value="+DI and -DI in the favour of the trade" id="DIChekbox">
	  <label class="form-check-label" for="DIChekbox">
	    +DI and -DI Contracting or in the favour of the trade
	  </label>
	</div>
</div>

<div class="col-md-4">
	<label for="rsiLevelSwing" class="form-label">RSI</label>
	<input type="text" class="form-control" id="rsiLevelSwing">
	 <small id="rsiLevelMomentumHelp" class="form-text text-muted">
	  	RSI > 40 (for bulls) and RSI < 60 (for bears)
	  </small>
</div>



</div>
</div>