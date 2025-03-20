package com.selekode.topaz.repository;

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

public interface JournalRepository {
	public static final String DB_URL = "jdbc:sqlite:src/main/resources/database/topazdatabase.db";

	public static List<JournalEntry> selectAllJournalEntries() {
		// SQL query to retrieve data from the table, ordered by date descending
		String sql = "SELECT id, date, title, content FROM journal ORDER BY date DESC";

		List<JournalEntry> journalEntries = new ArrayList<>();

		// Connect to Database
		try (Connection conn = DriverManager.getConnection(DB_URL);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			// Loop through the result set and add each row to the list
			while (rs.next()) {
				int id = rs.getInt("id");
				long date = rs.getLong("date");
				String title = rs.getString("title");
				String content = rs.getString("content");

				// Convert date from UNIX time to String
				String dateStr = convertDateToString_ddMMMyyy(date);

				// Create a new JournalEntry object and add it to the list
				JournalEntry journalEntry = new JournalEntry(id, dateStr, title, content);
				journalEntries.add(journalEntry);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("TopazRepository: Selected all rows from table journal");

		return journalEntries;
	}

	public static JournalEntry selectJournalEntry(Long id) {
		JournalEntry journalEntry = null;
		String query = "SELECT id, date, title, content FROM journal WHERE id = ?";

		try (Connection connection = DriverManager.getConnection(DB_URL);
				PreparedStatement statement = connection.prepareStatement(query)) {

			// Set the ID parameter
			statement.setLong(1, id);

			// Execute the query
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					// Create a journalEntry object and load it with the data from the DB
					journalEntry = new JournalEntry(0, "", "", "");
					journalEntry.setId(resultSet.getInt("id"));
					journalEntry.setDate(resultSet.getString("date"));
					journalEntry.setTitle(resultSet.getString("title"));
					journalEntry.setContent(resultSet.getString("content"));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("TopazRepository: Selected row (ID: " + id + ") of table journal successfully");

		return journalEntry;
	}

	public static void insertJournalEntry(JournalEntry journalEntry, Long unixTime) {
		try (Connection conn = DriverManager.getConnection(DB_URL)) {
			String sql = "INSERT INTO journal (date, title, content) VALUES (?, ?, ?)";
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setLong(1, unixTime);
				pstmt.setString(2, journalEntry.getTitle());
				pstmt.setString(3, journalEntry.getContent());
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("TopazRepository: Inserted row into table journal successfully");

	}

	public static void updateJournalEntry(Long id, JournalEntry journalEntry) {
		String updateQuery = "UPDATE journal SET title = ?, content = ? WHERE id = ?";

		try (Connection connection = DriverManager.getConnection(DB_URL);
				PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

			// Set values from the journalEntry object to the PreparedStatement
			preparedStatement.setString(1, journalEntry.getTitle());
			preparedStatement.setString(2, journalEntry.getContent());
			preparedStatement.setLong(3, journalEntry.getId());

			// Execute the update and get the number of affected rows
			int affectedRows = preparedStatement.executeUpdate();

			if (affectedRows > 0) {
				// System.out.println("TopazRepositry: Number of affected rows >0");
			}
			;

		} catch (SQLException e) {
			e.printStackTrace(); // Print any exceptions for debugging
		}
		System.out.println("TopazRepository: Updated row (ID: " + id + ") of table journal successfully");

	}

	public static void deleteJournalEntry(Long id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// Establishing a connection to the database
			connection = DriverManager.getConnection(DB_URL);

			// SQL query to delete the journal entry by ID
			String sql = "DELETE FROM journal WHERE id = ?";

			// Create a PreparedStatement to execute the query
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id); // Set the ID parameter

			// Execute the query
			int rowsAffected = preparedStatement.executeUpdate();

			// Check if the deletion was successful
			if (rowsAffected > 0) {
				System.out.println("TopazRepository: Deleted row (ID: " + id + ") of table journal successfully");

			} else {
				System.out.println("No journal entry found with ID " + id);
			}
		} catch (SQLException e) {
			System.err.println("Error deleting journal entry: " + e.getMessage());
		} finally {
			// Close resources to avoid memory leaks
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.err.println("Error closing resources: " + e.getMessage());
			}
		}
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
