package com.selekode.topaz.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.selekode.topaz.model.StatsDateRange;
import com.selekode.topaz.model.StatsEmotionFrequency;
import com.selekode.topaz.model.StatsEntryCount;
import com.selekode.topaz.repository.StatsRepository;
import com.selekode.topaz.utils.StatsHelper;
import com.selekode.topaz.model.PersonalRatings;
import com.selekode.topaz.model.StatsActivityPerDayOfWeek;

@Service
public class StatsService {
	public static StatsDateRange getDateRangeLastWeek() {
		long dateStart = StatsHelper.calculateLastWeekDates().getDateStart();
		long dateEnd = StatsHelper.calculateLastWeekDates().getDateEnd();

		String dateStartStr = StatsHelper.convertDateLongToStr(dateStart);
		String dateEndStr = StatsHelper.convertDateLongToStr(dateEnd);
		StatsDateRange dateRangeLastWeek = new StatsDateRange(dateStartStr, dateEndStr);

		return dateRangeLastWeek;
	}

	public static StatsDateRange getDateRangeLastMonth() {
		long dateStart = StatsHelper.calculateLastMonthDates().getDateStart();
		long dateEnd = StatsHelper.calculateLastMonthDates().getDateEnd();

		String dateStartStr = StatsHelper.convertDateLongToStr(dateStart);
		String dateEndStr = StatsHelper.convertDateLongToStr(dateEnd);
		StatsDateRange dateRangeLastMonth = new StatsDateRange(dateStartStr, dateEndStr);

		return dateRangeLastMonth;
	}

	public static StatsEntryCount getEntryCountAllTime() {
		// Calculate entryCounts for all time
		int journalEntryCount = StatsRepository.findJournalEntryCountAllTime();
		int revisionEntryCount = StatsRepository.findRevisionEntryCountAllTime();
		StatsEntryCount entryCount = StatsHelper.calculateEntryCount(journalEntryCount, revisionEntryCount);

		return entryCount;
	}

	public static StatsEntryCount getEntryCountDateRange(StatsDateRange statsDateRange) {
		// Calculate entryCounts in a date range
		long dateStart = StatsHelper.convertDateStrToLong(statsDateRange.getStartDate());
		long dateEnd = StatsHelper.convertDateStrToLong(statsDateRange.getEndDate());
		int journalEntryCount = StatsRepository.findJournalEntryCountDateRange(dateStart, dateEnd);
		int revisionEntryCount = StatsRepository.findRevisionEntryCountDateRange(dateStart, dateEnd);
		StatsEntryCount entryCount = StatsHelper.calculateEntryCount(journalEntryCount, revisionEntryCount);

		return entryCount;
	}

	public static StatsEntryCount getEntryCountWeek() {
		long dateStart = StatsHelper.calculateLastWeekDates().getDateStart();
		long dateEnd = StatsHelper.calculateLastWeekDates().getDateEnd();
		int journalEntryCount = StatsRepository.findJournalEntryCountDateRange(dateStart, dateEnd);
		int revisionEntryCount = StatsRepository.findRevisionEntryCountDateRange(dateStart, dateEnd);
		StatsEntryCount entryCount = StatsHelper.calculateEntryCount(journalEntryCount, revisionEntryCount);

		return entryCount;
	}

	public static StatsEntryCount getEntryCountMonth() {
		long dateStart = StatsHelper.calculateLastMonthDates().getDateStart();
		long dateEnd = StatsHelper.calculateLastMonthDates().getDateEnd();
		int journalEntryCount = StatsRepository.findJournalEntryCountDateRange(dateStart, dateEnd);
		int revisionEntryCount = StatsRepository.findRevisionEntryCountDateRange(dateStart, dateEnd);
		StatsEntryCount entryCount = StatsHelper.calculateEntryCount(journalEntryCount, revisionEntryCount);

		return entryCount;
	}

	public static StatsActivityPerDayOfWeek getActivityPerDayOfWeekAllTime() {
		// Retreives row count from DB, adds them to activityPerDayOfWeek, leaving two
		// fields empty (journalMostActiveDayN & revisionMostActiveDayN), which we will
		// calculate and fill here in the helper layer.
		StatsActivityPerDayOfWeek activityPerDayOfWeek = StatsRepository.findEntryCountPerDayAllTime();
		activityPerDayOfWeek = StatsHelper.calculateActivityPerDayOfWeek(activityPerDayOfWeek);

		return activityPerDayOfWeek;
	}

	public static StatsActivityPerDayOfWeek getActivityPerDayOfWeekDateRange(StatsDateRange statsDateRange) {
		long dateStart = StatsHelper.convertDateStrToLong(statsDateRange.getStartDate());
		long dateEnd = StatsHelper.convertDateStrToLong(statsDateRange.getEndDate());
		StatsActivityPerDayOfWeek activityPerDayOfWeek = StatsRepository.findEntryCountPerDayDateRange(dateStart,
				dateEnd);
		activityPerDayOfWeek = StatsHelper.calculateActivityPerDayOfWeek(activityPerDayOfWeek);

		return activityPerDayOfWeek;
	}

	public static StatsActivityPerDayOfWeek getActivityPerDayOfWeekWeek() {
		long dateStart = StatsHelper.calculateLastWeekDates().getDateStart();
		long dateEnd = StatsHelper.calculateLastWeekDates().getDateEnd();
		StatsActivityPerDayOfWeek activityPerDayOfWeek = StatsRepository.findEntryCountPerDayDateRange(dateStart,
				dateEnd);
		activityPerDayOfWeek = StatsHelper.calculateActivityPerDayOfWeek(activityPerDayOfWeek);

		return activityPerDayOfWeek;
	}

	public static StatsActivityPerDayOfWeek getActivityPerDayOfWeekMonth() {
		long dateStart = StatsHelper.calculateLastMonthDates().getDateStart();
		long dateEnd = StatsHelper.calculateLastMonthDates().getDateEnd();
		StatsActivityPerDayOfWeek activityPerDayOfWeek = StatsRepository.findEntryCountPerDayDateRange(dateStart,
				dateEnd);
		activityPerDayOfWeek = StatsHelper.calculateActivityPerDayOfWeek(activityPerDayOfWeek);
		return activityPerDayOfWeek;
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
