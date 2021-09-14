package com.trades.demo.common;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import org.springframework.util.ResourceUtils;

public class ShortListedStocks 
{
	public static List<String> strategy1()
	{
		try 
		{
			File shortlistedile = ResourceUtils.getFile("classpath:Shortlisted-strategy1.txt");
			  
			  List<String> lines = Collections.emptyList();
			  lines = Files.readAllLines(Paths.get(shortlistedile.getPath()));
			  
			  return lines;
		} 
		catch (Exception e) 
		{
			return null;
		}
	}
}
