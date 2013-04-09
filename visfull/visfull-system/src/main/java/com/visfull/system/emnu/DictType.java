package com.visfull.system.emnu;

import java.util.HashMap;
import java.util.Map;

import com.visfull.system.domain.IEnumDisplay;

public enum DictType implements IEnumDisplay {
	XIAOQU("小区"),SHANGQUAN("商圈"),OTHER("其他");
	private String msg;

	private DictType(String msg) {
		this.msg = msg;
	}

	public String getDisplayName() {
		return msg;
	}
	
    private static final Map<String, DictType> stringToEnum = new HashMap<String, DictType>();
    static {
        for(DictType targetType : values()) {
            stringToEnum.put(targetType.toString(), targetType);
        }
    }
    public static DictType fromString(String symbol) {
        return stringToEnum.get(symbol);
    }
}
