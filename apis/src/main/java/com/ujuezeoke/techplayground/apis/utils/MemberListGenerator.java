package com.ujuezeoke.techplayground.apis.utils;

import com.github.javafaker.Faker;
import com.ujuezeoke.techplayground.apis.model.Course;
import com.ujuezeoke.techplayground.apis.model.Member;
import org.apache.commons.lang3.RandomUtils;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MemberListGenerator {
    private static final Faker FAKER = new Faker(Locale.ENGLISH);

    private MemberListGenerator(){}

    public static List<Member> getMembers(int maxSize, List<Course> courses) {
        return Stream.generate(() -> new Member(FAKER.name().fullName(),
                RandomUtils.nextInt(18, 60), RandomUtils.nextBoolean(), CoursesListGenerator.subList(courses)))
                .limit(maxSize)
                .collect(Collectors.toList());
    }
}
