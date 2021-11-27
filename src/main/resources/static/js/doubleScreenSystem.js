$( document ).ready(function() {

defaultInit();

buySellToggele();

$('.btnradioBuySell').click(function () {
	buySellToggele();
});

$('#dssTradeCalBtn').click(function () {
	tradeCal();
});


$('#dssTradeLogBtn').click(function () {
	dssTradeLog();
});

$('#capitalAmount,#percentageRiskPerTrade').keyup(function () {
    let capitalAmount = $("#capitalAmount").val();  
	let percentageRiskPerTrade = $("#percentageRiskPerTrade").val();  
	maxRiskPerTrade = Math.round((capitalAmount * percentageRiskPerTrade) / 100);
	$("#maxRiskPerTrade").val(maxRiskPerTrade);  
});

$('.collapse').on('show.bs.collapse', function () {
    $('.collapse.show').collapse('hide');
});


$('#dssTradeSummaryTable').DataTable({
		        dom: 'Bfrtip',
		        buttons: [
		            'copyHtml5',
		            'excelHtml5',
		            'csvHtml5'
		        ],
				"targets": [11],
				"paging":   true,
				"ordering": false,
    	});

$('[id^="dssTradeDetailModalSaveBtn_"]').click(function() {
   let tradeId = $(this).attr('data-uid');
   let selectedTradeState = $('#changeTradeState_'+tradeId).val();
   console.log('tradeId '+tradeId);
   console.log('selectedTradeState '+selectedTradeState);
   var tradeEnrty = {uid :tradeId,tradeState:selectedTradeState};
   console.log('tradeEnrty '+tradeEnrty);
	
	let tradeEntryRequest = JSON.stringify(tradeEnrty);
	
	 $.ajax({
            type: "POST",
            url: dssUpdateTradeStateUrl,
            contentType: "application/json;",
 			data: tradeEntryRequest,	
            success: function (response) {
                console.log(response);
						$('#dssTradeDetailModalSaveBtn_'+tradeId).html('Updated'); 
						$('#dssTradeDetailModalSaveBtn_'+tradeId).addClass('btn-success');
						$('#dssTradeDetailModalSaveBtn_'+tradeId).removeClass('btn-primary');
				window.setTimeout(function() { 
						$('#dssTradeDetailModal_'+tradeId).hide();
						location.reload();
				}, 2000);
				
						
            }
 		});

});

$('.modal').on('hide.bs.modal', function () {
    
});


});

function defaultInit()
{
	$("#capitalAmount").val(5000);  
	$("#percentageRiskPerTrade").val(2);
	let capitalAmount = $("#capitalAmount").val();  
	let percentageRiskPerTrade = $("#percentageRiskPerTrade").val();  
	maxRiskPerTrade = Math.round((capitalAmount * percentageRiskPerTrade) / 100);
	$("#maxRiskPerTrade").val(maxRiskPerTrade); 
	
	initDate();
	
}

function initDate()
{
	const monthNames = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
	
	var today = new Date();
	var day = today.getDate();
    var month = monthNames[today.getMonth()];
    var year = today.getFullYear();
	$("#studyDate").val(day + '-' + month + '-' + year);  
}

