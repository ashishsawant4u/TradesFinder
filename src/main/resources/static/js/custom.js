$( document ).ready(function() {
 		
		$('#dailyEODWithMATable').DataTable({
		        dom: 'Bfrtip',
		        buttons: [
		            'excelHtml5',
		            'csvHtml5'
		        ],
				"paging":   true,
				"ordering": false,
    	});

});

