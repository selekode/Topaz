package com.selekode.topaz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.selekode.topaz.repository.StatsRepository;
import org.springframework.stereotype.Service;

import com.selekode.topaz.model.DateRange;
import com.selekode.topaz.model.EmotionFrequencyDTO;
import com.selekode.topaz.model.EntryCountDTO;
import com.selekode.topaz.model.Table;
import com.selekode.topaz.utils.DatesUtils;
import com.selekode.topaz.utils.StatsUtils;
import com.selekode.topaz.model.PersonalRatings;
import com.selekode.topaz.model.ActivityPerDayOfWeekDTO;

@Service
public class StatsService {
		private final StatsRepository statsRepository;

		public StatsService(StatsRepository statsRepository){
			this.statsRepository = statsRepository;
		}
	
		public EntryCountDTO getEntryCountAllTime() {
		// Calculate entryCounts for all time
		int journalEntryCount = statsRepository.findJournalEntryCountAllTime();
		int revisionEntryCount = statsRepository.findRevisionEntryCountAllTime();
		int innerWorkEntryCount = statsRepository.findInnerWorkEntryCountAllTime();
		EntryCountDTO entryCount = StatsUtils.calculateEntryCount(journalEntryCount, revisionEntryCount,
				innerWorkEntryCount);
		System.out.println("TopazStatistics: Entry Count: " + entryCount.toString());

		return entryCount;
	}

	public EntryCountDTO getEntryCountDateRange(DateRange statsDateRange) {
		// Calculate entryCounts in a date range
		long dateStart = StatsUtils.convertDateStrToLong(statsDateRange.getStartDate());
		long dateEnd = StatsUtils.convertDateStrToLong(statsDateRange.getEndDate());
		int journalEntryCount = statsRepository.findJournalEntryCountDateRange(dateStart, dateEnd);
		int revisionEntryCount = statsRepository.findRevisionEntryCountDateRange(dateStart, dateEnd);
		int innerWorkEntryCount = statsRepository.findInnerWorkEntryCountDateRange(dateStart, dateEnd);

		EntryCountDTO entryCount = StatsUtils.calculateEntryCount(journalEntryCount, revisionEntryCount,
				innerWorkEntryCount);

		return entryCount;
	}

	public EntryCountDTO getEntryCountWeek() {
		long dateStart = DatesUtils.calculateLastWeekDates().getStartDate();
		long dateEnd = DatesUtils.calculateLastWeekDates().getEndDate();
		int journalEntryCount = statsRepository.findJournalEntryCountDateRange(dateStart, dateEnd);
		int revisionEntryCount = statsRepository.findRevisionEntryCountDateRange(dateStart, dateEnd);
		int innerWorkEntryCount = statsRepository.findInnerWorkEntryCountDateRange(dateStart, dateEnd);

		EntryCountDTO entryCount = StatsUtils.calculateEntryCount(journalEntryCount, revisionEntryCount,
				innerWorkEntryCount);

		return entryCount;
	}

	public EntryCountDTO getEntryCountMonth() {
		long dateStart = DatesUtils.calculateLastMonthDates().getStartDate();
		long dateEnd = DatesUtils.calculateLastMonthDates().getEndDate();
		int journalEntryCount = statsRepository.findJournalEntryCountDateRange(dateStart, dateEnd);
		int revisionEntryCount = statsRepository.findRevisionEntryCountDateRange(dateStart, dateEnd);
		int innerWorkEntryCount = statsRepository.findInnerWorkEntryCountDateRange(dateStart, dateEnd);

		EntryCountDTO entryCount = StatsUtils.calculateEntryCount(journalEntryCount, revisionEntryCount,
				innerWorkEntryCount);

		return entryCount;
	}

