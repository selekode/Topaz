package com.selekode.topaz.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.selekode.topaz.model.DashboardData;
import com.selekode.topaz.model.Table;
import com.selekode.topaz.repository.DashboardRepository;
import com.selekode.topaz.utils.DashboardHelper;

@Service
public class DashboardService {

	public static Map<String, DashboardData> getDashboardData() {
		int journalStreak = DashboardRepository.getCurrentStreak(Table.JOURNAL);
		int revisionStreak = DashboardRepository.getCurrentStreak(Table.REVISION);
		int longestJournalStreak = DashboardRepository.getLongestStreak(Table.JOURNAL);
		int longestRevisionStreak = DashboardRepository.getLongestStreak(Table.REVISION);
		boolean journalToday = DashboardRepository.getIsWrittenToday(Table.JOURNAL);
		boolean revisionToday = DashboardRepository.getIsWrittenToday(Table.REVISION);
		boolean innerWorkToday = DashboardRepository.getIsWrittenToday(Table.INNER_WORK_ENTRY);
		
		String strJournalStreak = DashboardHelper.getJournalStreakParagraph(journalStreak);
		String strRevisionStreak = DashboardHelper.getRevisionStreakParagraph(revisionStreak);
		String strLongestJournalStreak = DashboardHelper.getLongestJournalStreakParagraph(longestJournalStreak);
		String strLongestRevisionStreak = DashboardHelper.getLongestRevisionStreakParagraph(longestRevisionStreak);
		String strJournalToday = DashboardHelper.getJournalIsWrittenTodayParagraph(journalToday);
		String strRevisionToday = DashboardHelper.getRevisionIsWrittenTodayParagraph(revisionToday);
		String strInnerWorkToday = DashboardHelper.getInnerWorkIsWrittenTodayParagraph(innerWorkToday);

		DashboardData dashboardDataJournal = new DashboardData(journalStreak, longestJournalStreak, journalToday,
				strJournalStreak, strLongestJournalStreak, strJournalToday);

		DashboardData dashboardDataRevision = new DashboardData(revisionStreak, longestRevisionStreak, revisionToday,
				strRevisionStreak, strLongestRevisionStreak, strRevisionToday);

		DashboardData dashboardDataInnerWork = new DashboardData(0, 0, innerWorkToday, "", "", strInnerWorkToday);

		Map<String, DashboardData> dashboardDataMap = new HashMap<>();
		dashboardDataMap.put("Journal", dashboardDataJournal);
		dashboardDataMap.put("Revision", dashboardDataRevision);
		dashboardDataMap.put("InnerWork", dashboardDataInnerWork);

		return dashboardDataMap;
	}

}
