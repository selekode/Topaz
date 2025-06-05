package com.selekode.topaz.utils;

public class DashboardHelper {
	public static String getJournalStreakParagraph(int journalStreak) {
		String journalStreakParagraph = "";
		System.out.println("journalStreak: " + journalStreak);

		if (journalStreak <= 1) {
			journalStreakParagraph = "Deberías empezar una racha en tu diario. ¡Escribe varios dias seguidos para conseguirla!";
		} else if (journalStreak > 1) {
			journalStreakParagraph = "Llevas " + journalStreak + " días seguidos escribiendo en tu diario. ¡Sigue así!";
		}
		if (journalStreak == 10) {
			journalStreakParagraph = "¡Enhorabuena! Llevas " + journalStreak
					+ " días seguidos escribiendo en tu diario. ¡Sigue así!";
		}
		if (journalStreak == 14) {
			journalStreakParagraph = "¡Enhorabuena! Llevas dos semanas seguidas escribiendo en tu diario. ¡Sigue así!";
		}
		if (journalStreak == 30) {
			journalStreakParagraph = "¡Enhorabuena! Llevas un mes seguido escribiendo en tu diario. ¡Sigue así!";
		}
		if (journalStreak == 60) {
			journalStreakParagraph = "¡Enhorabuena! Llevas dos meses seguidos escribiendo en tu diario. ¡Sigue así!";
		}
		if (journalStreak == 90) {
			journalStreakParagraph = "¡Enhorabuena! Llevas tres meses seguidos escribiendo en tu diario. ¡Sigue así!";
		}
		if (journalStreak == 100) {
			journalStreakParagraph = "¡Enhorabuena! Llevas " + journalStreak
					+ " días seguidos escribiendo en tu diario. ¡Sigue así!";
		}
		if (journalStreak == 182) {
			journalStreakParagraph = "¡Enhorabuena! Llevas seis meses seguidos escribiendo en tu diario. ¡Sigue así!";
		}
		if (journalStreak == 274) {
			journalStreakParagraph = "¡Enhorabuena! Llevas nueve meses seguidos escribiendo en tu diario. ¡Sigue así!";
		}
		if (journalStreak == 365) {
			journalStreakParagraph = "¡Enhorabuena! Llevas un año entero escribiendo en tu diario. ¡A por otro año!";
		}

		return journalStreakParagraph;
	}

	public static String getRevisionStreakParagraph(int revisionStreak) {
		String revisionStreakParagraph = null;
		System.out.println("RevisionStreak: " + revisionStreak);
		if (revisionStreak <= 1) {
			revisionStreakParagraph = "Deberías empezar una racha de revisiones. ¡Escribe varios dias seguidos para conseguirla!";
		} else if (revisionStreak > 1) {
			revisionStreakParagraph = "Llevas " + revisionStreak
					+ " días seguidos escribiendo revisiones. ¡Sigue así!";
		}
		if (revisionStreak == 10) {
			revisionStreakParagraph = "¡Enhorabuena! Llevas " + revisionStreak
					+ " días seguidos escribiendo en tu diario. ¡Sigue así!";
		}
		if (revisionStreak == 14) {
			revisionStreakParagraph = "¡Enhorabuena! Llevas dos semanas seguidas escribiendo revisiones. ¡Sigue así!";
		}
		if (revisionStreak == 30) {
			revisionStreakParagraph = "¡Enhorabuena! Llevas un mes seguido escribiendo revisiones. ¡Sigue así!";
		}
		if (revisionStreak == 60) {
			revisionStreakParagraph = "¡Enhorabuena! Llevas dos meses seguidos escribiendo revisiones. ¡Sigue así!";
		}
		if (revisionStreak == 90) {
			revisionStreakParagraph = "¡Enhorabuena! Llevas tres meses seguidos escribiendo revisiones. ¡Sigue así!";
		}
		if (revisionStreak == 100) {
			revisionStreakParagraph = "¡Enhorabuena! Llevas " + revisionStreak
					+ " días seguidos escribiendo revisiones. ¡Sigue así!";
		}
		if (revisionStreak == 182) {
			revisionStreakParagraph = "¡Enhorabuena! Llevas seis meses seguidos escribiendo revisiones. ¡Sigue así!";
		}
		if (revisionStreak == 274) {
			revisionStreakParagraph = "¡Enhorabuena! Llevas nueve meses seguidos escribiendo revisiones. ¡Sigue así!";
		}
		if (revisionStreak == 365) {
			revisionStreakParagraph = "¡Enhorabuena! Llevas un año entero escribiendo revisiones. ¡A por otro año!";
		}
		return revisionStreakParagraph;
	}

	public static String getLongestJournalStreakParagraph(int longestJournalStreak) {
		String longestJournalStreakParagraph = null;
		if (longestJournalStreak <= 1) {
			longestJournalStreakParagraph = "";
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
			longestRrevisionStreakParagraph = "";
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
			journalIsWrittenTodayParagraph = "Hoy no has escrito en tu diario aún.";
		}
		return journalIsWrittenTodayParagraph;
	}

	public static String getRevisionIsWrittenTodayParagraph(boolean revisionToday) {
		String revisionIsWrittenTodayParagraph = null;
		if (revisionToday == true) {
			revisionIsWrittenTodayParagraph = "";
		} else {
			revisionIsWrittenTodayParagraph = "Hoy no has escrito una revisión aún.";
		}
		return revisionIsWrittenTodayParagraph;
	}
}
