package com.ujuezeoke.techplayground.apis.controller;

import com.ujuezeoke.techplayground.apis.db.DatabaseAccess;
import com.ujuezeoke.techplayground.apis.model.Course;
import com.ujuezeoke.techplayground.apis.utils.CsvWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


@RestController
@RequestMapping("/courses")
public class CoursesController {

    private final DatabaseAccess databaseAccess;

    @Autowired
    public CoursesController(DatabaseAccess databaseAccess) {
        this.databaseAccess = databaseAccess;
    }

    @GetMapping(path = "/json", produces = "application/json")
    public Collection<Course> getCoursesAsJson() {
        return databaseAccess.getCourses();
    }

    @GetMapping(path = "/xml", produces = "text/xml")
    public Collection<Course> getCoursesAsXml() {
        return databaseAccess.getCourses();
    }

    @GetMapping(path = "/courses.csv", produces = "text/csv")
    public String getCoursesAsCsv() {
        return CsvWriter.toCsv(databaseAccess.getCourses(), Course.class);
    }
}
