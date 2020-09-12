package com.merobo.beans;

import com.merobo.dtos.RoomDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rooms")
public class Room {

    @Id
    private String id;
    private String name;
    private Boolean hasProjector;
    private Boolean hasAc;
    private Integer capacity;

    public Room() {
    }

    public Room map(RoomDto roomDto) {
        this.name = roomDto.getName();
        this.hasProjector = roomDto.getHasProjector();
        this.hasAc = roomDto.getHasAc();
        this.capacity = roomDto.getCapacity();
        return this;
    }

    public Room(String name, Boolean hasProjector, Boolean hasAc, Integer capacity) {
        this.name = name;
        this.hasProjector = hasProjector;
        this.hasAc = hasAc;
        this.capacity = capacity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean hasProjector() {
        return hasProjector;
    }

    public Boolean hasAc() {
        return hasAc;
    }

    public Integer getCapacity() {
        return capacity;
    }
}
