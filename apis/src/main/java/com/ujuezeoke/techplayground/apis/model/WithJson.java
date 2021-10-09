package com.ujuezeoke.techplayground.apis.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public interface WithJson {
    ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    @SneakyThrows
    default String asJson() {
        return OBJECT_MAPPER
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(this);
    }

    @SneakyThrows
    static <T> T fromJson(String json, Class<T> tClass) {
        return OBJECT_MAPPER.readValue(json, tClass);
    }
}
