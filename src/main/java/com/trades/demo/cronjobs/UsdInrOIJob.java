package com.trades.demo.cronjobs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UsdInrOIJob 
{
	Logger logger = LoggerFactory.getLogger(UsdInrOIJob.class);
	
	//@Scheduled(fixedRate = 5000)
	public void fetchOI()  throws Exception
	{
		logger.info("attempting OI "+new Date());
		
		
		
		try 
		{
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("www1.nseindia.com", 443));
			
			URL url = new URL("https://www1.nseindia.com/live_market/dynaContent/live_watch/get_quote/GetQuoteCID.jsp?underlying=USDINR&instrument=FUTCUR&expiry=29JUL2022&key=FUTCURUSDINR29JUL2022--08JUL2022&type=SELECT&strike=-");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(15000);
			conn.setReadTimeout(15000);
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36");
			conn.setRequestProperty("Referer", "https://www1.nseindia.com/live_market/dynaContent/live_watch/get_quote/GetQuoteCID.jsp?underlying=USDINR&instrument=FUTCUR&expiry=29JUL2022&key=FUTCURUSDINR29JUL2022--08JUL2022&type=SELECT&strike=-");
			//conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*,q=0.8,application/signed-exchange;v=b3;q=0.9");
			conn.setRequestProperty("Accept-Encoding", "gzip");
			conn.setRequestProperty("Accept-Language", "en-IN,en;q=0.9,en-GB;q=0.8,en-US;q=0.7,hi;q=0.6");
			//conn.setRequestProperty("Connection", "keep-alive");
			//conn.setRequestProperty("Host", "keep-alive");
			//conn.setRequestProperty("Cache-Control","www1.nseindia.com");
			//conn.setRequestProperty("Host","www1.nseindia.com");
			//String cookie1 = "bm_sv=E2109FAE3F0EA09C38163BBF24DD9A7E~t53LAJFVQDcB/+q14T3amyom/sJ5dm1gV7z2R0E3DKg6WiKBpLgF0t1Mv32gad4CqvL3DIswsfAKTAHD16vNlona86iCn3267hHmZU/O7DrKPY73XE6C4p5geps7yRwXxoUOlsqqPtbPsWsxE7cyDxr6R+RFqYMoDc9XuhS7e18=";
			String cookie1 = "E2109FAE3F0EA09C38163BBF24DD9A7E~t53LAJFVQDcB/+q14T3amyom/sJ5dm1gV7z2R0E3DKg6WiKBpLgF0t1Mv32gad4CqvL3DIswsfAKTAHD16vNlona86iCn3267hHmZU/O7DrKPY73XE6C4p5geps7yRwXxoUOlsqqPtbPsWsxE7cyDxr6R+RFqYMoDc9XuhS7e18=";
			conn.setRequestProperty("Cookie",cookie1);
			
			int respCode = conn.getResponseCode(); 
			
			logger.info("respCode OI "+respCode);
			
		BufferedReader br = new BufferedReader(
		                     new InputStreamReader(conn.getInputStream()));
		
		  String inputLine;
		  while ((inputLine = br.readLine()) != null) {
		      System.out.println(inputLine);
		  }
		  br.close();
		
		  System.out.println("Done");
		} 
		catch (Exception e) 
		{
			logger.error("error while fetching oi  "+e);
		}
	}
}
