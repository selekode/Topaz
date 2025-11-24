package com.selekode.topaz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.selekode.topaz.model.DateRange;
import com.selekode.topaz.model.EmotionFrequencyDTO;
import com.selekode.topaz.model.EntryCountDTO;
import com.selekode.topaz.model.Table;
import com.selekode.topaz.repository.StatsRepository;
import com.selekode.topaz.utils.DatesUtils;
import com.selekode.topaz.utils.StatsUtils;
import com.selekode.topaz.model.PersonalRatings;
import com.selekode.topaz.model.ActivityPerDayOfWeekDTO;

@Service
public class StatsService {
		public static EntryCountDTO getEntryCountAllTime() {
		// Calculate entryCounts for all time
		int journalEntryCount = StatsRepository.findJournalEntryCountAllTime();
		int revisionEntryCount = StatsRepository.findRevisionEntryCountAllTime();
		int innerWorkEntryCount = StatsRepository.findInnerWorkEntryCountAllTime();
		EntryCountDTO entryCount = StatsUtils.calculateEntryCount(journalEntryCount, revisionEntryCount,
				innerWorkEntryCount);
		System.out.println("TopazStatistics: Entry Count: " + entryCount.toString());

		return entryCount;
	}

	public static EntryCountDTO getEntryCountDateRange(DateRange statsDateRange) {
		// Calculate entryCounts in a date range
		long dateStart = StatsUtils.convertDateStrToLong(statsDateRange.getStartDate());
		long dateEnd = StatsUtils.convertDateStrToLong(statsDateRange.getEndDate());
		int journalEntryCount = StatsRepository.findJournalEntryCountDateRange(dateStart, dateEnd);
		int revisionEntryCount = StatsRepository.findRevisionEntryCountDateRange(dateStart, dateEnd);
		int innerWorkEntryCount = StatsRepository.findInnerWorkEntryCountDateRange(dateStart, dateEnd);

		EntryCountDTO entryCount = StatsUtils.calculateEntryCount(journalEntryCount, revisionEntryCount,
				innerWorkEntryCount);

		return entryCount;
	}

	public static EntryCountDTO getEntryCountWeek() {
		long dateStart = DatesUtils.calculateLastWeekDates().getStartDate();
		long dateEnd = DatesUtils.calculateLastWeekDates().getEndDate();
		int journalEntryCount = StatsRepository.findJournalEntryCountDateRange(dateStart, dateEnd);
		int revisionEntryCount = StatsRepository.findRevisionEntryCountDateRange(dateStart, dateEnd);
		int innerWorkEntryCount = StatsRepository.findInnerWorkEntryCountDateRange(dateStart, dateEnd);

		EntryCountDTO entryCount = StatsUtils.calculateEntryCount(journalEntryCount, revisionEntryCount,
				innerWorkEntryCount);

		return entryCount;
	}

	public static EntryCountDTO getEntryCountMonth() {
		long dateStart = DatesUtils.calculateLastMonthDates().getStartDate();
		long dateEnd = DatesUtils.calculateLastMonthDates().getEndDate();
		int journalEntryCount = StatsRepository.findJournalEntryCountDateRange(dateStart, dateEnd);
		int revisionEntryCount = StatsRepository.findRevisionEntryCountDateRange(dateStart, dateEnd);
		int innerWorkEntryCount = StatsRepository.findInnerWorkEntryCountDateRange(dateStart, dateEnd);

		EntryCountDTO entryCount = StatsUtils.calculateEntryCount(journalEntryCount, revisionEntryCount,
				innerWorkEntryCount);

		return entryCount;
	}

