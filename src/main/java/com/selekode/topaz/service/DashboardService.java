package com.selekode.topaz.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.selekode.topaz.model.DashboardData;
import com.selekode.topaz.repository.InnerWorkEntryRepository;
import com.selekode.topaz.repository.JournalRepository;
import com.selekode.topaz.repository.RevisionRepository;
import com.selekode.topaz.utils.DashboardUtils;

@Service
public class DashboardService {

    private final JournalRepository journalRepository;
    private final RevisionRepository revisionRepository;
    private final InnerWorkEntryRepository innerWorkRepository;

    public DashboardService(JournalRepository journalRepository,
                            RevisionRepository revisionRepository,
                            InnerWorkEntryRepository innerWorkRepository) {
        this.journalRepository = journalRepository;
        this.revisionRepository = revisionRepository;
        this.innerWorkRepository = innerWorkRepository;
    }

    public Map<String, DashboardData> getDashboardData() {

        // --- Fetch and sort Unix epoch dates ---
        List<Long> journalDatesUnix = journalRepository.findAllDatesAsUnix();
        List<Long> revisionDatesUnix = revisionRepository.findAllDatesAsUnix();
        
        List<LocalDate> journalDates = DashboardUtils.toLocalDateList(journalDatesUnix);
        List<LocalDate> revisionDates = DashboardUtils.toLocalDateList(revisionDatesUnix);

        // --- Calculate streaks ---
        int journalStreak = DashboardUtils.calculateCurrentStreak(journalDates);
        int revisionStreak = DashboardUtils.calculateCurrentStreak(revisionDates);
        int longestJournalStreak = DashboardUtils.calculateLongestStreak(journalDates);
        int longestRevisionStreak = DashboardUtils.calculateLongestStreak(revisionDates);

        System.out.println("JournalStreak: " + journalStreak);
        System.out.println("RevisionStreak: " + revisionStreak);
        System.out.println("LongestJournalStreak: " + longestJournalStreak);
        System.out.println("LongestRevisionStreak: " + longestRevisionStreak);

        // --- Check entries today ---
        LocalDate today = LocalDate.now();
        boolean journalToday = journalRepository.existsByDateBetween(today, today);
        boolean revisionToday = revisionRepository.existsByDateBetween(today, today);
        boolean innerWorkToday = innerWorkRepository.existsByDateBetween(today, today);

        // --- Generate paragraphs for Thymeleaf ---
        String strJournalStreak = DashboardUtils.getJournalStreakParagraph(journalStreak);
        String strRevisionStreak = DashboardUtils.getRevisionStreakParagraph(revisionStreak);
        String strLongestJournalStreak = DashboardUtils.getLongestJournalStreakParagraph(longestJournalStreak);
        String strLongestRevisionStreak = DashboardUtils.getLongestRevisionStreakParagraph(longestRevisionStreak);
        String strJournalToday = DashboardUtils.getJournalIsWrittenTodayParagraph(journalToday);
        String strRevisionToday = DashboardUtils.getRevisionIsWrittenTodayParagraph(revisionToday);
        String strInnerWorkToday = DashboardUtils.getInnerWorkIsWrittenTodayParagraph(innerWorkToday);

        // --- Pack into DTOs ---
        DashboardData dashboardDataJournal = new DashboardData(
                journalStreak, longestJournalStreak, journalToday,
                strJournalStreak, strLongestJournalStreak, strJournalToday
        );

        DashboardData dashboardDataRevision = new DashboardData(
                revisionStreak, longestRevisionStreak, revisionToday,
                strRevisionStreak, strLongestRevisionStreak, strRevisionToday
        );

        DashboardData dashboardDataInnerWork = new DashboardData(
                0, 0, innerWorkToday,
                "", "", strInnerWorkToday
        );

        // --- Return map ---
        Map<String, DashboardData> dashboardDataMap = new HashMap<>();
        dashboardDataMap.put("Journal", dashboardDataJournal);
        dashboardDataMap.put("Revision", dashboardDataRevision);
        dashboardDataMap.put("InnerWork", dashboardDataInnerWork);

        return dashboardDataMap;
    }
}
