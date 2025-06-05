package com.selekode.topaz.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.selekode.topaz.database.DatabaseConstants;

public class DashboardRepository {
	public static final String DB_URL = DatabaseConstants.DB_URL;

	public static int getCurrentJournalStreak() {
		String sql = "WITH Streak AS (SELECT date(date, 'unixepoch') AS entry_date, julianday(date(date, 'unixepoch')) - ROW_NUMBER() OVER (ORDER BY date(date, 'unixepoch')) AS streak_group FROM journal GROUP BY entry_date), LatestDate AS (SELECT MAX(date(date, 'unixepoch')) AS last_entry_date FROM journal), ValidStreak AS (SELECT streak_group FROM Streak, LatestDate WHERE entry_date = last_entry_date AND last_entry_date >= date('now', '-1 day')) SELECT COALESCE((SELECT COUNT(*) FROM Streak WHERE streak_group = (SELECT streak_group FROM ValidStreak)), 0) AS streak_days;";

		int journalStreak = 0; // Default value

		try (Connection conn = DriverManager.getConnection(DB_URL);
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			if (rs.next()) {
				journalStreak = rs.getInt("streak_days");
			}

		} catch (SQLException e) {
			e.printStackTrace(); // Handle exception properly in production
		}

		return journalStreak;
	}

	public static int getCurrentRevisionStreak() {
		String sql = "WITH Streak AS (SELECT date(date, 'unixepoch') AS entry_date, julianday(date(date, 'unixepoch')) - ROW_NUMBER() OVER (ORDER BY date(date, 'unixepoch')) AS streak_group FROM revision GROUP BY entry_date), LatestDate AS (SELECT MAX(date(date, 'unixepoch')) AS last_entry_date FROM journal), ValidStreak AS (SELECT streak_group FROM Streak, LatestDate WHERE entry_date = last_entry_date AND last_entry_date >= date('now', '-1 day')) SELECT COALESCE((SELECT COUNT(*) FROM Streak WHERE streak_group = (SELECT streak_group FROM ValidStreak)), 0) AS streak_days;";


		int revisionStreak = 0; // Default value

		try (Connection conn = DriverManager.getConnection(DB_URL);
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			if (rs.next()) {
				revisionStreak = rs.getInt("streak_days");
			}

		} catch (SQLException e) {
			e.printStackTrace(); // Handle exception properly in production
		}

		return revisionStreak;
	}

	public static int getLongestJournalStreak() {
		String sql = """
				    WITH Streak AS (
				        SELECT
				            date(date, 'unixepoch') AS entry_date,
				            julianday(date(date, 'unixepoch')) - ROW_NUMBER() OVER (ORDER BY date(date, 'unixepoch')) AS streak_group
				        FROM journal
				        GROUP BY entry_date
				    )
				    SELECT COALESCE(
				        (SELECT MAX(streak_count) FROM (
				            SELECT streak_group, COUNT(*) AS streak_count
				            FROM Streak
				            GROUP BY streak_group
				        )),
				        0
				    ) AS longest_streak;
				""";

		int longestJournalStreak = 0; // Default value

		try (Connection conn = DriverManager.getConnection(DB_URL);
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			if (rs.next()) {
				longestJournalStreak = rs.getInt("longest_streak");
			}

		} catch (SQLException e) {
			e.printStackTrace(); // Handle exception properly in production
		}

		return longestJournalStreak;
	}

	public static int getLongestRevisionStreak() {
		String sql = """
				    WITH Streak AS (
				        SELECT
				            date(date, 'unixepoch') AS entry_date,
				            julianday(date(date, 'unixepoch')) - ROW_NUMBER() OVER (ORDER BY date(date, 'unixepoch')) AS streak_group
				        FROM revision
				        GROUP BY entry_date
				    )
				    SELECT COALESCE(
				        (SELECT MAX(streak_count) FROM (
				            SELECT streak_group, COUNT(*) AS streak_count
				            FROM Streak
				            GROUP BY streak_group
				        )),
				        0
				    ) AS longest_streak;
				""";

		int longestRevisionStreak = 0; // Default value

		try (Connection conn = DriverManager.getConnection(DB_URL);
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			if (rs.next()) {
				longestRevisionStreak = rs.getInt("longest_streak");
			}

		} catch (SQLException e) {
			e.printStackTrace(); // Handle exception properly in production
		}

		return longestRevisionStreak;
	}

	public static boolean getJournalIsWrittenToday() {
		String sql = """
				    SELECT COUNT(*)
				    FROM journal
				    WHERE date(date, 'unixepoch') = date('now');
				""";

		boolean isWrittenToday = false;

		try (Connection conn = DriverManager.getConnection(DB_URL);
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			if (rs.next()) {
				isWrittenToday = rs.getInt(1) > 0;
			}

		} catch (SQLException e) {
			e.printStackTrace(); // Handle exception properly in production
		}

		return isWrittenToday;
	}

	public static boolean getRevisionIsWrittenToday() {
		String sql = """
				    SELECT COUNT(*)
				    FROM revision
				    WHERE date(date, 'unixepoch') = date('now');
				""";

		boolean isWrittenToday = false;

		try (Connection conn = DriverManager.getConnection(DB_URL);
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			if (rs.next()) {
				isWrittenToday = rs.getInt(1) > 0;
			}

		} catch (SQLException e) {
			e.printStackTrace(); // Handle exception properly in production
		}

		return isWrittenToday;
	}
}
