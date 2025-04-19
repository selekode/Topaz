package com.selekode.topaz.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.selekode.topaz.database.DatabaseVariables;
import com.selekode.topaz.database.DatabaseCreator;

@Service
public class SettingsService {
	public static String getCurrentDatabasePath() throws IOException {
		Path pathFile = Paths.get(DatabaseVariables.DB_PATH_FILE);
	    String dataBaseCurrentPath = Files.readString(pathFile);
	    
		return dataBaseCurrentPath;
		
	}
	
	public static void updateDatabasePath(MultipartFile file) {
		if (!file.isEmpty()) {
	        // This only gives us the file name, not the full path (browser security)
	        String filename = file.getOriginalFilename();

	        // Write the filename to the path file (if that makes sense in your case)
	        Path configPath = Paths.get(DatabaseVariables.DB_PATH_FILE);
	        try {
	            Files.writeString(configPath, filename, StandardOpenOption.TRUNCATE_EXISTING);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	        DatabaseCreator.main(null);
	    }
	}

}
