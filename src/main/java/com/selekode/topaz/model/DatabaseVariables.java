package com.selekode.topaz.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DatabaseVariables {
	public static String DB_PATH_FILE = "C:\\Topaz\\data\\topazdatabase_path.txt";
    public static String DB_PATH;
    public static String DB_URL;
    
	public String getDB_PATH_FILE() {
		return DB_PATH_FILE;
	}
	public void setDB_PATH_FILE(String dB_PATH_FILE) {
		DB_PATH_FILE = dB_PATH_FILE;
	}
	public String getDB_PATH() {
		return DB_PATH;
	}
	public void setDB_PATH(String dB_PATH) {
		DB_PATH = dB_PATH;
	}
	public String getDB_URL() {
		return DB_URL;
	}
	public void setDB_URL(String dB_URL) {
		DB_URL = dB_URL;
	}
	
	public DatabaseVariables(String dB_PATH_FILE, String dB_PATH, String dB_URL) {
		super();
		DB_PATH_FILE = dB_PATH_FILE;
		DB_PATH = dB_PATH;
		DB_URL = dB_URL;
	}
}
