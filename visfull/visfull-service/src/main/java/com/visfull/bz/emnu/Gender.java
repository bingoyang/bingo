package com.visfull.bz.emnu;

import com.visfull.bz.domain.IEnumDisplay;

public enum Gender implements IEnumDisplay {
	MALE("男"), FEMALE("女");
	private String msg;

	private Gender(String msg) {
		this.msg = msg;
	}

	public String getDisplayName() {
		return msg;
	}

}