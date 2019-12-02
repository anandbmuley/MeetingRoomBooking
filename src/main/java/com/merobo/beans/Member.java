package com.merobo.beans;

public class Member {

    private String name;

    public Member(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MemberBean [name=" + name + "]";
    }

}
