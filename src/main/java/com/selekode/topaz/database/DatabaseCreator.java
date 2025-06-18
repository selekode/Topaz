package com.selekode.topaz.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseCreator {

	public static void main(String[] args) {
		// Define the directory and database file
		String directoryPath = "C:\\Topaz\\data";
		String dbFilePath = DatabaseConstants.DB_Path_Windows;

		// Check if the directory exists, if not, create it
		File directory = new File(directoryPath);
		if (!directory.exists()) {
			if (directory.mkdirs()) {
				System.out.println("TopazDB: Topaz Data Directory created successfully: " + directoryPath);
			} else {
				System.out.println("TopazDB: Failed to create Topaz Data Directory: " + directoryPath);
				return;
			}
		} else {
			System.out.println("TopazDB: Topaz Data Directory already exists: " + directoryPath);
		}

		// Check if the database file exists, if not, create it
		File databaseFile = new File(dbFilePath);
		if (databaseFile.exists()) {
			System.out.println("TopazDB: Database file already exists: " + dbFilePath);
			// For some reason, even if the file does exist, it doesn't detect it
		} else if (!databaseFile.exists()) {
			System.out.println("TopazDB: Database file DOES NOT already exist: ");
			System.out.println("TopazDB: Creating Database file");
			String createJournalTableSQL = "CREATE TABLE IF NOT EXISTS journal (" + "id INTEGER NOT NULL UNIQUE, "
					+ "date INTEGER NOT NULL, " + "title TEXT NOT NULL, " + "contentGeneral TEXT, "
					+ "contentSaludFisica TEXT, " + "contentBienestarMental TEXT, " + "contentRelacionesSociales TEXT, "
					+ "contentCarreraProfesional TEXT, " + "contentEstabilidadFinanciera TEXT, "
					+ "contentCrecimientoPersonal TEXT, " + "contentPasatiemposCreatividad TEXT, "
					+ "contentEspiritualidadProposito TEXT, " + "contentRecreacionDiversion TEXT, "
					+ "contentContribucionLegado TEXT, " + "contentErroresCometidos TEXT, "
					+ "PRIMARY KEY(id AUTOINCREMENT)" + ");";

			String createRevisionTableSQL = "CREATE TABLE IF NOT EXISTS revision (" + "id INTEGER NOT NULL UNIQUE, "
					+ "date INTEGER NOT NULL, " + "estadoEmocional TEXT, " + "estadoEmocionalWhy TEXT, "
					+ "importanteParaMi TEXT, " + "aprendidoSobreMi TEXT, " + "valoracionDisciplina INTEGER, "
					+ "valoracionOrden INTEGER, " + "valoracionImpulsividad INTEGER, "
					+ "valoracionConstancia INTEGER, " + "valoracionTolerancia INTEGER, "
					+ "valoracionControlPrepotencia INTEGER, " + "valoracionHonestidad INTEGER, "
					+ "valoracionAceptacion INTEGER, " + "valoracionConsecucionObjetivos INTEGER, "
					+ "explicacionValoracion TEXT, " + "objetivosPersonales TEXT, "
					+ "emocionAlegria BOOLEAN DEFAULT 0, " + "emocionTristeza BOOLEAN DEFAULT 0, "
					+ "emocionIra BOOLEAN DEFAULT 0, " + "emocionMiedo BOOLEAN DEFAULT 0, "
					+ "emocionAnsiedad BOOLEAN DEFAULT 0, " + "emocionAmor BOOLEAN DEFAULT 0, "
					+ "emocionSorpresa BOOLEAN DEFAULT 0, " + "emocionVerguenza BOOLEAN DEFAULT 0, "
					+ "emocionFrustracion BOOLEAN DEFAULT 0, " + "emocionSatisfaccion BOOLEAN DEFAULT 0, "
					+ "emocionAburrimiento BOOLEAN DEFAULT 0, " + "emocionSerenidad BOOLEAN DEFAULT 0, "
					+ "emocionConfianza BOOLEAN DEFAULT 0, " + "emocionAbrumado BOOLEAN DEFAULT 0, "
					+ "emocionEsperanza BOOLEAN DEFAULT 0, " + "PRIMARY KEY(id AUTOINCREMENT)" + ");";

			String createInnerWorkEntryTableSQL = "CREATE TABLE IF NOT EXISTS inner_work_entry ("
			        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
			        + "date INTEGER NOT NULL, "
			        + "tag_id INTEGER, "
			        + "title TEXT,"
			        + "content TEXT,"
			        + "FOREIGN KEY (tag_id) REFERENCES inner_work_tags(id) ON DELETE CASCADE);";
			        

			String createInnerWorkTagsTableSQL = "CREATE TABLE IF NOT EXISTS inner_work_tags (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL);";

			// Connect to the database and create the tables
			try (Connection connection = DriverManager.getConnection(dbFilePath);
					Statement statement = connection.createStatement()) {

				// Execute the SQL statements to create the tables
				statement.execute(createJournalTableSQL);
				statement.execute(createRevisionTableSQL);
				statement.execute(createInnerWorkEntryTableSQL);
				statement.execute(createInnerWorkTagsTableSQL);

				System.out.println("TopazDB: Database and tables created successfully!");

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
