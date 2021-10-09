package com.ujuezeoke.techplayground.apis.db;

import com.ujuezeoke.techplayground.apis.model.Course;
import com.ujuezeoke.techplayground.apis.model.Member;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.postgres.PostgresPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;

@Component
public class PostgresDbDatabasePopulator implements DatabasePopulator {
    private final Jdbi jdbi;
    private final PostgresJdbiDataAccess postgresJdbiDataAccess;

    @Autowired
    public PostgresDbDatabasePopulator(@Value("${postgresdb.uri}") String connectionString) {
        jdbi = Jdbi.create(connectionString)
                .installPlugin(new PostgresPlugin())
                .installPlugin(new SqlObjectPlugin());
        postgresJdbiDataAccess = jdbi.onDemand(PostgresJdbiDataAccess.class);
    }

    @Override
    public void saveMembersBatch(Collection<Member> members) {
        postgresJdbiDataAccess.saveMembers(members);
        postgresJdbiDataAccess.saveMemberCourses(members);
    }

    @Override
    public void saveCourseBatch(Collection<Course> courses) {
        postgresJdbiDataAccess.saveCourses(courses);
    }

    @Override
    public void create() {
        jdbi.open()
                .createScript("CREATE TABLE IF NOT EXISTS members (" +
                        "id varchar not null PRIMARY KEY," +
                        "name varchar," +
                        "age int," +
                        "trouble_maker boolean)")
                .execute();
        jdbi.open()
                .createScript("CREATE TABLE IF NOT EXISTS courses (" +
                        "id varchar not null PRIMARY KEY," +
                        "category varchar," +
                        "title varchar," +
                        "price float)")
                .execute();
        jdbi.open()
                .createScript("CREATE TABLE IF NOT EXISTS members_courses (" +
                        "id varchar not null PRIMARY KEY," +
                        "member_id varchar," +
                        "course_id varchar," +
                        "constraint fk_member FOREIGN KEY(member_id) REFERENCES members(id)," +
                        "constraint fk_courses FOREIGN KEY(course_id) REFERENCES courses(id))")
                .execute();


    }
}
