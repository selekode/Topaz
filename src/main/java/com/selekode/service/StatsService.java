package com.selekode.service;

import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.selekode.repository.StatsRepository;
import com.selekode.topaz.model.StatsDateRange;
import com.selekode.topaz.model.StatsEmotionAndRatingCorrelationData;
import com.selekode.topaz.model.StatsEmotionFrequency;
import com.selekode.topaz.model.StatsEntryCount;
import com.selekode.topaz.model.PersonalRatings;
import com.selekode.topaz.model.StatsActivityPerDayOfWeek;

@Service
public class StatsService {
	public static StatsDateRange getDateRangeLastWeek() {
		long unixWeek = 604800;
		long dateEnd = Instant.now().getEpochSecond();
		long dateStart = dateEnd - unixWeek;

		String dateStartStr = convertDateLongToStr(dateStart);
		String dateEndStr = convertDateLongToStr(dateEnd);
		StatsDateRange dateRangeLastWeek = new StatsDateRange(dateStartStr, dateEndStr);

		return dateRangeLastWeek;
	}

	public static StatsDateRange getDateRangeLastMonth() {
		long unixWeek = 2629746;
		long dateEnd = Instant.now().getEpochSecond();
		long dateStart = dateEnd - unixWeek;

		String dateStartStr = convertDateLongToStr(dateStart);
		String dateEndStr = convertDateLongToStr(dateEnd);
		StatsDateRange dateRangeLastWeek = new StatsDateRange(dateStartStr, dateEndStr);

		return dateRangeLastWeek;
	}

	public static StatsEntryCount getEntryCountAllTime() {
		// Calculate entryCounts for all time
		StatsEntryCount entryCount = null;
		int journalEntryCount = StatsRepository.getJournalEntryCountAllTime();
		int revisionEntryCount = StatsRepository.getRevisionEntryCountAllTime();
		int totalEntryCount = journalEntryCount + revisionEntryCount;
		entryCount = new StatsEntryCount(totalEntryCount, journalEntryCount, revisionEntryCount);

		return entryCount;
	}

	public static StatsEntryCount getEntryCountDateRange(StatsDateRange statsDateRange) {
		// Calculate entryCounts in a date range
		long dateStart = convertDateStrToLong(statsDateRange.getStartDate());
		long dateEnd = convertDateStrToLong(statsDateRange.getEndDate());
		StatsEntryCount entryCount = null;
		int journalEntryCount = StatsRepository.getJournalEntryCountDateRange(dateStart, dateEnd);
		int revisionEntryCount = StatsRepository.getRevisionEntryCountDateRange(dateStart, dateEnd);
		int totalEntryCount = journalEntryCount + revisionEntryCount;
		entryCount = new StatsEntryCount(totalEntryCount, journalEntryCount, revisionEntryCount);

		return entryCount;
	}

	public static StatsEntryCount getEntryCountWeek() {
		long unixWeek = 604800;
		long dateEnd = Instant.now().getEpochSecond();
		long dateStart = dateEnd - unixWeek;
		StatsEntryCount entryCount = null;
		int journalEntryCount = StatsRepository.getJournalEntryCountDateRange(dateStart, dateEnd);
		int revisionEntryCount = StatsRepository.getRevisionEntryCountDateRange(dateStart, dateEnd);
		int totalEntryCount = journalEntryCount + revisionEntryCount;
		entryCount = new StatsEntryCount(totalEntryCount, journalEntryCount, revisionEntryCount);

		return entryCount;
	}

