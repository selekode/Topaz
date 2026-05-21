package com.selekode.topaz.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.selekode.topaz.model.PersonalRatings;
import com.selekode.topaz.model.ActivityPerDayOfWeekDTO;
import com.selekode.topaz.model.DateRange;
import com.selekode.topaz.model.EmotionFrequencyDTO;
import com.selekode.topaz.model.EntryCountDTO;
import com.selekode.topaz.model.UnixDateRange;

public class StatsUtils {
	private StatsUtils() {
	}
	
	public static DateRange calculateDateRangeDDmmmYYYY(DateRange statsDateRange) {
		statsDateRange = DatesUtils.convertYYYYmmDDtoDDmmmYYYY(statsDateRange);
		
		return statsDateRange;
	}

	public static DateRange calculateDateRangeLastWeek() {
		long dateStart = DatesUtils.calculateLastWeekDates().getStartDate();
		long dateEnd = DatesUtils.calculateLastWeekDates().getEndDate();

		String dateStartStr = StatsUtils.convertDateLongToStr(dateStart);
		String dateEndStr = StatsUtils.convertDateLongToStr(dateEnd);
		DateRange dateRangeLastWeek = new DateRange(dateStartStr, dateEndStr);

		return dateRangeLastWeek;
	}

	public static DateRange calculateDateRangeLastMonth() {
		long dateStart = DatesUtils.calculateLastMonthDates().getStartDate();
		long dateEnd = DatesUtils.calculateLastMonthDates().getEndDate();

		String dateStartStr = StatsUtils.convertDateLongToStr(dateStart);
		String dateEndStr = StatsUtils.convertDateLongToStr(dateEnd);
		DateRange dateRangeLastMonth = new DateRange(dateStartStr, dateEndStr);

		return dateRangeLastMonth;
	}

	public static String convertDateToString_ddMMMyyy_hhmma(long date) {
		Instant instant = Instant.ofEpochSecond(date);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a")
				.withZone(ZoneId.systemDefault());
		String dateStr = formatter.format(instant);

		return dateStr;
	}

	public static String convertDateToString_ddMMMyyy(long date) {
		Instant instant = Instant.ofEpochSecond(date);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy").withZone(ZoneId.systemDefault());
		String dateStr = formatter.format(instant);

		return dateStr;
	}

	public static long convertDateStrToLong(String dateStr) {
	    if (dateStr == null || dateStr.isBlank()) {
	        return 0L; // fallback for null or empty strings
	    }

	    dateStr = dateStr.trim(); // remove whitespace/newlines

	    try {
	        LocalDate date;
	        // Detect the format
	        if (dateStr.matches("\\d{4}-\\d{2}-\\d{2}")) { // yyyy-MM-dd
	            date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
	        } else if (dateStr.matches("\\d{2}-[a-zA-Z]{3}-\\d{4}")) { // dd-MMM-yyyy
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
	            date = LocalDate.parse(dateStr, formatter);
	        } else if (dateStr.matches("\\d{8}")) { // yyyyMMdd
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	            date = LocalDate.parse(dateStr, formatter);
	        } else {
	            throw new DateTimeParseException("Unknown date format", dateStr, 0);
	        }

	        // Convert to Unix timestamp (seconds since epoch)
	        return date.atStartOfDay(ZoneOffset.UTC).toEpochSecond();
	    } catch (DateTimeParseException e) {
	        e.printStackTrace();
	        return 0L;
	    }
	}


	public static String convertDateLongToStr(long dateLong) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy").withZone(ZoneId.systemDefault());
		String dateStr = Instant.ofEpochSecond(dateLong).atZone(ZoneId.systemDefault()).format(formatter);

