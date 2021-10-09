package com.ujuezeoke.techplayground.apis.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Locale;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "course")
@Getter
@ToString
public class Course implements WithJson{

    private final String id;
    private final String category;
    private final double price;
    private final String title;

    public Course(CourseCategories courseCategories, double price, String title) {
        this(UUID.randomUUID().toString(), courseCategories, price, title);
    }

    @JsonCreator
    public Course(@JsonProperty("id") String id,
                  @JsonProperty("category") CourseCategories courseCategories,
                  @JsonProperty("price") double price,
                  @JsonProperty("title") String title) {
        this.id = id;
        this.category = courseCategories.name().toLowerCase(Locale.ROOT);
        this.price = price;
        this.title = title;
    }
}
