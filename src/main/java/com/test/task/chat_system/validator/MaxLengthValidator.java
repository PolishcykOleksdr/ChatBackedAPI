package com.test.task.chat_system.validator;

import com.test.task.chat_system.annotation.MaxLength;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * author: user,
 * date: 17.05.2026
 */

public class MaxLengthValidator implements ConstraintValidator<MaxLength, String> {
    private int maxLength;

    @Override
    public void initialize(MaxLength constraintAnnotation) {
        maxLength = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.length() > maxLength;
    }
}