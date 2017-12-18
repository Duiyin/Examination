package com.koala.util;

import java.util.Arrays;
import java.util.Objects;

public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = -4754310636975621327L;
	private final String code;
	private final Object[] params;

	public ServiceException(String code) {
		super("Service Message Code: " + code);
		this.code = code;
		this.params = new Object[0];
	}

	public ServiceException(String code, Object... params) {
		super("Service Message Code: " + code, parseCause(params));
		this.code = code;
		this.params = parseParams(params);
	}

	private static Throwable parseCause(Object[] params) {
		if (Objects.nonNull(params) && params.length > 0) {
			Object lastParam = params[params.length - 1];
			if (Objects.nonNull(lastParam) && lastParam instanceof Throwable) {
				return (Throwable) lastParam;
			}
		}
		return null;
	}

	private Object[] parseParams(Object[] params) {
		if (Objects.nonNull(params) && params.length > 0) {
			Object lastParam = params[params.length - 1];
			if (Objects.nonNull(lastParam) && lastParam instanceof Throwable) {
				return Arrays.copyOfRange(params, 0, params.length - 1);
			} else {
				return params;
			}
		} else {
			return new Object[0];
		}
	}

	public String getCode() {
		return code;
	}

	public Object[] getParams() {
		return params;
	}
}
