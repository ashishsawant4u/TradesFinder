package com.trades.demo.utils;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.CSVReader;

public class StockIndicesHelper 
{
	static Logger logger = LoggerFactory.getLogger(StockIndicesHelper.class);
	
	static String CSV_EXTN = ".csv";
	
	public static void downloadAllIndices()
	{
		downloadZip(URLConstants.ALL_NSE_EQ_SYMBOLS_DOWNLOAD_URL, URLConstants.INDICES_BASE_DIR_LOC + IndicesConstants.ALL_NSE_EQ_SYMBOLS + CSV_EXTN);
		downloadZip(URLConstants.NIFTY_50_SYMBOLS_DOWNLOAD_URL, URLConstants.INDICES_BASE_DIR_LOC + IndicesConstants.NIFTY_50_SYMBOLS + CSV_EXTN);
		downloadZip(URLConstants.NIFTY_NEXT_50_SYMBOLS_DOWNLOAD_URL, URLConstants.INDICES_BASE_DIR_LOC + IndicesConstants.NIFTY_NEXT_50_SYMBOLS + CSV_EXTN);
		downloadZip(URLConstants.NIFTY_100_SYMBOLS_DOWNLOAD_URL, URLConstants.INDICES_BASE_DIR_LOC + IndicesConstants.NIFTY_100_SYMBOLS + CSV_EXTN);
		downloadZip(URLConstants.NIFTY_200_SYMBOLS_DOWNLOAD_URL, URLConstants.INDICES_BASE_DIR_LOC + IndicesConstants.NIFTY_200_SYMBOLS + CSV_EXTN);
		downloadZip(URLConstants.NIFTY_500_SYMBOLS_DOWNLOAD_URL, URLConstants.INDICES_BASE_DIR_LOC + IndicesConstants.NIFTY_500_SYMBOLS + CSV_EXTN);
		downloadZip(URLConstants.NIFTY_MULTICAP_500_SYMBOLS_DOWNLOAD_URL, URLConstants.INDICES_BASE_DIR_LOC + IndicesConstants.NIFTY_MULTICAP_500_SYMBOLS + CSV_EXTN);
	
		downloadZip(URLConstants.NIFTY_MIDCAP_50_SYMBOLS_DOWNLOAD_URL, URLConstants.INDICES_BASE_DIR_LOC + IndicesConstants.NIFTY_MIDCAP_50_SYMBOLS + CSV_EXTN);
		downloadZip(URLConstants.NIFTY_MIDCAP_100_SYMBOLS_DOWNLOAD_URL, URLConstants.INDICES_BASE_DIR_LOC + IndicesConstants.NIFTY_MIDCAP_100_SYMBOLS + CSV_EXTN);
		downloadZip(URLConstants.NIFTY_MIDCAP_150_SYMBOLS_DOWNLOAD_URL, URLConstants.INDICES_BASE_DIR_LOC + IndicesConstants.NIFTY_MIDCAP_150_SYMBOLS + CSV_EXTN);

		downloadZip(URLConstants.NIFTY_SMALLCAP_50_SYMBOLS_DOWNLOAD_URL, URLConstants.INDICES_BASE_DIR_LOC + IndicesConstants.NIFTY_SMALLCAP_50_SYMBOLS + CSV_EXTN);
		downloadZip(URLConstants.NIFTY_SMALLCAP_100_SYMBOLS_DOWNLOAD_URL, URLConstants.INDICES_BASE_DIR_LOC + IndicesConstants.NIFTY_SMALLCAP_100_SYMBOLS + CSV_EXTN);
		downloadZip(URLConstants.NIFTY_SMALLCAP_250_SYMBOLS_DOWNLOAD_URL, URLConstants.INDICES_BASE_DIR_LOC + IndicesConstants.NIFTY_SMALLCAP_250_SYMBOLS + CSV_EXTN);

		downloadZip(URLConstants.NIFTY_MICROCAP_250_SYMBOLS_DOWNLOAD_URL, URLConstants.INDICES_BASE_DIR_LOC + IndicesConstants.NIFTY_MICROCAP_250_SYMBOLS + CSV_EXTN);
		downloadZip(URLConstants.NIFTY_LARGE_MIDCAP_250_SYMBOLS_DOWNLOAD_URL, URLConstants.INDICES_BASE_DIR_LOC + IndicesConstants.NIFTY_LARGE_MIDCAP_250_SYMBOLS + CSV_EXTN);
		downloadZip(URLConstants.NIFTY_MID_SMALLCAP_400_SYMBOLS_DOWNLOAD_URL, URLConstants.INDICES_BASE_DIR_LOC + IndicesConstants.NIFTY_MID_SMALLCAP_400_SYMBOLS + CSV_EXTN);

		
		//Sector
		
		downloadZip(URLConstants.Nifty_Auto_Index, URLConstants.INDICES_BASE_DIR_LOC + IndicesConstants.Nifty_Auto_Index + CSV_EXTN);
		downloadZip(URLConstants.Nifty_Bank_Index, URLConstants.INDICES_BASE_DIR_LOC + IndicesConstants.Nifty_Bank_Index + CSV_EXTN);
		downloadZip(URLConstants.Nifty_Consumer_Durables_Index, URLConstants.INDICES_BASE_DIR_LOC + IndicesConstants.Nifty_Consumer_Durables_Index + CSV_EXTN);
		downloadZip(URLConstants.Nifty_Financial_Services_Index, URLConstants.INDICES_BASE_DIR_LOC + IndicesConstants.Nifty_Financial_Services_Index + CSV_EXTN);
		downloadZip(URLConstants.Nifty_Financial_Services_25_50_Index, URLConstants.INDICES_BASE_DIR_LOC + IndicesConstants.Nifty_Financial_Services_25_50_Index + CSV_EXTN);
		downloadZip(URLConstants.Nifty_FMCG_Index, URLConstants.INDICES_BASE_DIR_LOC + IndicesConstants.Nifty_FMCG_Index + CSV_EXTN);
		downloadZip(URLConstants.Nifty_Healthcare_Index, URLConstants.INDICES_BASE_DIR_LOC + IndicesConstants.Nifty_Healthcare_Index + CSV_EXTN);
		downloadZip(URLConstants.Nifty_IT_Index, URLConstants.INDICES_BASE_DIR_LOC + IndicesConstants.Nifty_IT_Index + CSV_EXTN);
		downloadZip(URLConstants.Nifty_Media_Index, URLConstants.INDICES_BASE_DIR_LOC + IndicesConstants.Nifty_Media_Index + CSV_EXTN);
		downloadZip(URLConstants.Nifty_Metal_Index, URLConstants.INDICES_BASE_DIR_LOC + IndicesConstants.Nifty_Metal_Index + CSV_EXTN);
		downloadZip(URLConstants.Nifty_Oil_And_Gas_Index, URLConstants.INDICES_BASE_DIR_LOC + IndicesConstants.Nifty_Oil_And_Gas_Index + CSV_EXTN);
		downloadZip(URLConstants.Nifty_Pharma_Index, URLConstants.INDICES_BASE_DIR_LOC + IndicesConstants.Nifty_Pharma_Index + CSV_EXTN);
		downloadZip(URLConstants.Nifty_Private_Bank_Index, URLConstants.INDICES_BASE_DIR_LOC + IndicesConstants.Nifty_Private_Bank_Index + CSV_EXTN);
		downloadZip(URLConstants.Nifty_PSU_Bank_Index, URLConstants.INDICES_BASE_DIR_LOC + IndicesConstants.Nifty_PSU_Bank_Index + CSV_EXTN);
		downloadZip(URLConstants.Nifty_Realty_Index, URLConstants.INDICES_BASE_DIR_LOC + IndicesConstants.Nifty_Realty_Index + CSV_EXTN);
	}
	
