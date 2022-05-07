<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  


<c:url scope="session" var="dssPlannerUrl" value="/doublescreen/plan"/>    
<c:url scope="session" var="dssTradeListUrl" value="/doublescreen/trades"/>
<c:url scope="session" var="dssSaveTradeLogUrl" value="/doublescreen/tradelog"/>
<c:url scope="session" var="dssUpdateTradeStateUrl" value="/doublescreen/updateTradeState"/>
<c:url scope="session" var="indicesUrl" value="/tabela/indices"/>
<c:url scope="session" var="indicesPageUrl" value="/tabela/nifty-indices"/>
<c:url scope="session" var="papaGuideUrl" value="/doublescreen/papaGuide"/>

<c:url scope="session" var="currencyCalcUrl" value="/currency/calculator"/>
<c:url scope="session" var="calculateCapitalRiskUrl" value="/currency/capitalrisk"/>
<c:url scope="session" var="currencyPairInfoUrl" value="/currency/currencyPairInfo"/>
<c:url scope="session" var="calculateCurrencyTradeUrl" value="/currency/calculateCurrencyTrade"/>
<c:url scope="session" var="saveCurrencyTradeUrl" value="/currency/saveTrade"/>
<c:url scope="session" var="currencyTradesUrl" value="/currency/trades"/>
<c:url scope="session" var="getTradeSummaryUrl" value="/currency/getTradeSummary"/>
<c:url scope="session" var="getTradeDetailsUrl" value="/currency/getTradeDetails"/>
<c:url scope="session" var="updateCurrencyTradeUrl" value="/currency/updateTrade"/>
<c:url scope="session" var="currencyPerformanceStatsUrl" value="/currency/performanceStats"/>
<c:url scope="session" var="currencyLearningsUrl" value="/currency/learnings"/>
<c:url scope="session" var="currencyRulesUrl" value="/currency/rules"/>
<c:url scope="session" var="saveRuleUrl" value="/currency/saveRule"/>
<c:url scope="session" var="getAllRulesUrl" value="/currency/getAllRules"/>
<c:url scope="session" var="getTradesLearningsUrl" value="/currency/getTradesLearnings"/>
<c:url scope="session" var="deleteRuleUrl" value="/currency/deleteRule"/>

<script type="text/javascript">
	var dssPlannerUrl = "${dssPlannerUrl}";
	var dssTradeListUrl = "${dssTradeListUrl}";
	var dssSaveTradeLogUrl = "${dssSaveTradeLogUrl}";
	var dssUpdateTradeStateUrl = "${dssUpdateTradeStateUrl}";
	var indicesUrl = "${indicesUrl}";
	var indicesPageUrl = "${indicesPageUrl}";
	var papaGuideUrl = "${papaGuideUrl}";
	var calculateCapitalRiskUrl = "${calculateCapitalRiskUrl}";
	var currencyPairInfoUrl = "${currencyPairInfoUrl}";
	var calculateCurrencyTradeUrl = "${calculateCurrencyTradeUrl}";
	var saveCurrencyTradeUrl = "${saveCurrencyTradeUrl}";
	var currencyTradesUrl = "${currencyTradesUrl}";
	var getTradeSummaryUrl = "${getTradeSummaryUrl}";
	var getTradeDetailsUrl = "${getTradeDetailsUrl}";
	var updateCurrencyTradeUrl = "${updateCurrencyTradeUrl}";
	var currencyCalcUrl = "${currencyCalcUrl}";
	var currencyPerformanceStatsUrl = "${currencyPerformanceStatsUrl}";
	var currencyLearningsUrl = "${currencyLearningsUrl}";
	var currencyRulesUrl = "${currencyRulesUrl}";
	var getAllRulesUrl = "${getAllRulesUrl}";
	var saveRuleUrl = "${saveRuleUrl}";
	var getTradesLearningsUrl = "${getTradesLearningsUrl}";
	var deleteRuleUrl = "${deleteRuleUrl}";
</script>