<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  


<c:url var="dssPlannerUrl" value="/doublescreen/plan"/>    
<c:url var="dssTradeListUrl" value="/doublescreen/trades"/>
<c:url var="dssSaveTradeLogUrl" value="/doublescreen/tradelog"/>
<c:url var="dssUpdateTradeStateUrl" value="/doublescreen/updateTradeState"/>


<script type="text/javascript">
	var dssPlannerUrl = "${dssPlannerUrl}";
	var dssTradeListUrl = "${dssTradeListUrl}";
	var dssSaveTradeLogUrl = "${dssSaveTradeLogUrl}";
	var dssUpdateTradeStateUrl = "${dssUpdateTradeStateUrl}";
</script>