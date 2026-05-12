package com.selekode.topaz.bootstrap;

import java.nio.file.Files;
import java.nio.file.Path;

public class AppBootstrap {

    public static void initialize() {
        System.out.println("PROCESS: Initializing Topaz");

        int port = PortManager.findAvailablePort();
        System.setProperty("server.port", String.valueOf(port));
        System.setProperty("app.active.port", String.valueOf(port));
        System.out.println("PORT: " + String.valueOf(port));

        String dataDir = AppDirectories.getDataDirectory();
        createDirectories(dataDir);
        System.setProperty("app.data.dir", dataDir);
        System.out.println("APPDATA_DIRECTORY: " + String.valueOf(dataDir));
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