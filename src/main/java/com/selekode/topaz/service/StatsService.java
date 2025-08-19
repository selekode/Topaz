package com.selekode.topaz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.selekode.topaz.model.StatsDateRange;
import com.selekode.topaz.model.StatsEmotionFrequency;
import com.selekode.topaz.model.StatsEntryCount;
import com.selekode.topaz.model.Table;
import com.selekode.topaz.repository.StatsRepository;
import com.selekode.topaz.utils.StatsHelper;
import com.selekode.topaz.model.PersonalRatings;
import com.selekode.topaz.model.StatsActivityPerDayOfWeek;

@Service
public class StatsService {
		public static StatsEntryCount getEntryCountAllTime() {
		// Calculate entryCounts for all time
		int journalEntryCount = StatsRepository.findJournalEntryCountAllTime();
		int revisionEntryCount = StatsRepository.findRevisionEntryCountAllTime();
		int innerWorkEntryCount = StatsRepository.findInnerWorkEntryCountAllTime();
		StatsEntryCount entryCount = StatsHelper.calculateEntryCount(journalEntryCount, revisionEntryCount,
				innerWorkEntryCount);
		System.out.println("TopazStatistics: Entry Count: " + entryCount.toString());

		return entryCount;
	}

	public static StatsEntryCount getEntryCountDateRange(StatsDateRange statsDateRange) {
		// Calculate entryCounts in a date range
		long dateStart = StatsHelper.convertDateStrToLong(statsDateRange.getStartDate());
		long dateEnd = StatsHelper.convertDateStrToLong(statsDateRange.getEndDate());
		int journalEntryCount = StatsRepository.findJournalEntryCountDateRange(dateStart, dateEnd);
		int revisionEntryCount = StatsRepository.findRevisionEntryCountDateRange(dateStart, dateEnd);
		int innerWorkEntryCount = StatsRepository.findInnerWorkEntryCountDateRange(dateStart, dateEnd);

		StatsEntryCount entryCount = StatsHelper.calculateEntryCount(journalEntryCount, revisionEntryCount,
				innerWorkEntryCount);

		return entryCount;
	}

	public static StatsEntryCount getEntryCountWeek() {
		long dateStart = StatsHelper.calculateLastWeekDates().getDateStart();
		long dateEnd = StatsHelper.calculateLastWeekDates().getDateEnd();
		int journalEntryCount = StatsRepository.findJournalEntryCountDateRange(dateStart, dateEnd);
		int revisionEntryCount = StatsRepository.findRevisionEntryCountDateRange(dateStart, dateEnd);
		int innerWorkEntryCount = StatsRepository.findInnerWorkEntryCountDateRange(dateStart, dateEnd);

		StatsEntryCount entryCount = StatsHelper.calculateEntryCount(journalEntryCount, revisionEntryCount,
				innerWorkEntryCount);

		return entryCount;
	}

	public static StatsEntryCount getEntryCountMonth() {
		long dateStart = StatsHelper.calculateLastMonthDates().getDateStart();
		long dateEnd = StatsHelper.calculateLastMonthDates().getDateEnd();
		int journalEntryCount = StatsRepository.findJournalEntryCountDateRange(dateStart, dateEnd);
		int revisionEntryCount = StatsRepository.findRevisionEntryCountDateRange(dateStart, dateEnd);
		int innerWorkEntryCount = StatsRepository.findInnerWorkEntryCountDateRange(dateStart, dateEnd);

		StatsEntryCount entryCount = StatsHelper.calculateEntryCount(journalEntryCount, revisionEntryCount,
				innerWorkEntryCount);

		return entryCount;
	}

