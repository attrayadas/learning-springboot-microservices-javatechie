package com.attraya.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class CourseTypeValidator implements ConstraintValidator<CourseTypeValidation, String> {

    @Override
    public boolean isValid(String coursetype, ConstraintValidatorContext constraintValidator) {
        List<String> list = List.of("LIVE", "RECORDED");
        return list.contains(coursetype);
    }
}
