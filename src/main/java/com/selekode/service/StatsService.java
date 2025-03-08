package com.selekode.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.selekode.repository.StatsRepository;
import com.selekode.topaz.model.StatsDateRange;
import com.selekode.topaz.model.StatsEntryCount;
import com.selekode.topaz.model.StatsActivityPerDayOfWeek;

@Service
public class StatsService {
	/*
	 * public static StatsEntryCount calculateStatsInDateRange(StatsDateRange
	 * statsDateRange) { // Convert date from str to unix time long long dateStart =
	 * convertDateStrToLong(statsDateRange.getStartDate()); long dateEnd =
	 * convertDateStrToLong(statsDateRange.getEndDate()); StatsEntryCount
	 * statsEntryCount = getStatsEntryCountDateRange(dateStart,dateEnd);
	 * 
	 * return statsEntryCount; }
	 */

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

	public static long convertDateStrToLong(String dateStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		LocalDate date = LocalDate.parse(dateStr, formatter);
		Long unixTime = date.atStartOfDay().toEpochSecond(ZoneOffset.UTC);

		return unixTime;
	}

	public static StatsActivityPerDayOfWeek getActivityPerDayOfWeekAllTime() {
		StatsActivityPerDayOfWeek activityPerDayOfWeek = StatsRepository.getEntryCountPerDay();

		// Calculate most active day in journal
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

		activityPerDayOfWeek.setJournalMostActiveDay(getDayWithHighestJournalCount(activityPerDayOfWeek));
		activityPerDayOfWeek.setRevisionMostActiveDay(getDayWithHighestRevisionCount(activityPerDayOfWeek));
		// activityPerDayOfWeek.setRevisionMostActiveDay(activityPerDayOfWeek.getDayWithHighestRevisionCount());

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

		return getMaxDay(journalCounts, days);
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

		return getMaxDay(revisionCounts, days);
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

	private static String getMaxDay(int[] counts, String[] days) {
		int maxIndex = 0;
		for (int i = 1; i < counts.length; i++) {
			if (counts[i] > counts[maxIndex]) {
				maxIndex = i;
			}
		}
		return days[maxIndex]; // Returns the day with the highest count
	}

}