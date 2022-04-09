$( document ).ready(function() {
	
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
	
	
	
	
});	

function cur_buySellToggle()
{
	if($('#cur_btnradioBuy').is(':checked'))
	{ 
		console.log('Buy');
		let entryForm = currencyEntryForm('Buy');
		calculateCurrencyTrade(entryForm);
	}
	else if($('#cur_btnradioShortSell').is(':checked'))
	{
		console.log('ShortSell');
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
		$('#cur_PnlPercentageAsPerAllLotsForTarget').text(resp.pnlPercentageAsPerAllLotsForTarget);
		
		$('#cur_SlPriceMovement').text(resp.slPriceMovement);
		$('#cur_SlPipMovenment').text(resp.slPipMovenment);
		$('#cur_MaxLossPerLot').text(resp.maxLossPerLot);
		$('#cur_MaxLossAllLots').text(resp.maxLossAllLots);
		$('#cur_PnlPercentageAsPerAllLotsForSL').text(resp.pnlPercentageAsPerAllLotsForSL);
		
		$('#cur_quantity').val(resp.numberOfLotsCanBeTraded);
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
		riskPerTrade : $('#cur_maxRiskPerTrade').val()
	}
	
	return entryForm;
}		