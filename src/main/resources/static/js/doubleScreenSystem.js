$( document ).ready(function() {

buySellToggele();

$('.btnradioBuySell').click(function () {
	buySellToggele();
});

$('#dssTradeCalBtn').click(function () {
	tradeCal();
});



});

function buySellToggele()
{
	if($('#btnradioBuy').is(':checked'))
	{ 
		$('.steps-input').addClass('bullish');
		$('.steps-input').removeClass('bearish');
	}
	else
	{
		$('.steps-input').addClass('bearish');
		$('.steps-input').removeClass('bullish');
	}
}

function tradeCal()
{
	let capitalAmount = $("#capitalAmount").val();  
	let percentageRiskPerTrade = $("#percentageRiskPerTrade").val();  
	let maxRiskPerTrade = $("#maxRiskPerTrade").val();  
	let immediateSupportForStopLoss = $("#immediateSupportForStopLoss").val();  
	let majorResistanceForTarget = $("#majorResistanceForTarget").val();  
	let immediateResistanceForStopLoss = $("#immediateResistanceForStopLoss").val();  
	let majorSupportForTarget = $("#majorSupportForTarget").val();  
	let closePrice = $("#closePrice").val();  
	let entryPrice = $("#entryPrice").val();     
	let stopLossPrice = $("#stopLossPrice").val();     
	let minTargetPrice = $("#minTargetPrice").val();     
	let maxTargetPrice = $("#maxTargetPrices").val();              
	let tradeInvestment = $("#tradeInvestment").val();  
	
	maxRiskPerTrade = Math.round((capitalAmount * percentageRiskPerTrade) / 100);
	$("#maxRiskPerTrade").val(maxRiskPerTrade);  
	
	let tradeData = {
		capitalAmount:capitalAmount,
		percentageRiskPerTrade:percentageRiskPerTrade,
		maxRiskPerTrade:maxRiskPerTrade,
		immediateSupportForStopLoss:immediateSupportForStopLoss,
		majorResistanceForTarget:majorResistanceForTarget,
		immediateResistanceForStopLoss:immediateResistanceForStopLoss,
		majorSupportForTarget:majorSupportForTarget,
		closePrice : closePrice,
		entryPrice : entryPrice,
		stopLossPrice : stopLossPrice,
		minTargetPrice : minTargetPrice,
		maxTargetPrice : maxTargetPrice,
		tradeInvestment : tradeInvestment
	}
	
	//console.log(tradeData);
	if($('#btnradioBuy').is(':checked'))
	{ 
		console.log("BUY Transaction");
		buyTransactionHandler(tradeData);
	}
	else
	{
		console.log("SHORT SELL Transaction");
		shortSellTransactionHandler(tradeData);
	}  
}

function buyTransactionHandler(tradeData)
{
	let riskPerUnit = (tradeData.entryPrice - tradeData.stopLossPrice).toFixed(2);
	$("#riskPerUnit").val(riskPerUnit);   
	
	let quantity = 	Math.round(tradeData.maxRiskPerTrade / riskPerUnit);
	$("#quantity").val(quantity);  
	
	let tradeInvestment = tradeData.entryPrice * quantity;
	$("#tradeInvestment").val(tradeInvestment);
	
	let totalRisk = (riskPerUnit * quantity).toFixed(2);
	$("#totalRisk").val(totalRisk);   
	
	let minReward = ($("#minTargetPrice").val() - tradeData.entryPrice).toFixed(2);  
	$("#minReward").val(minReward); 
	
	let maxReward = ($("#maxTargetPrice").val() - tradeData.entryPrice).toFixed(2);  
	$("#maxReward").val(maxReward); 
	
	let minProfitPotential = (minReward * quantity).toFixed(2);
	$("#minProfitPotential").val(minProfitPotential); 
	
	let maxProfitPotential = (maxReward * quantity).toFixed(2);
	$("#maxProfitPotential").val(maxProfitPotential); 
	
	let minROI = ((minProfitPotential/tradeInvestment)*100).toFixed(2);
	$("#minROI").val(minROI); 
	
	let maxROI = ((maxProfitPotential/tradeInvestment)*100).toFixed(2);
	$("#maxROI").val(maxROI);
	
	let minRR =  (minReward/riskPerUnit).toFixed(2);
	$("#minRR").val(minRR);
	
	let maxRR =  (maxReward/riskPerUnit).toFixed(2);
	$("#maxRR").val(maxRR);
}

function shortSellTransactionHandler(tradeData)
{
	let riskPerUnit = (tradeData.stopLossPrice - tradeData.entryPrice).toFixed(2);
	$("#riskPerUnit").val(riskPerUnit);   
	
	let quantity = 	Math.round(tradeData.maxRiskPerTrade / riskPerUnit);
	$("#quantity").val(quantity);  
	
	let tradeInvestment = ($("#minTargetPrice").val() * quantity).toFixed(2);
	$("#tradeInvestment").val(tradeInvestment);
	
	let totalRisk = (riskPerUnit * quantity).toFixed(2);
	$("#totalRisk").val(totalRisk);   
	
	let minReward = (tradeData.entryPrice - $("#minTargetPrice").val()).toFixed(2);  
	$("#minReward").val(minReward); 
	
	let maxReward = (tradeData.entryPrice - $("#maxTargetPrice").val()).toFixed(2);  
	$("#maxReward").val(maxReward); 
	
	let minProfitPotential = (minReward * quantity).toFixed(2);
	$("#minProfitPotential").val(minProfitPotential); 
	
	let maxProfitPotential = (maxReward * quantity).toFixed(2);
	$("#maxProfitPotential").val(maxProfitPotential); 
	
	let minROI = ((minProfitPotential/tradeInvestment)*100).toFixed(2);
	$("#minROI").val(minROI); 
	
	let maxROI = ((maxProfitPotential/tradeInvestment)*100).toFixed(2);
	$("#maxROI").val(maxROI);
	
	let minRR =  (minReward/riskPerUnit).toFixed(2);
	$("#minRR").val(minRR);
	
	let maxRR =  (maxReward/riskPerUnit).toFixed(2);
	$("#maxRR").val(maxRR);
}





















