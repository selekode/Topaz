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
import com.selekode.topaz.model.StatsActivityPerDayOfWeek;
import com.selekode.topaz.model.StatsEmotionFrequency;
import com.selekode.topaz.model.StatsEntryCount;
import com.selekode.topaz.model.UnixDates;

// Utility helper classes are a great way to reduce clutter in your service classes by moving reusable logic into separate, stateless classes. 
public class StatsHelper {
	private StatsHelper() {
		// Private constructor to prevent instantiation
	}

	public static String convertDateToString_ddMMMyyy_hhmma(long date) {
		// Convert the Unix timestamp (milliseconds) to an Instant
		Instant instant = Instant.ofEpochSecond(date);

		// Define the desired format (DD-MMM-YYYY)
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a")
				.withZone(ZoneId.systemDefault());
		String dateStr = formatter.format(instant);

		return dateStr;
	}

	public static String convertDateToString_ddMMMyyy(long date) {
		// Convert the Unix timestamp (milliseconds) to an Instant
		Instant instant = Instant.ofEpochSecond(date);

		// Define the desired format (DD-MMM-YYYY)
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

	public static UnixDates calculateLastWeekDates() {
		long unixWeek = 604800;
		long dateEnd = Instant.now().getEpochSecond();
		long dateStart = dateEnd - unixWeek;
		UnixDates unixDates = new UnixDates(dateStart, dateEnd);

		return unixDates;
	}

	public static UnixDates calculateLastMonthDates() {
		long unixMonth = 2629746;
		long dateEnd = Instant.now().getEpochSecond();
		long dateStart = dateEnd - unixMonth;
		UnixDates unixDates = new UnixDates(dateStart, dateEnd);

		return unixDates;
	}

	public static StatsEntryCount calculateEntryCount(int journalEntryCount, int revisionEntryCount) {
		int totalEntryCount = journalEntryCount + revisionEntryCount;
		StatsEntryCount entryCount = new StatsEntryCount(totalEntryCount, journalEntryCount, revisionEntryCount);

		return entryCount;
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

	public static String calculateDayWithHighestJournalCount(StatsActivityPerDayOfWeek activityPerDayOfWeek) {
		int[] journalCounts = { activityPerDayOfWeek.getJournalMondayEntryCount(),
				activityPerDayOfWeek.getJournalTuesdayEntryCount(),
				activityPerDayOfWeek.getJournalWednesdayEntryCount(),
				activityPerDayOfWeek.getJournalThursdayEntryCount(), activityPerDayOfWeek.getJournalFridayEntryCount(),
				activityPerDayOfWeek.getJournalSaturdayEntryCount(),
				activityPerDayOfWeek.getJournalSundayEntryCount() };

		String[] days = { "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo" };

		return StatsHelper.calculateMaxEntryDayOfWeek(journalCounts, days);
	}

	public static int calculateDayWithHighestJournalCountN(StatsActivityPerDayOfWeek activityPerDayOfWeek) {
		int journalMostActiveDayN = 0;
		int[] journalCounts = { activityPerDayOfWeek.getJournalMondayEntryCount(),
				activityPerDayOfWeek.getJournalTuesdayEntryCount(),
				activityPerDayOfWeek.getJournalWednesdayEntryCount(),
				activityPerDayOfWeek.getJournalThursdayEntryCount(), activityPerDayOfWeek.getJournalFridayEntryCount(),
				activityPerDayOfWeek.getJournalSaturdayEntryCount(),
				activityPerDayOfWeek.getJournalSundayEntryCount() };

		int maxJournalEntries = 0;

		for (int i = 0; i < journalCounts.length; i++) {
			if (journalCounts[i] > maxJournalEntries) {
				maxJournalEntries = journalCounts[i];
			}
		}

		journalMostActiveDayN = maxJournalEntries;

		return journalMostActiveDayN;
	}

	private static String calculateDayWithHighestRevisionCount(StatsActivityPerDayOfWeek activityPerDayOfWeek) {
		int[] revisionCounts = { activityPerDayOfWeek.getRevisionMondayEntryCount(),
				activityPerDayOfWeek.getRevisionTuesdayEntryCount(),
				activityPerDayOfWeek.getRevisionWednesdayEntryCount(),
				activityPerDayOfWeek.getRevisionThursdayEntryCount(),
				activityPerDayOfWeek.getRevisionFridayEntryCount(),
				activityPerDayOfWeek.getRevisionSaturdayEntryCount(),
				activityPerDayOfWeek.getRevisionSundayEntryCount() };

		String[] days = { "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo" };

		return StatsHelper.calculateMaxEntryDayOfWeek(revisionCounts, days);
	}

	public static int calculateDayWithHighestRevisionCountN(StatsActivityPerDayOfWeek activityPerDayOfWeek) {
		int revisionMostActiveDayN = 0;
		int[] revisionCounts = { activityPerDayOfWeek.getRevisionMondayEntryCount(),
				activityPerDayOfWeek.getRevisionTuesdayEntryCount(),
				activityPerDayOfWeek.getRevisionWednesdayEntryCount(),
				activityPerDayOfWeek.getRevisionThursdayEntryCount(),
				activityPerDayOfWeek.getRevisionFridayEntryCount(),
				activityPerDayOfWeek.getRevisionSaturdayEntryCount(),
				activityPerDayOfWeek.getRevisionSundayEntryCount() };
		int maxRevisionEntries = 0;

		for (int i = 0; i < revisionCounts.length; i++) {
			if (revisionCounts[i] > maxRevisionEntries) {
				maxRevisionEntries = revisionCounts[i];
			}
		}
		revisionMostActiveDayN = maxRevisionEntries;
		return revisionMostActiveDayN;
	}

	public static StatsActivityPerDayOfWeek calculateActivityPerDayOfWeek(
			StatsActivityPerDayOfWeek activityPerDayOfWeek) {
		// We set the entry counts to this object to help us with the calculation
		activityPerDayOfWeek.setJournalEntryCounts(activityPerDayOfWeek.getJournalMondayEntryCount(),
				activityPerDayOfWeek.getJournalTuesdayEntryCount(),
				activityPerDayOfWeek.getJournalWednesdayEntryCount(),
				activityPerDayOfWeek.getJournalThursdayEntryCount(), activityPerDayOfWeek.getJournalFridayEntryCount(),
				activityPerDayOfWeek.getJournalSaturdayEntryCount(), activityPerDayOfWeek.getJournalSundayEntryCount());

		activityPerDayOfWeek.setRevisionEntryCounts(activityPerDayOfWeek.getRevisionMondayEntryCount(),
				activityPerDayOfWeek.getRevisionTuesdayEntryCount(),
				activityPerDayOfWeek.getRevisionWednesdayEntryCount(),
				activityPerDayOfWeek.getRevisionThursdayEntryCount(),
				activityPerDayOfWeek.getRevisionFridayEntryCount(),
				activityPerDayOfWeek.getRevisionSaturdayEntryCount(),
				activityPerDayOfWeek.getRevisionSundayEntryCount());

		// Caluclate which day of the week has the most ammount of entries
		activityPerDayOfWeek.setJournalMostActiveDay(calculateDayWithHighestJournalCount(activityPerDayOfWeek));
		activityPerDayOfWeek.setRevisionMostActiveDay(calculateDayWithHighestRevisionCount(activityPerDayOfWeek));

		// Caluclate the ammount of entries the day of the week with the most ammount of
		// entries has
		activityPerDayOfWeek.setJournalMostActiveDayN(calculateDayWithHighestJournalCountN(activityPerDayOfWeek));
		activityPerDayOfWeek.setRevisionMostActiveDayN(calculateDayWithHighestRevisionCountN(activityPerDayOfWeek));
		return activityPerDayOfWeek;

	}

	public static StatsEmotionFrequency calculateEmotionFrequency(StatsEmotionFrequency emotionFrequency) {
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