	public static List<ActivityPerDayOfWeekDTO> getActivityPerDayOfWeekAllTime() {
		List<ActivityPerDayOfWeekDTO> activitiesPerDayOfWeek = new ArrayList<>();
		;

		ActivityPerDayOfWeekDTO activityPerDayOfWeekJournal = StatsRepository
				.findEntryCountPerDayAllTime(Table.JOURNAL);
		ActivityPerDayOfWeekDTO activityPerDayOfWeekRevision = StatsRepository
				.findEntryCountPerDayAllTime(Table.REVISION);
		ActivityPerDayOfWeekDTO activityPerDayOfWeekInnerWork = StatsRepository
				.findEntryCountPerDayAllTime(Table.INNER_WORK_ENTRY);

		activityPerDayOfWeekJournal = StatsUtils.calculateActivityPerDayOfWeek(activityPerDayOfWeekJournal);
		activityPerDayOfWeekJournal.setMostActiveDayN(StatsUtils.calculateDayWithHighestCountN(activityPerDayOfWeekJournal));
		
		activityPerDayOfWeekRevision = StatsUtils.calculateActivityPerDayOfWeek(activityPerDayOfWeekRevision);
		activityPerDayOfWeekRevision.setMostActiveDayN(StatsUtils.calculateDayWithHighestCountN(activityPerDayOfWeekRevision));

		activityPerDayOfWeekInnerWork = StatsUtils.calculateActivityPerDayOfWeek(activityPerDayOfWeekInnerWork);
		activityPerDayOfWeekInnerWork.setMostActiveDayN(StatsUtils.calculateDayWithHighestCountN(activityPerDayOfWeekInnerWork));
		
		System.out.println("Journal acitivty per day of week highest N count: " + activityPerDayOfWeekJournal.getMostActiveDayN());

		activitiesPerDayOfWeek.add(activityPerDayOfWeekJournal);
		activitiesPerDayOfWeek.add(activityPerDayOfWeekRevision);
		activitiesPerDayOfWeek.add(activityPerDayOfWeekInnerWork);

		return activitiesPerDayOfWeek;
	}

	public static List<ActivityPerDayOfWeekDTO> getActivityPerDayOfWeekDateRange(
			DateRange statsDateRange) {
		long dateStart = StatsUtils.convertDateStrToLong(statsDateRange.getStartDate());
		long dateEnd = StatsUtils.convertDateStrToLong(statsDateRange.getEndDate());
		List<ActivityPerDayOfWeekDTO> activitiesPerDayOfWeek = new ArrayList<>();

		ActivityPerDayOfWeekDTO activityPerDayOfWeekJournal = StatsRepository
				.findEntryCountPerDayGenericDateRange(dateStart, dateEnd, Table.JOURNAL);
		ActivityPerDayOfWeekDTO activityPerDayOfWeekRevision = StatsRepository
				.findEntryCountPerDayGenericDateRange(dateStart, dateEnd, Table.REVISION);
		ActivityPerDayOfWeekDTO activityPerDayOfWeekInnerWork = StatsRepository
				.findEntryCountPerDayGenericDateRange(dateStart, dateEnd, Table.INNER_WORK_ENTRY);

		activityPerDayOfWeekJournal = StatsUtils.calculateActivityPerDayOfWeek(activityPerDayOfWeekJournal);
		activityPerDayOfWeekJournal.setMostActiveDayN(StatsUtils.calculateDayWithHighestCountN(activityPerDayOfWeekJournal));
		
		activityPerDayOfWeekRevision = StatsUtils.calculateActivityPerDayOfWeek(activityPerDayOfWeekRevision);
		activityPerDayOfWeekRevision.setMostActiveDayN(StatsUtils.calculateDayWithHighestCountN(activityPerDayOfWeekRevision));

		activityPerDayOfWeekInnerWork = StatsUtils.calculateActivityPerDayOfWeek(activityPerDayOfWeekInnerWork);
		activityPerDayOfWeekInnerWork.setMostActiveDayN(StatsUtils.calculateDayWithHighestCountN(activityPerDayOfWeekInnerWork));

		activitiesPerDayOfWeek.add(activityPerDayOfWeekJournal);
		activitiesPerDayOfWeek.add(activityPerDayOfWeekRevision);
		activitiesPerDayOfWeek.add(activityPerDayOfWeekInnerWork);
		
		return activitiesPerDayOfWeek;
	}

	public static List<ActivityPerDayOfWeekDTO> getActivityPerDayOfWeekWeek() {
		long dateStart = DatesUtils.calculateLastWeekDates().getStartDate();
		long dateEnd = DatesUtils.calculateLastWeekDates().getEndDate();
		List<ActivityPerDayOfWeekDTO> activitiesPerDayOfWeek = new ArrayList<>();

		ActivityPerDayOfWeekDTO activityPerDayOfWeekJournal = StatsRepository
				.findEntryCountPerDayGenericDateRange(dateStart, dateEnd, Table.JOURNAL);
		ActivityPerDayOfWeekDTO activityPerDayOfWeekRevision = StatsRepository
				.findEntryCountPerDayGenericDateRange(dateStart, dateEnd, Table.REVISION);
		ActivityPerDayOfWeekDTO activityPerDayOfWeekInnerWork = StatsRepository
				.findEntryCountPerDayGenericDateRange(dateStart, dateEnd, Table.INNER_WORK_ENTRY);

		activityPerDayOfWeekJournal = StatsUtils.calculateActivityPerDayOfWeek(activityPerDayOfWeekJournal);
		activityPerDayOfWeekJournal.setMostActiveDayN(StatsUtils.calculateDayWithHighestCountN(activityPerDayOfWeekJournal));
		
		activityPerDayOfWeekRevision = StatsUtils.calculateActivityPerDayOfWeek(activityPerDayOfWeekRevision);
		activityPerDayOfWeekRevision.setMostActiveDayN(StatsUtils.calculateDayWithHighestCountN(activityPerDayOfWeekRevision));

		activityPerDayOfWeekInnerWork = StatsUtils.calculateActivityPerDayOfWeek(activityPerDayOfWeekInnerWork);
		activityPerDayOfWeekInnerWork.setMostActiveDayN(StatsUtils.calculateDayWithHighestCountN(activityPerDayOfWeekInnerWork));
		
		activitiesPerDayOfWeek.add(activityPerDayOfWeekJournal);
		activitiesPerDayOfWeek.add(activityPerDayOfWeekRevision);
		activitiesPerDayOfWeek.add(activityPerDayOfWeekInnerWork);
		
		return activitiesPerDayOfWeek;
	}

