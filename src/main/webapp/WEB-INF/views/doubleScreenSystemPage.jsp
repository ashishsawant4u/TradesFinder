<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>         
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    


<tags:javascriptVariables/>

<div class="container-fluid">

<tags:navigation/>
    
<div class="row container-fluid">
<div class="col-xs-6 col-lg-6 ">
	<tags:dssStudy/>
	<tags:papaDecsionSheet/>
</div>
<div class="col-xs-6 col-lg-6 ">
	<%-- <tags:priceActionGuide/> --%>
	<tags:dssMoneyMgmt/>
</div>


</div>
</div>

<tags:scripts/>