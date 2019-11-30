package com.merobo.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RoomDto {

    private String id;
    private String name;

    public RoomDto(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @JsonCreator
    public RoomDto(@JsonProperty String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