	public static List<StatsActivityPerDayOfWeek> getActivityPerDayOfWeekAllTime() {
		List<StatsActivityPerDayOfWeek> activitiesPerDayOfWeek = new ArrayList<>();
		;

		StatsActivityPerDayOfWeek activityPerDayOfWeekJournal = StatsRepository
				.findEntryCountPerDayAllTime(Table.JOURNAL);
		StatsActivityPerDayOfWeek activityPerDayOfWeekRevision = StatsRepository
				.findEntryCountPerDayAllTime(Table.REVISION);
		StatsActivityPerDayOfWeek activityPerDayOfWeekInnerWork = StatsRepository
				.findEntryCountPerDayAllTime(Table.INNER_WORK_ENTRY);

		activityPerDayOfWeekJournal = StatsHelper.calculateActivityPerDayOfWeek(activityPerDayOfWeekJournal);
		activityPerDayOfWeekRevision = StatsHelper.calculateActivityPerDayOfWeek(activityPerDayOfWeekRevision);
		activityPerDayOfWeekInnerWork = StatsHelper.calculateActivityPerDayOfWeek(activityPerDayOfWeekInnerWork);

		activitiesPerDayOfWeek.add(activityPerDayOfWeekJournal);
		activitiesPerDayOfWeek.add(activityPerDayOfWeekRevision);
		activitiesPerDayOfWeek.add(activityPerDayOfWeekInnerWork);

		System.out.println(activitiesPerDayOfWeek);

		return activitiesPerDayOfWeek;
	}

	public static List<StatsActivityPerDayOfWeek> getActivityPerDayOfWeekDateRange(
			StatsDateRange statsDateRange) {
		long dateStart = StatsHelper.convertDateStrToLong(statsDateRange.getStartDate());
		long dateEnd = StatsHelper.convertDateStrToLong(statsDateRange.getEndDate());
		List<StatsActivityPerDayOfWeek> activitiesPerDayOfWeek = new ArrayList<>();

		StatsActivityPerDayOfWeek activityPerDayOfWeekJournal = StatsRepository
				.findEntryCountPerDayGenericDateRange(dateStart, dateEnd, Table.JOURNAL);
		StatsActivityPerDayOfWeek activityPerDayOfWeekRevision = StatsRepository
				.findEntryCountPerDayGenericDateRange(dateStart, dateEnd, Table.REVISION);
		StatsActivityPerDayOfWeek activityPerDayOfWeekInnerWork = StatsRepository
				.findEntryCountPerDayGenericDateRange(dateStart, dateEnd, Table.INNER_WORK_ENTRY);

		activityPerDayOfWeekJournal = StatsHelper.calculateActivityPerDayOfWeek(activityPerDayOfWeekJournal);
		activityPerDayOfWeekRevision = StatsHelper.calculateActivityPerDayOfWeek(activityPerDayOfWeekRevision);
		activityPerDayOfWeekInnerWork = StatsHelper.calculateActivityPerDayOfWeek(activityPerDayOfWeekInnerWork);

		activitiesPerDayOfWeek.add(activityPerDayOfWeekJournal);
		activitiesPerDayOfWeek.add(activityPerDayOfWeekRevision);
		activitiesPerDayOfWeek.add(activityPerDayOfWeekInnerWork);
		
		System.out.println(activitiesPerDayOfWeek);

		return activitiesPerDayOfWeek;
	}

	public static List<StatsActivityPerDayOfWeek> getActivityPerDayOfWeekWeek() {
		long dateStart = StatsHelper.calculateLastWeekDates().getDateStart();
		long dateEnd = StatsHelper.calculateLastWeekDates().getDateEnd();
		List<StatsActivityPerDayOfWeek> activitiesPerDayOfWeek = new ArrayList<>();

		StatsActivityPerDayOfWeek activityPerDayOfWeekJournal = StatsRepository
				.findEntryCountPerDayGenericDateRange(dateStart, dateEnd, Table.JOURNAL);
		StatsActivityPerDayOfWeek activityPerDayOfWeekRevision = StatsRepository
				.findEntryCountPerDayGenericDateRange(dateStart, dateEnd, Table.REVISION);
		StatsActivityPerDayOfWeek activityPerDayOfWeekInnerWork = StatsRepository
				.findEntryCountPerDayGenericDateRange(dateStart, dateEnd, Table.INNER_WORK_ENTRY);

		activityPerDayOfWeekJournal = StatsHelper.calculateActivityPerDayOfWeek(activityPerDayOfWeekJournal);
		activityPerDayOfWeekRevision = StatsHelper.calculateActivityPerDayOfWeek(activityPerDayOfWeekRevision);
		activityPerDayOfWeekInnerWork = StatsHelper.calculateActivityPerDayOfWeek(activityPerDayOfWeekInnerWork);
		
		activitiesPerDayOfWeek.add(activityPerDayOfWeekJournal);
		activitiesPerDayOfWeek.add(activityPerDayOfWeekRevision);
		activitiesPerDayOfWeek.add(activityPerDayOfWeekInnerWork);
		
		System.out.println(activitiesPerDayOfWeek);

		return activitiesPerDayOfWeek;
	}

