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
import java.util.List;

import com.selekode.topaz.model.JournalEntry;
import com.selekode.topaz.model.RevisionEntry;
import com.selekode.topaz.model.StatsEntryCount;
import com.selekode.topaz.model.StatsActivityPerDayOfWeek;

public interface StatsRepository {
	public static final String DB_URL = "jdbc:sqlite:src/main/resources/database/topazdatabase.db";

	public static String convertDateToString_ddMMMyyy(long date) {
		// Convert the Unix timestamp (milliseconds) to an Instant
		Instant instant = Instant.ofEpochSecond(date);

		// Define the desired format (DD-MMM-YYYY)
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a")
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

}
