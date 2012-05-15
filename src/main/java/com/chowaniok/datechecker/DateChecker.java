package com.chowaniok.datechecker;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.joda.time.DateTime;

public class DateChecker {

	private Logger log = Logger.getLogger(this.getClass().getName());
	private DateTime dateTime = new DateTime();

	public void checkDirs(String filePath) throws IOException {
		File dir = new File(filePath);
		String[] list = dir.list(DirectoryFileFilter.INSTANCE);
		for (int i = 0; i < list.length; i++) {
			dateTime = Util.getInstance().rollDate(dateTime);
			File file = new File(dir.getCanonicalFile(), list[i]);
			if (file.isDirectory()) {
				String dateString = list[i].toString();
				DateTime dateTimeFile = Util.getInstance().getCalendarFromName(dateString);

				if (dateTime.isAfter(dateTimeFile)) {
					dateTime = new DateTime(dateTimeFile);
				}
 				while (Util.getInstance().checkForMissingDate(dateTime, dateTimeFile)) {
					dateTime = Util.getInstance().rollDate(dateTime);
				}
				Util.getInstance().checkFiles(file);
			}
		}
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
//		DateTime dateTime = new DateTime();
		// System.out.println(day);

		new DateChecker().checkDirs("/Volumes/Public/sorted/Trading/Data/2011");
		//new DateChecker().checkDirs("/Volumes/2TSamsung/trading/data/NT7-data/2010");
	}

}
