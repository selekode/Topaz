package com.selekode.topaz.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.selekode.topaz.model.Table;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class DashboardRepository {

	@Value("${spring.datasource.url}")
	private String datasourceUrl;

	public boolean getIsWrittenToday(Table table) {
		boolean isWrittenToday = false;
		String sql = String.format("""
                SELECT COUNT(*)
                FROM %s
                WHERE date("date", 'unixepoch', 'localtime') = date('now', 'localtime');
            """, table.getDbName());

		try (Connection conn = DriverManager.getConnection(datasourceUrl);
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 ResultSet rs = pstmt.executeQuery()) {

			if (rs.next()) {
				isWrittenToday = rs.getInt(1) > 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("Hoy, " + table.getDbName() + " es " + isWrittenToday);
		return isWrittenToday;
	}

	public int getCurrentStreak(Table table) {
		String sql = String.format("""
                WITH Streak AS (
                    SELECT 
                        date("date", 'unixepoch') AS entry_date,
                        julianday(date("date", 'unixepoch')) - ROW_NUMBER() OVER (ORDER BY date("date", 'unixepoch')) AS streak_group
                    FROM %s
                    GROUP BY entry_date
                ),
                LatestDate AS (
                    SELECT MAX(date("date", 'unixepoch')) AS last_entry_date FROM %s
                ),
                ValidStreak AS (
                    SELECT streak_group FROM Streak, LatestDate
                    WHERE entry_date = last_entry_date AND last_entry_date >= date('now', '-1 day')
                )
                SELECT COALESCE((
                    SELECT COUNT(*) FROM Streak WHERE streak_group = (SELECT streak_group FROM ValidStreak)
                ), 0) AS streak_days;
            """, table.getDbName(), table.getDbName());

		int streak = 0;
		try (Connection conn = DriverManager.getConnection(datasourceUrl);
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 ResultSet rs = pstmt.executeQuery()) {

			if (rs.next()) {
				streak = rs.getInt("streak_days");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return streak;
	}

	public int getLongestStreak(Table table) {
		String sql = String.format("""
                WITH Streak AS (
                    SELECT
                        date("date", 'unixepoch') AS entry_date,
                        julianday(date("date", 'unixepoch')) - ROW_NUMBER() OVER (ORDER BY date("date", 'unixepoch')) AS streak_group
                    FROM %s
                    GROUP BY entry_date
                )
                SELECT COALESCE((
                    SELECT MAX(streak_count) FROM (
                        SELECT streak_group, COUNT(*) AS streak_count FROM Streak GROUP BY streak_group
                    )
                ), 0) AS longest_streak;
            """, table.getDbName());

		int streak = 0;
		try (Connection conn = DriverManager.getConnection(datasourceUrl);
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 ResultSet rs = pstmt.executeQuery()) {

			if (rs.next()) {
				streak = rs.getInt("longest_streak");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return streak;
	}
}
