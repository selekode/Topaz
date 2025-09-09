package com.selekode.topaz.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		LocalDate date = LocalDate.parse(dateStr, formatter);
		Long unixTime = date.atStartOfDay().toEpochSecond(ZoneOffset.UTC);

		return unixTime;
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
		// Create a map to store emotion counts with their names
		Map<String, Integer> emotionCounts = new HashMap<>();
		emotionCounts.put("Alegría", emotionFrequency.getEmocionAlegriaCount());
		emotionCounts.put("Tristeza", emotionFrequency.getEmocionTristezaCount());
		emotionCounts.put("Ira", emotionFrequency.getEmocionIraCount());
		emotionCounts.put("Miedo", emotionFrequency.getEmocionMiedoCount());
		emotionCounts.put("Ansiedad", emotionFrequency.getEmocionAnsiedadCount());
		emotionCounts.put("Amor", emotionFrequency.getEmocionAmorCount());
		emotionCounts.put("Sorpresa", emotionFrequency.getEmocionSorpresaCount());
		emotionCounts.put("Vergüenza", emotionFrequency.getEmocionVerguenzaCount());
		emotionCounts.put("Frustración", emotionFrequency.getEmocionFrustracionCount());
		emotionCounts.put("Satisfacción", emotionFrequency.getEmocionSatisfaccionCount());
		emotionCounts.put("Aburrimiento", emotionFrequency.getEmocionAburrimientoCount());
		emotionCounts.put("Serenidad", emotionFrequency.getEmocionSerenidadCount());
		emotionCounts.put("Confianza", emotionFrequency.getEmocionConfianzaCount());
		emotionCounts.put("Abrumado", emotionFrequency.getEmocionAbrumadoCount());
		emotionCounts.put("Esperanza", emotionFrequency.getEmocionEsperanzaCount());

		List<Map.Entry<String, Integer>> sortedEmotionList = new ArrayList<>(emotionCounts.entrySet());
		sortedEmotionList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

		// Set the top 4 emotions
		if (!sortedEmotionList.isEmpty()) {
			emotionFrequency.setTopEmotion1(sortedEmotionList.get(0).getKey());
			emotionFrequency.setTopEmotion1Count(sortedEmotionList.get(0).getValue());

			if (sortedEmotionList.size() > 1) {
				emotionFrequency.setTopEmotion2(sortedEmotionList.get(1).getKey());
				emotionFrequency.setTopEmotion2Count(sortedEmotionList.get(1).getValue());
			}

			if (sortedEmotionList.size() > 2) {
				emotionFrequency.setTopEmotion3(sortedEmotionList.get(2).getKey());
				emotionFrequency.setTopEmotion3Count(sortedEmotionList.get(2).getValue());
			}

			if (sortedEmotionList.size() > 3) {
				emotionFrequency.setTopEmotion4(sortedEmotionList.get(3).getKey());
				emotionFrequency.setTopEmotion4Count(sortedEmotionList.get(3).getValue());
			}
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
