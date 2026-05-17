package com.test.task.chat_system.validator;

import com.test.task.chat_system.annotation.ValidId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * author: user,
 * date: 17.05.2026
 */

public class ValidIdValidator implements ConstraintValidator<ValidId, Long> {
    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return value > 0;
    }
}