package com.merobo.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rooms")
public class RoomBean {

    @Id
    private String id;
    private String name;

    public RoomBean() {
    }

    public RoomBean(String name) {
        this.name = name;
    }

    public RoomBean(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
