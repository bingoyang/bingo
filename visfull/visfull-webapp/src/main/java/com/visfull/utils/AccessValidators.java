package com.visfull.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import com.google.gson.reflect.TypeToken;
import com.visfull.bz.domain.BzCallRecord;
import com.visfull.bz.emnu.TargetType;
import com.visfull.system.domain.AuthSession;


public class AccessValidators {
	private final static Set<String> SPURL = new HashSet<String>();
	private final static Set<String> CPURL = new HashSet<String>();
	private final static Set<String> SERVERURL = new HashSet<String>();
	
	static{
		SPURL.add("/serviceproviderlist");
		SPURL.add("/servers");
		SPURL.add("/addoperator");
		SPURL.add("/addserviceprovider");
		SPURL.add("/addserver");
		SPURL.add("/deleteoperator");
		SPURL.add("/deleteprovider");
		SPURL.add("/deleteserver");
		SPURL.add("/updateoperator");
		SPURL.add("/updateserviceprovider");
		SPURL.add("/updateserver");
		SPURL.add("/uploadcallrecord");
		SPURL.add("/uploadservicerecord");
		CPURL.add("/servers");
		CPURL.add("/addserver");
		CPURL.add("/deleteserver");
		CPURL.add("/updateserver");
		CPURL.add("/updateserviceprovider");
		CPURL.add("/uploadservicerecord");
		CPURL.add("/uploadcallrecord");
		SERVERURL.add("/updateserver");
		SERVERURL.add("/uploadservicerecord");
		SERVERURL.add("/uploadcallrecord");
	}
	
	public static boolean validAccessUrl(HttpSession session,String url){
		String code = (String) session.getAttribute("code");
		TargetType userType = (TargetType) session.getAttribute("userType");
		boolean result = false;
		if(code!=null&&!"".equals(code)){
			switch (userType) {
			case SP:
				result = SPURL.contains(url);
				break;
			case CP:
				result = CPURL.contains(url);
				break;
			case SERVER:
				result = SERVERURL.contains(url);
				break;
			default:
				break;
			}
		}
		return result;
	}
	
	public static boolean validAccessUrl(AuthSession session,String url){
		String data = session.getData();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		if(data!=null&&!"".equals(data)){
			dataMap = JsonUtils.fromJson(data, new TypeToken<Map<String, Object>>(){}.getType());
		}
		String code = (String) dataMap.get("code");
		TargetType userType = TargetType.fromString((String)dataMap.get("userType"));
		boolean result = false;
		if(code!=null&&!"".equals(code)){
			switch (userType) {
			case SP:
				result = SPURL.contains(url);
				break;
			case CP:
				result = CPURL.contains(url);
				break;
			case SERVER:
				result = SERVERURL.contains(url);
				break;
			default:
				break;
			}
		}
		return result;
	}
	
}