	public static List<ActivityPerDayOfWeekDTO> getActivityPerDayOfWeekMonth() {
		long dateStart = DatesUtils.calculateLastMonthDates().getStartDate();
		long dateEnd = DatesUtils.calculateLastMonthDates().getEndDate();
		List<ActivityPerDayOfWeekDTO> activitiesPerDayOfWeek = new ArrayList<>();

		ActivityPerDayOfWeekDTO activityPerDayOfWeekJournal = StatsRepository
				.findEntryCountPerDayGenericDateRange(dateStart, dateEnd, Table.JOURNAL);
		ActivityPerDayOfWeekDTO activityPerDayOfWeekRevision = StatsRepository
				.findEntryCountPerDayGenericDateRange(dateStart, dateEnd, Table.REVISION);
		ActivityPerDayOfWeekDTO activityPerDayOfWeekInnerWork = StatsRepository
				.findEntryCountPerDayGenericDateRange(dateStart, dateEnd, Table.INNER_WORK_ENTRY);

		activityPerDayOfWeekJournal = StatsUtils.calculateActivityPerDayOfWeek(activityPerDayOfWeekJournal);
		activityPerDayOfWeekJournal.setMostActiveDayN(StatsUtils.calculateDayWithHighestCountN(activityPerDayOfWeekJournal));
		
		activityPerDayOfWeekRevision = StatsUtils.calculateActivityPerDayOfWeek(activityPerDayOfWeekRevision);
		activityPerDayOfWeekRevision.setMostActiveDayN(StatsUtils.calculateDayWithHighestCountN(activityPerDayOfWeekRevision));

		activityPerDayOfWeekInnerWork = StatsUtils.calculateActivityPerDayOfWeek(activityPerDayOfWeekInnerWork);
		activityPerDayOfWeekInnerWork.setMostActiveDayN(StatsUtils.calculateDayWithHighestCountN(activityPerDayOfWeekInnerWork));

		activitiesPerDayOfWeek.add(activityPerDayOfWeekJournal);
		activitiesPerDayOfWeek.add(activityPerDayOfWeekRevision);
		activitiesPerDayOfWeek.add(activityPerDayOfWeekInnerWork);
		
		System.out.println("Most active day per day of week month: " + activityPerDayOfWeekJournal);
		
		return activitiesPerDayOfWeek;
	}

	public static EmotionFrequencyDTO getEmotionFrequencyAllTime() {
		EmotionFrequencyDTO emotionFrequency = StatsRepository.findEmotionCountAllTime();
		emotionFrequency = StatsUtils.calculateEmotionFrequency(emotionFrequency);
		return emotionFrequency;
	}

	public static EmotionFrequencyDTO getEmotionFrequencyDateRange(DateRange statsDateRange) {
		long dateStart = StatsUtils.convertDateStrToLong(statsDateRange.getStartDate());
		long dateEnd = StatsUtils.convertDateStrToLong(statsDateRange.getEndDate());
		EmotionFrequencyDTO emotionFrequency = StatsRepository.findEmotionCountDateRange(dateStart, dateEnd);
		emotionFrequency = StatsUtils.calculateEmotionFrequency(emotionFrequency);
		return emotionFrequency;
	}

	public static EmotionFrequencyDTO getEmotionFrequencyWeek() {
		long dateStart = DatesUtils.calculateLastWeekDates().getStartDate();
		long dateEnd = DatesUtils.calculateLastWeekDates().getEndDate();
		EmotionFrequencyDTO emotionFrequency = StatsRepository.findEmotionCountDateRange(dateStart, dateEnd);
		emotionFrequency = StatsUtils.calculateEmotionFrequency(emotionFrequency);

		return emotionFrequency;
	}

