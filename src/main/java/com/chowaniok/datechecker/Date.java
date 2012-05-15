package com.chowaniok.datechecker;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Date {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("MM-dd-yy");
		if (cal.get(Calendar.DATE) <= cal.getMaximum(Calendar.DAY_OF_MONTH)) {

			switch (cal.get(Calendar.DAY_OF_WEEK)) {
			case Calendar.SATURDAY:
				cal.add(Calendar.DATE, 3);
				break;
			case Calendar.SUNDAY:
				cal.add(Calendar.DATE, 2);
				break;
			case Calendar.MONDAY:
				cal.add(Calendar.DATE, 1);
				break;

			default:
				cal.add(Calendar.DATE, 1);
				break;
			}
		}

		String datestr = df.format(cal.getTime());
		Runtime rt = Runtime.getRuntime();
		Process proc;
		try {
			proc = rt.exec("cmd /C date " + datestr);
			// proc = rt.exec("cmd /C time " + timestr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
