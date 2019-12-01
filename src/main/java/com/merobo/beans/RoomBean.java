package com.merobo.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rooms")
public class RoomBean {

    @Id
    private String id;
    private String name;
    private Boolean hasProjector;
    private Boolean hasAc;
    private Integer capacity;

    public RoomBean() {
    }

    public RoomBean(String name, Boolean hasProjector, Boolean hasAc, Integer capacity) {
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
