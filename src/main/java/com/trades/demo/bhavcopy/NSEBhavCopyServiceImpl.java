package com.trades.demo.bhavcopy;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.trades.demo.models.CandleModel;
import com.trades.demo.utils.DataHandler;

@Component("nseBhavCopyService")
public class NSEBhavCopyServiceImpl implements NSEBhavCopyService 
{
	Logger logger = LoggerFactory.getLogger(NSEBhavCopyServiceImpl.class);
	
	String NSE_BHAVCOPY_BASE_DIR = "C:\\Users\\ashis\\Pictures\\nse_bhavcopy\\";
	String NSE_BHAVCOPY_ZIP_DIR = NSE_BHAVCOPY_BASE_DIR + "bhavcopy_zip\\";
	String NSE_BHAVCOPY_CSV_DIR = NSE_BHAVCOPY_BASE_DIR + "bhavcopy_csv\\";
	String NSE_BHAVCOPY_SYMBOLWISE_DIR = NSE_BHAVCOPY_BASE_DIR + "bhavcopy_symbol\\";

	@Override
	public String getNSEDailyEodBhavCopy(Date fromDate, Date tillDate) 
	{
		purgeEODDataDirectories();
		
		downloadBhavCopy(fromDate, tillDate);
		
		return "EOD Data Fetched Successful";
	}
	
