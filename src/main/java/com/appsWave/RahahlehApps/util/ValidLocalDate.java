package com.appsWave.RahahlehApps.util;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidLocalDate {
    String message() default "Invalid local date format, the expected format is "+NewsUtility.DATE_PATTERN;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
