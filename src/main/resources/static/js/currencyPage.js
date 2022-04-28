$( document ).ready(function() {
	
	$("[rel='tooltip']").tooltip();
	
	$("#cur_capitalAmount,#cur_percentageRiskPerTrade").keyup(function(){
		
		let urlPath = "/"+$('#cur_capitalAmount').val()+"/"+$('#cur_percentageRiskPerTrade').val();
		
		$.ajax({
            type: "GET",
            url: calculateCapitalRiskUrl+urlPath,
            success: function (response) {
                console.log(response);
				$('#cur_capitalAmount').val(response.capital);
				$('#cur_percentageRiskPerTrade').val(response.riskPercentage);
			    $('#cur_maxRiskPerTrade').val(response.riskPerTrade);		
            },
			error: function (jqXHR) {
				$('#cur_alert_status').text(jqXHR.status);
				$('#cur_alert_msg').text(jqXHR.responseText);
			}	
 		});
	});
	
	$("#cur_instrument").change(function(){
		
		let urlPath = "/"+$('#cur_instrument').val();
		$.ajax({
            type: "GET",
            url: currencyPairInfoUrl+urlPath,
            success: function (response) {
                console.log(response);
				$('#cur_pipSize').val(response.pipSize);
				$('#cur_lotSize').val(response.lotSize);
			    $('#cur_profitAndLossPerLotPerPip').val(response.profitAndLossPerLotPerPip);		
            },
			error: function (jqXHR) {
				$('#cur_alert_status').text(jqXHR.status);
				$('#cur_alert_msg').text(jqXHR.responseText);
			}
 		});
	});
	
	$("#cur_entryPrice,#cur_numberOfLots").keyup(function(){
			let totalAmtForAllLots = parseFloat($('#cur_entryPrice').val()) *
			parseFloat($('#cur_lotSize').val()) *
			parseFloat($('#cur_numberOfLots').val());
			if(!isNaN(totalAmtForAllLots))
			{
				$('#cur_totalAmountForAllLots').val(totalAmtForAllLots);
			}
	});	
	
	$('.cur_btnradioBuySell').click(function () {
		cur_buySellToggle();
	});
	
	$('#cur_TradeCalBtn').click(function () {
		cur_buySellToggle();
	});
	
	$('#cur_saveTradeBtn').click(function () {
		cur_saveTrade();
	});
	
	$("#cur_chartImageUrl").change(function(){
			$('#cur_chartImage').attr('src',$("#cur_chartImageUrl").val());
	});	
	
	$('#cur_updateTradeBtn').click(function () {
		cur_updateTrade();
	});
	
	if ($("body").hasClass("cur_tradespage")) 
	{
			var cur_TradeSummaryTable = $('#cur_TradeSummaryTable').DataTable({
			    ajax: getTradeSummaryUrl,
				dataSrc:"",
				ordering: false,
				columnDefs: [
				  {
				    className: "text-uppercase",
				    targets: [1]
				  },
				  {
						"render": function ( data, type, row ) {
		                    return 'T_'+data;
		                },
						targets: [0]
				  },
				  {
						"createdCell": function (td, cellData, rowData, row, col) {
		                    if(cellData === 'Buy')
							{
						        $(td).addClass('bullish-text');
						    }
							else
							{
								$(td).addClass('bearish-text');
							}
							$(td).addClass('text-uppercase');
		                },
						targets: [3]
				  }
				],
				columns: [
					  { data: 'uid' },
					  { data: 'calculations.instrument' },
					  { data: 'date' },
					  { data: 'calculations.tradeDecision' },
					  { data: 'calculations.entry' },
					  { data: 'calculations.stopLoss' },
					  { data: 'calculations.target' },
				 	  { data: 'calculations.maxLossAllLots' },
					  { data: 'calculations.numberOfLots' },
					  { data: 'tradeState' }
				
				]
			});
			
			
			
			$('#cur_TradeSummaryTable tbody').on( 'click', 'tr', function () {
				
				let $tradeDetailsModal = $('#cur_TradeDetailsModal');	
				
				$tradeDetailsModal.find('#cur_TradeAnalysisTable').dataTable().fnDestroy();
				$tradeDetailsModal.find('#cur_TradeEntryTable').dataTable().fnDestroy();
				
				 let rowData = cur_TradeSummaryTable.row( this ).data();
				
				if(rowData.calculations.tradeDecision === 'Buy')
				{
					$tradeDetailsModal.find('table thead').addClass('bullish').removeClass('theme1').removeClass('bearish');
				}
				else
				{
					$tradeDetailsModal.find('table thead').addClass('bearish').removeClass('theme1').removeClass('bullish');
				}
				
			   
				
				$('#cur_TradeAnalysisTable').attr('data-tradeid',rowData.uid);
				$tradeDetailsModal.find('#cur_trade_instrument').text(rowData.calculations.instrument);	
				$tradeDetailsModal.find('#cur_trade_date').text(rowData.date);
				$tradeDetailsModal.find('#cur_trade_id').text('T_'+rowData.uid);
				$tradeDetailsModal.find('#cur_trade_decision').text(rowData.calculations.tradeDecision);
				if(!isNull(rowData.chartImageUrl))
				{
					$tradeDetailsModal.find('#cur_trade_snapshot').attr('href',rowData.chartImageUrl);
					$tradeDetailsModal.find('#cur_trade_snapshot').removeClass('d-none');
				}
				else
				{
					$tradeDetailsModal.find('#cur_trade_snapshot').addClass('d-none');
				}
				$tradeDetailsModal.find('#cur_Confirmation').val(rowData.confirmation);
				$tradeDetailsModal.find('#cur_Learnings').val(rowData.learnings);
				$tradeDetailsModal.find('#cur_comment').val(rowData.comment);
				$tradeDetailsModal.find('#cur_tradeDuration').val(rowData.tradeDuration);
				$tradeDetailsModal.find('#cur_tradeState').val(rowData.tradeState).change();
				$tradeDetailsModal.find('#cur_actualRewardRatio').val(rowData.calculations.actualRewardRatio);
				$tradeDetailsModal.find('#cur_actualPnL').val(rowData.calculations.actualPnL);
				
				
				let tradeData = [];
				tradeData.push(rowData);
				
				$('#cur_TradeAnalysisTable').DataTable({
				    data: tradeData,
					paging: false,
					searching: false,
					ordering: false,
					info: false,
					columns: [
							{ data: 'storyAndViewBuilding' },
							{ data: 'tradeBias' },
							{ data: 'opportunity' },
							{ data: 'whatIfAnalysis' }
						]
				});
				
				$('#cur_TradeEntryTable').DataTable({
				    data: tradeData,
					paging: false,
					searching: false,
					ordering: false,
					info: false,
					columns: [
							  { data: 'calculations.entry' },
							  { data: 'calculations.stopLoss' },
							  { data: 'calculations.target' },
							  { data: 'calculations.numberOfLots' },
							  { data: 'calculations.marginAmountForAllLots' },
							   { data: 'calculations.rewardRatio' }
						]
				});
				
				let tradeCalc = rowData.calculations;
				
				$tradeDetailsModal.find('#cur_TargetPriceMovement').text(tradeCalc.targetPriceMovement);
				$tradeDetailsModal.find('#cur_TargetPipMovenment').text(tradeCalc.targetPipMovenment);
				$tradeDetailsModal.find('#cur_MaxProfitPerLot').text(tradeCalc.maxProfitPerLot);
				$tradeDetailsModal.find('#cur_MaxProfitAllLots').text(tradeCalc.maxProfitAllLots);
				$tradeDetailsModal.find('#cur_PnlPercentageAsPerAllLotsForTarget').text(tradeCalc.pnlPercentageAsPerAllLotsForTarget+' %');
				
				$tradeDetailsModal.find('#cur_SlPriceMovement').text(tradeCalc.slPriceMovement);
				$tradeDetailsModal.find('#cur_SlPipMovenment').text(tradeCalc.slPipMovenment);
				$tradeDetailsModal.find('#cur_MaxLossPerLot').text(tradeCalc.maxLossPerLot);
				$tradeDetailsModal.find('#cur_MaxLossAllLots').text(tradeCalc.maxLossAllLots);
				$tradeDetailsModal.find('#cur_PnlPercentageAsPerAllLotsForSL').text(tradeCalc.pnlPercentageAsPerAllLotsForSL+' %');
				
				
								
				
				$tradeDetailsModal.modal('show');
			});
			
			cur_PerformanceStats();
	}
	
	
});	

