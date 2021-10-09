package com.ujuezeoke.techplayground.apis.utils;

import com.github.javafaker.Faker;
import com.ujuezeoke.techplayground.apis.model.Course;
import com.ujuezeoke.techplayground.apis.model.CourseCategories;
import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CoursesListGenerator {
    private static final Faker FAKER = new Faker(Locale.ENGLISH);

    private CoursesListGenerator(){}

    public static List<Course> getCourses(int maxSize) {
        return Stream.generate(() -> new Course(randomCategory(),
                Math.round(RandomUtils.nextDouble(50.0, 1589) * 100.0)/100.0, FAKER.job().title()+" Course"))
                .limit(maxSize)
                .collect(Collectors.toList());
    }

    public static List<Course> subList(List<Course> from){
        List<Course> coursesToReturn = new ArrayList<>();
        int randomSizeOfSubList = RandomUtils.nextInt(1, from.size());

        for (int i = 0; i < randomSizeOfSubList; i++) {
            int randomIndex = RandomUtils.nextInt(0, from.size());
            coursesToReturn.add(from.get(randomIndex));
        }

        return coursesToReturn;
    }

    private static CourseCategories randomCategory() {
        final int randomIndex = RandomUtils.nextInt(0, CourseCategories.values().length);
        return CourseCategories.values()[randomIndex];
    }
}
