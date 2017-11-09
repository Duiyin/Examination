package com.openkx.kxexam.util;

import java.util.UUID;

public class ID {
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
