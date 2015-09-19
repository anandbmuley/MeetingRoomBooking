package com.merobo.dtos;

import java.util.ArrayList;
import java.util.List;

public class TeamTo {

	private String id;
	private String name;
	private String memberName;
	private List<MemberTo> memberTos = new ArrayList<MemberTo>();

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

	public List<MemberTo> getMemberTos() {
		return memberTos;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((memberName == null) ? 0 : memberName.hashCode());
		result = prime * result
				+ ((memberTos == null) ? 0 : memberTos.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TeamTo other = (TeamTo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (memberName == null) {
			if (other.memberName != null)
				return false;
		} else if (!memberName.equals(other.memberName))
			return false;
		if (memberTos == null) {
			if (other.memberTos != null)
				return false;
		} else if (!memberTos.equals(other.memberTos))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TeamTo [id=" + id + ", name=" + name + ", memberName="
				+ memberName + ", memberTos=" + memberTos + "]";
	}

}
