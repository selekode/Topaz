package com.selekode.topaz.database;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseCreator {

    public static void main(String[] args) {
        // Define the directory and database file
        DatabaseVariables.updateVariables();
        String dataDirectoryPath = "C:\\Topaz\\data";
        String DB_PATH_FILE = DatabaseVariables.DB_PATH_FILE;
        String DB_PATH = DatabaseVariables.DB_PATH;
        String DB_URL = DatabaseVariables.DB_URL;

        // Check if the directory exists, if not, create it
        File directory = new File(dataDirectoryPath);
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("TopazDB: Topaz Data Directory created successfully: " + dataDirectoryPath);
            } else {
                System.out.println("TopazDB: Failed to create Topaz Data Directory: " + dataDirectoryPath);
                return;
            }
        } else {
            System.out.println("TopazDB: Topaz Data Directory already exists: " + dataDirectoryPath);
        }
        
        // Check if the database path file exists, if not, create it
        File databasePathFile = new File(DB_PATH_FILE);
        if(databasePathFile.exists()) {
            System.out.println("TopazDB: Database path file already exists: " + DB_PATH_FILE);
            // For some reason, even if the file does exist, it doesn't detect it
        } else if (!databasePathFile.exists()) {
            System.out.println("TopazDB: Database path file" + DB_PATH + "DOES NOT already exists: " + DB_PATH_FILE);
            System.out.println("TopazDB: Creating Database Path file");
            String dbPathFileContent = "C:\\Topaz\\data\\topaz_database_default.db";

            try {
                Path path = Paths.get(DB_PATH_FILE);
                
                // Ensure the parent directory exists
                Files.createDirectories(path.getParent());
                
                // Write content to the file
                Files.writeString(path, dbPathFileContent);
                
                System.out.println("File created at: " + path.toAbsolutePath());
            } catch (IOException e) {
                System.err.println("Error writing file: " + e.getMessage());
            }            
        }
        
        // Check if the database file exists, if not, create it
        File databaseFile = new File(DB_PATH);
        if(databaseFile.exists()) {
            System.out.println("TopazDB: Database file already exists: " + DB_PATH);
            // For some reason, even if the file does exist, it doesn't detect it
        } else if (!databaseFile.exists()) {
            System.out.println("TopazDB: Database file: " + DB_PATH +  " DOES NOT already exist: ");
            System.out.println("TopazDB: Creating Database file");
            String createJournalTableSQL = 
                    "CREATE TABLE IF NOT EXISTS journal (" +
                    "id INTEGER NOT NULL UNIQUE, " +
                    "date INTEGER NOT NULL, " +
                    "title TEXT NOT NULL, " +
                    "contentGeneral TEXT, " +
                    "contentSaludFisica TEXT, " +
                    "contentBienestarMental TEXT, " +
                    "contentRelacionesSociales TEXT, " +
                    "contentCarreraProfesional TEXT, " +
                    "contentEstabilidadFinanciera TEXT, " +
                    "contentCrecimientoPersonal TEXT, " +
                    "contentPasatiemposCreatividad TEXT, " +
                    "contentEspiritualidadProposito TEXT, " +
                    "contentRecreacionDiversion TEXT, " +
                    "contentContribucionLegado TEXT, " +
                    "contentErroresCometidos TEXT, " +
                    "PRIMARY KEY(id AUTOINCREMENT)" +
                    ");";
            
            String createRevisionTableSQL = 
                    "CREATE TABLE IF NOT EXISTS revision (" +
                    "id INTEGER NOT NULL UNIQUE, " +
                    "date INTEGER NOT NULL, " +
                    "estadoEmocional TEXT, " +
                    "estadoEmocionalWhy TEXT, " +
                    "importanteParaMi TEXT, " +
                    "aprendidoSobreMi TEXT, " +
                    "valoracionDisciplina INTEGER, " +
                    "valoracionOrden INTEGER, " +
                    "valoracionImpulsividad INTEGER, " +
                    "valoracionConstancia INTEGER, " +
                    "valoracionTolerancia INTEGER, " +
                    "valoracionControlPrepotencia INTEGER, " +
                    "valoracionHonestidad INTEGER, " +
                    "valoracionAceptacion INTEGER, " +
                    "valoracionConsecucionObjetivos INTEGER, " +
                    "explicacionValoracion TEXT, " +
                    "objetivosPersonales TEXT, " +
                    "emocionAlegria BOOLEAN DEFAULT 0, " +
                    "emocionTristeza BOOLEAN DEFAULT 0, " +
                    "emocionIra BOOLEAN DEFAULT 0, " +
                    "emocionMiedo BOOLEAN DEFAULT 0, " +
                    "emocionAnsiedad BOOLEAN DEFAULT 0, " +
                    "emocionAmor BOOLEAN DEFAULT 0, " +
                    "emocionSorpresa BOOLEAN DEFAULT 0, " +
                    "emocionVerguenza BOOLEAN DEFAULT 0, " +
                    "emocionFrustracion BOOLEAN DEFAULT 0, " +
                    "emocionSatisfaccion BOOLEAN DEFAULT 0, " +
                    "emocionAburrimiento BOOLEAN DEFAULT 0, " +
                    "emocionSerenidad BOOLEAN DEFAULT 0, " +
                    "emocionConfianza BOOLEAN DEFAULT 0, " +
                    "emocionAbrumado BOOLEAN DEFAULT 0, " +
                    "emocionEsperanza BOOLEAN DEFAULT 0, " +
                    "PRIMARY KEY(id AUTOINCREMENT)" +
                    ");";

            // Connect to the database and create the tables
            try (Connection connection = DriverManager.getConnection(DB_URL);
                 Statement statement = connection.createStatement()) {

                // Execute the SQL statements to create the tables
                statement.execute(createJournalTableSQL);
                statement.execute(createRevisionTableSQL);

                System.out.println("TopazDB: Database and tables created successfully!");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