function buySellToggele()
{
	if($('#btnradioBuy').is(':checked'))
	{ 
		$('.steps-input').addClass('bullish');
		$('.steps-input').removeClass('bearish');
		$('.bears-sections').addClass('d-none');
		$('.bulls-sections').removeClass('d-none');
		$(".bullishCandlestickPattern").removeClass('d-none');
		$(".bullishChartPatern").removeClass('d-none');
		$(".bearishCandlestickPattern").addClass('d-none');
		$(".bearishChartPatern").addClass('d-none');
	}
	else if($('#btnradioShortSell').is(':checked'))
	{
		$('.steps-input').addClass('bearish');
		$('.steps-input').removeClass('bullish');
		$('.bears-sections').removeClass('d-none');
		$('.bulls-sections').addClass('d-none');
		$(".bullishCandlestickPattern").addClass('d-none');
		$(".bullishChartPatern").addClass('d-none');
		$(".bearishCandlestickPattern").removeClass('d-none');
		$(".bearishChartPatern").removeClass('d-none');
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
	
	$(".dssTradeLogBtn").removeClass("d-none"); 
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
	
	tradeDecision(minRR);
}

function shortSellTransactionHandler(tradeData)
{
	let riskPerUnit = Math.abs((tradeData.stopLossPrice - tradeData.entryPrice).toFixed(2));
	$("#riskPerUnit").val(riskPerUnit);   
	
	let quantity = 	Math.abs(Math.round(tradeData.maxRiskPerTrade / riskPerUnit))	;
	$("#quantity").val(quantity);  
	
	let tradeInvestment = Math.abs(($("#minTargetPrice").val() * quantity).toFixed(2));
	$("#tradeInvestment").val(tradeInvestment);
	
	let totalRisk = Math.abs((riskPerUnit * quantity).toFixed(2));
	$("#totalRisk").val(totalRisk);   
	
	let minReward = Math.abs((tradeData.entryPrice - $("#minTargetPrice").val()).toFixed(2));  
	$("#minReward").val(minReward); 
	
	let maxReward = Math.abs((tradeData.entryPrice - $("#maxTargetPrice").val()).toFixed(2));  
	$("#maxReward").val(maxReward); 
	
	let minProfitPotential = Math.abs((minReward * quantity).toFixed(2));
	$("#minProfitPotential").val(minProfitPotential); 
	
	let maxProfitPotential = Math.abs((maxReward * quantity).toFixed(2));
	$("#maxProfitPotential").val(maxProfitPotential); 
	
	let minROI = Math.abs(((minProfitPotential/tradeInvestment)*100).toFixed(2));
	$("#minROI").val(minROI); 
	
	let maxROI = Math.abs(((maxProfitPotential/tradeInvestment)*100).toFixed(2));
	$("#maxROI").val(maxROI);
	
	let minRR =  Math.abs((minReward/riskPerUnit).toFixed(2));
	$("#minRR").val(minRR);
	
	let maxRR =  Math.abs((maxReward/riskPerUnit).toFixed(2));
	$("#maxRR").val(maxRR);
	
	tradeDecision(minRR);
}

function tradeDecision(minRR)
{
	if(minRR >= 3)
	{
		$(".riskRewardRatioDiv").addClass('bullish');
		$(".riskRewardRatioDiv").removeClass('bearish');
		$("#riskRewardRatioAlert").addClass('d-none');
		$("#riskRewardRatioAlert").slideUp();
	}
	else
	{
		$(".riskRewardRatioDiv").addClass('bearish');
		$(".riskRewardRatioDiv").removeClass('bullish');
		$("#riskRewardRatioAlert").removeClass('d-none');
		$("#riskRewardRatioAlert").slideDown();
	}
}

function dssTradeLog()
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
	let maxTargetPrice = $("#maxTargetPrice").val();              
	let tradeInvestment = $("#tradeInvestment").val();
	let riskPerUnit = $("#riskPerUnit").val();  
	let quantity = 	$("#quantity").val(); 
	let totalRisk = $("#totalRisk").val();  
	let minReward =   $("#minReward").val(); 
	let maxReward =$("#maxReward").val(); 
	let minProfitPotential = $("#minProfitPotential").val();
	let maxProfitPotential = $("#maxProfitPotential").val();
	let minROI = $("#minROI").val(); 
	let maxROI = $("#maxROI").val();
	let minRR =  $("#minRR").val();
	let maxRR =  $("#maxRR").val();
	let stock =  $("#stockName").val();
	let date =  $("#studyDate").val();
	let tide =  $("#tideText").val();
	let wave =  $("#waveText").val();
	let dssDecision =  $('input[name="btnradioBuySell"]:checked').val();
	let candleStickpattern;
	let chartpattern;
	
	if($('#btnradioBuy').is(':checked'))
	{
		candleStickpattern =  $("#bullishCandlestickPattern").val();
		chartpattern =  $("#bullishChartPatern").val();
	}
	else
	{
		candleStickpattern =  $("#bearishCandlestickPattern").val();
		chartpattern =  $("#bearishChartPatern").val();
	}
	
	let volume =  $("#volumeText").val();
	let ema =  $("#emaState").val();
	let fibRetracement =  $("#fibRetracement").val();
	let divergence =  $("#divergence").val();
	let tradeState = $("#tradeState").val();
	
	var tradeEnrty = {capitalAmount :capitalAmount ,percentageRiskPerTrade :percentageRiskPerTrade ,maxRiskPerTrade :maxRiskPerTrade ,immediateSupportForStopLoss :immediateSupportForStopLoss ,majorResistanceForTarget :majorResistanceForTarget ,immediateResistanceForStopLoss :immediateResistanceForStopLoss ,majorSupportForTarget :majorSupportForTarget ,closePrice :closePrice ,entryPrice :entryPrice ,stopLossPrice :stopLossPrice ,minTargetPrice :minTargetPrice ,maxTargetPrice :maxTargetPrice ,tradeInvestment :tradeInvestment ,riskPerUnit :riskPerUnit ,quantity :quantity ,tradeInvestment :tradeInvestment ,totalRisk :totalRisk ,minReward :minReward ,maxReward :maxReward ,minProfitPotential :minProfitPotential ,maxProfitPotential :maxProfitPotential ,minROI :minROI ,maxROI :maxROI ,minRR :minRR ,maxRR :maxRR ,stock :stock ,date :date ,tide :tide ,wave :wave ,dssDecision :dssDecision ,candleStickpattern:candleStickpattern,chartpattern:chartpattern,volume :volume ,ema :ema ,fibRetracement :fibRetracement ,divergence :divergence,tradeState:tradeState};
	
	console.log('tradeEnrty '+tradeEnrty);
	
	let tradeEntryRequest = JSON.stringify(tradeEnrty);
	
	 $.ajax({
            type: "POST",
            url: "http://localhost:8045/tradesfinder/doublescreen/tradelog",
            contentType: "application/json;",
 			data: tradeEntryRequest,	
            success: function (response) {
                console.log(response);
						$('#dssTradeLogBtn').html('Saved'); 
						$('#dssTradeLogBtn').addClass('btn-success');
						$('#dssTradeLogBtn').removeClass('btn-primary');
				window.setTimeout(function() { 
						$('#dssTradeLogBtn').html('Save Trade'); 
						$('#dssTradeLogBtn').removeClass('btn-success');
						$('#dssTradeLogBtn').addClass('btn-primary');
				}, 2000);
				
						
            }
 		});

}




















