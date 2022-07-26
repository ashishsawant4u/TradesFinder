$( document ).ready(function() {
   if ($("body").hasClass("oi_chartspage")) 
   {
	
	
		$.ajax({  
				url: oiLightWeightChartLineDataUrl+'/'+$('body').data('filedate'),  
				type: 'GET',  
				success: function(data) 
				{  
				    openInterstLineChart(data);
					priceLineChart(data);
					
			    }  
		}); 
   }	

});

function openInterstLineChart(data)
{
	var oiContainer = document.createElement('div');
	document.body.appendChild(oiContainer);
	
	const chart = LightweightCharts.createChart(oiContainer, 
															    { 
																	width: 0, 
																	height: 350,
																	timeScale: {timeVisible: true,secondsVisible: true}, 
																	layout: {
																	    background: {
																	      color: '#000000'
																	    },
																		textColor:'white'
																	  },
																	grid: {
																		vertLines: {
																			color: '#2B2B43',
																			visible:false
																		},
																		horzLines: {
																			color: '#363C4E',
																			visible:false
																		},
																	}
																});
					const lineSeries = chart.addAreaSeries();
					
					lineSeries.applyOptions({
				        color: 'rgba(38, 198, 218, 1)'
				    });
					
					let oidata=[];
					
					data.forEach(d => {
					    oidata.push({"time":Date.parse(d.time)/1000 ,"value":d.openInterst});
					});
					
					
					lineSeries.setData(oidata); 
					
					var toolTip = document.createElement('div');
					toolTip.className = 'chart-oi-tooltip';
					oiContainer.appendChild(toolTip);
					toolTip.style.display = 'block';
					toolTip.style.left = 3 + 'px';
					toolTip.style.top = 3 + 'px';
					
					chart.subscribeCrosshairMove(function(param) {
							 //console.log("oi "+param.seriesPrices.get(lineSeries)+" time "+param.time);
							 if(typeof param.seriesPrices.get(lineSeries) !== "undefined")
							 {
								toolTip.innerHTML = "<h4>OI: "+param.seriesPrices.get(lineSeries)+"</h4><h6>"+timeConverter(param.time)+"</h6>";
							 }
						     else
							 {
								//toolTip.innerHTML = "";
							 }
					});
}		

function priceLineChart(data)
{
	var priceContainer = document.createElement('div');
	document.body.appendChild(priceContainer);
	const chart = LightweightCharts.createChart(priceContainer, 
															    { 
																	width: 0, 
																	height: 350,
																	timeScale: {timeVisible: true,secondsVisible: true}, 
																	layout: {
																	    background: {
																	      color: '#000000'
																	    },
																		textColor:'white'
																	  },
																	grid: {
																		vertLines: {
																			color: '#2B2B43',
																			visible:false
																		},
																		horzLines: {
																			color: '#363C4E',
																			visible:false
																		},
																	}
																});
																
					const lineSeriesPrice = chart.addLineSeries();
					
					let pricedata=[];
					
					data.forEach(d => {
					    pricedata.push({"time":Date.parse(d.time)/1000 ,"value":d.price});
					});
					
					lineSeriesPrice.setData(pricedata); 
					
					var toolTip = document.createElement('div');
					toolTip.className = 'chart-price-tooltip';
					priceContainer.appendChild(toolTip);
					toolTip.style.display = 'block';
					toolTip.style.left = 3 + 'px';
					toolTip.style.top = 360 + 'px';
					
					chart.subscribeCrosshairMove(function(param) {
							 //console.log("oi "+param.seriesPrices.get(lineSeriesPrice)+" time "+param.time);
							 if(typeof param.seriesPrices.get(lineSeriesPrice) !== "undefined")
							 {
								toolTip.innerHTML = "<h4>Price: "+param.seriesPrices.get(lineSeriesPrice)+"</h4><h6>"+timeConverter(param.time)+"</h6>";
							 }
						     else
							 {
								//toolTip.innerHTML = "";
							 }
					});
}

function timeConverter(UNIX_timestamp)
{
  var isoStr = new Date(UNIX_timestamp * 1000).toISOString();

 // console.log(UNIX_timestamp+" >> "+isoStr);

  return isoStr.substring(0,19).replace('T',' ');

}