	private static void downloadZip(String downloadUrl, String fileDownloadLoc)
	{
		try 
		{
			URL url = new URL(downloadUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
			conn.setRequestProperty("Referer", "https://www1.nseindia.com/products/content/equities/equities/archieve_eq.htm");
			
			int respCode = conn.getResponseCode(); 
			
			if(respCode!=404)
			{
				BufferedInputStream inputStream = new BufferedInputStream(conn.getInputStream());
				FileOutputStream fileOS = new FileOutputStream(fileDownloadLoc);
				
				byte data[] = new byte[1024];
				int byteContent;
				while ((byteContent = inputStream.read(data, 0, 1024)) != -1) 
				{
				    fileOS.write(data, 0, byteContent);
				}
				logger.info(fileDownloadLoc+" download complete");
			}
			else
			{
				logger.info(downloadUrl+" Error "+respCode);
			}
		} 
		catch (Exception e) 
		{
			logger.info(e.getMessage());
		}
	}
	
	public static List<String> getSymbolsList(String indices)
	{
		String filePath = URLConstants.INDICES_BASE_DIR_LOC + indices + CSV_EXTN;

		return readSymbolColumn(filePath);
	}
		
	public static List<String> readSymbolColumn(String filePath)
	{
		List<String> symbolList = new ArrayList<String>();
		
		try 
		{
			FileReader filereader = new FileReader(filePath);
			CSVReader csvReader = new CSVReader(filereader);
			String [] nextLine;
			int symbolColumnPosition;
			nextLine = csvReader.readNext();
			symbolColumnPosition = Arrays.asList(nextLine).stream().map(h->h.toLowerCase().trim()).collect(Collectors.toList()).indexOf("symbol");
			int seriesColumnPosition = Arrays.asList(nextLine).stream().map(h->h.toLowerCase().trim()).collect(Collectors.toList()).indexOf("series");
			
			while ((nextLine = csvReader.readNext()) != null && symbolColumnPosition > -1) 
			{
				if(nextLine[seriesColumnPosition].equals("EQ"))
				{
					symbolList.add(nextLine[symbolColumnPosition]);
				}
			}
			
		} 
		catch (Exception e) 
		{
			logger.info(e.getMessage());
		}
		
		return symbolList;
	}
}	
