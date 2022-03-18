package dtr;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeSolve {
	String inn,outt;
	String inDate,outDate;
	
	TimeSolve(String inn, String outt, String inDate, String outDate){
		this.inn = inn;
		this.outt = outt;
		this.inDate = inDate;
		this.outDate = outDate;
		
		
	}
	
	
	public long TotalTime() throws ParseException {
		long diff = 0;
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		
		Date date1 = format.parse(this.inn);
		Date date2 = format.parse(this.outt);
		Date date24 = format.parse("24:00:00");
		
		
		
		if(Integer.parseInt(outDate) > Integer.parseInt(inDate)) {
			int ans = Integer.parseInt(outDate) - Integer.parseInt(inDate);
			for(int i=0; i < ans;i++) {
				diff += (date2.getTime() + date24.getTime()) - date1.getTime();
			}		
		}else {
			diff = date2.getTime() - date1.getTime();
		}
		
		
		
//		long diffSeconds = diff / 1000 % 60;
//		long diffMinutes = diff / (60 * 1000) % 60;
//		long diffHours = diff / (60 * 60 * 1000) % 24; //remove %24 to get total hrs 
		
//		String sec = String.format("%02d", diffSeconds);
//		String min = String.format("%02d", diffMinutes);
//		String hrs = String.format("%02d", diffHours);
		
		
		
		//long diffDays = diff / (24 * 60 * 60 * 1000);

		
		
		return diff;
		
	}
	
	
	

}
