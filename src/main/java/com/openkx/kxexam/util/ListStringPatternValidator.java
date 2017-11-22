package com.openkx.kxexam.util;

import java.util.Iterator;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

public class ListStringPatternValidator
        implements ConstraintValidator<ListStringPattern, List<String>> {
    private String pattern;
    private String message;

    public void initialize(ListStringPattern constraintAnnotation) {
        this.pattern = constraintAnnotation.regexp();
        this.message = constraintAnnotation.message();
    }

    public boolean isValid(List<String> list, ConstraintValidatorContext constraintContext) {
        if (list == null) {
            return true;
        }
        String errorString = null;
        boolean isValid = true;
        if (pattern == null || "".equals(pattern.trim())) {
            isValid = false;
            if (!isValid) {
                constraintContext.disableDefaultConstraintViolation();
                constraintContext.buildConstraintViolationWithTemplate("指定的正则表达式为空")
                        .addConstraintViolation();
            }
            return isValid;
        } else {
            Iterator<String> iter = list.iterator();
            while (iter.hasNext()) {
                String string = iter.next();
                if (!string.matches(pattern)) {
                    isValid = false;
                    errorString = string;
                    break;
                }
            }
        }
        if (!isValid) {
            constraintContext.disableDefaultConstraintViolation();
            String message = this.message;
            if (StringUtils.isBlank(message)) {
                message = "List<String>为空，或者其中的" + errorString + "不符合正则表达式：" + pattern;;
            }
            constraintContext.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        }
        return isValid;
    }

}