	public static List<StatsActivityPerDayOfWeek> getActivityPerDayOfWeekMonth() {
		long dateStart = StatsHelper.calculateLastMonthDates().getDateStart();
		long dateEnd = StatsHelper.calculateLastMonthDates().getDateEnd();
		List<StatsActivityPerDayOfWeek> activitiesPerDayOfWeek = new ArrayList<>();

		StatsActivityPerDayOfWeek activityPerDayOfWeekJournal = StatsRepository
				.findEntryCountPerDayGenericDateRange(dateStart, dateEnd, Table.JOURNAL);
		StatsActivityPerDayOfWeek activityPerDayOfWeekRevision = StatsRepository
				.findEntryCountPerDayGenericDateRange(dateStart, dateEnd, Table.REVISION);
		StatsActivityPerDayOfWeek activityPerDayOfWeekInnerWork = StatsRepository
				.findEntryCountPerDayGenericDateRange(dateStart, dateEnd, Table.INNER_WORK_ENTRY);

		activityPerDayOfWeekJournal = StatsHelper.calculateActivityPerDayOfWeek(activityPerDayOfWeekJournal);
		activityPerDayOfWeekRevision = StatsHelper.calculateActivityPerDayOfWeek(activityPerDayOfWeekRevision);
		activityPerDayOfWeekInnerWork = StatsHelper.calculateActivityPerDayOfWeek(activityPerDayOfWeekInnerWork);

		activitiesPerDayOfWeek.add(activityPerDayOfWeekJournal);
		activitiesPerDayOfWeek.add(activityPerDayOfWeekRevision);
		activitiesPerDayOfWeek.add(activityPerDayOfWeekInnerWork);
		
		System.out.println(activitiesPerDayOfWeek);

		return activitiesPerDayOfWeek;
	}

	public static StatsEmotionFrequency getEmotionFrequencyAllTime() {
		StatsEmotionFrequency emotionFrequency = StatsRepository.findEmotionCountAllTime();
		emotionFrequency = StatsHelper.calculateEmotionFrequency(emotionFrequency);
		return emotionFrequency;
	}

	public static StatsEmotionFrequency getEmotionFrequencyDateRange(StatsDateRange statsDateRange) {
		long dateStart = StatsHelper.convertDateStrToLong(statsDateRange.getStartDate());
		long dateEnd = StatsHelper.convertDateStrToLong(statsDateRange.getEndDate());
		StatsEmotionFrequency emotionFrequency = StatsRepository.findEmotionCountDateRange(dateStart, dateEnd);
		emotionFrequency = StatsHelper.calculateEmotionFrequency(emotionFrequency);
		return emotionFrequency;
	}

	public static StatsEmotionFrequency getEmotionFrequencyWeek() {
		long dateStart = StatsHelper.calculateLastWeekDates().getDateStart();
		long dateEnd = StatsHelper.calculateLastWeekDates().getDateEnd();
		StatsEmotionFrequency emotionFrequency = StatsRepository.findEmotionCountDateRange(dateStart, dateEnd);
		emotionFrequency = StatsHelper.calculateEmotionFrequency(emotionFrequency);

		return emotionFrequency;
	}

	public static StatsEmotionFrequency getEmotionFrequencyMonth() {
		long dateStart = StatsHelper.calculateLastMonthDates().getDateStart();
		long dateEnd = StatsHelper.calculateLastMonthDates().getDateEnd();
		StatsEmotionFrequency emotionFrequency = StatsRepository.findEmotionCountDateRange(dateStart, dateEnd);
		emotionFrequency = StatsHelper.calculateEmotionFrequency(emotionFrequency);

		return emotionFrequency;
	}

