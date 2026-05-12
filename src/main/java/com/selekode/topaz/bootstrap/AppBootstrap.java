package com.selekode.topaz.bootstrap;

import java.nio.file.Files;
import java.nio.file.Path;

public class AppBootstrap {

    public static void initialize() {

        PortManager.killPortIfOccupied(8080);

        String dataDir = AppDirectories.getDataDirectory();

        createDirectories(dataDir);

        System.setProperty("app.data.dir", dataDir);
    }

    private static void createDirectories(String dataDir) {

        try {

            Files.createDirectories(Path.of(dataDir));

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to create application directories",
                    e
            );
        }
    }
}