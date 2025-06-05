package com.selekode.topaz.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.selekode.topaz.model.InnerWorkEntry;
import com.selekode.topaz.model.InnerWorkTag;
import com.selekode.topaz.database.DatabaseConstants;

public class InnerWorkTagRepository {
	public static final String DB_URL = DatabaseConstants.DB_URL;

	public static List<InnerWorkTag> selectAllInnerWorkTags() {
		// String SQL
		String sql = "SELECT * FROM inner_work_tags;";

		List<InnerWorkTag> innerWorkTags = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(DB_URL);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");

				InnerWorkTag innerWorkTag = new InnerWorkTag(id, name);
				innerWorkTags.add(innerWorkTag);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		// Add tags from db to list

		return innerWorkTags;

	}

	public static InnerWorkTag selectInnerWorkTag(int id) {
		InnerWorkTag innerWorkTag = null;

		// String SQL
		String sql = "SELECT id,name FROM inner_work_tags WHERE id = ?";
		
		try (Connection connection = DriverManager.getConnection(DB_URL);
				PreparedStatement statement = connection.prepareStatement(sql)) {

			// Set the ID parameter
			statement.setInt(1, id);

			// Execute the query
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					innerWorkTag = new InnerWorkTag(0, "");
					innerWorkTag.setId(resultSet.getInt("id"));
					innerWorkTag.setName(resultSet.getString("name"));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("TopazRepository: Selected row (ID: " + id + ") of table inner_work_tags successfully");
		
		return innerWorkTag;
	}

	public static void insertInnerWorkTag(InnerWorkTag tag) {
		try (Connection conn = DriverManager.getConnection(DB_URL)) {
			String sql = "INSERT INTO inner_work_tags (name) " + "VALUES (?)";

			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				System.out.println("Setting data");

				pstmt.setString(1, tag.getName());

				pstmt.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateInnerWorkTag(int id, InnerWorkTag tag) {
	    String updateQuery = "UPDATE inner_work_tags SET name = ? WHERE id = ?";

	    try (Connection connection = DriverManager.getConnection(DB_URL);
	         PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

	        preparedStatement.setString(1, tag.getName());
	        preparedStatement.setInt(2, id);

	        int affectedRows = preparedStatement.executeUpdate();

	        if (affectedRows > 0) {
	            // Update successful
	        }

	    } catch (SQLException e) {
	        e.printStackTrace(); // Debug
	    }
	}


	public static void deleteInnerWorkTag(int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// Establishing a connection to the database
			connection = DriverManager.getConnection(DB_URL);

			// SQL query to delete the journal entry by ID
			String sql = "DELETE FROM inner_work_tags WHERE id = ?";

			// Create a PreparedStatement to execute the query
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id); // Set the ID parameter

			// Execute the query
			int rowsAffected = preparedStatement.executeUpdate();

			// Check if the deletion was successful
			if (rowsAffected > 0) {
				System.out
						.println("TopazRepository: Deleted row (ID: " + id + ") of table inner_work_tags successfully");

			} else {
				System.out.println("No inner_work_tags entry found with ID " + id);
			}
		} catch (SQLException e) {
			System.err.println("Error deleting inner_work_tags tag: " + e.getMessage());
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
