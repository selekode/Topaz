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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.selekode.topaz.database.DatabaseConstants;
import com.selekode.topaz.model.PersonalRatings;
import com.selekode.topaz.model.ActivityPerDayOfWeekDTO;
import com.selekode.topaz.model.EmotionFrequencyDTO;
import com.selekode.topaz.model.Table;
import com.selekode.topaz.utils.StatsUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class StatsRepository {

	@Value("${spring.datasource.url}")
	private String datasourceUrl;

	public int findJournalEntryCountAllTime() {
		int journalEntryCount = 0;

		// SQL query to count rows in the 'journal' table
		String sql = "SELECT COUNT(*) FROM journal";

		// Database connection
		try (Connection conn = DriverManager.getConnection(datasourceUrl);
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

	public int findRevisionEntryCountAllTime() {
		int revisionEntryCount = 0;

		// SQL query to count rows in the 'journal' table
		String sql = "SELECT COUNT(*) FROM revision";

		// Database connection
		try (Connection conn = DriverManager.getConnection(datasourceUrl);
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

	public int findInnerWorkEntryCountAllTime() {
		int innerWorkEntryCount = 0;

		// SQL query to count rows in the 'journal' table
		String sql = "SELECT COUNT(*) FROM inner_work_entry";

		// Database connection
		try (Connection conn = DriverManager.getConnection(datasourceUrl);
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			// Check if the result set has a value
			if (rs.next()) {
				int rowCount = rs.getInt(1); // Get the count of rows
				innerWorkEntryCount = rowCount;
				System.out.println("Number of rows in 'inner_work_entry' table: " + rowCount);
			}

		} catch (SQLException e) {
			System.err.println("Error accessing the database: " + e.getMessage());
		}

		return innerWorkEntryCount;
	}

	public int findJournalEntryCountDateRange(long dateStart, long dateEnd) {
		int journalEntryCount = 0;
		System.out.println("Searching for rows in journal between:" + dateStart + " and " + dateEnd);

		// SQLite query to count the rows between date1 and date2
		String sql = "SELECT COUNT(*) FROM journal WHERE date BETWEEN ? AND ?";

		// Database connection
		try (Connection connection = DriverManager.getConnection(datasourceUrl);
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

	public int findRevisionEntryCountDateRange(long dateStart, long dateEnd) {
		int revisionEntryCount = 0;
		System.out.println("Searching for rows in revision between:" + dateStart + " and " + dateEnd);

		// SQLite query to count the rows between date1 and date2
		String sql = "SELECT COUNT(*) FROM revision WHERE date BETWEEN ? AND ?";

		// Database connection
		try (Connection connection = DriverManager.getConnection(datasourceUrl);
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

	public int findInnerWorkEntryCountDateRange(long dateStart, long dateEnd) {
		int innerWorkEntryCount = 0;
		System.out.println("Searching for rows in inner_work_entry between:" + dateStart + " and " + dateEnd);

		// SQLite query to count the rows between date1 and date2
		String sql = "SELECT COUNT(*) FROM inner_work_entry WHERE date BETWEEN ? AND ?";

		// Database connection
		try (Connection connection = DriverManager.getConnection(datasourceUrl);
				PreparedStatement stmt = connection.prepareStatement(sql)) {

			stmt.setLong(1, dateStart);
			stmt.setLong(2, dateEnd);

			try (ResultSet rs = stmt.executeQuery()) {
				// Check if the result set has a value
				if (rs.next()) {
					int rowCount = rs.getInt(1); // Get the count of rows
					innerWorkEntryCount = rowCount;
					System.out.println("Number of rows in 'inner_work_entry' table: " + rowCount);
				}
			}

		} catch (SQLException e) {
			System.err.println("Error accessing the database: " + e.getMessage());
		}
		return innerWorkEntryCount;
	}


	public ActivityPerDayOfWeekDTO findEntryCountPerDayGenericDateRange(long dateStart, long dateEnd, Table table) {
		int mondayEntryCount = 0;
		int tuesdayEntryCount = 0;
		int wednesdayEntryCount = 0;
		int thursdayEntryCount = 0;
		int fridayEntryCount = 0;
		int saturdayEntryCount = 0;
		int sundayEntryCount = 0;

		ActivityPerDayOfWeekDTO entryCountPerDay = null;

		String sql = "SELECT date, strftime('%w', date, 'unixepoch') AS weekday, COUNT(*) AS entry_count "
				+ "FROM " + table.getDbName() + " WHERE date BETWEEN ? AND ? " + "GROUP BY date, weekday " + // Group by date as well
				// to retain individual
				// dates
				"ORDER BY date, weekday;";

		try (Connection conn = DriverManager.getConnection(datasourceUrl);
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
					case 0 -> sundayEntryCount += count;
					case 1 -> mondayEntryCount += count;
					case 2 -> tuesdayEntryCount += count;
					case 3 -> wednesdayEntryCount += count;
					case 4 -> thursdayEntryCount += count;
					case 5 -> fridayEntryCount += count;
					case 6 -> saturdayEntryCount += count;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		entryCountPerDay = new ActivityPerDayOfWeekDTO(mondayEntryCount, tuesdayEntryCount,
				wednesdayEntryCount, thursdayEntryCount, fridayEntryCount,
				saturdayEntryCount, sundayEntryCount, "", 0);

		return entryCountPerDay;

	}

	public ActivityPerDayOfWeekDTO findEntryCountPerDayAllTime(Table table) {
		int mondayEntryCount = 0;
		int tuesdayEntryCount = 0;
		int wednesdayEntryCount = 0;
		int thursdayEntryCount = 0;
		int fridayEntryCount = 0;
		int saturdayEntryCount = 0;
		int sundayEntryCount = 0;

		ActivityPerDayOfWeekDTO entryCountPerDay = null;

		String sql = "SELECT strftime('%w', date, 'unixepoch') AS weekday, COUNT(*) AS entry_count FROM " + table.getDbName() +" GROUP BY weekday ORDER BY weekday;";

		try (Connection conn = DriverManager.getConnection(datasourceUrl);
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				int weekday = rs.getInt("weekday");
				int count = rs.getInt("entry_count");

				switch (weekday) {
				case 0 -> sundayEntryCount = count;
				case 1 -> mondayEntryCount = count;
				case 2 -> tuesdayEntryCount = count;
				case 3 -> wednesdayEntryCount = count;
				case 4 -> thursdayEntryCount = count;
				case 5 -> fridayEntryCount = count;
				case 6 -> saturdayEntryCount = count;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		entryCountPerDay = new ActivityPerDayOfWeekDTO(mondayEntryCount, tuesdayEntryCount,
				wednesdayEntryCount, thursdayEntryCount, fridayEntryCount,
				saturdayEntryCount, sundayEntryCount, "", 0);

		return entryCountPerDay;

	}

	public EmotionFrequencyDTO findEmotionCountAllTime() {
	    String sql = """
	                SELECT
	                    SUM(CASE WHEN emocion_alegria = 1 THEN 1 ELSE 0 END) AS emocionAlegriaCount,
	                    SUM(CASE WHEN emocion_tristeza = 1 THEN 1 ELSE 0 END) AS emocionTristezaCount,
	                    SUM(CASE WHEN emocion_ira = 1 THEN 1 ELSE 0 END) AS emocionIraCount,
	                    SUM(CASE WHEN emocion_miedo = 1 THEN 1 ELSE 0 END) AS emocionMiedoCount,
	                    SUM(CASE WHEN emocion_ansiedad = 1 THEN 1 ELSE 0 END) AS emocionAnsiedadCount,
	                    SUM(CASE WHEN emocion_amor = 1 THEN 1 ELSE 0 END) AS emocionAmorCount,
	                    SUM(CASE WHEN emocion_sorpresa = 1 THEN 1 ELSE 0 END) AS emocionSorpresaCount,
	                    SUM(CASE WHEN emocion_verguenza = 1 THEN 1 ELSE 0 END) AS emocionVerguenzaCount,
	                    SUM(CASE WHEN emocion_frustracion = 1 THEN 1 ELSE 0 END) AS emocionFrustracionCount,
	                    SUM(CASE WHEN emocion_satisfaccion = 1 THEN 1 ELSE 0 END) AS emocionSatisfaccionCount,
	                    SUM(CASE WHEN emocion_aburrimiento = 1 THEN 1 ELSE 0 END) AS emocionAburrimientoCount,
	                    SUM(CASE WHEN emocion_serenidad = 1 THEN 1 ELSE 0 END) AS emocionSerenidadCount,
	                    SUM(CASE WHEN emocion_confianza = 1 THEN 1 ELSE 0 END) AS emocionConfianzaCount,
	                    SUM(CASE WHEN emocion_abrumado = 1 THEN 1 ELSE 0 END) AS emocionAbrumadoCount,
	                    SUM(CASE WHEN emocion_esperanza = 1 THEN 1 ELSE 0 END) AS emocionEsperanzaCount
	                FROM revision;
	            """;

	    EmotionFrequencyDTO emotionFrequency = new EmotionFrequencyDTO(
	            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	            "", 0, "", 0, "", 0, "", 0
	    );

	    try (Connection conn = DriverManager.getConnection(datasourceUrl);
	         PreparedStatement pstmt = conn.prepareStatement(sql);
	         ResultSet rs = pstmt.executeQuery()) {

	        if (rs.next()) {
	            emotionFrequency.setEmocionAlegriaCount(rs.getInt("emocionAlegriaCount"));
	            emotionFrequency.setEmocionTristezaCount(rs.getInt("emocionTristezaCount"));
	            emotionFrequency.setEmocionIraCount(rs.getInt("emocionIraCount"));
	            emotionFrequency.setEmocionMiedoCount(rs.getInt("emocionMiedoCount"));
	            emotionFrequency.setEmocionAnsiedadCount(rs.getInt("emocionAnsiedadCount"));
	            emotionFrequency.setEmocionAmorCount(rs.getInt("emocionAmorCount"));
	            emotionFrequency.setEmocionSorpresaCount(rs.getInt("emocionSorpresaCount"));
	            emotionFrequency.setEmocionVerguenzaCount(rs.getInt("emocionVerguenzaCount"));
	            emotionFrequency.setEmocionFrustracionCount(rs.getInt("emocionFrustracionCount"));
	            emotionFrequency.setEmocionSatisfaccionCount(rs.getInt("emocionSatisfaccionCount"));
	            emotionFrequency.setEmocionAburrimientoCount(rs.getInt("emocionAburrimientoCount"));
	            emotionFrequency.setEmocionSerenidadCount(rs.getInt("emocionSerenidadCount"));
	            emotionFrequency.setEmocionConfianzaCount(rs.getInt("emocionConfianzaCount"));
	            emotionFrequency.setEmocionAbrumadoCount(rs.getInt("emocionAbrumadoCount"));
	            emotionFrequency.setEmocionEsperanzaCount(rs.getInt("emocionEsperanzaCount"));
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return emotionFrequency;
	}


	public EmotionFrequencyDTO findEmotionCountDateRange(Long startDate, Long endDate) {
    String sql = """
            SELECT
                SUM(CASE WHEN emocion_alegria = 1 THEN 1 ELSE 0 END) AS emocionAlegriaCount,
                SUM(CASE WHEN emocion_tristeza = 1 THEN 1 ELSE 0 END) AS emocionTristezaCount,
                SUM(CASE WHEN emocion_ira = 1 THEN 1 ELSE 0 END) AS emocionIraCount,
                SUM(CASE WHEN emocion_miedo = 1 THEN 1 ELSE 0 END) AS emocionMiedoCount,
                SUM(CASE WHEN emocion_ansiedad = 1 THEN 1 ELSE 0 END) AS emocionAnsiedadCount,
                SUM(CASE WHEN emocion_amor = 1 THEN 1 ELSE 0 END) AS emocionAmorCount,
                SUM(CASE WHEN emocion_sorpresa = 1 THEN 1 ELSE 0 END) AS emocionSorpresaCount,
                SUM(CASE WHEN emocion_verguenza = 1 THEN 1 ELSE 0 END) AS emocionVerguenzaCount,
                SUM(CASE WHEN emocion_frustracion = 1 THEN 1 ELSE 0 END) AS emocionFrustracionCount,
                SUM(CASE WHEN emocion_satisfaccion = 1 THEN 1 ELSE 0 END) AS emocionSatisfaccionCount,
                SUM(CASE WHEN emocion_aburrimiento = 1 THEN 1 ELSE 0 END) AS emocionAburrimientoCount,
                SUM(CASE WHEN emocion_serenidad = 1 THEN 1 ELSE 0 END) AS emocionSerenidadCount,
                SUM(CASE WHEN emocion_confianza = 1 THEN 1 ELSE 0 END) AS emocionConfianzaCount,
                SUM(CASE WHEN emocion_abrumado = 1 THEN 1 ELSE 0 END) AS emocionAbrumadoCount,
                SUM(CASE WHEN emocion_esperanza = 1 THEN 1 ELSE 0 END) AS emocionEsperanzaCount
            FROM revision
            WHERE date BETWEEN ? AND ?;
            """;

    EmotionFrequencyDTO emotionFrequency = new EmotionFrequencyDTO(
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            "", 0, "", 0, "", 0, "", 0
    );

    try (Connection conn = DriverManager.getConnection(datasourceUrl);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setLong(1, startDate);
        pstmt.setLong(2, endDate);

        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                emotionFrequency.setEmocionAlegriaCount(rs.getInt("emocionAlegriaCount"));
                emotionFrequency.setEmocionTristezaCount(rs.getInt("emocionTristezaCount"));
                emotionFrequency.setEmocionIraCount(rs.getInt("emocionIraCount"));
                emotionFrequency.setEmocionMiedoCount(rs.getInt("emocionMiedoCount"));
                emotionFrequency.setEmocionAnsiedadCount(rs.getInt("emocionAnsiedadCount"));
                emotionFrequency.setEmocionAmorCount(rs.getInt("emocionAmorCount"));
                emotionFrequency.setEmocionSorpresaCount(rs.getInt("emocionSorpresaCount"));
                emotionFrequency.setEmocionVerguenzaCount(rs.getInt("emocionVerguenzaCount"));
                emotionFrequency.setEmocionFrustracionCount(rs.getInt("emocionFrustracionCount"));
                emotionFrequency.setEmocionSatisfaccionCount(rs.getInt("emocionSatisfaccionCount"));
                emotionFrequency.setEmocionAburrimientoCount(rs.getInt("emocionAburrimientoCount"));
                emotionFrequency.setEmocionSerenidadCount(rs.getInt("emocionSerenidadCount"));
                emotionFrequency.setEmocionConfianzaCount(rs.getInt("emocionConfianzaCount"));
                emotionFrequency.setEmocionAbrumadoCount(rs.getInt("emocionAbrumadoCount"));
                emotionFrequency.setEmocionEsperanzaCount(rs.getInt("emocionEsperanzaCount"));
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return emotionFrequency;
}

	public List<PersonalRatings> findPersonalRatingsAllTime() {
	    List<PersonalRatings> personalRatings = new ArrayList<>();

	    // SQL query with snake_case column names
	    String sql = "SELECT valoracion_disciplina, valoracion_orden, valoracion_impulsividad, "
	               + "valoracion_constancia, valoracion_tolerancia, valoracion_control_prepotencia, "
	               + "valoracion_honestidad, valoracion_aceptacion, valoracion_consecucion_objetivos "
	               + "FROM revision";

	    try (Connection conn = DriverManager.getConnection(datasourceUrl);
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	            int valoracionDisciplina = rs.getInt("valoracion_disciplina");
	            int valoracionOrden = rs.getInt("valoracion_orden");
	            int valoracionImpulsividad = rs.getInt("valoracion_impulsividad");
	            int valoracionConstancia = rs.getInt("valoracion_constancia");
	            int valoracionTolerancia = rs.getInt("valoracion_tolerancia");
	            int valoracionControlPrepotencia = rs.getInt("valoracion_control_prepotencia");
	            int valoracionHonestidad = rs.getInt("valoracion_honestidad");
	            int valoracionAceptacion = rs.getInt("valoracion_aceptacion");
	            int valoracionConsecucionObjetivos = rs.getInt("valoracion_consecucion_objetivos");

	            PersonalRatings ratings = new PersonalRatings(
	                valoracionDisciplina, valoracionOrden, valoracionImpulsividad,
	                valoracionConstancia, valoracionTolerancia, valoracionControlPrepotencia,
	                valoracionHonestidad, valoracionAceptacion, valoracionConsecucionObjetivos
	            );

	            personalRatings.add(ratings);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return personalRatings;
	}

	public List<PersonalRatings> findPersonalRatingsDateRange(long dateStart, long dateEnd) {
	    List<PersonalRatings> personalRatings = new ArrayList<>();

	    // SQL query with snake_case column names and date column
	    String sql = "SELECT valoracion_disciplina, valoracion_orden, valoracion_impulsividad, "
	               + "valoracion_constancia, valoracion_tolerancia, valoracion_control_prepotencia, "
	               + "valoracion_honestidad, valoracion_aceptacion, valoracion_consecucion_objetivos "
	               + "FROM revision "
	               + "WHERE date >= ? AND date <= ?";

	    try (Connection conn = DriverManager.getConnection(datasourceUrl);
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        // Set the parameters for the date range
	        pstmt.setLong(1, dateStart);
	        pstmt.setLong(2, dateEnd);

	        // Execute the query
	        try (ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                int valoracionDisciplina = rs.getInt("valoracion_disciplina");
	                int valoracionOrden = rs.getInt("valoracion_orden");
	                int valoracionImpulsividad = rs.getInt("valoracion_impulsividad");
	                int valoracionConstancia = rs.getInt("valoracion_constancia");
	                int valoracionTolerancia = rs.getInt("valoracion_tolerancia");
	                int valoracionControlPrepotencia = rs.getInt("valoracion_control_prepotencia");
	                int valoracionHonestidad = rs.getInt("valoracion_honestidad");
	                int valoracionAceptacion = rs.getInt("valoracion_aceptacion");
	                int valoracionConsecucionObjetivos = rs.getInt("valoracion_consecucion_objetivos");

	                PersonalRatings ratings = new PersonalRatings(
	                    valoracionDisciplina, valoracionOrden, valoracionImpulsividad,
	                    valoracionConstancia, valoracionTolerancia, valoracionControlPrepotencia,
	                    valoracionHonestidad, valoracionAceptacion, valoracionConsecucionObjetivos
	                );

	                personalRatings.add(ratings);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return personalRatings;
	}

	public Map<String, PersonalRatings> findRatingsDatedAllTime() {
	    Map<String, PersonalRatings> personalRatingsMap = new LinkedHashMap<>();

	    // SQL query with snake_case column names and date column
	    String sql = "SELECT date, valoracion_disciplina, valoracion_orden, valoracion_impulsividad, "
	               + "valoracion_constancia, valoracion_tolerancia, valoracion_control_prepotencia, "
	               + "valoracion_honestidad, valoracion_aceptacion, valoracion_consecucion_objetivos "
	               + "FROM revision "
	               + "ORDER BY date ASC";

	    try (Connection conn = DriverManager.getConnection(datasourceUrl);
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	            String date = StatsUtils.convertDateToString_ddMMMyyy(rs.getLong("date"));

	            int valoracionDisciplina = rs.getInt("valoracion_disciplina");
	            int valoracionOrden = rs.getInt("valoracion_orden");
	            int valoracionImpulsividad = rs.getInt("valoracion_impulsividad");
	            int valoracionConstancia = rs.getInt("valoracion_constancia");
	            int valoracionTolerancia = rs.getInt("valoracion_tolerancia");
	            int valoracionControlPrepotencia = rs.getInt("valoracion_control_prepotencia");
	            int valoracionHonestidad = rs.getInt("valoracion_honestidad");
	            int valoracionAceptacion = rs.getInt("valoracion_aceptacion");
	            int valoracionConsecucionObjetivos = rs.getInt("valoracion_consecucion_objetivos");

	            PersonalRatings ratings = new PersonalRatings(
	                valoracionDisciplina, valoracionOrden, valoracionImpulsividad,
	                valoracionConstancia, valoracionTolerancia, valoracionControlPrepotencia,
	                valoracionHonestidad, valoracionAceptacion, valoracionConsecucionObjetivos
	            );

	            personalRatingsMap.put(date, ratings);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return personalRatingsMap;
	}

	public Map<String, PersonalRatings> findRatingsDatedDateRange(long dateStart, long dateEnd) {
    Map<String, PersonalRatings> personalRatingsMap = new LinkedHashMap<>();

    // SQL query with snake_case column names and date column
    String sql = "SELECT date, valoracion_disciplina, valoracion_orden, valoracion_impulsividad, "
               + "valoracion_constancia, valoracion_tolerancia, valoracion_control_prepotencia, "
               + "valoracion_honestidad, valoracion_aceptacion, valoracion_consecucion_objetivos "
               + "FROM revision "
               + "WHERE date >= ? AND date <= ? "
               + "ORDER BY date ASC";

    try (Connection conn = DriverManager.getConnection(datasourceUrl);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        // Set the parameters for the date range
        pstmt.setLong(1, dateStart);
        pstmt.setLong(2, dateEnd);

        // Execute the query
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String date = StatsUtils.convertDateToString_ddMMMyyy(rs.getLong("date"));

                int valoracionDisciplina = rs.getInt("valoracion_disciplina");
                int valoracionOrden = rs.getInt("valoracion_orden");
                int valoracionImpulsividad = rs.getInt("valoracion_impulsividad");
                int valoracionConstancia = rs.getInt("valoracion_constancia");
                int valoracionTolerancia = rs.getInt("valoracion_tolerancia");
                int valoracionControlPrepotencia = rs.getInt("valoracion_control_prepotencia");
                int valoracionHonestidad = rs.getInt("valoracion_honestidad");
                int valoracionAceptacion = rs.getInt("valoracion_aceptacion");
                int valoracionConsecucionObjetivos = rs.getInt("valoracion_consecucion_objetivos");

                PersonalRatings ratings = new PersonalRatings(
                    valoracionDisciplina, valoracionOrden, valoracionImpulsividad,
                    valoracionConstancia, valoracionTolerancia, valoracionControlPrepotencia,
                    valoracionHonestidad, valoracionAceptacion, valoracionConsecucionObjetivos
                );

                personalRatingsMap.put(date, ratings);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return personalRatingsMap;
}

}
