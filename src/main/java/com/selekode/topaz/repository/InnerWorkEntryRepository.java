package com.selekode.topaz.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.selekode.topaz.database.DatabaseConstants;
import com.selekode.topaz.model.InnerWorkEntry;
import com.selekode.topaz.model.JournalEntry;
import com.selekode.topaz.utils.DatesHelper;

public class InnerWorkEntryRepository {
	public static final String DB_URL = DatabaseConstants.DB_URL;

	public static List<InnerWorkEntry> selectAllInnerWorkEntries() {
		String sql = "SELECT * FROM inner_work_entry ORDER BY date DESC";
		List<InnerWorkEntry> innerWorkEntries = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(DB_URL);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				int id = rs.getInt("id");
				long date = rs.getLong("date");
				Integer tagID = rs.getInt("tag_id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				
				// Convert date from UNIX time to String
				String dateStr = DatesHelper.convertDateToString_ddMMMyyy(date);
				
				InnerWorkEntry innerWorkEntry = new InnerWorkEntry(id, dateStr, tagID,title, content);
				innerWorkEntries.add(innerWorkEntry);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return innerWorkEntries;
	}
	
	public static InnerWorkEntry selectInnerWorkEntry(int id) {
		InnerWorkEntry innerWorkEntry = null;
		String query = "SELECT id,date, tag_id, title, content FROM inner_work_entry WHERE id = ?";

		try (Connection connection = DriverManager.getConnection(DB_URL);
				PreparedStatement statement = connection.prepareStatement(query)) {

			// Set the ID parameter
			statement.setLong(1, id);

			// Execute the query
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					innerWorkEntry = new InnerWorkEntry(0, "", 0, "", "");
					innerWorkEntry.setId(resultSet.getInt("id"));
					innerWorkEntry.setTagID(resultSet.getInt("tag_id"));
					innerWorkEntry.setDate(resultSet.getString("date"));
					innerWorkEntry.setTitle(resultSet.getString("title"));
					innerWorkEntry.setContent(resultSet.getString("content"));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("TopazRepository: Selected row (ID: " + id + ") of table inner_work_entry successfully");

		return innerWorkEntry;
	}
	
	public static void insertInnerWorkEntry(InnerWorkEntry entry, Long unixTime) {
		try (Connection conn = DriverManager.getConnection(DB_URL)) {
			String sql = "INSERT INTO inner_work_entry (date, tag_id, title, content) "
					+ "VALUES (?, ?, ?, ?)";

			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				System.out.println("Setting data");

				pstmt.setLong(1, unixTime);
				pstmt.setInt(2, entry.getTagID());
				pstmt.setString(3, entry.getTitle());
				pstmt.setString(4, entry.getContent());
				pstmt.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateInnerWorkEntry(int id, InnerWorkEntry entry) {
		String updateQuery = "UPDATE inner_work_entry SET title = ?, tag_id = ?, content = ? WHERE id = ?";

		try (Connection connection = DriverManager.getConnection(DB_URL);
				PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

			preparedStatement.setString(1, entry.getTitle());
			preparedStatement.setInt(2, entry.getTagID());
			preparedStatement.setString(3, entry.getContent());
			preparedStatement.setInt(4, id);
			System.out.println("Updated entry with: ID: " + id + ", Title: " + entry.getTitle() + ", TagID: " + entry.getTagID() + ", Content: " + entry.getContent());
			
			// Execute the update and get the number of affected rows
			int affectedRows = preparedStatement.executeUpdate();

			if (affectedRows > 0) {
				// System.out.println("TopazRepository: Number of affected rows > 0");
			}

		} catch (SQLException e) {
			e.printStackTrace(); // Print any exceptions for debugging
		}
		System.out.println("TopazRepository: Updated row (ID: " + id + ") of table inner_work_entry successfully");
	}
	
	public static void deleteInnerWorkEntry(int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// Establishing a connection to the database
			connection = DriverManager.getConnection(DB_URL);

			// SQL query to delete the journal entry by ID
			String sql = "DELETE FROM inner_work_entry WHERE id = ?";

			// Create a PreparedStatement to execute the query
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id); // Set the ID parameter

			// Execute the query
			int rowsAffected = preparedStatement.executeUpdate();

			// Check if the deletion was successful
			if (rowsAffected > 0) {
				System.out.println("TopazRepository: Deleted row (ID: " + id + ") of table inner_work_entry successfully");

			} else {
				System.out.println("No inner_work_entry entry found with ID " + id);
			}
		} catch (SQLException e) {
			System.err.println("Error deleting inner_work_entry entry: " + e.getMessage());
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
}
