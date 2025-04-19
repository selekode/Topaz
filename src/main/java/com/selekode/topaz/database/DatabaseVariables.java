package com.selekode.topaz.database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;

@Component
public class DatabaseVariables {
	public static String DB_PATH_FILE = "C:\\Topaz\\data\\topazdatabase_path.txt";
    public static String DB_PATH;
    public static String DB_URL;
    
    public static void updateVariables() {
    	try {
            DB_PATH = Files.readString(Paths.get(DB_PATH_FILE)).trim();
            DB_URL = "jdbc:sqlite:" + DB_PATH;
            System.out.println("DB_URL: " + DB_URL);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    	System.out.println("TopazDB: Database Variables Updated");
    }
    
    public static String getDatabaseUrl() {
        updateVariables();
        System.out.println("TopazDB: Database URL: " + DB_URL );
		return DB_URL;
    }
}


