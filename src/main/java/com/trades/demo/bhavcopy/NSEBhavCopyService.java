package com.trades.demo.bhavcopy;

import java.util.Date;

public interface NSEBhavCopyService 
{
	public String getNSEDailyEodBhavCopy(Date from,Date till);
	
	public String updateNSEDailyEodBhavCopy();
}
