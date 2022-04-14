<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>      
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Currency Calculator</title>
</head>
<body>
<tags:javascriptVariables/>

<div class="row container-fluid">
 
<div class="col-xs-6 col-lg-6 ">
	<tags:currencyTradeAnalysis/>
</div>
 
<div class="col-xs-6 col-lg-6 ">  
  <tags:capitalInfo />
  <tags:currencyPairInfo/>
  <tags:currencyEntry/>
  <tags:currencyTradeCalculations/>
  
</div>  
</div>

<tags:scripts/>
</body>
</html>