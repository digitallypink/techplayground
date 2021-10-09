package com.ujuezeoke.techplayground.apis.db;

import com.ujuezeoke.techplayground.apis.model.Course;
import com.ujuezeoke.techplayground.apis.model.Member;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlBatch;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.Collection;
import java.util.UUID;

public interface PostgresJdbiDataAccess {
    @SqlBatch("insert into members (id, name, age, trouble_maker) values (:id, :name, " +
            ":age, :troubleMaker)")
    void saveMembers(@BindBean Collection<Member> members);

    @SqlBatch("insert into courses (id, category, title, price) values (:id, " +
            ":category, :title, :price)")
    void saveCourses(@BindBean Collection<Course> members);

    @SqlUpdate("insert into members_courses (id, member_id, course_id) values (:id, :member_id, :course_id)")
    void saveMemberCourses(@Bind("id") String id, @Bind("member_id") String memberId, @Bind("course_id") String courseId);

    default void saveMemberCourses(Collection<Member> members){
        members.forEach(member ->
                member.getCourses().forEach(course -> saveMemberCourses(UUID.randomUUID().toString(),
                        member.getId(), course.getId()))
        );
    }

}
