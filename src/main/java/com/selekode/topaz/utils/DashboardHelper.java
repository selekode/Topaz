package com.selekode.topaz.utils;

public class DashboardHelper {
	public static String getJournalStreakParagraph(int journalStreak) {
		String journalStreakParagraph = "";
		System.out.println("journalStreak: " + journalStreak);

		if (journalStreak <= 1) {
			journalStreakParagraph = "¡Escribe varios dias seguidos para empezar una racha!";
		} else if (journalStreak > 1) {
			journalStreakParagraph = "Llevas " + journalStreak
					+ " días seguidos escribiendo revisiones. ¡Sigue así!";
		}
		
		return journalStreakParagraph;
	}

	public static String getRevisionStreakParagraph(int revisionStreak) {
		String revisionStreakParagraph = null;
		System.out.println("RevisionStreak: " + revisionStreak);
		if (revisionStreak <= 1) {
			revisionStreakParagraph = "¡Escribe varios dias seguidos para empezar una racha!";
		} else if (revisionStreak > 1) {
			revisionStreakParagraph = "Llevas " + revisionStreak
					+ " días seguidos escribiendo revisiones. ¡Sigue así!";
		}
		return revisionStreakParagraph;
	}

	public static String getLongestJournalStreakParagraph(int longestJournalStreak) {
		String longestJournalStreakParagraph = null;
		if (longestJournalStreak <= 1) {
			longestJournalStreakParagraph = "Aún no has conseguido una racha escribiendo en tu diario";
		}
		if (longestJournalStreak > 1) {
			longestJournalStreakParagraph = "Tu racha más larga escribiendo en tu diario ha sido de "
					+ longestJournalStreak + " días.";
		}
		return longestJournalStreakParagraph;
	}

	public static String getLongestRevisionStreakParagraph(int longestRevisionStreak) {
		String longestRrevisionStreakParagraph = null;
		if (longestRevisionStreak <= 1) {
			longestRrevisionStreakParagraph = "Aún no has conseguido una racha de revisiones";
		}
		if (longestRevisionStreak > 1) {
			longestRrevisionStreakParagraph = "Tu racha más larga escribiendo revisiones ha sido de "
					+ longestRevisionStreak + " días.";
		}
		return longestRrevisionStreakParagraph;
	}

	public static String getJournalIsWrittenTodayParagraph(boolean journalToday) {
		String journalIsWrittenTodayParagraph = null;
		if (journalToday == true) {
			journalIsWrittenTodayParagraph = "";
		} else {
			journalIsWrittenTodayParagraph = "• Aún no has escrito en tu diario hoy.";
		}
		return journalIsWrittenTodayParagraph;
	}

	public static String getRevisionIsWrittenTodayParagraph(boolean revisionToday) {
		String revisionIsWrittenTodayParagraph = null;
		if (revisionToday == true) {
			revisionIsWrittenTodayParagraph = "";
		} else {
			revisionIsWrittenTodayParagraph = "• Aún no has escrito una revisión hoy.";
		}
		return revisionIsWrittenTodayParagraph;
	}
}
