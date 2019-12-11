package com.merobo.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RoomDto {

    private String id;
    private String name;
    private Boolean hasProjector;
    private Boolean hasAc;
    private Integer capacity;
    private boolean booked;

    @JsonCreator
    public RoomDto(@JsonProperty String id,
                   @JsonProperty String name,
                   @JsonProperty Boolean hasProjector,
                   @JsonProperty Boolean hasAc,
                   @JsonProperty Integer capacity) {
        this.id = id;
        this.name = name;
        this.hasProjector = hasProjector;
        this.hasAc = hasAc;
        this.capacity = capacity;
    }

    public RoomDto(String id, String name, Boolean hasProjector, Boolean hasAc, Integer capacity, boolean booked) {
        this.id = id;
        this.name = name;
        this.hasProjector = hasProjector;
        this.hasAc = hasAc;
        this.capacity = capacity;
        this.booked = booked;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getHasProjector() {
        return hasProjector;
    }

    public Boolean getHasAc() {
        return hasAc;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public boolean isBooked() {
        return booked;
    }
}
