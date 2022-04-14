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
	<label for="cur_chartImageUrl" class="form-label">Chart Snapshot</label>
 	<div class="input-group">
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
  	 <button type="button" class="btn theme1 w-100" id="cur_saveTradeBtn">Save Trade</button>
 	</div>
</div>
</div>

<div class="row pt-1 pb-1">
	<img src="#" class="img-fluid" id="cur_chartImage" alt="chart image">
</div>
