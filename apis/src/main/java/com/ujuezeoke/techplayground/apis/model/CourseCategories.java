package com.ujuezeoke.techplayground.apis.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Locale;

public enum CourseCategories {
    BUSINESS, HOBBIES, INFORMATION_TECHNOLOGY;

    @JsonCreator
    public static CourseCategories from(String name){
        return CourseCategories.valueOf(name.toUpperCase(Locale.ROOT));
    }
}