		return dateStr;
	}

	public static String convertObjectToJSON(Object objectToConvert) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(objectToConvert);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static EntryCountDTO calculateEntryCount(int journalEntryCount, int revisionEntryCount,
			int innerWorkEntryCount) {
		int totalEntryCount = journalEntryCount + revisionEntryCount + innerWorkEntryCount;
		EntryCountDTO entryCount = new EntryCountDTO(totalEntryCount, journalEntryCount, revisionEntryCount,
				innerWorkEntryCount);

		return entryCount;
	}

	public static ActivityPerDayOfWeekDTO calculateActivityPerDayOfWeek(
			ActivityPerDayOfWeekDTO activityPerDayOfWeek) {
		activityPerDayOfWeek.setEntryCounts(activityPerDayOfWeek.getMondayEntryCount(),
				activityPerDayOfWeek.getTuesdayEntryCount(), activityPerDayOfWeek.getWednesdayEntryCount(),
				activityPerDayOfWeek.getThursdayEntryCount(), activityPerDayOfWeek.getFridayEntryCount(),
				activityPerDayOfWeek.getSaturdayEntryCount(), activityPerDayOfWeek.getSundayEntryCount());

		// Calculate which day of the week has the most amount of entries
		activityPerDayOfWeek.setMostActiveDay(calculateDayWithHighestCount(activityPerDayOfWeek));

		// Calculate how many entries the day of the week with the most amount of
		// entries has
		activityPerDayOfWeek.setMostActiveDayN(calculateDayWithHighestCountN(activityPerDayOfWeek));

		return activityPerDayOfWeek;
	}

	public static String calculateMaxEntryDayOfWeek(int[] counts, String[] days) {
		int maxIndex = 0;
		for (int i = 1; i < counts.length; i++) {
			if (counts[i] > counts[maxIndex]) {
				maxIndex = i;
			}
		}
		return days[maxIndex]; // Returns the day with the highest count
	}

	public static String calculateDayWithHighestCount(ActivityPerDayOfWeekDTO activityPerDayOfWeek) {
		int[] entryCounts = { activityPerDayOfWeek.getMondayEntryCount(), activityPerDayOfWeek.getTuesdayEntryCount(),
				activityPerDayOfWeek.getWednesdayEntryCount(), activityPerDayOfWeek.getThursdayEntryCount(),
				activityPerDayOfWeek.getFridayEntryCount(), activityPerDayOfWeek.getSaturdayEntryCount(),
				activityPerDayOfWeek.getSundayEntryCount() };

		String[] days = { "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo" };

		return StatsUtils.calculateMaxEntryDayOfWeek(entryCounts, days);
	}

	public static int calculateDayWithHighestCountN(ActivityPerDayOfWeekDTO activityPerDayOfWeek) {
		int mostActiveDayN = 0;
		int[] entryCounts = { activityPerDayOfWeek.getMondayEntryCount(), activityPerDayOfWeek.getTuesdayEntryCount(),
				activityPerDayOfWeek.getWednesdayEntryCount(), activityPerDayOfWeek.getThursdayEntryCount(),
				activityPerDayOfWeek.getFridayEntryCount(), activityPerDayOfWeek.getSaturdayEntryCount(),
				activityPerDayOfWeek.getSundayEntryCount() };

		int maxEntries = 0;

		for (int i = 0; i < entryCounts.length; i++) {
			if (entryCounts[i] > maxEntries) {
				maxEntries = entryCounts[i];
			}
		}

		mostActiveDayN = maxEntries;
		
		return mostActiveDayN;
	}

	public static EmotionFrequencyDTO calculateEmotionFrequency(EmotionFrequencyDTO emotionFrequency) {
		// Map of emotion names to their count values
		Map<String, Integer> emotionMap = new LinkedHashMap<>();
		emotionMap.put("Alegría", emotionFrequency.getEmocionAlegriaCount());
		emotionMap.put("Tristeza", emotionFrequency.getEmocionTristezaCount());
		emotionMap.put("Ira", emotionFrequency.getEmocionIraCount());
		emotionMap.put("Miedo", emotionFrequency.getEmocionMiedoCount());
		emotionMap.put("Confianza", emotionFrequency.getEmocionConfianzaCount());
		emotionMap.put("Sorpresa", emotionFrequency.getEmocionSorpresaCount());
		emotionMap.put("Anticipación", emotionFrequency.getEmocionAnticipacionCount());
		emotionMap.put("Rechazo", emotionFrequency.getEmocionRechazoCount());
		emotionMap.put("Serenidad", emotionFrequency.getEmocionSerenidadCount());
		emotionMap.put("Melancolía", emotionFrequency.getEmocionMelancoliaCount());
		emotionMap.put("Fastidio", emotionFrequency.getEmocionFastidioCount());
		emotionMap.put("Aprensión", emotionFrequency.getEmocionAprensionCount());
		emotionMap.put("Aceptación", emotionFrequency.getEmocionAceptacionCount());
		emotionMap.put("Distracción", emotionFrequency.getEmocionDistraccionCount());
		emotionMap.put("Interés", emotionFrequency.getEmocionInteresCount());
		emotionMap.put("Aburrimiento", emotionFrequency.getEmocionAburrimientoCount());
		emotionMap.put("Éxtasis", emotionFrequency.getEmocionExtasisCount());
		emotionMap.put("Pena/Dolor", emotionFrequency.getEmocionPenaDolorCount());
		emotionMap.put("Furia", emotionFrequency.getEmocionFuriaCount());
		emotionMap.put("Terror", emotionFrequency.getEmocionTerrorCount());
		emotionMap.put("Admiración", emotionFrequency.getEmocionAdmiracionCount());
		emotionMap.put("Asombro", emotionFrequency.getEmocionAsombroCount());
		emotionMap.put("Vigilancia", emotionFrequency.getEmocionVigilanciaCount());
		emotionMap.put("Asco", emotionFrequency.getEmocionAscoCount());
		emotionMap.put("Ansiedad", emotionFrequency.getEmocionAnsiedadCount());
		emotionMap.put("Frustración", emotionFrequency.getEmocionFrustracionCount());
		emotionMap.put("Vergüenza", emotionFrequency.getEmocionVerguenzaCount());
		emotionMap.put("Esperanza", emotionFrequency.getEmocionEsperanzaCount());
		emotionMap.put("Orgullo", emotionFrequency.getEmocionOrgulloCount());
		emotionMap.put("Agobio", emotionFrequency.getEmocionAgobioCount());
		emotionMap.put("Neutral", emotionFrequency.getEmocionNeutralCount());

		// Find top 4 emotions
		List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(emotionMap.entrySet());
		sortedEntries.sort((a, b) -> b.getValue().compareTo(a.getValue()));

		// Set top 4
		if (sortedEntries.size() > 0) {
			emotionFrequency.setTopEmotion1(sortedEntries.get(0).getKey());
			emotionFrequency.setTopEmotion1Count(sortedEntries.get(0).getValue());
		}
		if (sortedEntries.size() > 1) {
			emotionFrequency.setTopEmotion2(sortedEntries.get(1).getKey());
			emotionFrequency.setTopEmotion2Count(sortedEntries.get(1).getValue());
		}
		if (sortedEntries.size() > 2) {
			emotionFrequency.setTopEmotion3(sortedEntries.get(2).getKey());
			emotionFrequency.setTopEmotion3Count(sortedEntries.get(2).getValue());
		}
		if (sortedEntries.size() > 3) {
			emotionFrequency.setTopEmotion4(sortedEntries.get(3).getKey());
			emotionFrequency.setTopEmotion4Count(sortedEntries.get(3).getValue());
		}

		return emotionFrequency;
	}

	public static PersonalRatings calculateRatingsAverage(List<PersonalRatings> personalRatings) {
		if (personalRatings.isEmpty()) {
			return new PersonalRatings(0, 0, 0, 0, 0, 0, 0, 0, 0); // Return zeroed object if list is empty
		}

		int totalEntries = personalRatings.size();
		int totalDisciplina = 0, totalOrden = 0, totalImpulsividad = 0, totalConstancia = 0;
		int totalTolerancia = 0, totalControlPrepotencia = 0, totalHonestidad = 0, totalAceptacion = 0;
		int totalConsecucionObjetivos = 0;

		// Sum all values for each rating field
		for (PersonalRatings ratings : personalRatings) {
			totalDisciplina += ratings.getValoracionDisciplina();
			totalOrden += ratings.getValoracionOrden();
			totalImpulsividad += ratings.getValoracionImpulsividad();
			totalConstancia += ratings.getValoracionConstancia();
			totalTolerancia += ratings.getValoracionTolerancia();
			totalControlPrepotencia += ratings.getValoracionControlPrepotencia();
			totalHonestidad += ratings.getValoracionHonestidad();
			totalAceptacion += ratings.getValoracionAceptacion();
			totalConsecucionObjetivos += ratings.getValoracionConsecucionObjetivos();
		}

		// Calculate the averages for each field
		int avgDisciplina = totalDisciplina / totalEntries;
		int avgOrden = totalOrden / totalEntries;
		int avgImpulsividad = totalImpulsividad / totalEntries;
		int avgConstancia = totalConstancia / totalEntries;
		int avgTolerancia = totalTolerancia / totalEntries;
		int avgControlPrepotencia = totalControlPrepotencia / totalEntries;
		int avgHonestidad = totalHonestidad / totalEntries;
		int avgAceptacion = totalAceptacion / totalEntries;
		int avgConsecucionObjetivos = totalConsecucionObjetivos / totalEntries;

		PersonalRatings ratingsAverage = new PersonalRatings(avgDisciplina, avgOrden, avgImpulsividad, avgConstancia,
				avgTolerancia, avgControlPrepotencia, avgHonestidad, avgAceptacion, avgConsecucionObjetivos);

		return ratingsAverage;
	}
}
