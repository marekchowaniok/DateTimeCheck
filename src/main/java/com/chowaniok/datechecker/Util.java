package com.chowaniok.datechecker;

import java.io.*;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.logging.*;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.joda.time.DateTime;

public class Util {
	private static Logger log; 

	private static final Util INSTANCE = new Util();

	private Util() {
	}
	
	static {
	    try {
	      FileHandler fh = new FileHandler("TestLog.log");
//	      fh.setFormatter(new SimpleFormatter());
	      fh.setFormatter(new Formatter() {
	         public String format(LogRecord rec) {
	            StringBuffer buf = new StringBuffer(1000);
//	            buf.append(new java.util.Date());
//	            buf.append(' ');
//	            buf.append(rec.getLevel());
	            buf.append(formatMessage(rec));
//	            buf.append(' ');
	            buf.append('\n');
	            return buf.toString();
	            }
	          });
	      log = Logger.getLogger("Util");
	      log.addHandler(fh);
	    }
	    catch (IOException e) {
	      e.printStackTrace();
	    }
	}


	public static final Util getInstance() {
		return INSTANCE;
	}

	public boolean isWeekdDay(Calendar calendar) {
		int hourOfDay = 0;
		int minuteOfHour = 0;
		int secondOfMinute = 0;
		int millisOfSecond = 0;
		DateTime dt = new DateTime(calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH)+1,
				calendar.get(Calendar.DAY_OF_MONTH), hourOfDay, minuteOfHour,
				secondOfMinute, millisOfSecond);
		boolean isWeekDay = false;
		switch (dt.getDayOfWeek()) {
		case 1:
			isWeekDay = true;
			break;
		case 2:
			isWeekDay = true;
			break;
		case 3:
			isWeekDay = true;
			break;
		case 4:
			isWeekDay = true;
			break;
		case 5:
			isWeekDay = true;
			break;
		case 6:
			isWeekDay = false;
			break;
		default:
			isWeekDay = false;
			break;
		}
		return isWeekDay;
	}

	public boolean isWeekEnd(Calendar calendar) {
		boolean weekend = false;
		if (!isWeekdDay(calendar)) {
			weekend = true;
		}
		return weekend;
	}

	public DateTime rollDate(DateTime dateTime) {
		// roll jus 1 day
		dateTime = dateTime.plusDays(1);

		// check for weekend
		while (isWeekEnd(dateTime.toCalendar(Locale.ENGLISH))) {
			dateTime = dateTime.plusDays(1);
		}
		return dateTime;
	}

	public DateTime getCalendarFromName(String dateString) {
		int year = Integer.parseInt(dateString.substring(0, 4));
		int month = Integer.parseInt(dateString.substring(4, 6));
		int day = Integer.parseInt(dateString.substring(6, 8));
		DateTime dateTime = new DateTime(year,month,day,0,0,0,0);
		return dateTime;
	}

	public void checkFiles(File dir) {
		boolean TF = false;
		boolean ES = false;
		boolean YM = false;
		boolean NQ = false;

		List<File> files = (List<File>) FileUtils.listFiles(dir,
				TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
		for (File file : files) {
			if (file.getName().contains("TF")) {
				TF = true;
			} else if (file.getName().contains("ES")) {
				ES = true;
			} else if (file.getName().contains("YM")) {
				YM = true;
			} else if (file.getName().contains("NQ")) {
				NQ = true;
			}
		}
		StringBuffer sb = new StringBuffer();
		if (!TF) {
			sb.append(" - TF");
		}
		if (!ES) {
			sb.append(" - ES");
		}
		if (!YM) {
			sb.append(" - YM");
		}
		if (!NQ) {
			sb.append(" - NQ");
		}
		if(sb.length() > 0) {
			sb.insert(0, dir.getName());
			log.warning(sb.toString());
		}
	}
	
	public boolean checkForMissingDate(DateTime dateTime, DateTime dateTime2) {
		if (dateTime.isEqual(dateTime2)) {
			return false;
		}else {
			long diff = dateTime.getMillis() - dateTime2.getMillis();
			if (Math.abs(diff) > 100000) {
				log.warning("calendar mismatch!! original calendar:"
						+ dateTime.toDate().toString() + " File calendar: " + dateTime2.toDate().toString());
				return true;
				
			}
		}
		return false;
	}

}
