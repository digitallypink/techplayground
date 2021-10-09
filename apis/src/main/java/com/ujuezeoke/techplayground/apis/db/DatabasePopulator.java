package com.ujuezeoke.techplayground.apis.db;

import com.ujuezeoke.techplayground.apis.model.Course;
import com.ujuezeoke.techplayground.apis.model.Member;

import java.util.Collection;

public interface DatabasePopulator {
    void saveMembersBatch(Collection<Member> members);
    void saveCourseBatch(Collection<Course> courses);
    void create();
}
