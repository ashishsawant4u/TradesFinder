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
		
		$(document).on('click', '.cur_ruledelete_icon', function(){
			cur_deleteRule($(this).attr('data-uid'));
		});
		
		
	}
	
	$('#cur_globalalert').on( "close.bs.alert", function(e) {
			e.preventDefault();
			$('#cur_globalalert').removeClass('show');
			$('#cur_globalalert').addClass('d-none');
	});
	
});	

function cur_deleteRule(ruleId)
{
	
	$.ajax({
	            type: "GET",
	            url: deleteRuleUrl+'/'+ ruleId,
	            success: function (response) {
	                console.log(response);
					if(response === true)
					{
						$('#cur_globalalert').addClass('show');	
						$('#cur_globalalert').removeClass('d-none');
						$('#cur_alert_status').text("SUCCESS!!");
						$('#cur_alert_msg').html("Rule "+ruleId+" removed");
							window.setTimeout(function() { 
									window.location.href = currencyRulesUrl;	
							}, 2000);
					}
	            },
				error: function (jqXHR) {
					$('#cur_globalalert').addClass('show');	
					$('#cur_globalalert').removeClass('d-none');
					$('#cur_alert_status').text(jqXHR.status);
					$('#cur_alert_msg').html(jqXHR.responseText);
				}
	 		});
}

function cur_getRules()
{
	 $('#cur_RulesTable').DataTable({
			    ajax: getAllRulesUrl,
				dataSrc:"",
				ordering: false,
				pagingType: "full",
				columnDefs: [
					{
						"createdCell": function (td, cellData, rowData, row, col) {
							$(td).addClass('text-start');
		                },
						targets: '_all'
				    },
					{
						"mData": "uid",
						"render": function ( data, type, row ) {
		                    
		                	return "<span class='cur_ruledelete_icon' data-uid='"+data+"' id='ruledel_"+data+"'><svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor' class='bi bi-x-square' viewBox='0 0 16 16'><path d='M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z'/><path d='M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z'/></svg></span>";
						},
						targets: [2]
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
					$('#cur_globalalert').removeClass('d-none');
					$('#cur_alert_status').text(jqXHR.status);
					$('#cur_alert_msg').html(jqXHR.responseText);
				}
	 		});
}

function cur_loadLerningsTable()
{
	$('#cur_TradeLearningsTable').DataTable({
			    ajax: getTradesLearningsUrl,
				dataSrc:"",
				ordering: false,
				pageLength:7,
				lengthMenu:[7,10,25,100],
				pagingType: "full",
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
						targets: [2]
				  },
				  {
						"render": function ( data, type, row ) {
		                    return '<a href="' + data + '" target="_blank">Chart Snap</a>';
		                },
						targets: [6]
				  }
				],
				columns: [
					  { data: 'uid' },
					  { data: 'calculations.instrument' },
					  { data: 'calculations.tradeDecision' },
					  { data: 'tradeState' },
					  { data: 'learnings' },
					  { data: 'comment' },
					  { data: 'chartImageUrl' },
				
				]
			});
}