function cur_PerformanceStats()
{
	$.ajax({
            type: "GET",
            url: currencyPerformanceStatsUrl,	
            success: function (response) {
                console.log(response);	
				$('#cur_performance_wins').text(response.winningTradesCount);
				$('#cur_performance_loss').text(response.loosingTradesCount);
				$('#cur_performance_trades').text(response.numberOfTrades);
				$('#cur_performance_hitratio').text(response.hitRatio+'%');
				$('#cur_performance_avgRewardRatio').text('1:'+response.avgRewardRation);
				$('#cur_performance_totalPnL').text(response.totalPnL);
				$('#cur_performance_expectancy').html(response.expectancy+'R');
				$('#cur_performance_capitalgain').html(response.capitalGain+'%');	
            },
			error: function (jqXHR) {
				$('#cur_alert_status').text(jqXHR.status);
				$('#cur_alert_msg').text(jqXHR.responseText);
			}
 		});
}

function cur_updateTrade()
{
	let calculations = {actualRewardRatio:$('#cur_actualRewardRatio').val(),actualPnL:$('#cur_actualPnL').val()};
	
	let tradeData = {
		confirmation: $('#cur_Confirmation').val(),
		learnings: $('#cur_Learnings').val(),
		comment: $('#cur_comment').val(),
		tradeDuration: $('#cur_tradeDuration').val(),
		tradeState: $('#cur_tradeState').val(),
		uid: $('#cur_TradeAnalysisTable').attr('data-tradeid'),
		calculations : calculations
	}
	
	
	
	let tradeEntryRequest = JSON.stringify(tradeData);
	
		 $.ajax({
	            type: "POST",
	            url: updateCurrencyTradeUrl,
	            contentType: "application/json;",
	 			data: tradeEntryRequest,	
	            success: function (response) {
	                console.log(response);
							$('#cur_updateTradeBtn').html('Saved'); 
							$('#cur_updateTradeBtn').addClass('btn-success');
							$('#cur_updateTradeBtn').removeClass('btn-primary');
					window.setTimeout(function() { 
							$('#cur_updateTradeBtn').html('Save Trade'); 
							$('#cur_updateTradeBtn').removeClass('btn-success');
							$('#cur_updateTradeBtn').addClass('btn-primary');
					}, 2000);
					window.location.href = currencyTradesUrl;			
	            },
				error: function (jqXHR) {
					$('#cur_alert_status').text(jqXHR.status);
					$('#cur_alert_msg').text(jqXHR.responseText);
				}
	 		});
}

