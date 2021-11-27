<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>         
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<tags:scripts/>
<tags:javascriptVariables/>

<div class="container-fluid">

<nav class="navbar navbar-dark bg-primary mb-3 navbar-expand-lg">
  <div class="container-fluid">
    <a class="navbar-brand" href="${dssPlannerUrl}">Double Screen Trading System</a>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="${dssTradeListUrl}">Trades</a>
        </li>
       </ul>
    </div>    
 	<form class="d-flex">
      <a  id="resetFormLink" href="/tradesfinder/doublescreen/plan" class="text-decoration-none text-white cursor-pointer">
	                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-clockwise" viewBox="0 0 16 16">
					  <path fill-rule="evenodd" d="M8 3a5 5 0 1 0 4.546 2.914.5.5 0 0 1 .908-.417A6 6 0 1 1 8 2v1z"></path>
					  <path d="M8 4.466V.534a.25.25 0 0 1 .41-.192l2.36 1.966c.12.1.12.284 0 .384L8.41 4.658A.25.25 0 0 1 8 4.466z"></path>
					</svg>
					Reset
	     </a>
    </form>
  </div>
</nav>




<div class="row container-fluid">
<div class="col-xs-6 col-lg-6 ">
	<tags:dssStudy/>
</div>
<div class="col-xs-6 col-lg-6 ">
	<tags:dssMoneyMgmt/>
</div>
</div>
</div>