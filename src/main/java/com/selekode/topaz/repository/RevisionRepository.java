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

import com.selekode.topaz.config.DatabaseConfig;
import com.selekode.topaz.model.RevisionEntry;
import com.selekode.topaz.service.RevisionService;

public interface RevisionRepository {
	public static final String DB_URL = DatabaseConfig.DB_URL;
	
	public static List<RevisionEntry> selectAllRevisionEntries() {
		// SQL query to retrieve data from the table, ordered by date descending
		String sql = "SELECT id, date, estadoEmocional, estadoEmocionalWhy, importanteParaMi, "
				+ "aprendidoSobreMi, valoracionDisciplina, valoracionOrden, valoracionImpulsividad, "
				+ "valoracionConstancia, valoracionTolerancia, valoracionControlPrepotencia, "
				+ "valoracionHonestidad, valoracionAceptacion, valoracionConsecucionObjetivos, "
				+ "explicacionValoracion, objetivosPersonales, "
				+ "emocionAlegria, emocionTristeza, emocionIra, emocionMiedo, "
				+ "emocionAnsiedad, emocionAmor, emocionSorpresa, emocionVerguenza, "
				+ "emocionFrustracion, emocionSatisfaccion, emocionAburrimiento, "
				+ "emocionSerenidad, emocionConfianza, emocionAbrumado, emocionEsperanza "
				+ "FROM revision ORDER BY date DESC";

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

				// Retrieve the emotion booleans
				boolean emocionAlegria = rs.getBoolean("emocionAlegria");
				boolean emocionTristeza = rs.getBoolean("emocionTristeza");
				boolean emocionIra = rs.getBoolean("emocionIra");
				boolean emocionMiedo = rs.getBoolean("emocionMiedo");
				boolean emocionAnsiedad = rs.getBoolean("emocionAnsiedad");
				boolean emocionAmor = rs.getBoolean("emocionAmor");
				boolean emocionSorpresa = rs.getBoolean("emocionSorpresa");
				boolean emocionVerguenza = rs.getBoolean("emocionVerguenza");
				boolean emocionFrustracion = rs.getBoolean("emocionFrustracion");
				boolean emocionSatisfaccion = rs.getBoolean("emocionSatisfaccion");
				boolean emocionAburrimiento = rs.getBoolean("emocionAburrimiento");
				boolean emocionSerenidad = rs.getBoolean("emocionSerenidad");
				boolean emocionConfianza = rs.getBoolean("emocionConfianza");
				boolean emocionAbrumado = rs.getBoolean("emocionAbrumado");
				boolean emocionEsperanza = rs.getBoolean("emocionEsperanza");

				// Convert date from UNIX time to String
				String dateStr = RevisionRepository.convertDateToString_ddMMMyyy(date);

				// Create a new RevisionEntry object and add it to the list
				RevisionEntry revisionEntry = new RevisionEntry(id, dateStr, estadoEmocional, estadoEmocionalWhy,
						importanteParaMi, aprendidoSobreMi, valoracionDisciplina, valoracionOrden,
						valoracionImpulsividad, valoracionConstancia, valoracionTolerancia,
						valoracionControlPrepotencia, valoracionHonestidad, valoracionAceptacion,
						valoracionConsecucionObjetivos, explicacionValoracion, objetivosPersonales, emocionAlegria,
						emocionTristeza, emocionIra, emocionMiedo, emocionAnsiedad, emocionAmor, emocionSorpresa,
						emocionVerguenza, emocionFrustracion, emocionSatisfaccion, emocionAburrimiento, emocionSerenidad,
						emocionConfianza, emocionAbrumado, emocionEsperanza);
				revisionEntries.add(revisionEntry);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("TopazRepository: Selected all rows from table revision");

		return revisionEntries;
	}

