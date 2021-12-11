<%@ tag language="java" pageEncoding="ISO-8859-1"%>


<!-- Modal -->
<div class="modal fade" id="papaDecisionSheetModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">PAPA Decision Sheet</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <table class="table table-bordered table-hover" id="papaDecisionSheetTable">
			  <thead>
			    <tr class="table-dark">
			      <th scope="col">#</th>
			      <th scope="col">Trade Setup</th>
			      <th scope="col">PAPA Observation</th>
			      <th scope="col">Trigger Point</th>
			      <th scope="col">Supportive Indicators</th>
			      <th scope="col">Stop Loss</th>
			      <th scope="col">Target</th>
			    </tr>
			  </thead>
			  <tbody>
			    <tr>
			      <td>1</td>
			      <td>Double Top</td>
			      <td>Neutral or Bearish candle patterns at previous Resistance levels</td>
			      <td>Weapon of the bulls is taken out</td>
			      <td>Divergence with BKT, High Volume</td>
			      <td>High Of Weapon</td>
			      <td>Same as Double Top Rules</td>
			    </tr>
			    <tr>
			    	<td>2</td>
			    	<td>Double Bottom</td>
			    	<td>Neutral or Bullish candle patterns at previous Support levels</td>
			    	<td>Weapon of the bear is taken out</td>
			    	<td>Divergence with BKP, High Volume</td>
			    	<td>Same as Double Bottom Rules</td>
			    	<td></td>
			    </tr>
			    <tr>
			    	<td>3</td>
			    	<td>Three White Soldiers</td>
			    	<td>Three White Soldiers and then price dips in the range of 2nd soldier's High and Low levels</td>
			    	<td>Buying Signal/Bullish Candles</td>
			    	<td></td>
			    	<td>Low of 1st Candle</td>
			    	<td>As per Major Resistance or chart pattern</td>
			    </tr>
			     <tr>
			    	<td>4</td>
			    	<td>Three Black Crows</td>
			    	<td>Three Black Crows and price rallies in the range of 2nd crow's High and Low levels</td>
			    	<td>Selling Signals/bearish Candles</td>
			    	<td></td>
			    	<td>High of 1st Candle</td>
			    	<td>As per Major Support of chart pattern</td>
			    </tr>
			    <tr>
			    	<td>5</td>
			    	<td>Bulls Counter Attack</td>
			    	<td>Price open below the major support levels</td>
			    	<td>Once price enters again above the breakdown level</td>
			    	<td>Lower BBC Failed, High Volume</td>
			    	<td>Low of the Counter attack candle</td>
			    	<td>As per Major Resistance or chart pattern</td>
			    </tr>
			    <tr>
			    	<td>6</td>
			    	<td>Bears Counter Attack</td>
			    	<td>Price opens above the major resistance levels</td>
			    	<td>Once price enters again below the breakout level</td>
			    	<td>Upper BBC Failed, High Volume</td>
			    	<td>High of the Counter attack candle</td>
			    	<td>As per Major Support of chart pattern</td>
			    </tr>
			    <tr>
			    	<td>7</td>
			    	<td>Sandwich Pattern</td>
			    	<td>Multiple alternate Green and Red Candles within ranging price levels</td>
			    	<td>Once sandwich pattern breakout candle closes above the previous candle or breakdown candle closes below the previous candle</td>
			    	<td></td>
			    	<td>Low of the sandwich breakout candle or High of the sandwich breakdown candle</td>
			    	<td>Major Resistance for breakout candle or major support for breakdown candle or as per chart pattern</td>
			    </tr>
			    <tr>
			    	<td>8</td>
			    	<td>Rounding Bottom</td>
			    	<td>Multiple big red candles with no strong follow-up,mostly forming neutral candle patterns</td>
			    	<td>Strong breakout candle closing above the sideways range</td>
			    	<td></td>
			    	<td>Low of the candles in the range at the bottom</td>
			    	<td>As per Major Resistance or chart pattern</td>
			    </tr>
			    <tr>
			    	<td>9</td>
			    	<td>Rounding Top</td>
			    	<td>Multiple big green candles with no strong follow-up,mostly forming neutral candle patterns</td>
			    	<td>Strong breakdown candle closing below the sideways range</td>
			    	<td></td>
			    	<td>High of the candles in the range at Top</td>
			    	<td>As per Major Support of chart pattern</td>
			    </tr>
			    <tr>
			    	<td>10</td>
			    	<td>Genuine Breakout</td>
			    	<td>Shakeout at resistance level before actual breakout candle</td>
			    	<td>Once follow-up candle closes above the breakout level</td>
			    	<td><b>Ungli Setup</b></td>
			    	<td>Low of the breakout candle</td>
			    	<td>As per Major Resistance or chart pattern</td>
			    </tr>
			    <tr>
			    	<td>11</td>
			    	<td>Genuine Breakdown</td>
			    	<td>Shakeout at support level before actual breakdown candle</td>
			    	<td>Once follow-up candle closes belwo the breakdownn level</td>
			    	<td><b>Ungli Setup</b></td>
			    	<td>High of the breakdown candle</td>
			    	<td>As per Major Support of chart pattern</td>
			    </tr>
			    <tr>
			    	<td>12</td>
			    	<td>Fake Breakout</td>
			    	<td>Generally there is no shakeout before breakout, price again enters below the breakout level with bearish candles</td>
			    	<td>Once the follow-up candle closes below the breakout candle</td>
			    	<td>Divergences with <b>BKT</b>,with High Volume</td>
			    	<td>High of the breakout candle</td>
			    	<td>As per Major Support of chart pattern</td>
			    </tr>
			     <tr>
			    	<td>13</td>
			    	<td>Fake Breakdown</td>
			    	<td>Generally there is no shakeout before breakdown, price again enters above the breakdown level with bullish candles</td>
			    	<td>Once the follow-up candle closes above the breakdown candle</td>
			    	<td>Divergences with <b>BKP</b>,with High Volume</td>
			    	<td>Low of the breakdown candle</td>
			    	<td>As per Major Resistance or chart pattern</td>
			    </tr>
			     <tr>
			    	<td>14</td>
			    	<td>gap Up</td>
			    	<td>Price opens directly above the major resistance levels and sustains the gap</td>
			    	<td>One can enter after follow-up</td>
			    	<td><b>Ungli Setup</b></td>
			    	<td>Low of the breakout candle or below the Gap Up level</td>
			    	<td>As per Major Resistance or chart pattern</td>
			    </tr>
			     <tr>
			    	<td>15</td>
			    	<td>Gap Down</td>
			    	<td>Price opens directly below the major support levels and sustains the gap</td>
			    	<td>One can enter after follow-up</td>
			    	<td><b>Ungli Setup</b></td>
			    	<td>High of the breakdown candle or above the Gap Down level</td>
			    	<td>As per Major Support of chart pattern</td>
			    </tr>
			  </tbody>
		</table>
      </div>
    </div>
  </div>
</div>