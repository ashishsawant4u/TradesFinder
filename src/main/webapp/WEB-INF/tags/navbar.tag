<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>     

<c:url var="landigPageUrl" value="/landing/home"/>
<c:url var="canstickBasicsPageUrl" value="/candlebasics/data/APARINDS"/>
<c:url var="nseBhavcopyRefreshUrl" value="/bhavcopy/nse-refresh"/>
<c:url var="nseBhavcopyUpdateUrl" value="/bhavcopy/nse-update"/>
<c:url var="strategy1Url" value="/backtest/strategy1"/>


<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="${landigPageUrl}">Trades Finder</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="${canstickBasicsPageUrl}">Candlestick Basic</a>
        </li>
        <li class="nav-item">
          <a class="nav-link disabled" href="${nseBhavcopyRefreshUrl}">NSE Bhavcopy Refresh</a>
        </li>
        <li class="nav-item">
          <a class="nav-link disabled" href="${nseBhavcopyUpdateUrl}">NSE Bhavcopy update</a>
        </li>
       <li class="nav-item">
          <a class="nav-link" href="${strategy1Url}">Strategy 1</a>
        </li>
      </ul>
    </div>
  </div>
</nav>