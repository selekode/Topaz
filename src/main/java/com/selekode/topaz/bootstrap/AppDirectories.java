package com.selekode.topaz.bootstrap;

import java.nio.file.Path;

public class AppDirectories {

    public static String getDataDirectory() {

        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {

            return Path.of(
                    System.getenv("LOCALAPPDATA"),
                    "Topaz",
                    "data"
            ).toString();
        }

        if (os.contains("mac")) {

            return Path.of(
                    System.getProperty("user.home"),
                    "Library",
                    "Application Support",
                    "Topaz",
                    "data"
            ).toString();
        }

        return Path.of(
                System.getProperty("user.home"),
                ".local",
                "share",
                "Topaz",
                "data"
        ).toString();
    }
}
