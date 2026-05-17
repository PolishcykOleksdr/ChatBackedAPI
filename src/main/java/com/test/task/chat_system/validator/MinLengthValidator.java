package com.test.task.chat_system.validator;

import com.test.task.chat_system.annotation.MinLength;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * author: user,
 * date: 17.05.2026
 */

public class MinLengthValidator implements ConstraintValidator<MinLength, String> {
    private int minLength;

    @Override
    public void initialize(MinLength constraintAnnotation) {
        minLength = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.length() > minLength;
    }
}
