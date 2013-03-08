package com.visfull.bz.emnu;

import com.visfull.bz.domain.IEnumDisplay;

public enum CallType implements IEnumDisplay {
	CALLIN("呼入"), CALLOUT("呼出");
	private String msg;

	private CallType(String msg) {
		this.msg = msg;
	}

	public String getDisplayName() {
		return msg;
	}
}
