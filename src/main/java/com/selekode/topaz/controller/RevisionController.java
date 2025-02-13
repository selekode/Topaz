package com.selekode.topaz.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import java.sql.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import com.selekode.topaz.model.RevisionEntry;

@RequestMapping("/revision")
@Controller
public class RevisionController {
	private static final String DB_URL = "jdbc:sqlite:src/main/resources/database/topazdatabase.db";

	@GetMapping("/load")
	public String loadPage(Model model) {
		List<RevisionEntry> revisionEntries = loadAllEntries();
		model.addAttribute("revisionEntries", revisionEntries); // Add revisionEntries to the model

		return "revision"; // Load revision.html
	}

	public List<RevisionEntry> loadAllEntries() {
		// SQL query to retrieve data from the table, ordered by date descending
		String sql = "SELECT id, date, estadoEmocional, estadoEmocionalWhy, importanteParaMi, aprendidoSobreMi, valoracionDisciplina, valoracionOrden, valoracionImpulsividad, valoracionConstancia, valoracionTolerancia, valoracionControlPrepotencia, valoracionHonestidad, valoracionAceptacion, valoracionConsecucionObjetivos, explicacionValoracion, objetivosPersonales FROM revision ORDER BY date DESC";

		List<RevisionEntry> revisionEntries = new ArrayList<>();

		// Connect to Database
		try (Connection conn = DriverManager.getConnection(DB_URL);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			// Loop through the result set and add each row to the list
			while (rs.next()) {
				int id = rs.getInt("id");
				long date = rs.getLong("date");
				String estadoEmocional = rs.getString("estadoEmocional");
				String estadoEmocionalWhy = rs.getString("estadoEmocionalWhy");
				String importanteParaMi = rs.getString("importanteParaMi");
				String aprendidoSobreMi = rs.getString("aprendidoSobreMi");
				int valoracionDisciplina = rs.getInt("valoracionDisciplina");
				int valoracionOrden = rs.getInt("valoracionOrden");
				int valoracionImpulsividad = rs.getInt("valoracionImpulsividad");
				int valoracionConstancia = rs.getInt("valoracionConstancia");
				int valoracionTolerancia = rs.getInt("valoracionTolerancia");
				int valoracionControlPrepotencia = rs.getInt("valoracionControlPrepotencia");
				int valoracionHonestidad = rs.getInt("valoracionHonestidad");
				int valoracionAceptacion = rs.getInt("valoracionAceptacion");
				int valoracionConsecucionObjetivos = rs.getInt("valoracionConsecucionObjetivos");
				String explicacionValoracion = rs.getString("explicacionValoracion");
				String objetivosPersonales = rs.getString("objetivosPersonales");

				// Convert date from UNIX time to String
				String dateStr = convertDateToString(date);

				// Create a new RevisionEntry object and add it to the list
				RevisionEntry revisionEntry = new RevisionEntry(id, dateStr, estadoEmocional, estadoEmocionalWhy,
						importanteParaMi, aprendidoSobreMi, valoracionDisciplina, valoracionOrden,
						valoracionImpulsividad, valoracionConstancia, valoracionTolerancia,
						valoracionControlPrepotencia, valoracionHonestidad, valoracionAceptacion,
						valoracionConsecucionObjetivos, explicacionValoracion, objetivosPersonales);
				revisionEntries.add(revisionEntry);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("Selected all rows from table revision");

		return revisionEntries;
	}

	@PostMapping("/deleteEntry")
	public String deleteEntry(@RequestParam("id") Long id) {
		System.out.println("About to delete row with id: " + id);
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// Establishing a connection to the database
			connection = DriverManager.getConnection(DB_URL);

			// SQL query to delete the journal entry by ID
			String sql = "DELETE FROM revision WHERE id = ?";

			// Create a PreparedStatement to execute the query
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id); // Set the ID parameter

			// Execute the query
			int rowsAffected = preparedStatement.executeUpdate();

			// Check if the deletion was successful
			if (rowsAffected > 0) {
				System.out.println("Revision entry with ID " + id + " was deleted successfully.");
			} else {
				System.out.println("No revision entry found with ID " + id);
			}
		} catch (SQLException e) {
			System.err.println("Error deleting revision entry: " + e.getMessage());
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

		return "redirect_revision";
	}

	public String convertDateToString(long date) {
		// Convert the Unix timestamp (milliseconds) to an Instant
		Instant instant = Instant.ofEpochSecond(date);

		// Define the desired format (DD-MMM-YYYY)
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy").withZone(ZoneId.systemDefault());
		String dateStr = formatter.format(instant);

		return dateStr;
	}
}
