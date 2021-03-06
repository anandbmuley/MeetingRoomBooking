package com.merobo.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class TeamDto {

    private String id;
    private String name;
    private List<UserDto> memberTos = new ArrayList<UserDto>();

    @JsonCreator
    public TeamDto(@JsonProperty String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserDto> getMemberTos() {
        return memberTos;
    }

    @Override
    public String toString() {
        return "TeamTo [id=" + id + ", name=" + name + ", memberTos=" + memberTos + "]";
    }

}
