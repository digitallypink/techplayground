package com.ujuezeoke.techplayground.apis.db;

import com.ujuezeoke.techplayground.apis.model.Member;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlBatch;

import java.util.Collection;

public interface Pp {
    @SqlBatch("insert into members (name, age, trouble_maker) values (:name, " +
            ":age, :troubleMaker)")
    void saveBatch(@BindBean Collection<Member> members);
}
