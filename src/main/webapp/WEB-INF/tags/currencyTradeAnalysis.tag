<%@ tag language="java" pageEncoding="ISO-8859-1"%>

<div class="row pt-1">
<div class="col-md-6">
	<label for="cur_StoryandViewBuilding" class="form-label">Story and View Building</label>
	<div class="input-group">
	<textarea class="form-control" rows="3" id="cur_StoryandViewBuilding"></textarea>
	</div>
</div>
<div class="col-md-6">
	<label for="cur_TradeBias" class="form-label">Trade Bias</label>
	<div class="input-group">
	<textarea class="form-control" rows="3" id="cur_TradeBias"></textarea>
	</div>
</div>
</div>

<div class="row">
<div class="col-md-6">
	<label for="cur_Opportunity" class="form-label">Opportunity</label>
	<div class="input-group">
	<textarea class="form-control" rows="3" id="cur_Opportunity"></textarea>
	</div>
</div>
<div class="col-md-6">
	<label for="cur_WHAT-IF" class="form-label">WHAT-IF</label>
	<div class="input-group">
	<textarea class="form-control" rows="3" id="cur_WHAT-IF"></textarea>
	</div>
</div>
</div>
<div class="row pt-1 pb-1">
<div class="col-md-6">
	<label for="cur_chartImageUrl" class="form-label">Chart Snapshot Url</label>
	<a class="link-primary float-end" href="https://beeimg.com/upload" target="_blank">upload</a>
 	<div class="input-group">
 	<span class="input-group-text">
		    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-image" viewBox="0 0 16 16">
			  <path d="M6.002 5.5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0z"/>
			  <path d="M2.002 1a2 2 0 0 0-2 2v10a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V3a2 2 0 0 0-2-2h-12zm12 1a1 1 0 0 1 1 1v6.5l-3.777-1.947a.5.5 0 0 0-.577.093l-3.71 3.71-2.66-1.772a.5.5 0 0 0-.63.062L1.002 12V3a1 1 0 0 1 1-1h12z"/>
			</svg>
	</span>
    <input type="text" class="form-control" id="cur_chartImageUrl">
    </div>
    <label for="tradeState" class="form-label">Trade State</label>
	<div class="input-group">
    <select class="form-select" aria-label="Select Trade State" id="cur_tradeState">
	  <option selected>Select Trade State</option>
	  <option value="Watchful">Watchful</option>
	  <option value="Go for it">Go for it</option>
	  <option value="Ignore">Ignore</option>
	  <option value="In Progress">In Progress</option>
	  <option value="Completed">Completed</option>
	  <option value="Practice">Practice</option>
	  </select>
    </div>
</div>
<div class="col-md-6">
	<label for="tradeComment" class="form-label">Comment</label>
	<div class="input-group">
	<textarea class="form-control" rows="2" id="cur_tradeComment"></textarea>
	</div>
	<div class="pt-2">
  	 <button type="button" class="btn theme5 w-100" id="cur_saveTradeBtn">Save Trade</button>
 	</div>
</div>
</div>

<div class="row pt-1 pb-1">
	<img src="https://iili.io/VcW1t4.png" class="img-fluid w-50" id="cur_chartImage" alt="chart image">
</div>
