package com.trades.demo.controller;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.trades.demo.utils.URLConstants;



@Controller
@RequestMapping("/oi")
public class OIController 
{
	Logger logger = LoggerFactory.getLogger(OIController.class);
	
	@CrossOrigin(origins = "https://www1.nseindia.com")
	@RequestMapping("/fetch/{oival}/{price}")
	@ResponseBody
	public String OIDataVal(Model model,@PathVariable("oival") String oival,@PathVariable("price") String price)
	{
		logger.info("OIDataVal >> "+oival+" price >> "+price);
		
		String todayFileName = "usdinr_oidata_"+new SimpleDateFormat("dd-MMM-yyyy").format(new Date())+".csv";
		
		
		String filePath = URLConstants.USDINR_OI_DATA_CSV_FILE+todayFileName;
		
		try 
		{
			File oiFile = new File(filePath);
			
			if (oiFile.createNewFile())
			{
				logger.info("File is created "+todayFileName);
			}
			
		    
			FileReader filereader = new FileReader(filePath);
			
		    CSVReader csvReader = new CSVReaderBuilder(filereader)
	                //.withSkipLines(1)
	                .build();
		    List<String[]> allData = csvReader.readAll();
		    
		 
		    String timestamp = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").format(new Date());
		    	
		    String data [] = {timestamp,oival,price};
		    
	        FileWriter outputfile = new FileWriter(filePath,true);
	  
	        CSVWriter writer = new CSVWriter(outputfile);
			
	        writer.writeNext(data);
			 
	        writer.close();
	    }
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    }
		
