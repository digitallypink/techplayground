package com.ujuezeoke.techplayground.apis.db;

import com.ujuezeoke.techplayground.apis.utils.MemberListGenerator;

public class DataGenerator {
    private final DatabasePopulator databasePopulator;

    public DataGenerator(DatabasePopulator databasePopulator) {
        this.databasePopulator = databasePopulator;
    }

    public void generateData() {
        this.databasePopulator.saveBatch(MemberListGenerator.getMembers(250));
    }
}
