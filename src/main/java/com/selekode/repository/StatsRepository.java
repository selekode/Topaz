package com.selekode.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.selekode.topaz.model.PersonalRatings;
import com.selekode.topaz.model.StatsActivityPerDayOfWeek;
import com.selekode.topaz.model.StatsEmotionFrequency;

public interface StatsRepository {
	public static final String DB_URL = "jdbc:sqlite:src/main/resources/database/topazdatabase.db";

	public static String convertDateToString_ddMMMyyy_hhmma(long date) {
		// Convert the Unix timestamp (milliseconds) to an Instant
		Instant instant = Instant.ofEpochSecond(date);

		// Define the desired format (DD-MMM-YYYY)
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a")
				.withZone(ZoneId.systemDefault());
		String dateStr = formatter.format(instant);

		return dateStr;
	}
	public static String convertDateToString_ddMMMyyy(long date) {
		// Convert the Unix timestamp (milliseconds) to an Instant
		Instant instant = Instant.ofEpochSecond(date);
		
		// Define the desired format (DD-MMM-YYYY)
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
				.withZone(ZoneId.systemDefault());
		String dateStr = formatter.format(instant);
		
		return dateStr;
	}

	public static int getJournalEntryCountAllTime() {
		int journalEntryCount = 0;

		// SQL query to count rows in the 'journal' table
		String sql = "SELECT COUNT(*) FROM journal";

		// Database connection
		try (Connection conn = DriverManager.getConnection(DB_URL);
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			// Check if the result set has a value
			if (rs.next()) {
				int rowCount = rs.getInt(1); // Get the count of rows
				journalEntryCount = rowCount;
				System.out.println("Number of rows in 'journal' table: " + rowCount);
			}

		} catch (SQLException e) {
			System.err.println("Error accessing the database: " + e.getMessage());
		}

		return journalEntryCount;
	}

	public static int getRevisionEntryCountAllTime() {
		int revisionEntryCount = 0;

		// SQL query to count rows in the 'journal' table
		String sql = "SELECT COUNT(*) FROM revision";

		// Database connection
		try (Connection conn = DriverManager.getConnection(DB_URL);
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			// Check if the result set has a value
			if (rs.next()) {
				int rowCount = rs.getInt(1); // Get the count of rows
				revisionEntryCount = rowCount;
				System.out.println("Number of rows in 'revision' table: " + rowCount);
			}

		} catch (SQLException e) {
			System.err.println("Error accessing the database: " + e.getMessage());
		}

		return revisionEntryCount;
	}

	public static int getJournalEntryCountDateRange(long dateStart, long dateEnd) {
		int journalEntryCount = 0;
		System.out.println("Searching for rows in journal between:" + dateStart + " and " + dateEnd);

		// SQLite query to count the rows between date1 and date2
		String sql = "SELECT COUNT(*) FROM journal WHERE date BETWEEN ? AND ?";

		// Database connection
		try (Connection connection = DriverManager.getConnection(DB_URL);
				PreparedStatement stmt = connection.prepareStatement(sql)) {

			stmt.setLong(1, dateStart);
			stmt.setLong(2, dateEnd);

			try (ResultSet rs = stmt.executeQuery()) {
				// Check if the result set has a value
				if (rs.next()) {
					int rowCount = rs.getInt(1); // Get the count of rows
					journalEntryCount = rowCount;
					System.out.println("Number of rows in 'journal' table: " + rowCount);
				}
			}

		} catch (SQLException e) {
			System.err.println("Error accessing the database: " + e.getMessage());
		}
		return journalEntryCount;
	}

	public static int getRevisionEntryCountDateRange(long dateStart, long dateEnd) {
		int revisionEntryCount = 0;
		System.out.println("Searching for rows in revision between:" + dateStart + " and " + dateEnd);

		// SQLite query to count the rows between date1 and date2
		String sql = "SELECT COUNT(*) FROM revision WHERE date BETWEEN ? AND ?";

		// Database connection
		try (Connection connection = DriverManager.getConnection(DB_URL);
				PreparedStatement stmt = connection.prepareStatement(sql)) {

			stmt.setLong(1, dateStart);
			stmt.setLong(2, dateEnd);

			try (ResultSet rs = stmt.executeQuery()) {
				// Check if the result set has a value
				if (rs.next()) {
					int rowCount = rs.getInt(1); // Get the count of rows
					revisionEntryCount = rowCount;
					System.out.println("Number of rows in 'revision' table: " + rowCount);
				}
			}

		} catch (SQLException e) {
			System.err.println("Error accessing the database: " + e.getMessage());
		}
		return revisionEntryCount;
	}

