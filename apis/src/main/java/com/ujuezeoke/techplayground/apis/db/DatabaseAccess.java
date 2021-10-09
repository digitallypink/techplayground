package com.ujuezeoke.techplayground.apis.db;

import com.ujuezeoke.techplayground.apis.model.Course;
import com.ujuezeoke.techplayground.apis.model.Member;

import java.util.Collection;

public interface DatabaseAccess {
    Collection<Member> getMembers();
    Collection<Member> getMembers(int limit);
    Collection<Course> getCourses();
    Collection<Course> getCourses(int limit);
}
