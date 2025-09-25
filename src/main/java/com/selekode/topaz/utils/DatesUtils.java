package com.selekode.topaz.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import com.selekode.topaz.model.DateRange;
import com.selekode.topaz.model.UnixDateRange;

public class DatesUtils {
	public static long generateUnixDate() {
		Instant now = Instant.now();
		long unixTime = now.getEpochSecond();

		return unixTime;
	}
	
	public static DateRange convertYYYYmmDDtoDDmmmYYYY(DateRange dateRangeYYYYmmDD) {
		String dateStart = dateRangeYYYYmmDD.getStartDate();
		String dateEnd = dateRangeYYYYmmDD.getEndDate();

		String dateStartStr = DatesUtils.convertDateStringToStringDDmmmYYYY(dateStart);
		String dateEndStr = DatesUtils.convertDateStringToStringDDmmmYYYY(dateEnd);
		DateRange dateRangeDDmmmYYYY = new DateRange(dateStartStr, dateEndStr);
		
		return dateRangeDDmmmYYYY;
	}
	
	public static String convertDateLongToStringDDmmmYYYY(long date) {
		Instant instant = Instant.ofEpochSecond(date);

		// Define the desired format (DD-MMM-YYYY)
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a")
				.withZone(ZoneId.systemDefault());
		String dateStr = formatter.format(instant);

		return dateStr;
	}
	
	public static String convertDateStringToStringDDmmmYYYY(String date) {
	    if (date == null || date.isBlank()) return "";

	    try {
	        // Trim input to remove whitespace/newlines
	        String cleanDate = date.trim();

	        // Parse the input string as ISO_LOCAL_DATE (yyyy-MM-dd)
	        LocalDate localDate = LocalDate.parse(cleanDate, DateTimeFormatter.ISO_LOCAL_DATE);

	        // Format it to dd MMM yyyy
	        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.forLanguageTag("es"));

	        return localDate.format(outputFormatter);

	    } catch (DateTimeParseException e) {
	        e.printStackTrace();
	        return ""; // fallback for invalid date strings
	    }
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
