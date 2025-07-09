package com.selekode.topaz.model;

public enum Table {
    JOURNAL("journal"),
    REVISION("revision"),
    INNER_WORK_ENTRY("inner_work_entry");

    private final String dbName;

    Table(String dbName) {
        this.dbName = dbName;
    }

    public String getDbName() {
        return dbName;
    }
}
