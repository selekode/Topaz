package com.selekode.topaz.service;

import org.springframework.stereotype.Service;
import com.selekode.topaz.model.DashboardData;
import com.selekode.topaz.repository.DashboardRepository;
import com.selekode.topaz.utils.DashboardHelper;

@Service
public class DashboardService {

	public static DashboardData getDashboardData() {
		int journalStreak = DashboardRepository.getCurrentJournalStreak();
		int revisionStreak = DashboardRepository.getCurrentRevisionStreak();
		int longestJournalStreak = DashboardRepository.getLongestJournalStreak();
		int longestRevisionStreak = DashboardRepository.getLongestRevisionStreak();
		boolean journalToday = DashboardRepository.getJournalIsWrittenToday();
		boolean revisionToday = DashboardRepository.getRevisionIsWrittenToday();

		String strJournalStreak = DashboardHelper.getJournalStreakParagraph(journalStreak);
		String strRevisionStreak = DashboardHelper.getRevisionStreakParagraph(revisionStreak);
		String strLongestJournalStreak = DashboardHelper.getLongestJournalStreakParagraph(longestJournalStreak);
		String strLongestRevisionStreak = DashboardHelper.getLongestRevisionStreakParagraph(longestRevisionStreak);
		String strJournalToday = DashboardHelper.getJournalIsWrittenTodayParagraph(journalToday);
		String strRevisionToday = DashboardHelper.getRevisionIsWrittenTodayParagraph(revisionToday);

		DashboardData dashboardData = new DashboardData(journalStreak, revisionStreak, longestJournalStreak,
				longestRevisionStreak, journalToday, revisionToday, strJournalStreak, strRevisionStreak,
				strLongestJournalStreak, strLongestRevisionStreak, strJournalToday, strRevisionToday);

		return dashboardData;
	}

}
