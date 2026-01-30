package com.selekode.topaz.database;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;

@Component
public class InitializeDatabase {

    @Value("${app.data.dir}")
    private String dataDir;

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @PostConstruct
    public void init() {
        try {
            createDataDirectoryIfNotExists();
            createDatabaseIfNotExists();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize database", e);
        }
    }

    private void createDataDirectoryIfNotExists() throws Exception {
        Path path = Paths.get(dataDir);

        if (Files.notExists(path)) {
            Files.createDirectories(path);
            System.out.println("Created data directory: " + path);
        }
    }

    private void createDatabaseIfNotExists() throws Exception {
        // SQLite creates the DB file automatically when connecting
        try (Connection conn = DriverManager.getConnection(datasourceUrl)) {
            if (conn != null) {
                System.out.println("Database ready");
            }
        }
    }
}
