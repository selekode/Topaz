package com.selekode.topaz.config;

import org.springframework.stereotype.Component;

@Component
public class DatabaseConfig {
    
    public static final String DB_URL = "jdbc:sqlite:src/main/resources/database/topazdatabase.db";
}

