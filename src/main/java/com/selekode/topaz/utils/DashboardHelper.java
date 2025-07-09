package com.selekode.topaz.utils;

public class DashboardHelper {
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