	public static StatsActivityPerDayOfWeek getEntryCountPerDayAllTime() {
		int journalMondayEntryCount = 0;
		int journalTuesdayEntryCount = 0;
		int journalWednesdayEntryCount = 0;
		int journalThursdayEntryCount = 0;
		int journalFridayEntryCount = 0;
		int journalSaturdayEntryCount = 0;
		int journalSundayEntryCount = 0;

		int revisionMondayEntryCount = 0;
		int revisionTuesdayEntryCount = 0;
		int revisionWednesdayEntryCount = 0;
		int revisionThursdayEntryCount = 0;
		int revisionFridayEntryCount = 0;
		int revisionSaturdayEntryCount = 0;
		int revisionSundayEntryCount = 0;

		StatsActivityPerDayOfWeek entryCountPerDay = null;

		String sql = "SELECT strftime('%w', date, 'unixepoch') AS weekday, COUNT(*) AS entry_count FROM journal GROUP BY weekday ORDER BY weekday;";

		try (Connection conn = DriverManager.getConnection(DB_URL);
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				int weekday = rs.getInt("weekday");
				int count = rs.getInt("entry_count");

				switch (weekday) {
				case 0 -> journalSundayEntryCount = count;
				case 1 -> journalMondayEntryCount = count;
				case 2 -> journalTuesdayEntryCount = count;
				case 3 -> journalWednesdayEntryCount = count;
				case 4 -> journalThursdayEntryCount = count;
				case 5 -> journalFridayEntryCount = count;
				case 6 -> journalSaturdayEntryCount = count;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		String sql2 = "SELECT strftime('%w', date, 'unixepoch') AS weekday, COUNT(*) AS entry_count FROM revision GROUP BY weekday ORDER BY weekday;";

		try (Connection conn = DriverManager.getConnection(DB_URL);
				PreparedStatement pstmt = conn.prepareStatement(sql2);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				int weekday = rs.getInt("weekday");
				int count = rs.getInt("entry_count");

				switch (weekday) {
				case 0 -> revisionSundayEntryCount = count;
				case 1 -> revisionMondayEntryCount = count;
				case 2 -> revisionTuesdayEntryCount = count;
				case 3 -> revisionWednesdayEntryCount = count;
				case 4 -> revisionThursdayEntryCount = count;
				case 5 -> revisionFridayEntryCount = count;
				case 6 -> revisionSaturdayEntryCount = count;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		entryCountPerDay = new StatsActivityPerDayOfWeek(journalMondayEntryCount, journalTuesdayEntryCount,
				journalWednesdayEntryCount, journalThursdayEntryCount, journalFridayEntryCount,
				journalSaturdayEntryCount, journalSundayEntryCount, revisionMondayEntryCount, revisionTuesdayEntryCount,
				revisionWednesdayEntryCount, revisionThursdayEntryCount, revisionFridayEntryCount,
				revisionSaturdayEntryCount, revisionSundayEntryCount, "", "", 0, 0);

		return entryCountPerDay;

	}

	public static StatsActivityPerDayOfWeek getEntryCountPerDayDateRange(long dateStart, long dateEnd) {
		int journalMondayEntryCount = 0;
		int journalTuesdayEntryCount = 0;
		int journalWednesdayEntryCount = 0;
		int journalThursdayEntryCount = 0;
		int journalFridayEntryCount = 0;
		int journalSaturdayEntryCount = 0;
		int journalSundayEntryCount = 0;

		int revisionMondayEntryCount = 0;
		int revisionTuesdayEntryCount = 0;
		int revisionWednesdayEntryCount = 0;
		int revisionThursdayEntryCount = 0;
		int revisionFridayEntryCount = 0;
		int revisionSaturdayEntryCount = 0;
		int revisionSundayEntryCount = 0;

		StatsActivityPerDayOfWeek entryCountPerDay = null;

		String sql = "SELECT date, strftime('%w', date, 'unixepoch') AS weekday, COUNT(*) AS entry_count "
				+ "FROM journal " + "WHERE date BETWEEN ? AND ? " + "GROUP BY date, weekday " + // Group by date as well
																								// to retain individual
																								// dates
				"ORDER BY date, weekday;";

		try (Connection conn = DriverManager.getConnection(DB_URL);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			// Set dateStart and dateEnd parameters (assuming they are in Unix timestamp
			// format)
			pstmt.setLong(1, dateStart);
			pstmt.setLong(2, dateEnd);

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					long entryDate = rs.getLong("date"); // Retrieve the date
					int weekday = rs.getInt("weekday");
					int count = rs.getInt("entry_count");

					System.out.println("Date: " + entryDate + ", Weekday: " + weekday + ", Count: " + count);

					switch (weekday) {
					case 0 -> journalSundayEntryCount += count;
					case 1 -> journalMondayEntryCount += count;
					case 2 -> journalTuesdayEntryCount += count;
					case 3 -> journalWednesdayEntryCount += count;
					case 4 -> journalThursdayEntryCount += count;
					case 5 -> journalFridayEntryCount += count;
					case 6 -> journalSaturdayEntryCount += count;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		String sql2 = "SELECT date, strftime('%w', date, 'unixepoch') AS weekday, COUNT(*) AS entry_count "
				+ "FROM revision " + "WHERE date BETWEEN ? AND ? " + "GROUP BY date, weekday " + // Grouping by date as
																									// well to retain
																									// individual dates
				"ORDER BY date, weekday;";

		try (Connection conn = DriverManager.getConnection(DB_URL);
				PreparedStatement pstmt = conn.prepareStatement(sql2)) {

			// Set dateStart and dateEnd parameters (assuming they are in Unix timestamp
			// format)
			pstmt.setLong(1, dateStart);
			pstmt.setLong(2, dateEnd);

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					long entryDate = rs.getLong("date"); // Retrieve the date
					int weekday = rs.getInt("weekday");
					int count = rs.getInt("entry_count");

					System.out.println("Date: " + entryDate + ", Weekday: " + weekday + ", Count: " + count);

					switch (weekday) {
					case 0 -> revisionSundayEntryCount += count;
					case 1 -> revisionMondayEntryCount += count;
					case 2 -> revisionTuesdayEntryCount += count;
					case 3 -> revisionWednesdayEntryCount += count;
					case 4 -> revisionThursdayEntryCount += count;
					case 5 -> revisionFridayEntryCount += count;
					case 6 -> revisionSaturdayEntryCount += count;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		entryCountPerDay = new StatsActivityPerDayOfWeek(journalMondayEntryCount, journalTuesdayEntryCount,
				journalWednesdayEntryCount, journalThursdayEntryCount, journalFridayEntryCount,
				journalSaturdayEntryCount, journalSundayEntryCount, revisionMondayEntryCount, revisionTuesdayEntryCount,
				revisionWednesdayEntryCount, revisionThursdayEntryCount, revisionFridayEntryCount,
				revisionSaturdayEntryCount, revisionSundayEntryCount, "", "", 0, 0);

		return entryCountPerDay;

	}

	public static StatsEmotionFrequency getEmotionCountAllTime() {
		String sql = """
				    SELECT
				        SUM(CASE WHEN emocionAlegria = 1 THEN 1 ELSE 0 END) AS emocionAlegriaCount,
				        SUM(CASE WHEN emocionTristeza = 1 THEN 1 ELSE 0 END) AS emocionTristezaCount,
				        SUM(CASE WHEN emocionIra = 1 THEN 1 ELSE 0 END) AS emocionIraCount,
				        SUM(CASE WHEN emocionMiedo = 1 THEN 1 ELSE 0 END) AS emocionMiedoCount,
				        SUM(CASE WHEN emocionAnsiedad = 1 THEN 1 ELSE 0 END) AS emocionAnsiedadCount,
				        SUM(CASE WHEN emocionAmor = 1 THEN 1 ELSE 0 END) AS emocionAmorCount,
				        SUM(CASE WHEN emocionSorpresa = 1 THEN 1 ELSE 0 END) AS emocionSorpresaCount,
				        SUM(CASE WHEN emocionVerguenza = 1 THEN 1 ELSE 0 END) AS emocionVerguenzaCount,
				        SUM(CASE WHEN emocionFrustracion = 1 THEN 1 ELSE 0 END) AS emocionFrustracionCount,
				        SUM(CASE WHEN emocionSatisfaccion = 1 THEN 1 ELSE 0 END) AS emocionSatisfaccionCount,
				        SUM(CASE WHEN emocionAburrimiento = 1 THEN 1 ELSE 0 END) AS emocionAburrimientoCount,
				        SUM(CASE WHEN emocionSerenidad = 1 THEN 1 ELSE 0 END) AS emocionSerenidadCount,
				        SUM(CASE WHEN emocionConfianza = 1 THEN 1 ELSE 0 END) AS emocionConfianzaCount,
				        SUM(CASE WHEN emocionAbrumado = 1 THEN 1 ELSE 0 END) AS emocionAbrumadoCount,
				        SUM(CASE WHEN emocionEsperanza = 1 THEN 1 ELSE 0 END) AS emocionEsperanzaCount
				    FROM revision;
				""";

		StatsEmotionFrequency emotionFrequency = new StatsEmotionFrequency(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				"", 0, "", 0, "", 0, "", 0);

		try (Connection conn = DriverManager.getConnection(DB_URL);
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			if (rs.next()) {
				emotionFrequency.setEmocionAlegriaCount(rs.getInt("emocionAlegriaCount"));
				emotionFrequency.setEmocionTristezaCount(rs.getInt("emocionTristezaCount"));
				emotionFrequency.setEmocionIraCount(rs.getInt("emocionIraCount"));
				emotionFrequency.setEmocionMiedoCount(rs.getInt("emocionMiedoCount"));
				emotionFrequency.setEmocionAnsiedadCount(rs.getInt("emocionAnsiedadCount"));
				emotionFrequency.setEmocionAmorCount(rs.getInt("emocionAmorCount"));
				emotionFrequency.setEmocionSorpresaCount(rs.getInt("emocionSorpresaCount"));
				emotionFrequency.setEmocionVerguenzaCount(rs.getInt("emocionVerguenzaCount"));
				emotionFrequency.setEmocionFrustracionCount(rs.getInt("emocionFrustracionCount"));
				emotionFrequency.setEmocionSatisfaccionCount(rs.getInt("emocionSatisfaccionCount"));
				emotionFrequency.setEmocionAburrimientoCount(rs.getInt("emocionAburrimientoCount"));
				emotionFrequency.setEmocionSerenidadCount(rs.getInt("emocionSerenidadCount"));
				emotionFrequency.setEmocionConfianzaCount(rs.getInt("emocionConfianzaCount"));
				emotionFrequency.setEmocionAbrumadoCount(rs.getInt("emocionAbrumadoCount"));
				emotionFrequency.setEmocionEsperanzaCount(rs.getInt("emocionEsperanzaCount"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return emotionFrequency;
	}

	public static StatsEmotionFrequency getEmotionCountDateRange(Long startDate, Long endDate) {
		String sql = """
				    SELECT
				        SUM(CASE WHEN emocionAlegria = 1 THEN 1 ELSE 0 END) AS emocionAlegriaCount,
				        SUM(CASE WHEN emocionTristeza = 1 THEN 1 ELSE 0 END) AS emocionTristezaCount,
				        SUM(CASE WHEN emocionIra = 1 THEN 1 ELSE 0 END) AS emocionIraCount,
				        SUM(CASE WHEN emocionMiedo = 1 THEN 1 ELSE 0 END) AS emocionMiedoCount,
				        SUM(CASE WHEN emocionAnsiedad = 1 THEN 1 ELSE 0 END) AS emocionAnsiedadCount,
				        SUM(CASE WHEN emocionAmor = 1 THEN 1 ELSE 0 END) AS emocionAmorCount,
				        SUM(CASE WHEN emocionSorpresa = 1 THEN 1 ELSE 0 END) AS emocionSorpresaCount,
				        SUM(CASE WHEN emocionVerguenza = 1 THEN 1 ELSE 0 END) AS emocionVerguenzaCount,
				        SUM(CASE WHEN emocionFrustracion = 1 THEN 1 ELSE 0 END) AS emocionFrustracionCount,
				        SUM(CASE WHEN emocionSatisfaccion = 1 THEN 1 ELSE 0 END) AS emocionSatisfaccionCount,
				        SUM(CASE WHEN emocionAburrimiento = 1 THEN 1 ELSE 0 END) AS emocionAburrimientoCount,
				        SUM(CASE WHEN emocionSerenidad = 1 THEN 1 ELSE 0 END) AS emocionSerenidadCount,
				        SUM(CASE WHEN emocionConfianza = 1 THEN 1 ELSE 0 END) AS emocionConfianzaCount,
				        SUM(CASE WHEN emocionAbrumado = 1 THEN 1 ELSE 0 END) AS emocionAbrumadoCount,
				        SUM(CASE WHEN emocionEsperanza = 1 THEN 1 ELSE 0 END) AS emocionEsperanzaCount
				    FROM revision
				    WHERE date BETWEEN ? AND ?;
				""";

		StatsEmotionFrequency emotionFrequency = new StatsEmotionFrequency(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				"", 0, "", 0, "", 0, "", 0);

		try (Connection conn = DriverManager.getConnection(DB_URL);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setLong(1, startDate);
			pstmt.setLong(2, endDate);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					emotionFrequency.setEmocionAlegriaCount(rs.getInt("emocionAlegriaCount"));
					emotionFrequency.setEmocionTristezaCount(rs.getInt("emocionTristezaCount"));
					emotionFrequency.setEmocionIraCount(rs.getInt("emocionIraCount"));
					emotionFrequency.setEmocionMiedoCount(rs.getInt("emocionMiedoCount"));
					emotionFrequency.setEmocionAnsiedadCount(rs.getInt("emocionAnsiedadCount"));
					emotionFrequency.setEmocionAmorCount(rs.getInt("emocionAmorCount"));
					emotionFrequency.setEmocionSorpresaCount(rs.getInt("emocionSorpresaCount"));
					emotionFrequency.setEmocionVerguenzaCount(rs.getInt("emocionVerguenzaCount"));
					emotionFrequency.setEmocionFrustracionCount(rs.getInt("emocionFrustracionCount"));
					emotionFrequency.setEmocionSatisfaccionCount(rs.getInt("emocionSatisfaccionCount"));
					emotionFrequency.setEmocionAburrimientoCount(rs.getInt("emocionAburrimientoCount"));
					emotionFrequency.setEmocionSerenidadCount(rs.getInt("emocionSerenidadCount"));
					emotionFrequency.setEmocionConfianzaCount(rs.getInt("emocionConfianzaCount"));
					emotionFrequency.setEmocionAbrumadoCount(rs.getInt("emocionAbrumadoCount"));
					emotionFrequency.setEmocionEsperanzaCount(rs.getInt("emocionEsperanzaCount"));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return emotionFrequency;
	}

	public static List<PersonalRatings> getPersonalRatingsAllTime() {
		List<PersonalRatings> personalRatings = new ArrayList<>();

		// SQL query to fetch the required columns
		String sql = "SELECT valoracionDisciplina, valoracionOrden, valoracionImpulsividad, valoracionConstancia, "
				+ "valoracionTolerancia, valoracionControlPrepotencia, valoracionHonestidad, valoracionAceptacion, "
				+ "valoracionConsecucionObjetivos FROM revision";

		try (Connection conn = DriverManager.getConnection(DB_URL);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				int valoracionDisciplina = rs.getInt("valoracionDisciplina");
				int valoracionOrden = rs.getInt("valoracionOrden");
				int valoracionImpulsividad = rs.getInt("valoracionImpulsividad");
				int valoracionConstancia = rs.getInt("valoracionConstancia");
				int valoracionTolerancia = rs.getInt("valoracionTolerancia");
				int valoracionControlPrepotencia = rs.getInt("valoracionControlPrepotencia");
				int valoracionHonestidad = rs.getInt("valoracionHonestidad");
				int valoracionAceptacion = rs.getInt("valoracionAceptacion");
				int valoracionConsecucionObjetivos = rs.getInt("valoracionConsecucionObjetivos");

				// Create a new PersonalRatings object and add it to the list
				PersonalRatings ratings = new PersonalRatings(valoracionDisciplina, valoracionOrden,
						valoracionImpulsividad, valoracionConstancia, valoracionTolerancia,
						valoracionControlPrepotencia, valoracionHonestidad, valoracionAceptacion,
						valoracionConsecucionObjetivos);

				personalRatings.add(ratings);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personalRatings;
	}

	public static List<PersonalRatings> getPersonalRatingsDateRange(long dateStart, long dateEnd) {
		List<PersonalRatings> personalRatings = new ArrayList<>();

		// SQL query to fetch the required columns, using placeholders for the dates
		String sql = "SELECT valoracionDisciplina, valoracionOrden, valoracionImpulsividad, valoracionConstancia, "
				+ "valoracionTolerancia, valoracionControlPrepotencia, valoracionHonestidad, valoracionAceptacion, "
				+ "valoracionConsecucionObjetivos " + "FROM revision " + "WHERE date >= ? AND date <= ?";

		try (Connection conn = DriverManager.getConnection(DB_URL);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			// Set the parameters for the date range
			pstmt.setLong(1, dateStart);
			pstmt.setLong(2, dateEnd);

			// Execute the query
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					int valoracionDisciplina = rs.getInt("valoracionDisciplina");
					int valoracionOrden = rs.getInt("valoracionOrden");
					int valoracionImpulsividad = rs.getInt("valoracionImpulsividad");
					int valoracionConstancia = rs.getInt("valoracionConstancia");
					int valoracionTolerancia = rs.getInt("valoracionTolerancia");
					int valoracionControlPrepotencia = rs.getInt("valoracionControlPrepotencia");
					int valoracionHonestidad = rs.getInt("valoracionHonestidad");
					int valoracionAceptacion = rs.getInt("valoracionAceptacion");
					int valoracionConsecucionObjetivos = rs.getInt("valoracionConsecucionObjetivos");

					// Create a new PersonalRatings object and add it to the list
					PersonalRatings ratings = new PersonalRatings(valoracionDisciplina, valoracionOrden,
							valoracionImpulsividad, valoracionConstancia, valoracionTolerancia,
							valoracionControlPrepotencia, valoracionHonestidad, valoracionAceptacion,
							valoracionConsecucionObjetivos);

					personalRatings.add(ratings);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personalRatings;
	}	

	
	public static Map<String, PersonalRatings> findRatingsDatedAllTime() {
        Map<String, PersonalRatings> personalRatingsMap = new HashMap<>();

		// SQL query to fetch the required columns
		String sql = "SELECT date, valoracionDisciplina, valoracionOrden, valoracionImpulsividad, valoracionConstancia, "
				+ "valoracionTolerancia, valoracionControlPrepotencia, valoracionHonestidad, valoracionAceptacion, "
				+ "valoracionConsecucionObjetivos FROM revision";

		try (Connection conn = DriverManager.getConnection(DB_URL);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				String date = convertDateToString_ddMMMyyy(rs.getLong("date"));
				int valoracionDisciplina = rs.getInt("valoracionDisciplina");
				int valoracionOrden = rs.getInt("valoracionOrden");
				int valoracionImpulsividad = rs.getInt("valoracionImpulsividad");
				int valoracionConstancia = rs.getInt("valoracionConstancia");
				int valoracionTolerancia = rs.getInt("valoracionTolerancia");
				int valoracionControlPrepotencia = rs.getInt("valoracionControlPrepotencia");
				int valoracionHonestidad = rs.getInt("valoracionHonestidad");
				int valoracionAceptacion = rs.getInt("valoracionAceptacion");
				int valoracionConsecucionObjetivos = rs.getInt("valoracionConsecucionObjetivos");

				// Create a new PersonalRatings object and add it to the list
				PersonalRatings ratings = new PersonalRatings(valoracionDisciplina, valoracionOrden,
						valoracionImpulsividad, valoracionConstancia, valoracionTolerancia,
						valoracionControlPrepotencia, valoracionHonestidad, valoracionAceptacion,
						valoracionConsecucionObjetivos);
				
		        personalRatingsMap.put(date, ratings);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personalRatingsMap;
	}
}