	public static RevisionEntry selectRevisionEntry(Long id) {
		RevisionEntry revisionEntry = null;

		String query = "SELECT id, date, estadoEmocional, estadoEmocionalWhy, importanteParaMi, "
				+ "aprendidoSobreMi, valoracionDisciplina, valoracionOrden, valoracionImpulsividad, "
				+ "valoracionConstancia, valoracionTolerancia, valoracionControlPrepotencia, "
				+ "valoracionHonestidad, valoracionAceptacion, valoracionConsecucionObjetivos, "
				+ "explicacionValoracion, objetivosPersonales, emocionAlegria, emocionTristeza, "
				+ "emocionIra, emocionMiedo, emocionAnsiedad, emocionAmor, emocionSorpresa, "
				+ "emocionVerguenza, emocionFrustracion, emocionSatisfaccion, emocionAburrimiento, "
				+ "emocionSerenidad, emocionConfianza, emocionAbrumado, emocionEsperanza " + "FROM revision WHERE id = ?";

		try (Connection connection = DriverManager.getConnection(DB_URL);
				PreparedStatement statement = connection.prepareStatement(query)) {

			// Set the ID parameter
			statement.setLong(1, id);

			// Execute the query
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					// Create a revisionEntry object and load it with the data from the DB
					revisionEntry = new RevisionEntry(0, "", "", "", "", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, "", "", false,
							false, false, false, false, false, false, false, false, false, false, false, false, false,
							false);

					revisionEntry.setId(resultSet.getInt("id"));
					revisionEntry.setDate(resultSet.getString("date"));
					revisionEntry.setEstadoEmocional(resultSet.getString("estadoEmocional"));
					revisionEntry.setEstadoEmocionalWhy(resultSet.getString("estadoEmocionalWhy"));
					revisionEntry.setImportanteParaMi(resultSet.getString("importanteParaMi"));
					revisionEntry.setAprendidoSobreMi(resultSet.getString("aprendidoSobreMi"));
					revisionEntry.setValoracionDisciplina(resultSet.getInt("valoracionDisciplina"));
					revisionEntry.setValoracionOrden(resultSet.getInt("valoracionOrden"));
					revisionEntry.setValoracionImpulsividad(resultSet.getInt("valoracionImpulsividad"));
					revisionEntry.setValoracionConstancia(resultSet.getInt("valoracionConstancia"));
					revisionEntry.setValoracionTolerancia(resultSet.getInt("valoracionTolerancia"));
					revisionEntry.setValoracionControlPrepotencia(resultSet.getInt("valoracionControlPrepotencia"));
					revisionEntry.setValoracionHonestidad(resultSet.getInt("valoracionHonestidad"));
					revisionEntry.setValoracionAceptacion(resultSet.getInt("valoracionAceptacion"));
					revisionEntry.setValoracionConsecucionObjetivos(resultSet.getInt("valoracionConsecucionObjetivos"));
					revisionEntry.setExplicacionValoracion(resultSet.getString("explicacionValoracion"));
					revisionEntry.setObjetivosPersonales(resultSet.getString("objetivosPersonales"));

					// Set emotion fields
					revisionEntry.setEmocionAlegria(resultSet.getBoolean("emocionAlegria"));
					revisionEntry.setEmocionTristeza(resultSet.getBoolean("emocionTristeza"));
					revisionEntry.setEmocionIra(resultSet.getBoolean("emocionIra"));
					revisionEntry.setEmocionMiedo(resultSet.getBoolean("emocionMiedo"));
					revisionEntry.setEmocionAnsiedad(resultSet.getBoolean("emocionAnsiedad"));
					revisionEntry.setEmocionAmor(resultSet.getBoolean("emocionAmor"));
					revisionEntry.setEmocionSorpresa(resultSet.getBoolean("emocionSorpresa"));
					revisionEntry.setEmocionVerguenza(resultSet.getBoolean("emocionVerguenza"));
					revisionEntry.setEmocionFrustracion(resultSet.getBoolean("emocionFrustracion"));
					revisionEntry.setEmocionSatisfaccion(resultSet.getBoolean("emocionSatisfaccion"));
					revisionEntry.setEmocionAburrimiento(resultSet.getBoolean("emocionAburrimiento"));
					revisionEntry.setEmocionSerenidad(resultSet.getBoolean("emocionSerenidad"));
					revisionEntry.setEmocionConfianza(resultSet.getBoolean("emocionConfianza"));
					revisionEntry.setEmocionAbrumado(resultSet.getBoolean("emocionAbrumado"));
					revisionEntry.setEmocionEsperanza(resultSet.getBoolean("emocionEsperanza"));
				}

			}

