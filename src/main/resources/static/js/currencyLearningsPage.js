$( document ).ready(function() {
	
	if ($("body").hasClass("cur_learningspage")) 
	{
		cur_loadLerningsTable();
		cur_PerformanceStats();
	}
	
	if ($("body").hasClass("cur_rulespage")) 
	{
		cur_getRules();
		
		$('#cur_saveRuleBtn').click(function () {
			cur_saveRule();
		});
	}
	
	
	
});	

function cur_getRules()
{
	 $('#cur_RulesTable').DataTable({
			    ajax: getAllRulesUrl,
				dataSrc:"",
				ordering: false,
				columnDefs: [
					{
						"createdCell": function (td, cellData, rowData, row, col) {
							$(td).addClass('text-start');
		                },
						targets: '_all'
				  }	
				],
				columns: [
					  { data: 'uid' },
					  { data: 'rule' },
				]
			});
}

function cur_saveRule()
{
		$.ajax({
	            type: "GET",
	            url: encodeURI(saveRuleUrl+'/'+ $('#cur_createRuleTxt').val()),
	            success: function (response) {
	                console.log(response);
							$('#cur_saveRuleBtn').html('Saved'); 
					window.setTimeout(function() { 
							$('#cur_saveTradeBtn').html('Save Rule'); 
					}, 2000);
					
					window.location.href = currencyRulesUrl;		
	            },
				error: function (jqXHR) {
					$('#cur_globalalert').addClass('show');	
					$('#cur_alert_status').text(jqXHR.status);
					$('#cur_alert_msg').html(jqXHR.responseText);
				}
	 		});
}

function cur_loadLerningsTable()
{
	$('#cur_TradeLearningsTable').DataTable({
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
				  },
				  {
						"render": function ( data, type, row ) {
		                    return '<a href="' + data + '" target="_blank">Chart Snap</a>';
		                },
						targets: [7]
				  }
				],
				columns: [
					  { data: 'uid' },
					  { data: 'calculations.instrument' },
					  { data: 'date' },
					  { data: 'calculations.tradeDecision' },
					  { data: 'tradeState' },
					  { data: 'learnings' },
					  { data: 'comment' },
					  { data: 'chartImageUrl' },
				
				]
			});
}