	public List<ActivityPerDayOfWeekDTO> getActivityPerDayOfWeekAllTime() {
		List<ActivityPerDayOfWeekDTO> activitiesPerDayOfWeek = new ArrayList<>();
		;

		ActivityPerDayOfWeekDTO activityPerDayOfWeekJournal = statsRepository
				.findEntryCountPerDayAllTime(Table.JOURNAL);
		ActivityPerDayOfWeekDTO activityPerDayOfWeekRevision = statsRepository
				.findEntryCountPerDayAllTime(Table.REVISION);
		ActivityPerDayOfWeekDTO activityPerDayOfWeekInnerWork = statsRepository
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

	public List<ActivityPerDayOfWeekDTO> getActivityPerDayOfWeekDateRange(
			DateRange statsDateRange) {
		long dateStart = StatsUtils.convertDateStrToLong(statsDateRange.getStartDate());
		long dateEnd = StatsUtils.convertDateStrToLong(statsDateRange.getEndDate());
		List<ActivityPerDayOfWeekDTO> activitiesPerDayOfWeek = new ArrayList<>();

		ActivityPerDayOfWeekDTO activityPerDayOfWeekJournal = statsRepository
				.findEntryCountPerDayGenericDateRange(dateStart, dateEnd, Table.JOURNAL);
		ActivityPerDayOfWeekDTO activityPerDayOfWeekRevision = statsRepository
				.findEntryCountPerDayGenericDateRange(dateStart, dateEnd, Table.REVISION);
		ActivityPerDayOfWeekDTO activityPerDayOfWeekInnerWork = statsRepository
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

	public List<ActivityPerDayOfWeekDTO> getActivityPerDayOfWeekWeek() {
		long dateStart = DatesUtils.calculateLastWeekDates().getStartDate();
		long dateEnd = DatesUtils.calculateLastWeekDates().getEndDate();
		List<ActivityPerDayOfWeekDTO> activitiesPerDayOfWeek = new ArrayList<>();

		ActivityPerDayOfWeekDTO activityPerDayOfWeekJournal = statsRepository
				.findEntryCountPerDayGenericDateRange(dateStart, dateEnd, Table.JOURNAL);
		ActivityPerDayOfWeekDTO activityPerDayOfWeekRevision = statsRepository
				.findEntryCountPerDayGenericDateRange(dateStart, dateEnd, Table.REVISION);
		ActivityPerDayOfWeekDTO activityPerDayOfWeekInnerWork = statsRepository
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

	public List<ActivityPerDayOfWeekDTO> getActivityPerDayOfWeekMonth() {
		long dateStart = DatesUtils.calculateLastMonthDates().getStartDate();
		long dateEnd = DatesUtils.calculateLastMonthDates().getEndDate();
		List<ActivityPerDayOfWeekDTO> activitiesPerDayOfWeek = new ArrayList<>();

		ActivityPerDayOfWeekDTO activityPerDayOfWeekJournal = statsRepository
				.findEntryCountPerDayGenericDateRange(dateStart, dateEnd, Table.JOURNAL);
		ActivityPerDayOfWeekDTO activityPerDayOfWeekRevision = statsRepository
				.findEntryCountPerDayGenericDateRange(dateStart, dateEnd, Table.REVISION);
		ActivityPerDayOfWeekDTO activityPerDayOfWeekInnerWork = statsRepository
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

	public EmotionFrequencyDTO getEmotionFrequencyAllTime() {
		EmotionFrequencyDTO emotionFrequency = statsRepository.findEmotionCountAllTime();
		emotionFrequency = StatsUtils.calculateEmotionFrequency(emotionFrequency);
		return emotionFrequency;
	}

	public EmotionFrequencyDTO getEmotionFrequencyDateRange(DateRange statsDateRange) {
		long dateStart = StatsUtils.convertDateStrToLong(statsDateRange.getStartDate());
		long dateEnd = StatsUtils.convertDateStrToLong(statsDateRange.getEndDate());
		EmotionFrequencyDTO emotionFrequency = statsRepository.findEmotionCountDateRange(dateStart, dateEnd);
		emotionFrequency = StatsUtils.calculateEmotionFrequency(emotionFrequency);
		return emotionFrequency;
	}

	public EmotionFrequencyDTO getEmotionFrequencyWeek() {
		long dateStart = DatesUtils.calculateLastWeekDates().getStartDate();
		long dateEnd = DatesUtils.calculateLastWeekDates().getEndDate();
		EmotionFrequencyDTO emotionFrequency = statsRepository.findEmotionCountDateRange(dateStart, dateEnd);
		emotionFrequency = StatsUtils.calculateEmotionFrequency(emotionFrequency);

		return emotionFrequency;
	}

	public EmotionFrequencyDTO getEmotionFrequencyMonth() {
		long dateStart = DatesUtils.calculateLastMonthDates().getStartDate();
		long dateEnd = DatesUtils.calculateLastMonthDates().getEndDate();
		EmotionFrequencyDTO emotionFrequency = statsRepository.findEmotionCountDateRange(dateStart, dateEnd);
		emotionFrequency = StatsUtils.calculateEmotionFrequency(emotionFrequency);

		return emotionFrequency;
	}

	public PersonalRatings getRatingsAverageAllTime() {
		List<PersonalRatings> personalRatings = null;
		personalRatings = statsRepository.findPersonalRatingsAllTime();
		PersonalRatings ratingsAverage = StatsUtils.calculateRatingsAverage(personalRatings);
		return ratingsAverage;
	}

	public PersonalRatings getRatingsAverageDateRange(DateRange statsDateRange) {
		long dateStart = StatsUtils.convertDateStrToLong(statsDateRange.getStartDate());
		long dateEnd = StatsUtils.convertDateStrToLong(statsDateRange.getEndDate());
		List<PersonalRatings> personalRatings = null;
		personalRatings = statsRepository.findPersonalRatingsDateRange(dateStart, dateEnd);
		PersonalRatings ratingsAverage = StatsUtils.calculateRatingsAverage(personalRatings);
		return ratingsAverage;
	}

	public PersonalRatings getRatingsAverageWeek() {
		long dateStart = DatesUtils.calculateLastWeekDates().getStartDate();
		long dateEnd = DatesUtils.calculateLastWeekDates().getEndDate();
		List<PersonalRatings> personalRatings = null;
		personalRatings = statsRepository.findPersonalRatingsDateRange(dateStart, dateEnd);
		PersonalRatings ratingsAverage = StatsUtils.calculateRatingsAverage(personalRatings);

		return ratingsAverage;
	}

	public PersonalRatings getRatingsAverageMonth() {
		long dateStart = DatesUtils.calculateLastMonthDates().getStartDate();
		long dateEnd = DatesUtils.calculateLastMonthDates().getEndDate();
		List<PersonalRatings> personalRatings = null;
		personalRatings = statsRepository.findPersonalRatingsDateRange(dateStart, dateEnd);
		PersonalRatings ratingsAverage = StatsUtils.calculateRatingsAverage(personalRatings);
		return ratingsAverage;
	}

	public String getRatingsTrendAllTime() {
		Map<String, PersonalRatings> personalRatingsDated = statsRepository.findRatingsDatedAllTime();
		String jsonRatings = StatsUtils.convertObjectToJSON(personalRatingsDated);
		System.out.println("JSON: " + jsonRatings);

		return jsonRatings;
	}

	public String getRatingsTrendDateRange(DateRange statsDateRange) {
		long dateStart = StatsUtils.convertDateStrToLong(statsDateRange.getStartDate());
		long dateEnd = StatsUtils.convertDateStrToLong(statsDateRange.getEndDate());

		Map<String, PersonalRatings> personalRatingsDated = statsRepository.findRatingsDatedDateRange(dateStart,
				dateEnd);
		String jsonRatings = StatsUtils.convertObjectToJSON(personalRatingsDated);
		System.out.println("JSON: " + jsonRatings);

		return jsonRatings;
	}

	public String getRatingsTrendWeek() {
		long dateStart = DatesUtils.calculateLastWeekDates().getStartDate();
		long dateEnd = DatesUtils.calculateLastWeekDates().getEndDate();
		Map<String, PersonalRatings> personalRatingsDated = statsRepository.findRatingsDatedDateRange(dateStart,
				dateEnd);
		String jsonRatings = StatsUtils.convertObjectToJSON(personalRatingsDated);
		System.out.println("JSON: " + jsonRatings);

		return jsonRatings;
	}

	public String getRatingsTrendMonth() {
		long dateStart = DatesUtils.calculateLastMonthDates().getStartDate();
		long dateEnd = DatesUtils.calculateLastMonthDates().getEndDate();
		Map<String, PersonalRatings> personalRatingsDated = statsRepository.findRatingsDatedDateRange(dateStart,
				dateEnd);
		String jsonRatings = StatsUtils.convertObjectToJSON(personalRatingsDated);
		System.out.println("JSON: " + jsonRatings);

		return jsonRatings;
	}


}
