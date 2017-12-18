package com.koala.configure;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;

import com.koala.util.ServiceException;


@ControllerAdvice
public class GlobalAdvice {
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalAdvice.class);

	@Autowired
	private MessageSource messageSource;

	@ResponseBody
	@ExceptionHandler(ServiceException.class)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> businessExceptionHandler(ServiceException be) {
		LOGGER.error(be.getMessage(), be);
		final Locale locale = LocaleContextHolder.getLocale();
		final String message = messageSource.getMessage(be.getCode(), be.getParams(), locale);
		return new HashMap<String, Object>() {
			private static final long serialVersionUID = 2127035631102942291L;
			{
				put("result","error");
				put("code", be.getCode());
				put("message", message);
			}
		};
	}

	@ExceptionHandler(MultipartException.class)
	@ResponseBody
	public ServiceException handleControllerException(HttpServletRequest request, Throwable ex) {
		HttpStatus status = getStatus(request);
		return new ServiceException(ex.getMessage(), status);
	}

	/**
	 * bean校验未通过异常
	 * 
	 * @see javax.validation.Valid
	 * @see org.springframework.validation.Validator
	 * @see org.springframework.validation.DataBinder
	 */
	@ResponseBody
	@ExceptionHandler(BindException.class)
	public Map<String, Object> validExceptionHandler(BindException ex) {
		final Locale locale = LocaleContextHolder.getLocale();
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		return new HashMap<String, Object>() {
			private static final long serialVersionUID = -3222861572243435035L;
			{
				put("result", "error");
				for (FieldError error : fieldErrors) {
					String message = messageSource.getMessage(error.getDefaultMessage(), null, locale);
					put(error.getField(), message);
				}
			}
		};
	}

	@ResponseBody
	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> nullPointerExceptionHandler(NullPointerException re) {
		return logAndWrapMessage("System data exception.", re);
	}

	@ResponseBody
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> runtimeExceptionHandler(RuntimeException re) {
		return logAndWrapMessage(re.getMessage(), re);
	}

	@ResponseBody
	@ExceptionHandler(Throwable.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, Object> throwableHandler(Throwable t) {
		return logAndWrapMessage(t.getMessage(), t);
	}

	private Map<String, Object> logAndWrapMessage(String message, Throwable t) {
		long timestamp = System.currentTimeMillis();
		LOGGER.error("Timestamp: {}, Message: {}", timestamp, message, t);
		return new HashMap<String, Object>() {
			private static final long serialVersionUID = 7885275817658870017L;
			{
				put("result", "error");
				put("timestamp", timestamp);
				put("message", message);
			}
		};
	}

	private HttpStatus getStatus(HttpServletRequest request) {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		if (statusCode == null) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return HttpStatus.valueOf(statusCode);
	}
}
