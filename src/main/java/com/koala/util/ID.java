package com.koala.util;

import java.util.UUID;

public class ID {

	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	// 截取前十位数字
	public static String Intercept() {
		return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
	}
}