	/**
	 * Download Zip File For daily eod data for date range
	 * https://www1.nseindia.com/content/historical/EQUITIES/2020/JAN/cm02JAN2010bhav.csv.zip
	 */
	private void downloadBhavCopy(Date fromDate, Date tillDate) 
	{
		try  
		{
			
			LocalDate start = fromDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate end = tillDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

			for (LocalDate ld = start; !ld.isAfter(end); ld = ld.plusDays(1)) 
			{
				String marketDateStr = ld.getDayOfMonth()+""+ld.getMonthValue()+""+ld.getYear();
				Date marketDate = Date.from(ld.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
				String day=  new SimpleDateFormat("dd").format(marketDate);
				String month = new SimpleDateFormat("MMM").format(marketDate).toUpperCase();
				String year = new SimpleDateFormat("yyyy").format(marketDate);
				
				String baseUrl = "https://www1.nseindia.com/content/historical/EQUITIES/"+year+"/"+month+"/";
				String fileName = "cm"+day+month+year+"bhav.csv.zip";
				String FILE_URL = baseUrl + fileName;
				logger.info(FILE_URL);
				try
				{
					downloadZip(fileName, FILE_URL);
				    
				}
				catch (Exception e) {
					logger.info("File Fetching Failed "+marketDateStr+" >> "+e);
				}
				
			}
			
			prepareSymbolwiseFiles();
			
		} 
		catch (Exception e) 
		{
			logger.info("NseSiteAPIServiceImpl downloadEODZIP Failed "+e.getMessage());
		}
	}
	
	/**
	 * Method to download zip file by hitting specific date file url
	 * https://www1.nseindia.com/content/historical/EQUITIES/2020/JAN/cm02JAN2010bhav.csv.zip
	 * @param NSE_BHAVCOPY_ZIP_DIR
	 * @param fileName
	 * @param FILE_URL
	 * @param NSE_BHAVCOPY_CSV_DIR
	 * @throws IOException
	 * @throws MalformedURLException
	 * @throws FileNotFoundException
	 */
	private void downloadZip(String fileName, String FILE_URL) throws IOException, MalformedURLException, FileNotFoundException 
	{
		URL url = new URL(FILE_URL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
		conn.setRequestProperty("Referer", "https://www1.nseindia.com/products/content/equities/equities/archieve_eq.htm");
		
		int respCode = conn.getResponseCode(); 
		
		if(respCode==403)
		{
			logger.info("403 error for "+FILE_URL);
		}
		
		if(respCode!=404)
		{
			BufferedInputStream inputStream = new BufferedInputStream(conn.getInputStream());
			FileOutputStream fileOS = new FileOutputStream(NSE_BHAVCOPY_ZIP_DIR+fileName);
			
			byte data[] = new byte[1024];
			int byteContent;
			while ((byteContent = inputStream.read(data, 0, 1024)) != -1) 
			{
			    fileOS.write(data, 0, byteContent);
			}
			logger.info(fileName+" download complete");
			
			extractFile(NSE_BHAVCOPY_ZIP_DIR+fileName,NSE_BHAVCOPY_CSV_DIR,fileName.replace(".zip", ""));
		}
		
	}
	
	/**
	 * Extract the downloaded zip file to get csv
	 * @param zipFilePath
	 * @param destDir
	 * @param fileName
	 * @throws IOException
	 */
	private void extractFile(String zipFilePath,String destDir,String fileName) throws IOException 
	{
		File dir = new File(destDir);
        // create output directory if it doesn't exist
        if(!dir.exists()) dir.mkdirs();
        FileInputStream fis;
        //buffer for read and write data to file
        byte[] buffer = new byte[1024];
        try {
            fis = new FileInputStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            while(ze != null){
                //String fileName = ze.getName();
                File newFile = new File(destDir + File.separator + fileName);
                System.out.println("Unzipping to "+newFile.getAbsolutePath());
                //create directories for sub directories in zip
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
                }
                fos.close();
                //close this ZipEntry
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
            //close last ZipEntry
            zis.closeEntry();
            zis.close();
            fis.close();
            logger.info(fileName+" extract complete");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
	
	/**
	 * create symbol wise csv file for daily eod data
	 */
	private void prepareSymbolwiseFiles()
	{
		String COMMA_DELIMITER = ",";
		
		File[] files = new File(NSE_BHAVCOPY_CSV_DIR).listFiles();
		  
		  for (File file : files)
		  { 
			  if (file.isFile()) 
			  {
				  int count = 0;
				  try 
					{
						
						BufferedReader br = new BufferedReader(new FileReader(file));
						    String line;
						    while ((line = br.readLine()) != null) {
						    	
						    	if(count != 0)
						    	{
						    		 	String[] lineData = line.split(COMMA_DELIMITER);
								        
						    		 	if(lineData[1].equals("EQ"))
						    		 	{
						    		 		String symbol = lineData[0];
						    		 		String open = lineData[2];
						    		 		String high = lineData[3];
						    		 		String low = lineData[4];
						    		 		String close = lineData[5];
						    		 		String marketDate = lineData[10];
						    		 		
						    		 		String symbolFilePath = NSE_BHAVCOPY_SYMBOLWISE_DIR + symbol + ".csv";
						    		 		File symbolFile = new File(symbolFilePath);
						    		 		
						    		 		if(symbolFile.exists())
						    		 		{
						    		 			//append to existing symbol wise file
						    		 			 CSVWriter existingWriter = new CSVWriter(new FileWriter(symbolFilePath, true));
						    		 			 String[] newRow = { symbol, open, high, low, close, marketDate };
						    		 			 existingWriter.writeNext(newRow);
						    		 			 existingWriter.close();
						    		 		}
						    		 		else
						    		 		{
						    		 			//create new file for symbol
						    		 			FileWriter outputfile = new FileWriter(symbolFile);
						    		 			CSVWriter writer = new CSVWriter(outputfile);
						    		 			String[] header = { "SYMBOL", "OPEN", "HIGH" ,"LOW","CLOSE","MARKETDATE"};
						    		 	        writer.writeNext(header);
						    		 	        String[] row = { symbol, open, high, low, close, marketDate };
						    		 	        writer.writeNext(row);
						    		 	        writer.close();
						    		 		}
						    		 		
						    		 	}
						    		 	
						    	}
						       
						    	count++;
						        
						    }
						
						
					} 
					catch (Exception e) {
						logger.info("Exception while reading CSV "+e);
					}
			  }
			  logger.info(file.getName()+" done");
		  }	  
		  
		  logger.info("Symbolwise File Created Successfully.");
	}

	private void purgeEODDataDirectories() 
	{
		Arrays.stream(new File(NSE_BHAVCOPY_ZIP_DIR).listFiles()).forEach(File::delete);
		Arrays.stream(new File(NSE_BHAVCOPY_CSV_DIR).listFiles()).forEach(File::delete);
		Arrays.stream(new File(NSE_BHAVCOPY_SYMBOLWISE_DIR).listFiles()).forEach(File::delete);
	}

	@Override
	public String updateNSEDailyEodBhavCopy() 
	{
		List<CandleModel> candleList = DataHandler.readSymbolDailyEODCandles("ACC");	
		
		Calendar cal = Calendar.getInstance();  
		cal.setTime(candleList.get(candleList.size()-1).getMarketDateTime());
		cal.add(Calendar.DATE, 1);
		Date previousDate = cal.getTime();
		Date currentDate = new Date();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		
		String latestFilesDir =  "latest_"+dateFormat.format(previousDate)+"-TO-"+dateFormat.format(currentDate)+"\\";
		String zipFileDirectory = NSE_BHAVCOPY_ZIP_DIR + latestFilesDir;
		String csvFileDirectory = NSE_BHAVCOPY_CSV_DIR + latestFilesDir;
		
		
		try  
		{
			File zipFileDirectoryRef = new File(zipFileDirectory);
			if (zipFileDirectoryRef.exists())
			{
				zipFileDirectoryRef.delete();
				zipFileDirectoryRef.mkdir();
			}
			else
			{
				zipFileDirectoryRef.mkdir();
			}
			
			File csvFileDirectoryRef = new File(csvFileDirectory);
			if (csvFileDirectoryRef.exists())
			{
				csvFileDirectoryRef.delete();
				csvFileDirectoryRef.mkdir();
			}
			else
			{
				csvFileDirectoryRef.mkdir();
			}
			
			
			LocalDate start = previousDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate end = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			
			for (LocalDate ld = start; ld.isBefore(end) || ld.equals(end); ld = ld.plusDays(1)) 
			{
				String marketDateStr = ld.getDayOfMonth()+""+ld.getMonthValue()+""+ld.getYear();
				Date marketDate = Date.from(ld.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
				String day=  new SimpleDateFormat("dd").format(marketDate);
				String month = new SimpleDateFormat("MMM").format(marketDate).toUpperCase();
				String year = new SimpleDateFormat("yyyy").format(marketDate);
				
				String baseUrl = "https://www1.nseindia.com/content/historical/EQUITIES/"+year+"/"+month+"/";
				String fileName = "cm"+day+month+year+"bhav.csv.zip";
				String FILE_URL = baseUrl + fileName;
				logger.info(FILE_URL);
				try
				{
					downloadZip(fileName, FILE_URL);				    
				}
				catch (Exception e) {
					logger.info("File Fetching Failed "+marketDateStr+" >> "+e);
				}
				
			}
			
			updateSymbolwiseFiles(csvFileDirectory);
			
		} 
		catch (Exception e) 
		{
			logger.info("NseSiteAPIServiceImpl downloadEODZIP Failed "+e.getMessage());
		}
		
		return "EOD Data Updated Successful";
	}
	
	private void updateSymbolwiseFiles(String csvFileDirectory)
	{
		String COMMA_DELIMITER = ",";
		
		File[] files = new File(csvFileDirectory).listFiles();
		  
		  for (File file : files)
		  { 
			  if (file.isFile()) 
			  {
				  int count = 0;
				  try 
					{
						
						BufferedReader br = new BufferedReader(new FileReader(file));
						    String line;
						    while ((line = br.readLine()) != null) {
						    	
						    	if(count != 0)
						    	{
						    		 	String[] lineData = line.split(COMMA_DELIMITER);
								        
						    		 	if(lineData[1].equals("EQ"))
						    		 	{
						    		 		String symbol = lineData[0];
						    		 		String open = lineData[2];
						    		 		String high = lineData[3];
						    		 		String low = lineData[4];
						    		 		String close = lineData[5];
						    		 		String marketDate = lineData[10];
						    		 		
						    		 		String symbolFilePath = NSE_BHAVCOPY_SYMBOLWISE_DIR + symbol + ".csv";
						    		 		File symbolFile = new File(symbolFilePath);
						    		 		
						    		 		if(symbolFile.exists())
						    		 		{
						    		 			//append to existing symbol wise file
						    		 			 if(!csvContainsValue(symbolFilePath, 5, marketDate))
						    		 			 {
						    		 				 CSVWriter existingWriter = new CSVWriter(new FileWriter(symbolFilePath, true));
							    		 			 String[] newRow = { symbol, open, high, low, close, marketDate };
							    		 			 existingWriter.writeNext(newRow);
							    		 			 existingWriter.close();
						    		 			 }
						    		 		}
						    		 		else
						    		 		{
						    		 			//create new file for symbol
						    		 			FileWriter outputfile = new FileWriter(symbolFile);
						    		 			CSVWriter writer = new CSVWriter(outputfile);
						    		 			String[] header = { "SYMBOL", "OPEN", "HIGH" ,"LOW","CLOSE","MARKETDATE"};
						    		 	        writer.writeNext(header);
						    		 	        String[] row = { symbol, open, high, low, close, marketDate };
						    		 	        writer.writeNext(row);
						    		 	        writer.close();
						    		 		}
						    		 		
						    		 	}
						    		 	
						    	}
						       
						    	count++;
						        
						    }
						
						
					} 
					catch (Exception e) {
						logger.info("Exception while reading CSV "+e);
					}
			  }
			  logger.info(file.getName()+" done");
		  }	  
		  
		  logger.info("Symbolwise File Updated Successfully.");
	}
	
	
	 public boolean csvContainsValue(String pathToFile, int columnIndex, String targetValue) 
	 {
		 try  
		 {
			 	CSVReader reader = new CSVReader(new FileReader(pathToFile));
	            List<String[]> rows = reader.readAll();
	            for(String[] lineData : rows)
	            {
	            	if(lineData[columnIndex].replace("\"", "").equals(targetValue))
	            	{
	            		 logger.info("record exist for "+targetValue+"in "+pathToFile);
	            		return true;
	            	}
	            }
	            
	     }
		 catch (Exception e) 
		 {
			 logger.info(e.getMessage());
		 }
	     
		 return false;
	 }
	
}
