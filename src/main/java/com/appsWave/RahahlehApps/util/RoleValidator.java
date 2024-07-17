package com.appsWave.RahahlehApps.util;

import com.appsWave.RahahlehApps.entities.Role;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashSet;
import java.util.Set;

public class RoleValidator  implements ConstraintValidator<ValidRole, String> {

    private static final Set<String> ROLES_ENUM = new HashSet<>();

    static {
        for(Role roleEnum:Role.values()) {
            ROLES_ENUM.add(roleEnum.name());
        }
    }


    @Override
    public void initialize(ValidRole constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value != null && ROLES_ENUM.contains(value.toUpperCase());
    }
}
