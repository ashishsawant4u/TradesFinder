<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>


<div class="card bg-light" id="futuresAnalysisComponent">
<div class="card-body row g-3">

<div class="input-group border-bottom p-1">
    <!-- <span class="input-group-text steps-input">13</span> -->
    <h5 class="card-title ms-2 pt-2">Futures Analysis</h5>
</div>


<div class="col-md-4 ">
	<label for="futurePriceDirection" class="form-label">Future Price</label>
	<div class="input-group">
      <select class="form-select" aria-label="Select Trading Style" id="futurePriceDirection">
	  <option selected>Select Future Price</option>
	  <option value="Future Price UP">Future Price UP</option>
	  <option value="Future Price DOWN">Future Price DOWN</option>
	  </select>
    </div>
</div>

<div class="col-md-4 ">
	<label for="futureOI" class="form-label">Future OI</label>
	<div class="input-group">
      <select class="form-select" aria-label="Select Trading Style" id="futureOI">
	  <option selected>Select Future OI</option>
	  <option value="Future OI UP">Future OI UP</option>
	  <option value="Future OI DOWN">Future OI DOWN</option>
	  </select>
    </div>
</div>

<div class="col-md-4 d-flex justify-content-center mt-5 fw-bold text-primary">
	<span id="futuresBuildUpCovering"></span>
</div>


</div>
</div>