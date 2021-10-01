package com.ujuezeoke.techplayground.apis.db;

import com.ujuezeoke.techplayground.apis.model.Member;
import com.ujuezeoke.techplayground.apis.utils.MemberListGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;

@Component
public class AllDatabasePopulator {
    private final Collection<DatabasePopulator> databasePopulators;

    @Autowired
    public AllDatabasePopulator(Collection<DatabasePopulator> databasePopulators) {
        this.databasePopulators = databasePopulators;
    }

    @PostConstruct
    public void populate(){
        final List<Member> members = MemberListGenerator.getMembers(250);
        databasePopulators.forEach(databasePopulator -> databasePopulator.saveBatch(members));
    }
}
