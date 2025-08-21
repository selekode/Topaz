package com.selekode.topaz.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

public class DashboardUtils {
	public static List<LocalDate> toLocalDateList(List<Long> epochSecondsList) {
        if (epochSecondsList == null) return List.of();

        return epochSecondsList.stream()
                .filter(epoch -> epoch != null)
                .map(epoch -> Instant.ofEpochSecond(epoch)
                                     .atZone(ZoneId.systemDefault())
                                     .toLocalDate())
                .collect(Collectors.toList());
    }

	public static int calculateCurrentStreak(List<LocalDate> dates) {
	    if (dates == null || dates.isEmpty()) return 0;

	    int streak = 0;
	    LocalDate today = LocalDate.now();
	    LocalDate expected = today;

	    // Iterate from the latest date backwards
	    for (int i = dates.size() - 1; i >= 0; i--) {
	        LocalDate entryDate = dates.get(i);
	        if (entryDate == null) continue;

	        if (entryDate.equals(expected)) {
	            streak++;
	            expected = expected.minusDays(1);
	        } else if (entryDate.isBefore(expected)) {
	            break; // streak broken
	        }
	    }
	    return streak;
	}

	public static int calculateLongestStreak(List<LocalDate> dates) {
	    if (dates == null || dates.isEmpty()) return 0;

	    int longest = 0;
	    int current = 0;
	    LocalDate prevDate = null;

	    for (LocalDate currDate : dates) {
	        if (currDate == null) continue;

	        if (prevDate != null && currDate.equals(prevDate.plusDays(1))) {
	            current++;
	        } else {
	            current = 1;
	        }

	        longest = Math.max(longest, current);
	        prevDate = currDate;
	    }
	    return longest;
	}
	
	public static String getJournalStreakParagraph(int streak) {
		String paragraph = "";
		System.out.println("journalStreak: " + streak);

		if (streak <= 1) {
			paragraph = "¡Escribe varios dias seguidos para empezar una racha!";
		} else if (streak > 1) {
			paragraph = "Llevas " + streak
					+ " días seguidos escribiendo revisiones. ¡Sigue así!";
		}
		
		return paragraph;
	}

	public static String getRevisionStreakParagraph(int streak) {
		String paragraph = null;
		System.out.println("RevisionStreak: " + streak);
		if (streak <= 1) {
			paragraph = "¡Escribe varios dias seguidos para empezar una racha!";
		} else if (streak > 1) {
			paragraph = "Llevas " + streak
					+ " días seguidos escribiendo revisiones. ¡Sigue así!";
		}
		return paragraph;
	}

	public static String getLongestJournalStreakParagraph(int streak) {
		String paragraph = null;
		if (streak <= 1) {
			paragraph = "Aún no has conseguido una racha escribiendo en tu diario";
		}
		if (streak > 1) {
			paragraph = "Tu racha más larga escribiendo en tu diario ha sido de "
					+ streak + " días";
		}
		return paragraph;
	}

	public static String getLongestRevisionStreakParagraph(int streak) {
		String paragraph = null;
		if (streak <= 1) {
			paragraph = "Aún no has conseguido una racha de revisiones";
		}
		if (streak > 1) {
			paragraph = "Tu racha más larga escribiendo revisiones ha sido de "
					+ streak + " días";
		}
		return paragraph;
	}

	public static String getJournalIsWrittenTodayParagraph(boolean isWritten) {
		String paragraph = null;
		if (isWritten == true) {
			paragraph = " ";
		} else {
			paragraph = "• Aún no has escrito en tu diario hoy";
		}
		return paragraph;
	}

	public static String getRevisionIsWrittenTodayParagraph(boolean isWritten) {
		String paragraph = null;
		if (isWritten == true) {
			paragraph = "";
		} else {
			paragraph = "• Aún no has escrito una revisión hoy";
		}
		return paragraph;
	}

	public static String getInnerWorkIsWrittenTodayParagraph(boolean isWritten) {
		String paragraph = null;
		
		if (isWritten == true) {
			paragraph = "";
		} else {
			paragraph = "• Podrías escribir un trabajo interno";
		}
		
		return paragraph;
	}

}
