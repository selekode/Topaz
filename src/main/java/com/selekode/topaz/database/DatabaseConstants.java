package com.selekode.topaz.database;

import org.springframework.stereotype.Component;

@Component
public class DatabaseConstants {
    static String userHome = System.getProperty("user.home");
    public static final String DB_URL = "jdbc:sqlite:" + userHome + "/Topaz/data/topazdatabase.db";
}

