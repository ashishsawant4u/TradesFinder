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
<title>Rules</title>
<tags:javascriptVariables/>
</head>
<body class="cur_rulespage">
<tags:navigation-currency/>
<tags:currencyGlobalError/>

<div class="container-fluid mt-1">


<div class="row mb-2 border p-2 bg-light" id="createRuleDiv">
	<div class="col-md-1">
    	<label for="inputEmail3" class="col-form-label fw-bold">Create Rule</label>
    </div>
    <div class="col-md-8">
      <textarea class="form-control" rows="2" id="cur_createRuleTxt"></textarea>
    </div>
    <div class="col-md-2">
    	<button type="button" class="btn theme1 mt-4" id="cur_saveRuleBtn" >Save Rule</button>
	</div>
</div>

<table class="table table-sm table-striped table-hover table-bordered pt-1" id="cur_RulesTable">
	<thead style="position: sticky;top: 0" class="bg-light">
	    <tr>
	      <th scope="col">ID</th>
	      <th scope="col">Rules</th>
	    </tr>
  </thead>
</table>  

</div>

<tags:scripts/>
</body>
</html>