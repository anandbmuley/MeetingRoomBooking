package com.merobo.dtos;

import java.util.ArrayList;
import java.util.List;

public class TeamTo {

	private String id;
	private String name;
	private List<UserTo> memberTos = new ArrayList<UserTo>();

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

	public List<UserTo> getMemberTos() {
		return memberTos;
	}

	@Override
	public String toString() {
		return "TeamTo [id=" + id + ", name=" + name + ", memberTos="
				+ memberTos + "]";
	}

}
