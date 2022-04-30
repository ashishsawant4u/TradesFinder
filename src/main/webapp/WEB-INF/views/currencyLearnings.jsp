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
<title>Learnings</title>
<tags:javascriptVariables/>
</head>
<body class="cur_learningspage">
<tags:navigation-currency/>
<tags:currencyGlobalError/>

<div class="container-fluid">

<table class="table table-sm table-hover text-center table-bordered pt-1" id="cur_TradeLearningsTable">
	<thead style="position: sticky;top: 0" class="bg-light">
	    <tr>
	      <th scope="col">ID</th>
	      <th scope="col">Instrument</th>
	      <th scope="col">Date</th>
	      <th scope="col">Decision</th>
	      <th scope="col">Trade State</th>
	      <th scope="col">Learnings</th>
	      <th scope="col">Comments</th>
	      <th scope="col">Chart</th>
	    </tr>
  </thead>
</table>  

<tags:performanceStats/>

</div>

<tags:scripts/>
</body>
</html>