package com.selekode.topaz.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import com.selekode.topaz.model.UnixDateRange;

public class DatesUtils {
	public static long generateUnixDate() {
		Instant now = Instant.now();
		long unixTime = now.getEpochSecond();

		return unixTime;
	}
	
	public static String convertDateToString_ddMMMyyy(long date) {
		Instant instant = Instant.ofEpochSecond(date);

		// Define the desired format (DD-MMM-YYYY)
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a")
				.withZone(ZoneId.systemDefault());
		String dateStr = formatter.format(instant);

		return dateStr;
	}
	
	public static UnixDateRange calculateLastWeekDates() {
		long unixWeek = 604800;
		long dateEnd = Instant.now().getEpochSecond();
		long dateStart = dateEnd - unixWeek;
		UnixDateRange unixDates = new UnixDateRange(dateStart, dateEnd);

		return unixDates;
	}

	public static UnixDateRange calculateLastMonthDates() {
		long unixMonth = 2629746;
		long dateEnd = Instant.now().getEpochSecond();
		long dateStart = dateEnd - unixMonth;
		UnixDateRange unixDates = new UnixDateRange(dateStart, dateEnd);

		return unixDates;
	}
}
