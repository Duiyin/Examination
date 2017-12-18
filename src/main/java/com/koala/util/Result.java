package com.koala.util;

import java.util.HashMap;
import java.util.Map;

public class Result {
	
	public static Map<String, Object> toUrl(String url) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tourl", url);
		return map;
	}
	
	public static Map<String, Object> success() {
		return set("success");
	}
	
	public static Map<String, Object> failed() {
		return set("failed");
	}
	
	public static Map<String, Object> yes() {
		return set("yes");
	}
	
	public static Map<String, Object> no() {
		return set("no");
	}
	
	private static Map<String, Object> set(String result) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		return map;
	}
	
}
