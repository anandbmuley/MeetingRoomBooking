package com.merobo.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "teams")
public class TeamBean {

    @Id
    private String id;
    private String name;
    private List<UserBean> members;

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

    public List<UserBean> getMembers() {
        return members;
    }

    public void setMembers(List<UserBean> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "TeamBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", members=" + members +
                '}';
    }
}
