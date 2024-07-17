package com.appsWave.RahahlehApps.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DateValidator implements ConstraintValidator<ValidLocalDate, String> {
    private static final  DateTimeFormatter formatter;
    static {
        formatter = DateTimeFormatter.ofPattern(NewsUtility.DATE_PATTERN);
    }

    @Override
    public void initialize(ValidLocalDate constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(value ==null || value.isEmpty())
            return false;
        try {
            //If parsing done with no exceptions, so no problem
            LocalDate.parse(value, formatter);
            return true;
        }catch (Exception e){
            return false;
        }

    }


}