		return "OK";
	}
	
	@RequestMapping("/data/{oisymbol}")
	public String OIDataPage(Model model,@PathVariable("oisymbol") String oisymbol)
	{
		model.addAttribute("oisymbol", oisymbol);
		
		//jsoupImpl(model);
		//htmlUnitImpl();
		
		String url = "https://www1.nseindia.com/live_market/dynaContent/live_watch/get_quote/GetQuoteCID.jsp?underlying=USDINR&instrument=FUTCUR&expiry=29JUL2022&key=FUTCURUSDINR29JUL2022--08JUL2022&type=SELECT&strike=-";	
		String useragent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36";
		String referrer = "https://www1.nseindia.com/live_market/dynaContent/live_watch/get_quote/GetQuoteCID.jsp?underlying=USDINR&instrument=FUTCUR&expiry=29JUL2022&key=FUTCURUSDINR29JUL2022--08JUL2022&type=SELECT&strike=-";
		String cookie1 = "E2109FAE3F0EA09C38163BBF24DD9A7E~t53LAJFVQDcB/+q14T3amyom/sJ5dm1gV7z2R0E3DKg6WiKBpLgF0t1Mv32gad4CqvL3DIswsfAKTAHD16vNlona86iCn3267hHmZU/O7DrKPY73XE6C4p5geps7yRwXxoUOlsqqPtbPsWsxE7cyDxr6R+RFqYMoDc9XuhS7e18=";
		
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "C:\\Users\\ashis\\Downloads\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
		capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_CUSTOMHEADERS_PREFIX + "Accept-Encoding", "gzip");
		capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_CUSTOMHEADERS_PREFIX + "Accept-Language", "en-IN,en;q=0.9,en-GB;q=0.8,en-US;q=0.7,hi;q=0.6");
		capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_CUSTOMHEADERS_PREFIX + "User-Agent", useragent);
		capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_CUSTOMHEADERS_PREFIX + "Referer", referrer);
		capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_CUSTOMHEADERS_PREFIX + "Cookie", cookie1);
		
		
		PhantomJSDriver driver = new PhantomJSDriver(capabilities);
		
		driver.get(url);
		Document doc = Jsoup.parse(driver.getPageSource());
		
		System.out.println(doc.getElementById("openInterest").html());
		
		
		return "oiDetailsPage";
	}

	private void htmlUnitImpl() {
		String url = "https://www1.nseindia.com/live_market/dynaContent/live_watch/get_quote/GetQuoteCID.jsp?underlying=USDINR&instrument=FUTCUR&expiry=29JUL2022&key=FUTCURUSDINR29JUL2022--08JUL2022&type=SELECT&strike=-";	
		String useragent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36";
		String referrer = "https://www1.nseindia.com/live_market/dynaContent/live_watch/get_quote/GetQuoteCID.jsp?underlying=USDINR&instrument=FUTCUR&expiry=29JUL2022&key=FUTCURUSDINR29JUL2022--08JUL2022&type=SELECT&strike=-";
		String cookie1 = "E2109FAE3F0EA09C38163BBF24DD9A7E~t53LAJFVQDcB/+q14T3amyom/sJ5dm1gV7z2R0E3DKg6WiKBpLgF0t1Mv32gad4CqvL3DIswsfAKTAHD16vNlona86iCn3267hHmZU/O7DrKPY73XE6C4p5geps7yRwXxoUOlsqqPtbPsWsxE7cyDxr6R+RFqYMoDc9XuhS7e18=";
		
		
		try 
		{
//			WebClient webClient = new WebClient();
//			webClient.addRequestHeader("User-Agent", useragent);
//			webClient.addRequestHeader("Referer", referrer);
//			webClient.addRequestHeader("Accept-Encoding", "gzip");
//			webClient.addRequestHeader("Accept-Language", "en-IN,en;q=0.9,en-GB;q=0.8,en-US;q=0.7,hi;q=0.6");
//			//webClient.addRequestHeader("Cookie",cookie1);
//			 CookieManager cookieManager = webClient.getCookieManager();
//			 cookieManager.setCookiesEnabled(true);
//			 Cookie cookie = new Cookie("www1.nseindia.com", "Cookie",cookie1 );
//		     cookieManager.addCookie(cookie);
//		     webClient.setCookieManager(cookieManager);
//			
//			webClient.waitForBackgroundJavaScript(50000);
//			webClient.getOptions().setThrowExceptionOnScriptError(false);
//			webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
//			webClient.getOptions().setPrintContentOnFailingStatusCode(false);
//			webClient.getOptions().setCssEnabled(true);
//			webClient.getOptions().setJavaScriptEnabled(true);
//			
//			HtmlPage myPage = webClient.getPage(url);
//			
//			Document doc = Jsoup.parse(myPage.asXml());
//			
//			System.out.println("OI >> " +doc.getElementById("openInterest").html());
			
			//model.addAttribute("data", doc.getElementById("openInterest").html());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void jsoupImpl(Model model) throws IOException 
	{
		String url = "https://www1.nseindia.com/live_market/dynaContent/live_watch/get_quote/GetQuoteCID.jsp?underlying=USDINR&instrument=FUTCUR&expiry=29JUL2022&key=FUTCURUSDINR29JUL2022--08JUL2022&type=SELECT&strike=-";	
		String useragent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36";
		String referrer = "https://www1.nseindia.com/live_market/dynaContent/live_watch/get_quote/GetQuoteCID.jsp?underlying=USDINR&instrument=FUTCUR&expiry=29JUL2022&key=FUTCURUSDINR29JUL2022--08JUL2022&type=SELECT&strike=-";
		String cookie1 = "E2109FAE3F0EA09C38163BBF24DD9A7E~t53LAJFVQDcB/+q14T3amyom/sJ5dm1gV7z2R0E3DKg6WiKBpLgF0t1Mv32gad4CqvL3DIswsfAKTAHD16vNlona86iCn3267hHmZU/O7DrKPY73XE6C4p5geps7yRwXxoUOlsqqPtbPsWsxE7cyDxr6R+RFqYMoDc9XuhS7e18=";
		
		Document doc = Jsoup.connect(url).userAgent(useragent).referrer(referrer).header("Accept-Encoding", "gzip")
						  .header("Accept-Language", "en-IN,en;q=0.9,en-GB;q=0.8,en-US;q=0.7,hi;q=0.6")
						  .header("Cookie",cookie1).get();
		
		//model.addAttribute("data", doc.getElementById("openInterest").html());
		model.addAttribute("data", doc.html());
	}
}