	public static PersonalRatings getRatingsAverageAllTime() {
		List<PersonalRatings> personalRatings = null;
		personalRatings = StatsRepository.findPersonalRatingsAllTime();
		PersonalRatings ratingsAverage = StatsHelper.calculateRatingsAverage(personalRatings);
		return ratingsAverage;
	}

	public static PersonalRatings getRatingsAverageDateRange(StatsDateRange statsDateRange) {
		long dateStart = StatsHelper.convertDateStrToLong(statsDateRange.getStartDate());
		long dateEnd = StatsHelper.convertDateStrToLong(statsDateRange.getEndDate());
		List<PersonalRatings> personalRatings = null;
		personalRatings = StatsRepository.findPersonalRatingsDateRange(dateStart, dateEnd);
		PersonalRatings ratingsAverage = StatsHelper.calculateRatingsAverage(personalRatings);
		return ratingsAverage;
	}

	public static PersonalRatings getRatingsAverageWeek() {
		long dateStart = StatsHelper.calculateLastWeekDates().getDateStart();
		long dateEnd = StatsHelper.calculateLastWeekDates().getDateEnd();
		List<PersonalRatings> personalRatings = null;
		personalRatings = StatsRepository.findPersonalRatingsDateRange(dateStart, dateEnd);
		PersonalRatings ratingsAverage = StatsHelper.calculateRatingsAverage(personalRatings);

		return ratingsAverage;
	}

	public static PersonalRatings getRatingsAverageMonth() {
		long dateStart = StatsHelper.calculateLastMonthDates().getDateStart();
		long dateEnd = StatsHelper.calculateLastMonthDates().getDateEnd();
		List<PersonalRatings> personalRatings = null;
		personalRatings = StatsRepository.findPersonalRatingsDateRange(dateStart, dateEnd);
		PersonalRatings ratingsAverage = StatsHelper.calculateRatingsAverage(personalRatings);
		return ratingsAverage;
	}

	public static String getRatingsTrendAllTime() {
		Map<String, PersonalRatings> personalRatingsDated = StatsRepository.findRatingsDatedAllTime();
		String jsonRatings = StatsHelper.convertObjectToJSON(personalRatingsDated);
		System.out.println("JSON: " + jsonRatings);

		return jsonRatings;
	}

	public static String getRatingsTrendDateRange(StatsDateRange statsDateRange) {
		long dateStart = StatsHelper.convertDateStrToLong(statsDateRange.getStartDate());
		long dateEnd = StatsHelper.convertDateStrToLong(statsDateRange.getEndDate());

		Map<String, PersonalRatings> personalRatingsDated = StatsRepository.findRatingsDatedDateRange(dateStart,
				dateEnd);
		String jsonRatings = StatsHelper.convertObjectToJSON(personalRatingsDated);
		System.out.println("JSON: " + jsonRatings);

		return jsonRatings;
	}

	public static String getRatingsTrendWeek() {
		long dateStart = StatsHelper.calculateLastWeekDates().getDateStart();
		long dateEnd = StatsHelper.calculateLastWeekDates().getDateEnd();
		Map<String, PersonalRatings> personalRatingsDated = StatsRepository.findRatingsDatedDateRange(dateStart,
				dateEnd);
		String jsonRatings = StatsHelper.convertObjectToJSON(personalRatingsDated);
		System.out.println("JSON: " + jsonRatings);

		return jsonRatings;
	}

	public static String getRatingsTrendMonth() {
		long dateStart = StatsHelper.calculateLastMonthDates().getDateStart();
		long dateEnd = StatsHelper.calculateLastMonthDates().getDateEnd();
		Map<String, PersonalRatings> personalRatingsDated = StatsRepository.findRatingsDatedDateRange(dateStart,
				dateEnd);
		String jsonRatings = StatsHelper.convertObjectToJSON(personalRatingsDated);
		System.out.println("JSON: " + jsonRatings);

		return jsonRatings;
	}
}