function cur_buySellToggle()
{
	$('#cur_entrysection').removeClass('theme1');
	
	if($('#cur_btnradioBuy').is(':checked'))
	{ 
		console.log('Buy');
		$('#cur_entrysection').addClass('bullish');
		$('#cur_entrysection').removeClass('bearish');
		let entryForm = currencyEntryForm('Buy');
		calculateCurrencyTrade(entryForm);
	}
	else if($('#cur_btnradioShortSell').is(':checked'))
	{
		console.log('ShortSell');
		$('#cur_entrysection').addClass('bearish');
		$('#cur_entrysection').removeClass('bullish');
		let entryForm = currencyEntryForm('Sell');
		calculateCurrencyTrade(entryForm);
	}
}

function calculateCurrencyTrade(entryForm)
{
	$.ajax({
            type: "POST",
            url: calculateCurrencyTradeUrl,
            contentType: "application/json;",
 			data: JSON.stringify(entryForm),	
            success: function (response) {
                console.log(response);
				renderTradeCalculations(response);
            },
			error: function (jqXHR) {
				$('#cur_alert_status').text(jqXHR.status);
				$('#cur_alert_msg').text(jqXHR.responseText);
			}
 		});
}

function renderTradeCalculations(resp)
{
	if(!isNaN(resp.pnlPercentageAsPerAllLotsForTarget))
	{
		$('#cur_TargetPriceMovement').text(resp.targetPriceMovement);
		$('#cur_TargetPipMovenment').text(resp.targetPipMovenment);
		$('#cur_MaxProfitPerLot').text(resp.maxProfitPerLot);
		$('#cur_MaxProfitAllLots').text(resp.maxProfitAllLots);
		$('#cur_PnlPercentageAsPerAllLotsForTarget').text(resp.pnlPercentageAsPerAllLotsForTarget+' %');
		
		$('#cur_SlPriceMovement').text(resp.slPriceMovement);
		$('#cur_SlPipMovenment').text(resp.slPipMovenment);
		$('#cur_MaxLossPerLot').text(resp.maxLossPerLot);
		$('#cur_MaxLossAllLots').text(resp.maxLossAllLots);
		$('#cur_PnlPercentageAsPerAllLotsForSL').text(resp.pnlPercentageAsPerAllLotsForSL+' %');
		
		$('#cur_quantity').val(resp.numberOfLotsCanBeTraded);
		
		$('#cur_riskReward').val(resp.rewardRatio);
		
		
		$('#tradeCalculationFormData').remove();
		$('<input>').attr({ type: 'hidden', id: 'tradeCalculationFormData',value: JSON.stringify(resp)}).appendTo('body');
	}
}

