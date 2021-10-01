package com.ujuezeoke.techplayground.apis.db;

import com.ujuezeoke.techplayground.apis.model.Member;

import java.util.Collection;

public interface DatabasePopulator {
    void saveBatch(Collection<Member> members);
    void create();
}