			System.out.println("TopazRepository: Selected row (ID: " + id + ") of table revision successfully");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return revisionEntry;
	}

	public static void insertRevisionEntry(RevisionEntry revisionEntry, Long unixTime) {
		try (Connection conn = DriverManager.getConnection(DB_URL)) {
			String sql = "INSERT INTO revision (date, estadoEmocional, estadoEmocionalWhy, importanteParaMi, aprendidoSobreMi, valoracionDisciplina, valoracionOrden, valoracionImpulsividad, valoracionConstancia, valoracionTolerancia, valoracionControlPrepotencia, valoracionHonestidad, valoracionAceptacion, valoracionConsecucionObjetivos, explicacionValoracion, objetivosPersonales, emocionAlegria, emocionTristeza, emocionIra, emocionMiedo, emocionAnsiedad, emocionAmor, emocionSorpresa, emocionVerguenza, emocionFrustracion, emocionSatisfaccion, emocionAburrimiento, emocionSerenidad, emocionConfianza, emocionAbrumado, emocionEsperanza) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

				pstmt.setLong(1, unixTime);
				pstmt.setString(2, revisionEntry.getEstadoEmocional());
				pstmt.setString(3, revisionEntry.getEstadoEmocionalWhy());
				pstmt.setString(4, revisionEntry.getImportanteParaMi());
				pstmt.setString(5, revisionEntry.getAprendidoSobreMi());
				pstmt.setInt(6, revisionEntry.getValoracionDisciplina());
				pstmt.setInt(7, revisionEntry.getValoracionOrden());
				pstmt.setInt(8, revisionEntry.getValoracionImpulsividad());
				pstmt.setInt(9, revisionEntry.getValoracionConstancia());
				pstmt.setInt(10, revisionEntry.getValoracionTolerancia());
				pstmt.setInt(11, revisionEntry.getValoracionControlPrepotencia());
				pstmt.setInt(12, revisionEntry.getValoracionHonestidad());
				pstmt.setInt(13, revisionEntry.getValoracionAceptacion());
				pstmt.setInt(14, revisionEntry.getValoracionConsecucionObjetivos());
				pstmt.setString(15, revisionEntry.getExplicacionValoracion());
				pstmt.setString(16, revisionEntry.getObjetivosPersonales());

				// Set values for the emotion booleans
				pstmt.setBoolean(17, revisionEntry.isEmocionAlegria());
				pstmt.setBoolean(18, revisionEntry.isEmocionTristeza());
				pstmt.setBoolean(19, revisionEntry.isEmocionIra());
				pstmt.setBoolean(20, revisionEntry.isEmocionMiedo());
				pstmt.setBoolean(21, revisionEntry.isEmocionAnsiedad());
				pstmt.setBoolean(22, revisionEntry.isEmocionAmor());
				pstmt.setBoolean(23, revisionEntry.isEmocionSorpresa());
				pstmt.setBoolean(24, revisionEntry.isEmocionVerguenza());
				pstmt.setBoolean(25, revisionEntry.isEmocionFrustracion());
				pstmt.setBoolean(26, revisionEntry.isEmocionSatisfaccion());
				pstmt.setBoolean(27, revisionEntry.isEmocionAburrimiento());
				pstmt.setBoolean(28, revisionEntry.isEmocionSerenidad());
				pstmt.setBoolean(29, revisionEntry.isEmocionConfianza());
				pstmt.setBoolean(30, revisionEntry.isEmocionAbrumado());
				pstmt.setBoolean(31, revisionEntry.isEmocionEsperanza());

				pstmt.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("TopazRepository: Inserted row into table revision successfully");

	}

	public static void updateRevisionEntry(Long id, RevisionEntry revisionEntry) {
		String updateQuery = "UPDATE revision SET estadoEmocional = ?, estadoEmocionalWhy = ?, importanteParaMi = ?, "
				+ "aprendidoSobreMi = ?, valoracionDisciplina = ?, valoracionOrden = ?, valoracionImpulsividad = ?, "
				+ "valoracionConstancia = ?, valoracionTolerancia = ?, valoracionControlPrepotencia = ?, "
				+ "valoracionHonestidad = ?, valoracionAceptacion = ?, valoracionConsecucionObjetivos = ?, "
				+ "explicacionValoracion = ?, objetivosPersonales = ?, "
				+ "emocionAlegria = ?, emocionTristeza = ?, emocionIra = ?, emocionMiedo = ?, "
				+ "emocionAnsiedad = ?, emocionAmor = ?, emocionSorpresa = ?, emocionVerguenza = ?, "
				+ "emocionFrustracion = ?, emocionSatisfaccion = ?, emocionAburrimiento = ?, "
				+ "emocionSerenidad = ?, emocionConfianza = ?, emocionAbrumado = ?, emocionEsperanza = ? " + "WHERE id = ?";

		try (Connection connection = DriverManager.getConnection(DB_URL);
				PreparedStatement pstmt = connection.prepareStatement(updateQuery)) {

			// Set values from the journalEntry object to the PreparedStatement
			pstmt.setString(1, revisionEntry.getEstadoEmocional());
			pstmt.setString(2, revisionEntry.getEstadoEmocionalWhy());
			pstmt.setString(3, revisionEntry.getImportanteParaMi());
			pstmt.setString(4, revisionEntry.getAprendidoSobreMi());
			pstmt.setInt(5, revisionEntry.getValoracionDisciplina());
			pstmt.setInt(6, revisionEntry.getValoracionOrden());
			pstmt.setInt(7, revisionEntry.getValoracionImpulsividad());
			pstmt.setInt(8, revisionEntry.getValoracionConstancia());
			pstmt.setInt(9, revisionEntry.getValoracionTolerancia());
			pstmt.setInt(10, revisionEntry.getValoracionControlPrepotencia());
			pstmt.setInt(11, revisionEntry.getValoracionHonestidad());
			pstmt.setInt(12, revisionEntry.getValoracionAceptacion());
			pstmt.setInt(13, revisionEntry.getValoracionConsecucionObjetivos());
			pstmt.setString(14, revisionEntry.getExplicacionValoracion());
			pstmt.setString(15, revisionEntry.getObjetivosPersonales());
			pstmt.setBoolean(16, revisionEntry.isEmocionAlegria());
			pstmt.setBoolean(17, revisionEntry.isEmocionTristeza());
			pstmt.setBoolean(18, revisionEntry.isEmocionIra());
			pstmt.setBoolean(19, revisionEntry.isEmocionMiedo());
			pstmt.setBoolean(20, revisionEntry.isEmocionAnsiedad());
			pstmt.setBoolean(21, revisionEntry.isEmocionAmor());
			pstmt.setBoolean(22, revisionEntry.isEmocionSorpresa());
			pstmt.setBoolean(23, revisionEntry.isEmocionVerguenza());
			pstmt.setBoolean(24, revisionEntry.isEmocionFrustracion());
			pstmt.setBoolean(25, revisionEntry.isEmocionSatisfaccion());
			pstmt.setBoolean(26, revisionEntry.isEmocionAburrimiento());
			pstmt.setBoolean(27, revisionEntry.isEmocionSerenidad());
			pstmt.setBoolean(28, revisionEntry.isEmocionConfianza());
			pstmt.setBoolean(29, revisionEntry.isEmocionAbrumado());
			pstmt.setBoolean(30, revisionEntry.isEmocionEsperanza());
			pstmt.setInt(31, revisionEntry.getId()); // Make sure this is the last parameter

			// Execute the update and get the number of affected rows
			int affectedRows = pstmt.executeUpdate();
			System.out.println("TopazRepository: Updated row (ID: " + id + ") of table revision successfully");

			if (affectedRows > 0) {
				;
			}
			;

		} catch (SQLException e) {
			e.printStackTrace(); // Print any exceptions for debugging
		}
	}

	public static void deleteRevisionEntry(Long id) {
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
				System.out.println("TopazRepository: Deleted row (ID: " + id + ") of table revision successfully");
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
