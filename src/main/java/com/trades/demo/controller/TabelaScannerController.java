package com.trades.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trades.demo.utils.IndicesConstants;
import com.trades.demo.utils.StockIndicesHelper;

@Controller
@RequestMapping("/tabela")
public class TabelaScannerController 
{

	@RequestMapping("/nifty-indices")
	public String downloadIndices(Model model)
	{	
		return "sectorSymbols";
	}
	
	
	@RequestMapping("/indices")
	@ResponseBody
	public List<List<String>> indices(@RequestParam(name="refresh",defaultValue = "false")String refresh)
	{	
		if(null!=refresh && refresh == "true")
		{
			StockIndicesHelper.downloadAllIndices();
		}
		
		List<List<String>> columns = new ArrayList<>();
		
		for(String sector : IndicesConstants.All_SECTOR_INDICES)
		{
			List<String> sectorSymbols =  StockIndicesHelper.getSymbolsList(sector);
			
			sectorSymbols.add(0, sector);
			
			columns.add(sectorSymbols);	
		}	
		
		return columns;
	}
	
	
	

	
	
}
