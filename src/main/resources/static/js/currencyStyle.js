$( document ).ready(function() {
	
	if(isCur_DarkModeOn() === true)
	{
		cur_activateDarkMode();
	}

	$('#cur_darkModeSwitch').click(function () {	
		
		if($('#cur_darkModeSwitch').is(':checked'))
		{
			console.log('dark mode');
			localStorage.setItem('cur_darkmode', "on");
			cur_activateDarkMode();
		}
		else
		{
			console.log('light mode');
			localStorage.setItem('cur_darkmode', "off");
			location.reload();
		}
		
	});	
	
	$('#cur_TradeSummaryTable,#cur_TradeLearningsTable,#cur_RulesTable').on( 'draw.dt', function ( e, settings, len ) {
		if(isCur_DarkModeOn() === true)	   
		{
			cur_activateDarkMode();
		}
	});


});
function isCur_DarkModeOn()
{
	let cur_darkmodeFlag =  localStorage.getItem('cur_darkmode');
	if(!isNull(cur_darkmodeFlag))
	{
		return (cur_darkmodeFlag === 'on');
	}
}


function cur_activateDarkMode()
{
	$('#cur_darkModeSwitch').prop('checked', true);
	$('body').addClass('bg-dark text-white');
	$('.dataTables_wrapper label').addClass('text-white');
	$('.dataTables_wrapper select').addClass('text-white');
	$('.dataTables_wrapper option').addClass('text-dark');
	$('.dataTable').addClass('table-dark');
	$('.dataTables_wrapper div').addClass('text-white');
	$('.dataTables_wrapper a:not(".disabled")').attr('style', 'color:white !important');
	$('.dataTables_filter input').attr('style', 'color:white !important');
	$('#createRuleDiv label').addClass('text-dark');
	$('#createRuleDiv').removeClass('border');
	$('#cur_tradeCalculationsRow table').addClass('text-white');
	$('#cur_TradeDetailsModal .modal-content').addClass('bg-dark');
	$('#cur_tradeModelCalculationsRow table').addClass('text-white');
	$('#cur_tradeModelCalculationsRow table').removeClass('table-hover');
	$('#cur_tradeModelCommentsDiv').removeClass('bg-light');
	$('#cur_tradeModelCommentsDiv label').addClass('text-white');
	$('#cur_chartImage').attr('src','https://beeimg.com/images/f21989410552.png');
	$('#marginHelp').removeClass('text-danger').addClass('text-white');
}