package com.trades.demo.controller;


import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.trades.demo.models.LineChartModel;
import com.trades.demo.utils.URLConstants;


@Controller
@RequestMapping("oichart")
public class OIChartController 
{


	@RequestMapping("/lightweight/{filedate}")
	public String getLandingPage(@PathVariable("filedate") String filedate,Model model)
	{
		model.addAttribute("oiLineChartFIleDate", filedate);
		
		return "lightweightchartPage";
	}
	
	/*
	 * /linechartdata/25-Jul-2022
	 */
	@RequestMapping("/linechartdata/{filedate}")
	@ResponseBody
	public List<LineChartModel> getLineChartData(@PathVariable("filedate") String filedate)
	{
		String filepath = URLConstants.USDINR_OI_DATA_CSV_FILE+"usdinr_oidata_"+filedate+".csv";
		
		List<LineChartModel> data = new ArrayList<LineChartModel>();
		
		try 
		{
			FileReader fileReader = new FileReader(filepath);
			
			CSVReader csvReader = new CSVReaderBuilder(fileReader).build();
			
			List<String[]> allRows = csvReader.readAll();
			
			for(String[] row : allRows)
			{
				LineChartModel chartModel = new LineChartModel();
				chartModel.setTime(getIsoTime(row[0]));
				chartModel.setOpenInterst(Double.parseDouble(row[1]));
				chartModel.setPrice(Double.parseDouble(row[2]));
				data.add(chartModel);
			}
		} 
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		
		return data;
	}
	
	private String getIsoTime(String originalDate) 
	{
		String isoTime = "";
		
		
		try 
		{
			Date ogDate =new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(originalDate); 
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			sdf.setTimeZone(TimeZone.getTimeZone("IST"));
			isoTime = sdf.format(ogDate);
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
		
		
		return isoTime;
	}
}
