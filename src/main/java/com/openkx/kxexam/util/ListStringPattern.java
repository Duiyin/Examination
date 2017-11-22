package com.openkx.kxexam.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 自定义校验器
 * 校验List<String>中的参数
 * <pre>
 * @ListStringPattern(regexp = "", message = "")
 * </pre>
 * @author rong.e
 *
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ListStringPatternValidator.class)
@Documented
public @interface ListStringPattern {

    String message() default "string in list no match pattern";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String regexp();

    /**
     * Defines several {@link ListStringPattern} annotations on the same element.
     * 
     * @see ListStringPattern
     */
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        ListStringPattern[] value();
    }

}