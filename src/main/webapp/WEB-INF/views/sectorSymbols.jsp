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
<title>Insert title here</title>

<tags:scripts/>
<tags:javascriptVariables/>

</head>
<body>

<div class="container-fluid">

<tags:navigation/>

<a href="#" class="btn btn-primary mb-2" onclick="download_table_as_csv('indicesTable');">Download as CSV</a>

<table class="table table-hover table-bordered" id="indicesTable">
     <tbody id="table-content">
     </tbody>
</table>


</div>



</body>
</html>