function currencyEntryForm(tradeDecision)
{
	let entryForm = {
		instrument : $('#cur_instrument').val(),
		tradeDecision : tradeDecision,
		entry : $('#cur_entryPrice').val(),
		stopLoss : $('#cur_stopLossPrice').val(),
		target : $('#cur_targetPrice').val(),
		numberOfLots : $('#cur_numberOfLots').val(),
		totalAmountForAllLots : $('#cur_totalAmountForAllLots').val(),
		marginAmountForAllLots : $('#cur_marginAmountForAllLots').val(),
		riskPerTrade : $('#cur_maxRiskPerTrade').val(),
		pipSize : $('#cur_pipSize').val(),
		lotSize : $('#cur_lotSize').val(),
	}
	
	return entryForm;
}	

function cur_saveTrade()
{
	let tradeData = {
		storyAndViewBuilding : $('#cur_StoryandViewBuilding').val(),
		tradeBias : $('#cur_TradeBias').val(),
		opportunity : $('#cur_Opportunity').val(),
		whatIfAnalysis : $('#cur_WHAT-IF').val(),
		chartImageUrl : $('#cur_chartImageUrl').val(),
		comment : $('#cur_tradeComment').val(),
		tradeState : $('#cur_tradeState').val(),
		calculations : JSON.parse($('#tradeCalculationFormData').val()),
	}
	
	console.log('tradeData',tradeData);
	
	if(!isNull($('#cur_StoryandViewBuilding').val()))
	{
		let tradeEntryRequest = JSON.stringify(tradeData);
	
		 $.ajax({
	            type: "POST",
	            url: saveCurrencyTradeUrl,
	            contentType: "application/json;",
	 			data: tradeEntryRequest,	
	            success: function (response) {
	                console.log(response);
							$('#cur_saveTradeBtn').html('Saved'); 
							$('#cur_saveTradeBtn').addClass('btn-success');
							$('#cur_saveTradeBtn').removeClass('btn-primary');
					window.setTimeout(function() { 
							$('#cur_saveTradeBtn').html('Save Trade'); 
							$('#cur_saveTradeBtn').removeClass('btn-success');
							$('#cur_saveTradeBtn').addClass('btn-primary');
					}, 2000);
							
	            },
				error: function (jqXHR) {
					$('#cur_alert_status').text(jqXHR.status);
					$('#cur_alert_msg').text(jqXHR.responseText);
				}
	 		});
	}
	
}	

function isNull(value)
{
	if(typeof value === "undefined" || value === null || value === ""  || value.length==0){
		return true;
	}else{
		return false;
	}
}