package com.visfull.bz.emnu;

import java.util.HashMap;
import java.util.Map;

import com.visfull.bz.domain.IEnumDisplay;

public enum TargetType implements IEnumDisplay {
	SP("运营商"), CP("服务提供商"), SERVER("服务人员");
	private String msg;

	private TargetType(String msg) {
		this.msg = msg;
	}

	public String getDisplayName() {
		return msg;
	}
	
    private static final Map<String, TargetType> stringToEnum = new HashMap<String, TargetType>();
    static {
        for(TargetType targetType : values()) {
            stringToEnum.put(targetType.toString(), targetType);
        }
    }
    
    public static TargetType fromString(String symbol) {
        return stringToEnum.get(symbol);
    }
}
