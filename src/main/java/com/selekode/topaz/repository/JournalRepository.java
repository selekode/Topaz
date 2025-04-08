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

import com.selekode.topaz.database.DatabaseConstants;
import com.selekode.topaz.model.JournalEntry;
import com.selekode.topaz.model.RevisionEntry;

public interface JournalRepository {
	public static final String DB_URL = DatabaseConstants.DB_URL;

	public static List<JournalEntry> selectAllJournalEntries() {
		// SQL query to retrieve data from the table, ordered by date descending
		String sql = "SELECT id, date, title, contentGeneral, contentSaludFisica, contentBienestarMental, contentRelacionesSociales, "
				+ "contentCarreraProfesional, contentEstabilidadFinanciera, contentCrecimientoPersonal, contentPasatiemposCreatividad, "
				+ "contentEspiritualidadProposito, contentRecreacionDiversion, contentContribucionLegado,contentErroresCometidos FROM journal ORDER BY date DESC";

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
				String contentGeneral = rs.getString("contentGeneral");
				String contentSaludFisica = rs.getString("contentSaludFisica");
				String contentBienestarMental = rs.getString("contentBienestarMental");
				String contentRelacionesSociales = rs.getString("contentRelacionesSociales");
				String contentCarreraProfesional = rs.getString("contentCarreraProfesional");
				String contentEstabilidadFinanciera = rs.getString("contentEstabilidadFinanciera");
				String contentCrecimientoPersonal = rs.getString("contentCrecimientoPersonal");
				String contentPasatiemposCreatividad = rs.getString("contentPasatiemposCreatividad");
				String contentEspiritualidadProposito = rs.getString("contentEspiritualidadProposito");
				String contentRecreacionDiversion = rs.getString("contentRecreacionDiversion");
				String contentContribucionLegado = rs.getString("contentContribucionLegado");
				String contentErroresCometidos = rs.getString("contentErroresCometidos");

				// Convert date from UNIX time to String
				String dateStr = convertDateToString_ddMMMyyy(date);

				// Create a new JournalEntry object and set all content fields
				JournalEntry journalEntry = new JournalEntry(id, dateStr, title, contentGeneral, contentSaludFisica,
						contentBienestarMental, contentRelacionesSociales, contentCarreraProfesional,
						contentEstabilidadFinanciera, contentCrecimientoPersonal, contentPasatiemposCreatividad,
						contentEspiritualidadProposito, contentRecreacionDiversion, contentContribucionLegado,
						contentErroresCometidos);
				// Add the journal entry to the list
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
		String query = "SELECT id, date, title, contentGeneral, contentSaludFisica, contentBienestarMental, contentRelacionesSociales, "
				+ "contentCarreraProfesional, contentEstabilidadFinanciera, contentCrecimientoPersonal, contentPasatiemposCreatividad, "
				+ "contentEspiritualidadProposito, contentRecreacionDiversion, contentContribucionLegado, contentErroresCometidos FROM journal WHERE id = ?";

		try (Connection connection = DriverManager.getConnection(DB_URL);
				PreparedStatement statement = connection.prepareStatement(query)) {

			// Set the ID parameter
			statement.setLong(1, id);

			// Execute the query
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					// Create a journalEntry object and load it with the data from the DB
					journalEntry = new JournalEntry(0, "", "", "", "", "", "", "", "", "", "", "", "", "", "");
					journalEntry.setId(resultSet.getInt("id"));
					journalEntry.setDate(resultSet.getString("date"));
					journalEntry.setTitle(resultSet.getString("title"));
					journalEntry.setContentGeneral(resultSet.getString("contentGeneral"));
					journalEntry.setContentSaludFisica(resultSet.getString("contentSaludFisica"));
					journalEntry.setContentBienestarMental(resultSet.getString("contentBienestarMental"));
					journalEntry.setContentRelacionesSociales(resultSet.getString("contentRelacionesSociales"));
					journalEntry.setContentCarreraProfesional(resultSet.getString("contentCarreraProfesional"));
					journalEntry.setContentEstabilidadFinanciera(resultSet.getString("contentEstabilidadFinanciera"));
					journalEntry.setContentCrecimientoPersonal(resultSet.getString("contentCrecimientoPersonal"));
					journalEntry.setContentPasatiemposCreatividad(resultSet.getString("contentPasatiemposCreatividad"));
					journalEntry
							.setContentEspiritualidadProposito(resultSet.getString("contentEspiritualidadProposito"));
					journalEntry.setContentRecreacionDiversion(resultSet.getString("contentRecreacionDiversion"));
					journalEntry.setContentContribucionLegado(resultSet.getString("contentContribucionLegado"));
					journalEntry.setContentErroresCometidos(resultSet.getString("contentErroresCometidos"));
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
			String sql = "INSERT INTO journal (date, title, contentGeneral, contentSaludFisica, contentBienestarMental, contentRelacionesSociales, "
					+ "contentCarreraProfesional, contentEstabilidadFinanciera, contentCrecimientoPersonal, contentPasatiemposCreatividad, "
					+ "contentEspiritualidadProposito, contentRecreacionDiversion, contentContribucionLegado, contentErroresCometidos) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; // Fix: Now 13 placeholders

			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				System.out.println("Setting data");

				pstmt.setLong(1, unixTime);
				pstmt.setString(2, journalEntry.getTitle());
				pstmt.setString(3, journalEntry.getContentGeneral());
				pstmt.setString(4, journalEntry.getContentSaludFisica());
				pstmt.setString(5, journalEntry.getContentBienestarMental());
				pstmt.setString(6, journalEntry.getContentRelacionesSociales());
				pstmt.setString(7, journalEntry.getContentCarreraProfesional());
				pstmt.setString(8, journalEntry.getContentEstabilidadFinanciera());
				pstmt.setString(9, journalEntry.getContentCrecimientoPersonal());
				pstmt.setString(10, journalEntry.getContentPasatiemposCreatividad());
				pstmt.setString(11, journalEntry.getContentEspiritualidadProposito());
				pstmt.setString(12, journalEntry.getContentRecreacionDiversion());
				pstmt.setString(13, journalEntry.getContentContribucionLegado());
				pstmt.setString(14, journalEntry.getContentErroresCometidos());
				pstmt.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateJournalEntry(Long id, JournalEntry journalEntry) {
		String updateQuery = "UPDATE journal SET title = ?, contentGeneral = ?, contentSaludFisica = ?, contentBienestarMental = ?, "
				+ "contentRelacionesSociales = ?, contentCarreraProfesional = ?, contentEstabilidadFinanciera = ?, contentCrecimientoPersonal = ?, "
				+ "contentPasatiemposCreatividad = ?, contentEspiritualidadProposito = ?, contentRecreacionDiversion = ?, contentContribucionLegado = ?, contentErroresCometidos = ? "
				+ "WHERE id = ?";

		try (Connection connection = DriverManager.getConnection(DB_URL);
				PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

			// Set values from the journalEntry object to the PreparedStatement
			preparedStatement.setString(1, journalEntry.getTitle());
			preparedStatement.setString(2, journalEntry.getContentGeneral());
			preparedStatement.setString(3, journalEntry.getContentSaludFisica());
			preparedStatement.setString(4, journalEntry.getContentBienestarMental());
			preparedStatement.setString(5, journalEntry.getContentRelacionesSociales());
			preparedStatement.setString(6, journalEntry.getContentCarreraProfesional());
			preparedStatement.setString(7, journalEntry.getContentEstabilidadFinanciera());
			preparedStatement.setString(8, journalEntry.getContentCrecimientoPersonal());
			preparedStatement.setString(9, journalEntry.getContentPasatiemposCreatividad());
			preparedStatement.setString(10, journalEntry.getContentEspiritualidadProposito());
			preparedStatement.setString(11, journalEntry.getContentRecreacionDiversion());
			preparedStatement.setString(12, journalEntry.getContentContribucionLegado());
			preparedStatement.setString(13, journalEntry.getContentErroresCometidos());
			preparedStatement.setLong(14, journalEntry.getId());

			// Execute the update and get the number of affected rows
			int affectedRows = preparedStatement.executeUpdate();

			if (affectedRows > 0) {
				// System.out.println("TopazRepository: Number of affected rows > 0");
			}

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
		Instant instant = Instant.ofEpochSecond(date);

		// Define the desired format (DD-MMM-YYYY)
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a")
				.withZone(ZoneId.systemDefault());
		String dateStr = formatter.format(instant);

		return dateStr;
	}
}