	public static EmotionFrequencyDTO getEmotionFrequencyMonth() {
		long dateStart = DatesUtils.calculateLastMonthDates().getStartDate();
		long dateEnd = DatesUtils.calculateLastMonthDates().getEndDate();
		EmotionFrequencyDTO emotionFrequency = StatsRepository.findEmotionCountDateRange(dateStart, dateEnd);
		emotionFrequency = StatsUtils.calculateEmotionFrequency(emotionFrequency);

		return emotionFrequency;
	}

	public static PersonalRatings getRatingsAverageAllTime() {
		List<PersonalRatings> personalRatings = null;
		personalRatings = StatsRepository.findPersonalRatingsAllTime();
		PersonalRatings ratingsAverage = StatsUtils.calculateRatingsAverage(personalRatings);
		return ratingsAverage;
	}

	public static PersonalRatings getRatingsAverageDateRange(DateRange statsDateRange) {
		long dateStart = StatsUtils.convertDateStrToLong(statsDateRange.getStartDate());
		long dateEnd = StatsUtils.convertDateStrToLong(statsDateRange.getEndDate());
		List<PersonalRatings> personalRatings = null;
		personalRatings = StatsRepository.findPersonalRatingsDateRange(dateStart, dateEnd);
		PersonalRatings ratingsAverage = StatsUtils.calculateRatingsAverage(personalRatings);
		return ratingsAverage;
	}

	public static PersonalRatings getRatingsAverageWeek() {
		long dateStart = DatesUtils.calculateLastWeekDates().getStartDate();
		long dateEnd = DatesUtils.calculateLastWeekDates().getEndDate();
		List<PersonalRatings> personalRatings = null;
		personalRatings = StatsRepository.findPersonalRatingsDateRange(dateStart, dateEnd);
		PersonalRatings ratingsAverage = StatsUtils.calculateRatingsAverage(personalRatings);

		return ratingsAverage;
	}

	public static PersonalRatings getRatingsAverageMonth() {
		long dateStart = DatesUtils.calculateLastMonthDates().getStartDate();
		long dateEnd = DatesUtils.calculateLastMonthDates().getEndDate();
		List<PersonalRatings> personalRatings = null;
		personalRatings = StatsRepository.findPersonalRatingsDateRange(dateStart, dateEnd);
		PersonalRatings ratingsAverage = StatsUtils.calculateRatingsAverage(personalRatings);
		return ratingsAverage;
	}

	public static String getRatingsTrendAllTime() {
		Map<String, PersonalRatings> personalRatingsDated = StatsRepository.findRatingsDatedAllTime();
		String jsonRatings = StatsUtils.convertObjectToJSON(personalRatingsDated);
		System.out.println("JSON: " + jsonRatings);

		return jsonRatings;
	}

	public static String getRatingsTrendDateRange(DateRange statsDateRange) {
		long dateStart = StatsUtils.convertDateStrToLong(statsDateRange.getStartDate());
		long dateEnd = StatsUtils.convertDateStrToLong(statsDateRange.getEndDate());

		Map<String, PersonalRatings> personalRatingsDated = StatsRepository.findRatingsDatedDateRange(dateStart,
				dateEnd);
		String jsonRatings = StatsUtils.convertObjectToJSON(personalRatingsDated);
		System.out.println("JSON: " + jsonRatings);

		return jsonRatings;
	}

	public static String getRatingsTrendWeek() {
		long dateStart = DatesUtils.calculateLastWeekDates().getStartDate();
		long dateEnd = DatesUtils.calculateLastWeekDates().getEndDate();
		Map<String, PersonalRatings> personalRatingsDated = StatsRepository.findRatingsDatedDateRange(dateStart,
				dateEnd);
		String jsonRatings = StatsUtils.convertObjectToJSON(personalRatingsDated);
		System.out.println("JSON: " + jsonRatings);

		return jsonRatings;
	}

	public static String getRatingsTrendMonth() {
		long dateStart = DatesUtils.calculateLastMonthDates().getStartDate();
		long dateEnd = DatesUtils.calculateLastMonthDates().getEndDate();
		Map<String, PersonalRatings> personalRatingsDated = StatsRepository.findRatingsDatedDateRange(dateStart,
				dateEnd);
		String jsonRatings = StatsUtils.convertObjectToJSON(personalRatingsDated);
		System.out.println("JSON: " + jsonRatings);

		return jsonRatings;
	}
}
