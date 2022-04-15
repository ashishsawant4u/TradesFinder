<%@ tag language="java" pageEncoding="ISO-8859-1"%>

<div class="row pt-1">

	  <div class="col-md-4 theme2 p-1">
	    <label for="cur_instrument" class="form-label">Instrument</label>
	    <div class="input-group">
	    <span class="input-group-text">
		    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-bar-chart-fill" viewBox="0 0 16 16">
			  <path d="M1 11a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v3a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1v-3zm5-4a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v7a1 1 0 0 1-1 1H7a1 1 0 0 1-1-1V7zm5-5a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1h-2a1 1 0 0 1-1-1V2z"/>
			</svg>
		</span>
	    <input type="text" class="form-control text-uppercase" id="cur_instrument">
	    </div>
	  </div>
	  <div class="col-md-2 theme2 p-1">
	    <label for="cur_pipSize" class="form-label">PIP Size</label>
	    <div class="input-group">
	       <input type="text" class="form-control" id="cur_pipSize" disabled>
		</div>
	  </div>
	  <div class="col-md-2 theme2 p-1">
	    <label for="cur_lotSize" class="form-label">Lot Size</label>
	  	<div class="input-group">
	    <input type="text" class="form-control" id="cur_lotSize" disabled>
	    </div>
	  </div>
	  <div class="col-md-4 theme2 p-1">
	    <label for="cur_profitAndLossPerLotPerPip" class="form-label">P/L PerLot PerPip</label>
	  	<div class="input-group">
	    <input type="text" class="form-control" id="cur_profitAndLossPerLotPerPip" disabled>
	    </div>
      </div>

</div>