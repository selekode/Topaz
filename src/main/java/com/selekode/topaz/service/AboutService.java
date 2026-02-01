package com.selekode.topaz.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AboutService {
    @Value("${app.data.dir}")
    private String appDataDirectory;

    public String getDatabasePath() {
        String databasePath = new String();
        databasePath = appDataDirectory;

        return databasePath;
    }
}
