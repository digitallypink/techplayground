package com.ujuezeoke.techplayground.apis.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.ToString;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "member")
@Getter
@ToString
public class Member implements WithJson {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final String id;
    private final String name;
    private final int age;
    private final boolean troubleMaker;
    private final Collection<Course> courses;

    public Member(String name, int age, boolean troubleMaker) {
        this(UUID.randomUUID().toString(), name, age, troubleMaker, new ArrayList<>());
    }

    public Member(String name, int age, boolean troubleMaker, Collection<Course> courses) {
        this(UUID.randomUUID().toString(), name, age, troubleMaker, courses);
    }

    @JsonCreator
    public Member(@JsonProperty("id") String id,
                  @JsonProperty("name") String name,
                  @JsonProperty("age") int age,
                  @JsonProperty("troubleMaker") boolean troubleMaker,
                  @JsonProperty("courses") Collection<Course> courses) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.troubleMaker = troubleMaker;
        this.courses = courses;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addCourse(Collection<Course> newCourses) {
        courses.addAll(newCourses);
    }

}
