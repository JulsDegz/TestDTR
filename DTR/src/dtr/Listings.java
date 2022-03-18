package dtr;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Listings {

	KL kl = new KL();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * Calendar calen = new GregorianCalendar(); Date dates = new Date();
		 * 
		 * SimpleDateFormat sdf_time = new SimpleDateFormat("hh:mm:ss");
		 * SimpleDateFormat sdf_date = new SimpleDateFormat("MM dd, yyyy");
		 * 
		 * 
		 * 
		 * 
		 * System.out.println(sdf_time.format(dates));
		 * System.out.println(sdf_date.format(dates)); System.out.println(dates);
		 */
		try {
			timesolve();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void timesolve() throws ParseException {
		String t1 = ":25:00";
		String t2 = ":00:00";

		int thrs1 = 19;
		int thrs2 = 10;
		if (thrs1 > thrs2) {
			thrs2 += 24;
		}
		String time1 = thrs1 + t1;
		String time2 = thrs2 + t2;

		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		Date date1 = format.parse(time1);
		Date date2 = format.parse(time2);

		long diff = date2.getTime() - date1.getTime();

		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		long diffDays = diff / (24 * 60 * 60 * 1000);

		System.out.print(diffDays + " days, ");
		System.out.print(diffHours + " hours, ");
		System.out.print(diffMinutes + " minutes, ");
		System.out.print(diffSeconds + " seconds.");

	}

	public void runme() {

		Listings kk = new Listings();
		for (int i = 0; i < kk.kl.listss().length; i++) {

			System.out.println(i + 1 + ". " + kk.kl.mmm() + kk.kl.listss()[i]);

		}

	}

}
