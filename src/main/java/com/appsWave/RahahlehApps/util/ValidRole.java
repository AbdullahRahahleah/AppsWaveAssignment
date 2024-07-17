package com.appsWave.RahahlehApps.util;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RoleValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRole  {
    String message() default "Invalid Role, allowed options are NORMAL " +
            "CONTENT_WRITER," +
            "ADMIN ";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