	public static StatsEntryCount getEntryCountMonth() {
		long unixMonth = 2629746;
		long dateEnd = Instant.now().getEpochSecond();
		long dateStart = dateEnd - unixMonth;
		StatsEntryCount entryCount = null;
		int journalEntryCount = StatsRepository.getJournalEntryCountDateRange(dateStart, dateEnd);
		int revisionEntryCount = StatsRepository.getRevisionEntryCountDateRange(dateStart, dateEnd);
		int totalEntryCount = journalEntryCount + revisionEntryCount;
		entryCount = new StatsEntryCount(totalEntryCount, journalEntryCount, revisionEntryCount);

		return entryCount;
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

	public static StatsActivityPerDayOfWeek getActivityPerDayOfWeekAllTime() {
		// Retreives row count from DB, adds them to activityPerDayOfWeek, leaving two
		// fields empty (journalMostActiveDayN & revisionMostActiveDayN), which we will
		// calculate and fill here in the service layer.
		StatsActivityPerDayOfWeek activityPerDayOfWeek = StatsRepository.getEntryCountPerDayAllTime();

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
		activityPerDayOfWeek.setJournalMostActiveDay(getDayWithHighestJournalCount(activityPerDayOfWeek));
		activityPerDayOfWeek.setRevisionMostActiveDay(getDayWithHighestRevisionCount(activityPerDayOfWeek));

		// Caluclate the ammount of entries the day of the week with the most ammount of
		// entries has
		activityPerDayOfWeek.setJournalMostActiveDayN(getDayWithHighestJournalCountN(activityPerDayOfWeek));
		activityPerDayOfWeek.setRevisionMostActiveDayN(getDayWithHighestRevisionCountN(activityPerDayOfWeek));

		return activityPerDayOfWeek;
	}

	public static StatsActivityPerDayOfWeek getActivityPerDayOfWeekDateRange(StatsDateRange statsDateRange) {
		// Retreives row count from DB, adds them to activityPerDayOfWeek, leaving two
		// fields empty (journalMostActiveDayN & revisionMostActiveDayN), which we will
		// calculate and fill here in the service layer.
		// Calculate entryCounts in a date range
		long dateStart = convertDateStrToLong(statsDateRange.getStartDate());
		long dateEnd = convertDateStrToLong(statsDateRange.getEndDate());
		StatsActivityPerDayOfWeek activityPerDayOfWeek = StatsRepository.getEntryCountPerDayDateRange(dateStart,
				dateEnd);

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

		// Calculate which day of the week has the most ammount of entries
		activityPerDayOfWeek.setJournalMostActiveDay(getDayWithHighestJournalCount(activityPerDayOfWeek));
		activityPerDayOfWeek.setRevisionMostActiveDay(getDayWithHighestRevisionCount(activityPerDayOfWeek));

		// Calculate the ammount of entries the day of the week with the most ammount of
		// entries has
		activityPerDayOfWeek.setJournalMostActiveDayN(getDayWithHighestJournalCountN(activityPerDayOfWeek));
		activityPerDayOfWeek.setRevisionMostActiveDayN(getDayWithHighestRevisionCountN(activityPerDayOfWeek));

		return activityPerDayOfWeek;
	}

	public static StatsActivityPerDayOfWeek getActivityPerDayOfWeekWeek() {
		long unixWeek = 604800;
		long dateEnd = Instant.now().getEpochSecond();
		long dateStart = dateEnd - unixWeek;
		StatsActivityPerDayOfWeek activityPerDayOfWeek = StatsRepository.getEntryCountPerDayDateRange(dateStart,
				dateEnd);

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

		// Calculate which day of the week has the most ammount of entries
		activityPerDayOfWeek.setJournalMostActiveDay(getDayWithHighestJournalCount(activityPerDayOfWeek));
		activityPerDayOfWeek.setRevisionMostActiveDay(getDayWithHighestRevisionCount(activityPerDayOfWeek));

		// Calculate the ammount of entries the day of the week with the most ammount of
		// entries has
		activityPerDayOfWeek.setJournalMostActiveDayN(getDayWithHighestJournalCountN(activityPerDayOfWeek));
		activityPerDayOfWeek.setRevisionMostActiveDayN(getDayWithHighestRevisionCountN(activityPerDayOfWeek));

		return activityPerDayOfWeek;
	}

	public static StatsActivityPerDayOfWeek getActivityPerDayOfWeekMonth() {
		long unixMonth = 2629746;
		long dateEnd = Instant.now().getEpochSecond();
		long dateStart = dateEnd - unixMonth;
		StatsActivityPerDayOfWeek activityPerDayOfWeek = StatsRepository.getEntryCountPerDayDateRange(dateStart,
				dateEnd);

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

		// Calculate which day of the week has the most ammount of entries
		activityPerDayOfWeek.setJournalMostActiveDay(getDayWithHighestJournalCount(activityPerDayOfWeek));
		activityPerDayOfWeek.setRevisionMostActiveDay(getDayWithHighestRevisionCount(activityPerDayOfWeek));

		// Calculate the ammount of entries the day of the week with the most ammount of
		// entries has
		activityPerDayOfWeek.setJournalMostActiveDayN(getDayWithHighestJournalCountN(activityPerDayOfWeek));
		activityPerDayOfWeek.setRevisionMostActiveDayN(getDayWithHighestRevisionCountN(activityPerDayOfWeek));

		return activityPerDayOfWeek;
	}

	public static String getDayWithHighestJournalCount(StatsActivityPerDayOfWeek activityPerDayOfWeek) {
		int[] journalCounts = { activityPerDayOfWeek.getJournalMondayEntryCount(),
				activityPerDayOfWeek.getJournalTuesdayEntryCount(),
				activityPerDayOfWeek.getJournalWednesdayEntryCount(),
				activityPerDayOfWeek.getJournalThursdayEntryCount(), activityPerDayOfWeek.getJournalFridayEntryCount(),
				activityPerDayOfWeek.getJournalSaturdayEntryCount(),
				activityPerDayOfWeek.getJournalSundayEntryCount() };

		String[] days = { "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo" };

		return getMaxEntryDayOfWeek(journalCounts, days);
	}

	public static int getDayWithHighestJournalCountN(StatsActivityPerDayOfWeek activityPerDayOfWeek) {
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

	private static String getDayWithHighestRevisionCount(StatsActivityPerDayOfWeek activityPerDayOfWeek) {
		int[] revisionCounts = { activityPerDayOfWeek.getRevisionMondayEntryCount(),
				activityPerDayOfWeek.getRevisionTuesdayEntryCount(),
				activityPerDayOfWeek.getRevisionWednesdayEntryCount(),
				activityPerDayOfWeek.getRevisionThursdayEntryCount(),
				activityPerDayOfWeek.getRevisionFridayEntryCount(),
				activityPerDayOfWeek.getRevisionSaturdayEntryCount(),
				activityPerDayOfWeek.getRevisionSundayEntryCount() };

		String[] days = { "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo" };

		return getMaxEntryDayOfWeek(revisionCounts, days);
	}

	public static int getDayWithHighestRevisionCountN(StatsActivityPerDayOfWeek activityPerDayOfWeek) {
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

	private static String getMaxEntryDayOfWeek(int[] counts, String[] days) {
		int maxIndex = 0;
		for (int i = 1; i < counts.length; i++) {
			if (counts[i] > counts[maxIndex]) {
				maxIndex = i;
			}
		}
		return days[maxIndex]; // Returns the day with the highest count
	}

	public static StatsEmotionFrequency getEmotionFrequencyAllTime() {
		StatsEmotionFrequency emotionFrequency = StatsRepository.getEmotionCountAllTime();

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

		// Sort the map by values and get the top 4
		// entrySet() is a method on a Map that returns a Set of Map.Entry<K, V>
		// objects. Each Map.Entry represents a key-value pair from the map.
		// The entrySet() returns a collection of these pairs. The code converts this
		// set into a List<Map.Entry<String, Integer>>, which allows sorting since lists
		// can be sorted, but sets cannot.
		// After this line, sortedEmotionList contains a list of all entries from the
		// map (pairs of emotions and their counts) which can be sorted
		List<Map.Entry<String, Integer>> sortedEmotionList = new ArrayList<>(emotionCounts.entrySet());
		// compareTo() compares the two Integer values. If entry2.getValue() is greater
		// than entry1.getValue(), it returns a positive number, indicating entry2
		// should come before entry1 in the sorted list.
		sortedEmotionList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

		// Set the top 4 emotions
		emotionFrequency.setTopEmotion1(sortedEmotionList.get(0).getKey());
		emotionFrequency.setTopEmotion1Count(sortedEmotionList.get(0).getValue());

		emotionFrequency.setTopEmotion2(sortedEmotionList.get(1).getKey());
		emotionFrequency.setTopEmotion2Count(sortedEmotionList.get(1).getValue());

		emotionFrequency.setTopEmotion3(sortedEmotionList.get(2).getKey());
		emotionFrequency.setTopEmotion3Count(sortedEmotionList.get(2).getValue());

		emotionFrequency.setTopEmotion4(sortedEmotionList.get(3).getKey());
		emotionFrequency.setTopEmotion4Count(sortedEmotionList.get(3).getValue());
		return emotionFrequency;
	}

	public static StatsEmotionFrequency getEmotionFrequencyDateRange(StatsDateRange statsDateRange) {
		long dateStart = convertDateStrToLong(statsDateRange.getStartDate());
		long dateEnd = convertDateStrToLong(statsDateRange.getEndDate());
		StatsEmotionFrequency emotionFrequency = StatsRepository.getEmotionCountDateRange(dateStart, dateEnd);

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

	public static StatsEmotionFrequency getEmotionFrequencyWeek() {
		long unixWeek = 604800;
		long dateEnd = Instant.now().getEpochSecond();
		long dateStart = dateEnd - unixWeek;
		StatsEmotionFrequency emotionFrequency = StatsRepository.getEmotionCountDateRange(dateStart, dateEnd);

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

	public static StatsEmotionFrequency getEmotionFrequencyMonth() {
		long unixMonth = 2629746;
		long dateEnd = Instant.now().getEpochSecond();
		long dateStart = dateEnd - unixMonth;
		StatsEmotionFrequency emotionFrequency = StatsRepository.getEmotionCountDateRange(dateStart, dateEnd);

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

	public static PersonalRatings getRatingsAverageAllTime() {
		List<PersonalRatings> personalRatings = null;
		personalRatings = StatsRepository.getPersonalRatingsAllTime();

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

		// Return a new PersonalRatings object containing the averages

		System.out.println("AverageRatings:" + ratingsAverage.toString());
		return ratingsAverage;
	}

	public static PersonalRatings getRatingsAverageWeek() {
		long unixWeek = 604800;
		long dateEnd = Instant.now().getEpochSecond();
		long dateStart = dateEnd - unixWeek;

		List<PersonalRatings> personalRatings = null;
		personalRatings = StatsRepository.getPersonalRatingsDateRange(dateStart, dateEnd);

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

	public static PersonalRatings getRatingsAverageMonth() {
		long unixMonth = 2629746;
		long dateEnd = Instant.now().getEpochSecond();
		long dateStart = dateEnd - unixMonth;

		List<PersonalRatings> personalRatings = null;
		personalRatings = StatsRepository.getPersonalRatingsDateRange(dateStart, dateEnd);

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

	public static PersonalRatings getRatingsAverageDateRange(StatsDateRange statsDateRange) {
		long dateStart = convertDateStrToLong(statsDateRange.getStartDate());
		long dateEnd = convertDateStrToLong(statsDateRange.getEndDate());

		List<PersonalRatings> personalRatings = null;
		personalRatings = StatsRepository.getPersonalRatingsDateRange(dateStart, dateEnd);

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

	public static String convertObjectToJSON(Object objectToConvert) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(objectToConvert);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getRatingsTrendAllTime() {
		Map<String, PersonalRatings> personalRatingsDated = StatsRepository.findRatingsDatedAllTime();
		String jsonRatings = convertObjectToJSON(personalRatingsDated);
		System.out.println("JSON: " + jsonRatings);

		return jsonRatings;
	}

	public static String getRatingsTrendWeek() {
		long unixWeek = 604800;
		long dateEnd = Instant.now().getEpochSecond();
		long dateStart = dateEnd - unixWeek;
		Map<String, PersonalRatings> personalRatingsDated = StatsRepository.findRatingsDatedDateRange(dateStart,
				dateEnd);
		String jsonRatings = convertObjectToJSON(personalRatingsDated);
		System.out.println("JSON: " + jsonRatings);

		return jsonRatings;
	}

	public static String getRatingsTrendMonth() {
		long unixMonth = 2629746;
		long dateEnd = Instant.now().getEpochSecond();
		long dateStart = dateEnd - unixMonth;
		Map<String, PersonalRatings> personalRatingsDated = StatsRepository.findRatingsDatedDateRange(dateStart,
				dateEnd);
		String jsonRatings = convertObjectToJSON(personalRatingsDated);
		System.out.println("JSON: " + jsonRatings);

		return jsonRatings;
	}

	public static String getRatingsTrendDateRange(StatsDateRange statsDateRange) {
		long dateStart = convertDateStrToLong(statsDateRange.getStartDate());
		long dateEnd = convertDateStrToLong(statsDateRange.getEndDate());

		Map<String, PersonalRatings> personalRatingsDated = StatsRepository.findRatingsDatedDateRange(dateStart,
				dateEnd);
		String jsonRatings = convertObjectToJSON(personalRatingsDated);
		System.out.println("JSON: " + jsonRatings);

		return jsonRatings;
	}

	private static double[] computePearsonGetColumn(double[][] matrix, int colIndex) {
		return Arrays.stream(matrix).mapToDouble(row -> row[colIndex]).toArray();
	}

	public static String getCorrelationEmotionsRatingsAllTime() {
		StatsEmotionAndRatingCorrelationData emotionAndRatingCorrelationData = StatsRepository
				.findEmotionAndRatingCorrelationDataAllTime();
		double[][] emotions = emotionAndRatingCorrelationData.emotions;
		double[][] ratings = emotionAndRatingCorrelationData.ratings;

		int numEmotions = emotions[0].length;
		int numRatings = ratings[0].length;
		double[][] correlationMatrix = new double[numEmotions][numRatings];

		PearsonsCorrelation correlation = new PearsonsCorrelation();

		for (int i = 0; i < numEmotions; i++) {
			for (int j = 0; j < numRatings; j++) {
				double[] emotionColumn = computePearsonGetColumn(emotions, i);
				double[] ratingColumn = computePearsonGetColumn(ratings, j);
				double correlationValue = correlation.correlation(emotionColumn, ratingColumn);
				// Handle NaN values and replace with 0 or null equivalent
				if (Double.isNaN(correlationValue)) {
					correlationMatrix[i][j] = 0.0; // Replace NaN with 0 (or null equivalent)
				} else {
					correlationMatrix[i][j] = correlationValue;
				}
			}
		}

		// Convert the matrix to JSON
		String jsonCorrelationMatrix = convertObjectToJSON(correlationMatrix);
		System.out.println("JSON: " + jsonCorrelationMatrix);

		return jsonCorrelationMatrix;
	}

	/*
	public static String getCorrelationEmotionsRatingsMonth() {
		long unixWeek = 604800;
		long dateEnd = Instant.now().getEpochSecond();
		long dateStart = dateEnd - unixWeek;
		
		StatsEmotionAndRatingCorrelationData emotionAndRatingCorrelationData = StatsRepository
				.findEmotionAndRatingCorrelationDataAllTime();
		double[][] emotions = emotionAndRatingCorrelationData.emotions;
		double[][] ratings = emotionAndRatingCorrelationData.ratings;

		int numEmotions = emotions[0].length;
		int numRatings = ratings[0].length;
		double[][] correlationMatrix = new double[numEmotions][numRatings];

		PearsonsCorrelation correlation = new PearsonsCorrelation();

		for (int i = 0; i < numEmotions; i++) {
			for (int j = 0; j < numRatings; j++) {
				double[] emotionColumn = computePearsonGetColumn(emotions, i);
				double[] ratingColumn = computePearsonGetColumn(ratings, j);
				double correlationValue = correlation.correlation(emotionColumn, ratingColumn);
				// Handle NaN values and replace with 0 or null equivalent
				if (Double.isNaN(correlationValue)) {
					correlationMatrix[i][j] = 0.0; // Replace NaN with 0 (or null equivalent)
				} else {
					correlationMatrix[i][j] = correlationValue;
				}
			}
		}

		// Convert the matrix to JSON
		String jsonCorrelationMatrix = convertObjectToJSON(correlationMatrix);
		System.out.println("JSON: " + jsonCorrelationMatrix);

		return jsonCorrelationMatrix;
	}
	
	public static String getCorrelationEmotionsRatingsDateRange(StatsDateRange statsDateRange) {
		long dateStart = convertDateStrToLong(statsDateRange.getStartDate());
		long dateEnd = convertDateStrToLong(statsDateRange.getEndDate());
		StatsEmotionAndRatingCorrelationData emotionAndRatingCorrelationData = StatsRepository
				.findEmotionAndRatingCorrelationDataDateRange(dateStart, dateEnd);
		double[][] emotions = emotionAndRatingCorrelationData.emotions;
		double[][] ratings = emotionAndRatingCorrelationData.ratings;
		
		int numEmotions = emotions[0].length;
		int numRatings = ratings[0].length;
		double[][] correlationMatrix = new double[numEmotions][numRatings];
		
		PearsonsCorrelation correlation = new PearsonsCorrelation();
		
		for (int i = 0; i < numEmotions; i++) {
			for (int j = 0; j < numRatings; j++) {
				double[] emotionColumn = computePearsonGetColumn(emotions, i);
				double[] ratingColumn = computePearsonGetColumn(ratings, j);
				double correlationValue = correlation.correlation(emotionColumn, ratingColumn);
				// Handle NaN values and replace with 0 or null equivalent
				if (Double.isNaN(correlationValue)) {
					correlationMatrix[i][j] = 0.0; // Replace NaN with 0 (or null equivalent)
				} else {
					correlationMatrix[i][j] = correlationValue;
				}
			}
		}
		
		// Convert the matrix to JSON
		String jsonCorrelationMatrix = convertObjectToJSON(correlationMatrix);
		System.out.println("JSON: " + jsonCorrelationMatrix);
		
		return jsonCorrelationMatrix;
	}
	*/
}
