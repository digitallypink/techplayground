package com.ujuezeoke.techplayground.apis.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.ToString;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "member")
@Getter
@ToString
public class Member {

    private final String name;
    private final int age;
    private final boolean troubleMaker;

    @JsonCreator
    public Member(@JsonProperty("name") String name,
                  @JsonProperty("age") int age,
                  @JsonProperty("troubleMaker") boolean troubleMaker) {
        this.name = name;
        this.age = age;
        this.troubleMaker = troubleMaker;
    }

    @SneakyThrows
    public String asJson() {
        return new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(this);
    }
}
