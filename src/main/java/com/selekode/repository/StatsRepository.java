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

public interface StatsRepository {
	public static final String DB_URL = "jdbc:sqlite:src/main/resources/database/topazdatabase.db";

	public static int getJournalEntryCount() {
		int journalEntryCount = 0;

		 // SQL query to count rows in the 'journal' table
        String query = "SELECT COUNT(*) FROM journal";

        // Database connection
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(query);
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

	public static int getRevisionEntryCount() {
		int revisionEntryCount = 0;

		 // SQL query to count rows in the 'journal' table
        String query = "SELECT COUNT(*) FROM revision";

        // Database connection
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(query);
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

	
	public static String convertDateToString_ddMMMyyy(long date) {
		// Convert the Unix timestamp (milliseconds) to an Instant
		Instant instant = Instant.ofEpochSecond(date);

		// Define the desired format (DD-MMM-YYYY)
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a").withZone(ZoneId.systemDefault());
		String dateStr = formatter.format(instant);

		return dateStr;
	}

}
