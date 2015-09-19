package com.merobo.beans;

public class MemberBean {

	private String name;

	public MemberBean(String name) {
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
